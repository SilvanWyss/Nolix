//package declaration
package ch.nolix.elementtest;

//own imports
import ch.nolix.common.basetest.TestPool;
import ch.nolix.elementtest.colortest.ColorTestPool;
import ch.nolix.elementtest.containerwidgettest.ContainerWidgetTestPool;
import ch.nolix.elementtest.elementenumtest.ElementEnumTestPool;
import ch.nolix.elementtest.financetest.FinanceTestPool;
import ch.nolix.elementtest.graphictest.GraphicTestPool;
import ch.nolix.elementtest.guitest.GUITestPool;
import ch.nolix.elementtest.timetest.TimeTestPool;
import ch.nolix.elementtest.widgettest.WidgetTestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-09-01
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
			new ElementEnumTestPool(),
			new FinanceTestPool(),
			new GraphicTestPool(),
			new GUITestPool(),
			new TimeTestPool(),
			new WidgetTestPool()
		);
	}
}
