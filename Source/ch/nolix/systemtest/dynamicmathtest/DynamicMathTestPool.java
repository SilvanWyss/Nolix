//package declaration
package ch.nolix.systemtest.dynamicmathtest;

import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DynamicMathTestPool extends TestPool {
	
	//constructor
	public DynamicMathTestPool() {
		super(ClosedIntervalTest.class,	ComplexNumberTest.class);
	}
}
