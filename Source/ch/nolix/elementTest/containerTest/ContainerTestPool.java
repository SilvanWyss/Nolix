//package declaration
package ch.nolix.elementTest.containerTest;

//own imports
import ch.nolix.core.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 20
 */
public final class ContainerTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link ContainerTestPool}.
	 */
	public ContainerTestPool() {
		addTestClass(
			AccordionTest.class,
			SingleContainerTest.class,
			VerticalStackTest.class
		);
	}
}
