package com.vee.algorithms.interview;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
You have a set of tasks that depend on one another. For example, assuming we had the following 4 tasks (Thumbtack related!):
Follows Community Standards
Post Editing
Validate License
Write Snippet

And the following set of rules:
We don't want to do Post Editing, Validate License or Write Snippet until we do Follows Community Standards
We don't want to do Write Snippet until we Post Editing, Validate License

However, what if we added the rule:
We don't want to do Follows Community Standards until we do Write Snippet
This makes the set of rules and tasks invalid.


public class TaskManagement {
 
 Set<Rule> rules = new HashSet<>();
 Map<Task, List<Task>> adjMap = new HashMap<>();
 Map<Task, boolean> map = new HashMap<>();
 Stack<Task> stack = new LinkedStack<>();
 
 public boolean isValid() {
     for (Rule rule : rules) {
         map.add(rule.fromTask, false);
         map.add(rule.toTask, false);
         if (adjMap.contains(rule.fromTask)) {
             adjMap.get(rule.fromTask).add(rule.getTask);
         } else {
             List<Task> list = new ArrayList<>();
             list.add(rule.toTask);
             adjMap.put(rule.fromTask, list);
         }
     }
     
     for (Task task : map.keys()) {
         while (!stack.isEmpty()) {
         for(Task toTask : adjMap.get(task)) {
             stack.push(toTask);
         }
         
     }
     
     
 }
 
 public void addRule(Task fromTask, Task toTask) throws IllegalArgumentException {
     Rule rule = new Rule(fromTask, toTask);
     if (rules.contains(rule)) {
         throw new IllegalArgumentException();
     }
     rules.add(rule);
 }
}

public class Rule {
 final Task fromTask;
 final Task toTask;
 public Rule(Task fromTask, Task toTask) {
     this.fromTask = fromTask;
     this.toTask = toTask;
 }
 
 @Override
 public int hashCode() {
     return fromTask.hashCode() + toTask.hashCode();
 }
}

enum Task {
 FOLLOW_COMM_STANDARDS,
 POST_EDITING,
 VALIDATE_LICENSE,
 WRITE_SNIPPET
}



* {1,2,3,4,5}
* 
* 
* 1-> 2,3,4
* 2-> 5
**/
//interview = thumbtack
public class DetectCycles {
	int[] incoming = new int[Task.values.length];
	Map<Task, Set<Task>> adj = new HashMap<>();
	Set<Rule> rules;
	Queue<Task> q = new ArrayDeque<>();
	Set<Task> topOrder = new LinkedHashSet<>();
	
	public DetectCycles(Set<Rule> r) {
		rules = r;
		rules.forEach(rule -> {
			adj.computeIfAbsent(rule.from, v -> new HashSet<>()).add(rule.to);
			incoming[rule.to.ordinal()]++;
		});
		IntStream.range(0, incoming.length).filter(i -> incoming[i] == 0).forEach(v -> q.offer(Task.values[v]));
		int count = 0;
		while (!q.isEmpty()) {
			Task t = q.poll();
			topOrder.add(t);
			Set<Task> tasks = adj.getOrDefault(t, new HashSet<>());
			tasks.forEach(to -> {;
				if (--incoming[to.ordinal()] == 0) {
					q.offer(to);
				}
			});
			count++;
		}
		if (count != Task.values.length) {
			System.out.println("Cycle");
		} else {
			System.out.println(topOrder.stream().map(Task::name).collect(Collectors.joining("->")));
		}
	}
	
	public static void main(String args[]) {
		Set<Rule> rules = new HashSet<>();
		rules.add(new Rule(Task.A, Task.B));
		rules.add(new Rule(Task.B, Task.C));
		//rules.add(new Rule(Task.C, Task.A));
		new DetectCycles(rules);
	}
	
}

class Rule {
	Task from;
	Task to;
	
	Rule(Task from, Task to) {
		this.from = from;
		this.to= to;
	}
}
enum Task {
	A,
	B,
	C;
	
	public static final Task[] values = values();
}
