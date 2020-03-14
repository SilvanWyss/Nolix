//package declaration
package ch.nolix.techTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.techTest.genericMathTest.GenericMathTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		super(new GenericMathTestPool());
	}
}
