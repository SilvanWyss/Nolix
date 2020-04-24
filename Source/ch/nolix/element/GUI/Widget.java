//package declaration
package ch.nolix.element.GUI;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.functionAPI.IElementTaker;
import ch.nolix.common.functionAPI.IFunction;
import ch.nolix.common.generalSkillAPI.ISmartObject;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotBelongToParentException;
import ch.nolix.common.invalidArgumentException.ClosedArgumentException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.rasterAPI.TopLeftPositionedRecangular;
import ch.nolix.common.skillAPI.Recalculable;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.configuration.ConfigurableElement;
import ch.nolix.element.elementAPI.IConfigurableElement;
import ch.nolix.element.elementEnum.DirectionOfRotation;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link Widget} is an element on a {@link LayerGUI}.
 * A {@link Widget} determines its width and height.
 * A {@link Widget} is a {@link ConfigurableElement}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1890
 * @param <W> The type of a {@link Widget}.
 * @param <WL> The type of the {@link WidgetLook} of a {@link Widget}.
 */
public abstract class Widget<W extends Widget<W, WL>, WL extends WidgetLook<WL>> extends ConfigurableElement<W>
implements ISmartObject<W>, Recalculable, TopLeftPositionedRecangular {
	
	//default value
	public static final CursorIcon DEFAULT_CURSOR_ICON = CursorIcon.Arrow;
	
	//constants
	private static final String STATE_HEADER ="State";
	private static final String GREY_OUT_WHEN_DISABLED_FLAG_HEADER = "GreyOutWhenDisabled";
	
	//constants
	private static final String BASE_PREFIX = "Base";
	private static final String FOCUS_PREFIX = "Focus";
	private static final String HOVER_PREFIX = "Hover";
	
	//attributes
	private WidgetState state;
	private CursorIcon customCursorIcon;
	private boolean greyOutWhenDisabled;
	
	//attributes
	private final WL baseLook = createLook();
	private final WL hoverLook = createLook();
	private final WL focusLook = createLook();
	
	//attributes
	private int xPositionOnParent;
	private int yPositionOnParent;
	
	//attributes
	private int width;
	private int height;
	
	//attributes
	protected int cursorXPosition;
	protected int cursorYPosition;
	
	//optional attribute
	private WidgetParent parent;
	
	//optional attributes
	private IFunction leftMouseButtonClickCommand;
	private IFunction leftMouseButtonPressCommand;
	private IFunction leftMouseButtonReleaseCommand;
	private IFunction rightMouseButtonClickCommand;
	private IFunction rightMouseButtonPressCommand;
	private IFunction rightMouseButtonReleaseCommand;
	
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
			case STATE_HEADER:
				setState(WidgetState.fromSpecification(attribute));
				break;
			case CursorIcon.TYPE_NAME:
				setCustomCursorIcon(CursorIcon.fromSpecification(attribute));
				break;
			case GREY_OUT_WHEN_DISABLED_FLAG_HEADER:
				
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
		return (isUnderCursor() && getRefPaintableWidgets().contains(w -> w.isUnderCursor()));
	}
	
	//method
	/**
	 * {@inheritDoc}}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class.
		final var attributes = super.getAttributes();
		
		attributes.addAtBegin(getInteractionAttributes());
		attributes.addAtEnd(getCustomCursorIcon().getSpecification());
		attributes.addAtEnd(new Node(GREY_OUT_WHEN_DISABLED_FLAG_HEADER, greyOutWhenDisabled));
			
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
		
		final var widgetUnderCursor = getRefPaintableWidgets().getRefFirstOrNull(w -> w.isUnderCursor());
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
		return getRefGUI().getRefWidgets().getIndexOf(this);
	}
	
	//method
	/**
	 * The interaction attributes of a {@link Widget} are those attributes a user can change.
	 * 
	 * @return the interaction attributes of the current {@link Widget}.
	 */
	public LinkedList<Node> getInteractionAttributes() {
		return new LinkedList<> (getState().getSpecificationAs(STATE_HEADER));
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
	 * @return the {@link LayerGUI} the current {@link Widget} belongs to, directly or indirectly.
	 * @throws InvalidArgumentException if the current {@link Widget} does not belong to a {@link LayerGUI}.
	 */
	public final LayerGUI<?> getRefGUI() {
		
		if (parent == null) {
			throw new ArgumentDoesNotBelongToParentException(this, GUI.class);
		}
		
		return parent.getRefGUI();
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
		fillUpPaintableWidgets(widgetsForPainting);
		
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
	 * @return true if the current {@link Widget} has a left mouse button click command.
	 */
	public final boolean hasLeftMouseButtonClickCommand() {
		return (leftMouseButtonClickCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a left mouse button press command.
	 */
	public final boolean hasLeftMouseButtonPressCommand() {
		return (leftMouseButtonPressCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a left mouse button release command.
	 */
	public final boolean hasLeftMouseButtonReleaseCommand() {
		return (leftMouseButtonReleaseCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a right mouse button click command.
	 */
	public final boolean hasRightMouseButtonClickCommand() {
		return (rightMouseButtonClickCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a right mouse button press command.
	 */
	public final boolean hasRightMouseButtonPressCommand() {
		return (rightMouseButtonPressCommand != null);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} has a right mouse button release command.
	 */
	public final boolean hasRightMouseButtonReleaseCommand() {
		return (rightMouseButtonReleaseCommand != null);
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
	 * Lets the current {@link Widget} note a key press.
	 * 
	 * @param key
	 */
	public final void noteKeyPress(final Key key) {
		if (isEnabled()) {
			noteKeyPressWhenEnabled(key);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key press recursively.
	 * 
	 * @param key
	 */
	public final void noteKeyPressRecursively(final Key key) {
		
		noteKeyPress(key);
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteKeyPressRecursively(key));
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key release.
	 * 
	 * @param key
	 */
	public final void noteKeyRelease(final Key key) {
		if (isEnabled()) {
			noteKeyReleaseWhenEnabled(key);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key release recursively.
	 * 
	 * @param key
	 */
	public final void noteKeyReleaseRecursively(final Key key) {
		
		noteKeyRelease(key);
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteKeyReleaseRecursively(key));
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key typing.
	 * 
	 * @param key
	 */
	public final void noteKeyTyping(final Key key) {
		if (isEnabled()) {
			noteKeyTypingWhenEnabled(key);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key typing recursively.
	 * 
	 * @param key
	 */
	public final void noteKeyTypingRecursively(final Key key) {
		
		noteKeyTyping(key);
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteKeyTypingRecursively(key));
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button click.
	 */
	public final void noteLeftMouseButtonClick() {
		if (isEnabled()) {
			noteLeftMouseButtonClickWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button click recursively.
	 */
	public final void noteLeftMouseButtonClickRecursively() {
		
		noteLeftMouseButtonClick();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteLeftMouseButtonClickRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button press.
	 */
	public final void noteLeftMouseButtonPress() {
		if (isEnabled()) {
			noteLeftMouseButtonPressWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button press recursively.
	 */
	public final void noteLeftMouseButtonPressRecursively() {
		
		noteLeftMouseButtonPress();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteLeftMouseButtonPressRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button release.
	 */
	public final void noteLeftMouseButtonRelease() {
		if (isEnabled()) {
			noteLeftMouseButtonReleaseWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button release recursively.
	 */
	public final void noteLeftMouseButtonReleaseRecursively() {
		
		noteLeftMouseButtonRelease();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteLeftMouseButtonReleaseRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse move.
	 */
	public final void noteMouseMove() {
		if (isEnabled()) {
			noteMouseMoveWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse move recursively.
	 */
	public final void noteMouseMoveRecursively() {
		
		noteMouseMove();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteMouseMoveRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel click.
	 */
	public void noteMouseWheelClick() {
		if (isEnabled()) {
			noteMouseWheelClickWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel click recursively.
	 */
	public void noteMouseWheelClickRecursively() {
		
		noteMouseWheelClick();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteMouseWheelClickRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel press.
	 */
	public void noteMouseWheelPress() {
		if (isEnabled()) {
			noteMouseWheelPressWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel press recursively.
	 */
	public void noteMouseWheelPressRecursively() {
		
		noteMouseWheelPress();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteMouseWheelPressRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel release.
	 */
	public void noteMouseWheelRelease() {
		if (isEnabled()) {
			noteMouseWheelReleaseWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel release recursively.
	 */
	public void noteMouseWheelReleaseRecursively() {
		
		noteMouseWheelRelease();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteMouseWheelReleaseRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel rotation step.
	 * 
	 * @param directionOfRotation
	 */
	public void noteMouseWheelRotationStep(final DirectionOfRotation directionOfRotation) {
		if (isEnabled()) {
			noteMouseWheelRotationStepWhenEnabled(directionOfRotation);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel rotation step recursively.
	 * 
	 * @param directionOfRotation
	 */
	public void noteMouseWheelRotationStepRecursively(final DirectionOfRotation directionOfRotation) {
		
		noteMouseWheelRotationStep(directionOfRotation);
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteMouseWheelRotationStepRecursively(directionOfRotation));
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button click.
	 */
	public final void noteRightMouseButtonClick() {
		if (isEnabled()) {
			noteRightMouseButtonClickWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button click recursively.
	 */
	public final void noteRightMouseButtonClickRecursively() {
		
		noteRightMouseButtonClick();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteRightMouseButtonClickRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button press.
	 */
	public final void noteRightMouseButtonPress() {
		if (isEnabled()) {
			noteRightMouseButtonPressWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button press recursively.
	 */
	public final void noteRightMouseButtonPressRecursively() {
		
		noteRightMouseButtonPress();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteRightMouseButtonPressRecursively());
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button release.
	 */
	public final void noteRightMouseButtonRelease() {
		if (isEnabled()) {
			noteRightMouseButtonReleaseWhenEnabled();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button release recursively.
	 */
	public final void noteRightMouseButtonReleaseRecursively() {
		
		noteRightMouseButtonRelease();
		
		if (redirectsEventsToPaintableWidgetsAPriori()) {
			getRefPaintableWidgets().forEach(w -> w.noteRightMouseButtonReleaseRecursively());
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
	public void recalculate() {
		width = calculatedWidth();
		height = calculatedHeight();
	}
	
	//method
	/**
	 * Recalculates the current {@link Widget} recursively.
	 */
	public final void recalculateRecursively() {
		
		getRefPaintableWidgets().forEach(cw -> cw.recalculateRecursively());
		
		recalculate();
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
	 * Removes the left mouse button click command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeLeftMouseButtonClickCommand() {
		
		leftMouseButtonClickCommand = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the left mouse button press command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeLeftMouseButtonPressCommand() {
		
		leftMouseButtonPressCommand = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the left mouse button release command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeLeftMouseButtonReleaseCommand() {
		
		leftMouseButtonReleaseCommand = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right mouse button click command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeRightMouseButtonClickCommand() {
		
		rightMouseButtonClickCommand = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right mouse button press command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeRightMouseButtonPressCommand() {
		
		rightMouseButtonPressCommand = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the right mouse button release command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeRightMouseButtonReleaseCommand() {
		
		rightMouseButtonReleaseCommand = null;
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public W reset() {
				
		setNormal();
		
		removeLeftMouseButtonClickCommand();
		removeLeftMouseButtonPressCommand();
		removeLeftMouseButtonReleaseCommand();
		removeRightMouseButtonClickCommand();
		removeRightMouseButtonPressCommand();
		removeRightMouseButtonReleaseCommand();
		
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
	 * Sets the position of the cursor on the current {@link Widget} recursively.
	 * 
	 * @param cursorXPosition
	 * @param cursorYPosition
	 * @return the current {@link Widget}.
	 */
	public W setCursorPositionRecursively(final int cursorXPosition, final int cursorYPosition) {
		
		this.cursorXPosition = cursorXPosition;
		this.cursorYPosition = cursorYPosition;
		
		for (final var w : getRefPaintableWidgets()) {
			w.setParentCursorPositionRecursively(cursorXPosition, cursorYPosition);
		}
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the position of the cursor on the parent of the current {@link Widget} recursively.
	 * 
	 * @param parentCursorXPosition
	 * @param parentCursorYPosition
	 * @return the current {@link Widget}.
	 */
	public final void setParentCursorPositionRecursively(
		final int parentCursorXPosition,
		final int parentCursorYPosition
	) {
		setCursorPositionRecursively(
			parentCursorXPosition - xPositionOnParent,
			parentCursorYPosition - yPositionOnParent
		);
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
	 * Sets the left mouse button click command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonClickCommand
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonClickCommand is null.
	 */
	public final W setLeftMouseButtonClickCommand(final IFunction leftMouseButtonClickCommand) {
		
		//Asserts that the given leftMouseButtonClickCommand is not null.
		Validator.assertThat(leftMouseButtonClickCommand).thatIsNamed("left mouse button click command").isNotNull();
		
		this.leftMouseButtonClickCommand = leftMouseButtonClickCommand;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the left mouse button press command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonPressCommand is null.
	 */
	public final W setLeftMouseButtonPressCommand(final IFunction leftMouseButtonPressCommand) {
		
		//Asserts that the given leftMouseButtonPressCommand is not null.
		Validator.assertThat(leftMouseButtonPressCommand).thatIsNamed("left mouse button press command").isNotNull();
		
		this.leftMouseButtonPressCommand = leftMouseButtonPressCommand;
		
		return asConcrete();
	}	
		
	//method
	/**
	 * Sets the left mouse button release command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given leftMouseButtonReleaseCommand is null.
	 */
	public final W setLeftMouseButtonReleaseCommand(final IFunction leftMouseButtonReleaseCommand) {
		
		//Asserts that the given leftMouseButtonReleaseCommandd is not null.
		Validator.assertThat(leftMouseButtonReleaseCommand).thatIsNamed("left mouse button release command").isNotNull();
		
		this.leftMouseButtonReleaseCommand = leftMouseButtonReleaseCommand;
		
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
	 * Sets the right mouse button click command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonClickCommand
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonClickCommand is null.
	 */
	public final W setRightMouseButtonClickCommand(final IFunction rightMouseButtonClickCommand) {
		
		//Asserts that the given rightMouseButtonClickCommand is not null.
		Validator.assertThat(rightMouseButtonClickCommand).thatIsNamed("right mouse button click command").isNotNull();
		
		this.rightMouseButtonClickCommand = rightMouseButtonClickCommand;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the right mouse button press command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonPressCommand is null.
	 */
	public final W setRightMouseButtonPressCommand(final IFunction rightMouseButtonPressCommand) {
		
		//Asserts that the given rightMouseButtonPressCommand is not null.
		Validator.assertThat(rightMouseButtonPressCommand).thatIsNamed("right mouse button press command").isNotNull();
		
		this.rightMouseButtonPressCommand = rightMouseButtonPressCommand;
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the right mouse button release command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @return the current {@link Widget}.
	 * @throws ArgumentIsNullException if the given rightMouseButtonReleaseCommand is null.
	 */
	public final W setRightMouseButtonReleaseCommand(final IFunction rightMouseButtonReleaseCommand) {
		
		//Asserts that the given rightMouseButtonReleaseCommand is not null.
		Validator.assertThat(rightMouseButtonReleaseCommand).thatIsNamed("right mouse button release command").isNotNull();
		
		this.rightMouseButtonReleaseCommand = rightMouseButtonReleaseCommand;
		
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
	public abstract boolean viewAreaIsUnderCursor();
	
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
	 * and does not create a new {@link LinkedList}.
	 * 
	 * @param list
	 */
	protected abstract void fillUpChildWidgets(LinkedList<Widget<?, ?>> list);
	
	//method declaration
	/**
	 * Fills up the paintable {@link Widget}s of the current {@link Widget} into the given list.
	 * 
	 * For a better performance, a {@link Widget} fills up its paintable {@link Widget}s into a given {@link LinkedList}
	 * and does not create a new {@link LinkedList}.
	 * 
	 * @param list
	 */
	protected abstract void fillUpPaintableWidgets(LinkedList<Widget<?, ?>> list);
	
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
	
	//method
	/**
	 * Lets the current {@link Widget} note a key press for the case when it is enabled.
	 * 
	 * @param key
	 */
	protected void noteKeyPressWhenEnabled(final Key key) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key release for the case when it is enabled.
	 * 
	 * @param key
	 */
	protected void noteKeyReleaseWhenEnabled(final Key key) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key typing for the case when it is enabled.
	 * 
	 * @param key
	 */
	protected void noteKeyTypingWhenEnabled(final Key key) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button click for the case when it is enabled.
	 */
	protected void noteLeftMouseButtonClickWhenEnabled() {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button press for the case when it is enabled.
	 */
	protected void noteLeftMouseButtonPressWhenEnabled() {		
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
			
			if (viewAreaIsUnderCursor() && hasLeftMouseButtonPressCommand()) {				
				leftMouseButtonPressCommand.run();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button release for the case when it is enabled.
	 */
	protected void noteLeftMouseButtonReleaseWhenEnabled() {
		
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
			if (viewAreaIsUnderCursor() && hasLeftMouseButtonReleaseCommand()) {
				leftMouseButtonReleaseCommand.run();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse move for the case when it is enabled.
	 */
	protected void noteMouseMoveWhenEnabled() {
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
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel click for the case when it is enabled.
	 */
	protected void noteMouseWheelClickWhenEnabled() {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel press for the case when it is enabled.
	 */
	protected void noteMouseWheelPressWhenEnabled() {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel release for the case when it is enabled.
	 */
	protected void noteMouseWheelReleaseWhenEnabled() {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse wheel rotation step for the case when it is enabled.
	 * 
	 * @param directionOfRotation
	 */
	protected void noteMouseWheelRotationStepWhenEnabled(final DirectionOfRotation directionOfRotation) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button click for the case when it is enabled.
	 */
	protected void noteRightMouseButtonClickWhenEnabled() {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button press for the case when it is enabled.
	 */
	protected void noteRightMouseButtonPressWhenEnabled() {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button release for the case when it is enabled.
	 */
	protected void noteRightMouseButtonReleaseWhenEnabled() {}
	
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
	 * @return true if the current {@link Widget} redictes the events to its paintable {@link Widgets} a priori.
	 */
	protected boolean redirectsEventsToPaintableWidgetsAPriori() {
		return true;
	}

	//method
	/**
	 * @throws ClosedArgumentException if the current {@link Widget} belongs to a {@link LayerGUI} that is closed.
	 */
	protected final void supposeGUIIsAlive() {
		if (belongsToGUI() && parent.GUIIsClosed()) {
			throw new ClosedArgumentException(getRefGUI());
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
		fillUpPaintableWidgets(list);
		getRefPaintableWidgets().forEach(w -> w.fillUpWidgetsForPaintingRecursively(list));
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
