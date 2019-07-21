//package declaration
package ch.nolix.tech.project;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableOptionalProperty;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.base.Property;
import ch.nolix.element.baseAPI.IMutableElement;
import ch.nolix.element.time.Time;

//class
/**
 * @author Silvan Wyss
 * @month 2018-01
 * @lines 380
 */
public final class Task extends Element<Task> implements IMutableElement<Task> {
	
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
	public static Task createFromSpecification(final DocumentNodeoid specification) {
		return new Task(specification);
	}
	
	//attribute
	private final MutableProperty<String> title =
	new MutableProperty<>(
		TITLE_HEADER,
		t -> setTitle(t),
		s -> s.getOneAttributeAsString(),
		t -> DocumentNode.createWithOneAttribute(t)
	);
	
	//attribute
	private final Property<Time> creationDate =
	new Property<>(
		CREATION_DATE_HEADER,
		cd -> setCreationDate(cd),
		s -> Time.createFromSpecification(s),
		cd -> cd.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<Time> solveTime =
	new MutableOptionalProperty<>(
		SOLVE_DATE_HEADER,
		st -> setAsSolved(st),
		s -> Time.createFromSpecification(s),
		st -> st.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<TaskSize> size =
	new MutableOptionalProperty<>(
		SIZE_HEADER,
		s -> setSize(s),
		s -> TaskSize.createFromSpecification(s),
		s -> s.getSpecification()
	);
	
	//constructor
	/**
	 * Creates a new task with the given title.
	 * The task will have the current time as creation date.
	 * 
	 * @param title
	 * @throws NullArgumentException if the given title is null.
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
	 * @throws NullArgumentException if the given title is null.
	 * @throws NullArgumentException if the given size is null.
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
	 * @throws NullArgumentException if the given title is null.
	 * @throws NullArgumentException if the given creation date is null.
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
	 * @throws NullArgumentException if the given title is null.
	 * @throws NullArgumentException if the given creation date is null.
	 * @throws NullArgumentException if the given solve date is null.
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
		setAsSolved(solveDate);
	}
	
	//constructor
	/**
	 * Creates a new task with the given title, size, creation date and solve date.
	 * 
	 * @param title
	 * @param size
	 * @param creationDate
	 * @param solveDate
	 * @throws NullArgumentException if the given title is null.
	 * @throws NullArgumentException if the given size is null.
	 * @throws NullArgumentException if the given creation date is null.
	 * @throws NullArgumentException if the given solve date is null.
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
		setAsSolved(solveDate);
	}
	
	//constructor
	/**
	 * Creates a new task with the given specification.
	 * 
	 * @param specification
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	private Task(final DocumentNodeoid specification) {
		reset(specification);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Calls method of the base class.
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	/**
	 * @return the creation time of this task.
	 */
	public Time getCreationDate() {
		return creationDate.getValue();
	}
	
	//method
	/**
	 * @return the size of this task.
	 * @throws ArgumentMissesAttributeException if this task is not assigned a size.
	 */
	public TaskSize getSize() {
		return size.getValue();
	}
	
	//method
	/**
	 * @return the solve date of this task.
	 * @throws InvalidArgumentException if this task is not solved.
	 */
	public Time getSolveDate() {
		
		//Checks if this task is solved.
		supposeIsSolved();
		
		return solveTime.getValue();
	}
	
	//method
	/**
	 * @return the title of this task.
	 */
	public String getTitle() {
		return title.getValue();
	}
	
	//method
	/**
	 * @return true if this task is assigned a size.
	 */
	public boolean isAssignedSize() {
		return size.containsAny();
	}
	
	//method
	/**
	 * @return true if this task is solved.
	 */
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
		setAsUnsolved();
		
		return this;
	}
	
	//method
	/**
	 * Sets this task as solved with the current time as solve time.
	 * 
	 * @return this task.
	 * @throws InvalidArgumentException if this task is already solved.
	 */
	public Task setAsSolved() {
		return setAsSolved(Time.createCurrentTime());
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
	public Task setAsSolved(final Time solveTime) {
		
		//Checks if the given solve time is after the creation time of this task.
		if (!getSolveDate().isAfter(getCreationDate())) {
			throw new InvalidArgumentException(
				"solve time",
				solveTime,				
				"is not after the creation time '"
				+ getCreationDate().toString()
				+ "' of the task"
			);
		}
		
		//Checks if this task is not already solved.
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
	public Task setAsUnsolved() {
		
		solveTime.clear();
		
		return this;
	}
	
	//method
	/**
	 * Sets the size of this task.
	 * 
	 * @param size
	 * @return this task.
	 * @throws NullArgumentException if the given size is null.
	 */
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
	 * @throws NullArgumentException if the given title is null.
	 */
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
		
		//Checks if this task is not solved.
		if (isSolved()) {
			throw new InvalidArgumentException(this, "is solved");
		}
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if this task is not solved.
	 */
	private void supposeIsSolved() {
		
		//Checks if this task is solved.
		if (!isSolved()) {
			throw new InvalidArgumentException(this, "is not solved");
		}
	}
}
