//package declaration
package ch.nolix.techtest;

//own imports
import ch.nolix.common.basetest.TestPool;
import ch.nolix.techtest.genericmathtest.GenericMathTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		super(new GenericMathTestPool());
	}
}
