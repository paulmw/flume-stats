package com.cloudera.flume.stats;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.flume.Event;
import org.junit.Test;

public class CountingInterceptorTest {

	/**
	 * Verify that the call to collect also resets the count.
	 */
	@Test
	public void testCollect() {
		CountingInterceptor ci = new CountingInterceptor();
		assertEquals(0, ci.collect());
		
		ci.intercept((Event) null);
		assertEquals(1, ci.collect());
		assertEquals(0, ci.collect());
		
		ci.intercept((Event) null);
		ci.intercept((Event) null);
		assertEquals(2, ci.collect());
		assertEquals(0, ci.collect());
		
		List<Event> events = new ArrayList<Event>();
		
		events.add(null);
		ci.intercept(events);
		events.clear();
		assertEquals(1, ci.collect());
		assertEquals(0, ci.collect());
		
		
		events.add(null);
		events.add(null);
		ci.intercept(events);
		events.clear();
		assertEquals(2, ci.collect());
		assertEquals(0, ci.collect());
	}

}
