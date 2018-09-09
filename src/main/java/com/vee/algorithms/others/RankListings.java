package com.vee.algorithms.others;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class RankListings {
	public static void main(String args[]) {
		try (Scanner scan = new Scanner(new File("/tmp/cities.csv"))) {
			List<Result> results = new ArrayList<>();
			scan.nextLine();
			scan.useDelimiter("[,\n]");
			while (scan.hasNext()) {
				int a = scan.nextInt();
				int b = scan.nextInt();
				double c = scan.nextDouble(); 
				String d = scan.next();
				results.add(new Result(a,b,c,d));
			}
			List<List<String>> s = new RankListings().paginate(results, 12);
			for (List<String> pg : s) {
				for (String val : pg) {
					System.out.println(val);
				}
				System.out.println("\n\n...\n\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<List<String>> paginate(List<Result> results, int pageSize) {
		int numPages = results.size() / pageSize + (results.size() % pageSize == 0 ? 0 : 1);
		List<List<String>> paginatedList = new ArrayList<>();
		for (int i = 0; i < numPages; i++) {
			paginatedList.add(new ArrayList<>());
		}
		int nextPage = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (Result r : results) {
			if (paginatedList.get(nextPage).size() == pageSize) {
				nextPage++;
			}
			Integer page = map.get(r.host_id);
			if (page == null) {
				page = 0;
			}
			page = Math.max(page, nextPage);
			paginatedList.get(page).add(r.toString());
			map.put(r.host_id, Math.min(page+1, numPages-1));
		}
		return paginatedList;
	}
}

class Result {
	int host_id;
	int listing_id;
	double score;
	String city;
	
	public Result(int host_id,int listing_id,double score,String city) {
		this.host_id = host_id;
		this.listing_id = listing_id;
		this.score = score;
		this.city = city;
	}
	
	@Override
	public String toString() {
		return String.format("%d,%d,%f,%s", host_id, listing_id, score, city);
	}
}