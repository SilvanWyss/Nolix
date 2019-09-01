//package declaration
package ch.nolix.nolixTest;

import ch.nolix.common.baseTest.TestPool;
import ch.nolix.coreTest.CoreTestPool;
import ch.nolix.elementTest.ElementTestPool;
import ch.nolix.systemTest.SystemTestPool;
import ch.nolix.techTest.TechTestPool;

//class
/**
 * A {@link NolixTestPool} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 30
 */
public final class NolixTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link NolixTestPool}.
	 */
	public NolixTestPool() {
		addTestPool(
			new CoreTestPool(),
			new ElementTestPool(),
			new SystemTestPool(),
			new TechTestPool()
		);
	}
}
