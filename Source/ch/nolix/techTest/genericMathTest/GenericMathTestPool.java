//package declaration
package ch.nolix.techTest.genericMathTest;

import ch.nolix.core.baseTest.TestPool;

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
