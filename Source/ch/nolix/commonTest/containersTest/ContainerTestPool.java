//package declaration
package ch.nolix.commonTest.containersTest;

import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public final class ContainerTestPool extends TestPool {

	//constructor
	/**
	 * Creates a new container test pool.
	 */
	public ContainerTestPool() {
		addTestClass(
			ListTest.class,
			MatrixTest.class,
			ReadContainerTest.class
		);
	}
}
