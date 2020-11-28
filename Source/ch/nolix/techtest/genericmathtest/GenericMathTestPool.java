//package declaration
package ch.nolix.techtest.genericmathtest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
public final class GenericMathTestPool extends TestPool {
	
	//constructor
	public GenericMathTestPool() {
		super(ClosedIntervalTest.class,	ComplexNumberTest.class);
	}
}
