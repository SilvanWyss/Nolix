//package declaration
package ch.nolix.elementtest.containerwidgettest;

import ch.nolix.common.testing.basetest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2017-04-01
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
			AligningContainerTest.class,
			SingleContainerTest.class,
			TabContainerTest.class,
			VerticalStackTest.class
		);
	}
}
