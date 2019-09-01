//package declaration
package ch.nolix.techTest.genericMathTest;

import ch.nolix.common.baseTest.TestPool;

//class
public final class GenericMathTestPool extends TestPool {
	
	//constructor
	public GenericMathTestPool() {
		addTestClass(
			ClosedIntervalTest.class,
			ComplexNumberTest.class	
		);
	}
}
