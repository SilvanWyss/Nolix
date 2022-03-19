//package declaration
package ch.nolix.elementtest;

//own imports
import ch.nolix.core.testing.basetest.TestPool;
import ch.nolix.elementtest.elementenumtest.ElementEnumTestPool;
import ch.nolix.elementtest.formatelementtest.FormatElementTestPool;
import ch.nolix.elementtest.guitest.GUITestPool;
import ch.nolix.elementtest.guitest.colortest.ColorTestPool;
import ch.nolix.elementtest.guitest.containerwidgettest.ContainerWidgetTestPool;
import ch.nolix.elementtest.guitest.imagetest.ImageTestPool;
import ch.nolix.elementtest.guitest.widgettest.WidgetTestPool;
import ch.nolix.elementtest.timetest.TimeTestPool;
import ch.nolix.elementtest.tradingtest.TradingTestPool;

//class
/**
 * @author Silvan Wyss
 * @date 2016-09-01
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
			new FormatElementTestPool(),
			new ImageTestPool(),
			new GUITestPool(),
			new TimeTestPool(),
			new TradingTestPool(),
			new WidgetTestPool()
		);
	}
}
