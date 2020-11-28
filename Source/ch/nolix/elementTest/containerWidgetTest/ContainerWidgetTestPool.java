//package declaration
package ch.nolix.elementTest.containerWidgetTest;

import ch.nolix.common.basetest.TestPool;

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
		super(
			AccordionTest.class,
			SingleContainerTest.class,
			VerticalStackTest.class
		);
	}
}
