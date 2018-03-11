//package declaration
package ch.nolix.elementTest.taskTest;

import ch.nolix.element.task.Task;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * A task test is a test for the task class.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public final class TaskTest extends Test {

	//test method
	public void test_constructor() {
				
		//setup
		final Task task = new Task("My task");
		
		//verification
		expect(task.getTitle()).isEqualTo("My task");
		expect(task.getCreationDate().getHourOfDay()).isZero();
		expect(task.getCreationDate().getMinuteOfHour()).isZero();
		expect(task.getCreationDate().getSecondOfMinute()).isZero();
		expect(task.getCreationDate().getMillisecondOfSecond()).isZero();
		expectNot(task.isAssignedSize());
		expectNot(task.isSolved());
	}
}
