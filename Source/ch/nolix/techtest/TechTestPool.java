//package declaration
package ch.nolix.techtest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.techtest.genericmathtest.GenericMathTestPool;
import ch.nolix.techtest.projecttest.ProjectTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		super(new GenericMathTestPool(), new ProjectTestPool());
	}
}
