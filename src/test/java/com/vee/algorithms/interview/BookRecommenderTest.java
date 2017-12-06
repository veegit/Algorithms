package com.vee.algorithms.interview;

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

import com.vee.algorithms.interview.Recommender;

public class BookRecommenderTest {
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