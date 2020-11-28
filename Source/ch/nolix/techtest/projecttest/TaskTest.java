//package declaration
package ch.nolix.techtest.projecttest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.test.Test;
import ch.nolix.tech.project.Task;

//class
/**
 * A {@link TaskTest} is a test for {@link Task}s.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 30
 */
public final class TaskTest extends Test {
	
	//method
	@TestCase
	public void testCase_creation() {
		
		//setup
		final var task = new Task("My task");
		
		//verification
		expect(task.getTitle()).isEqualTo("My task");
		expect(task.getCreationDate().getHourOfDay()).isEqualTo(0);
		expect(task.getCreationDate().getMinuteOfHour()).isEqualTo(0);
		expect(task.getCreationDate().getSecondOfMinute()).isEqualTo(0);
		expect(task.getCreationDate().getMillisecondOfSecond()).isEqualTo(0);
		expectNot(task.hasSize());
		expectNot(task.isSolved());
	}
}
