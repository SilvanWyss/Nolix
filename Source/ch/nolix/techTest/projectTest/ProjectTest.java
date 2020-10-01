//package declaration
package ch.nolix.techTest.projectTest;

//own imports
import ch.nolix.common.baseTest.TestCase;
import ch.nolix.common.invalidArgumentException.ArgumentIsNullException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.test.Test;
import ch.nolix.tech.project.Project;
import ch.nolix.tech.project.Task;

//class
/**
 * A {@link ProjectTest} is a test for {@link Project}s.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 80
 */
public final class ProjectTest extends Test {
	
	//method
	@TestCase
	public void testCase_addTask_1() {
		
		//setup
		final var project = new Project();
		final var task1 = new Task("Task1");
		final var task2 = new Task("Task2");
		
		//execution
		project.addTask(task1, task2);
		
		//verification
		expect(project.getRefTasks().contains(task1));
		expect(project.getRefTasks().contains(task2));
	}
	
	//method
	@TestCase
	public void testCase_addTask_2() {
		
		//setup
		final var project = new Project();
		final Task task = null;
		
		//execution & verification
		expect(() -> project.addTask(task))
		.throwsException()
		.ofType(ArgumentIsNullException.class);
	}
	
	//method
	@TestCase
	public void testCase_addTask_3() {
		
		//setup
		final var project = new Project();
		final var task = new Task("Task");
		project.addTask(task);
		
		//execution & verification
		expect(() -> project.addTask(task))
		.throwsException()
		.ofType(InvalidArgumentException.class);
	}
	
	//method
	@TestCase
	public void testCase_clear() {
		
		//setup
		final var project = new Project();
		project.addTask(
			new Task("Task1"),
			new Task("Task2"),
			new Task("Task3"),
			new Task("Task4")
		);
		
		//execution
		project.clear();
		
		//verification
		expect(project.isEmpty());
	}
}
