//package declaration
package ch.nolix.system.gui.base;

//own imports
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PascalCaseCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentBelongsToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionapi.I2ElementTaker;
import ch.nolix.core.functionapi.IElementTaker;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.skillapi.Clearable;
import ch.nolix.core.skillapi.Recalculable;
import ch.nolix.system.configuration.ConfigurableElement;
import ch.nolix.system.discretegeometry.Discrete2DPoint;
import ch.nolix.system.element.MutableOptionalValue;
import ch.nolix.system.element.MutableOptionalValueExtractor;
import ch.nolix.system.element.MutableValue;
import ch.nolix.system.element.OptionalValue;
import ch.nolix.system.elementenum.ExtendedContentPosition;
import ch.nolix.system.elementenum.RotationDirection;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.color.ColorGradient;
import ch.nolix.system.gui.image.Background;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.input.IResizableInputTaker;
import ch.nolix.system.gui.widget.BorderWidget;
import ch.nolix.system.gui.widget.Widget;
import ch.nolix.systemapi.elementapi.IConfigurableElement;
import ch.nolix.systemapi.guiapi.baseapi.IOccupiableCanvasInputActionManager;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.inputapi.IInputTaker;
import ch.nolix.systemapi.guiapi.inputapi.Key;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

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
 */
public final class Layer extends ConfigurableElement<Layer>
implements Clearable, IOccupiableCanvasInputActionManager<Layer>, IResizableInputTaker, Recalculable {
	
	//constant
	public static final double DEFAULT_OPACITY_PERCENTAGE = 1.0;
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;
	
	//constant
	public static final ExtendedContentPosition DEFAULT_CONTENT_POSITION = ExtendedContentPosition.TOP;
	
	//constant
	public static final boolean DEFAULT_CONFIGURATION_ALLOW_STATE = true;
	
	//constant
	private static final String OPACITY_PERCENTAGE = "OpacityPercentage";
	
	//constant
	private static final String BACKGROUND_HEADER = "Background";
	
	//constant
	private static final String CONTENT_POSITION_HEADER = "ContentPosition";
	
	//constant
	private static final String FREE_CONTENT_POSITION_HEADER = "FreeContentPosition";
	
	//constant
	private static final String ROOT_WIDGET_HEADER = "RootWidget";
	
	//constant
	private static final String CONFIGURATION_ALLOWED_FLAG_HEADER = "ConfigurationAllowed";
		
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
		PascalCaseCatalogue.ROLE,
		this::setRole,
		LayerRole::fromSpecification,
		LayerRole::getSpecification
	);
	
	//attribute
	private final MutableValue<Double> opacityPercentage =
	MutableValue.forDouble(
		OPACITY_PERCENTAGE,
		DEFAULT_OPACITY_PERCENTAGE,
		this::setOpacityPercentage
	);
	
	//attribute
	private final MutableOptionalValue<Background> background =
	new MutableOptionalValue<>(
		BACKGROUND_HEADER,
		this::setBackground,
		Background::fromSpecification,
		Background::getSpecification
	);
	
	//attribute
	private final MutableValue<ExtendedContentPosition> contentPosition =
	new MutableValue<>(
		CONTENT_POSITION_HEADER,
		DEFAULT_CONTENT_POSITION,
		this::setContentPosition,
		ExtendedContentPosition::fromSpecification,
		ExtendedContentPosition::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<Discrete2DPoint> optionalFreeContentPosition =
	new MutableOptionalValue<>(
		FREE_CONTENT_POSITION_HEADER,
		fcp -> setFreeContentPosition_(fcp.getX(), fcp.getY()),
		Discrete2DPoint::fromSpecification,
		Discrete2DPoint::getSpecification
	);
	
	//attribute
	private final MutableValue<Boolean> configurationAllowedFlag =
	MutableValue.forBoolean(
		CONFIGURATION_ALLOWED_FLAG_HEADER,
		DEFAULT_CONFIGURATION_ALLOW_STATE,
		this::setConfigurationAllowedFlag
	);
	
	//attribute
	private int cursorXPosition;
	
	//attribute
	private int cursorYPosition;
	
	//attribute
	private boolean notedLeftMouseButtonPress;
	
	//attribute
	private boolean notedRightMouseButtonPress;
	
	//attribute
	private boolean notedMouseWheelPress;
	
	//attribute
	private boolean notedKeyPress;
	
	//optional attribute
	/**
	 * The {@link WidgetGUI} the current {@link Layer} belongs to
	 * if the current {@link Layer} belongs to a {@link WidgetGUI}.
	 */
	private WidgetGUI<?> parentGUI;
	
	//optional attribute
	private Widget<?, ?> rootWidget;
	
	//attribute
	@SuppressWarnings("unused")
	private final MutableOptionalValueExtractor<Widget<?, ?>> rootWidgetExtractor =
	new MutableOptionalValueExtractor<>(
		ROOT_WIDGET_HEADER,
		this::setRootWidget,
		this::containsAny,
		this::getRefRootWidget,
		s -> WidgetGUI.createWidgetFrom(s.getRefOneAttribute()),
		w -> Node.withAttribute(w.getSpecification())
	);
	
	//optional attribute
	private I2ElementTaker<Layer, Key> keyDownAction;
	
	//optional attribute
	private IElementTaker<Layer> mouseMoveAction;
	
	//optional attribute
	private IElementTaker<Layer> leftMouseButtonClickAction;
	
	//optional attribute
	private IElementTaker<Layer> leftMouseButtonPressAction;
	
	//optional attribute
	private IElementTaker<Layer> leftMouseButtonReleaseAction;
	
	//optional attribute
	private IElementTaker<Layer> rightMouseButtonClickAction;
	
	//optional attribute
	private IElementTaker<Layer> rightMouseButtonPressAction;
	
	//optional attribute
	private IElementTaker<Layer> rightMouseButtonReleaseAction;
	
	//optional attribute
	private IElementTaker<Layer> mouseWheelClickAction;
	
	//optional attribute
	private IElementTaker<Layer> mouseWheelPressAction;
	
	//optional attribute
	private IElementTaker<Layer> mouseWheelReleaseAction;
	
	//method
	/**
	 * @return true if the current {@link Layer} allows to be configured.
	 */
	public boolean allowesConfiguration() {
		return configurationAllowedFlag.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} belongs to a {@link WidgetGUI}.
	 */
	public boolean belongsToGUI() {
		return (parentGUI != null);
	}
	
	//method
	/**
	 * Removes the root {@link Widget} of the current {@link Layer}.
	 */
	@Override
	public void clear() {
		rootWidget = null;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean freeAreaIsUnderCursor() {
		return (isUnderCursor() && (rootWidget == null || !rootWidget.isUnderCursor()));
	}
	
	//method
	/**
	 * @return the background {@link Color} of the current {@link Layer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@Layer} does not have a background {@link Color}.
	 */
	public Color getBackgroundColor() {
		return background.getValue().getColor();
	}
	
	//method
	/**
	 * @return the background {@link ColorGradient} of the current {@link Layer}.
	 * @throws ArgumentDoesNotHaveAttributeException if
	 * the current {@Layer} does not have a background {@link ColorGradient}.
	 */
	public ColorGradient getBackgroundColorGradient() {
		return background.getValue().getColorGradient();
	}
	
	//method
	/**
	 * @return the background {@link MutableImage} of the current {@link Layer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@Layer} does not have a background {@link MutableImage}.
	 */
	public MutableImage getBackgroundImage() {
		return background.getValue().getImage();
	}
	
	//method
	/**
	 * @return the content position of the current {@link Layer}.
	 */
	public ExtendedContentPosition getContentPosition() {
		return contentPosition.getValue();
	}
	
	//method
	/**
	 * @return the x-free-position of the current {@link Layer}.
	 */
	public int getOptionalContentFreeXPosition() {
		return optionalFreeContentPosition.getValue().getX();
	}
	
	//method
	/**
	 * @return the y-free-position of the current {@link Layer}.
	 */
	public int getOptionalContentFreeYPosition() {
		return optionalFreeContentPosition.getValue().getY();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the {@link CursorIcon} of the current {@link Layer}.
	 */
	public CursorIcon getCursorIcon() {
		
		if (rootWidget != null && rootWidget.isUnderCursor()) {
			return rootWidget.getCursorIcon();
		}
				
		return DEFAULT_CURSOR_ICON;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link Layer}.
	 */
	public int getCursorXPosition() {
		return cursorXPosition;
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link Layer}.
	 */
	public int getCursorYPosition() {
		return cursorYPosition;
	}
	
	//method
	/**
	 * @return the opacity percentage of the current {@link Layer}.
	 */
	public double getOpacityPercentage() {
		return opacityPercentage.getValue();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the {@link WidgetGUI} the current {@link Layer} belongs to.
	 * @throws ArgumentDoesNotBelongToParentException
	 * if the current {@link Layer} does not belong to a {@link WidgetGUI}.
	 */
	public WidgetGUI<?> getParentGUI() {
		
		//Asserts that the current Layer belongs to a GUI.
		if (parentGUI == null) {
			throw new ArgumentDoesNotBelongToParentException(this, LowerCaseCatalogue.GUI);
		}
		
		return parentGUI;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the root {@link Widget} of the current {@link Layer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Layer} does not have a root {@link Widget}.
	 */
	public Widget<?, ?> getRefRootWidget() {
		
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
	public IContainer<Widget<?, ?>> getRefWidgetsForPainting() {
		
		//Handles the case that the current Layer does not have a root Widget.
		if (rootWidget == null) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current Layer has a root Widget.
		final var widgetsForPainting = rootWidget.getRefWidgetsForPainting();
		widgetsForPainting.addAtBegin(rootWidget);
		return widgetsForPainting;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the {@link Widget}s of the current {@link Layer}.
	 */
	public IContainer<Widget<?, ?>> getRefWidgets() {
		
		//Handles the case that the current Layer does not have a root Widget.
		if (rootWidget == null) {
			return new LinkedList<>();
		}
		
		//Handles the case that the current Layer has a root Widget.
		final var widgets = rootWidget.getChildWidgets();
		widgets.addAtEnd(rootWidget);
		return widgets;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the role of the current {@link Layer}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link Layer} does not have a role.
	 */
	public LayerRole getRole() {
		
		//Asserts that the current Layer has a role.
		if (!role.hasValue()) {
			throw new ArgumentDoesNotHaveAttributeException(this, LowerCaseCatalogue.ROLE);
		}
		
		return role.getValue();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IContainer<IConfigurableElement<?>> getSubConfigurables() {
		
		final var configurables = new LinkedList<IConfigurableElement<?>>();
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			configurables.addAtEnd(rootWidget);
		}
		
		return configurables;
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a background.
	 */
	public boolean hasBackground() {
		return background.hasValue();
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a background {@link Color}.
	 */
	public boolean hasBackgroundColor() {
		return (hasBackground() && background.getValue().isColor());
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a background {@link ColorGradient}.
	 */
	public boolean hasBackgroundColorGradient() {
		return (hasBackground() && background.getValue().isColorGradient());
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a background {@link MutableImage}.
	 */
	public boolean hasBackgroundImage() {
		return (hasBackground() && background.getValue().isImage());
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a full opacity.
	 */
	public boolean hasFullOpacity() {
		return (getOpacityPercentage() == 1.0);
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a role.
	 */
	public boolean hasRole() {
		return role.hasValue();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRole(final String role) {
		return (hasRole() && getRole().toString().equals(role));
	}
	
	//method
	/**
	 * @return true if the current {@link Layer} has a root {@link Widget}.
	 */
	@Override
	public boolean isEmpty() {
		return (rootWidget == null);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUnderCursor() {
		return getParentGUI().viewAreaIsUnderCursor();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteKeyDown(final Key key) {
		
		notedKeyPress = true;
		
		//Handles the case that the current Layer has a continuous key press action.
		if (keyDownAction != null) {
			keyDownAction.run(this, key);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.noteKeyDown(key);
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
	public void noteKeyRelease(final Key key) {
		
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
	public void noteKeyTyping(final Key key) {
		
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
	public void noteLeftMouseButtonClick() {
				
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
	public void noteLeftMouseButtonPress() {
		
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
	public void noteLeftMouseButtonRelease() {
		
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
	public void noteMouseMove(final int cursorXPosition, final int cursorYPosition) {
		
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
	public void noteMouseWheelClick() {
		
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
	public void noteMouseWheelPress() {
		
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
	public void noteMouseWheelRelease() {
		
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
	public void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {
		
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
	public void noteResize(final int viewAreaWidth, final int viewAreaHeight) {
		
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
	public void noteRightMouseButtonClick() {
		
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
	public void noteRightMouseButtonPress() {
		
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
	public void noteRightMouseButtonRelease() {
		
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
	public void paint(final IPainter painter) {
		
		painter.setOpacityPercentage(getOpacityPercentage());
		
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
	public void recalculate() {
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			recalculateWhenHasRootWidget();
		}
	}
	
	//method
	/**
	 * Removes any background of the current {@link Layer}.
	 */
	public void removeBackground() {
		background.clear();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Removes the current {@link Layer} from its parent {@link GUI}.
	 */
	public void removeSelfFromGUI() {
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
	public void resetElementConfiguration() {
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
	public Layer setBackgroundColor(final Color backgroundColor) {
		
		final var lBackground = new Background();
		lBackground.setColor(backgroundColor);
		
		setBackground(lBackground);
		
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
	public Layer setBackgroundColorGradient(final ColorGradient backgroundColorGradient) {
		
		final var lBackground = new Background();
		lBackground.setColorGradient(backgroundColorGradient);
		
		setBackground(lBackground);
		
		return this;
	}
	
	//method
	/**
	 * Sets the background {@link MutableImage} of the current {@link Layer}.
	 * Removes any former background of the current {@link Layer}.
	 * 
	 * @param backgroundImage
	 * @param imageApplication
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given backgroundColor is null.
	 * @throws ArgumentIsNullException if the given imageApplication is null.
	 */
	public Layer setBackgroundImage(final MutableImage backgroundImage, final ImageApplication imageApplication) {
		
		final var lBackground = new Background();
		lBackground.setImage(backgroundImage, imageApplication);
		
		setBackground(lBackground);
		
		return this;
	}
	
	//method
	/**
	 * Lets the current {@link Layer} be allowed to be configured.
	 * 
	 * @return the current {@link Layer}.
	 */
	public Layer setConfigurationAllowed() {
		
		configurationAllowedFlag.setValue(true);
		
		return this;
	}
	
	//method
	/**
	 * Lets the current {@link Layer} be not allowed to be configured.
	 * 
	 * @return the current {@link Layer}.
	 */
	public Layer setConfigurationNotAllowed() {
		
		configurationAllowedFlag.setValue(false);
		
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
	public Layer setContentPosition(final ExtendedContentPosition contentPosition) {
		
		if (contentPosition == ExtendedContentPosition.FREE) {
			optionalFreeContentPosition.clear();
		}
		
		this.contentPosition.setValue(contentPosition);
		
		return this;
	}
	
	//method
	/**
	 * Sets the continuous key press action of the current {@link Layer}. 
	 * 
	 * @param keyDownAction
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given keyDownAction is null.
	 */
	public Layer setKeyDownAction(final I2ElementTaker<Layer, Key> keyDownAction) {
		
		//Asserts that the given customCursorIcon is not null.
		Validator.assertThat(keyDownAction).thatIsNamed("continuous key press action").isNotNull();
		
		this.keyDownAction = keyDownAction;
		
		return asConcrete();
	}
	
	/**
	 * Sets the free content position of the current {@link Layer}.
	 * 
	 * @param xFreeContentPosition
	 * @param yFreeContentPosition
	 * @return the current {@link Layer}.
	 */
	public Layer setFreeContentPosition(final int xFreeContentPosition, final int yFreeContentPosition) {
		
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
	public Layer setLeftMouseButtonClickAction(final IElementTaker<Layer> leftMouseButtonClickAction) {
		
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
	public Layer setLeftMouseButtonPressAction(final IElementTaker<Layer> leftMouseButtonPressAction) {
		
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
	public Layer setLeftMouseButtonReleaseAction(IElementTaker<Layer> leftMouseButtonReleaseAction) {
		
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
	public Layer setMouseMoveAction(final IElementTaker<Layer> mouseMoveAction) {
		
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
	public Layer setMouseWheelClickAction(IElementTaker<Layer> mouseWheelClickAction) {
		
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
	public Layer setMouseWheelPressAction(IElementTaker<Layer> mouseWheelPressAction) {
		
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
	public Layer setMouseWheelReleaseAction(IElementTaker<Layer> mouseWheelReleaseAction) {
		
		Validator.assertThat(mouseWheelReleaseAction).thatIsNamed("mouse wheel release action").isNotNull();
		
		this.mouseWheelReleaseAction = mouseWheelReleaseAction;
		
		return this;
	}
	
	//method
	/**
	 * Sets the opacity percentage of the current {@link Layer}.
	 * 
	 * @param opacityPercentage
	 * @return the current {@link Layer}.
	 */
	public Layer setOpacityPercentage(final double opacityPercentage) {
		
		Validator.assertThat(opacityPercentage).thatIsNamed("opacity percentage").isBetween(0.0, 1.0);
		
		this.opacityPercentage.setValue(opacityPercentage);
		
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
	public Layer setRightMouseButtonClickAction(IElementTaker<Layer> rightMouseButtonClickAction) {
		
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
	public Layer setRightMouseButtonPressAction(IElementTaker<Layer> rightMouseButtonPressAction) {
		
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
	public Layer setRightMouseButtonReleaseAction(IElementTaker<Layer> rightMouseButtonReleaseAction) {
		
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
	public Layer setRole(final LayerRole role) {
		
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
	public Layer setRootWidget(final Widget<?, ?> rootWidget) {
		
		Validator.assertThat(rootWidget).thatIsNamed("root Widget").isNotNull();
		
		rootWidget.setParent(this);
		this.rootWidget = rootWidget;
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetConfigurableElement() {
		setConfigurationAllowed();
		clear();
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * Sets the {@link GUI} the current {@link Layer} will belong to.
	 * 
	 * @param parentGUI
	 * @throws ArgumentIsNullException if the given parentGUI is null.
	 */
	void setParentGUI(final WidgetGUI<?> parentGUI) {
		
		Validator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		//Asserts that the current Layer does not belong to another GUI.
		if (this.parentGUI != null && this.parentGUI != parentGUI) {
			throw new ArgumentBelongsToParentException(this, this.parentGUI);
		}
		
		//Handles the case that the current Layer has a root Widget.
		if (rootWidget != null) {
			rootWidget.setParent(this);
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
		final var rootBorderWidget = rootWidget.as(BorderWidget.class);
		
		//Sets max width and max height to the root BorderWidget.
		rootBorderWidget.setMaxWidth(viewAreaWidth).setMaxHeight(viewAreaHeight);
		
		//Handles the case that the root BorderWidget has activated automatic size.
		if (rootBorderWidget.hasAutomaticSize()) {
			rootBorderWidget.setProposalWidth(viewAreaWidth).setProposalHeight(viewAreaHeight);
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
		
		//Handles the case that the current Layer has a background Color.
		if (hasBackgroundColor()) {
			painter.setColor(getBackgroundColor());
			painter.paintFilledRectangle(parentGUI.getViewAreaWidth(), parentGUI.getViewAreaHeight());
			
		//Handles the case that the current Layer has a background ColorGradient.
		} else if (hasBackgroundColorGradient()) {
			painter.setColorGradient(getBackgroundColorGradient());
			painter.paintFilledRectangle(parentGUI.getViewAreaWidth(), parentGUI.getViewAreaHeight());
		
		//Handles the case that the current Layer has a background Image.
		} else if (hasBackgroundImage()) {
			painter.paintImage(getBackgroundImage(), parentGUI.getViewAreaWidth(), parentGUI.getViewAreaHeight());
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
					lRootWidget
					.setProposalWidth(parentGUI.getViewAreaWidth())
					.setProposalHeight(parentGUI.getViewAreaHeight());
				}
			}
		}
	}
	
	//method
	private void setBackground(final Background background) {
		this.background.setValue(background);
	}
	
	//method
	private void setConfigurationAllowedFlag(final boolean configurationAllowedFlag) {
		if (!configurationAllowedFlag) {
			setConfigurationNotAllowed();
		} else {
			setConfigurationAllowed();
		}
	}
	
	//method
	private void setFreeContentPosition_(final int xFreeContentPosition, final int yFreeContentPosition) {
		optionalFreeContentPosition.setValue(new Discrete2DPoint(xFreeContentPosition, yFreeContentPosition));
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
				
				final var freeContentPositionValue = this.optionalFreeContentPosition.getValue();	
				
				getRefRootWidget().setPositionOnParent(
					freeContentPositionValue.getX(),
					freeContentPositionValue.getY()
				);
				
				break;
		}
	}
}
