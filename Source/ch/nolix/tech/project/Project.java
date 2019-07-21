//package declaration
package ch.nolix.tech.project;

//own imports
import ch.nolix.core.attributeAPI.Namable;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.skillAPI.Clearable;
import ch.nolix.core.skillAPI.Resettable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MultiProperty;
import ch.nolix.element.base.MutableProperty;

//class
/**
 * A tableau can contain tasks.
 * A tableau is clearable.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 160
 */
public final class Project extends Element<Project>
implements Clearable<Project>, Namable<Project>, Resettable<Project> {
	
	//attribute
	private final MutableProperty<String> name =
	new MutableProperty<>(
		VariableNameCatalogue.NAME,
		n -> setName(n),
		s -> s.getOneAttributeAsString(),
		n -> new DocumentNode(VariableNameCatalogue.NAME, n)
	);
	
	//attribute
	private final MultiProperty<Task> tasks =
	new MultiProperty<>(
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
	public Project addTask(final Task task) {
		
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
	public Project addTask(final Task...tasks) {
		
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
	public Project clear() {
		
		tasks.clear();
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return name.getValue();
	}
	
	//method
	/**
	 * @return the tasks of this tableau.
	 */
	public IContainer<Task> getRefTasks() {
		return tasks;
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
	public Project reset() {
				
		clear();
		
		return this;
	}
	
	//method
	/**
	 * Sets the name of this tableau.
	 * 
	 * @param name
	 * @return this tableau.
	 * @throws NullArgumentException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 */
	@Override
	public Project setName(final String name) {
		
		//Checks if the given name is not blank.
		Validator.suppose(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank();
		
		//Sets the name of this tableau.
		this.name.setValue(name);
		
		return this;
	}
}
