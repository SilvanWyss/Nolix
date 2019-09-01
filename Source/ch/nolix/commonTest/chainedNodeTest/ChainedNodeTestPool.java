//package declaration
package ch.nolix.commonTest.chainedNodeTest;

import ch.nolix.common.baseTest.TestPool;

//class
public final class ChainedNodeTestPool extends TestPool {
	
	//constructor
	public ChainedNodeTestPool() {
		addTestClass(ChainedNodeTest.class);
	}
}
