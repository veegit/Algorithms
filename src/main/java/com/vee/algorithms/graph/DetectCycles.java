package com.vee.algorithms.graph;

import static org.testng.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;


/*
 * for all unvisited nodes, visit(n)
 * 
 * visit(n)
 * 
 * if node is marked hardly - return
 * if node is marked softly - not a dag
 * mark node as soft
 * visit(neighbours) of n
 * mark node n as hard and add to visited
 */

public class DetectCycles {	
	private final Map<State, Boolean[]> markers = new HashMap<>();
	private final Map<State, Set<State>> adjMap = new HashMap<>();
		
	public void init(Set<Rule> rules) {
		rules.stream().forEach(r -> adjMap.computeIfAbsent(r.fromState, v -> new HashSet<>()).add(r.toState));
		Arrays.stream(State.values()).forEach(s -> markers.put(s, new Boolean[]{false, false}));
	}
	
	public boolean hasCycles() {
		Set<State> unVisitedNodes = new HashSet<>();
		unVisitedNodes.addAll(markers.keySet());
		boolean hasCycles = false;
		for (State state : unVisitedNodes) {
			hasCycles = visit(state);
			if (hasCycles) {
				break;
			}
		}
		return hasCycles;
	}
	
	private boolean visit(State state) {
		System.out.println("Visiting State " + state);
		if (!adjMap.containsKey(state) || adjMap.get(state).isEmpty()) {
			return false;
		} if (markers.get(state)[1]) {
			return false;
		} else if (markers.get(state)[0]) {
			return true;
		} else {
			boolean hasCycles = false;
			markers.get(state)[0] = true;
			for (State neighbour : adjMap.get(state)) {
				hasCycles = visit(neighbour);
				if (hasCycles) {
					break;
				}
			}
			markers.get(state)[1] = true;
			return hasCycles;
		}
	}

	@Test
	public void testSuccess_1CC() {
		Set<Rule> rules = new HashSet<>();
		rules.add(new Rule(State.OPEN, State.IN_PROGRESS));
		rules.add(new Rule(State.IN_PROGRESS, State.IN_CODE_REVIEW));
		rules.add(new Rule(State.IN_PROGRESS, State.IN_VALIDATION));
		rules.add(new Rule(State.IN_PROGRESS, State.RESOLVED));
		rules.add(new Rule(State.IN_CODE_REVIEW, State.IN_VALIDATION));
		rules.add(new Rule(State.IN_CODE_REVIEW, State.RESOLVED));
		rules.add(new Rule(State.IN_VALIDATION, State.RESOLVED));

		DetectCycles dc = new DetectCycles();
		dc.init(rules);
		assertEquals(dc.hasCycles(), false);
	}
	
	@Test
	public void testSuccess_2CC() {
		Set<Rule> rules = new HashSet<>();
		rules.add(new Rule(State.OPEN, State.IN_PROGRESS));
		rules.add(new Rule(State.IN_PROGRESS, State.IN_CODE_REVIEW));
		rules.add(new Rule(State.IN_PROGRESS, State.IN_VALIDATION));
		rules.add(new Rule(State.IN_PROGRESS, State.RESOLVED));
		rules.add(new Rule(State.IN_CODE_REVIEW, State.IN_VALIDATION));
		rules.add(new Rule(State.IN_CODE_REVIEW, State.RESOLVED));
		rules.add(new Rule(State.IN_VALIDATION, State.RESOLVED));

		rules.add(new Rule(State.OPEN, State.TRIAGE));
		rules.add(new Rule(State.TRIAGE, State.REVIEW));
		rules.add(new Rule(State.REVIEW, State.RESOLVED));

		DetectCycles dc = new DetectCycles();
		dc.init(rules);
		assertEquals(dc.hasCycles(), false);
	}
	
	@Test
	public void testFail_1CC() {
		Set<Rule> rules = new HashSet<>();
		rules.add(new Rule(State.OPEN, State.IN_PROGRESS));
		rules.add(new Rule(State.IN_PROGRESS, State.IN_CODE_REVIEW));
		rules.add(new Rule(State.IN_PROGRESS, State.IN_VALIDATION));
		rules.add(new Rule(State.IN_PROGRESS, State.RESOLVED));
		rules.add(new Rule(State.IN_CODE_REVIEW, State.IN_VALIDATION));
		rules.add(new Rule(State.IN_CODE_REVIEW, State.RESOLVED));
		rules.add(new Rule(State.IN_VALIDATION, State.RESOLVED));
		rules.add(new Rule(State.RESOLVED, State.IN_PROGRESS));

		DetectCycles dc = new DetectCycles();
		dc.init(rules);
		assertEquals(dc.hasCycles(), true);
	}
	
	@Test
	public void testFail_2CC() {
		Set<Rule> rules = new HashSet<>();
		rules.add(new Rule(State.OPEN, State.IN_PROGRESS));
		rules.add(new Rule(State.IN_PROGRESS, State.IN_CODE_REVIEW));
		rules.add(new Rule(State.IN_PROGRESS, State.IN_VALIDATION));
		rules.add(new Rule(State.IN_PROGRESS, State.RESOLVED));
		rules.add(new Rule(State.IN_CODE_REVIEW, State.IN_VALIDATION));
		rules.add(new Rule(State.IN_CODE_REVIEW, State.RESOLVED));
		rules.add(new Rule(State.IN_VALIDATION, State.RESOLVED));

		rules.add(new Rule(State.OPEN, State.TRIAGE));
		rules.add(new Rule(State.TRIAGE, State.REVIEW));
		rules.add(new Rule(State.REVIEW, State.RESOLVED));
		
		rules.add(new Rule(State.REVIEW, State.IN_PROGRESS));
		rules.add(new Rule(State.IN_VALIDATION, State.TRIAGE));

		DetectCycles dc = new DetectCycles();
		dc.init(rules);
		assertEquals(dc.hasCycles(), true);
	}
}

final class Rule {
	final State fromState;
	final State toState;
	
	public Rule(final State fromState, final State toState) {
		this.fromState = fromState;
		this.toState = toState;
	}
}

enum State {
	OPEN,
	IN_PROGRESS,
	IN_CODE_REVIEW,
	IN_VALIDATION,
	RESOLVED,
	
	TRIAGE,
	REVIEW
}