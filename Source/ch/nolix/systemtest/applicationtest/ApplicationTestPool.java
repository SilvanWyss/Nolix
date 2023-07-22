//package declaration
package ch.nolix.systemtest.applicationtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.systemtest.applicationtest.maintest.MainTestPool;

//class
public final class ApplicationTestPool extends TestPool {
	
	//constructor
	public ApplicationTestPool() {
		super(new MainTestPool());
	}
}
