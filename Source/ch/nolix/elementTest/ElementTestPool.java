//package declaration
package ch.nolix.elementTest;

import ch.nolix.core.testoid.TestPool;
//own imports
import ch.nolix.elementTest.GUITest.GUITestPool;
import ch.nolix.elementTest.colorTest.ColorTestPool;
import ch.nolix.elementTest.coreTest.CoreTestPool;
import ch.nolix.elementTest.financeTest.FinanceTestPool;
import ch.nolix.elementTest.taskTest.TaskTestPool;

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
			new CoreTestPool(),
			new FinanceTestPool(),
			new GUITestPool(),
			new TaskTestPool()
		);
	}
}
