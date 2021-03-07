//package declaration
package ch.nolix.systemtest.dynamicmathtest;

import ch.nolix.common.testing.basetest.TestPool;

//class
public final class DynamicMathTestPool extends TestPool {
	
	//constructor
	public DynamicMathTestPool() {
		super(ClosedIntervalTest.class,	ComplexNumberTest.class);
	}
}
