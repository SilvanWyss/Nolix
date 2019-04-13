//package declaration
package ch.nolix.element.task;

//own imports
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.entity.MultiProperty;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.element.bases.NamableElement;

//class
/**
 * A tableau can contain tasks.
 * A tableau is clearable.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 110
 */
public final class Tableau
extends NamableElement<Tableau>
implements Clearable<Tableau> {

	//attribute
	private final MultiProperty<Task> tasks =
	new MultiProperty<Task>(
		Task.TYPE_NAME,
		t -> addTask(t),
		s -> Task.createFromSpecification(s),
		t -> t.getSpecification()
	);
		
	//method
	/**
	 * Adds the given task to this tableau.
	 * 
	 * @param task
	 * @return this tableau.
	 * @throws NullArgumentException
	 * if the given task is null.
	 */
	public Tableau addTask(final Task task) {
		
		tasks.addValue(task);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given tasks to this tableau.
	 * 
	 * @param tasks
	 * @return this Tableau.
	 * @throws NullArgumentException
	 * if one of the given tasks is null.
	 */
	public Tableau addTask(final Task...tasks) {
		
		//Iterates the given tasks.
		for (final Task t : tasks) {
			addTask(t);
		}
		
		return this;
	
	}

	//method
	/**
	 * Removes all tasks of this tableau.
	 */
	@Override
	public Tableau clear() {
		
		tasks.clear();
		
		return this;
	}
	
	//method
	/**
	 * @return the tasks of this tableau.
	 */
	public ReadContainer<Task> getRefTasks() {
		return tasks.getRefValues();
	}

	//method
	/**
	 * @return true if this tableau does not contain a task.
	 */
	@Override
	public boolean isEmpty() {
		return tasks.isEmpty();
	}

	//method
	/**
	 * Removes the given task from this tableau.
	 * 
	 * @param task
	 * @throws InvalidArgumentException
	 * if this tableau does not contain the given task.
	 */
	public void removeTask(final Task task) {
		tasks.removeValue(task);
	}
	
	//method
	/**
	 * Resets this tableau.
	 * 
	 * @return this tableau.
	 */
	@Override
	public Tableau reset() {
				
		clear();
		
		//Calls method of the base class.
		return super.reset();
	}
}
