//package declaration
package ch.nolix.element.gui;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionapi.I2ElementTaker;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.invalidargumentexception.ArgumentBelongsToUnexchangeableParentException;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.skillapi.Clearable;
import ch.nolix.common.skillapi.Recalculable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.base.OptionalValue;
import ch.nolix.element.baseguiapi.IOccupiableCanvasInputActionManager;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.configuration.ConfigurableElement;
import ch.nolix.element.discretegeometry.Discrete2DPoint;
import ch.nolix.element.elementapi.IConfigurableElement;
import ch.nolix.element.elementenum.ContentPosition;
import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.input.IInputTaker;
import ch.nolix.element.input.IResizableInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.painterapi.IPainter;
import ch.nolix.element.widget.BorderWidget;

//class
/**
 * A {@link Layer} can have:
 * -a background {@link Color} or background {@ColorGradient}
 * -a root {@link Widget}
 * 
 * A {@link Layer} can belong to a {@link WidgetGUI}.
 * 
 * @author Silvan Wyss
 * @date 2019-05-18
 * @lines 1370
 */
public class Layer extends ConfigurableElement<Layer>
implements Clearable, IOccupiableCanvasInputActionManager<Layer>, IResizableInputTaker, Recalculable {
	
	//constants
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final ExtendedContentPosition DEFAULT_CONTENT_POSITION = ExtendedContentPosition.TOP;
	public static final Discrete2DPoint DEFAULT_FREE_CONTENT_POSITION = new Discrete2DPoint(1, 1);
	public static final boolean DEFAULT_CONFIGURATION_ALLOW_STATE = true;
	
	//constants
	private static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	private static final String FREE_CONTENT_POSITION_HEADER = "FreeContentPosition";
	private static final String CONFIGURATION_ALLOWED_HEADER = "ConfigurationAllowed";
	
	//static method
	/**
	 * @param specification
	 * @return a new {@link Layer} from the given specification.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Layer fromSpecification(final BaseNode specification) {
		
		final var layer = new Layer();
		layer.resetFrom(specification);
		
		return layer;
	}
	
	//attribute
	private final OptionalValue<LayerRole> role =
	new OptionalValue<>(
		PascalCaseNameCatalogue.ROLE,
		this::setRole,
		LayerRole::fromSpecification,
		LayerRole::getSpecification
	);
	
	//attribute
	private final MutableValue<Boolean> configurationAllowed =
	new MutableValue<>(
		CONFIGURATION_ALLOWED_HEADER,
		DEFAULT_CONFIGURATION_ALLOW_STATE,
		ca -> {
			if (ca.booleanValue()) {
				setConfigurationAllowed();
			} else {
				setConfigurationNotAllowed();
			}
		},
		BaseNode::getOneAttributeAsBoolean,
		Node::withAttribute
	);
	
	//attribute
	private final MutableOptionalValue<Color> backgroundColor =
	new MutableOptionalValue<>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		this::setBackgroundColor,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<ColorGradient> backgroundColorGradient =
	new MutableOptionalValue<>(
		BACKGROUND_COLOR_GRADIENT_HEADER, 
		this::setBackgroundColorGradient,
		ColorGradient::fromSpecification,
		ColorGradient::getSpecification
	);
	
	//attribute
	private final MutableValue<ExtendedContentPosition> contentPosition =
	new MutableValue<>(
		ContentPosition.TYPE_NAME,
		DEFAULT_CONTENT_POSITION,
		this::setContentPosition,
		ExtendedContentPosition::fromSpecification,
		ExtendedContentPosition::getSpecification
	);
	
	//attribute
	private final MutableValue<Discrete2DPoint> freeContentPosition =
	new MutableValue<>(
		FREE_CONTENT_POSITION_HEADER,
		DEFAULT_FREE_CONTENT_POSITION,
		fcp -> setFreeContentPosition_(fcp.getX(), fcp.getY()),
		Discrete2DPoint::fromSpecification,
		Discrete2DPoint::getSpecification
	);
	
	//attributes
	private int cursorXPosition;
	private int cursorYPosition;
	
	//attributes
	private boolean notedLeftMouseButtonPress;
	private boolean notedRightMouseButtonPress;
	private boolean notedMouseWheelPress;
	private boolean notedKeyPress;
	
	//optional attribute
	/**
	 * The {@link WidgetGUI} the current {@link Layer} belongs to
	 * if the current {@link Layer} belongs to a {@link WidgetGUI}.
	 */
	private WidgetGUI<?> parentGUI;
	
	//optional attribute
	private Widget<?, ?> rootWidget;
	
	//optional attributes
	private I2ElementTaker<Layer, Key> continuousKeyPressAction;
	private IElementTaker<Layer> mouseMoveAction;
	private IElementTaker<Layer> leftMouseButtonClickAction;
	private IElementTaker<Layer> leftMouseButtonPressAction;
	private IElementTaker<Layer> leftMouseButtonReleaseAction;
	private IElementTaker<Layer> rightMouseButtonClickAction;
	private IElementTaker<Layer> rightMouseButtonPressAction;
	private IElementTaker<Layer> rightMouseButtonReleaseAction;
	private IElementTaker<Layer> mouseWheelClickAction;
	private IElementTaker<Layer> mouseWheelPressAction;
	private IElementTaker<Layer> mouseWheelReleaseAction;
	
	//method
	@Override
	public final void addOrChangeAttribute(final BaseNode attribute) {
		if (WidgetGUI.canCreateWidgetFrom(attribute)) {
			setRootWidget(WidgetGUI.createWidgetFrom(attribute));
		} else {
			
			//Calls method of the base class.
			super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} allows to be configured.
	 */
	public final boolean allowesConfiguration() {
		return configurationAllowed.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} belongs to a {@link WidgetGUI}.
	 */
	public final boolean belongsToGUI() {
		return (parentGUI != null);
	}
	
	//method
	/**
	 * Removes the root {@link Widget} of the current {@link Layer}.
	 */
	@Override
	public final void clear() {
		rootWidget = null;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean freeAreaIsUnderCursor() {
		return (isUnderCursor() && (rootWidget == null || !rootWidget.isUnderCursor()));
	}
	
	//method
	/**
	 * @return the background {@link Color} of the current {@link Layer}.
	 */
	public final Color getBackgroundColor() {
		return backgroundColor.getValue();
	}
	
	//method
	/**
	 * @return the background {@link ColorGradient} of the current {@link Layer}.
	 */
	public final ColorGradient getBackgroundColorGradient() {
		return backgroundColorGradient.getValue();
	}
	
	//method
	/**
	 * @return the content position of the current {@link Layer}.
	 */
	public final ExtendedContentPosition getContentPosition() {
		return contentPosition.getValue();
	}
	
	//method
	/**
	 * @return the x-free-position of the current {@link Layer}.
	 */
	public final int getContentXFreePosition() {
		return freeContentPosition.getValue().getX();
	}
	
	//method
	/**
	 * @return the y-free-position of the current {@link Layer}.
	 */
	public final int getContentYFreePosition() {
		return freeContentPosition.getValue().getY();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the {@link CursorIcon} of the current {@link Layer}.
	 */
	public final CursorIcon getCursorIcon() {
		
		if (rootWidget != null && rootWidget.isUnderCursor()) {
			return rootWidget.getCursorIcon();
		}
				
		return DEFAULT_CURSOR_ICON;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link Layer}.
	 */
	public final int getCursorXPosition() {
		return cursorXPosition;
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link Layer}.
	 */
	public final int getCursorYPosition() {
		return cursorYPosition;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the {@link WidgetGUI} the current {@link Layer} belongs to.
	 * @throws ArgumentDoesNotBelongToParentException
	 * if the current {@link Layer} does not belong to a {@link WidgetGUI}.
	 */
	public final WidgetGUI<?> getParentGUI() {
		
		//Asserts that the current Layer belongs to a GUI.
		if (parentGUI == null) {
			throw new ArgumentDoesNotBelongToParentException(this, VariableNameCatalogue.GUI);
		}
		
		return parentGUI;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the root {@link Widget} of the current {@link Layer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Layer} does not have a root {@link Widget}.
	 */
	public final Widget<?, ?> getRefRootWidget() {
		
		//Asserts that the current Layer has a root Widget.
		if (rootWidget == null) {
			throw new ArgumentDoesNotHaveAttributeException(this, "root Widget");
		}
		
		return rootWidget;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the {@link Widget}s of the current {@link Layer} that are supposed to be painted.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgetsForPainting() {
		
		//Handles the case that the current Layer does not have a root Widget.
		if (rootWidget == null) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current Layer has a root Widget.			
		return rootWidget.getRefPaintableWidgets().addAtEnd(rootWidget);
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the {@link Widget}s of the current {@link Layer}.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgets() {
		
		//Handles the case that the current Layer does not have a root Widget.
		if (rootWidget == null) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current Layer has a root Widget.
		return rootWidget.getChildWidgetsRecursively().addAtEnd(rootWidget);
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the role of the current {@link Layer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Layer} does not have a role.
	 */
	public final LayerRole getRole() {
		
		//Asserts that the current Layer has a role.
		if (role.isEmpty()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ROLE);
		}
		
		return role.getValue();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<IConfigurableElement<?>> getSubConfigurables() {
		
		final var configurables = new LinkedList<IConfigurableElement<?>>();
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			configurables.addAtEnd(rootWidget);
		}
		
		return configurables;
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a background {@link Color}.
	 */
	public final boolean hasBackgroundColor() {
		return backgroundColor.containsAny();
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a background {@link ColorGradient}.
	 */
	public final boolean hasBackgroundColorGradient() {
		return backgroundColorGradient.containsAny();
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a role.
	 */
	public final boolean hasRole() {
		return role.containsAny();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasRole(final String role) {
		return (hasRole() && getRole().toString().equals(role));
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a root {@link Widget}.
	 */
	@Override
	public final boolean isEmpty() {
		return (rootWidget == null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isUnderCursor() {
		return getParentGUI().viewAreaIsUnderCursor();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyPress(final Key key) {
		
		notedKeyPress = true;
		
		//Handles the case that the current Layer has a continuous key press action.
		if (continuousKeyPressAction != null) {
			continuousKeyPressAction.getOutput(this, key);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteKeyPress(key);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Lets the current {@link IInputTaker} note a key release if
	 * the current {@link Layer} noted already a key press.
	 * 
	 * @param key
	 */
	@Override
	public final void noteKeyRelease(final Key key) {
		
		//Handles the case that the current Layer noted already a key press.
		if (notedKeyPress) {
			noteKeyReleaseWhenNotedKeyPress(key);
		}	
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyTyping(final Key key) {
		
		notedKeyPress = true;
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteKeyTyping(key);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonClick() {
				
		notedLeftMouseButtonPress = true;
		
		//Handles the case that the current Layer has a left mouse button click action.
		if (leftMouseButtonClickAction != null) {
			leftMouseButtonClickAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteLeftMouseButtonClick();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonPress() {
		
		notedLeftMouseButtonPress = true;
		
		//Handles the case that the current Layer has a left mouse button press action.
		if (leftMouseButtonPressAction != null) {
			leftMouseButtonPressAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteLeftMouseButtonPress();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Lets the current {@link IInputTaker} note a left mouse button release if
	 * the current {@link Layer} noted already a left mouse button press.
	 */
	@Override
	public final void noteLeftMouseButtonRelease() {
		
		//Handles the case that the current Layer noted already a left mouse button press.
		if (notedLeftMouseButtonPress) {
			noteLeftMouseButtonReleaseWhenNotedLeftMouseButtonPress();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseMove(final int cursorXPosition, final int cursorYPosition) {
		
		//Sets the cursor position of the current Layer.
		this.cursorXPosition = cursorXPosition;
		this.cursorYPosition = cursorYPosition;
		
		//Handles the case that the current Layer has a mouse move action.
		if (mouseMoveAction != null) {
			mouseMoveAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteMouseMove(
				cursorXPosition - rootWidget.getXPosition(),
				cursorYPosition - rootWidget.getYPosition()
			);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelClick() {
		
		notedMouseWheelPress = true;
		
		//Handles the case that the current Layer has a mouse wheel click action.
		if (mouseWheelClickAction != null) {
			mouseWheelClickAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteMouseWheelClick();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelPress() {
		
		notedMouseWheelPress = true;
		
		//Handles the case that the current Layer has a mouse wheel press action.
		if (mouseWheelPressAction != null) {
			mouseMoveAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteMouseWheelPress();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Lets the current {@link IInputTaker} note a mouse wheel release if
	 * the current {@link Layer} noted already a mouse wheel press.
	 */
	@Override
	public final void noteMouseWheelRelease() {
		
		//Handles the case that the current Layer noted already a mouse wheel press.
		if (notedMouseWheelPress) {
			noteMouseWheelReleaseWhenNotedMouseWheelPress();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteMouseWheelRotationStep(rotationDirection);
		}		
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		
		//Handles the case that the current Layer has a root Widget that is a BorderWidget.
		if (rootWidget instanceof BorderWidget) {
			noteResizeWhenHasBorderWidget(viewAreaWidth, viewAreaHeight);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonClick() {
		
		notedRightMouseButtonPress = true;
		
		//Handles the case that the current Layer has a right mouse button click action.
		if (rightMouseButtonClickAction != null) {
			rightMouseButtonClickAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteRightMouseButtonClick();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonPress() {
		
		notedRightMouseButtonPress = true;
		
		//Handles the case that the current Layer has a right mouse button press action.
		if (rightMouseButtonPressAction != null) {
			rightMouseButtonPressAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteRightMouseButtonPress();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Lets the current {@link IInputTaker} note a right mouse button release if
	 * the current {@link Layer} noted already a right mouse button press.
	 */
	@Override
	public final void noteRightMouseButtonRelease() {
		
		//Handles the case that the current Layer noted already a right mouse button press.
		if (notedRightMouseButtonPress) {
			noteRightMouseButtonReleaseWhenHasNotedRightMouseButtonPress();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Paints the current {@link Layer} using the given painter.
	 * 
	 * @param painter
	 */
	public final void paint(final IPainter painter) {
		
		//Paints the background of the current Layer.
		paintBackground(painter);
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.paintRecursively(painter);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public final void recalculate() {
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			recalculateWhenHasRootWidget();
		}
	}
	
	//method
	/**
	 * Removes any background of the current {@link Layer}.
	 */
	public final void removeBackground() {
		backgroundColor.clear();
		backgroundColorGradient.clear();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Removes the current {@link Layer} from its parent {@link GUI}.
	 */
	public final void removeSelfFromGUI() {
		if (parentGUI != null) {
			parentGUI.removeLayer(this);
			parentGUI = null;
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void resetConfigurationOnSelf() {
		setContentPosition(DEFAULT_CONTENT_POSITION);
	}
	
	//method
	/**
	 * Sets the background {@link Color} of the current {@link Layer}.
	 * Removes any former background of the current {@link Layer}.
	 * 
	 * @param backgroundColor
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given backgroundColor is null.
	 */
	public final Layer setBackgroundColor(final Color backgroundColor) {
		
		removeBackground();
		
		this.backgroundColor.setValue(backgroundColor);
		
		return this;
	}
	
	//method
	/**
	 * Sets the background {@link ColorGradient} of the current {@link Layer}.
	 * Removes any former background of the current {@link Layer}.
	 * 
	 * @param backgroundColorGradient
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given backgroundColorGradient is null.
	 */
	public final Layer setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		removeBackground();
		
		this.backgroundColorGradient.setValue(backgroundColorGradient);
		
		return this;
	}
	
	//method
	/**
	 * Lets the current {@link Layer} be allowed to be configured.
	 * 
	 * @return the current {@link Layer}.
	 */
	public final Layer setConfigurationAllowed() {
		
		configurationAllowed.setValue(true);
		
		return this;
	}
	
	//method
	/**
	 * Lets the current {@link Layer} be not allowed to be configured.
	 * 
	 * @return the current {@link Layer}.
	 */
	public final Layer setConfigurationNotAllowed() {
		
		configurationAllowed.setValue(false);
		
		return this;
	}
	
	//method
	/**
	 * Sets the {@link ExtendedContentPosition} of the current {@link Layer}.
	 * 
	 * @param contentPosition
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given contentPosition is null.
	 */
	public final Layer setContentPosition(final ExtendedContentPosition contentPosition) {
		
		this.contentPosition.setValue(contentPosition);
		
		return this;
	}
	
	//method
	/**
	 * Sets the continuous key press action of the current {@link Layer}. 
	 * 
	 * @param continuousKeyPressAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given continuousKeyPressAction is null.
	 */
	public final Layer setContinuousKeyPressAction(final I2ElementTaker<Layer, Key> continuousKeyPressAction) {
		
		//Asserts that the given customCursorIcon is not null.
		Validator.assertThat(continuousKeyPressAction).thatIsNamed("continuous key press action").isNotNull();
		
		this.continuousKeyPressAction = continuousKeyPressAction;
		
		return asConcrete();
	}
	
	/**
	 * Sets the free content position of the current {@link Layer}.
	 * 
	 * @param xFreeContentPosition
	 * @param yFreeContentPosition
	 * @return the current {@link Layer}.
	 */
	public final Layer setFreeContentPosition(final int xFreeContentPosition, final int yFreeContentPosition) {
		
		setContentPosition(ExtendedContentPosition.FREE);
		setFreeContentPosition_(xFreeContentPosition, yFreeContentPosition);
		
		return this;
	}
	
	//method
	/**
	 * Sets the left mouse button click action of the current {@link Layer}.
	 * 
	 * @param leftMouseButtonClickAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonClickAction is null.
	 */
	@Override
	public final Layer setLeftMouseButtonClickAction(final IElementTaker<Layer> leftMouseButtonClickAction) {
		
		Validator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button click action").isNotNull();
		
		this.leftMouseButtonClickAction = leftMouseButtonClickAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the left mouse button press action of the current {@link Layer}.
	 * 
	 * @param leftMouseButtonPressAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonPressAction is null.
	 */
	public final Layer setLeftMouseButtonPressAction(final IElementTaker<Layer> leftMouseButtonPressAction) {
		
		Validator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button press action").isNotNull();
		
		this.leftMouseButtonPressAction = leftMouseButtonPressAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the left mouse button release action of the current {@link Layer}.
	 * 
	 * @param leftMouseButtonReleaseAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonReleaseAction is null.
	 */
	@Override
	public final Layer setLeftMouseButtonReleaseAction(IElementTaker<Layer> leftMouseButtonReleaseAction) {
		
		Validator.assertThat(leftMouseButtonReleaseAction).thatIsNamed("left mouse button release action").isNotNull();
		
		this.leftMouseButtonReleaseAction = leftMouseButtonReleaseAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the mouse move action of the current {@link Layer}.
	 * 
	 * @param mouseMoveAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonPressAction is null.
	 */
	@Override
	public final Layer setMouseMoveAction(final IElementTaker<Layer> mouseMoveAction) {
		
		Validator.assertThat(mouseMoveAction).thatIsNamed("mouse move action").isNotNull();
		
		this.mouseMoveAction = mouseMoveAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the mouse wheel click action of the current {@link Layer}.
	 * 
	 * @param mouseWheelClickAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given mouseWheelClickAction is null.
	 */
	@Override
	public final Layer setMouseWheelClickAction(IElementTaker<Layer> mouseWheelClickAction) {
		
		Validator.assertThat(mouseWheelClickAction).thatIsNamed("mouse wheel click action").isNotNull();
		
		this.mouseWheelClickAction = mouseWheelClickAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the mouse wheel press action of the current {@link Layer}.
	 * 
	 * @param mouseWheelPressAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given mouseWheelPressAction is null.
	 */
	@Override
	public final Layer setMouseWheelPressAction(IElementTaker<Layer> mouseWheelPressAction) {
		
		Validator.assertThat(mouseWheelPressAction).thatIsNamed("mouse wheel press action").isNotNull();
		
		this.mouseWheelPressAction = mouseWheelPressAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the mouse wheel release action of the current {@link Layer}.
	 * 
	 * @param mouseWheelReleaseAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given mouseWheelReleaseAction is null.
	 */
	@Override
	public final Layer setMouseWheelReleaseAction(IElementTaker<Layer> mouseWheelReleaseAction) {
		
		Validator.assertThat(mouseWheelReleaseAction).thatIsNamed("mouse wheel release action").isNotNull();
		
		this.mouseWheelReleaseAction = mouseWheelReleaseAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the right mouse button click action of the current {@link Layer}.
	 * 
	 * @param rightMouseButtonClickAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonClickAction is null.
	 */
	@Override
	public final Layer setRightMouseButtonClickAction(IElementTaker<Layer> rightMouseButtonClickAction) {
		
		Validator.assertThat(rightMouseButtonClickAction).thatIsNamed("right mouse button click action").isNotNull();
		
		this.rightMouseButtonClickAction = rightMouseButtonClickAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the right mouse button press action of the current {@link Layer}.
	 * 
	 * @param rightMouseButtonPressAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonPressAction is null.
	 */
	@Override
	public final Layer setRightMouseButtonPressAction(IElementTaker<Layer> rightMouseButtonPressAction) {
		
		Validator.assertThat(rightMouseButtonPressAction).thatIsNamed("right mouse button press action").isNotNull();
		
		this.rightMouseButtonPressAction = rightMouseButtonPressAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the right mouse button release action of the current {@link Layer}.
	 * 
	 * @param rightMouseButtonReleaseAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonReleaseAction is null.
	 */
	@Override
	public final Layer setRightMouseButtonReleaseAction(IElementTaker<Layer> rightMouseButtonReleaseAction) {
		
		Validator.assertThat(rightMouseButtonReleaseAction).thatIsNamed("right mouse button release action").isNotNull();
		
		this.rightMouseButtonReleaseAction = rightMouseButtonReleaseAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the role of the current {@link Layer}.
	 * 
	 * @param role
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given role is null.
	 * @throws InvalidArgumentException if the current {@link Layer} has already a role.
	 */
	public final Layer setRole(final LayerRole role) {
		
		this.role.setValue(role);
		
		return this;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Sets the root {@link Widget} of the current {@link Layer}.
	 * 
	 * @param rootWidget
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final Layer setRootWidget(final Widget<?, ?> rootWidget) {
		
		Validator.assertThat(rootWidget).thatIsNamed("root Widget").isNotNull();
		
		//Handles the case that the current Layer belongs to a GUI.
		if (parentGUI != null) {
			rootWidget.setParent(parentGUI);
		}
		
		this.rootWidget = rootWidget;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpConfigurableElementAttributesInto(final LinkedList<Node> list) {
		
		//Handles the case that the current Layer has a root Widget.
		if (containsAny()) {
			list.addAtEnd(rootWidget.getSpecification());
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetConfigurableElement() {
		setConfigurationAllowed();
		clear();
	}
	
	//visibility-reduced method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Sets the {@link GUI} the current {@link Layer} will belong to.
	 * 
	 * @param parentGUI
	 * @throws ArgumentIsNullException if the given parentGUI is null.
	 */
	final void setParentGUI(final WidgetGUI<?> parentGUI) {
		
		Validator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		//Asserts that the current Layer does not belong to another GUI.
		if (this.parentGUI != null && this.parentGUI != parentGUI) {
			throw new ArgumentBelongsToUnexchangeableParentException(this, this.parentGUI);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.setParent(parentGUI);
		}
		
		//Sets the parentGUI of the current Layer.
		this.parentGUI = parentGUI;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void noteKeyReleaseWhenNotedKeyPress(final Key key) {
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteKeyRelease(key);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void noteLeftMouseButtonReleaseWhenNotedLeftMouseButtonPress() {
		
		//Handles the case that the current Layer has a left mouse button release action.
		if (leftMouseButtonReleaseAction != null) {
			leftMouseButtonReleaseAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteLeftMouseButtonRelease();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void noteMouseWheelReleaseWhenNotedMouseWheelPress() {
		
		//Handles the case that the current Layer has a mouse wheel release action.
		if (mouseWheelReleaseAction != null) {
			mouseWheelReleaseAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteMouseWheelRelease();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void noteResizeWhenHasBorderWidget(int viewAreaWidth, int viewAreaHeight) {
		
		//Gets the root Widget of the current Layer as BorderWidget.
		final var borderWidget = rootWidget.as(BorderWidget.class);
		
		//Handles the case that the borderWidget has activated automatic size.
		if (borderWidget.hasAutomaticSize()) {					
			borderWidget.setProposalSize(viewAreaWidth, viewAreaHeight);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void noteRightMouseButtonReleaseWhenHasNotedRightMouseButtonPress() {
		
		//Handles the case that the current Layer has a right mouse button release action.
		if (rightMouseButtonReleaseAction != null) {
			rightMouseButtonReleaseAction.run(this);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteRightMouseButtonRelease();
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void paintBackground(final IPainter painter) {
		
		//Handles the case that the current Layer has a background color.
		if (hasBackgroundColor()) {
			painter.setColor(getBackgroundColor());
			painter.paintFilledRectangle(parentGUI.getViewAreaWidth(), parentGUI.getViewAreaHeight());
			
		//Handles the case that the current Layer has a background color gradient.
		} else if (hasBackgroundColorGradient()) {
			painter.setColorGradient(getBackgroundColorGradient());
			painter.paintFilledRectangle(parentGUI.getViewAreaWidth(), parentGUI.getViewAreaHeight());
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void recalculateWhenHasRootWidget() {
			
		setAutomaticSizeToRootWidgetIfSuitable();
		
		rootWidget.recalculate();
		
		setPositionOfRootWidgetWhenHasRootWidget();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void setAutomaticSizeToRootWidgetIfSuitable() {
		if (rootWidget instanceof BorderWidget) {
			
			final var lRootWidget = rootWidget.as(BorderWidget.class);
			
			if (parentGUI != null && lRootWidget.hasAutomaticSize()) {
				
				final var viewAreaWidth = parentGUI.getViewAreaWidth();
				final var viewAreaHeight = parentGUI.getViewAreaHeight();
				
				if (viewAreaWidth > 0 && viewAreaHeight > 0) {
					lRootWidget.setProposalSize(
						parentGUI.getViewAreaWidth(),
						parentGUI.getViewAreaHeight()
					);
				}
			}
		}
	}
	
	//method
	private void setFreeContentPosition_(final int xFreeContentPosition, final int yFreeContentPosition) {
		freeContentPosition.setValue(new Discrete2DPoint(xFreeContentPosition, yFreeContentPosition));
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void setPositionOfRootWidgetWhenHasRootWidget() {
			
		//Enumerates the content position of the current Layer.
		switch (contentPosition.getValue()) {
			case TOP_LEFT:
				
				getRefRootWidget().setPositionOnParent(0, 0);
				
				break;
			case LEFT:
				
				getRefRootWidget().setPositionOnParent(
					0,
					Calculator.getMax(0, (getParentGUI().getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
				);
				
				break;
			case BOTTOM_LEFT:
				
				getRefRootWidget().setPositionOnParent(
					0,
					Calculator.getMax(0, getParentGUI().getViewAreaHeight() - getRefRootWidget().getHeight())
				);
				
				break;
			case TOP:
				
				getRefRootWidget().setPositionOnParent(
					Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
					0
				);
				
				break;
			case CENTER:
							
				getRefRootWidget().setPositionOnParent(
					Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
					Calculator.getMax(0, (parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
				);
				
				break;
			case BOTTOM:
				
				getRefRootWidget().setPositionOnParent(
					Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
					Calculator.getMax(0, parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight())
				);
				
				break;
			case TOP_RIGHT:
				
				getRefRootWidget().setPositionOnParent(
					Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
					0
				);
				
				break;
			case RIGHT:
			
				getRefRootWidget().setPositionOnParent(
					Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
					Calculator.getMax(0, (parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
				);
			
				break;
			case BOTTOM_RIGHT:
				
				getRefRootWidget().setPositionOnParent(
					Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
					Calculator.getMax(0, parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight())
				);
				
				break;
			case FREE:
				
				final var freeContentPositionValue = this.freeContentPosition.getValue();	
				
				getRefRootWidget().setPositionOnParent(
					freeContentPositionValue.getX(),
					freeContentPositionValue.getY()
				);
				
				break;
		}
	}
}
