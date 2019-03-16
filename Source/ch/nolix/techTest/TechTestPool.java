//package declaration
package ch.nolix.techTest;

//own imports
import ch.nolix.core.testoid.TestPool;
import ch.nolix.techTest.genericMathTest.GenericMathTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		addTestPool(new GenericMathTestPool());
	}
}
