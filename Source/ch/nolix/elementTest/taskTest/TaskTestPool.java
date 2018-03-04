//package declaration
package ch.nolix.elementTest.taskTest;

//own import
import ch.nolix.core.testoid.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 20
 */
public final class TaskTestPool extends TestPool {

	//constructor
	/**
	 * Creates new task test pool.
	 */
	public TaskTestPool() {
		addTest(new TaskTest());
	}
}
