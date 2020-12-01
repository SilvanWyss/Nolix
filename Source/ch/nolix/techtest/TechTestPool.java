//package declaration
package ch.nolix.techtest;

//own imports
import ch.nolix.common.basetest.TestPool;
import ch.nolix.techtest.dynamicmathtest.DynamicMathTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		super(new DynamicMathTestPool());
	}
}
