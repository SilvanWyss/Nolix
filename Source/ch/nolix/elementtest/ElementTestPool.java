//package declaration
package ch.nolix.elementtest;

import ch.nolix.common.basetest.TestPool;
import ch.nolix.elementtest.colortest.ColorTestPool;
import ch.nolix.elementtest.containerwidgettest.ContainerWidgetTestPool;
import ch.nolix.elementtest.financetest.FinanceTestPool;
import ch.nolix.elementtest.graphictest.GraphicTestPool;
import ch.nolix.elementtest.guitest.GUITestPool;
import ch.nolix.elementtest.timetest.TimeTestPool;
import ch.nolix.elementtest.widgettest.WidgetTestPool;
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
