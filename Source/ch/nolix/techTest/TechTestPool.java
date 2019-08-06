//package declaration
package ch.nolix.techTest;

import ch.nolix.core.baseTest.TestPool;
import ch.nolix.techTest.genericMathTest.GenericMathTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		addTestPool(new GenericMathTestPool());
	}
}
