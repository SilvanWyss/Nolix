//package declaration
package ch.nolix.elementTest;

import ch.nolix.common.baseTest.TestPool;
import ch.nolix.elementTest.colorTest.ColorTestPool;
import ch.nolix.elementTest.containerWidgetsTest.ContainerWidgetTestPool;
import ch.nolix.elementTest.coreTest.CoreTestPool;
import ch.nolix.elementTest.financeTest.FinanceTestPool;
import ch.nolix.elementTest.widgetsTest.WidgetTestPool;
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
	 * Creates a new element test pool.
	 */
	public ElementTestPool() {
		addTestPool(
			new ColorTestPool(),
			new ContainerWidgetTestPool(),
			new CoreTestPool(),
			new FinanceTestPool(),
			new TaskTestPool(),
			new WidgetTestPool()
		);
	}
}
