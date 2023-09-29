//package declaration
package ch.nolix.coreapitest.programcontrolapitest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.coreapitest.programcontrolapitest.savecontrolapitest.SaveControlApiTestPool;

//class
public final class ProgramControlApiTestPool extends TestPool {
	
	//constructor
	public ProgramControlApiTestPool() {
		super(new SaveControlApiTestPool());
	}
}
