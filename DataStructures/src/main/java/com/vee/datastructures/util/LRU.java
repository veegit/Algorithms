package com.vee.datastructures.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

public class LRU<K, V> extends HashMap<K, V> {
	private static final long serialVersionUID = 1L;
	
	private LinkedList<K> list = new LinkedList<K>();
	private long size;
	private Function<K, V> function;
		
	public LRU(long size, Function<K, V> function) {
		this.size = size;
		this.function = function;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		K k = (K) key;
		V value;
		if (super.containsKey(key)) {
			value = super.get(k);
			list.remove(k);
		} else {
			value = fetch(k);
			if (value == null) {
				throw new NoSuchElementException();
			}
			super.put(k, value);
		}
		list.addFirst(k);
		while (list.size() > size) {
			list.removeLast();
		}
		return value;
	}
	
	public List<K> getList() {
		return list;
	}

	private V fetch(K key) {
		return function.apply(key);
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		LRU<Integer, String> lru = new LRU<>(100000, getFunction());
		for (int i = 0; i < Integer.MAX_VALUE/1000; i++) {
			int r = random.nextInt(100000*3);
			lru.get(r);
			if (i%10000 == 0) {
				System.out.println(lru.getList());
			}
		}
	}
	
	private static Function<Integer, String> getFunction() {
		Function<Integer, String> function = (Integer i) -> {
			String word="";
			try(BufferedReader bf = new BufferedReader(new InputStreamReader
					(new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt").openStream()))) {
				 int count = i;
				while ((word = bf.readLine()) != null) {
					if (count-- == 0) {
						break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return word;
		};
		return function;
	}
}