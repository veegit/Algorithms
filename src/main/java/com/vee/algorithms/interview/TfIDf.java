package com.vee.algorithms.interview;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.vee.algorithms.util.Tuple;

/*
 * 
 * public class Search {
    
    Map<String, TfIdf> tfidf = new HashMap<>();
    long numDocuments = 0;
    
    public void buildIndex(List<String> docs) {
        numDocuments = docs.size();
        tokenize(docs);
    }
    
    public void tokenize(List<String> docs) {
        for (String doc : docs) {
            Map<String, Integer> map = tokenize(doc));
            for (Map.Entry e : map.entrySet()) {
                if (tfidf.contains(e.getKey()) {
                    e.getValue().docf++;
                    List list = e.getValue().tf;
                    list.add(new Tf(1, map.getKey());
                } else {
                    TfIdf tfidf = new TfIdf();
                    tfidf.tf.add(new Tf(1, map.getKey());
                    tfidf.put(e.getKey(), tfidf);
                }
            }
        }
    }
    
    public Map<String, Integer> tokenize(String doc) {
        List<String> tokenList = Arrays.toList(doc.split("\W+"));
        Map<String, Integer> map = new HasHMap<>();
        for (String str : tokenList) {
            String e = map.get(str);
            if (e == null) {
                map.put(e, 1);
            } else {
                int freq = map.get(e);
                map.put(e, freq+1);
            }
        }
        return map;
    }
    
    
    public static void main(String[] args) {
        
    }
    
}

class TfIdf {
    List<Tf> tf = new ArrayList<>();
    int docf = 1;
}

class Tf {
    int docId;
    long tf;    
    
    public TfIdf(int docId, long tf) {
        this.docId = docId;
        this.tf = tf;
    }
}



{t1, 1}, {t2, 1}...

{t1 -> [{docid, tf, idf}, {docid, tf, idf}, ..} 
 * 
 * tf = Number of times a term occurs in a document
 * idf = 1 / number of documents the term occurs in
 * 
 * term1 -> {doc1: 1, doc2:2, doc3:1}
 */

//interview = thumbtack

public class TfIDf {
	Map<String, TreeSet<DocumentFrequency>> index = new HashMap<>();
	
	private static class DocumentFrequencyComparator implements Comparator<DocumentFrequency> {
		@Override
		public int compare(DocumentFrequency o1, DocumentFrequency o2) {
			int c = Long.compare(o2.frequency, o1.frequency);
			return c == 0 ? o1.docId.compareTo(o2.docId) : c;
		}	
	}

	private void addToIndex(Map<String, Long> tf, int id) {
		tf.forEach((k,v) -> index
				.computeIfAbsent(k, val -> new TreeSet<DocumentFrequency>(new DocumentFrequencyComparator()))
				.add(new DocumentFrequency(String.valueOf(id), v)));
	}
	
	private Map<String, Long> tokenize(String content) {
		String delims = "[ .,?!;:\"']+";
		Map<String, Long> tf = Arrays.asList(content.toLowerCase().split(delims))
			.stream()
			.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		return tf;
	}
	
	private void printIndex() {
		index.forEach((k,v) -> {
						 			System.out.print(k + " -> {"); 
						 			System.out.print(v.stream().map(t -> t.docId + ":" + t.frequency + ":" + t.score).collect(Collectors.joining(",")));
						 			System.out.print("}\n");
						 		}
					);
	}
	
	private void buildIndex() {
		String docs[] = {
							"This is the first line. I intend to make this line shorter",
							"I am writing a sample program",
							"May be I should think about this deeply since i built this and i am responsible for it"
						};
		for (int i = 0; i< docs.length; i++) {
			Map<String, Long> c = tokenize(docs[i]);
			addToIndex(c, i);
		}
		index.forEach((k, v) -> v.forEach(d -> d.score = (double) d.frequency / v.size()));
		printIndex();
	}
	
	private void search(String[] tokens) {
		List<TreeSet<DocumentFrequency>> list = new ArrayList<>();
		for (String token : tokens) {
			list.add(index.get(token));
		}
		Map<String, Double> map = list.stream().flatMap(Collection::stream).collect(Collectors.toMap(e -> e.docId, e-> e.score, Double::sum));
		System.out.println(map);
	}
	
	public static void main(String args[]) {
		TfIDf td = new TfIDf();
		td.buildIndex();
		td.search(new String[]{"this", "line"});
	}

}

class DocumentFrequency {
	String docId;
	Long frequency;
	double score;
	
	public DocumentFrequency(String docId, Long frequency) {
		this.docId = docId;
		this.frequency = frequency;
	}
}
