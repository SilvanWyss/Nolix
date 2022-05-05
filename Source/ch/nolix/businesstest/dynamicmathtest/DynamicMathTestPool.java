//package declaration
package ch.nolix.businesstest.dynamicmathtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class DynamicMathTestPool extends TestPool {
	
	//constructor
	public DynamicMathTestPool() {
		super(ClosedIntervalTest.class,	ComplexNumberTest.class, SequenceDefinedBy1PredecessorTest.class);
	}
}
