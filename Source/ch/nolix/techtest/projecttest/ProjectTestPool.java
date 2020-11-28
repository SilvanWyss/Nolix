//package declaration
package ch.nolix.techtest.projecttest;

//own import
import ch.nolix.common.baseTest.TestPool;

//class
/**
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 20
 */
public final class ProjectTestPool extends TestPool {
	
	//constructor
	/**
	 * Creates a new {@link ProjectTestPool}.
	 */
	public ProjectTestPool() {
		super(ProjectTest.class, TaskTest.class);
	}
}
