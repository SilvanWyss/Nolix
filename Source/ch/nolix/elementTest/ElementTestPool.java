//package declaration
package ch.nolix.elementTest;

//own imports
import ch.nolix.common.baseTest.TestPool;
import ch.nolix.elementTest.GUITest.GUITestPool;
import ch.nolix.elementTest.colorTest.ColorTestPool;
import ch.nolix.elementTest.containerWidgetTest.ContainerWidgetTestPool;
import ch.nolix.elementTest.financeTest.FinanceTestPool;
import ch.nolix.elementTest.imageTest.ImageTestPool;
import ch.nolix.elementTest.timeTest.TimeTestPool;
import ch.nolix.elementTest.widgetTest.WidgetTestPool;
import ch.nolix.techTest.projectTest.TaskTestPool;

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
			new ImageTestPool(),
			new TaskTestPool(),
			new TimeTestPool(),
			new WidgetTestPool()
		);
	}
}
