//package declaration
package ch.nolix.techTest.projectTest;

//own imports
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.test.Test;
import ch.nolix.tech.project.Project;
import ch.nolix.tech.project.Task;

//test class
/**
 * A tableau test is a test for the tableau class.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 80
 */
public final class TableauTest extends Test {

	//test case
	public void testCase_addTask() {
		
		//setup
		final Project project = new Project();
		final Task task1 = new Task("Task1");
		final Task task2 = new Task("Task2");
		
		//execution
		project.addTask(task1, task2);
		
		//verification
		expect(project.containsAny());
		expect(project.getRefTasks().getSize()).isEqualTo(2);
		expect(project.getRefTasks().contains(task1));
		expect(project.getRefTasks().contains(task2));
	}
	
	//test case
	public void testCase_addTask_2() {
		
		//setup
		final Project project = new Project();
		final Task task = null;
		
		//execution & verification
		expect(() -> project.addTask(task))
		.throwsException()
		.ofType(NullArgumentException.class);
	}
	
	//test case
	public void testCase_addTask_3() {
		
		//setup
		final Project project = new Project();
		final Task task = new Task("Task");
		project.addTask(task);
		
		//execution & verification
		expect(() -> project.addTask(task))
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//test case
	public void testCase_clear() {
		
		//setup
		final Project project = new Project();
		project.addTask(
			new Task("Task1"),
			new Task("Task2"),
			new Task("Task3"),
			new Task("Task5")
		);
		
		//execution
		project.clear();
		
		//verification
		expect(() -> project.isEmpty());
	}
}
