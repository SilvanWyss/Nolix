//package declaration
package ch.nolix.elementtest.containerwidgettest;

//own import
import ch.nolix.common.basetest.TestPool;

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
