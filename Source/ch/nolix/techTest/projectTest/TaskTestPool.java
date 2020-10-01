//package declaration
package ch.nolix.techTest.projectTest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 20
 */
public final class TaskTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link TaskTestPool}.
	 */
	public TaskTestPool() {
		super(
			TaskTest.class,
			ProjectTest.class
		);
	}
}
