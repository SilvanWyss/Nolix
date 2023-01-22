//package declaration
package ch.nolix.businesstest;

//own imports
import ch.nolix.businesstest.mathtest.MathTestPool;
import ch.nolix.core.testing.basetest.TestPool;

//class
public final class BusinessTestPool extends TestPool {
	
	//constructor
	public BusinessTestPool() {
		super(new MathTestPool	());
	}
}
