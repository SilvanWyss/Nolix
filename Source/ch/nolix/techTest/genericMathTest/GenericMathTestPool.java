//package declaration
package ch.nolix.techTest.genericMathTest;

//own import
import ch.nolix.core.testoid.TestPool;

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
