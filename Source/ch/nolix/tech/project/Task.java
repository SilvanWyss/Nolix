//package declaration
package ch.nolix.tech.project;

import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableOptionalProperty;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.base.Property;
import ch.nolix.element.time.Time;
import ch.nolix.techAPI.projectAPI.ITask;
import ch.nolix.techAPI.projectAPI.TaskSize;

//class
/**
 * @author Silvan Wyss
 * @month 2018-01
 * @lines 380
 */
public final class Task extends Element<Task> implements ITask {
	
	//default value
	private static final String DEFAULT_TITLE = StringCatalogue.EMPTY_STRING;
	
	//constants
	public static final String TYPE_NAME = "Task";
	private static final String TITLE_HEADER = "Title";
	private static final String CREATION_DATE_HEADER = "CreationDate";
	private static final String SOLVE_DATE_HEADER = "SolveDate";
	private static final String SIZE_HEADER = "Size";
	
	//static method
	/**
	 * @param specification
	 * @return a new task from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Task fromSpecification(final BaseNode specification) {
		return new Task(specification);
	}
	
	//attribute
	private final MutableProperty<String> title =
	new MutableProperty<>(
		TITLE_HEADER,
		t -> setTitle(t),
		s -> s.getOneAttributeAsString(),
		t -> Node.withOneAttribute(t)
	);
	
	//attribute
	private final Property<Time> creationDate =
	new Property<>(
		CREATION_DATE_HEADER,
		cd -> setCreationDate(cd),
		s -> Time.fromSpecification(s),
		cd -> cd.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<Time> solveTime =
	new MutableOptionalProperty<>(
		SOLVE_DATE_HEADER,
		st -> setSolved(st),
		s -> Time.fromSpecification(s),
		st -> st.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<TaskSize> size =
	new MutableOptionalProperty<>(
		SIZE_HEADER,
		s -> setSize(s),
		s -> TaskSize.fromSpecification(s),
		s -> s.getSpecification()
	);
	
	//constructor
	/**
	 * Creates a new task with the given title.
	 * The task will have the current time as creation date.
	 * 
	 * @param title
	 * @throws ArgumentIsNullException if the given title is null.
	 */
	public Task(final String title) {
		setTitle(title);
		setCreationDate(Time.createCurrentTime());
	}
	
	//constructor
	/**
	 * Creates a new task with the given title and size.
	 * 
	 * @param title
	 * @param creationDate
	 * @param solveDate
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws ArgumentIsNullException if the given size is null.
	 */
	public Task(final String title,	final TaskSize size) {
		setTitle(title);
		setSize(size);
	}
	
	//constructor
	/**
	 * Creates a new task with the given title and creation date.
	 * 
	 * @param title
	 * @param creationDate
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws ArgumentIsNullException if the given creation date is null.
	 */
	public Task(final String title, final Time creationDate) {
		setTitle(title);
		setCreationDate(creationDate);
	}
	
	//constructor
	/**
	 * Creates a new task with the given title, creation date and solve date.
	 * 
	 * @param title
	 * @param creationDate
	 * @param solveDate
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws ArgumentIsNullException if the given creation date is null.
	 * @throws ArgumentIsNullException if the given solve date is null.
	 * @throws InvalidArgumentException
	 * if the given solve date is before the given creation date.
	 */
	public Task(
		final String title,
		final Time creationDate,
		final Time solveDate
	) {
		setTitle(title);
		setCreationDate(creationDate);
		setSolved(solveDate);
	}
	
	//constructor
	/**
	 * Creates a new task with the given title, size, creation date and solve date.
	 * 
	 * @param title
	 * @param size
	 * @param creationDate
	 * @param solveDate
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws ArgumentIsNullException if the given size is null.
	 * @throws ArgumentIsNullException if the given creation date is null.
	 * @throws ArgumentIsNullException if the given solve date is null.
	 * @throws InvalidArgumentException
	 * if the given solve date is before the given creation date.
	 */
	public Task(
		final String title,
		final TaskSize size,
		final Time creationDate,
		final Time solveDate
	) {
		setTitle(title);
		setSize(size);
		setCreationDate(creationDate);
		setSolved(solveDate);
	}
	
	//constructor
	/**
	 * Creates a new task with the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	private Task(final BaseNode specification) {
		reset();
		specification.getRefAttributes().forEach(a -> addOrChangeAttribute(a));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	/**
	 * @return the creation time of this task.
	 */
	@Override
	public Time getCreationDate() {
		return creationDate.getValue();
	}
	
	//method
	/**
	 * @return the size of this task.
	 * @throws ArgumentDoesNotHaveAttributeException if this task is not assigned a size.
	 */
	@Override
	public TaskSize getSize() {
		return size.getValue();
	}
	
	//method
	/**
	 * @return the solve date of this task.
	 * @throws InvalidArgumentException if this task is not solved.
	 */
	@Override
	public Time getSolveDate() {
		
		//Asserts that this task is solved.
		supposeIsSolved();
		
		return solveTime.getValue();
	}
	
	//method
	/**
	 * @return the title of this task.
	 */
	@Override
	public String getTitle() {
		return title.getValue();
	}
	
	//method
	/**
	 * @return true if this task is assigned a size.
	 */
	@Override
	public boolean hasSize() {
		return size.containsAny();
	}
	
	//method
	/**
	 * @return true if this task is solved.
	 */
	@Override
	public boolean isSolved() {
		return !solveTime.isEmpty();
	}
	
	//method
	/**
	 * Resets this task.
	 * 
	 * @return this task.
	 */
	@Override
	public Task reset() {
		
		setTitle(DEFAULT_TITLE);
		setUnsolved();
		
		return this;
	}
	
	//method
	/**
	 * Sets this task as solved with the current time as solve time.
	 * 
	 * @return this task.
	 * @throws InvalidArgumentException if this task is already solved.
	 */
	@Override
	public Task setSolved() {
		return setSolved(Time.createCurrentTime());
	}
	
	//method
	/**
	 * Sets this task as solved at the given solve time.
	 * 
	 * @param solveTime
	 * @return this task.
	 * @throws InvalidArgumentException if the given solve time is null.
	 * @throws InvalidArgumentException if this task is already solved.
	 */
	@Override
	public Task setSolved(final Time solveTime) {
		
		//Asserts that the given solve time is after the creation time of this task.
		if (!getSolveDate().isAfter(getCreationDate())) {
			throw new InvalidArgumentException(
				"solve time",
				solveTime,				
				"is not after the creation time '"
				+ getCreationDate().toString()
				+ "' of the task"
			);
		}
		
		//Asserts that this task is not already solved.
		supposeIsNotSolved();
		
		this.solveTime.setValue(solveTime);
		
		return this;
	}
	
	//method
	/**
	 * Sets this task as unsolved.
	 * 
	 * @return this task.
	 */
	@Override
	public Task setUnsolved() {
		
		solveTime.clear();
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of this task.
	 * 
	 * @param size
	 * @return this task.
	 * @throws ArgumentIsNullException if the given size is null.
	 */
	@Override
	public Task setSize(final TaskSize size) {
		
		this.size.setValue(size);
		
		return this;
	}
	
	//method
	/**
	 * Sets the title of this task.
	 * 
	 * @param title
	 * @return this task.
	 * @throws ArgumentIsNullException if the given title is null.
	 */
	@Override
	public Task setTitle(final String title) {
		
		this.title.setValue(title);
		
		return this;
	}
	
	//method
	/**
	 * Sets the creation date of this task.
	 * 
	 * @param creationDate
	 * @throws InvalidArgumentException if the properties of this task are approved.
	 */
	private void setCreationDate(final Time creationDate) {
		this.creationDate.setValue(creationDate.getDay());
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this task is solved.
	 */
	private void supposeIsNotSolved() {
		
		//Asserts that this task is not solved.
		if (isSolved()) {
			throw new InvalidArgumentException(this, "is solved");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this task is not solved.
	 */
	private void supposeIsSolved() {
		
		//Asserts that this task is solved.
		if (!isSolved()) {
			throw new InvalidArgumentException(this, "is not solved");
		}
	}
}
