//package declaration
package ch.nolix.elementTest.containerWidgetsTest;

import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 20
 */
public final class ContainerWidgetTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link ContainerWidgetTestPool}.
	 */
	public ContainerWidgetTestPool() {
		addTestClass(
			AccordionTest.class,
			SingleContainerTest.class,
			VerticalStackTest.class
		);
	}
}
