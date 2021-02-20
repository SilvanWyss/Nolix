//package declaration
package ch.nolix.systemtest.dynamicmathtest;

//own imports
import ch.nolix.common.basetest.TestPool;

//class
public final class DynamicMathTestPool extends TestPool {
	
	//constructor
	public DynamicMathTestPool() {
		super(ClosedIntervalTest.class,	ComplexNumberTest.class);
	}
}
