//package declaration
package ch.nolix.coreTest.containerTest;

import ch.nolix.core.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public final class ContainerTestPool extends TestPool {

	//constructor
	/**
	 * Creates new container test pool.
	 */
	public ContainerTestPool() {
		addTest(
			new ListTest(),
			new MatrixTest()
		);
	}
}
