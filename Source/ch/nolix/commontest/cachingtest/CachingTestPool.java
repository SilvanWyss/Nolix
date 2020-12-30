//package declaration
package ch.nolix.commontest.cachingtest;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
public final class CachingTestPool extends TestPool {
	
	//constructor
	public CachingTestPool() {
		super(CachingContainerTest.class);
	}
}
