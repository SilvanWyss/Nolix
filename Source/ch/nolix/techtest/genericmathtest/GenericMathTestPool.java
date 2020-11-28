//package declaration
package ch.nolix.techtest.genericmathtest;

import ch.nolix.common.basetest.TestPool;

//class
public final class GenericMathTestPool extends TestPool {
	
	//constructor
	public GenericMathTestPool() {
		super(ClosedIntervalTest.class,	ComplexNumberTest.class);
	}
}
