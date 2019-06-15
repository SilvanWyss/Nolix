//package declaration
package ch.nolix.element.widget;

//Java import
import java.awt.event.KeyEvent;

//own imports
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.functionAPI.IElementTaker;
import ch.nolix.core.functionAPI.IFunction;
import ch.nolix.core.invalidArgumentException.ClosedArgumentException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.skillAPI.Recalculable;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.constants.FunctionCatalogue;
import ch.nolix.core.container.List;
import ch.nolix.core.specificationAPI.Configurable;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.configuration.ConfigurableElement;
import ch.nolix.element.painter.IPainter;

//abstract class
/**
 * A {@link Widget} is an element on a {@link IGUI}.
 * A {@link Widget} determines its width and height.
 * A {@link Widget} is a {@link ConfigurableElement}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1950
 * @param <W> The type of a {@link Widget}.
 * @param <WL> The type of the {@link WidgetLook} of a {@link Widget}.
 */
public abstract class Widget<W extends Widget<W, WL>, WL extends WidgetLook<WL>> extends ConfigurableElement<W>
implements Recalculable {
	
	//constants
	private static final String STATE_HEADER ="State";
	private static final String NO_GREY_OUT_WHEN_DISABLED_HEADER = "NoGreyOutWhenDisabled";
	
	//constants
	private static final String BASE_PREFIX = "Base";
	private static final String FOCUS_PREFIX = "Focus";
	private static final String HOVER_PREFIX = "Hover";
	private static final String HOVER_FOCUS_PREFIX = "HoverFocus";
	
	//constants
	private static final String LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER = "LeftMouseButtonPressCommand";
	private static final String LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER = "LeftMouseButtonReleaseCommand";
	private static final String RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER = "RightMouseButtonPressCommand";
	private static final String RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER = "RightMouseButtonReleaseCommand";
	
	//attributes
	private WidgetState state = WidgetState.Normal;
	private boolean keepsFocus = false;
	private CursorIcon customCursorIcon = CursorIcon.Arrow;
	private boolean greyOutWhenDisabled = true;
	
	//attributes
	private final WL baseLook = createWidgetLook();
	private final WL hoverLook = createWidgetLook();
	private final WL focusLook = createWidgetLook();
	private final WL hoverFocusLook = createWidgetLook();
	
	//attributes
	private int xPositionOnParent = 1;
	private int yPositionOnParent = 1;
	protected int cursorXPosition = 0;
	protected int cursorYPosition = 0;
	
	//attributes
	private int width;
	private int height;
	
	//optional attribute
	/**
	 * The {@link Widget} the current {@link Widget} belongs to.
	 */
	private Widget<?, ?> parentWidget;
	
	//optional attribute
	/**
	 * The {@link IGUI} the current {@link Widget} belongs to.
	 */
	private IGUI<?> parentGUI;
	
	//optional attributes
	private IFunction leftMouseButtonPressCommand;
	private IFunction leftMouseButtonReleaseCommand;
	private IFunction rightMouseButtonPressCommand;
	private IFunction rightMouseButtonReleaseCommand;
	
	//constructor
	/**
	 * Creates a new {@link Widget}.
	 */
	public Widget() {
		getRefHoverLook().setBaseLook(getRefBaseLook());
		getRefFocusLook().setBaseLook(getRefBaseLook());
		getRefHoverFocusLook().setBaseLook(getRefFocusLook());
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link Widget}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case STATE_HEADER:
				setState(WidgetState.createFromSpecification(attribute));
				break;
			case CursorIcon.TYPE_NAME:
				setCustomCursorIcon(CursorIcon.valueOf(attribute.getOneAttributeAsString()));
				break;
			case NO_GREY_OUT_WHEN_DISABLED_HEADER:
				removeGreyOutWhenDisabled();
				break;
			case LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER:
				setLeftMouseButtonPressCommand(FunctionCatalogue.EMPTY);
				break;
			case LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER:
				setLeftMouseButtonReleaseCommand(FunctionCatalogue.EMPTY);
				break;
			case RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER:
				setRightMouseButtonPressCommand(FunctionCatalogue.EMPTY);
				break;
			case RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER:				
				setRightMouseButtonReleaseCommand(FunctionCatalogue.EMPTY);
				break;
			default:
				if (attribute.getHeader().startsWith(BASE_PREFIX)) {
					final var temp = attribute.getCopy();
					temp.setHeader(attribute.getHeader().substring(BASE_PREFIX.length()));
					getRefBaseLook().addOrChangeAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(HOVER_PREFIX)) {
					final var temp = attribute.getCopy();
					temp.setHeader(attribute.getHeader().substring(HOVER_PREFIX.length()));
					getRefHoverLook().addOrChangeAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(FOCUS_PREFIX)) {
					final var temp = attribute.getCopy();
					temp.setHeader(attribute.getHeader().substring(FOCUS_PREFIX.length()));
					getRefFocusLook().addOrChangeAttribute(temp);
				}
				else if (attribute.getHeader().startsWith(HOVER_FOCUS_PREFIX)) {
					final var temp = attribute.getCopy();
					temp.setHeader(attribute.getHeader().substring(HOVER_FOCUS_PREFIX.length()));
					getRefFocusLook().addOrChangeAttribute(temp);
				}
				else {
				
					//Calls method of the base class.
					super.addOrChangeAttribute(attribute);
				}
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
		
		//For a better performance, this implementation does not use all comfortable methods.
		baseLookMutator.run(baseLook);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Applies the given focus look mutator to the focus look of the current {@link Widget}.
	 * 
	 * @param focusLookMutator
	 * @return the current {@link Widget}.
	 */
	public final W applyOnFocusLook(final IElementTaker<WL> focusLookMutator) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		focusLookMutator.run(focusLook);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Applies the given hover focus look mutator to the hover focus look of the current {@link Widget}.
	 * 
	 * @param hoverFocusLookMutator
	 * @return the current {@link Widget}.
	 */
	public final W applyOnHoverFocusLook(final IElementTaker<WL> hoverFocusLookMutator) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		hoverFocusLookMutator.run(hoverFocusLook);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Applies the given hover look mutator to the hover look of the current {@link Widget}.
	 * 
	 * @param hoverLookMutator
	 * @return the current {@link Widget}.
	 */
	public final W applyOnHoverLook(final IElementTaker<WL> hoverLookMutator) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		hoverLookMutator.run(hoverLook);
		
		return asConcreteType();
	}

	//method
	/**
	 * @return true if the current {@link Widget} belongs to a GUI.
	 */
	public final boolean belongsToGUI() {
		
		if (parentGUI != null) {
			return true;
		}
		
		if (belongsToWidget()) {
			return getParentWidget().belongsToGUI();
		}
		
		return false;
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} belongs to a {@link Widget}.
	 */
	public final boolean belongsToWidget() {
		return (parentWidget != null);
	}
	
	//method
	/**
	 * @param GUI
	 * @return true if the current {@link Widget} belongs to the given GUI.
	 * @throws NullArgumentException if the given GUI is null.
	 */
	public final boolean belongsToGUI(final IGUI<?> GUI) {
		
		//Checks if the given GUI is not null.
		Validator.suppose(GUI).isNotNull();
		
		return (this.parentGUI != GUI);
	}
	
	//method
	/**
	 * @param xPosition
	 * @param yPosition
	 * @return true if the current {@link Widget} covers the point with the given x-position and y-position.
	 */
	public final boolean coversPoint(final int xPosition, final int yPosition) {
		
		//For a better performance, this implementation does the cheap comparisons at first.
		return
		xPosition > 0
		&& yPosition > 0
		&& xPosition <= getWidth()
		&& yPosition <= getHeight();
	}
	
	//method
	/**
	 * @param xPositionOnGUI
	 * @param yPositionOnGUI
	 * @return true if the current {@link Widget}
	 * covers the point with the given x-position on GUI and y-position on GUI.
	 */
	public final boolean coversPointOnGUI(final int xPositionOnGUI, final int yPositionOnGUI) {
		
		//For a better performance, this implementation does the cheap comparisons at first.
		//For a better performance, this implementation does the cheap comparisons at first.
			final var thisXPositionOnGUI = getXPositionOnGUI();
			
			if (xPositionOnGUI < thisXPositionOnGUI || xPositionOnGUI >= thisXPositionOnGUI + getWidth()) {
				return false;
			}
			
			final var thisYPositionOnGUI = getYPositionOnGUI();
			
			if (yPositionOnGUI < thisYPositionOnGUI || yPositionOnGUI >= thisYPositionOnGUI + getHeight()) {
				return false;
			}
			
			return true;
	}
	
	//method
	/**
	 * @param xPositionOnParent
	 * @param yPositionOnParent
	 * @return true if the current {@link Widget}
	 * covers the point with the given x-position on parent and y-position on parent.
	 */
	public final boolean coversPointOnParent(final int xPositionOnParent, final int yPositionOnParent) {
		
		//For a better performance, this implementation does not use all comfortable methods.
		//For a better performance, this implementation does the cheap comparisons at first.
		return
		xPositionOnParent >= this.xPositionOnParent
		&& yPositionOnParent >= this.yPositionOnParent
		&& xPositionOnParent < this.xPositionOnParent + getWidth()
		&& yPositionOnParent < this.yPositionOnParent + getHeight();
	}
	
	//method
	/**
	 * @return the attributes of the current {@link Widget}.
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class.
		final List<DocumentNode> attributes = super.getAttributes();
		
		attributes.addAtBegin(getInteractionAttributes());
		
		//Handles the case that the custom cursor icon of the current widget is not a arrow.
		if (getCustomCursorIcon() != CursorIcon.Arrow) {
			attributes.addAtEnd(getCustomCursorIcon().getSpecification());
		}
		
		//Handles the case that the current widget has a left mouse button press command.
		if (hasLeftMouseButtonPressCommand()) {
			attributes.addAtEnd(
				new DocumentNode(
					LEFT_MOUSE_BUTTON_PRESS_COMMAND_HEADER,
					leftMouseButtonPressCommand.toString()
				)
			);
		}
		
		//Handles the case that the current widget has a left mouse button release command.
		if (hasLeftMouseButtonReleaseCommand()) {
			attributes.addAtEnd(
				new DocumentNode(
					LEFT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER,
					leftMouseButtonReleaseCommand.toString()
				)
			);
		}
		
		//Handles the case that the current widget has a right mouse button press command.
		if (hasRightMouseButtonPressCommand()) {
			attributes.addAtEnd(
				new DocumentNode(
					RIGHT_MOUSE_BUTTON_PRESS_COMMAND_HEADER,
					rightMouseButtonPressCommand.toString()
				)
			);
		}
		
		//Handles the case that the current widget has a right mouse button release command.
		if (hasRightMouseButtonReleaseCommand()) {
			attributes.addAtEnd(
				new DocumentNode(
					RIGHT_MOUSE_BUTTON_RELEASE_COMMAND_HEADER,
					rightMouseButtonReleaseCommand.toString()
				)
			);
		}
		
		//Handles the case that the current widget does not grey out when it is disabled.
		if (!greysOutWhenDisabled()) {
			attributes.addAtEnd(DocumentNode.createFromString(NO_GREY_OUT_WHEN_DISABLED_HEADER));
		}
	
		//Extracts the normal state attributes of the current widget.
		final List<DocumentNode> normalStateAttributes = getRefBaseLook().getAttributes();
		normalStateAttributes.forEach(a -> a.addPrefixToHeader(BASE_PREFIX));
		attributes.addAtEnd(normalStateAttributes);
		
		//Extracts the hover state attributes of the current widget.
		final List<DocumentNode> hoverStateAttributes = getRefHoverLook().getAttributes();
		hoverStateAttributes.forEach(a -> a.addPrefixToHeader(HOVER_PREFIX));
		attributes.addAtEnd(hoverStateAttributes);
		
		//Extracts focus state attributes of the current widget.
		final List<DocumentNode> focusStateAttributes = getRefFocusLook().getAttributes();
		focusStateAttributes.forEach(a -> a.addPrefixToHeader(FOCUS_PREFIX));
		attributes.addAtEnd(focusStateAttributes);
		
		return attributes;
	}
	
	//method
	/** 
	 * @return all child {@link Widget} of the current {@link Widget}.
	 */
	public final List<Widget<?, ?>> getChildWidgets() {
		
		final var childWidgets = new List<Widget<?, ?>>();
		fillUpChildWidgets(childWidgets);
		
		return childWidgets;
	}
	
	//method
	/** 
	 * @return all configurable child {@link Widget} of the current {@link Widget}.
	 */
	public final List<Widget<?, ?>> getConfigurableChildWidgets() {
		
		final var configurableChildWidgets = new List<Widget<?, ?>>();
		fillUpConfigurableChildWidgets(configurableChildWidgets);
		
		return configurableChildWidgets;
	}
	
	//abstract method
	/**
	 * @return the cursor icon of the current {@link Widget}.
	 */
	public CursorIcon getCursorIcon() {
		
		final var childWidgetUnderCursor = getTriggerableChildWidgets().getRefFirstOrNull(cw -> cw.isUnderCursor());
		
		if (childWidgetUnderCursor != null) {
			return childWidgetUnderCursor.getCursorIcon();
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
	 * @return the custom cursor icon of the current {@link Widget}.
	 */
	public final CursorIcon getCustomCursorIcon() {
		return customCursorIcon;
	}
	
	//method
	/**
	 * @return the height of the current {@link Widget}.
	 */
	public final int getHeight() {
		return height;
	}
	
	//method
	/**
	 * @return the index of the current {@link Widget} on its {@link IGUI}.
	 * @throws InvalidArgumentException if the current {@link Widget} does not belong to a {@link IGUI}.
	 */
	public final int getIndexOnGUI() {
		return getParentGUI().getRefWidgetsRecursively().getIndexOf(this);
	}
	
	//method
	/**
	 * Example: index path of a {@link Widget}
	 * -Lets a {@link IGUI} A contains a {@link WidgetGUI} B.
	 * -Lets B contain a {@link WidgetGUI} C.
	 * -Lets C contain a {@link Widget} D.
	 * ->The path of D is 'A.B.C.D'.
	 * 
	 * @return the index path of the current {@link Widget} on its root {@link IGUI}.
	 * @throws InvalidArgumentException if the current {@link Widget} does not belong to a {@link IGUI}.
	 */
	@SuppressWarnings("unchecked")
	public final List<Integer> getIndexPathOnRootGUI() {
		
		//Handles the case that the GUI, the current widget belongs to, is not a root GUI.
		if (getParentGUI().isRootGUI()) {
			return new List<Integer>(getIndexOnGUI());
		}
		
		//Handles the case that the GUI, the current widget belongs to, is a root GUI.
		return
		getParentGUI()
		.as(Widget.class)
		.getIndexPathOnRootGUI()
		.addAtEnd(getIndexOnGUI());
	}
	
	//method
	/**
	 * The interaction attributes of a {@link Widget} are those a user can change.
	 * 
	 * @return the interaction attributes of the current {@link Widget}.
	 */
	public List<DocumentNode> getInteractionAttributes() {
		return
		new List<DocumentNode> (
			getState().getSpecificationAs(STATE_HEADER)
		);
	}
	
	//method
	/**
	 * @return the GUI the current {@link Widget} belongs to.
	 * @throws InvalidArgumentException if the current {@link Widget} does not belong to a GUI.
	 */
	public final IGUI<?> getParentGUI() {
		
		if (parentGUI == null) {
			
			if (parentWidget == null) {
				throw new InvalidArgumentException(this, "does not belong to a GUI");
			}
			
			parentGUI = getParentWidget().getParentGUI();
		}
		
		return parentGUI;
	}
	
	//method
	/**
	 * @return the {@link Widget} the current {@link Widget} belongs to.
	 * @throws InvalidArgumentException if the current {@link Widget} does not belong to a {@link Widget}.
	 */
	public final Widget<?, ?> getParentWidget() {
		
		//Checks if the current widget belongs to a Widget.
		supposeBelongsToWidget();
		
		return parentWidget;
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
	 * @return the configurable elements of the current {@link Widget}.
	 */
	@Override
	public final List<Configurable<?>> getRefConfigurables() {
		
		final var configurables = new List<Widget<?, ?>>();
		fillUpConfigurableChildWidgets(configurables);
		
		return new List<Configurable<?>>(configurables);
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
	 * @return the hover focus structure of the current {@link Widget}.
	 */
	public final WL getRefHoverFocusLook() {
		return hoverFocusLook;
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
	 * @return the state of the current {@link Widget}.
	 */
	public final WidgetState getState() {
		return state;
	}
	
	//method
	/**
	 * @return the triggerable child {@link Widget}s of the current {@link Widget}.
	 */
	public final List<Widget<?, ?>> getTriggerableChildWidgets() {
		
		final var widgets = new List<Widget<?, ?>>();
		
		fillUpTriggerableChildWidgets(widgets);
		
		return widgets;
	}
	
	//method
	/**
	 * @return the triggerable child {@link Widget}s of the current {@link Widget}.
	 */
	public final List<Widget<?, ?>> getTriggerableChildWidgetsRecursively() {
		
		final var widgets = new List<Widget<?, ?>>();
		
		fillUpTriggerableChildWidgetsRecursively(widgets);
		
		return widgets;
	}
	
	//method
	/**
	 * @return the width of the current {@link Widget}.
	 */
	public final int getWidth() {
		return width;
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} greys out when it is disabled.
	 */
	public final boolean greysOutWhenDisabled() {
		return greyOutWhenDisabled;
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
		return (getState() == WidgetState.Collapsed);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is disabled.
	 */
	public final boolean isDisabled() {
		return (getState() == WidgetState.Disabled);
	}
	
	//method
	/**
	 * A {@link Widget} is enables when it is normal, hovered, focused or hover focused.
	 * 
	 * @return true if the current {@link Widget} is enabled.
	 */
	public final boolean isEnabled() {
		return (isNormal() || isHovered() || isFocused() || isHoverFocused());
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is focused.
	 */
	public final boolean isFocused() {
		return (getState() == WidgetState.Focused);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is hovered.
	 */
	public final boolean isHovered() {
		return (getState() == WidgetState.Hovered);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is hover-focused.
	 */
	public final boolean isHoverFocused() {
		return (getState() == WidgetState.HoverFocused);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is normal.
	 */
	public final boolean isNormal() {
		return (getState() == WidgetState.Normal);
	}
	
	//method
	/**
	 * @return true if the current {@link Widget} is under the cursor.
	 */
	public final boolean isUnderCursor() {
		return coversPoint(cursorXPosition, cursorYPosition);
	}
	
	//abstract method
	/**
	 * @return true if the current {@link Widget} keeps the focus.
	 */
	public final boolean keepsFocus() {
		return keepsFocus;
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any key press.
	 * 
	 * @param keyEvent
	 */
	public final void noteAnyKeyPress(final KeyEvent keyEvent) {
		
		//Handles the case that the current Widget is focused or hover focused.
		if (isFocused() || isHoverFocused()) {
			noteKeyPress(keyEvent);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any key typing.
	 * 
	 * @param keyEvent
	 */
	public final void noteAnyKeyTyping(final KeyEvent keyEvent) {
		
		//Handles the case that the current Widget is focused or hover focused.
		if (isFocused() || isHoverFocused()) {
			noteKeyTyping(keyEvent);
		}
	}

	//method
	/**
	 * Lets the current {@link Widget} note any mouse button press.
	 */
	public final void noteAnyLeftMouseButtonPress() {			
		if (isEnabled()) {
			if (!isUnderCursor()) {
				switch (getState()) {
					case Focused:
					case HoverFocused:
						
						if (!keepsFocus()) {
							setNormal();
						}
						
						break;
					default:
				}
			}
			else {
				
				if (isNormal()) {
					setHoverFocused();
				}
				
				else if (isHovered()) {
					setHoverFocused();
				}
				
				else if (isFocused()) {
					setHoverFocused();
				}
				
				noteLeftMouseButtonPress();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any mouse button press recursively.
	 */
	public final void noteAnyLeftMouseButtonPressRecursively() {
		
		noteAnyLeftMouseButtonPress();
		
		getTriggerableChildWidgets().forEach(w -> w.noteAnyLeftMouseButtonPress());
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any mouse button release.
	 */
	public void noteAnyLeftMouseButtonRelease() {
		if (isEnabled()) {
			if (!isUnderCursor()) {
				switch (getState()) {
					case Focused:
					case HoverFocused:
						
						if (!keepsFocus()) {
							setNormal();
						}
						
						break;
					default:
				}
			}
			else {
				noteLeftMouseButtonRelease();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any mouse move.
	 */
	public void noteAnyMouseMove() {
		if (isEnabled()) {
			if (!isUnderCursor()) {
				
				if (isHovered()) {
					setNormal();
				}
				
				else if (isFocused()) {
					noteMouseMove();
				}
				
				else if (isHoverFocused()) {
					setFocused();
				}
			}
			else {
				
				if (isNormal()) {
					setHovered();
				}
				
				else if (isFocused()) {
					setHoverFocused();
				}
				
				noteMouseMove();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any mouse move recursively.
	 */
	public final void noteAnyMouseMoveRecursively() {
		
		noteAnyMouseMove();
		
		getTriggerableChildWidgetsRecursively().forEach(w -> w.noteAnyMouseMove());
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note the given mouse wheel rotation steps.
	 * The given number of mouse wheel rotation steps is positive if the mouse wheel was rotated forward.
	 * The given number mouse wheel rotation steps is negative if the mouse wheel was rotated backward.
	 * 
	 * @param rotationSteps
	 */
	public void noteAnyMouseWheelRotationSteps(final int mouseWheelRotationSteps) {
		if (isEnabled() && isUnderCursor()) {
			noteMouseWheelRotationSteps(mouseWheelRotationSteps);
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any right mouse button press.
	 */
	public final void noteAnyRightMouseButtonPress() {
		if (isEnabled() && isUnderCursor()) {
			noteRightMouseButtonPress();
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note any right mouse button release.
	 */
	public final void noteAnyRightMouseButtonRelease() {
		if (isEnabled()) {
			if (isUnderCursor()) {
				noteRightMouseButtonRelease();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key press.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyPress(final KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a key typing.
	 * 
	 * @param keyEvent
	 */
	public void noteKeyTyping(final KeyEvent keyEvent) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
		//Handles the case that the cursor is under the view area of the current widget.
		if (viewAreaIsUnderCursor()) {
			
			//Handles the case that the current widget has a left mouse button release command.
			if (hasLeftMouseButtonPressCommand()) {
				
				leftMouseButtonPressCommand.run();
				
				//Handles the case that the GUI the current widget belongs to has a controller.
				if (getParentGUI().hasController()) {
					getParentGUI().getRefController().noteLeftMouseButtonPressCommand(this);
				}
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a left mouse button release.
	 */
	public void noteLeftMouseButtonRelease() {
		
		//Handles the case that the cursor is under the view area of the current widget.
		if (viewAreaIsUnderCursor()) {
			
			//Handles the case that the current widget has a left mouse button release command.
			if (hasLeftMouseButtonReleaseCommand()) {
				
				leftMouseButtonReleaseCommand.run();
				
				//Handles the case that the GUI the current widget belongs to has a controller.
				if (getParentGUI().hasController()) {
					getParentGUI().getRefController().noteLeftMouseButtonReleaseCommand(this);
				}
			}
		}
		
		if (!isUnderCursor()) {
			if (!keepsFocus()) {
				setNormal();
			}
		}
	}
	
	//method
	/**
	 * Lets the current {@link Widget} note a mouse move.
	 */
	public void noteMouseMove() {}
	
	//method
	/**
	 * Lets this GUI note the given mouse wheel rotation steps.
	 * The given number of mouse wheel rotation steps is positive if the mouse wheel was rotated forward.
	 * The given number mouse wheel rotation steps is negative if the mouse wheel was rotated backward.
	 * 
	 * @param rotationSteps
	 */
	public void noteMouseWheelRotationSteps(final int mouseWheelRotationSteps) {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button press.
	 */
	public void noteRightMouseButtonPress() {}
	
	//method
	/**
	 * Lets the current {@link Widget} note a right mouse button release.
	 */
	public void noteRightMouseButtonRelease() {}
	
	//method
	/**
	 * Paints the current {@link Widget} using the position on its parent container using the given painter.
	 * This method promises that the given painter has the same position at the end as at the beginning.
	 * 
	 * @param painter
	 */
	public final void paint(final IPainter painter) {
		paint2(
			painter.createPainter(
				getXPositionOnParent(),
				getYPositionOnParent()
			)
		);
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	public void recalculate() {
		width = calculatedWidth();
		height = calculatedHeight();
	}
	
	//method
	/**
	 * Recalculates the {@link Widget} recursively.
	 */
	public final void recalculateRecursively() {
		
		getChildWidgets().forEach(cw -> cw.recalculateRecursively());
		
		recalculate();
	}
	
	//method
	/**
	 * Avoids that the current widget greys out when it is disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W removeGreyOutWhenDisabled() {
		
		greyOutWhenDisabled = false;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the left mouse button press command of the current {@link Widget}.
	 */
	public final void removeLeftMouseButtonPressCommand() {
		leftMouseButtonPressCommand = null;
	}
	
	//method
	/**
	 * Removes the left mouse button release command of the current {@link Widget}.
	 */
	public final void removeLeftMouseButtonReleaseCommand() {
		leftMouseButtonReleaseCommand = null;
	}
	
	//method
	/**
	 * Removes the right mouse button press command of the current {@link Widget}.
	 */
	public final void removeRightMouseButtonPressCommand() {
		rightMouseButtonPressCommand = null;
	}
	
	//method
	/**
	 * Removes the right mouse button release command of the current {@link Widget}.
	 */
	public final void removeRightMouseButtonReleaseCommand() {
		rightMouseButtonReleaseCommand = null;
	}
	
	//method
	/**
	 * Resets the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	@Override
	public W reset() {
		
		//Calls method of the base class.
		super.reset();
		
		setNormal();
		setGreyOutWhenDisabled();
		
		removeLeftMouseButtonPressCommand();
		removeLeftMouseButtonReleaseCommand();
		removeRightMouseButtonPressCommand();
		removeRightMouseButtonReleaseCommand();
		
		resetConfiguration();
		
		return asConcreteType();
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
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 */
	@Override
	public W resetConfiguration() {
		
		getRefBaseLook().reset();
		getRefHoverLook().reset();
		getRefFocusLook().reset();
		
		setCustomCursorIcon(CursorIcon.Arrow);
		
		//Resets the configuration of the widgets of the current widget.
		getChildWidgets().forEach(r -> r.resetConfiguration());
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Runs the left mouse button press command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a left mouse button press command.
	 */
	public final W runLeftMouseButtonPressCommand() {
		
		getLeftMouseButtonPressCommand().run();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Runs the left mouse button release command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a left mouse button release command.
	 */
	public final W runLeftMouseButtonReleaseCommand() {
		
		getLeftMouseButtonReleaseCommand().run();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Runs the right mouse button press command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a right mouse button press command.
	 */
	public final W runRightMouseButtonPressCommand() {
		
		getRightMouseButtonPressCommand().run();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Runs the right mouse button release command of the current {@link Widget}.
	 * 
	 * @return the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a right mouse button release command.
	 */
	public final W runRightMouseButtonReleaseCommand() {
		
		getRightMouseButtonReleaseCommand().run();
		
		return asConcreteType();
	}

	//method
	/**
	 * Sets the current {@link Widget} collapsed.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setCollapsed() {
		
		state = WidgetState.Collapsed;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the custom cursor icon of the current {@link Widget}.
	 * 
	 * @param customCursorIcon
	 * @throws NullArgumentException if the given custom cursor icon is null.
	 */
	public final W setCustomCursorIcon(final CursorIcon customCursorIcon) {
		
		//Checks if the given custom cursor icon is not null.
		Validator
		.suppose(customCursorIcon)
		.thatIsNamed("custom cursor icon")
		.isNotNull();
		
		//Sets the custom cursor icon of the current widget.
		this.customCursorIcon = customCursorIcon;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the position of the cursor to the current {@link Widget}.
	 * 
	 * @param cursorXPosition
	 * @param cursorYPosition
	 */
	public void setCursorPosition(final int cursorXPosition, final int cursorYPosition) {
		
		this.cursorXPosition = cursorXPosition;
		this.cursorYPosition = cursorYPosition;
		
		getTriggerableChildWidgets().forEach(cw -> cw.setParentCursorPosition(cursorXPosition, cursorYPosition));
	}
	
	//method
	/**
	 * Sets the cursor position of the parent of the current {@link Widget}.
	 * 
	 * @param parentCursorXPosition
	 * @param parentCursorYPosition
	 */
	public final void setParentCursorPosition(
		final int parentCursorXPosition,
		final int parentCursorYPosition
	) {
		setCursorPosition(parentCursorXPosition - xPositionOnParent, parentCursorYPosition - yPositionOnParent);
	}
	
	//method
	/**
	 * Sets the current {@link Widget} disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setDisabled() {
		
		state = WidgetState.Disabled;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} focused.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setFocused() {
		
		state = WidgetState.Focused;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Lets the current {@link Widget} grey out when it is disabled.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setGreyOutWhenDisabled() {
		
		greyOutWhenDisabled = true;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} hovered.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setHovered() {
		
		state = WidgetState.Hovered;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} hover-focused.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setHoverFocused() {
		
		state = WidgetState.HoverFocused;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets that the current {@link widget} keeps the focus.
	 * 
	 * @return the current {@link widget}.
	 */
	public final W setKeepsFocus() {
		
		keepsFocus = true;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the left mouse button press command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonPressCommand
	 * @return the current {@link Widget}.
	 * @throws NullArgumentException if the given left mouse button press command is null.
	 */
	public final W setLeftMouseButtonPressCommand(final IFunction leftMouseButtonPressCommand) {
		
		//Checks if the given left mouse button press command is not null.
		Validator
		.suppose(leftMouseButtonPressCommand)
		.thatIsNamed("left mouse button press command")
		.isNotNull();
		
		this.leftMouseButtonPressCommand = leftMouseButtonPressCommand;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the left mouse button release command of the current {@link Widget}.
	 * 
	 * @param leftMouseButtonReleaseCommand
	 * @return the current {@link Widget}.
	 * @throws NullArgumentException if the given left mouse button release command is null.
	 */
	public final W setLeftMouseButtonReleaseCommand(final IFunction leftMouseButtonReleaseCommand) {
		
		//Checks if the given left mouse button release command is not null.
		Validator
		.suppose(leftMouseButtonReleaseCommand)
		.thatIsNamed("left mouse button release command")
		.isNotNull();
		
		this.leftMouseButtonReleaseCommand = leftMouseButtonReleaseCommand;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the right mouse button press command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonPressCommand
	 * @return the current {@link Widget}.
	 * @throws NullArgumentException if the given right mouse button press command is null.
	 */
	public final W setRightMouseButtonPressCommand(final IFunction rightMouseButtonPressCommand) {
		
		//Checks if the given right mouse button press command is not null.
		Validator
		.suppose(rightMouseButtonPressCommand)
		.thatIsNamed("right mouse button press command")
		.isNotNull();
		
		this.rightMouseButtonPressCommand = rightMouseButtonPressCommand;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the right mouse button release command of the current {@link Widget}.
	 * 
	 * @param rightMouseButtonReleaseCommand
	 * @return the current {@link Widget}.
	 * @throws NullArgumentException if the given right mouse button release command is null.
	 */
	public final W setRightMouseButtonReleaseCommand(final IFunction rightMouseButtonReleaseCommand) {
		
		//Checks if the given right mouse button release command is not null.
		Validator
		.suppose(rightMouseButtonReleaseCommand)
		.thatIsNamed("right mouse button release command")
		.isNotNull();
		
		this.rightMouseButtonReleaseCommand = rightMouseButtonReleaseCommand;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the current {@link Widget} normal.
	 * 
	 * @return the current {@link Widget}.
	 */
	public final W setNormal() {
		
		state = WidgetState.Normal;
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the state of the current {@link Widget}.
	 * 
	 * @param state
	 * @throws NullArgumentException if the given state is null.
	 */
	public final W setState(final WidgetState state) {
		
		//Checks if the given state is not null.
		Validator.suppose(state).thatIsNamed("state").isNotNull();

		//Sets the state of the current {@link Widget}.
		this.state = state;
		
		return asConcreteType();
	}
	
	//abstract method
	/**
	 * Applies a usable configuration to the current {@link Widget}
	 * when the configuration of the current {@link Widget} has been reset.
	 */
	protected abstract void applyDefaultConfigurationWhenHasBeenReset();
	
	//abstract method
	/**
	 * @return a new widget look for the current {@link Widget}.
	 */
	protected abstract WL createWidgetLook();
	
	//abstract method
	/**
	 * Fills up all child {@link Widget} of the current {@link Widget} into the given list.
	 * 
	 * For a better performance, a {@link Widget} fills up all its child {@link Widget} into a list
	 * and does not create a new list.
	 * 
	 * @param list
	 */
	protected abstract void fillUpChildWidgets(List<Widget<?, ?>> list);
	
	//abstract method
	/**
	 * Fills up all configurable child {@link Widget} of the current {@link Widget} into the given list.
	 * 
	 * For a better performance, a {@link Widget} fills up all its configurable child {@link Widget} into a list
	 * and does not create a new list.
	 * 
	 * @param list
	 */
	protected abstract void fillUpConfigurableChildWidgets(List<Widget<?, ?>> list);
	
	//method
	/**
	 * Fills up the triggerable child {@link Widget}s of the current {@link Widget} into the given list.
	 * 
	 * For a better performance, a {@link Widget} fills up its triggerable child {@link Widget}s into a list
	 * and does not create a new list.
	 * 
	 * @param list
	 */
	protected void fillUpTriggerableChildWidgets(final List<Widget<?, ?>> list) {
		fillUpChildWidgets(list);
	}
	
	//protected abstract void fillUpShownWidgets(final List<Widget<?, ?>> list);
	
	//abstract method
	/**
	 * @return the height of the current {@link Widget} when it is s not collapsed.
	 */
	protected abstract int getHeightWhenNotCollapsed();
	
	//method
	/**
	 * @return the current look of the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget} does not have a current look.
	 */
	protected final WL getRefCurrentLook() {
		
		//Enumerates the state of the current {@link Widget}.
		switch (getState()) {
			case Normal:
			case Disabled:
			case Collapsed:
				return getRefBaseLook();
			case Hovered:
				return getRefHoverLook();
			case Focused:
				return getRefFocusLook();
			case HoverFocused:
				return getRefHoverFocusLook();
			default:
				throw new ArgumentMissesAttributeException(
					this,
					"current look"
				);
		}
	}
	
	//method
	/**
	 * @return the own widgets of the current {@link Widget} recursively.
	 */
	public final List<Widget<?, ?>> getChildWidgetsRecursively() {
		
		final var widgets = new List<Widget<?, ?>>();
		
		fillUpChildWidgetsRecursively(widgets);
		
		return widgets;
	}
	
	//abstract method
	/**
	 * @return the width of the current {@link Widget} when it is not collapsed.
	 */
	protected abstract int getWidthWhenNotCollapsed();
	
	//method
	/**
	 * @return the x-position of the current {@link Widget} on the {@link IGUI} it belongs to.
	 */
	protected final int getXPositionOnGUI() {
		
		if (!belongsToWidget()) {
			return getXPositionOnParent();
		}
		
		return (getParentWidget().getXPositionOnGUI() + getXPositionOnParent());
	}
	
	//method
	/**
	 * @return the x-position of the current {@link Widget} on its parent container.
	 */
	protected final int getXPositionOnParent() {
		return xPositionOnParent;
	}
	
	//method
	/**
	 * @return the y-position of the current {@link Widget} on the {@link IGUI} it belongs to.
	 */
	protected final int getYPositionOnGUI() {
		
		if (!belongsToWidget()) {
			return getYPositionOnParent();
		}
		
		return (getParentWidget().getYPositionOnGUI() + getYPositionOnParent());
	}
	
	//method
	/**
	 * @return the relative y-position of the current {@link Widget} on its parent container.
	 */
	public final int getYPositionOnParent() {
		return yPositionOnParent;
	}
	
	//method
	/**
	 * Paints the current {@link Widget} using the given painter.
	 * 
	 * @param painter
	 */
	protected final void paint2(final IPainter painter) {
		
		paint3(painter);
		
		//Handles the case that the current widget is disabled and would grey out.
		if (isDisabled() && greysOutWhenDisabled()) {
			painter.setColor(Color.GREY);
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
	}
	
	protected void paint3(final IPainter painter) {
		
		paint(getRefCurrentLook(), painter);
		
		getChildWidgets().forEach(cw ->  cw.paint(painter));
	}
	
	//abstract method
	/**
	 * Paints the current {@link Widget} using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	protected abstract void paint(final WL widgetStructure, final IPainter painter);
	
	//method
	/**
	 * Sets the {@link Widget} the current {@link Widget} will belong to.
	 * 
	 * @param parentWidget
	 * @throws NullArgumentException if the given parent widget is null.
	 */
	public final void setParentWidget(final Widget<?, ?> parentWidget) {
		
		//Checks if the given parent widget is not null.
		Validator
		.suppose(parentWidget)
		.thatIsNamed("parent widget")
		.isNotNull();
		
		//Sets the parent widget of the current widget.
		this.parentWidget = parentWidget;
	}
	
	//method
	/**
	 * Sets the position of the current {@link Widget} on its parent.
	 * 
	 * @param xPositionOnParent
	 * @param yPositionOnParent
	 */
	public final void setPositionOnParent(
		final int xPositionOnParent,
		final int yPositionOnParent
	) {				
		this.xPositionOnParent = xPositionOnParent;
		this.yPositionOnParent = yPositionOnParent;
	}
	
	//method
	/**
	 * @throws ClosedArgumentException if the current {@link Widget} belongs to a GUI that is closed.
	 */
	protected final void supposeGUIIsAlive() {
		if (belongsToGUI() && getParentGUI().isClosed()) {
			throw new ClosedArgumentException(getParentGUI());
		}
	}
	
	//abstract method
	/**
	 * @return true if the view are of the current {@link Widget} is under the cursor.
	 */
	protected abstract boolean viewAreaIsUnderCursor();
	
	//method
	/**
	 * Sets the GUI the current {@link Widget} will belong to.
	 * 
	 * @param parentGUI
	 * @throws NullArgumentException if the given parentGUI is null.
	 */
	public final void setParentGUI(final IGUI<?> parentGUI) {
		
		//Checks if the given parentGUI is not null.
		Validator.suppose(parentGUI).thatIsNamed("parent GUI").isNotNull();
		
		//Checks if the given parentGUI does not contain already a Widget with the same name than the current Widget.
		if (parentGUI.containsElement(getName())) {
			throw
			new InvalidArgumentException(
				"parent GUI",
				parentGUI,
				"contains already a Widget with the name " + getNameInQuotes()
			);
		}
				
		//Sets the parentGUI of the current Widget.
		this.parentGUI = parentGUI;
		
		//Sets the parentGUI of the child Widgets of the current Widget.
		getChildWidgets().forEach(cw -> cw.setParentGUI(parentGUI));
	}
	
	//method
	/**
	 * @return the newly calculated height of the current {@link Widget}.
	 */
	private int calculatedHeight() {
		
		//Handles the case that the current widget is collapsed.
		if (isCollapsed()) {
			return 0;
		}
		
		//Handles the case that the current widget is not collapsed.
		return getHeightWhenNotCollapsed();
	}
	
	//method
	/**
	 * @return the newly calculated width of the current {@link Widget}.
	 */
	private int calculatedWidth() {
		
		//Handles the case that the current widget is collapsed.
		if (isCollapsed()) {
			return 0;
		}
		
		//Handles the case that the current widget is not collapsed.
		return getWidthWhenNotCollapsed();
	}
	
	//method
	/**
	 * Fills up recursively the own widgets of the current {@link Widget} into the given list.
	 * 
	 * For a better performance,
	 * a {@link Widget} fills up recursively its own widgets into a list
	 * and does not create a new list with its own widgets.
	 * 
	 * @param list
	 */
	private void fillUpChildWidgetsRecursively(final List<Widget<?, ?>> list) {
		fillUpChildWidgets(list);
		getChildWidgets().forEach(w -> w.fillUpChildWidgetsRecursively(list));
	}
	
	//method
	/**
	 * Fills up recursively the triggerable child {@link Widget}s of the current {@link Widget}.
	 * 
	 * For a better performance, a {@link Widget}
	 * fills up recursively it striggerable child {@link Widget}s into a list
	 * and does not create a new list with its triggerable child {@link Widget}s.
	 * 
	 * @param list
	 */
	private void fillUpTriggerableChildWidgetsRecursively(final List<Widget<?, ?>> list) {
		fillUpTriggerableChildWidgets(list);
		getTriggerableChildWidgets().forEach(w -> w.fillUpTriggerableChildWidgetsRecursively(list));
	}
	
	//method
	/**
	 * @return the left mouse button press command of the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a left mouse button press command.
	 */
	private IFunction getLeftMouseButtonPressCommand() {

		supposeHasLeftMouseButtonPressCommad();
		
		return leftMouseButtonPressCommand;
	}
	
	//method
	/**
	 * @return the left mouse button release command of the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a left mouse button release command.
	 */
	private IFunction getLeftMouseButtonReleaseCommand() {

		supposeHasLeftMouseButtonReleaseCommad();
		
		return leftMouseButtonReleaseCommand;
	}
	
	//method
	/**
	 * @return the right mouse button press command of the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a right mouse button press command.
	 */
	private IFunction getRightMouseButtonPressCommand() {

		supposeHasRightMouseButtonPressCommad();
		
		return rightMouseButtonPressCommand;
	}
	
	//method
	/**
	 * @return the right mouse button release command of the current {@link Widget}.
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a right mouse button release command.
	 */
	private IFunction getRightMouseButtonReleaseCommand() {

		supposeHasRightMouseButtonReleaseCommad();
		
		return rightMouseButtonReleaseCommand;
	}
	
	//method
	/**
	 * @throws InvalidArgumentException if the current {@link Widget} does not belong to a {@link Widget}.
	 */
	private void supposeBelongsToWidget() {
		
		//Checks if the current widget belongs to a widget.
		if (!belongsToWidget()) {
			throw new InvalidArgumentException(
				this,
				"does not belong to a widget"
			);
		}
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a left mouse button press command.
	 */
	private void supposeHasLeftMouseButtonPressCommad() {
		
		//Checks if the current widget has a left mouse button press command.
		if (!hasLeftMouseButtonPressCommand()) {
			throw 
			new ArgumentMissesAttributeException(
				this,
				"left mouse button press command"
			);
		}
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a left mouse button release command.
	 */
	private void supposeHasLeftMouseButtonReleaseCommad() {
		
		//Checks if the current widget has a left mouse button release command.
		if (!hasLeftMouseButtonReleaseCommand()) {
			throw 
			new ArgumentMissesAttributeException(
				this,
				"left mouse button release command"
			);
		}
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a right mouse button press command.
	 */
	private void supposeHasRightMouseButtonPressCommad() {
		
		//Checks if the current widget has a right mouse button press command.
		if (!hasRightMouseButtonPressCommand()) {
			throw 
			new ArgumentMissesAttributeException(
				this,
				"right mouse button press command"
			);
		}
	}
	
	//method
	/**
	 * @throws ArgumentMissesAttributeException if the current {@link Widget}
	 * does not have a right mouse button release command.
	 */
	private void supposeHasRightMouseButtonReleaseCommad() {
		
		//Checks if the current widget has a right mouse button release command.
		if (!hasRightMouseButtonReleaseCommand()) {
			throw 
			new ArgumentMissesAttributeException(
				this,
				"right mouse button release command"
			);
		}
	}
}
