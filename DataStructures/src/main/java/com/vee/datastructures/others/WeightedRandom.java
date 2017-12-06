package com.vee.datastructures.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TODO Fix the logic
public class WeightedRandom<T> {
	
	public T getRandomItem(List<Item<T>> items) {
		int sumOfWeights = items.stream().collect(Collectors.summingInt(i -> i.weight));
		int random = new Random().nextInt(sumOfWeights);
		int sum = 0;
		T data = items.get(0).data;
		for (Item<T> item : items) {
			if (random < sum) {
				break;
			}
			data = item.data;
			sum += item.weight;
		}
		return data;
	}
	
	public static void main(String args[]) {
		WeightedRandom<Integer> wr = new WeightedRandom<Integer>();
		List<Item<Integer>> list = new ArrayList<>();
		IntStream.range(1,6).forEach(i -> list.add(new Item<Integer>(i,i)));
		System.out.println(list);
		System.out.println(wr.getRandomItem(list));
	}
}

class Item<T> {
	T data;
	int weight;
	public Item(T data, int weight) {
		this.data = data;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return data + "" + weight;
	}
}
