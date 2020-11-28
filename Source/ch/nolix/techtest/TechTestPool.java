//package declaration
package ch.nolix.techtest;

import ch.nolix.common.basetest.TestPool;
import ch.nolix.techtest.genericmathtest.GenericMathTestPool;
import ch.nolix.techtest.projecttest.ProjectTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		super(new GenericMathTestPool(), new ProjectTestPool());
	}
}
