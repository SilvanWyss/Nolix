//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.documentNode.DocumentNode;
import ch.nolix.core.documentNode.DocumentNodeoid;
import ch.nolix.core.entity.MutableOptionalProperty;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.math.Calculator;
import ch.nolix.core.invalidArgumentException.ArgumentMissesAttributeException;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.NonNegativeInteger;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.painter.IPainter;

//abstract class
/**
 * A border widget is a background widget that:
 * -Can have an individual border at each of its 4 sides.
 * -Can have a min size
 * that has the effect that the content of the border widget is moved inside its borders.
 * -Can have a max size
 * that has the effect that the border widget becomes scrollable.
 * 
 * A border widget consists of the following areas from outer to inner.
 * 1. widget area: Contains the probable shadows area and caption area.
 * 2. caption area: Contains the probable captions and main area.
 * 3. main area: Contains the probable borders and bordered area.
 * 4. bordered area: Contains the probable scrollbars and view area.
 * 5. view area: Is over the scroll area and is like a hole to look on the scroll area below.
 * 6. scroll area: Contains the probable paddings and content area.
 * 7. content area: Contains the content.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1910
 * @param <BW> The type of a border widget.
 * @param <BWS> The type of the widget structures of a border widget.
 */
public abstract class BorderWidget<
	BW extends BorderWidget<BW, BWS>,
	BWS extends BorderWidgetLook<BWS>
>
extends BackgroundWidget<BW, BWS> {
	
	//constants
	public static final String TYPE_NAME = "Borderablewidget";
	private static final int VIEW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP = 50;
	
	//limit
	private static final int MIN_SCROLL_CURSOR_LENGTH = 20;
	
	//constants
	private static final String MIN_WIDTH_HEADER = "MinWidth";
	private static final String MIN_HEIGHT_HEADER = "MinHeight";
	private static final String MAX_WIDTH_HEADER = "MaxWidth";
	private static final String MAX_HEIGHT_HEADER = "MaxHeight";
	private static final String PROPOSAL_WIDTH_HEADER = "ProposalWidth";
	private static final String PROPOSAL_HEIGHT_HEADER = "ProposalHeight";
	private static final String VIEW_AREA_X_POSITION_ON_SCROLL_AREA_HEADER = "ViewAreaXPositionOnScrollArea";
	private static final String VIEW_AREA_Y_POSITION_ON_SCROLL_AREA_HEADER = "ViewAreaYPositionOnScrollArea";
	
	//attribute
	private ContentPosition contentPosition;
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> minWidth =
	new MutableOptionalProperty<PositiveInteger>(
		MIN_WIDTH_HEADER,
		mw -> setMinWidth(mw.getValue()),
		s -> PositiveInteger.createFromSpecification(s),
		mw -> mw.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> minHeight =
	new MutableOptionalProperty<PositiveInteger>(
		MIN_HEIGHT_HEADER,
		mh -> setMinHeight(mh.getValue()),
		s -> PositiveInteger.createFromSpecification(s),
		mh -> mh.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> maxWidth =
	new MutableOptionalProperty<PositiveInteger>(
		MAX_WIDTH_HEADER,
		mw -> setMaxWidth(mw.getValue()),
		s -> PositiveInteger.createFromSpecification(s),
		mw -> mw.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> maxHeight =
	new MutableOptionalProperty<PositiveInteger>(
		MAX_HEIGHT_HEADER,
		mh -> setMaxHeight(mh.getValue()),
		s -> PositiveInteger.createFromSpecification(s),
		mh -> mh.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> proposalWidth =
	new MutableOptionalProperty<PositiveInteger>(
		PROPOSAL_WIDTH_HEADER,
		pw -> setProposalWidth(pw.getValue()),
		s -> PositiveInteger.createFromSpecification(s),
		pw -> pw.getSpecification()
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> proposalHeight =
	new MutableOptionalProperty<PositiveInteger>(
		PROPOSAL_HEIGHT_HEADER,
		ph -> setProposalHeight(ph.getValue()),
		s -> PositiveInteger.createFromSpecification(s),
		ph -> ph.getSpecification()
	);
	
	//attribute
	private final MutableProperty<NonNegativeInteger> viewAreaXPositionOnScrollArea =
	new MutableProperty<NonNegativeInteger>(
		VIEW_AREA_X_POSITION_ON_SCROLL_AREA_HEADER,
		x -> setViewAreaXPositionOnScrollArea(x.getValue()),
		s -> NonNegativeInteger.createFromSpecification(s),
		x -> x.getSpecification()
	);
	
	//attribute
	private final MutableProperty<NonNegativeInteger> viewAreaYPositionOnScrollArea =
	new MutableProperty<NonNegativeInteger>(
		VIEW_AREA_Y_POSITION_ON_SCROLL_AREA_HEADER,
		y -> setViewAreaYPositionOnScrollArea(y.getValue()),
		s -> NonNegativeInteger.createFromSpecification(s),
		y -> y.getSpecification()
	);
	
	//attributes
	private boolean isMovingVerticalScrollbarCursor = false;
	private boolean isMovingHorizontalScrollbarCursor = false;
	
	//optional attributes
	private int verticalScrollingCursorStartYPosition;
	private int horizontalScrollingCursorStartXPosition;
	
	//method
	/**
	 * Adds or changes the given attribute to the current {@link BorderWidget}.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	@Override
	public void addOrChangeAttribute(final DocumentNodeoid attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case ContentPosition.TYPE_NAME:
				
				setContentPosition(
					ContentPosition.createFromSpecification(attribute)
				);
				
				break;
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CursorIcon getCursorIcon() {
		
		if (horizontalScrollbarIsUnderCursor() || verticalScrollbarIsUnderCursor()) {
			return CursorIcon.Arrow;
		}
		
		return super.getCursorIcon();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DocumentNode> getAttributes() {
		
		//Calls method of the base class
		final List<DocumentNode> attributes = super.getAttributes();
		
		attributes.addAtEnd(contentPosition.getSpecification());

		return attributes;
	}
	
	//method
	/**
	 * @return the content position of the current {@link BorderWidget}.
	 */
	public final ContentPosition getContentPosition() {
		return contentPosition;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the scroll area of the current {@link BorderWidget}.
	 */
	public final int getCursorXPositionOnScrollArea() {
		return (getCursorXPosition() - getViewAreaXPositionOnScrollArea());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the scroll area of the current {@link BorderWidget}.
	 */
	public final int getCursorYPositionOnScrollArea() {
		return (getCursorYPosition() - getViewAreaYPositionOnScrollArea());
	}
	
	//method
	/**
	 * @return color of the horizontal scrollbar of the current {@link BorderWidget}.
	 */
	public final Color getHorizontalScrollbarColor() {
		
		final var currentLook = getRefCurrentLook();
		
		if (!isMovingHorizontalScrollbarCursor()) {
			if (!horizontalScrollbarCursorIsUnderCursor()) {
				return
				currentLook.getRecursiveOrDefaultBaseScrollbarLook().getScrollbarColor();
			}
			else {
				return
				currentLook.getRecursiveOrDefaultHoverScrollbarLook().getScrollbarColor();
			}
		}
		else {
			return
			currentLook.getRecursiveOrDefaultSelectionScrollbarLook().getScrollbarColor();
		}
	}
	
	//method
	/**
	 * @return color of the horizontal scrollbar cursor of the current {@link BorderWidget}.
	 */
	public final Color getHorizontalScrollbarCursorColor() {
		
		final var currentLook = getRefCurrentLook();
		
		if (!isMovingHorizontalScrollbarCursor()) {
			if (!horizontalScrollbarCursorIsUnderCursor()) {
				return
				currentLook.getRecursiveOrDefaultBaseScrollbarLook().getScrollbarCursorColor();
			}
			else {
				return
				currentLook.getRecursiveOrDefaultHoverScrollbarLook().getScrollbarCursorColor();
			}
		}
		else {
			return
			currentLook.getRecursiveOrDefaultSelectionScrollbarLook().getScrollbarCursorColor();
		}
	}
	
	//method
	/**
	 * @return the thickness of the horizontal scrollbar of the current {@link BorderWidget}.
	 */
	public final int getHorizontalScrollbarThickness() {
		
		//Handles the case that the current border widget does not have a horizontal scrollbar.
		if (!hasHorizontalScrollbar()) {
			return 0;
		}
		
		//Handles the case that the current border widget has a horizontal scrollbar.
		return 20;
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidget}.
	 * @throws ArgumentMissesAttributeException if the current {@link BorderWidget} does not have a max height.
	 */
	public final int getMaxHeight() {
		return maxHeight.getValue().getValue();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidget}.
	 * @throws ArgumentMissesAttributeException if the current {@link BorderWidget} does not have a max widt.
	 */
	public final int getMaxWidth() {
		return maxWidth.getValue().getValue();
	}
	
	//method
	/**
	 * @return the min height of the current {@link BorderWidget}.
	 * @throws ArgumentMissesAttributeException if the current {@link BorderWidget} does not have a min height.
	 */
	public final int getMinHeight() {
		return minHeight.getValue().getValue();
	}
	
	//method
	/**
	 * @return the min width of the current {@link BorderWidget}.
	 * @throws ArgumentMissesAttributeException if the current {@link BorderWidget} does not have a min width.
	 */
	public final int getMinWidth() {
		return minWidth.getValue().getValue();
	}
	
	//method
	/**
	 * The natural height of a border widget is its height when the border widget:
	 * -Is not collapsed.
	 * -Does not have a min height.
	 * -Does not have a max height.
	 * 
	 * @return the natural height of the current {@link BorderWidget}.
	 */
	public final int getNaturalHeight() {
		
		final var currentStructure = getRefCurrentLook();
		
		return
		currentStructure.getRecursiveOrDefaultTopBorderThickness()
		+ getNaturalScrollAreaHeight()
		+ getHorizontalScrollbarThickness()
		+ currentStructure.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * The natural width of a border widget is its width when the border widget:
	 * -Is not collapsed.
	 * -Does not have a min width.
	 * -Does not have a max width.
	 * 
	 * @return the natural width of the current {@link BorderWidget}.
	 */
	public final int getNaturalWidth() {
		
		final var currentStructure = getRefCurrentLook();
		
		return
		currentStructure.getRecursiveOrDefaultLeftBorderThickness()
		+ getNaturalScrollAreaWidth()
		+ getVerticalScrollbarThickness()
		+ currentStructure.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidget}.
	 * @throws ArgumentMissesAttributeException if the current {@link BorderWidget} does not have a proposal height.
	 */
	public final int getProposalHeight() {
		return proposalHeight.getValue().getValue();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidget}.
	 * @throws ArgumentMissesAttributeException if the current {@link BorderWidget} does not have a proposal width.
	 */
	public final int getProposalWidth() {
		return proposalWidth.getValue().getValue();
	}
	
	//method
	/**
	 * @return color of the vertical scrollbar of the current {@link BorderWidget}.
	 */
	public final Color getVerticalScrollbarColor() {
		
		final var currentLook = getRefCurrentLook();
		
		if (!isMovingVerticalScrollbarCursor()) {
			if (!verticalScrollbarCursorIsUnderCursor()) {
				return
				currentLook.getRecursiveOrDefaultBaseScrollbarLook().getScrollbarColor();
			}
			else {
				return
				currentLook.getRecursiveOrDefaultHoverScrollbarLook().getScrollbarColor();
			}
		}
		else {
			return
			currentLook.getRecursiveOrDefaultSelectionScrollbarLook().getScrollbarColor();
		}
	}
	
	//method
	/**
	 * @return color of the vertical scrollbar cursor of the current {@link BorderWidget}.
	 */
	public final Color getVerticalScrollbarCursorColor() {
		
		final var currentLook = getRefCurrentLook();
		
		if (!isMovingVerticalScrollbarCursor()) {
			if (!verticalScrollbarCursorIsUnderCursor()) {
				return
				currentLook.getRecursiveOrDefaultBaseScrollbarLook().getScrollbarCursorColor();
			}
			else {
				return
				currentLook.getRecursiveOrDefaultHoverScrollbarLook().getScrollbarCursorColor();
			}
		}
		else {
			return
			currentLook.getRecursiveOrDefaultSelectionScrollbarLook().getScrollbarCursorColor();
		}
	}
	
	//method
	/**
	 * @return the thickness of the vertical scrollbar of the current {@link BorderWidget}.
	 */
	public final int getVerticalScrollbarThickness() {
		
		//Handles the case that the current border widget does not have a vertical scrollbar.
		if (!hasVerticalScrollbar()) {
			return 0;
		}
		
		//Handles the case that the current border widget} has a vertical scroll bar.
		return 20;
	}
	
	//method
	/**
	 * @return x-position of the view area of the current {@link BorderWidget} on the scroll area.
	 */
	public final int getViewAreaXPositionOnScrollArea() {
		return viewAreaXPositionOnScrollArea.getValue().getValue();
	}
	
	//method
	/**
	 * @return the y-position of the view area of the current {@link BorderWidget} on the scroll area.
	 */
	public final int getViewAreaYPositionOnScrollArea() {
		return viewAreaYPositionOnScrollArea.getValue().getValue();
	}
	
	//method
	/**
	 * This method determines if the current {@link BorderWidget} has a horizontal scroll bar.
	 * 
	 * There can be the following issues.
	 * -The horizontal scrollbar is not there, but the user needs it.
	 * -The horizontal scrollbar is there, but the user does not need it.
	 * 
	 * No matter what result this method returns, the program works technically.
	 * This method makes a good but not absolute guess if the user needs a horizontal scrollbar.
	 * 
	 * @return true if the current {@link BorderWidget} has a horizontal scrollbar.
	 */
	public final boolean hasHorizontalScrollbar() {
		
		final var naturalScrollAreaWidth = getNaturalScrollAreaWidth();
		
		return
		(hasMaxWidth() && getMaxWidth() < naturalScrollAreaWidth)
		|| (hasProposalWidth() && getProposalWidth() < naturalScrollAreaWidth);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a max height.
	 */
	public final boolean hasMaxHeight() {
		return maxHeight.hasValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a max width.
	 */
	public final boolean hasMaxWidth() {
		return maxWidth.hasValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a min height.
	 */	
	public final boolean hasMinHeight() {
		return minHeight.hasValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a min width.
	 */
	public final boolean hasMinWidth() {
		return minWidth.hasValue();
	}
	
	/**
	 * @return true if the current {@link BorderWidget} has a proposal height.
	 */
	public final boolean hasProposalHeight() {
		return proposalHeight.hasValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a proposal width.
	 */
	public final boolean hasProposalWidth() {
		return proposalWidth.hasValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has a vertical scrollbar.
	 */
	public final boolean hasVerticalScrollbar() {
		
		final var naturalScrollAreaHeight = getNaturalScrollAreaHeight();
		
		return
		(hasMaxHeight() && getMaxHeight() < naturalScrollAreaHeight)
		|| (hasProposalHeight() && getProposalHeight() < naturalScrollAreaHeight);
	}
	
	//method
	/**
	 * @return true if the user is moving the horizontal scrollbar cursor of the current {@link BorderWidget}.
	 */
	public final boolean isMovingHorizontalScrollbarCursor() {
		return isMovingHorizontalScrollbarCursor;
	}
	
	//method
	/**
	 * @return true if the user is moving the vertical scrollbar cursor of the current {@link BorderWidget}.
	 */
	public final boolean isMovingVerticalScrollbarCursor() {
		return isMovingVerticalScrollbarCursor;
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} note a left mouse button press.
	 */
	@Override
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		//Handles the case that the cursor is over the vertical scrollbar cursor.
		if (verticalScrollbarCursorIsUnderCursor()) {
			
			isMovingVerticalScrollbarCursor = true;
			
			verticalScrollingCursorStartYPosition =
			getCursorYPosition()
			- getVerticalScrollbarCursorYPositionOnVerticalScrollbar();
		}
		
		//Handles the case that the cursor is over the horizontal scrollbar cursor.
		else if (horizontalScrollbarCursorIsUnderCursor()) {
			
			isMovingHorizontalScrollbarCursor = true;
			
			horizontalScrollingCursorStartXPosition =
			getCursorXPosition()
			- getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteAnyLeftMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteAnyLeftMouseButtonRelease();
		
		if (isEnabled()) {
			isMovingHorizontalScrollbarCursor = false;
			isMovingVerticalScrollbarCursor = false;
		}
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} note a mouse move
	 */
	@Override
	public void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		if (isMovingVerticalScrollbarCursor) {
			
			final var verticalScrollbarCursorYDelta =
			getCursorYPosition() - verticalScrollingCursorStartYPosition;
			
			final var viewAreaHeight = getViewAreaHeight();
			
			final var viewAreaYDelta =
			(verticalScrollbarCursorYDelta * (getScrollAreaHeight() - viewAreaHeight))
			/ (viewAreaHeight - getVerticalScrollbarCursorHeight());
			
			final var viewAreaYPositionOnScrollArea = viewAreaYDelta;
			
			setViewAreaYPositionOnScrollArea(viewAreaYPositionOnScrollArea);
		}
		
		if (isMovingHorizontalScrollbarCursor) {
			
			final var horizontalScrollbarCursorXDelta =
			getCursorXPosition() - horizontalScrollingCursorStartXPosition;
			
			final var viewAreaWidth = getViewAreaWidth();
			
			final var viewAreaXDelta =
			(horizontalScrollbarCursorXDelta * (getScrollAreaWidth() - viewAreaWidth))
			/ (viewAreaWidth - getHorizontalScrollbarCursorWidth());
			
			final var viewAreaXPositionOnScrollArea = viewAreaXDelta;
			
			setViewAreaXPositionOnScrollArea(viewAreaXPositionOnScrollArea);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noteAnyMouseWheelRotationSteps(final int mouseWheelRotationSteps) {
		
		//Calls method of the base class.
		super.noteAnyMouseWheelRotationSteps(mouseWheelRotationSteps);
		
		if (isEnabled()) {
			if (isFocused() || isHoverFocused()) {
				setViewAreaYPositionOnScrollArea(
					getViewAreaYPositionOnScrollArea()
					+ VIEW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP * mouseWheelRotationSteps
				);
			}
		}
	}

	//method
	/**
	 * Removes the max height of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMaxHeight() {
		
		maxHeight.clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the max width of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMaxWidtht() {
		
		maxWidth.clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the min height of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMinHeight() {
		
		minHeight.clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Removes the min width of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMinWidth() {
		
		minWidth.clear();
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Resets the configuration of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	@Override
	public BW resetConfiguration() {
		
		//Calls method of the base class
		super.resetConfiguration();
		
		setContentPosition(ContentPosition.LeftTop);
		removeMinWidth();
		removeMinHeight();
		removeMaxWidtht();
		removeMaxHeight();
		setViewAreaXPositionOnScrollArea(0);
		setViewAreaYPositionOnScrollArea(0);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to bottom.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToBottom() {
		return setViewAreaYPositionOnScrollArea(getScrollAreaHeight());
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to left.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToLeft() {
		return setViewAreaXPositionOnScrollArea(0);
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to right.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToRight() {
		return setViewAreaXPositionOnScrollArea(getScrollAreaWidth());
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to top.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToTop() {
		return setViewAreaYPositionOnScrollArea(0);
	}
	
	//method
	/**
	 * Sets the content position of the current {@link BorderWidget}.
	 * 
	 * @param contentPosition
	 * @return the current {@link BorderWidget}.
	 * @throws NullArgumentException if the given content position is null.
	 */
	public final BW setContentPosition(final ContentPosition contentPosition) {
		
		//Checks if the given content position is not null.
		Validator.suppose(contentPosition).isOfType(ContentPosition.class);

		//Sets the content position of the current border widget.
		this.contentPosition = contentPosition;
		
		return asConcreteType();
	}
	
	//method
	public void setCursorPosition(final int cursorXPosition, final int cursorYPosition) {
		
		this.cursorXPosition = cursorXPosition;
		this.cursorYPosition = cursorYPosition;
		
		getChildWidgets().forEach(cw -> cw.setParentCursorPosition(getCursorXPositionOnContentArea(), getCursorYPositionOnContentArea()));
	}
	
	//method
	/**
	 * Sets the max height of the current {@link BorderWidget}.
	 * 
	 * @param maxHeight
	 * @return the current {@link BorderWidget}.
	 * @throws NonPositiveArgumentException if the given max height is not positive.
	 */
	public final BW setMaxHeight(final int maxHeight) {
		
		this.maxHeight.setValue(new PositiveInteger(maxHeight));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the max width of the current {@link BorderWidget}.
	 * 
	 * @param maxWidth
	 * @return the current {@link BorderWidget}.
	 * @throws NonPositiveArgumentException if the given max width is not positive.
	 */
	public final BW setMaxWidth(final int maxWidth) {
		
		this.maxWidth.setValue(new PositiveInteger(maxWidth));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the min height of the current {@link BorderWidget}.
	 * 
	 * @param minHeight
	 * @return the current {@link BorderWidget}.
	 * @throws NonPositiveArgumentException if the given min height is not positive.
	 */
	public final BW setMinHeight(final int minHeight) {
		
		this.minHeight.setValue(new PositiveInteger(minHeight));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the min width of the current {@link BorderWidget}.
	 * 
	 * @param minWidth
	 * @return the current {@link BorderWidget}.
	 * @throws NonPositiveArgumentException if the given min width is not positive.
	 */
	public final BW setMinWidth(final int minWidth) {
		
		this.minWidth.setValue(new PositiveInteger(minWidth));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the proposal height of the current {@link BorderWidget}.
	 * 
	 * @param proposalHeight
	 * @return the current {@link BorderWidget}.
	 * @throws NonPositiveArgumentException if the given proposal height is not positive.
	 */
	public final BW setProposalHeight(final int proposalHeight) {
		
		this.proposalHeight.setValue(new PositiveInteger(proposalHeight));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the proposal width and the proposal height of the current {@link BorderWidget}.
	 * 
	 * @param proposalWidth
	 * @param proposalHeight
	 * @return the current {@link BorderWidget}.
	 * @throws NonPositiveArgumentException if the given proposal width is not positive.
	 * @throws NonPositiveArgumentException if the given proposal height is not positive.
	 */
	public final BW setProposalSize(final int proposalWidth, final int proposalHeight) {
		
		setProposalWidth(proposalWidth);
		setProposalHeight(proposalHeight);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the proposal with of the current {@link BorderWidget}.
	 * 
	 * @param proposalWidth
	 * @return the current {@link BorderWidget}.
	 * @throws NonPositiveArgumentException if the given proposal width is not positive.
	 */
	public final BW setProposalWidth(final int proposalWidth) {
		
		this.proposalWidth.setValue(new PositiveInteger(proposalWidth));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the x-position of the view area on the scroll area of the current {@link BorderWidget}.
	 * 
	 * @param viewAreaXPositionOnScrollArea
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setViewAreaXPositionOnScrollArea(int viewAreaXPositionOnScrollArea) {
		
		viewAreaXPositionOnScrollArea = Calculator.getMax(viewAreaXPositionOnScrollArea, 0);
		
		viewAreaXPositionOnScrollArea = Calculator.getMin(
			viewAreaXPositionOnScrollArea,
			getScrollAreaWidth() - getViewAreaWidth()
		);
		
		this
		.viewAreaXPositionOnScrollArea
		.setValue(new NonNegativeInteger(viewAreaXPositionOnScrollArea));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the y-position of the view area on the scroll area of the current {@link BorderWidget}.
	 * 
	 * @param viewAreaYPositionOnScrollArea
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setViewAreaYPositionOnScrollArea(int viewAreaYPositionOnScrollArea) {
		
		viewAreaYPositionOnScrollArea = Calculator.getMax(viewAreaYPositionOnScrollArea, 0);
		
		viewAreaYPositionOnScrollArea = Calculator.getMin(
			viewAreaYPositionOnScrollArea,
			getScrollAreaHeight() - getViewAreaHeight()
		);
		
		this
		.viewAreaYPositionOnScrollArea
		.setValue(new NonNegativeInteger(viewAreaYPositionOnScrollArea));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * @return true if the content area of the current {@link BorderWidget} is under the cursor.
	 */
	protected final boolean contentAreaIsUnderCursor() {
		return (
			getCursorXPositionOnContentArea() >= 0
			&& getCursorXPositionOnContentArea() < getContentAreaWidth()
			&& getCursorYPositionOnContentArea() >= 0
			&& getCursorYPositionOnContentArea() < getContentAreaHeight()
		);
	}
	
	//abstract method
	/**
	 * @return the height of the content area of the current {@link BorderWidget}.
	 */
	protected abstract int getContentAreaHeight();
	
	//abstract method
	/**
	 * @return the width of the content area of the current {@link BorderWidget}.
	 */
	protected abstract int getContentAreaWidth();
	
	//method
	/**
	 * @return the x-position of the content area of the current {@link BorderWidget}.
	 */
	protected final int getContentAreaXPosition() {
		
		final var currentStructure = getRefCurrentLook();
		
		return
		currentStructure.getRecursiveOrDefaultLeftBorderThickness()
		- getViewAreaXPositionOnScrollArea()
		+ getContentAreaXPositionOnScrollArea();
	}
	
	//method
	/**
	 * @return the y-position of the content area of the current {@link BorderWidget}.
	 */
	protected final int getContentAreaYPosition() {
		
		final var currentStructure = getRefCurrentLook();
		
		return
		currentStructure.getRecursiveOrDefaultTopBorderThickness()
		- getViewAreaYPositionOnScrollArea()
		+ getContentAreaYPositionOnScrollArea();
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the content area of the current {@link BorderWidget}.
	 */
	protected final int getCursorXPositionOnContentArea() {
		return (getCursorXPosition() - getContentAreaXPosition());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the content area of the current {@link BorderWidget}.
	 */
	protected final int getCursorYPositionOnContentArea() {
		return (getCursorYPosition() - getContentAreaYPosition());
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidget} when it is not collapsed.
	 */
	@Override
	protected final int getHeightWhenNotCollapsed() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		currentLook.getRecursiveOrDefaultTopBorderThickness()		
		+ getBorderedAreaHeight()
		+ currentLook.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * This method returns just a propose for the height for the content area of the current {@link BorderWidget}.
	 * This is supposed for a {@link Widget}, that cannot determine a meaningful content area height.
	 * 
	 * @return a proposed height for the content area of the current {@link BorderWidget}.
	 */
	protected final int getProposedContentAreaHeight() {
		
		var proposedContentAreaHeight =
		hasProposalHeight() ? getProposalHeight() : ValueCatalogue.MEDIUM_WIDGET_HEIGHT;
		
		//Handles the case that the current border widget has a min height.
		if (hasMinHeight()) {
			proposedContentAreaHeight =	Calculator.getMax(getMinHeight(), proposedContentAreaHeight);
		}
		
		final var currentLook = getRefCurrentLook();
		
		return
		proposedContentAreaHeight
		- currentLook.getRecursiveOrDefaultTopBorderThickness()
		- currentLook.getRecursiveOrDefaultTopPadding()
		- currentLook.getRecursiveOrDefaultBottomPadding()
		- currentLook.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * This method returns just a propose for the width for the content area of the current {@link BorderWidget}.
	 * This is supposed for a {@link Widget}, that cannot determine a meaningful content area width.
	 * 
	 * @return a proposed width for the content area of the current {@link BorderWidget}.
	 */
	protected final int getProposedContentAreaWidth() {
		
		var proposedContentAreaWidth =
		hasProposalWidth() ? getProposalWidth() : ValueCatalogue.MEDIUM_WIDGET_WIDTH;
		
		//Handles the case that the current border widget has a min width.
		if (hasMinWidth()) {
			proposedContentAreaWidth = Calculator.getMax(getMinWidth(), proposedContentAreaWidth);
		}
		
		final var currentLook = getRefCurrentLook();
		
		return
		proposedContentAreaWidth
		- currentLook.getRecursiveOrDefaultLeftBorderThickness()
		- currentLook.getRecursiveOrDefaultLeftPadding()
		- currentLook.getRecursiveOrDefaultRightPadding()
		- currentLook.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidget} when it is not collapsed.
	 */
	@Override
	protected final int getWidthWhenNotCollapsed() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		currentLook.getRecursiveOrDefaultLeftBorderThickness()		
		+ getBorderedAreaWidth()
		+ currentLook.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * Paints the current {@link BorderWidget} using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	@Override
	protected final void paint(
		final BWS widgetStructure,
		final IPainter painter
	) {
				
		//Calls method of the base class.
		super.paint(widgetStructure, painter);
		
		//Paints the left border if the given widget structure has an active left border thickness.
		if (widgetStructure.getRecursiveOrDefaultLeftBorderThickness() > 0) {
			
			painter.setColor(widgetStructure.getRecursiveOrDefaultLeftBorderColor());
			
			painter.paintFilledRectangle(
				widgetStructure.getRecursiveOrDefaultLeftBorderThickness(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the right border if the given widget structure has an active right border thickness.
		if (widgetStructure.getRecursiveOrDefaultRightBorderThickness() > 0) {
			
			painter.setColor(widgetStructure.getRecursiveOrDefaultRightBorderColor());
			
			painter.paintFilledRectangle(
				getWidth() - widgetStructure.getRecursiveOrDefaultLeftBorderThickness(),
				0,
				widgetStructure.getRecursiveOrDefaultLeftBorderThickness(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the top border if the given widget structure has an active top border thickness.
		if (widgetStructure.getRecursiveOrDefaultTopBorderThickness() > 0) {
			
			painter.setColor(widgetStructure.getRecursiveOrDefaultTopBorderColor());
			
			painter.paintFilledRectangle(
				getWidth(),
				widgetStructure.getRecursiveOrDefaultTopBorderThickness()
			);
		}
		
		//Paints the bottom border if the given widget structure has an active bottom border thickness.
		if (widgetStructure.getRecursiveOrDefaultBottomBorderThickness() > 0) {
			
			painter.setColor(widgetStructure.getRecursiveOrDefaultBottomBorderColor());
			
			painter.paintFilledRectangle(
				0,
				getHeightWhenNotCollapsed() - widgetStructure.getRecursiveOrDefaultBottomBorderThickness(),
				getWidth(),
				widgetStructure.getRecursiveOrDefaultBottomBorderThickness()
			);
		}
		
		//Paints the bordered area of the current border widget.
		paintBorderedArea(
			widgetStructure,
			painter.createPainter(
				getBorderedAreaXPosition(),
				getBorderedAreaYPosition()
			)
		);
	}
	
	protected void paint3(IPainter painter) {
		paint(getRefCurrentLook(), painter);
	}
	
	//abstract method
	/**
	 * Paints the content area of the current {@link BorderWidget} using the given border widget look and painter.
	 * 
	 * @param borderWidgetLook
	 * @param painter
	 */
	protected void paintContentArea(BWS borderWidgetLook, IPainter painter) {}
	
	private void paintContentArea2(BWS borderWidgetLook, IPainter painter) {
		
		paintContentArea(borderWidgetLook, painter);
		
		getChildWidgets().forEach(cw -> cw.paint(painter));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean viewAreaIsUnderCursor() {
		
		//For a better performance, this implementation differs from the standard approach.	
			final var cursorXPositionOnViewArea = getCursorXPositionOnViewArea();
			final var cursorYPositionOnViewArea = getCursorYPositionOnViewArea();
			
			return
			cursorXPositionOnViewArea > 0
			&& cursorYPositionOnViewArea > 0
			&& cursorXPositionOnViewArea < getViewAreaWidth()
			&& cursorYPositionOnViewArea < getViewAreaHeight();
		
	}
	
	//method
	/**
	 * @return the height of the bordered area of the current {@link BorderWidget}.
	 */
	private int getBorderedAreaHeight() {
		return 
		getViewAreaHeight()
		+ getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the width of the bordered area of the current {@link BorderWidget}.
	 */
	private int getBorderedAreaWidth() {
		return 
		getViewAreaWidth()
		+ getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the x-position of the bordered area of the current {@link BorderWidget}.
	 */
	private int getBorderedAreaXPosition() {
		
		final var currentStructure = getRefCurrentLook();
		
		return currentStructure.getRecursiveOrDefaultLeftBorderThickness();
	}
	
	//method
	/**
	 * @return the y-position of the bordered area of the current {@link BorderWidget}.
	 */
	private int getBorderedAreaYPosition() {
		
		final var currentStructure = getRefCurrentLook();
		
		return currentStructure.getRecursiveOrDefaultTopBorderThickness();
	}
	
	//method
	/**
	 * @return the x-position of the content area of the current {@link BorderWidget}
	 * on the scroll area of the current {@link BorderWidget}.
	 */
	private int getContentAreaXPositionOnScrollArea() {
		
		final var currentStructure = getRefCurrentLook();
		
		//Enumerates the content position of the current border widget.
		switch (getContentPosition()) {
			case LeftTop:
			case Left:
			case LeftBottom:
				return currentStructure.getRecursiveOrDefaultLeftPadding();
			case Top:
			case Center:
			case Bottom:
				return (getScrollAreaWidth() - getContentAreaWidth()) / 2;
			case RightTop:
			case Right:
			case RightBottom:				
				return
				getScrollAreaWidth()
				- getContentAreaWidth()
				- currentStructure.getRecursiveOrDefaultRightPadding();
		}
		
		throw new InvalidArgumentException(this);
	}
	
	//method
	/**
	 * @return the y-position of the content area of the current {@link BorderWidget}
	 * on the scroll area of the current {@link BorderWidget}.
	 */
	private int getContentAreaYPositionOnScrollArea() {
		
		final var currentStructure = getRefCurrentLook();
		
		//Enumerates the content orientation of the current border widget.
		switch (getContentPosition()) {
			case LeftTop:
			case Top:
			case RightTop:
				return currentStructure.getRecursiveOrDefaultTopPadding();
			case Left:
			case Center:
			case Right:
				return (getScrollAreaHeight() - getContentAreaHeight()) / 2;
			case LeftBottom:
			case Bottom:
			case RightBottom:
				return
				getScrollAreaHeight()
				- getContentAreaHeight()
				- currentStructure.getRecursiveOrDefaultBottomPadding();
		}
		
		throw new InvalidArgumentException(this);
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the view area of the current {@link BorderWidget}.
	 */
	private int getCursorXPositionOnViewArea() {
		return (getCursorXPosition() - getViewAreaXPosition());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the view area of the current {@link BorderWidget}.
	 */
	private int getCursorYPositionOnViewArea() {
		return (getCursorYPosition() - getViewAreaYPosition());
	}
	
	//method
	private int getHorizontalScrollbarCursorWidth() {
		return
		Calculator.getMax(
			(int)(Math.pow(getViewAreaWidth(), 2) / getScrollAreaWidth()),
			MIN_SCROLL_CURSOR_LENGTH
		);
	}
	
	//method
	/**
	 * @return the x-position of the horizontal scrollbar cursor.
	 */
	private int getHorizontalScrollbarCursorXPosition() {
		return
		getBorderedAreaXPosition()
		+ getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar();
	}
	
	//method
	/**
	 * @return the x-position of the horizontal scrollbar cursor
	 * on the horizontal scrollbar of this broder widget.
	 */
	private int getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar() {
				
		final var viewAreaWidth = getViewAreaWidth();
		
		return
		(viewAreaWidth - getHorizontalScrollbarCursorWidth())
		* getViewAreaXPositionOnScrollArea()
		/ (getScrollAreaWidth() - viewAreaWidth);
	}
	
	//method
	/**
	 * @return the y-position of the horizontal scrollbar cursor of the current {@link BorderWidget}
	 */
	private int getHorizontalScrollbarCursorYPosition() {
		return
		getBorderedAreaYPosition()
		+ getHorizontalScrollbarYPositionOnBorderedArea();
	}
	
	//method
	/**
	 * @return the y-position of the horizontal scrollbar
	 * on the bordered area of the current {@link BorderWidget}.
	 */
	private int getHorizontalScrollbarYPositionOnBorderedArea() {
		return (getBorderedAreaHeight()	- getHorizontalScrollbarThickness());
	}
	
	//method
	private int getMaxViewAreaHeight() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		getMaxHeight()
		- currentLook.getRecursiveOrDefaultTopBorderThickness()
		- currentLook.getRecursiveOrDefaultBottomBorderThickness()
		- getHorizontalScrollbarThickness();
	}

	//method
	private int getMaxViewAreaWidth() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		getMaxWidth()
		- currentLook.getRecursiveOrDefaultLeftBorderThickness()
		- currentLook.getRecursiveOrDefaultRightBorderThickness()
		- getVerticalScrollbarThickness();
	}
	
	//method
	private int getMinScrollAreaHeight() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		getMinHeight()
		- currentLook.getRecursiveOrDefaultTopBorderThickness()
		- currentLook.getRecursiveOrDefaultBottomBorderThickness()
		- getHorizontalScrollbarThickness();
	}
	
	//method
	private int getMinScrollAreaWidth() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		getMinWidth()
		- currentLook.getRecursiveOrDefaultLeftBorderThickness()
		- currentLook.getRecursiveOrDefaultRightBorderThickness()
		- getVerticalScrollbarThickness();
	}
	
	//method
	private int getMinViewAreaHeight() {
		return getMinScrollAreaHeight();
	}
	
	//method
	private int getMinViewAreaWidth() {
		return getMinScrollAreaWidth();
	}
	

	
	//method
	private int getNaturalScrollAreaHeight() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		currentLook.getRecursiveOrDefaultTopPadding()
		+ getContentAreaHeight()
		+ currentLook.getRecursiveOrDefaultBottomPadding();
	}

	//method
	private int getNaturalScrollAreaWidth() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		currentLook.getRecursiveOrDefaultLeftPadding()
		+ getContentAreaWidth()
		+ currentLook.getRecursiveOrDefaultRightPadding();
	}
	
	//method
	private int getNaturalViewAreaHeight() {
		return getNaturalScrollAreaHeight();
	}
	
	//method
	private int getNaturalViewAreaWidth() {
		return getNaturalScrollAreaWidth();
	}
	
	//method
	private int getProposalScrollAreaHeight() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		getProposalHeight()
		- currentLook.getRecursiveOrDefaultTopBorderThickness()
		- currentLook.getRecursiveOrDefaultBottomBorderThickness()
		- getHorizontalScrollbarThickness();
	}
	
	//method
	private int getProposalScrollAreaWith() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		getProposalWidth()
		- currentLook.getRecursiveOrDefaultLeftBorderThickness()
		- currentLook.getRecursiveOrDefaultRightBorderThickness()
		- getVerticalScrollbarThickness();
	}
	
	//method
	private int getProposalViewAreaHeight() {
		return getProposalScrollAreaHeight();
	}

	//method
	private int getProposalViewAreaWidth() {
		return getProposalScrollAreaWith();
	}
	
	//method
	/**
	 * @return the height of the scroll area of the current {@link BorderWidget}.
	 */
	private int getScrollAreaHeight() {
		
		var scrollAreaHeight = getNaturalScrollAreaHeight();
		
		if (hasProposalHeight()) {
			scrollAreaHeight = Calculator.getMax(scrollAreaHeight, getProposalScrollAreaHeight());
		}
		
		//Handles the case that the current border widget has a min height.
		if (hasMinHeight()) {
			scrollAreaHeight = Calculator.getMax(
				scrollAreaHeight,
				getMinScrollAreaHeight()
			);
		}
		
		return scrollAreaHeight;
	}
	
	//method
	/**
	 * @return the width of the scroll area of the current {@link BorderWidget}.
	 */
	private int getScrollAreaWidth() {
			
		var scrollAreaWidth = getNaturalScrollAreaWidth();
		
		if (hasProposalWidth()) {
			scrollAreaWidth = Calculator.getMax(scrollAreaWidth, getProposalScrollAreaWith());
		}
		
		//Handles the case that the current border widget has a min width.
		if (hasMinWidth()) {
			scrollAreaWidth = Calculator.getMax(
				scrollAreaWidth,
				getMinScrollAreaWidth()
			);
		}
		
		return scrollAreaWidth;
	}
	
	//method
	/**
	 * @return the height of the vertical scrollbar cursor of the current {@link BorderWidget}.
	 */
	private int getVerticalScrollbarCursorHeight() {
		return
		Calculator.getMax(
			(int)(Math.pow(getViewAreaHeight(), 2) / getScrollAreaHeight()),
			MIN_SCROLL_CURSOR_LENGTH
		);
	}
	
	//method
	/**
	 * @return the x-position of the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	private int getVerticalScrollbarCursorXPosition() {
		return
		getBorderedAreaXPosition()
		+ getVerticalScrollbarXPositionOnBorderedArea();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	private int getVerticalScrollbarCursorYPosition() {
		return
		getBorderedAreaYPosition()
		+ getVerticalScrollbarCursorYPositionOnVerticalScrollbar();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scrollbar cursor
	 * on the vertical scrollbar of the current {@link BorderWidget}.
	 */
	private int getVerticalScrollbarCursorYPositionOnVerticalScrollbar() {
		
		final var viewAreaHeight = getViewAreaHeight();
		
		return
		(viewAreaHeight - getVerticalScrollbarCursorHeight())
		* getViewAreaYPositionOnScrollArea()
		/ (getScrollAreaHeight() - viewAreaHeight);
	}
	
	//method
	/**
	 * @return the x-position of the vertical scrollbar
	 * on the bordered area of the current {@link BorderWidget}.
	 */
	private int getVerticalScrollbarXPositionOnBorderedArea() {
		return (getBorderedAreaWidth() - getVerticalScrollbarThickness());
	}
	
	//method
	/**
	 * @return the height of the view area of the current {@link BorderWidget}.
	 */
	private int getViewAreaHeight() {
		
		var viewAreaHeight =
		hasProposalHeight() ? getProposalViewAreaHeight() : getNaturalViewAreaHeight();
		
		if (hasMinHeight()) {
			viewAreaHeight = Calculator.getMax(viewAreaHeight, getMinViewAreaHeight());
		}
		
		if (hasMaxHeight()) {
			viewAreaHeight = Calculator.getMin(viewAreaHeight, getMaxViewAreaHeight());
		}
		
		return viewAreaHeight;
	}
	
	//method
	/**
	 * @return the width of the view area of the current {@link BorderWidget}.
	 */
	private int getViewAreaWidth() {
						
		var viewAreaWidth =
		hasProposalWidth() ? getProposalViewAreaWidth() : getNaturalViewAreaWidth();
		
		if (hasMinWidth()) {
			viewAreaWidth = Calculator.getMax(viewAreaWidth, getMinViewAreaWidth());
		}
		
		if (hasMaxWidth()) {
			viewAreaWidth = Calculator.getMin(viewAreaWidth, getMaxViewAreaWidth());
		}
		
		return viewAreaWidth;
	}
	
	//method
	/**
	 * @return the x-position of the view area of the current {@link BorderWidget}.
	 */
	private int getViewAreaXPosition() {
		return getBorderedAreaXPosition();
	}
	
	//method
	/**
	 * @return the y-position of the view area of the current {@link BorderWidget}.
	 */
	private int getViewAreaYPosition() {
		return getBorderedAreaYPosition();
	}
	
	//method
	/**
	 * @return true if the cursor is over the horizontal scrollbar cursor of the current {@link BorderWidget}.
	 */
	private boolean horizontalScrollbarCursorIsUnderCursor() {
		
		//Handles the case that the current border widget does not have a horizontal scrollbar.
		if (!hasHorizontalScrollbar()) {
			return false;
		}
		
		//Handles the case that the current border widget has a horizontal scrollbar.
			final var cursorXPosition = getCursorXPosition();
			final var cursorYPosition = getCursorYPosition();
			final var horizontalScrollbarCursorXPosition = getHorizontalScrollbarCursorXPosition();
			final var horizontalScrollbarCursorYPosition = getHorizontalScrollbarCursorYPosition();
			
			return
			cursorXPosition >= horizontalScrollbarCursorXPosition
			&& cursorXPosition < horizontalScrollbarCursorXPosition + getHorizontalScrollbarCursorWidth()
			&& cursorYPosition >= horizontalScrollbarCursorYPosition
			&& cursorYPosition < horizontalScrollbarCursorYPosition + getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return true if the cursor is over the horizontal scrollbar of the current {@link BorderWidget}.
	 */
	private boolean horizontalScrollbarIsUnderCursor() {
		
		//Handles the case that the current border widget does not have a horizontal scrollbar.
		if (!hasHorizontalScrollbar()) {
			return false;
		}
		
		//Handles the case that the current border widget has a horizontal scrollbar.
			final var cursorXPosition = getCursorXPosition();
			final var cursorYPosition = getCursorYPosition();
			final var horizontalScrollbarCursorXPosition = getHorizontalScrollbarCursorXPosition();
			final var horizontalScrollbarCursorYPosition = getHorizontalScrollbarCursorYPosition();
			
			return
			cursorXPosition >= horizontalScrollbarCursorXPosition
			&& cursorXPosition < horizontalScrollbarCursorXPosition + getViewAreaWidth()
			&& cursorYPosition >= horizontalScrollbarCursorYPosition
			&& cursorYPosition < horizontalScrollbarCursorYPosition + getHorizontalScrollbarThickness();
	}

	//method
	/**
	 * Paints the bordered area of the current {@link BorderWidget}
	 * using the given border widget structure and painter.
	 * 
	 * @param borderWidgetStructure
	 * @param painter
	 */
	private void paintBorderedArea(
		final BWS borderWidgetStructure,
		final IPainter painter
	) {
		
		//Paints the vertical scroll bar if the current border widget has a vertical scrollbar.
		if (hasVerticalScrollbar()) {
			
			//Paints the vertical scrollbar.				
				painter.setColor(getVerticalScrollbarColor());
				
				painter.paintFilledRectangle(
					getVerticalScrollbarXPositionOnBorderedArea(),
					0,
					getVerticalScrollbarThickness(),
					getViewAreaHeight()
				);
			
			//Paints the vertical scrollbar cursor.				
				painter.setColor(getVerticalScrollbarCursorColor());
				
				painter.paintFilledRectangle(
					getVerticalScrollbarXPositionOnBorderedArea(),
					getVerticalScrollbarCursorYPositionOnVerticalScrollbar(),
					getVerticalScrollbarThickness(),
					getVerticalScrollbarCursorHeight()
				);
		}
		
		//Paints the horizontal scrollbar if the current border widget has a horizontal scrollbar.
		if (hasHorizontalScrollbar()) {
			
			//Paints the horizontal scrollbar.	
				painter.setColor(getHorizontalScrollbarColor());
				
				painter.paintFilledRectangle(
					0,
					getHorizontalScrollbarYPositionOnBorderedArea(),
					getViewAreaWidth(),
					getHorizontalScrollbarThickness()
				);
			
			//Paints the horizontal scrollbar cursor.			
				painter.setColor(getHorizontalScrollbarCursorColor());
				
				painter.paintFilledRectangle(
					getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar(),
					getHorizontalScrollbarYPositionOnBorderedArea(),
					getHorizontalScrollbarCursorWidth(),
					getHorizontalScrollbarThickness()
				);
		}
		
		//Paints the view area of the current border widget.
		paintViewArea(
			borderWidgetStructure,
			painter.createPainter(
				0,
				0,
				getViewAreaWidth(),				
				getViewAreaHeight()
			)
		);
	}
	
	//method
	/**
	 * Paints the view area of the current {@link BorderWidget}
	 * using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	private void paintViewArea(
		final BWS widgetStructure,
		final IPainter painter
	) {
		paintScrollArea(
			widgetStructure,
			painter.createPainter(
				-getViewAreaXPositionOnScrollArea(),
				-getViewAreaYPositionOnScrollArea(),
				getScrollAreaWidth(),
				getScrollAreaHeight()
			)
		);
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} paint its scroll area
	 * using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	private void paintScrollArea(
		final BWS widgetStructure,
		final IPainter painter
	) {
		paintContentArea2(
			widgetStructure,
			painter.createPainter(
				getContentAreaXPositionOnScrollArea(),
				getContentAreaYPositionOnScrollArea(),
				getScrollAreaWidth(),
				getScrollAreaHeight()
			)
		);
	}
	
	//method
	/**
	 * @return true if the cursor is over the vertical scrollbar cursor of the current {@link BorderWidget}.
	 */
	private boolean verticalScrollbarCursorIsUnderCursor() {
		
		//Handles the case that the current border widget does not have a vertical scrollbar.
		if (!hasVerticalScrollbar()) {
			return false;
		}
		
		//Handles the case that the current border widget has a vertical scrollbar.
			final var cursorXPosition = getCursorXPosition();
			final var cursorYPosition = getCursorYPosition();
			final var verticalScrollbarCursorXPosition = getVerticalScrollbarCursorXPosition();
			final var verticalScrollbarCursorYPosition = getVerticalScrollbarCursorYPosition();
			
			return
			cursorXPosition >= verticalScrollbarCursorXPosition
			&& cursorXPosition < verticalScrollbarCursorXPosition + getVerticalScrollbarThickness()
			&& cursorYPosition >= verticalScrollbarCursorYPosition
			&& cursorYPosition < verticalScrollbarCursorYPosition + getVerticalScrollbarCursorHeight();
	}
	
	//method
	/**
	 * @return true if the cursor is over the vertical scrollbar of the current {@link BorderWidget}.
	 */
	private boolean verticalScrollbarIsUnderCursor() {
		
		//Handles the case that the current border widget does not have a vertical scrollbar.
		if (!hasVerticalScrollbar()) {
			return false;
		}
		
		//Handles the case that the current border widget has a vertical scrollbar.
			final var cursorXPosition = getCursorXPosition();
			final var cursorYPosition = getCursorYPosition();
			final var verticalScrollbarCursorXPosition = getVerticalScrollbarCursorXPosition();
			final var verticalScrollbarCursorYPosition = getVerticalScrollbarCursorYPosition();
						
			return
			cursorXPosition >= verticalScrollbarCursorXPosition
			&& cursorXPosition < verticalScrollbarCursorXPosition + getVerticalScrollbarThickness()
			&& cursorYPosition >= verticalScrollbarCursorYPosition
			&& cursorYPosition < verticalScrollbarCursorYPosition + getViewAreaHeight();
	}
}
