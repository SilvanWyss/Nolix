//package declaration
package ch.nolix.element._3DGUI;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.core.constants.StringCatalogue;
import ch.nolix.core.container.ReadContainer;
import ch.nolix.core.container.List;
import ch.nolix.core.container.Pair;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.interfaces.Clearable;
import ch.nolix.core.interfaces.Closable;
import ch.nolix.core.interfaces.Refreshable;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationInterfaces.Configurable;
import ch.nolix.element.color.Color;
import ch.nolix.element.configurationElement.ConfigurationElement;
import ch.nolix.element.data.BackgroundColor;
import ch.nolix.element.data.Title;
import ch.nolix.primitive.invalidArgumentException.Argument;
import ch.nolix.primitive.invalidArgumentException.ArgumentName;
import ch.nolix.primitive.invalidArgumentException.ErrorPredicate;
import ch.nolix.primitive.invalidArgumentException.InvalidArgumentException;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * A 3D GUI is clearable.
 * A 3D GUI is closable.
 * A 3D GUI is refreshable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 390
 * @param <G> The type of a 3D GUI.
 */
public abstract class _3DGUI<G extends _3DGUI<G>>
extends ConfigurationElement<G>
implements Clearable<G>, Closable, Refreshable {
	
	//default values
	public static final String DEFAULT_TITLE = StringCatalogue.DEFAULT_STRING;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;

	//attribute
	private final MutableProperty<Title> title =
	new MutableProperty<Title>(
		Title.TYPE_NAME,
		s -> setTitle(s.toString()),
		s -> Title.createFromSpecification(s)
	);
	
	//attribute
	private final MutableProperty<Color> backgroundColor =
	new MutableProperty<Color>(
		BackgroundColor.TYPE_NAME,
		bc -> setBackgroundColor(bc),
		s -> Color.createFromSpecification(s)
	);
	
	//optional element
	private Shape<?> rootShape;
	
	//multiple element
	private List<Pair<Class<?>, IShapeRenderer<?, ?, ?>>> shapeClasses = new List<>();
	
	//method
	/**
	 * Adds or changes the given attribute to this 3D GUI.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public final void addOrChangeAttribute(final Specification attribute) {
		
		//Handles the case that the given attribute specifies a shape.
		if (canCreateShape(attribute.getHeader())) {
			setRootShape(createShape(attribute.getHeader()));
			return;
		}
		
		//Handles the case that the given attribute specifies no shape.
		super.addOrChangeAttribute(attribute);			
	}
	
	//method
	/**
	 * Adds the given shape class with the given shape renderer to this 3D GUI.
	 * 
	 * @param shapeClass
	 * @param shapeRenderer
	 * @return this 3D GUI.
	 * @throws NullArgumentException if the given shape class is null.
	 * @throws NullArgumentException if the given shape renderer is null.
	 * @throws InvalidStateException if this 3D GUI contains already
	 * a shape class with the same name as the given shape class.
	 */
	public G addShapeClass(final Class<?> shapeClass, IShapeRenderer<?, ?, ?> shapeRenderer) {
		
		//Checks if the given shape class is not null.
		Validator.suppose(shapeClass).thatIsNamed("shape class").isNotNull();
		
		//Checks if the given shape renderer is not null.
		Validator.suppose(shapeRenderer).thatIsNamed("shape renderer").isNotNull();
		
		//Checks if this 3D GUI does not contain already
		//a shape class with the same name as the given shape class.
		if (canCreateShape(shapeClass.getSimpleName())) {
			throw new InvalidStateException(this, "contains already a shape class '" + shapeClass + "'");
		}
		
		shapeClasses.addAtEnd(new Pair<>(shapeClass, shapeRenderer));
		
		return getInstance();
	}
	
	//method
	/**
	 * @param type
	 * @return true if this 3D GUI can create a shape of the given type.
	 */
	public final boolean canCreateShape(final String type) {
		return shapeClasses.contains(sc -> sc.getRefElement1().getSimpleName().equals(type));
	}
	
	//method
	/**
	 * Removes the root shape of this 3D GUI.
	 */
	public final G clear() {
		
		removeRootShape();
		
		return getInstance();
	}
	
	//method
	/**
	 * @return the attributes of this 3D GUI.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class.
		final List<StandardSpecification> attributes = super.getAttributes();
		
		//Handles the case that this 3D GUI has a root shape.
		if (hasRootShape()) {
			attributes.addAtEnd(getRefRootShape().getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * @return the background color of this 3D GUI.
	 */
	public final Color getBackgroundColor() {
		return backgroundColor.getValue();
	}
	
	//method
	/**
	 * @return the configurable objects of this 3D GUI.
	 */
	public final ReadContainer<Configurable> getRefConfigurables() {
		return new ReadContainer<>(getRefShapes());
	}

	//method
	/**
	 * @return the root shape of this 3D GUI.
	 * @throws UnexistingAttributeException if this 3D GUI has no root shape.
	 */
	public final Shape<?> getRefRootShape() {
		
		//Checks if this 3D GUI has a root shape.
		if (!hasRootShape()) {
			throw new UnexistingAttributeException(this, "root shape");
		}
		
		return rootShape;
	}
	
	//method
	/**
	 * @return the title of this 3D GUI.
	 */
	public final String getTitle() {
		return title.getValue().getValue();
	}
	
	//method
	/**
	 * @return true if this 3D GUI has the given role.
	 */
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return true if this 3D GUI has a root shape.
	 */
	public final boolean hasRootShape() {
		return (rootShape != null);
	}
	
	//method
	/**
	 * @return true if this 3D GUI has a root shape.
	 */
	public final boolean isEmpty() {
		return hasRootShape();
	}
	
	//abstract method
	/**
	 * Lets this 3D GUI note a left mouse button press.
	 */
	public abstract void noteLeftMouseButtonPress();
	
	//abstract method
	/**
	 * Lets this 3D GUI note a left mouse button release.
	 */
	public abstract void noteLeftMouseButtonRelease();
	
	//abstract method
	/**
	 * Lets this 3D GUI note a right mouse button press.
	 */
	public abstract void noteRightMouseButtonPress();
	
	//abstract method
	/**
	 * Lets this 3D GUI note a right mouse button release.
	 */
	public abstract void noteRightMouseButtonRelease();
	
	//method
	/**
	 * Removes the root shape of this 3D GUI.
	 * 
	 * @return this 3D GUI.
	 */
	public final G removeRootShape() {
		
		rootShape = null;
		
		return getInstance();
	}
	
	//method
	/**
	 * Refreshes this 3D GUI.
	 */
	public void refresh() {
		
		//Handles the case that this 3D GUI has a root shape.
		if (hasRootShape()) {
			getRefRootShape().renderRecursively();
		}
	}
	
	//method
	/**
	 * Resets this 3D GUI.
	 * 
	 * @return this 3D GUI.
	 */
	public G reset() {
		
		setTitle(DEFAULT_TITLE);
		
		//Calls method of the base class.
		return super.reset();
	}
	
	//method
	/**
	 * Resets the configuration of this GUI.
	 */
	public void resetConfiguration() {		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	
	//method
	/**
	 * Sets the background color of this 3D GUI.
	 * 
	 * @param backgroundColor
	 * @return this 3D GUI.
	 * @throws NullArgumentException if the given background color is null.
	 */
	public final G setBackgroundColor(final Color backgroundColor) {
				
		this.backgroundColor.setValue(new BackgroundColor(backgroundColor.getValue()));
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the root shape of this 3D GUI.
	 * 
	 * @param rootShape
	 * @return this 3D GUI.
	 * @throws NullArgumentException if the given root shape is null.
	 */
	public G setRootShape(final Shape<?> rootShape) {
		
		//Sets this GUI to the given root shape.
		rootShape.setGUI(this);
		
		//Sets the given root shape to this 3D GUI.
		this.rootShape = rootShape;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the title of this 3D GUI.
	 * 
	 * @param title
	 * @return this 3D GUI.
	 * @throws NullArgumentException if the given title is null.
	 * @throws EmptyArgumentException if the given title is empty.
	 */
	public final G setTitle(final String title) {
		
		this.title.setValue(new Title(title));
		
		return getInstance();
	}
	
	/**
	 * @param shape
	 * @return a new shape renderer for the given shape from this 3D GUI.
	 * @throws UnexistingAttributeException
	 * if this 3D GUI contains no shape renderer for the given shape.
	 */
	protected final IShapeRenderer<?, ?, ?> getShapeRendererFor(final Shape<?> shape) {
		return
		shapeClasses
		.getRefFirst(sc -> sc.getRefElement1() == shape.getClass())
		.getRefElement2();
	}
	
	//method
	/**
	 * @param type
	 * @return a new shape of the given type.
	 * @throws InvalidArgumentException if this 3D GUI cannot create a shape of the given type.
	 */
	private Shape<?> createShape(final String type) {
		try {
			return
			(Shape<?>)
			shapeClasses
			.getRefFirst(sc -> sc.getRefElement1().getSimpleName().equals(type))
			.getRefElement1().getDeclaredConstructor().newInstance();
		}
		catch (
			final 
			InstantiationException
			| IllegalAccessException
			| IllegalArgumentException
			| InvocationTargetException
			| NoSuchMethodException
			| SecurityException
			exception
		) {
			throw new InvalidArgumentException(
				new ArgumentName("type"),
				new Argument(type),
				new ErrorPredicate(
					"is not valid because the " + getType() + " cannot create a '" + type + "' shape"
				)
			);
		}
	}
	
	//method
	/**
	 * @return the shapes of this 3D GUI.
	 */
	private final ReadContainer<Shape<?>> getRefShapes() {
		
		final List<Shape<?>> shapes = new List<>();
		
		//Handles the case that this 3D GUI has a root shape.
		if (hasRootShape()) {
			shapes.addAtEnd(getRefRootShape());
		}
		
		return new ReadContainer<>(shapes);
	}
}
