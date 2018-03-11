//package declaration
package ch.nolix.elementTest;

import ch.nolix.elementTest.GUITest.GUITestPool;
import ch.nolix.elementTest.colorTest.ColorTestPool;
import ch.nolix.elementTest.coreTest.CoreTestPool;
import ch.nolix.elementTest.dataTest.DataTestPool;
import ch.nolix.elementTest.financeTest.FinanceTestPool;
import ch.nolix.elementTest.taskTest.TaskTestPool;
import ch.nolix.primitive.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 30
 */
public final class ElementTestPool extends TestPool {

	//constructor
	/**
	 * Creates new element test pool.
	 */
	public ElementTestPool() {
		addTestPool(
			new ColorTestPool(),
			new CoreTestPool(),
			new DataTestPool(),
			new FinanceTestPool(),
			new GUITestPool(),
			new TaskTestPool()
		);
	}
}
