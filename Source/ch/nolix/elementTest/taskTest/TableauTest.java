//package declaration
package ch.nolix.elementTest.taskTest;

import ch.nolix.element.task.Tableau;
import ch.nolix.element.task.Task;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;
import ch.nolix.primitive.test2.Test;

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
		final Tableau tableau = new Tableau();
		final Task task1 = new Task("Task1");
		final Task task2 = new Task("Task2");
		
		//execution
		tableau.addTask(task1, task2);
		
		//verification
		expect(tableau.containsAny());
		expect(tableau.getRefTasks().getElementCount()).isEqualTo(2);
		expect(tableau.getRefTasks().contains(task1));
		expect(tableau.getRefTasks().contains(task2));
	}
	
	//test case
	public void testCase_addTask_2() {
		
		//setup
		final Tableau tableau = new Tableau();
		final Task task = null;
		
		//execution and verification
		expect(() -> tableau.addTask(task))
		.throwsExceptionOfType(NullArgumentException.class);
	}
	
	//test case
	public void testCase_addTask_3() {
		
		//setup
		final Tableau tableau = new Tableau();
		final Task task = new Task("Task");
		tableau.addTask(task);
		
		//execution and verification
		expect(() -> tableau.addTask(task))
		.throwsExceptionOfType(InvalidArgumentException.class);
	}
	
	//test case
	public void testCase_clear() {
		
		//setup
		final Tableau tableau = new Tableau();
		tableau.addTask(
			new Task("Task1"),
			new Task("Task2"),
			new Task("Task3"),
			new Task("Task5")
		);
		
		//execution
		tableau.clear();
		
		//verification
		expect(() -> tableau.isEmpty());
	}
}
