package com.vee.datastructures.applications;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.beust.jcommander.Strings;

//TODO
public class SearchEngine {
	Map<String, Set<TermFrequency>> index = new HashMap<>();
	
	public void buildIndex() {
		
	}
	
	private void tokenize(List<String> docs) {
		for (String docId : docs) {
			Map<String, Long> termFrequency = tokenizeDoc(fetch(docId));
			for (Map.Entry<String, Long> e : termFrequency.entrySet()) {
				TermFrequency tf = new TermFrequency(docId, e.getValue());
				index.computeIfAbsent(e.getKey(), k -> new HashSet<TermFrequency>()).add(tf);
			}
		}
	}
	
	private String fetch(String docId) {
		return "abc";
	}
	
	private Map<String, Long> tokenizeDoc(String content) {
		return Arrays.asList(content.split("\\W+")).stream()
				.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
	}
	
}

class TermFrequency {
	private final String docId;
	private long tf;
	
	public TermFrequency(String docId, long tf) {
		this.docId = docId;
		this.tf = tf;
	}
	
	public void incrementTf(long tf) {
		this.tf += tf;
	}	
}