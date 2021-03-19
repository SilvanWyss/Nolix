//package declaration
package ch.nolix.element.gui.base;

//own imports
import ch.nolix.common.constant.LowerCaseCatalogue;
import ch.nolix.common.container.IContainer;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ClosedArgumentException;
import ch.nolix.common.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.common.functionapi.I2ElementTaker;
import ch.nolix.common.functionapi.IElementTaker;
import ch.nolix.common.rasterapi.TopLeftPositionedRecangular;
import ch.nolix.common.requestapi.EnablingRequestable;
import ch.nolix.common.requestapi.ExpansionRequestable;
import ch.nolix.common.skillapi.Recalculable;
import ch.nolix.element.base.ExchangableExtensionElement;
import ch.nolix.element.configuration.ConfigurableElement;
import ch.nolix.element.elementapi.IConfigurableElement;
import ch.nolix.element.elementenum.RotationDirection;
import ch.nolix.element.gui.baseapi.IFrontEndReader;
import ch.nolix.element.gui.baseapi.IFrontEndWriter;
import ch.nolix.element.gui.baseapi.IInputActionManager;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.input.IInputTaker;
import ch.nolix.element.gui.input.Key;
import ch.nolix.element.gui.inputdeviceapi.IKeyBoard;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * A {@link Widget} is an element on a {@link WidgetGUI}.
 * A {@link Widget} determines its width and height.
 * A {@link Widget} is a {@link ConfigurableElement}.
 * 
 * A class always defines (not just declares!) the required methods for its defined attributes.
 * A {@link Widget} makes the following exceptions for this rule.
 * -A {@link Widget} ensures that its shown child {@link Widget} are painted.
 * -A {@link Widget} ensures that all input events are leaded to its shown child {@link Widget}.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 2160
 * @param <W> is the type of a {@link Widget}.
 * @param <WL> is the type of the {@link WidgetLook} of a {@link Widget}.
 */
public abstract class Widget<W extends Widget<W, WL>, WL extends WidgetLook<WL>> extends ConfigurableElement<W>
implements
EnablingRequestable,
ExpansionRequestable,
IInputActionManager<W>,
IInputTaker, Recalculable,
TopLeftPositionedRecangular {
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.ARROW;
	
	//constants
	private static final String GREY_OUT_WHEN_DISABLED_HEADER = "GreyOutWhenDisabled";
	private static final String ENABLED_HEADER = "Enabled";
	private static final String EXPANDED_HEADER = "Expanded";
	private static final String FOCUSED_HEADER = "Focused";
	private static final String HOVERED_HEADER = "Hovered";
	
	//constants
	private static final String BASE_PREFIX = "Base";
	private static final String HOVER_PREFIX = "Hover";
	private static final String FOCUS_PREFIX = "Focus";
	
	//attributes	
	private CursorIcon customCursorIcon = DEFAULT_CURSOR_ICON;
	private boolean greysOutWhenDisabled;
	
	//attributes
	private boolean enabled = true;
	private boolean expanded = true;
	private boolean focused;
	private boolean hovered;
	
	//attribute
	private final ExchangableExtensionElement<WL> baseLook =
	new ExchangableExtensionElement<>(BASE_PREFIX, createLook());
	
	//attribute
	private final ExchangableExtensionElement<WL> hoverLook =
	new ExchangableExtensionElement<>(HOVER_PREFIX, createLook());
	
	//attribute
	private final ExchangableExtensionElement<WL> focusLook =
	new ExchangableExtensionElement<>(FOCUS_PREFIX, createLook());
	
	//attributes
	private int xPositionOnContentAreaOfParent;
	private int yPositionOnContentAreaOfParent;
	
	//attributes
	private int width;
	private int height;
	
	//attributes
	private int cursorXPosition;
	private int cursorYPosition;
	
	//optional attribute
	private WidgetParent parent;
	
	//optional attributes
	private I2ElementTaker<W, Key> continuousKeyPressAction;
	private IElementTaker<W> mouseMoveAction;
	private IElementTaker<W> leftMouseButtonClickAction;
	private IElementTaker<W> leftMouseButtonPressAction;
	private IElementTaker<W> leftMouseButtonReleaseAction;
	private IElementTaker<W> rightMouseButtonClickAction;
	private IElementTaker<W> rightMouseButtonPressAction;
	private IElementTaker<W> rightMouseButtonReleaseAction;
	private IElementTaker<W> mouseWheelClickAction;
	private IElementTaker<W> mouseWheelPressAction;
	private IElementTaker<W> mouseWheelReleaseAction;
	
	//constructor
	/**
	 * Creates a new {@link Widget}.
	 */
	public Widget() {
		createAndConnectLooks();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link Widget}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case CursorIcon.TYPE_NAME:
				setCustomCursorIcon(CursorIcon.fromSpecification(attribute));
				break;
			case GREY_OUT_WHEN_DISABLED_HEADER:
				setGreyOutState(attribute.getOneAttributeAsBoolean());
				break;
			case ENABLED_HEADER:
				setEnablingState(attribute.getOneAttributeAsBoolean());
				break;
			case EXPANDED_HEADER:
				setExpansionState(attribute.getOneAttributeAsBoolean());
				break;
			case FOCUSED_HEADER:
				setFocusState(attribute.getOneAttributeAsBoolean());
				break;
			case HOVERED_HEADER:
				setHoverState(attribute.getOneAttributeAsBoolean());
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Applies the given base look mutator to the base look of the current {@link Widget}.
	 * 
	 * @param baseLookMutator
	 * @return the current {@link Widget}.
	 */
	public final W applyOnBaseLook(final IElementTaker<WL> baseLookMutator) {
		
		baseLookMutator.run(getRefBaseLook());
		
		return asConcrete();
	}
	
	//method
	/**
	 * Applies the given focus look mutator to the focus look of the current {@link Widget}.
	 * 
	 * @param focusLookMutator
	 * @return the current {@link Widget}.
	 */
	public final W applyOnFocusLook(final IElementTaker<WL> focusLookMutator) {
		
		focusLookMutator.run(getRefFocusLook());
		
		return asConcrete();
	}
	
	//method
	/**
	 * Applies the given hover look mutator to the hover look of the current {@link Widget}.
	 * 
	 * @param hoverLookMutator
	 * @return the current {@link Widget}.
	 */
	public final W applyOnHoverLook(final IElementTaker<WL> hoverLookMutator) {
		
		hoverLookMutator.run(getRefHoverLook());
		
		return asConcrete();
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} belongs to a {@link WidgetGUI}, directly or indirectly.
	 */
	public final boolean belongsToGUI() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (parent != null && parent.belongsToGUI());
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} belongs directly to a {@link WidgetGUI} or another {@link Widget}.
	 */
	public final boolean belongsToParent() {
		return (parent != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} belongs directly to another {@link Widget}.
	 */
	public final boolean belongsToWidget() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (parent != null && parent.isWidget());
	}
	
	//method
	/**
	 * @param xPositionOnGUI
	 * @param yPositionOnGUI
	 * @return true if the current {@link Widget} contains the point, that has:
	 * -the given x-position on the {@link WidgetGUI} the current {@link Widget} belongs to
	 * -the given y-position on the {@link WidgetGUI} the current {@link Widget} belongs to
	 */
	public final boolean containsPointOnGUI(final int xPositionOnGUI, final int yPositionOnGUI) {
		
		//For a better performance, this implementation does the cheap comparisons at first.
		final var thisXPositionOnGUI = getXPositionOnGUI();		
		if (xPositionOnGUI <= thisXPositionOnGUI || xPositionOnGUI > thisXPositionOnGUI + getWidth()) {
			return false;
		}
		
		final var thisYPositionOnGUI = getYPositionOnGUI();
		return (yPositionOnGUI > thisYPositionOnGUI && yPositionOnGUI <= thisYPositionOnGUI + getHeight());
	}
	
	//method
	/**
	 * @param xPositionOnParent
	 * @param yPositionOnParent
	 * @return true if the current {@link Widget} contains the point, that has:
	 * -the given x-position on the parent the current {@link Widget} belongs to
	 * -the given y-position on the parent the current {@link Widget} belongs to
	 */
	public final boolean containsPointOnParent(final int xPositionOnParent, final int yPositionOnParent) {
		
		//For a better performance, this implementation does the cheap comparisons at first.
		return
		xPositionOnParent >= this.xPositionOnContentAreaOfParent
		&& yPositionOnParent >= this.yPositionOnContentAreaOfParent
		&& xPositionOnParent < this.xPositionOnContentAreaOfParent + getWidth()
		&& yPositionOnParent < this.yPositionOnContentAreaOfParent + getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillUpAttributesInto(final LinkedList<Node> list) {
		
		//Calls method of the base class.
		super.fillUpAttributesInto(list);
		
		list
		.addAtEnd(getCustomCursorIcon().getSpecification())
		.addAtEnd(Node.withHeaderAndAttribute(GREY_OUT_WHEN_DISABLED_HEADER, greysOutWhenDisabled()))
		.addAtEnd(Node.withHeaderAndAttribute(ENABLED_HEADER, isEnabled()))
		.addAtEnd(Node.withHeaderAndAttribute(EXPANDED_HEADER, isExpanded()))
		.addAtEnd(Node.withHeaderAndAttribute(FOCUSED_HEADER, isFocused()))
		.addAtEnd(Node.withHeaderAndAttribute(HOVERED_HEADER, isHovered()));
	}
	
	//method
	/**
	 * The free view area of a {@link Widget} is the part of the view area of the {@link Widget}
	 * where does not lie a child {@link Widget}, that is for painting, of the {@link Widget}.
	 * 
	 * @return true if the free view area of the curent {@link Widget} is under the cursor.
	 */
	public final boolean freeViewAreaIsUnderCursor() {
		return (isUnderCursor() && getRefPaintableWidgets().contains(Widget::isUnderCursor));
	}
	
	//method
	/**
	 * @return the {@link IFrontEndReader} of the parent {@link GUI} of the current {@link Widget}.
	 * @throws ArgumentDoesNotBelongToParentException if the current {@link Widget} does not belong to a {@link GUI}.
	 */
	public IFrontEndReader fromFrontEnd() {
		return getParentGUI().fromFrontEnd();
	}
	
	//method
	/** 
	 * @return the child {@link Widget}s of the current {@link Widget}.
	 */
	public final LinkedList<Widget<?, ?>> getChildWidgets() {
		
		final var childWidgets = new LinkedList<Widget<?, ?>>();
		fillUpChildWidgets(childWidgets);
		
		return childWidgets;
	}
	
	//method
	/** 
	 * @return the child {@link Widget}s of the current {@link Widget} recursively.
	 */
	public final LinkedList<Widget<?, ?>> getChildWidgetsRecursively() {
		
		final var widgets = new LinkedList<Widget<?, ?>>();
		fillUpChildWidgetsRecursively(widgets);
		
		return widgets;
	}
	
	//method declaration
	/**
	 * @return the {@link CursorIcon} of the current {@link Widget}.
	 */
	public CursorIcon getCursorIcon() {
		
		final var widgetContainer = getRefWidgetUnderCursor();
		if (widgetContainer.containsAny()) {
			return widgetContainer.getRefElement().getCursorIcon();
		}
				
		return getCustomCursorIcon();
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link Widget}.
	 */
	public final int getCursorXPosition() {
		return cursorXPosition;
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link Widget}.
	 */
	public final int getCursorYPosition() {
		return cursorYPosition;
	}
	
	//method
	/**
	 * @return the custom {@link CursorIcon} of the current {@link Widget}.
	 */
	public final CursorIcon getCustomCursorIcon() {
		return customCursorIcon;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getHeight() {
		return height;
	}
	
	//method
	/**
	 * @return the index of the current {@link Widget} on the {@link WidgetGUI} the current {@link Widget} belongs to.
	 * @throws InvalidArgumentException if the current {@link Widget} does not belong to a {@link WidgetGUI}.
	 */
	public final int getIndexOnGUI() {
		return getParentGUI().getRefWidgets().getIndexOf(this);
	}
	
	//method
	/**
	 * @return the parent the current {@link Widget} belongs to.
	 * @throws ArgumentDoesNotBelongToParentException if the current {@link Widget} does not belong to a parent.
	 */
	public final WidgetParent getParent() {
		
		//Asserts that the current Widget belongs to a parent.
		if (parent == null) {
			throw new ArgumentDoesNotBelongToParentException(this);
		}
		
		return parent;
	}
	
	//method
	/**
	 * @return the {@link GUI} the current {@link Widget} belongs to, directly or indirectly.
	 * @throws ArgumentDoesNotBelongToParentException
	 * if the current {@link Widget} does not belong to a {@link GUI}.
	 */
	public final WidgetGUI<?> getParentGUI() {
		
		//Asserts that the current Widget belongs to a GUI.
		if (parent == null) {
			throw new ArgumentDoesNotBelongToParentException(this, GUI.class);
		}
		
		return parent.getRefGUI();
	}
	
	//method
	/**
	 * @return the {@link Layer} the current {@link Widget} belongs to.
	 * @throws ArgumentDoesNotBelongToParentException if the current {@link Widget} does not belong to a {@link Layer}.
	 */
	public final Layer getParentLayer() {
		return getParent().getRefLayer();
	}
	
	//method
	/**
	 * @return the {@link Widget} the current {@link Widget} belongs to.
	 * @throws ArgumentDoesNotBelongToParentException
	 * if the current {@link Widget} does not belong to a {@link Widget}.
	 */
	public final Widget<?, ?> getParentWidget() {
		
		assertBelongsToWidget();
				
		return getParent().getRefWidget();
	}
	
	//method
	/**
	 * @return the base look of the current {@link Widget}.
	 */
	public final WL getRefBaseLook() {
		return baseLook.getExtensionElement();
	}
	
	//method
	/**
	 * @return the focus look of the current {@link Widget}.
	 */
	public final WL getRefFocusLook() {
		return focusLook.getExtensionElement();
	}
	
	//method
	/**
	 * @return the hover look of the current {@link Widget}.
	 */
	public final WL getRefHoverLook() {
		return hoverLook.getExtensionElement();
	}
	
	//method
	/**
	 * @return the {@link IKeyBoard} of the {@link GUI} the current {@link Widget} belongs to.
	 * @throws ArgumentDoesNotBelongToParentException if the current {@link Widget} does not belong to a {@link GUI}.
	 */
	public final IKeyBoard getRefKeyBoard() {
		return getParentGUI().getRefKeyBoard();
	}
	
	//method
	/**
	 * The current look of a {@link Widget} depends on its state.
	 * -not hovered, not focused -> base look
	 * -hovered, not focused -> hover look
	 * -hovered, focused -> hover look
	 * -focused, not hovered -> focus look
	 * 
	 * @return the current look of the current {@link Widget}.
	 */
	public final WL getRefLook() {
		
		if (isHovered()) {
			return getRefHoverLook();
		}
		
		if (isFocused()) {
			return getRefFocusLook();
		}
		
		return getRefBaseLook();
	}
	
	//method
	/** 
	 * @return the paintable {@link Widget}s of the current {@link Widget}.
	 */
	public final LinkedList<Widget<?, ?>> getRefPaintableWidgets() {
		
		final var widgetsForPainting = new LinkedList<Widget<?, ?>>();
		fillUpShownWidgets(widgetsForPainting);
		
		return widgetsForPainting;
	}
	
	//method
	/** 
	 * @return the paintable {@link Widget}s of the current {@link Widget} recursively.
	 */
	public final LinkedList<Widget<?, ?>> getRefPaintableWidgetsRecursively() {
		
		final var widgetsForPainting = new LinkedList<Widget<?, ?>>();
		fillUpWidgetsForPaintingRecursively(widgetsForPainting);
		
		return widgetsForPainting;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final IContainer<IConfigurableElement<?>> getSubConfigurables() {		
		return (LinkedList)getChildWidgets();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getWidth() {
		return width;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getXPosition() {
		return xPositionOnContentAreaOfParent;
	}
	
	//method
	/**
	 * @return the x-position of the current {@link Widget} on the parent of the current {@link Widget}.
	 */
	public final int getXPositionOnGUI() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		
		//Handles the case that the current Widget does not belong to a parent.
		if (parent == null) {
			return xPositionOnContentAreaOfParent;
		}
		
		//Handles the case that the parent of the current Widget is a Layer.
		if (parent.isLayer()) {
			return parent.getXPositionOnGUI() + xPositionOnContentAreaOfParent;
		}
		
		//Handles the case that the parent of the current Widget is a Widget.
		return
		parent.getXPositionOnGUI()
		+ parent.getRefWidget().getContentAreaXPosition()
		+ xPositionOnContentAreaOfParent;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getYPosition() {
		return yPositionOnContentAreaOfParent;
	}
	
	//method
	//For a better performance, this implementation does not use all comfortable methods.
	/**
	 * @return the y-position of the current {@link Widget} on the parent of the current {@link Widget}.
	 */
	public final int getYPositionOnGUI() {
				
		//Handles the case that the current Widget does not belong to a parent.
		if (parent == null) {
			return yPositionOnContentAreaOfParent;
		}
		
		//Handles the case that the parent of the current Widget is a Layer.
		if (parent.isLayer()) {
			return parent.getYPositionOnGUI() + yPositionOnContentAreaOfParent;
		}
		
		//Handles the case that the parent of the current Widget is a Widget.
		return
		parent.getYPositionOnGUI()
		+ parent.getRefWidget().getContentAreaYPosition()
		+ yPositionOnContentAreaOfParent;
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} grays out when it is disabled.
	 */
	public final boolean greysOutWhenDisabled() {
		return greysOutWhenDisabled;
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a left mouse button click action.
	 */
	public final boolean hasLeftMouseButtonClickAction() {
		return (leftMouseButtonClickAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a left mouse button press action.
	 */
	public final boolean hasLeftMouseButtonPressAction() {
		return (leftMouseButtonPressAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a left mouse button release action.
	 */
	public final boolean hasLeftMouseButtonReleaseAction() {
		return (leftMouseButtonReleaseAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a mouse move action.
	 */
	public final boolean hasMouseMoveAction() {
		return (mouseMoveAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a mouse wheel click action.
	 */
	public final boolean hasMouseWheelClickAction() {
		return (mouseWheelClickAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a mouse wheel press action.
	 */
	public final boolean hasMouseWheelPressAction() {
		return (mouseWheelPressAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a mouse wheel release action.
	 */
	public final boolean hasMouseWheelReleaseAction() {
		return (mouseWheelReleaseAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a right mouse button click action.
	 */
	public final boolean hasRightMouseButtonClickAction() {
		return (rightMouseButtonClickAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a right mouse button press action.
	 */
	public final boolean hasRightMouseButtonPressAction() {
		return (rightMouseButtonPressAction != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a right mouse button release action.
	 */
	public final boolean hasRightMouseButtonReleaseAction() {
		return (rightMouseButtonReleaseAction != null);
	}
		
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isEnabled() {
		return enabled;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isExpanded() {
		return expanded;
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is focused.
	 */
	public final boolean isFocused() {
		return focused;
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is hovered.
	 */
	public final boolean isHovered() {
		return hovered;
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is a root {@link Widget} on a {@link Layer}.
	 */
	public final boolean isRootWidgetOnLayer() {
		return (parent != null && parent.isLayer());
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is under the cursor.
	 */
	public final boolean isUnderCursor() {
		return containsPointRelatively(cursorXPosition, cursorYPosition);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyPress(final Key key) {
		if (isEnabled()) {
			
			noteContinuousKeyPressOnSelfWhenEnabled_(key);
			
			if (isFocused()) {
				noteKeyPressOnSelfWhenFocused(key);
			}	
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(w -> w.noteKeyPress(key));
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyRelease(final Key key) {
		if (isEnabled()) {
			
			if (isFocused()) {
				noteKeyReleaseOnSelfWhenFocused(key);
			}
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(w -> w.noteKeyRelease(key));
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteKeyTyping(final Key key) {
		if (isEnabled()) {
			
			if (isFocused()) {
				noteKeyTypingOnSelfWhenFocused(key);
			}
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(w -> w.noteKeyTyping(key));
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonClick() {
		if (isEnabled()) {
			
			noteLeftMouseButtonClickOnSelfWhenEnabled_();
			noteLeftMouseButtonClickOnSelfWhenEnabled();
									
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteLeftMouseButtonClick);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonPress() {
		if (isEnabled()) {
			
			noteLeftMouseButtonPressOnSelfWhenEnabled_();
			noteLeftMouseButtonPressOnSelfWhenEnabled();
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteLeftMouseButtonPress);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteLeftMouseButtonRelease() {
		if (isEnabled()) {
			
			noteLeftMouseButtonReleaseOnSelfWhenEnabled_();
			noteLeftMouseButtonReleaseOnSelfWhenEnabled();
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteLeftMouseButtonRelease);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseMove(final int cursorXPosition, final int cursorYPosition) {
		setCursorPosition(cursorXPosition, cursorYPosition);
		noteMouseMove();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelClick() {
		if (isEnabled()) {
			
			noteMouseWheelClickOnSelfWhenEnabled();
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteMouseWheelClick);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelPress() {
		if (isEnabled()) {
			
			noteMouseWheelPressOnSelfWhenEnabled();
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteMouseWheelPress);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteMouseWheelRelease() {
		if (isEnabled()) {
			
			noteMouseWheelReleaseOnSelfWhenEnabled();
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteMouseWheelRelease);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteMouseWheelRotationStep(final RotationDirection rotationDirection) {
		if (isEnabled()) {
			
			if (isFocused()) {
				noteMouseWheelRotationStepOnSelfWhenFocused(rotationDirection);
			}
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(w -> w.noteMouseWheelRotationStep(rotationDirection));
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonClick() {
		if (isEnabled()) {
			
			noteRightMouseButtonClickOnSelfWhenEnabled_();
			noteRightMouseButtonClickOnSelfWhenEnabled();
		
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteRightMouseButtonClick);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonPress() {
		if (isEnabled()) {
			
			noteRightMouseButtonPressOnSelfWhenEnabled_();
			noteRightMouseButtonPressOnSelfWhenEnabled();
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteRightMouseButtonPress);
			}
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void noteRightMouseButtonRelease() {
		if (isEnabled()) {
			
			noteRightMouseButtonReleaseOnSelfWhenEnabled_();
			noteRightMouseButtonReleaseOnSelfWhenEnabled();
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteRightMouseButtonRelease);
			}
		}
	}
	
	//method
	/**
	 * @return the {@link IFrontEndWriter} of the parent {@link GUI} of the current {@link Widget}.
	 * @throws ArgumentDoesNotBelongToParentException if the current {@link Widget} does not belong to a {@link GUI}.
	 */
	public IFrontEndWriter onFrontEnd() {
		return getParentGUI().onFrontEnd();
	}
	
	//method
	/**
	 * Paints the current {@link Widget} recursively using the position on its parent and the given painter.
	 * This method ensures that the given painter has the same position at the end of the painting as the beginning.
	 * 
	 * @param painter
	 */
	public final void paintRecursively(final IPainter painter) {
		paintRecursivelyUsingPositionedPainter(painter.createPainter(getXPosition(), getYPosition()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void recalculate() {
		
		getRefPaintableWidgets().forEach(Widget::recalculate);
		
		recalculateSelf();
	}
	
	//method
	/**
	 * Avoids that the current {@link Widget} grays out when it is disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeGreyOutWhenDisabled() {
		
		greysOutWhenDisabled = false;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the left mouse button click action of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeLeftMouseButtonClickAction() {
		
		leftMouseButtonClickAction = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the left mouse button press action of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeLeftMouseButtonPressAction() {
		
		leftMouseButtonPressAction = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the left mouse button release action of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeLeftMouseButtonReleaseAction() {
		
		leftMouseButtonReleaseAction = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right mouse button click action of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeRightMouseButtonClickAction() {
		
		rightMouseButtonClickAction = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right mouse button press action of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeRightMouseButtonPressAction() {
		
		rightMouseButtonPressAction = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right mouse button release action of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeRightMouseButtonReleaseAction() {
		
		rightMouseButtonReleaseAction = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void resetConfigurationOnSelf() {
		
		setCustomCursorIcon(DEFAULT_CURSOR_ICON);
		setGreyOutWhenDisabled();
		
		createAndConnectLooks();
		
		getRefBaseLook()
		.setTextSize(ValueCatalogue.MEDIUM_TEXT_SIZE)
		.setTextColor(Color.BLACK);
				
		resetWidgetConfigurationOnSelf();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} collapsed.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setCollapsed() {
		
		expanded = false;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the continuous key press action of the current {@link Widget}. 
	 * 
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given continuousKeyPressAction is null.
	 */
	@Override
	public final W setContinuousKeyPressAction(final I2ElementTaker<W, Key> continuousKeyPressAction) {
		
		//Asserts that the given customCursorIcon is not null.
		Validator.assertThat(continuousKeyPressAction).thatIsNamed("continuous key press action").isNotNull();
		
		this.continuousKeyPressAction = continuousKeyPressAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the custom {@link CursorIcon} of the current {@link Widget}.
	 * 
	 * @param customCursorIcon
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given customCursorIcon is null.
	 */
	public final W setCustomCursorIcon(final CursorIcon customCursorIcon) {
		
		//Asserts that the given customCursorIcon is not null.
		Validator.assertThat(customCursorIcon).thatIsNamed("custom cursor icon").isNotNull();
		
		//Sets the custom CursorIcon of the current Widget.
		this.customCursorIcon = customCursorIcon;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setDisabled() {
		
		enabled = false;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} enabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setEnabled() {
		
		enabled = true;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} expanded.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setExpanded() {
		
		expanded = true;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} focused.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setFocused() {
		
		focused = true;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link Widget} grey out when it is disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setGreyOutWhenDisabled() {
		
		greysOutWhenDisabled = true;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} hovered.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setHovered() {
		
		hovered = true;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the left mouse button click action of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonClickAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonClickAction is null.
	 */
	@Override
	public final W setLeftMouseButtonClickAction(final IElementTaker<W> leftMouseButtonClickAction) {
		
		Validator.assertThat(leftMouseButtonClickAction).thatIsNamed("left mouse button click action").isNotNull();
		
		this.leftMouseButtonClickAction = leftMouseButtonClickAction;
		
		return asConcrete();
	}

	//method
	/**
	 * Sets the left mouse button press action of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonPressAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonPressAction is null.
	 */
	public final W setLeftMouseButtonPressAction(final IElementTaker<W> leftMouseButtonPressAction) {
		
		Validator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button press action").isNotNull();
		
		this.leftMouseButtonPressAction = leftMouseButtonPressAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the left mouse button release action of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonReleaseAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonReleaseAction is null.
	 */
	@Override
	public final W setLeftMouseButtonReleaseAction(IElementTaker<W> leftMouseButtonReleaseAction) {
		
		Validator.assertThat(leftMouseButtonReleaseAction).thatIsNamed("left mouse button release action").isNotNull();
		
		this.leftMouseButtonReleaseAction = leftMouseButtonReleaseAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the mouse move action of the current {@link Widget}.
	 * 
	 * @param mouseMoveAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonPressAction is null.
	 */
	@Override
	public final W setMouseMoveAction(final IElementTaker<W> mouseMoveAction) {
		
		Validator.assertThat(mouseMoveAction).thatIsNamed("mouse move action").isNotNull();
		
		this.mouseMoveAction = mouseMoveAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the mouse wheel click action of the current {@link Widget}.
	 * 
	 * @param mouseWheelClickAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given mouseWheelClickAction is null.
	 */
	@Override
	public final W setMouseWheelClickAction(IElementTaker<W> mouseWheelClickAction) {
		
		Validator.assertThat(mouseWheelClickAction).thatIsNamed("mouse wheel click action").isNotNull();
		
		this.mouseWheelClickAction = mouseWheelClickAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the mouse wheel press action of the current {@link Widget}.
	 * 
	 * @param mouseWheelPressAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given mouseWheelPressAction is null.
	 */
	@Override
	public final W setMouseWheelPressAction(IElementTaker<W> mouseWheelPressAction) {
		
		Validator.assertThat(mouseWheelPressAction).thatIsNamed("mouse wheel press action").isNotNull();
		
		this.mouseWheelPressAction = mouseWheelPressAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the mouse wheel release action of the current {@link Widget}.
	 * 
	 * @param mouseWheelReleaseAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given mouseWheelReleaseAction is null.
	 */
	@Override
	public final W setMouseWheelReleaseAction(IElementTaker<W> mouseWheelReleaseAction) {
		
		Validator.assertThat(mouseWheelReleaseAction).thatIsNamed("mouse wheel release action").isNotNull();
		
		this.mouseWheelReleaseAction = mouseWheelReleaseAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the {@link Layer} the current {@link Widget} will belong.
	 * 
	 * @param parentLayer
	 * @throws ArgumentIsNullException if the given parentLayer is null.
	 * @throws InvalidArgumentException if the current {@link Widget} belongs already to a parent.
	 */
	public final void setParent(final Layer parentLayer) {
		setParent(new WidgetParent(parentLayer, this));
	}
	
	//method
	/**
	 * Sets the position of the current {@link Widget} on its parent.
	 * 
	 * @param xPositionOnParent
	 * @param yPositionOnParent
	 */
	public final void setPositionOnParent(final int xPositionOnParent,	final int yPositionOnParent) {				
		this.xPositionOnContentAreaOfParent = xPositionOnParent;
		this.yPositionOnContentAreaOfParent = yPositionOnParent;
	}
	
	//method
	/**
	 * Sets the right mouse button click action of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonClickAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonClickAction is null.
	 */
	@Override
	public final W setRightMouseButtonClickAction(IElementTaker<W> rightMouseButtonClickAction) {
		
		Validator.assertThat(rightMouseButtonClickAction).thatIsNamed("right mouse button click action").isNotNull();
		
		this.rightMouseButtonClickAction = rightMouseButtonClickAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the right mouse button press action of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonPressAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonPressAction is null.
	 */
	@Override
	public final W setRightMouseButtonPressAction(IElementTaker<W> rightMouseButtonPressAction) {
		
		Validator.assertThat(rightMouseButtonPressAction).thatIsNamed("right mouse button press action").isNotNull();
		
		this.rightMouseButtonPressAction = rightMouseButtonPressAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the right mouse button release action of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonReleaseAction
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonReleaseAction is null.
	 */
	@Override
	public final W setRightMouseButtonReleaseAction(IElementTaker<W> rightMouseButtonReleaseAction) {
		
		Validator.assertThat(rightMouseButtonReleaseAction).thatIsNamed("right mouse button release action").isNotNull();
		
		this.rightMouseButtonReleaseAction = rightMouseButtonReleaseAction;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} unhovered.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setUnhovered() {
		
		hovered = false;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} unfocused.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setUnfocused() {
		
		focused = false;
		
		return asConcrete();
	}
	
	//method declaration
	/**
	 * @return true if the view are of the current {@link Widget} is under the cursor.
	 */
	public abstract boolean showAreaIsUnderCursor();
	
	//method
	/**
	 * Adds the given childWidget to the current {@link Widget}.
	 * 
	 * @param childWidget
	 * @throws ArgumentIsNullException if the given childWidget is null.
	 */
	protected final void addChildWidget(final Widget<?, ?> childWidget) {
		
		//Asserts that the given childWidget is not null.
		Validator.assertThat(childWidget).thatIsNamed("child Widget").isNotNull();
		
		childWidget.setParent(this);
	}
	
	//method declaration
	/**
	 * @return a new look for the current {@link Widget}.
	 */
	protected abstract WL createLook();
	
	//method declaration
	/**
	 * Fills up the child {@link Widget}s of the current {@link Widget} into the given list.
	 * 
	 * For a better performance, a {@link Widget} fills up its child {@link Widget}s into a given {@link LinkedList}
	 * instead of creating a new {@link LinkedList}.
	 * 
	 * @param list
	 */
	protected abstract void fillUpChildWidgets(LinkedList<Widget<?, ?>> list);
	
	//method declaration
	/**
	 * Fills up the shown {@link Widget}s of the current {@link Widget} into the given list.
	 * 
	 * For a better performance, a {@link Widget} fills up its shown {@link Widget}s into a given {@link LinkedList}
	 * instead of creating a new {@link LinkedList}.
	 * 
	 * @param list
	 */
	protected abstract void fillUpShownWidgets(LinkedList<Widget<?, ?>> list);
	
	//method
	/**
	 * @return the x-position of the content area of the current {@link Widget} on the current {@link Widget}.
	 */
	protected int getContentAreaXPosition() {
		return 0;
	}
	
	//method
	/**
	 * @return the y-position of the content area of the current {@link Widget} on the current {@link Widget}.
	 */
	protected int getContentAreaYPosition() {
		return 0;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the content area of the current {@link Widget}.
	 */
	protected final int getCursorXPositionOnContentArea() {
		return (getCursorXPosition() - getContentAreaXPosition());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the content area of the current {@link Widget}.
	 */
	protected final int getCursorYPositionOnContentArea() {
		return (getCursorYPosition() - getContentAreaYPosition());
	}
	
	//method declaration
	/**
	 * @return the height of the current {@link Widget} when it is expanded.
	 */
	protected abstract int getHeightWhenExpanded();
	
	//method declaration
	/**
	 * @return the width of the current {@link Widget} when it is expanded.
	 */
	protected abstract int getWidthWhenExpanded();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a key press on itself
	 * for the case when the current {@link Widget} is focused.
	 * 
	 * @param key
	 */
	protected abstract void noteKeyPressOnSelfWhenFocused(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a key release on itself
	 * for the case when the current {@link Widget} is focused.
	 * 
	 * @param key
	 */
	protected abstract void noteKeyReleaseOnSelfWhenFocused(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a key typing on itself
	 * for the case when the current {@link Widget} is focused.
	 * 
	 * @param key
	 */
	protected abstract void noteKeyTypingOnSelfWhenFocused(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a left mouse button click on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonClickOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a left mouse button press on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonPressOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a left mouse button release on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonReleaseOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a mouse move on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteMouseMoveOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a mouse wheel click on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteMouseWheelClickOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a mouse wheel press on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteMouseWheelPressOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a mouse wheel release on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteMouseWheelReleaseOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a mouse wheel rotation step
	 * for the case when the current {@link Widget} is focused.
	 * 
	 * @param rotationDirection
	 */
	protected abstract void noteMouseWheelRotationStepOnSelfWhenFocused(final RotationDirection rotationDirection);
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a right mouse button click on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteRightMouseButtonClickOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a right mouse button press on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteRightMouseButtonPressOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a right mouse button release on itself
	 * for the case when the current {@link Widget} is enabled.
	 */
	protected abstract void noteRightMouseButtonReleaseOnSelfWhenEnabled();
	
	//method declaration
	/**
	 * Paints the current {@link Widget}
	 * using the given painter, that is supposed to be positioned, and look.
	 * 
	 * @param painter
	 * @param look
	 */
	protected abstract void paint(final IPainter painter, final WL look);
	
	//method
	/**
	 * @return true if the current {@link Widget} paints its paintable {@link Widget}s a priori.
	 */
	protected boolean paintsPaintableWidgetAPriori() {
		return true;
	}
	
	//method
	/**
	 * Recalculates the current {@link Widget} itself.
	 */
	protected final void recalculateSelf() {
		
		width = calculatedWidth();
		height = calculatedHeight();
		
		recalculateWidgetSelf();
	}
	
	//method
	/**
	 * Recalculates the current {@link Widget} itself.
	 */
	protected abstract void recalculateWidgetSelf();
	
	//method
	/**
	 * @return true if the current {@link Widget} redirects inputs to its shown {@link Widget}s.
	 */
	protected final boolean redirectsInputsToShownWidgets() {
		return (isEnabled() && showAreaIsUnderCursor());
	}
	
	//method declaration
	/**
	 * Resets the configuration of the current {@link Widget} on itself.
	 */
	protected abstract void resetWidgetConfigurationOnSelf();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void resetConfigurableElement() {
		
		setEnabled();
		setExpanded();
		setUnfocused();
		setUnhovered();
		
		removeLeftMouseButtonClickAction();
		removeLeftMouseButtonPressAction();
		removeLeftMouseButtonReleaseAction();
		removeRightMouseButtonClickAction();
		removeRightMouseButtonPressAction();
		removeRightMouseButtonReleaseAction();
		
		resetWidget();
	}
	
	//method declaration
	/**
	 * Resets the current {@link Widget}.
	 */
	protected abstract void resetWidget();
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link Widget} belongs to a {@link WidgetGUI} that is closed.
	 */
	protected final void supposeGUIIsAlive() {
		if (belongsToGUI() && parent.GUIIsClosed()) {
			throw new ClosedArgumentException(getParentGUI());
		}
	}
	
	//method
	/**
	 * @throws ArgumentDoesNotBelongToParentException
	 * if the current {@link Widget} does not belong to a {@link Widget}.
	 */
	private void assertBelongsToWidget() {
		if (!belongsToWidget()) {
			throw new ArgumentDoesNotBelongToParentException(this, Widget.class);
		}
	}
	
	//method
	/**
	 * @return the newly calculated height of the current {@link Widget}.
	 */
	private int calculatedHeight() {
		
		//Handles the case that the current Widget is collapsed.
		if (isCollapsed()) {
			return 0;
		}
		
		//Handles the case that the current Widget is expanded.
		return getHeightWhenExpanded();
	}
	
	//method
	/**
	 * @return the newly calculated width of the current {@link Widget}.
	 */
	private int calculatedWidth() {
		
		//Handles the case that the current Widget is collapsed.
		if (isCollapsed()) {
			return 0;
		}
		
		//Handles the case that the current Widget is expanded.
		return getWidthWhenExpanded();
	}
	
	//method
	/**
	 * Connects the {@link WidgetLook}s of the current {@link Widget}.
	 */
	private void connectLooks() {
		getRefHoverLook().setBaseLook(getRefBaseLook());
		getRefFocusLook().setBaseLook(getRefBaseLook());
	}
	
	//method
	/**
	 * Connects the {@link WidgetLook}s of the current {@link Widget}.
	 */
	private void createLooks() {
		baseLook.setExtensionElement(createLook());
		hoverLook.setExtensionElement(createLook());
		focusLook.setExtensionElement(createLook());
	}
	
	//method
	/**
	 * Creates and connects the {@link WidgetLook}s of the current {@link Widget}.
	 */
	private final void createAndConnectLooks() {
		createLooks();
		connectLooks();
	}
	
	//method
	/**
	 * Fills up recursively the child {@link Widget}s of the current {@link Widget} into the given list.
	 * 
	 * @param list
	 */
	private void fillUpChildWidgetsRecursively(final LinkedList<Widget<?, ?>> list) {
		fillUpChildWidgets(list);
		getChildWidgets().forEach(w -> w.fillUpChildWidgetsRecursively(list));
	}
	
	//method
	/**
	 * Fills up recursively the {@link Widget}s of the current {@link Widget},
	 * that are for painting, into the given list.
	 * 
	 * @param list
	 */
	private void fillUpWidgetsForPaintingRecursively(final LinkedList<Widget<?, ?>> list) {
		fillUpShownWidgets(list);
		getRefPaintableWidgets().forEach(w -> w.fillUpWidgetsForPaintingRecursively(list));
	}
	
	//method
	private SingleContainer<Widget<?, ?>> getRefWidgetUnderCursor() {
		return getRefPaintableWidgets().getRefFirstOptionally(Widget::isUnderCursor);
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a continuous key press on itself for the case when it is enabled.
	 * 
	 * @param key
	 */
	private void noteContinuousKeyPressOnSelfWhenEnabled_(final Key key) {
		
		//Handles the case that the current Widget has a continuous key press action.
		//For a better performance, this implementation does not use all comfortable methods.
		if (continuousKeyPressAction != null) {
			continuousKeyPressAction.getOutput(asConcrete(), key);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button click on itself for the case when it is enabled.
	 */
	private void noteLeftMouseButtonClickOnSelfWhenEnabled_() {
		if (showAreaIsUnderCursor() && hasLeftMouseButtonClickAction()) {				
			leftMouseButtonClickAction.run(asConcrete());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button press on itself for the case when it is enabled.
	 */
	private void noteLeftMouseButtonPressOnSelfWhenEnabled_() {		
		if (!isUnderCursor()) {
			noteLeftMouseButtonPressOnSelfWhenEnabledAndNotUnderCursor();
		} else {
			noteLeftMouseButtonPressOnSelfWhenEnabledAndUnderCursor();
		}
	}
	
	//method
	private void noteLeftMouseButtonPressOnSelfWhenEnabledAndUnderCursor() {
		
		if (!isFocused()) {
			setFocused();
		}
		
		if (hasLeftMouseButtonPressAction() && showAreaIsUnderCursor()) {				
			leftMouseButtonPressAction.run(asConcrete());
		}
	}
	
	//method
	private void noteLeftMouseButtonPressOnSelfWhenEnabledAndNotUnderCursor() {
		if (isFocused()) {
			setUnfocused();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button release for the case when it is enabled.
	 */
	private void noteLeftMouseButtonReleaseOnSelfWhenEnabled_() {
		if (!isUnderCursor()) {
			noteLeftMouseButtonReleaseOnSelfWhenEnabledAndNotUnderCursor();
		} else {
			noteLeftMouseButtonReleaseOnSelfWhenEnabledAndUnderCursor();
		}
	}
	
	//method
	private void noteLeftMouseButtonReleaseOnSelfWhenEnabledAndNotUnderCursor() {
		if (isFocused()) {
			setUnfocused();
		}
	}
	
	//method
	private void noteLeftMouseButtonReleaseOnSelfWhenEnabledAndUnderCursor() {
		if (hasLeftMouseButtonReleaseAction() && showAreaIsUnderCursor()) {
			leftMouseButtonReleaseAction.run(asConcrete());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse move.
	 */
	private void noteMouseMove() {
		if (isEnabled()) {
			
			noteMouseMoveOnSelfWhenEnabled_();
			noteMouseMoveOnSelfWhenEnabled();
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(Widget::noteMouseMove);
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse move on itself for the case when it is enabled.
	 */
	private void noteMouseMoveOnSelfWhenEnabled_() {
		if (!isUnderCursor()) {
			noteMouseMoveOnSelfWhenEnabledAndNotUnderCursor();
		} else {
			noteMouseMoveOnSelfWhenEnabledAndUnderCursor();
		}
	}
	
	//method
	private void noteMouseMoveOnSelfWhenEnabledAndUnderCursor() {
		
		if (!isHovered()) {
			setHovered();
		}
		
		if (hasMouseMoveAction() && showAreaIsUnderCursor()) {				
			mouseMoveAction.run(asConcrete());
		}
	}
	
	//method
	private void noteMouseMoveOnSelfWhenEnabledAndNotUnderCursor() {
		if (isHovered()) {
			setUnhovered();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button click on itself for the case when it is enabled.
	 */
	private void noteRightMouseButtonClickOnSelfWhenEnabled_() {
		if (showAreaIsUnderCursor() && hasRightMouseButtonClickAction()) {				
			rightMouseButtonClickAction.run(asConcrete());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button press on itself for the case when it is enabled.
	 */
	private void noteRightMouseButtonPressOnSelfWhenEnabled_() {
		if (showAreaIsUnderCursor() && hasRightMouseButtonPressAction()) {				
			rightMouseButtonPressAction.run(asConcrete());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button release on itself for the case when it is enabled.
	 */
	private void noteRightMouseButtonReleaseOnSelfWhenEnabled_() {
		if (showAreaIsUnderCursor() && hasRightMouseButtonReleaseAction()) {				
			rightMouseButtonReleaseAction.run(asConcrete());
		}
	}
	
	//method
	/**
	 * Paints a cover for the current {@link Widget} using the given painter, that is supposed to be positioned.
	 * 
	 * @param painter
	 */
	private void paintCoverUsingPositionedPainter(IPainter painter) {
		painter.setColor(Color.GREY);
		painter.paintFilledRectangle(getWidth(), getHeight());
	}
		
	//method
	/**
	 * Paints the current {@link Widget} recursively using the given painter, that is supposed to be positioned.
	 * 
	 * @param painter
	 */
	private void paintRecursivelyUsingPositionedPainter(final IPainter painter) {
		
		paintRecursivelyUsingPositionedPainterWhenNotDisabled(painter);
				
		//Handles the case that the current widget is disabled and would grey out.
		if (isDisabled() && greysOutWhenDisabled()) {
			paintCoverUsingPositionedPainter(painter);	
		}
	}
	
	//method
	/**
	 * Paints the current {@link Widget} recursively using the given painter, that is supposed to be positioned,
	 * for the case when the current {@link Widget} is not disabled.
	 * 
	 * @param painter
	 */
	private void paintRecursivelyUsingPositionedPainterWhenNotDisabled(final IPainter painter) {
		
		paint(painter, getRefLook());
		
		if (paintsPaintableWidgetAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.paintRecursively(painter));
		}
	}
	
	//method
	/**
	 * Sets the position of the cursor on the current {@link Widget} recursively.
	 * 
	 * @param cursorXPosition
	 * @param cursorYPosition
	 */
	private void setCursorPosition(final int cursorXPosition, final int cursorYPosition) {
		
		this.cursorXPosition = cursorXPosition;
		this.cursorYPosition = cursorYPosition;
				
		final var cursorXPositionOnScrolledArea = getCursorXPositionOnContentArea();
		final var cursorYPositionOnScrolledArea = getCursorYPositionOnContentArea();
		
		for (final var w : getRefPaintableWidgets()) {
			w.setCursorPosition(
				cursorXPositionOnScrolledArea - w.getXPosition(),
				cursorYPositionOnScrolledArea - w.getYPosition()
			);
		}
	}
	
	//method
	private void setEnablingState(final boolean enabled) {
		if (!enabled) {
			setDisabled();
		} else {
			setEnabled();
		}
	}
	
	//method
	private void setExpansionState(final boolean expanded) {
		if (!expanded) {
			setCollapsed();
		} else {
			setExpanded();
		}
	}
	
	//method
	private void setGreyOutState(final boolean greysOut) {
		if (!greysOut) {
			removeGreyOutWhenDisabled();
		} else {
			setGreyOutWhenDisabled();
		}
	}
	
	//method
	private void setFocusState(final boolean focused) {
		if (!focused) {
			setUnfocused();
		} else {
			setFocused();
		}
	}
	
	//method
	private void setHoverState(final boolean hovered) {
		if (!hovered) {
			setUnhovered();
		} else {
			setHovered();
		}
	}
	
	//method
	/**
	 * Sets the {@link Widget} the current {@link Widget} will belong to.
	 * 
	 * @param parentWidget
	 * @throws ArgumentIsNullException if the given parentWidget is null.
	 * @throws InvalidArgumentException if the current {@link Widget} belongs already to a parent.
	 */
	private void setParent(final Widget<?, ?> parentWidget) {
		setParent(new WidgetParent(parentWidget, this));
	}

	//method
	/**
	 * Sets the parent the current {@link Widget} will belong to.
	 * 
	 * @param parent
	 * @throws ArgumentIsNullException if the given parent is null.
	 * @throws InvalidArgumentException if the current {@link Widget} belongs already to a parent.
	 */
	private void setParent(final WidgetParent parent) {
		
		//Asserts that the given parent is not null.
		Validator.assertThat(parent).thatIsNamed(LowerCaseCatalogue.PARENT).isNotNull();
				
		//Sets the parent of the current Widget.
		this.parent = parent;
	}
}
