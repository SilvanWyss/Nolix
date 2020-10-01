//package declaration
package ch.nolix.techTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.techTest.genericMathTest.GenericMathTestPool;
import ch.nolix.techTest.projectTest.ProjectTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		super(new GenericMathTestPool(), new ProjectTestPool());
	}
}
