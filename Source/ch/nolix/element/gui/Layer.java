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
 * @month 2019-05
 * @lines 1290
 */
public class Layer extends ConfigurableElement<Layer>
implements Clearable, IOccupiableCanvasInputActionManager<Layer>, IResizableInputTaker {
	
	//constants
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final ExtendedContentPosition DEFAULT_CONTENT_POSITION = ExtendedContentPosition.TOP;
	public static final Discrete2DPoint DEFAULT_FREE_CONTENT_POSITION = new Discrete2DPoint(1, 1);
	public static final boolean DEFAULT_CONFIGURATION_ALLOW_STATE = true;
	
	//constants
	public static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	public static final String ROOT_WIDGET_HEADER = "RootWidget";
	public static final String FREE_CONTENT_POSITION_HEADER = "FreeContentPosition";
	public static final String CONFIGURATION_ALLOWED_HEADER = "ConfigurationAllowed";
	
	//static method
	/**
	 * Creates a new {@link Layer} from the given specification.
	 * 
	 * @param specification
	 * @return a new {@link Layer} from the given specification that will belong to the given parentGUI.
	 * @throws InvalidArgumentException if the given specification is not valid.
	 */
	public static Layer fromSpecification(final BaseNode specification) {
		return new Layer().reset(specification);
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
			}
			else {
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
	
	//attribute
	private boolean notedLeftMouseButtonPress;
	//TODO: private boolean notedRightMouseButtonPress
	//TODO: private boolean notedMouseWheelPress
	
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
	
	//constructor
	/**
	 * Creates a new {@link Layer}.
	 */
	public Layer() {
		reset();
	}
	
	//constructor
	/**
	 * Creates a new {@link Layer} with the given contentPosition and rootWidget.
	 * 
	 * @param contentPosition
	 * @param rootWidget
	 * @throws ArgumentIsNullException if the given contentPosition is null.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public Layer(ExtendedContentPosition contentPosition, Widget<?, ?> rootWidget) {
		
		//Calls other constructor.
		this(rootWidget);
		
		setContentPosition(contentPosition);
	}
	
	//constructor
	/**
	 * Creates a new {@link Layer} with the given rootWidget.
	 * 
	 * @param rootWidget
	 * @throws ArgumentIsNullException if the given parentGUI is null.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public Layer(final Widget<?, ?> rootWidget) {
		
		//Calls other constructor.
		this();
		
		setRootWidget(rootWidget);
	}
	
	//method
	@Override
	public final void addOrChangeAttribute(final BaseNode attribute) {
		if (WidgetGUI.canCreateWidgetFrom(attribute)) {
			setRootWidget(WidgetGUI.createWidgetFrom(attribute));
		}
		else {
			super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} allowes to be configurated.
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
	@Override
	public LinkedList<Node> getAttributes() {
		
		final var attributes = super.getAttributes();
		
		if (rootWidget != null) {
			attributes.addAtEnd(rootWidget.getSpecification());
		}
		
		return attributes;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean freeAreaIsUnderCursor() {
		
		//For a better performance, this implementation does not use all comfortable methods.
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
	/**
	 * @return the {@link CursorIcon} of the current {@link Layer}.
	 */
	public final CursorIcon getCursorIcon() {
		
		if (rootWidget != null && rootWidget.isUnderCursor()) {
			return rootWidget.getCursorIcon();
		}
				
		return CursorIcon.ARROW;
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
	/**
	 * @return the {@link WidgetGUI} the current {@link Layer} belongs to.
	 * @throws ArgumentDoesNotBelongToParentException
	 * if the current {@link Layer} does not belong to a {@link WidgetGUI}.
	 */
	public final WidgetGUI<?> getParentGUI() {
		
		//Asserts that the current Layer belongs to a GUI.
		//For a better performance, this implementation does not use all comfortable methods.
		if (parentGUI == null) {
			throw new ArgumentDoesNotBelongToParentException(this, "GUI");
		}
		
		return parentGUI;
	}
	
	//method
	/**
	 * @return the root {@link Widget} of the current {@link Layer}.
	 */
	public final Widget<?, ?> getRefRootWidget() {
		return rootWidget;
	}
	
	//method
	/**
	 * @return the {@link Widget}s of the current {@link Layer} that are supposed to be painted.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgetsForPainting() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		
		//Handles the case that the current GUILayer does not have a root Widget.
		if (rootWidget == null) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current GUILayer has a root Widget.			
		return rootWidget.getRefPaintableWidgets().addAtEnd(rootWidget);
	}
	
	//method
	/**
	 * @return the triggerable {@link Widget}s of the current {@link Layer} recursively.
	 */
	public final IContainer<Widget<?, ?>> getRefWidgets() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		
		//Handles the case that the current Layer does not have a root Widget.
		if (rootWidget == null) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current Layer has a root Widget.
		return rootWidget.getChildWidgetsRecursively().addAtEnd(rootWidget);
	}
	
	//method
	/**
	 * @return the role of the current {@link Layer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Layer} does not have a role.
	 */
	public final LayerRole getRole() {
		
		assertHasRole();
		
		return role.getValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IContainer<IConfigurableElement<?>> getSubConfigurables() {
		
		final var configurables = new LinkedList<IConfigurableElement<?>>();
		
		if (containsAny()) {
			configurables.addAtEnd(rootWidget);
		}
		
		return configurables;
	}
	
	//method
	/**
	 * @return the background {@link Color} of the current {@link Layer}.
	 */
	public final boolean hasBackgroundColor() {
		return backgroundColor.containsAny();
	}
	
	//method
	/**
	 * @return the background {@link ColorGradient} of the current {@link Layer}.
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
	/**
	 * @return true if the current {@link Layer} noted a left mouse button press.
	 */
	public final boolean notedLeftMouseButtonPress() {
		return notedLeftMouseButtonPress;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyPress(final Key key) {
		
		if (continuousKeyPressAction != null) {
			continuousKeyPressAction.getOutput(this, key);
		}
		
		if (rootWidget != null) {
			rootWidget.noteKeyPress(key);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyRelease(Key key) {
		if (rootWidget != null) {
			rootWidget.noteKeyRelease(key);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyTyping(final Key key) {
		if (rootWidget != null) {
			rootWidget.noteKeyTyping(key);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonClick() {
		
		if (rootWidget != null) {
			rootWidget.noteLeftMouseButtonClick();
		}
		
		if (leftMouseButtonClickAction != null) {
			leftMouseButtonClickAction.run(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonPress() {
		
		notedLeftMouseButtonPress = true;
		
		if (rootWidget != null) {
			rootWidget.noteLeftMouseButtonPress();
		}
		
		if (leftMouseButtonPressAction != null) {
			leftMouseButtonPressAction.run(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonRelease() {
		if (notedLeftMouseButtonPress()) {
			noteLeftMouseButtonReleaseWhenNotedLeftMouseButtonPress();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseMove(final int cursorXPosition, final int cursorYPosition) {
		
		this.cursorXPosition = cursorXPosition;
		this.cursorYPosition = cursorYPosition;
				
		if (rootWidget != null) {
			rootWidget.noteMouseMove(
				cursorXPosition - rootWidget.getXPosition(),
				cursorYPosition - rootWidget.getYPosition()
			);
		}
		
		if (mouseMoveAction != null) {
			mouseMoveAction.run(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelClick() {
		
		if (rootWidget != null) {
			rootWidget.noteMouseWheelClick();
		}
		
		if (mouseWheelClickAction != null) {
			mouseWheelClickAction.run(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelPress() {
		
		if (rootWidget != null) {
			rootWidget.noteMouseWheelPress();
		}
		
		if (mouseWheelPressAction != null) {
			mouseMoveAction.run(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRelease() {
		
		if (rootWidget != null) {
			rootWidget.noteMouseWheelRelease();
		}
		
		if (mouseWheelReleaseAction != null) {
			mouseWheelReleaseAction.run(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {
		if (rootWidget != null) {
			rootWidget.noteMouseWheelRotationStep(rotationDirection);
		}		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		if (containsAny() && getRefRootWidget() instanceof BorderWidget) {
			
			final var borderWidget = getRefRootWidget().as(BorderWidget.class);
			
			if (borderWidget.hasAutomaticSize()) {					
				borderWidget.setProposalSize(viewAreaWidth, viewAreaHeight);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonClick() {
		
		if (rootWidget != null) {
			rootWidget.noteRightMouseButtonClick();
		}
		
		if (rightMouseButtonClickAction != null) {
			rightMouseButtonClickAction.run(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonPress() {
		
		if (rootWidget != null) {
			rootWidget.noteRightMouseButtonPress();
		}
		
		if (rightMouseButtonPressAction != null) {
			rightMouseButtonPressAction.run(this);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonRelease() {
		
		if (rootWidget != null) {
			rootWidget.noteRightMouseButtonRelease();
		}
		
		if (rightMouseButtonReleaseAction != null) {
			rightMouseButtonReleaseAction.run(this);
		}
	}
	
	//method
	/**
	 * Paints the current {@link Layer} using the given painter.
	 * 
	 * @param painter
	 */
	public final void paint(final IPainter painter) {
		
		//Paints the background of the current GUILayer.
		paintBackground(painter);
		
		//Handles the case that the current GUILayer has a root Widget.
		//For a better performance, this implementation does not use all comfortable methods.
		if (rootWidget != null) {
			rootWidget.paintRecursively(painter);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Recalculates the current {@link Layer}.
	 */
	public final void recalculate() {
		
		//Handles the case that the current GUILayer has a root Widget.
		if (rootWidget != null) {
			
			setAutomaticSizeToRootWidget();
			
			//Recalculates the root Widget to update its size.
			rootWidget.recalculate();
			
			//Enumerates the content position of the current GUILayer.
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
	
	//method
	/**
	 * Removes any background of the current {@link Layer}.
	 * 
	 * @return the current {@link Layer}.
	 */
	public final Layer removeBackground() {
		
		backgroundColor.clear();
		backgroundColorGradient.clear();
		
		return this;
	}
	
	//method
	/**
	 * Removes the current {@link Layer} from its parent {@link GUI}.
	 */
	public final void removeSelfFromGUI() {
		if (belongsToGUI()) {
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
	 * @param x
	 * @param y
	 * @return the current {@link Layer}.
	 */
	public final Layer setFreeContentPosition(final int x, final int y) {
		
		setContentPosition(ExtendedContentPosition.FREE);
		setFreeContentPosition_(x, y);
		
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
	/**
	 * Sets the root {@link Widget} of the current {@link Layer}.
	 * 
	 * @param rootWidget
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final Layer setRootWidget(final Widget<?, ?> rootWidget) {
		
		Validator.assertThat(rootWidget).thatIsNamed("root widget").isNotNull();
		
		//For a better performance, this implementation does not use all comfortable methods.
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
	protected final void resetStage2() {
		setConfigurationAllowed();
		setFreeContentPosition(DEFAULT_FREE_CONTENT_POSITION.getX(), DEFAULT_FREE_CONTENT_POSITION.getY());
		clear();
	}
	
	//method
	/**
	 * Sets the {@link GUI} the current {@link Layer} will belong to.
	 * 
	 * @param parentGUI
	 * @throws ArgumentIsNullException if the given parentGUI is null.
	 */
	final void setParentGUI(final WidgetGUI<?> parentGUI) {
		
		Validator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		//For a better performance, this implementation does not use all comfortable methods.
		
		if (this.parentGUI != null && this.parentGUI != parentGUI) {
			throw new ArgumentBelongsToUnexchangeableParentException(this, this.parentGUI);
		}
		
		if (rootWidget != null) {
			rootWidget.setParent(parentGUI);
		}
		
		this.parentGUI = parentGUI;
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Layer} does not have a role.
	 */
	private void assertHasRole() {
		if (!hasRole()) {
			throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ROLE);
		}
	}
	
	//method
	private void noteLeftMouseButtonReleaseWhenNotedLeftMouseButtonPress() {
		
		if (rootWidget != null) {
			rootWidget.noteLeftMouseButtonRelease();
		}
		
		if (leftMouseButtonReleaseAction != null) {
			leftMouseButtonReleaseAction.run(this);
		}
	}
	
	//method
	private void paintBackground(final IPainter painter) {
		
		//Handles the case that the current GUILayer has a background color.
		if (hasBackgroundColor()) {
			painter.setColor(getBackgroundColor());
			painter.paintFilledRectangle(
				parentGUI.getViewAreaWidth(),
				parentGUI.getViewAreaHeight()
			);
		}
		
		//Handles the case that the current GUILayer has a background color gradient.
		else if (hasBackgroundColorGradient()) {
			painter.setColorGradient(getBackgroundColorGradient());
			painter.paintFilledRectangle(
				parentGUI.getViewAreaWidth(),
				parentGUI.getViewAreaHeight()
			);
		}
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	private void setAutomaticSizeToRootWidget() {
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
	private void setFreeContentPosition_(final int x, final int y) {
		freeContentPosition.setValue(new Discrete2DPoint(x, y));
	}
}
