//package declaration
package ch.nolix.techtest.mathtest;

import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.techtest.mathtest.bigdecimalmathtest.BigDecimalMathTestPool;

//class
public final class MathTestPool extends TestPool {
	
	//constructor
	public MathTestPool() {
		super(new BigDecimalMathTestPool());
	}
}
