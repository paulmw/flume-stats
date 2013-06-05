package com.cloudera.flume.stats;

import java.util.List;

import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

public class MockInterceptor implements Interceptor {

	private String id;
	
	public MockInterceptor(String id) {
		this.id = id;
	}
	
	@Override
	public void initialize() {}

	@Override
	public Event intercept(Event event) {
		return event;
	}

	@Override
	public List<Event> intercept(List<Event> events) {
		return events;
	}

	@Override
	public void close() {}
	
	public String toString() {
		return id;
	}
}