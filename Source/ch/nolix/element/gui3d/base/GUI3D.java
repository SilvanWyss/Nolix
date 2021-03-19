//package declaration
package ch.nolix.element.gui3d.base;

//Java import
import java.lang.reflect.InvocationTargetException;

//own imports
import ch.nolix.common.constant.PascalCaseCatalogue;
import ch.nolix.common.constant.StringCatalogue;
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.ReadContainer;
import ch.nolix.common.container.pair.Pair;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.programcontrol.closeableelement.CloseController;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.configuration.ConfigurationElement;
import ch.nolix.element.elementapi.IConfigurableElement;
import ch.nolix.element.gui.base.CursorIcon;
import ch.nolix.element.gui.base.LocalFrontEndReader;
import ch.nolix.element.gui.base.LocalFrontEndWriter;
import ch.nolix.element.gui.baseapi.IBaseGUI;
import ch.nolix.element.gui.baseapi.IFrontEndReader;
import ch.nolix.element.gui.baseapi.IFrontEndWriter;
import ch.nolix.element.gui.color.Color;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 410
 * @param <G> is the type of a {@link GUI3D}.
 */
public abstract class GUI3D<G extends GUI3D<G>> extends ConfigurationElement<G> implements Clearable, IBaseGUI<G> {
	
	//constants
	public static final String DEFAULT_TITLE = StringCatalogue.DEFAULT_STRING;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//attribute
	private final MutableValue<String> title =
	new MutableValue<>(
		PascalCaseCatalogue.TITLE,
		DEFAULT_TITLE,
		this::setTitle,
		BaseNode::getOneAttributeHeader,
		Node::withAttribute
	);
	
	//attribute
	private final MutableValue<Color> backgroundColor =
	new MutableValue<>(
		PascalCaseCatalogue.BACKGROUND_COLOR,
		DEFAULT_BACKGROUND_COLOR,
		this::setBackgroundColor,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final CloseController closeController = new CloseController(this);
	
	//optional element
	private Shape<?> rootShape;
	
	//multi-attribute
	private LinkedList<Pair<Class<?>, IShapeRenderer<?, ?, ?>>> shapeClasses = new LinkedList<>();
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link GUI3D}.
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
	 * Adds the given shape class with the given shape renderer to the current {@link GUI3D}.
	 * 
	 * @param shapeClass
	 * @param shapeRenderer
	 * @return the current {@link GUI3D}.
	 * @throws ArgumentIsNullException if the given shape class is null.
	 * @throws ArgumentIsNullException if the given shape renderer is null.
	 * @throws InvalidArgumentException if the current {@link GUI3D} contains already
	 * a shape class with the same name as the given shape class.
	 */
	public G addShapeClass(final Class<?> shapeClass, IShapeRenderer<?, ?, ?> shapeRenderer) {
		
		//Asserts that the given shape class is not null.
		Validator.assertThat(shapeClass).thatIsNamed("shape class").isNotNull();
		
		//Asserts that the given shape renderer is not null.
		Validator.assertThat(shapeRenderer).thatIsNamed("shape renderer").isNotNull();
		
		//Asserts that the current {@link _3D_GUI} does not contain already
		//a shape class with the same name as the given shape class.
		if (canCreateShape(shapeClass.getSimpleName())) {
			throw new InvalidArgumentException(this, "contains already a shape class '" + shapeClass + "'");
		}
		
		shapeClasses.addAtEnd(new Pair<>(shapeClass, shapeRenderer));
		
		return asConcrete();
	}
	
	//method
	/**
	 * @param type
	 * @return true if the current {@link GUI3D} can create a shape of the given type.
	 */
	public final boolean canCreateShape(final String type) {
		return shapeClasses.contains(sc -> sc.getRefElement1().getSimpleName().equals(type));
	}
	
	//method
	/**
	 * Removes the root shape of the current {@link GUI3D}.
	 */
	@Override
	public final void clear() {
		removeRootShape();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		//Handles the case that the current GUI3D has a root Shape.
		if (hasRootShape()) {
			list.addAtEnd(getRefRootShape().getSpecification());
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IFrontEndReader fromFrontEnd() {
		return new LocalFrontEndReader();
	}
	
	/**
	 * @return the background color of the current {@link GUI3D}.
	 */
	public final Color getBackgroundColor() {
		return backgroundColor.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public CursorIcon getCursorIcon() {
		return CursorIcon.ARROW;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CloseController getRefCloseController() {
		return closeController;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ReadContainer<IConfigurableElement<?>> getSubConfigurables() {
		return ReadContainer.forIterable(getRefShapes().asContainerWithElementsOfEvaluatedType());
	}
	
	//method
	/**
	 * @return the root shape of the current {@link GUI3D}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link GUI3D} does not have a root shape.
	 */
	public final Shape<?> getRefRootShape() {
		
		//Asserts that the current {@link _3D_GUI} has a root shape.
		if (!hasRootShape()) {
			throw new ArgumentDoesNotHaveAttributeException(this, "root shape");
		}
		
		return rootShape;
	}
	
	//method
	/**
	 * @return the title of the current {@link GUI3D}.
	 */
	public final String getTitle() {
		return title.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasRole(final String role) {
		return false;
	}
	
	//method
	/**
	 * @return true if the current {@link GUI3D} has a root shape.
	 */
	public final boolean hasRootShape() {
		return (rootShape != null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isEmpty() {
		return hasRootShape();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IFrontEndWriter onFrontEnd() {
		return new LocalFrontEndWriter();
	}
	
	//method
	/**
	 * Removes the root shape of the current {@link GUI3D}.
	 * 
	 * @return the current {@link GUI3D}.
	 */
	public final G removeRootShape() {
		
		rootShape = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void refresh() {
		
		//Handles the case that the current {@link _3D_GUI} has a root shape.
		if (hasRootShape()) {
			getRefRootShape().renderRecursively();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void resetConfigurationOnSelf() {
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
	}
	
	//method
	/**
	 * Sets the background color of the current {@link GUI3D}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link GUI3D}.
	 * @throws ArgumentIsNullException if the given background color is null.
	 */
	public final G setBackgroundColor(final Color backgroundColor) {
				
		this.backgroundColor.setValue(backgroundColor);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the root shape of the current {@link GUI3D}.
	 * 
	 * @param rootShape
	 * @return the current {@link GUI3D}.
	 * @throws ArgumentIsNullException if the given root shape is null.
	 */
	public G setRootShape(final Shape<?> rootShape) {
		
		//Sets this GUI to the given root shape.
		rootShape.setGUI(this);
		
		//Sets the given root shape to the current {@link _3D_GUI}.
		this.rootShape = rootShape;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the title of the current {@link GUI3D}.
	 * 
	 * @param title
	 * @return the current {@link GUI3D}.
	 * @throws ArgumentIsNullException if the given title is null.
	 * @throws EmptyArgumentException if the given title is blank.
	 */
	public final G setTitle(final String title) {
		
		//Asserts that the given title is not blank.
		Validator.assertThat(title).thatIsNamed(LowerCaseCatalogue.TITLE).isNotBlank();
		
		//Sets the title of the current 3D_GUI.
		this.title.setValue(title);
		
		return asConcrete();
	}
	
	/**
	 * @param shape
	 * @return a new shape renderer for the given shape from the current {@link GUI3D}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link GUI3D} does not contain a shape renderer for the given shape.
	 */
	protected final IShapeRenderer<?, ?, ?> getShapeRendererFor(final Shape<?> shape) {
		return
		shapeClasses
		.getRefFirst(sc -> sc.getRefElement1() == shape.getClass())
		.getRefElement2();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetConfigurationElement() {
		setTitle(DEFAULT_TITLE);
	}
	
	//method
	/**
	 * @param type
	 * @return a new shape of the given type.
	 * @throws InvalidArgumentException if the current {@link GUI3D} cannot create a shape of the given type.
	 */
	private Shape<?> createShape(final String type) {
		try {
			return
			(Shape<?>)
			shapeClasses
			.getRefFirst(sc -> sc.getRefElement1().getSimpleName().equals(type))
			.getRefElement1().getDeclaredConstructor().newInstance();
		} catch (
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
				LowerCaseCatalogue.TYPE,
				type,
				"is not valid because the current " + getType() + " cannot create a '" + type + "' shape"
			);
		}
	}
	
	//method
	/**
	 * @return the shapes of the current {@link GUI3D}.
	 */
	private final ReadContainer<Shape<?>> getRefShapes() {
		
		final LinkedList<Shape<?>> shapes = new LinkedList<>();
		
		//Handles the case that the current {@link _3D_GUI} has a root shape.
		if (hasRootShape()) {
			shapes.addAtEnd(getRefRootShape());
		}
		
		return ReadContainer.forIterable(shapes);
	}
}
