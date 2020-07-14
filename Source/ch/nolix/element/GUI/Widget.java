//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.common.constant.PascalCaseNameCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.rasterAPI.TopLeftPositionedRecangular;
import ch.nolix.common.skillAPI.Recalculable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseGUI_API.IInputActionManager;
import ch.nolix.element.color.Color;
import ch.nolix.element.configuration.ConfigurableElement;
import ch.nolix.element.elementAPI.IConfigurableElement;
import ch.nolix.element.elementEnum.DirectionOfRotation;
import ch.nolix.element.input.IInputTaker;
import ch.nolix.element.input.Key;
import ch.nolix.element.inputDeviceAPI.IKeyBoard;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link Widget} is an element on a {@link LayerGUI}.
 * A {@link Widget} determines its width and height.
 * A {@link Widget} is a {@link ConfigurableElement}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 2020
 * @param <W> The type of a {@link Widget}.
 * @param <WL> The type of the {@link WidgetLook} of a {@link Widget}.
 */
public abstract class Widget<W extends Widget<W, WL>, WL extends WidgetLook<WL>> extends ConfigurableElement<W>
implements IInputActionManager<W>, IInputTaker, Recalculable, TopLeftPositionedRecangular {
	
	//constant
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.Arrow;
	
	//constant
	private static final String GREY_OUT_WHEN_DISABLED_HEADER = "GreyOutWhenDisabled";
	
	//constants
	private static final String BASE_PREFIX = "Base";
	private static final String HOVER_PREFIX = "Hover";
	private static final String FOCUS_PREFIX = "Focus";
	
	//attributes
	private WidgetState state = WidgetState.Normal;
	private CursorIcon customCursorIcon = DEFAULT_CURSOR_ICON;
	private boolean greyOutWhenDisabled = false;
	
	//attributes
	private final WL baseLook = createLook();
	private final WL hoverLook = createLook();
	private final WL focusLook = createLook();
	
	//attributes
	private int xPositionOnParent = 0;
	private int yPositionOnParent = 0;
	
	//attributes
	private int width = 0;
	private int height = 0;
	
	//attributes
	private int cursorXPosition;
	private int cursorYPosition;
	
	//optional attribute
	private WidgetParent parent;
	
	//optional attributes
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
		hoverLook.setBaseLook(baseLook);
		focusLook.setBaseLook(baseLook);
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
			case PascalCaseNameCatalogue.STATE:
				setState(WidgetState.fromSpecification(attribute));
				break;
			case CursorIcon.TYPE_NAME:
				setCustomCursorIcon(CursorIcon.fromSpecification(attribute));
				break;
			case GREY_OUT_WHEN_DISABLED_HEADER:
				
				if (!attribute.getOneAttributeAsBoolean()) {
					removeGreyOutWhenDisabled();
				}
				else {
					setGreyOutWhenDisabled();
				}
				
				break;
			default:
				if (attribute.getHeader().startsWith(BASE_PREFIX)) {
					final var copy = attribute.getCopy();
					copy.setHeader(attribute.getHeader().substring(BASE_PREFIX.length()));
					baseLook.addOrChangeAttribute(copy);
				}
				else if (attribute.getHeader().startsWith(HOVER_PREFIX)) {
					final var copy = attribute.getCopy();
					copy.setHeader(attribute.getHeader().substring(HOVER_PREFIX.length()));
					hoverLook.addOrChangeAttribute(copy);
				}
				else if (attribute.getHeader().startsWith(FOCUS_PREFIX)) {
					final var copy = attribute.getCopy();
					copy.setHeader(attribute.getHeader().substring(FOCUS_PREFIX.length()));
					focusLook.addOrChangeAttribute(copy);
				}
				else {
				
					//Calls method of the base class.
					super.addOrChangeAttribute(attribute);
				}
		}
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link Widget}
	 * and applies a default configuration to the current{@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W applyDefaultConfiguration() {
		
		resetConfiguration();
		applyDefaultConfigurationWhenHasBeenReset();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Applies the given base look mutator to the base look of the current {@link Widget}.
	 * 
	 * @param baseLookMutator
	 * @return the current {@link Widget}.
	 */
	public final W applyOnBaseLook(final IElementTaker<WL> baseLookMutator) {
		
		baseLookMutator.run(baseLook);
		
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
		
		focusLookMutator.run(focusLook);
		
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
		
		hoverLookMutator.run(hoverLook);
		
		return asConcrete();
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} belongs directly to a {@link LayerGUI}.
	 */
	public final boolean belongsDirectltyToGUI() {
		return (parent != null && parent.isGUI());
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} belongs to a {@link LayerGUI}, directly or indirectly.
	 */
	public final boolean belongsToGUI() {
		
		//For a better performance, this implementation does not use all comfortable methods.
		return (parent != null && parent.belongsToGUI());
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} belongs directly to a {@link LayerGUI} or another {@link Widget}.
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
	 * -the given x-position on the {@link LayerGUI} the current {@link Widget} belongs to
	 * -the given y-position on the {@link LayerGUI} the current {@link Widget} belongs to
	 */
	public final boolean containsPointOnGUI(final int xPositionOnGUI, final int yPositionOnGUI) {
		
		//For a better performance, this implementation does the cheap comparisons at first.
			final var thisXPositionOnGUI = getXPositionOnGUI();		
			if (xPositionOnGUI <= thisXPositionOnGUI || xPositionOnGUI > thisXPositionOnGUI + getWidth()) {
				return false;
			}
			
			final var thisYPositionOnGUI = getYPositionOnGUI();
			if (yPositionOnGUI <= thisYPositionOnGUI || yPositionOnGUI > thisYPositionOnGUI + getHeight()) {
				return false;
			}
			
			return true;
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
		xPositionOnParent >= this.xPositionOnParent
		&& yPositionOnParent >= this.yPositionOnParent
		&& xPositionOnParent < this.xPositionOnParent + getWidth()
		&& yPositionOnParent < this.yPositionOnParent + getHeight();
	}
	
	//method
	/**
	 * The free view area of a {@link Widget} is the part of the view area of the {@link Widget}
	 * where does not lie a child {@link Widget}, that is for painting, of the {@link Widget}.
	 * 
	 * @return
	 */
	public final boolean freeViewAreaIsUnderCursor() {
		return (isUnderCursor() && getRefPaintableWidgets().contains(Widget::isUnderCursor));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		attributes.addAtEnd(getState().getSpecificationAs(PascalCaseNameCatalogue.STATE));
		attributes.addAtEnd(getCustomCursorIcon().getSpecification());
		attributes.addAtEnd(new Node(GREY_OUT_WHEN_DISABLED_HEADER, greyOutWhenDisabled));
			
		//Extracts the base state attributes of the current Widget.
		final var baseStateAttributes = getRefBaseLook().getAttributes();
		baseStateAttributes.forEach(a -> a.addPrefixToHeader(BASE_PREFIX));
		attributes.addAtEnd(baseStateAttributes);
		
		//Extracts the hover state attributes of the current Widget.
		final var hoverStateAttributes = getRefHoverLook().getAttributes();
		hoverStateAttributes.forEach(a -> a.addPrefixToHeader(HOVER_PREFIX));
		attributes.addAtEnd(hoverStateAttributes);
		
		//Extracts focus state attributes of the current Widget.
		final var focusStateAttributes = getRefFocusLook().getAttributes();
		focusStateAttributes.forEach(a -> a.addPrefixToHeader(FOCUS_PREFIX));
		attributes.addAtEnd(focusStateAttributes);
		
		return attributes;
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
		
		final var widgetUnderCursor = getRefPaintableWidgets().getRefFirstOrNull(Widget::isUnderCursor);
		if (widgetUnderCursor != null) {
			return widgetUnderCursor.getCursorIcon();
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
	 * @return the index of the current {@link Widget} on the {@link LayerGUI} the current {@link Widget} belongs to.
	 * @throws InvalidArgumentException if the current {@link Widget} does not belong to a {@link LayerGUI}.
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
	public final LayerGUI<?> getParentGUI() {
		
		//Asserts that the current Widget belongs to a GUI.
		if (parent == null) {
			throw new ArgumentDoesNotBelongToParentException(this, GUI.class);
		}
		
		return parent.getRefGUI();
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
		return baseLook;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final LinkedList<IConfigurableElement<?>> getRefConfigurables() {		
		return (LinkedList)getChildWidgets();
	}
	
	//method
	/**
	 * @return the focus look of the current {@link Widget}.
	 */
	public final WL getRefFocusLook() {
		return focusLook;
	}
	
	//method
	/**
	 * @return the hover look of the current {@link Widget}.
	 */
	public final WL getRefHoverLook() {
		return hoverLook;
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
	 * @return the current look of the current {@link Widget}.
	 */
	public final WL getRefLook() {
		
		//Enumerates the state of the current Widget.
		switch (state) {
			case Normal:
			case Disabled:
			case Collapsed:
				return getRefBaseLook();
			case Hovered:
				return getRefHoverLook();
			case Focused:
				return getRefFocusLook();
			default:
				throw new InvalidArgumentException(state);
		}
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
	 * @return the state of the current {@link Widget}.
	 */
	public final WidgetState getState() {
		return state;
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
		return xPositionOnParent;
	}
	
	//method
	/**
	 * @return the x-position of the current {@link Widget} on the {@link LayerGUI}
	 * the current {@link Widget} belongs to.
	 */
	public final int getXPositionOnGUI() {
				
		//Handles the case that the current Widget does not belong to a parent.
		if (parent == null) {
			return xPositionOnParent;
		}
		
		//Handles the case that the current Widget belongs to a parent.
		return (parent.getXPositionOnGUI() + xPositionOnParent);
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getYPosition() {
		return yPositionOnParent;
	}
	
	//method
	/**
	 * @return the y-position of the current {@link Widget} on the {@link LayerGUI}
	 * the current {@link Widget} belongs to.
	 */
	public final int getYPositionOnGUI() {
		
		//Handles the case that the current Widget does not belong to a parent.
		if (parent == null) {
			return yPositionOnParent;
		}
		
		//Handles the case that the current Widget belongs to a parent.
		return (parent.getYPositionOnGUI() + yPositionOnParent);
	}

	//method
	/**
	 * @return true if the current {@link Widget} grays out when it is disabled.
	 */
	public final boolean greysOutWhenDisabled() {
		return greyOutWhenDisabled;
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
	 * @return true if the current {@link Widget} is collapsed.
	 */
	public final boolean isCollapsed() {
		return (state == WidgetState.Collapsed);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is disabled.
	 */
	public final boolean isDisabled() {
		return (state == WidgetState.Disabled);
	}
	
	//method
	/**
	 * A {@link Widget} is enabled when it is normal, hovered or focused.
	 * 
	 * @return true if the current {@link Widget} is enabled.
	 */
	public final boolean isEnabled() {
		
		//Enumerates the state of the current Widget.
		switch (state) {
			case Normal:
			case Hovered:
			case Focused:
				return true;
			default:
				return false;
		}
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is focused.
	 */
	public final boolean isFocused() {
		return (state == WidgetState.Focused);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is hovered.
	 */
	public final boolean isHovered() {
		return (state == WidgetState.Hovered);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is normal.
	 */
	public final boolean isNormal() {
		return (state == WidgetState.Normal);
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
			
			noteKeyPressOnSelfWhenEnabled(key);
			
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
			
			noteKeyReleaseOnSelfWhenEnabled(key);
			
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
			
			noteKeyTypingOnSelfWhenEnabled(key);
			
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
			
			//TODO: noteMouseWheelClickOnSelfWhenEnabled()
			
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
			
			//TODO: noteMouseWheelPressOnSelfWhenEnabled()
			
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
			
			//TODO: noteMouseWheelReleaseOnSelfWhenEnabled()
			
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
	public void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {
		if (isEnabled()) {
			
			noteMouseWheelRotationStepOnSelfWhenEnabled(directionOfRotation);
			
			if (redirectsInputsToShownWidgets()) {
				getRefPaintableWidgets().forEach(w -> w.noteMouseWheelRotationStep(directionOfRotation));
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
	 * Avoids that the current {@link Widget} greys out when it is disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeGreyOutWhenDisabled() {
		
		greyOutWhenDisabled = false;
		
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
	public W reset() {
				
		setNormal();
		
		removeLeftMouseButtonClickAction();
		removeLeftMouseButtonPressAction();
		removeLeftMouseButtonReleaseAction();
		removeRightMouseButtonClickAction();
		removeRightMouseButtonPressAction();
		removeRightMouseButtonReleaseAction();
		
		/*
		 * Calls method of the base class.
		 * 
		 * The base class' reset method is called at the end of the current class' reset method,
		 * because the base class' reset method calls the resetConfiguration method,
		 * that requires the result of the current class' reset method.
		 */
		super.reset();
				
		return asConcrete();
	}
	
	//method
	/**
	 * Resets and applies the default configuration to the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W resetAndApplyDefaultConfiguration() {
		
		reset();
		applyDefaultConfigurationWhenHasBeenReset();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	@Override
	public W resetConfiguration() {
		
		setCustomCursorIcon(DEFAULT_CURSOR_ICON);
		setGreyOutWhenDisabled();
		
		baseLook.reset();
		hoverLook.reset();
		focusLook.reset();
				
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} collapsed.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setCollapsed() {
		
		state = WidgetState.Collapsed;
		
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
		
		state = WidgetState.Disabled;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} focused.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setFocused() {
		
		state = WidgetState.Focused;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link Widget} grey out when it is disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setGreyOutWhenDisabled() {
		
		greyOutWhenDisabled = true;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} hovered.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setHovered() {
		
		state = WidgetState.Hovered;
		
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
		
		Validator.assertThat(leftMouseButtonPressAction).thatIsNamed("left mouse button click action").isNotNull();
		
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
	 * Sets the {@link LayerGUI} the current {@link Widget} will belong.
	 * 
	 * @param parentGUI
	 * @throws ArgumentIsNullException if the given parentGUI is null.
	 * @throws InvalidArgumentException if the current {@link Widget} belongs already to a parent.
	 */
	public final void setParent(final LayerGUI<?> parentGUI) {
		setParent(new WidgetParent(parentGUI, this));
	}
	
	//method
	/**
	 * Sets the position of the current {@link Widget} on its parent.
	 * 
	 * @param xPositionOnParent
	 * @param yPositionOnParent
	 */
	public final void setPositionOnParent(final int xPositionOnParent,	final int yPositionOnParent) {				
		this.xPositionOnParent = xPositionOnParent;
		this.yPositionOnParent = yPositionOnParent;
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
	 * Sets the current {@link Widget} normal.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setNormal() {
		
		state = WidgetState.Normal;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the state of the current {@link Widget}.
	 * 
	 * @param state
	 * @throws ArgumentIsNullException if the given state is null.
	 */
	public final W setState(final WidgetState state) {
		
		//Asserts that the given state is not null.
		Validator.assertThat(state).thatIsNamed(VariableNameCatalogue.STATE).isNotNull();

		//Sets the state of the current Widget.
		this.state = state;
		
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
	 * Applies the default configuration to the current {@link Widget}
	 * for the case when the configuration of the current {@link Widget} has been reset.
	 */
	protected abstract void applyDefaultConfigurationWhenHasBeenReset();
	
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
	 * @return the height of the current {@link Widget} when it is s not collapsed.
	 */
	protected abstract int getHeightWhenNotCollapsed();
	
	//method declaration
	/**
	 * @return the width of the current {@link Widget} when it is not collapsed.
	 */
	protected abstract int getWidthWhenNotCollapsed();
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a key press on itself
	 * for the case when the current {@link Widget} is enabled.
	 * 
	 * @param key
	 */
	protected abstract void noteKeyPressOnSelfWhenEnabled(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a key release on itself
	 * for the case when the current {@link Widget} is enabled.
	 * 
	 * @param key
	 */
	protected abstract void noteKeyReleaseOnSelfWhenEnabled(Key key);
	
	//method declaration
	/**
	 * Lets the current {@link Widget} note a key typing on itself
	 * for the case when the current {@link Widget} is enabled.
	 * 
	 * @param key
	 */
	protected abstract void noteKeyTypingOnSelfWhenEnabled(Key key);
	
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
	 * Lets the current {@link Widget} note a mouse wheel rotation step
	 * for the case when the current {@link Widget} is enabled.
	 * 
	 * @param directionOfRotation
	 */
	protected abstract void noteMouseWheelRotationStepOnSelfWhenEnabled(final DirectionOfRotation directionOfRotation);
	
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
	 * @return true if the current {@link Widget} paints its paintable {@link Widgets} a priori.
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
		
		recalculateSelfStage2();
	}
	
	//method
	/**
	 * Recalculates the current {@link Widget} itself stage 2.
	 */
	protected abstract void recalculateSelfStage2();
	
	//method declaration
	/**
	 * @return true if the current {@link Widget} redirects intputs to its shown {@link Widgets}.
	 */
	protected abstract boolean redirectsInputsToShownWidgets();
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link Widget} belongs to a {@link LayerGUI} that is closed.
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
		if (state == WidgetState.Collapsed) {
			return 0;
		}
		
		//Handles the case that the current Widget is not collapsed.
		return getHeightWhenNotCollapsed();
	}
	
	//method
	/**
	 * @return the newly calculated width of the current {@link Widget}.
	 */
	private int calculatedWidth() {
		
		//Handles the case that the current Widget is collapsed.
		if (state == WidgetState.Collapsed) {
			return 0;
		}
		
		//Handles the case that the current Widget is not collapsed.
		return getWidthWhenNotCollapsed();
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
			
			//Enumerates the state of the current Widget.
			switch (state) {
				case Hovered:
				case Focused:
					setNormal();
					break;
				default:
					break;
			}
		}
		else {
			
			//Enumerates the state of the current Widget.
			switch (state) {
				case Normal:
				case Hovered:
					setFocused();
					break;
				default:
					break;
			}
			
			if (showAreaIsUnderCursor() && hasLeftMouseButtonPressAction()) {				
				leftMouseButtonPressAction.run(asConcrete());
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button release for the case when it is enabled.
	 */
	private void noteLeftMouseButtonReleaseOnSelfWhenEnabled_() {
		
		if (!isUnderCursor()) {
			
			//Enumerates the state of the current Widget.
			switch (state) {
				case Hovered:
				case Focused:
					setNormal();
					break;
				default:
					break;
			}
		}
		else {
			if (showAreaIsUnderCursor() && hasLeftMouseButtonReleaseAction()) {
				leftMouseButtonReleaseAction.run(asConcrete());
			}
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
			
			//Enumerates the sate of the current Widget.
			switch(state) {
				case Hovered:
					setNormal();
					break;
				default:
					break;
			}
		}
		else {
			
			//Enumerates the sate of the current Widget.
			switch(state) {
				case Normal:
					setHovered();
					break;
				default:
					break;
			}
			
			if (showAreaIsUnderCursor() && hasMouseMoveAction()) {				
				mouseMoveAction.run(asConcrete());
			}
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
	 * @return the current {@link Widget}.
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
		Validator.assertThat(parent).thatIsNamed(VariableNameCatalogue.PARENT).isNotNull();
				
		//Sets the parent of the current Widget.
		this.parent = parent;
	}
}
