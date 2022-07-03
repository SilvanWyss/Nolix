//package declaration
package ch.nolix.system.gui3d.main;

//Java imports
import java.lang.reflect.InvocationTargetException;

import ch.nolix.core.commontype.constant.StringCatalogue;
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.pair.Pair;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PascalCaseCatalogue;
import ch.nolix.core.programcontrol.groupcloseable.CloseController;
import ch.nolix.core.skilluniversalapi.Clearable;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.configuration.ConfigurationElement;
import ch.nolix.system.element.MutableOptionalSpecificationValueExtractor;
import ch.nolix.system.element.MutableValue;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.Image;
import ch.nolix.system.gui.main.GUIIconCatalogue;
import ch.nolix.system.gui.main.LocalFrontEndReader;
import ch.nolix.system.gui.main.LocalFrontEndWriter;
import ch.nolix.systemapi.elementapi.configurationapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.imageapi.IImage;
import ch.nolix.systemapi.guiapi.mainapi.CursorIcon;
import ch.nolix.systemapi.guiapi.mainapi.IBaseGUI;
import ch.nolix.systemapi.guiapi.mainapi.IFrontEndReader;
import ch.nolix.systemapi.guiapi.mainapi.IFrontEndWriter;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-11
 * @param <G> is the type of a {@link GUI3D}.
 */
public abstract class GUI3D<G extends GUI3D<G>> extends ConfigurationElement<G> implements Clearable, IBaseGUI<G> {
	
	//constants
	public static final String DEFAULT_TITLE = StringCatalogue.DEFAULT_STRING;
	public static final Image DEFAULT_ICON = GUIIconCatalogue.NOLIX_ICON;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	
	//constants
	private static final String ICON_HEADER = PascalCaseCatalogue.ICON;
	private static final String ROOT_SHAPE_HEADER = "RootShape";
	
	//attribute
	private final MutableValue<String> title =
	new MutableValue<>(
		PascalCaseCatalogue.TITLE,
		DEFAULT_TITLE,
		this::setTitle,
		INode::getSingleChildNodeHeader,
		Node::withChildNode
	);
	
	//attribute
	private final MutableValue<Image> icon =
	new MutableValue<>(
		ICON_HEADER,
		DEFAULT_ICON,
		this::setIcon,
		Image::fromSpecification,
		Image::getSpecification
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
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalSpecificationValueExtractor rootShapeExtractor =
	new MutableOptionalSpecificationValueExtractor(
		ROOT_SHAPE_HEADER,
		s -> setRootShape(createShape(s.getRefSingleChildNode())),
		this::containsAny,
		() -> Node.withChildNode(rootShape.getSpecification())
	);
	
	//multi-attribute
	private LinkedList<Pair<Class<?>, IShapeRenderer<?, ?, ?>>> shapeClasses = new LinkedList<>();
	
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
	public final G addShapeClass(final Class<?> shapeClass, IShapeRenderer<?, ?, ?> shapeRenderer) {
		
		//Asserts that the given shape class is not null.
		GlobalValidator.assertThat(shapeClass).thatIsNamed("shape class").isNotNull();
		
		//Asserts that the given shape renderer is not null.
		GlobalValidator.assertThat(shapeRenderer).thatIsNamed("shape renderer").isNotNull();
		
		//Asserts that the current {@link _3D_GUI} does not contain already
		//a shape class with the same name as the given shape class.
		if (canCreateShape(shapeClass.getSimpleName())) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				this,
				"contains already a shape class '" + shapeClass + "'"
			);
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
		return shapeClasses.containsAny(sc -> sc.getRefElement1().getSimpleName().equals(type));
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
	public final IFrontEndReader fromFrontEnd() {
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
	public final CursorIcon getCursorIcon() {
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
	public final ReadContainer<? extends IConfigurableElement<?>> getSubConfigurables() {
		return getRefShapes();
	}
	
	//method
	/**
	 * @return the root shape of the current {@link GUI3D}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link GUI3D} does not have a root shape.
	 */
	public final Shape<?> getRefRootShape() {
		
		//Asserts that the current {@link _3D_GUI} has a root shape.
		if (isEmpty()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "root shape");
		}
		
		return rootShape;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Image getIcon() {
		return icon.getValue();
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
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isEmpty() {
		return (rootShape == null);
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
	public final void refresh() {
		
		//Handles the case that the current {@link _3D_GUI} has a root shape.
		if (containsAny()) {
			getRefRootShape().renderRecursively();
		}
		
		refreshGUI();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void resetElementConfiguration() {
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
	public final G setRootShape(final Shape<?> rootShape) {
		
		//Sets this GUI to the given root shape.
		rootShape.setGUI(this);
		
		//Sets the given root shape to the current {@link _3D_GUI}.
		this.rootShape = rootShape;
		
		noteSetRootShape(rootShape);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final G setIcon(final IImage icon) {
		
		this.icon.setValue(Image.fromAnyImage(icon.withWidthAndHeight(64, 64)));
		
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
	@Override
	public final G setTitle(final String title) {
		
		//Asserts that the given title is not blank.
		GlobalValidator.assertThat(title).thatIsNamed(LowerCaseCatalogue.TITLE).isNotBlank();
		
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
	
	//method declaration
	/**
	 * Notes that the given rootShape was set to the current {@link GUI3D}.
	 * 
	 * @param rootShape
	 */
	protected abstract void noteSetRootShape(Shape<?> rootShape);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetConfigurationElement() {
		setTitle(DEFAULT_TITLE);
	}
	
	//method declaration
	/**
	 * Refreshes the current {@link GUI3D}.
	 */
	protected abstract void refreshGUI();
	
	//method
	/**
	 * @param specification
	 * @return a new {@link Shape} of the given specification.
	 * @throws InvalidArgumentException if the current specification is not valid.
	 */
	private Shape<?> createShape(final INode<?> specification) {
		
		final var shape = createShape(specification.getHeader());
		shape.resetFromSpecification(specification);
		
		return shape;
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
			throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
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
		if (containsAny()) {
			shapes.addAtEnd(getRefRootShape());
		}
		
		return ReadContainer.forIterable(shapes);
	}
}
