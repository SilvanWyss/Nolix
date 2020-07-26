//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.invalidArgumentException.ArgumentBelongsToUnexchangeableParentException;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotSupportMethodException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.requestAPI.IContainsElementByStringIdRequestable;
import ch.nolix.common.skillAPI.Clearable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.base.Element;
import ch.nolix.element.base.MutableOptionalProperty;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.baseGUI_API.IOccupiableCanvasInputActionManager;
import ch.nolix.element.color.Color;
import ch.nolix.element.color.ColorGradient;
import ch.nolix.element.discreteGeometry.Discrete2DPoint;
import ch.nolix.element.elementAPI.IConfigurableElement;
import ch.nolix.element.elementEnum.ContentPosition;
import ch.nolix.element.elementEnum.DirectionOfRotation;
import ch.nolix.element.elementEnum.ExtendedContentPosition;
import ch.nolix.element.input.IResizableInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link Layer} has:
 * -an optional background {@link Color} or background {@ColorGradient}
 * -an optional root {@link Widget}
 * 
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 1150
 */
public class Layer extends Element<Layer>
implements 
Clearable<Layer>,
IConfigurableElement<Layer>,
IContainsElementByStringIdRequestable,
IOccupiableCanvasInputActionManager<Layer>,
IResizableInputTaker {
	
	//constants
	public static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
	public static final ExtendedContentPosition DEFAULT_CONTENT_POSITION = ExtendedContentPosition.Top;
	public static final Discrete2DPoint DEFAULT_FREE_CONTENT_POSITION = new Discrete2DPoint(1, 1);
	
	//constants
	static final String BACKGROUND_COLOR_GRADIENT_HEADER = "BackgroundColorGradient";
	static final String ROOT_WIDGET_HEADER = "RootWidget";
	static final String FREE_CONTENT_POSITION_HEADER = "FreeContentPosition";
	
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
	/**
	 * The {@link GUI} the current {@link Layer} belongs to.
	 */
	private LayerGUI<?> parentGUI;
	
	//attribute
	private final MutableOptionalProperty<Color> backgroundColor =
	new MutableOptionalProperty<>(
		PascalCaseNameCatalogue.BACKGROUND_COLOR,
		this::setBackgroundColor,
		Color::fromSpecification,
		Color::getSpecification
	);
	
	//attribute
	private final MutableOptionalProperty<ColorGradient> backgroundColorGradient =
	new MutableOptionalProperty<>(
		BACKGROUND_COLOR_GRADIENT_HEADER, 
		this::setBackgroundColorGradient,
		ColorGradient::fromSpecification,
		ColorGradient::getSpecification
	);
	
	//attribute
	private final MutableProperty<ExtendedContentPosition> contentPosition =
	new MutableProperty<>(
		ContentPosition.TYPE_NAME,
		this::setContentPosition,
		ExtendedContentPosition::fromSpecification,
		ExtendedContentPosition::getSpecification
	);
	
	//attribute
	private final MutableProperty<Discrete2DPoint> freeContentPosition =
	new MutableProperty<>(
		FREE_CONTENT_POSITION_HEADER,
		fcp -> setFreeContentPosition_(fcp.getX(), fcp.getY()),
		Discrete2DPoint::fromSpecification,
		Discrete2DPoint::getSpecification
	);
	
	//attributes
	private int cursorXPosition = 0;
	private int cursorYPosition = 0;
	
	//optional attribute
	private Widget<?, ?> rootWidget;
	
	//optional attributes
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
	 * 
	 * @param parentGUI
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
		if (LayerGUI.canCreateWidgetFrom(attribute)) {
			setRootWidget(LayerGUI.createWidgetFrom(attribute));
		}
		else {
			super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Removes the root {@link Widget} of the current {@link Layer}.
	 * 
	 * @return the current {@link Layer}.
	 */
	@Override
	public final Layer clear() {
		
		rootWidget = null;
		
		return this;
	}
	
	//method
	/**
	 * {@inheridDoc}
	 */
	@Override
	public final boolean containsElement(final String id) {
		return getRefWidgets().contains(w -> w.hasId(id));
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
				
		return CursorIcon.Arrow;
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
	 * {@inheritDoc}
	 */
	@Override
	public final String getId() {
		throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.ID);
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
	 * @return the root {@link Widget} of the current {@link Layer}.
	 */
	public final Widget<?, ?> getRefRootWidget() {
		return rootWidget;
	}
	
	//method
	/**
	 * @return the {@link Widget}s of the current {@link Layer} that are supposed to be painted.
	 */
	public final LinkedList<Widget<?, ?>> getRefWidgetsForPainting() {
		
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
	public final LinkedList<Widget<?, ?>> getRefWidgets() {
		
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
	 * {@inheritDoc}
	 */
	@Override
	public final String getToken() {
		throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.TOKEN);
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
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasId() {
		return false;
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
	@Override
	public boolean hasToken() {
		return false;
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
		
		//TODO: Check if current Layer is under cursor.
		return true;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyPress(Key key) {
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
		
		if (rootWidget != null) {
			rootWidget.noteLeftMouseButtonRelease();
		}
		
		if (leftMouseButtonReleaseAction != null) {
			leftMouseButtonReleaseAction.run(this);
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
	public final void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {
		if (rootWidget != null) {
			rootWidget.noteMouseWheelRotationStep(directionOfRotation);
		}		
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteResize(final int viewAreaWidth, final int viewAreaHeight) {}
	
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
		
		//Handles the case that the current GUILayer has a root Widget.
		//For a better performance, this implementation does not use all comfortable methods.
		if (rootWidget != null) {
			rootWidget.paintRecursively(painter);
		}
	}
	
	//method
	/**
	 * Recalculates the current {@link Layer}.
	 */
	public final void recalculate() {
		
		//Handles the case that the current GUILayer has a root Widget.
		//For a better performance, this implementation does not use all comfortable methods.
		if (rootWidget != null) {
			
			//For updating the size of the root widget.
			rootWidget.recalculate();
									
			//Enumerates the content position of the current GUILayer.
			switch (contentPosition.getValue()) {
				case LeftTop:
					
					getRefRootWidget().setPositionOnParent(0, 0);
					
					break;
				case Left:
					
					getRefRootWidget().setPositionOnParent(
						0,
						Calculator.getMax(0, (parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;
				case LeftBottom:
					
					getRefRootWidget().setPositionOnParent(
						0,
						Calculator.getMax(0, parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case Top:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
						0
					);
					
					break;
				case Center:
								
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, (parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
					);
					
					break;
				case Bottom:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, (parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()) / 2),
						Calculator.getMax(0, parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case RightTop:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
						0
					);
					
					break;
				case Right:
				
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, (parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight()) / 2)
					);
				
					break;
				case RightBottom:
					
					getRefRootWidget().setPositionOnParent(
						Calculator.getMax(0, parentGUI.getViewAreaWidth() - getRefRootWidget().getWidth()),
						Calculator.getMax(0, parentGUI.getViewAreaHeight() - getRefRootWidget().getHeight())
					);
					
					break;
				case Free:
					
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
	 * {@inheritDoc}
	 */
	@Override
	public final Layer removeId() {
		return this;
	}
	
	//method
	@Override
	public Layer removeToken() {
		return this;
	}
	
	//method
	/**
	 * Resets the current {@link Layer}.
	 * 
	 * @return the current {@link Layer}.
	 */
	@Override
	public final Layer reset() {
		
		setFreeContentPosition(DEFAULT_FREE_CONTENT_POSITION.getX(), DEFAULT_FREE_CONTENT_POSITION.getY());
		clear();
		resetConfiguration();
		
		return this;
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link Layer}.
	 * 
	 * @return the current {@link Layer}.
	 */
	public final Layer resetConfiguration() {
		
		setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
		setContentPosition(DEFAULT_CONTENT_POSITION);
		
		if (containsAny()) {
			getRefRootWidget().resetConfiguration();
		}
		
		return this;
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
	
	/**
	 * Sets the free content position of the current {@link Layer}.
	 * 
	 * @param x
	 * @param y
	 * @return the current {@link Layer}.
	 */
	public final Layer setFreeContentPosition(final int x, final int y) {
		
		setContentPosition(ExtendedContentPosition.Free);
		setFreeContentPosition_(x, y);
		
		return this;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Layer setId(final String id) {
		throw new ArgumentDoesNotSupportMethodException(this, "setId");
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
	 * Sets the root {@link Widget} of the current {@link Layer}.
	 * 
	 * @param rootWidget
	 * @return the current {@link Layer}.
	 * @throws ArgumentIsNullException if the given rootWidget is null.
	 */
	public final Layer setRootWidget(final Widget<?, ?> rootWidget) {
		
		Validator.assertThat(rootWidget).thatIsNamed("root widget").isNotNull().andReturn();
				
		if (parentGUI != null) {
			rootWidget.setParent(parentGUI);
		}
		this.rootWidget = rootWidget;
		
		return this;
	}
	
	//method
	@Override
	public Layer setToken(String token) {
		throw new ArgumentDoesNotSupportMethodException(this, "setToken");
	}
	
	//method
	/**
	 * Sets the {@link GUI} the current {@link Layer} will belong to.
	 * 
	 * @param parentGUI
	 * @throws ArgumentIsNullException if the given parentGUI is null.
	 */
	final void setParentGUI(final LayerGUI<?> parentGUI) {
		
		Validator.assertThat(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		if (this.parentGUI != null && this.parentGUI != parentGUI) {
			throw new ArgumentBelongsToUnexchangeableParentException(this, this.parentGUI);
		}
		
		if (rootWidget != null) {
			rootWidget.setParent(parentGUI);
		}
		
		this.parentGUI = parentGUI;
	}
	
	//method
	private void setFreeContentPosition_(final int x, final int y) {
		freeContentPosition.setValue(new Discrete2DPoint(x, y));
	}
}
