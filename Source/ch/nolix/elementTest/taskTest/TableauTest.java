//package declaration
package ch.nolix.elementTest.taskTest;

//own imports
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.test2.Test;
import ch.nolix.element.task.Tableau;
import ch.nolix.element.task.Task;

//test class
/**
 * A tableau test is a test for the tableau class.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 80
 */
public final class TableauTest extends Test {

	//test method
	public void test_addTask() {
		
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
	
	//test method
	public void test_addTask_2() {
		
		//setup
		final Tableau tableau = new Tableau();
		final Task task = null;
		
		//execution and verification
		expect(() -> tableau.addTask(task))
		.throwsExceptionOfType(NullArgumentException.class);
	}
	
	//test method
	public void test_addTask_3() {
		
		//setup
		final Tableau tableau = new Tableau();
		final Task task = new Task("Task");
		tableau.addTask(task);
		
		//execution and verification
		expect(() -> tableau.addTask(task))
		.throwsExceptionOfType(InvalidArgumentException.class);
	}
	
	//test method
	public void test_clear() {
		
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
