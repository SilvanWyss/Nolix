//package declaration
package ch.nolix.coretest.cachingtest;

import ch.nolix.core.testing.basetest.TestPool;

//class
public final class CachingTestPool extends TestPool {
	
	//constructor
	public CachingTestPool() {
		super(CachingContainerTest.class);
	}
}
