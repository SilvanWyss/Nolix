//package declaration
package ch.nolix.elementTest.taskTest;

import ch.nolix.primitive.testoid.TestPool;

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
		addTest(
			new TaskTest(),
			new TableauTest()
		);
	}
}
