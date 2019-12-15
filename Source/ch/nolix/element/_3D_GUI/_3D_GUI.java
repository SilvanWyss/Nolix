//package declaration
package ch.nolix.element._3D_GUI;

//Java import
import java.lang.reflect.InvocationTargetException;

//own import
import ch.nolix.common.constants.PascalCaseNameCatalogue;
import ch.nolix.common.constants.StringCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.containers.List;
import ch.nolix.common.containers.ReadContainer;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.skillAPI.Closable;
import ch.nolix.common.skillAPI.Refreshable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseAPI.IConfigurableElement;
import ch.nolix.element.color.Color;
import ch.nolix.element.configuration.ConfigurationElement;

//abstract class
/**
 * A 3D GUI is clearable.
 * A 3D GUI is closable.
 * A 3D GUI is refreshable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 410
 * @param <G> The type of a 3D GUI.
 */
public abstract class _3D_GUI<G extends _3D_GUI<G>>
extends ConfigurationElement<G>
implements Clearable<G>, Closable, Refreshable {
	
	//default values
	public static final String DEFAULT_TITLE = StringCatalogue.DEFAULT_STRING;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//attribute
	private final MutableProperty<String> title =
	new MutableProperty<>(
		PascalCaseNameCatalogue.TITLE,
		s -> setTitle(s),
		s -> s.getOneAttributeAsString(),
		s -> Node.withOneAttribute(s)
	);
	
	//attribute
	private final MutableProperty<Color> backgroundColor =
	new MutableProperty<>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		bc -> setBackgroundColor(bc),
		s -> Color.fromSpecification(s),
		bc -> bc.getSpecification()
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
	@Override
	public final void addOrChangeAttribute(final BaseNode attribute) {
		
		//Handles the case that the given attribute specifies a shape.
		if (canCreateShape(attribute.getHeader())) {
			setRootShape(createShape(attribute.getHeader()));
			return;
		}
		
		//Handles the case that the given attribute does not specify a shape.
		super.addOrChangeAttribute(attribute);
	}
	
	//method
	/**
	 * Adds the given shape class with the given shape renderer to this 3D GUI.
	 * 
	 * @param shapeClass
	 * @param shapeRenderer
	 * @return this 3D GUI.
	 * @throws ArgumentIsNullException if the given shape class is null.
	 * @throws ArgumentIsNullException if the given shape renderer is null.
	 * @throws InvalidArgumentException if this 3D GUI contains already
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
			throw new InvalidArgumentException(this, "contains already a shape class '" + shapeClass + "'");
		}
		
		shapeClasses.addAtEnd(new Pair<>(shapeClass, shapeRenderer));
		
		return asConcreteType();
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
	@Override
	public final G clear() {
		
		removeRootShape();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return the attributes of this 3D GUI.
	 */
	@Override
	public List<Node> getAttributes() {
		
		//Calls method of the base class.
		final List<Node> attributes = super.getAttributes();
		
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
	@Override
	public final ReadContainer<IConfigurableElement<?>> getRefConfigurables() {
		return new ReadContainer<>(getRefShapes());
	}

	//method
	/**
	 * @return the root shape of this 3D GUI.
	 * @throws ArgumentDoesNotHaveAttributeException if this 3D GUI does not have a root shape.
	 */
	public final Shape<?> getRefRootShape() {
		
		//Checks if this 3D GUI has a root shape.
		if (!hasRootShape()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "root shape");
		}
		
		return rootShape;
	}
	
	//method
	/**
	 * @return the title of this 3D GUI.
	 */
	public final String getTitle() {
		return title.getValue();
	}
	
	//method
	/**
	 * @return true if this 3D GUI has the given role.
	 */
	@Override
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
	@Override
	public final boolean isEmpty() {
		return hasRootShape();
	}
	
	//method declaration
	/**
	 * Lets this 3D GUI note a left mouse button press.
	 */
	public abstract void noteLeftMouseButtonPress();
	
	//method declaration
	/**
	 * Lets this 3D GUI note a left mouse button release.
	 */
	public abstract void noteLeftMouseButtonRelease();
	
	//method declaration
	/**
	 * Lets this 3D GUI note a right mouse button press.
	 */
	public abstract void noteRightMouseButtonPress();
	
	//method declaration
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Refreshes this 3D GUI.
	 */
	@Override
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
	@Override
	public G reset() {
		
		setTitle(DEFAULT_TITLE);
		
		//Calls method of the base class.
		return super.reset();
	}
	
	//method
	/**
	 * Resets the configuration of this GUI.
	 * 
	 * @return this 3D GUI.
	 */
	@Override
	public G resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the background color of this 3D GUI.
	 * 
	 * @param backgroundColor
	 * @return this 3D GUI.
	 * @throws ArgumentIsNullException if the given background color is null.
	 */
	public final G setBackgroundColor(final Color backgroundColor) {
				
		this.backgroundColor.setValue(backgroundColor);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the root shape of this 3D GUI.
	 * 
	 * @param rootShape
	 * @return this 3D GUI.
	 * @throws ArgumentIsNullException if the given root shape is null.
	 */
	public G setRootShape(final Shape<?> rootShape) {
		
		//Sets this GUI to the given root shape.
		rootShape.setGUI(this);
		
		//Sets the given root shape to this 3D GUI.
		this.rootShape = rootShape;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the title of this 3D GUI.
	 * 
	 * @param title
	 * @return this 3D GUI.
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws EmptyArgumentException if the given title is blank.
	 */
	public final G setTitle(final String title) {
		
		//Checks if the given title is not blank.
		Validator.suppose(title).thatIsNamed(VariableNameCatalogue.TITLE).isNotBlank();
		
		//Sets the title of the current 3D_GUI.
		this.title.setValue(title);
		
		return asConcreteType();
	}
	
	/**
	 * @param shape
	 * @return a new shape renderer for the given shape from this 3D GUI.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if this 3D GUI does not contain a shape renderer for the given shape.
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
				VariableNameCatalogue.TYPE,
				type,
				"is not valid because the current " + getType() + " cannot create a '" + type + "' shape"
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
