package com.vee.algorithms.graph;

import java.util.*;
import java.util.Map.Entry;

public class KosarajuSCC {
	
	public static void main(String[] args) {
		Map<Integer, List<Integer>> adj = new HashMap<>();
		adj.put(1, Arrays.asList(2));
		adj.put(2, Arrays.asList(3,5,6));
		adj.put(3, Arrays.asList(4,7));
		adj.put(4, Arrays.asList(3,8));
		adj.put(5, Arrays.asList(1,6));
		adj.put(6, Arrays.asList(7));
		adj.put(7, Arrays.asList(6));
		adj.put(8, Arrays.asList(4,7));
		Map<Integer, Integer> scc = new KosarajuSCC().scc(adj);
		System.out.println(scc);
	}

	public Map<Integer, Integer> scc(Map<Integer, List<Integer>> adj) {
		Map<Integer, Integer> scc = new HashMap<>();
		Set<Integer> visited = new HashSet<>();
		List<Integer> list = new ArrayList<>();
		dfs(adj.keySet().iterator().next(), adj, list, visited);
		System.out.println(list);
		for (Integer u : list) {
			assign(u, revAdj(adj), u, scc);
		}
		return scc;
	}
	
	public Map<Integer, List<Integer>> revAdj(Map<Integer, List<Integer>> adj) {
		Map<Integer, List<Integer>> revAdj = new HashMap<>();
		adj.entrySet().forEach(
				e-> e.getValue().forEach(
						v -> revAdj.computeIfAbsent(v, 
								k -> new ArrayList<>()).add(e.getKey())));
		return revAdj;
	}
	
	public void assign(Integer u, Map<Integer, List<Integer>> adj, Integer root, Map<Integer, Integer> scc) {
		if (!scc.containsKey(u)) {
			scc.put(u, root);
			adj.get(u).forEach(v -> assign(v, adj, root, scc));
		}
	}
	
	public void dfs(Integer u, Map<Integer, List<Integer>> adj, List<Integer> list, Set<Integer> visited) {
		if (!visited.contains(u)) {
			visited.add(u);
			adj.get(u).forEach(v -> dfs(v, adj, list, visited));
			list.add(0, u);
		}
	}
}
