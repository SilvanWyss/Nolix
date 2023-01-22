//package declaration
package ch.nolix.businesstest.mathtest;

//own imports
import ch.nolix.businesstest.mathtest.bigdecimalmathtest.BigDecimalMathTestPool;
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class MathTestPool extends TestPool {
	
	//constructor
	public MathTestPool() {
		super(new BigDecimalMathTestPool());
	}
}
