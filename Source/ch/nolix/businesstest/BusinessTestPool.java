//package declaration
package ch.nolix.businesstest;

//own imports
import ch.nolix.businesstest.bigdecimalmathtest.BigDecimalMathTestPool;
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class BusinessTestPool extends TestPool {
	
	//constructor
	public BusinessTestPool() {
		super(new BigDecimalMathTestPool());
	}
}
