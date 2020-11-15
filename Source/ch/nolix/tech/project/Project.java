//package declaration
package ch.nolix.tech.project;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MultiValueProperty;
import ch.nolix.element.base.MutableValueProperty;
import ch.nolix.techAPI.projectAPI.IProject;
import ch.nolix.techAPI.projectAPI.ITask;

//class
/**
 * A tableau can contain tasks.
 * A tableau is clearable.
 * 
 * @author Silvan Wyss
 * @month 2018-03
 * @lines 160
 */
public final class Project extends Element<Project> implements IProject {
	
	//attribute
	private final MutableValueProperty<String> name =
	new MutableValueProperty<>(
		VariableNameCatalogue.NAME,
		this::setName,
		BaseNode::getOneAttributeHeader,
		n -> new Node(VariableNameCatalogue.NAME, n)
	);
	
	//attribute
	private final MultiValueProperty<ITask> tasks =
	new MultiValueProperty<>(
		Task.TYPE_NAME,
		t -> addTask(t),
		s -> Task.fromSpecification(s),
		t -> t.getSpecification()
	);
		
	//method
	/**
	 * Adds the given task to this tableau.
	 * 
	 * @param task
	 * @return this tableau.
	 * @throws ArgumentIsNullException
	 * if the given task is null.
	 */
	public Project addTask(final ITask task) {
		
		tasks.add(task);
		
		return this;
	}
	
	//method
	/**
	 * Adds the given tasks to this tableau.
	 * 
	 * @param tasks
	 * @return this Tableau.
	 * @throws ArgumentIsNullException
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
	public IContainer<ITask> getRefTasks() {
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
	public Project removeTask(final ITask task) {
		
		tasks.remove(task);
		
		return this;
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
	 * @throws ArgumentIsNullException if the given name is null.
	 * @throws InvalidArgumentException if the given name is blank.
	 */
	@Override
	public Project setName(final String name) {
		
		//Asserts that the given name is not blank.
		Validator.assertThat(name).thatIsNamed(VariableNameCatalogue.NAME).isNotBlank();
		
		//Sets the name of this tableau.
		this.name.setValue(name);
		
		return this;
	}
}
