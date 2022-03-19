//package declaration
package ch.nolix.coretest.containertest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
 */
public final class ContainerTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link ContainerTestPool}.
	 */
	public ContainerTestPool() {
		super(
			GapMatrixTest.class,
			LinkedListTest.class,
			MatrixTest.class,
			ReadContainerTest.class,
			SingleContainerTest.class
		);
	}
}
