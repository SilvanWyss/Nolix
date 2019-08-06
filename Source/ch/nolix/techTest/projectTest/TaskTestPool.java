//package declaration
package ch.nolix.techTest.projectTest;

import ch.nolix.core.baseTest.TestPool;

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
