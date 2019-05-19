//package declaration
package ch.nolix.elementTest;

//own imports
import ch.nolix.core.testoid.TestPool;
import ch.nolix.elementTest.colorTest.ColorTestPool;
import ch.nolix.elementTest.containerTest.ContainerTestPool;
import ch.nolix.elementTest.coreTest.CoreTestPool;
import ch.nolix.elementTest.financeTest.FinanceTestPool;
import ch.nolix.elementTest.taskTest.TaskTestPool;
import ch.nolix.elementTest.widgetTest.WidgetTestPool;

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
			new ContainerTestPool(),
			new CoreTestPool(),
			new FinanceTestPool(),
			new TaskTestPool(),
			new WidgetTestPool()
		);
	}
}
