//package declaration
package ch.nolix.elementTest;

import ch.nolix.common.basetest.TestPool;
import ch.nolix.elementTest.GUITest.GUITestPool;
import ch.nolix.elementTest.colorTest.ColorTestPool;
import ch.nolix.elementTest.containerWidgetTest.ContainerWidgetTestPool;
import ch.nolix.elementTest.financeTest.FinanceTestPool;
import ch.nolix.elementTest.graphicTest.GraphicTestPool;
import ch.nolix.elementTest.timeTest.TimeTestPool;
import ch.nolix.elementTest.widgetTest.WidgetTestPool;
import ch.nolix.techtest.projecttest.ProjectTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public final class ElementTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link ElementTestPool}.
	 */
	public ElementTestPool() {
		super(
			new ColorTestPool(),
			new ContainerWidgetTestPool(),
			new FinanceTestPool(),
			new GUITestPool(),
			new GraphicTestPool(),
			new ProjectTestPool(),
			new TimeTestPool(),
			new WidgetTestPool()
		);
	}
}
