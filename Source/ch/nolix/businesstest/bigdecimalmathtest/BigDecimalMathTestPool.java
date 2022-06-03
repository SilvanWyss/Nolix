//package declaration
package ch.nolix.businesstest.bigdecimalmathtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class BigDecimalMathTestPool extends TestPool {
	
	//constructor
	public BigDecimalMathTestPool() {
		super(ClosedIntervalTest.class,	ComplexNumberTest.class, ComplexSequenceDefinedBy1PredecessorTest.class);
	}
}
