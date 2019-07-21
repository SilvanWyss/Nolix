//package declaration
package ch.nolix.techTest.projectTest;

//own imports
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
	 * Creates a new task test pool.
	 */
	public TaskTestPool() {
		addTestClass(
			TaskTest.class,
			TableauTest.class
		);
	}
}
