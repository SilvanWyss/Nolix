//package declaration
package ch.nolix.commonTest.containerTest;

//own import
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
	 * Creates a new {@link ContainerTestPool}.
	 */
	public ContainerTestPool() {
		super(
			ListTest.class,
			MatrixTest.class,
			ReadContainerTest.class
		);
	}
}
