//package declaration
package ch.nolix.techTest.projectTest;

import ch.nolix.core.test.Test;
import ch.nolix.tech.project.Task;

//test class
/**
 * A task test is a test for the task class.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public final class TaskTest extends Test {

	//test case
	public void testCase_creation() {
				
		//setup
		final Task task = new Task("My task");
		
		//verification
		expect(task.getTitle()).isEqualTo("My task");
		expect(task.getCreationDate().getHourOfDay()).isZero();
		expect(task.getCreationDate().getMinuteOfHour()).isZero();
		expect(task.getCreationDate().getSecondOfMinute()).isZero();
		expect(task.getCreationDate().getMillisecondOfSecond()).isZero();
		expectNot(task.hasSize());
		expectNot(task.isSolved());
	}
}
