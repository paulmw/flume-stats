package com.cloudera.flume.stats;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InterceptorRegistryTest {
	
	@Test
	public void testRegistration() {
		MockInterceptor a = new MockInterceptor("a");
		assertEquals(0, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.register(MockInterceptor.class, a);
		assertEquals(1, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.clear();
	}
	
	@Test
	public void testMultipleRegistration() {
		MockInterceptor a = new MockInterceptor("a");
		assertEquals(0, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.register(MockInterceptor.class, a);
		assertEquals(1, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		MockInterceptor b = new MockInterceptor("b");
		InterceptorRegistry.register(MockInterceptor.class, b);
		assertEquals(2, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.clear();
	}
	
	@Test
	public void testDedupe() {
		MockInterceptor a = new MockInterceptor("a");
		assertEquals(0, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.register(MockInterceptor.class, a);
		assertEquals(1, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.register(MockInterceptor.class, a);
		assertEquals(1, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.clear();
	}
	
	@Test
	public void testDeregistration() {
		MockInterceptor a = new MockInterceptor("a");
		MockInterceptor b = new MockInterceptor("b");
		MockInterceptor c = new MockInterceptor("c");
		InterceptorRegistry.register(MockInterceptor.class, a);
		InterceptorRegistry.register(MockInterceptor.class, b);
		InterceptorRegistry.register(MockInterceptor.class, c);
		assertEquals(3, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.deregister(a);
		assertEquals(2, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.deregister(b);
		assertEquals(1, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.deregister(c);
		assertEquals(0, InterceptorRegistry.getInstances(MockInterceptor.class).size());
		InterceptorRegistry.clear();
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullType() {
		MockInterceptor a = new MockInterceptor("a");
		InterceptorRegistry.register(null, a);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullInstance() {
		InterceptorRegistry.register(MockInterceptor.class, null);
	}
	
}
