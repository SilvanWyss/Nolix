//package declaration
package ch.nolix.coreTest.chainedNodeTest;

import ch.nolix.core.baseTest.TestPool;

//class
public final class ChainedNodeTestPool extends TestPool {
	
	//constructor
	public ChainedNodeTestPool() {
		addTestClass(ChainedNodeTest.class);
	}
}
