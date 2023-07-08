//package declaration
package ch.nolix.techtest;

import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.techtest.mathtest.MathTestPool;

//class
public final class TechTestPool extends TestPool {
	
	//constructor
	public TechTestPool() {
		super(new MathTestPool	());
	}
}
