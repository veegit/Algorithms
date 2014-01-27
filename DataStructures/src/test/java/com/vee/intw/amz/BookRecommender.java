package com.vee.intw.amz;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Before;
import org.junit.Test;

public class BookRecommender {
	Recommender testMock;

	@Before
	public void setUp() {
		testMock = mock(Recommender.class);
	}

	@Test
	public void testString() {
		String str = "Hello! How are you \"Sire\", He said - \'Yes\'";
		System.out.println(str);
		System.out.println(StringEscapeUtils.escapeJavaScript(str));
		System.out.println(StringEscapeUtils.escapeXml(StringEscapeUtils.escapeJavaScript(str)));
	}

	public void testRankedListOfProducts() {
		//using mockito
		String userid = "U";
		String f[] = new String[] {"U1", "U2"};
		String prodForU[] = new String[] {"P1", "P2"};
		String prodForU1[] = new String[] {"P2", "P3"};
		String prodForU2[] = new String[] {"P3", "P4"};
		String result[] = new String[] {"P3", "P4"};
		when(testMock.getFriendsListForUser(any(String.class))).thenReturn(Arrays.asList(f));
		when(testMock.getLibraryForUser(userid)).thenReturn(Arrays.asList(prodForU));
		when(testMock.getLibraryForUser(f[0])).thenReturn(Arrays.asList(prodForU1));
		when(testMock.getLibraryForUser(f[1])).thenReturn(Arrays.asList(prodForU2));

		for (String s: testMock.getFriendsListForUser(userid)) {
			System.out.println(s);
		}
		List<String> p = testMock.recommendations(userid);

		assertEquals(p.size(), 2);
		assertEquals(p, Arrays.asList(result));
	}

	public void testRankedListOfProductsWithNoRecommendations() {
		//using mockito
		Recommender testMock = mock(Recommender.class);
		String userid = "U";
		String f[] = new String[] {"U1", "U2"};
		String prodForU[] = new String[] {"P1", "P2"};
		String prodForU1[] = new String[] {"P1"};
		String prodForU2[] = new String[] {"P2"};
		when(testMock.getFriendsListForUser(eq(userid))).thenReturn(Arrays.asList(f));
		when(testMock.getLibraryForUser(userid)).thenReturn(Arrays.asList(prodForU));
		when(testMock.getLibraryForUser(f[0])).thenReturn(Arrays.asList(prodForU1));
		when(testMock.getLibraryForUser(f[1])).thenReturn(Arrays.asList(prodForU2));

		List<String> p = testMock.recommendations(userid);

		assertEquals(p.size(), 0);
	}

	public void testRankedListOfProductsWithNoFriends() {
		Recommender testMock = mock(Recommender.class);
		//using mockito
		String userid = "U";
		String prodForU[] = new String[] {"P1", "P2"};
		when(testMock.getFriendsListForUser(userid)).thenReturn(new ArrayList<String>());
		when(testMock.getLibraryForUser(userid)).thenReturn(Arrays.asList(prodForU));

		List<String> p = testMock.recommendations(userid);

		assertEquals(p.size(), 0);
	}

	public void testRankedListOfProductsWithNoProduct() {
		Recommender testMock = mock(Recommender.class);
		//using mockito
		String userid = "U";
		String f[] = new String[] {"U1", "U2"};
		String prodForU1[] = new String[] {"P2", "P3"};
		String prodForU2[] = new String[] {"P3", "P4"};
		String result1[] = new String[] {"P3", "P4", "P2"};
		String result2[] = new String[] {"P3", "P2", "P4"};
		when(testMock.getFriendsListForUser(userid)).thenReturn(Arrays.asList(f));
		when(testMock.getLibraryForUser(userid)).thenReturn(new ArrayList<String>());
		when(testMock.getLibraryForUser(f[0])).thenReturn(Arrays.asList(prodForU1));
		when(testMock.getLibraryForUser(f[1])).thenReturn(Arrays.asList(prodForU2));
		List<String> p = testMock.recommendations(userid);

		//assertEquals(p.size(), 3);
		//assertThat(p, anyOf(equalTo(Arrays.asList(result1)), equalTo(Arrays.asList(result2))));
	}
}

class Recommender {
	/*
	Fetch List of all friends F for a User u
	Initialize product Map M with the library of the user U with value as 0
	For each friend f in F
	  fetch list of all products P for f (e.g. P1 for friend f1)
		 For each p in P
		   put it in M with value as 1 if its not present in M else increment by 1

	M now contains product as key and number of friends having the product as value
	Now to sort it in descending order of values and  return results

	Time Complexity = O(UP) where U is the number of Users and P is number of products in the system
	Space complexty = O(P)

	If U ~ P then the O(n^2) and O(n) resp
	*/

	public List<String> recommendations(String userid) {
		System.out.println(userid);
		List<String> friends = this.getFriendsListForUser(userid);
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (String p: getLibraryForUser(userid)) {
			map.put(p, 0);
		}

		for (String f : friends) {
			System.out.println(f);
			for (String p : getLibraryForUser(f)) {
				Integer v = map.get(p);
				if (v == null) {//product doesnt exist
					map.put(p, 1);
				} else if (v != 0) { // If v == 0 then then that product is owned by the user
					map.put(p, v+1);
				}
			}
		}

		List<ProductScore> scores = new ArrayList<ProductScore>();
		for (Entry<String, Integer> e: map.entrySet()) {
			if(e.getValue() != 0) {
				scores.add(new ProductScore(e.getKey(), e.getValue()));
			}
		}

		Collections.sort(scores);
		// We can shave off few cycles above if we only need top 10 or top K products by using a MinHeap as the above operation is nLogn
		//but minHeap gives us nLogK
		List<String> rankedProducts = new ArrayList<String>();
		for (ProductScore ps : scores) {
			rankedProducts.add(ps.product);
		}
		return rankedProducts;
	}

	public List<String> getFriendsListForUser(String user) {
		return new ArrayList<String>();
	}

	public List<String> getLibraryForUser(String user) {
		return new ArrayList<String>();
	}

}

//inner class or can be kept outside to give a recommendation score
	class ProductScore implements Comparable<ProductScore> {
		String product;
		int score;

		ProductScore(String product, int score) {
			this.product = product;
			this.score = score;
		}

		public int compareTo(ProductScore ps) {
			return ps.score - score; //for descending
		}
	}
