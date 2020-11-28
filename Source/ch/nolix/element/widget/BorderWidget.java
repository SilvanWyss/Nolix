//package declaration
package ch.nolix.element.widget;

import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.ValueCatalogue;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.base.MutableOptionalValue;
import ch.nolix.element.base.MutableValue;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnum.ContentPosition;
import ch.nolix.element.elementEnum.RotationDirection;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link BorderWidget} is a {@link Widget} that:
 * -Can have an individual border at each of its 4 sides.
 * -Can have a minWidth and a minHeight.
 * -Can have a maxWidth and a maxHeight.
 * -Can have a proposalWidth and a proposalHeight.
 * 
 * -A minWidth, minHeight, proposalWidth or proposalHeight of a {@link BorderWidget}
 *  can make that it is stretched what moves around its content inside its borders.
 * -A maxWidth, maxHeight, proposalWidth or proposalHeight of a {@link BorderWidget}
 *  can make that its content is moved into a show area which can be scrolled.
 *  Inside a show area, the content of a {@link BorderWidget} gets its natural width resp. natural height.
 * -The width resp. height of a {@link BorderWidget}, in the case when it is expanded,
 *  is set from its target width resp. target height if it has a target width resp. target height.
 *  Otherwise the width resp. height of a {@link BorderWidget} is set from its natural width resp. natural height.
 * 
 * A {@link BorderWidget} has a natural width resp. natural height.
 * The natural width resp. natural height of a {@link BorderWidget}
 * is the width resp. height it would have with its content ignoring any min-, max-, proposal- width resp. height.
 * 
 * -A {@link BorderWidget} can have a targetWidth resp. targetHeight.
 * The targetWidth resp. targetHeight of a {@link BorderWidget}
 * is calculated with the following priorities, from highest to lowest priority.
 * 1. The targetWidth resp. targetHeight of a {@link BorderWidget} is set from its maxWidth resp. maxHeight if:
 *    -It has a maxWidth resp. maxHeight.
 *    -Its maxWidth resp. maxHeight would be smaller than its targetWidth resp. targetHeight.
 * 2. The targetWidth resp. targetHeight of a {@link BorderWidget} is set from its minWidth resp minHeight if:
 *    -It has a minWidth resp. minHeight.
 *    -Its minWidth resp. minHeight would be bigger than its targetWidth resp. targetHeight.
 * 3. The targetWidth resp. targetHeight of a {@link BorderWidget} is set from its proposalWidth resp. proposalHeight if
 *    it has a proposalWidth resp proposalHeight.
 * 
 * A {@link BackgroundWidget consists of the following areas from outer to inner.
 * 1. widget area: Contains the probable shadows and main area.
 * 2. main area: Contains the probable borders and bordered area.
 * 3. bordered area: Contains the probable scroll bars and show area.
 * 4. show area: Is over the scrolled area and is like a hole to look on the scrolled area below.
 * 5. scrolled area: Contains the probable paddings and content area.
 * 6. content area: Contains the content.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 1630
 * @param <BW> The type of a {@link BackgroundWidget.
 * @param <BWL> The type of the {@link BorderWidgetLook}s of a {@link BackgroundWidget.
 */
public abstract class BorderWidget<BW extends BorderWidget<BW, BWL>,BWL extends BorderWidgetLook<BWL>>
extends Widget<BW, BWL> {
	
	//constant
	public static final int SCROLL_BAR_THICKNESS = 20;
	
	//constants
	private static final int MIN_SCROLL_CURSOR_LENGTH = 10;
	private static final int SHOW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP = 50;
		
	//constants
	private static final String AUTOMATIC_SIZE_HEADER = "AutomaticSize";
	private static final String MIN_WIDTH_HEADER = "MinWidth";
	private static final String MIN_HEIGHT_HEADER = "MinHeight";
	private static final String MAX_WIDTH_HEADER = "MaxWidth";
	private static final String MAX_HEIGHT_HEADER = "MaxHeight";
	private static final String PROPOSAL_WIDTH_HEADER = "ProposalWidth";
	private static final String PROPOSAL_HEIGHT_HEADER = "ProposalHeight";
	private static final String SHOW_AREA_X_POSITION_ON_SCROLLED_AREA_HEADER = "ShowAreaXPositionOnScrolledArea";
	private static final String SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA_HEADER = "ShowAreaYPositionOnScrolledArea";
	
	//attribute
	private MutableValue<Boolean> automaticSize =
	new MutableValue<>(
		AUTOMATIC_SIZE_HEADER,
		as -> {
			if (as.booleanValue()) {
				activateAutomaticSize();
			}
			else {
				deactivateAutomaticSize();
			}
		},
		BaseNode::getOneAttributeAsBoolean,
		Node::withOneAttribute
	);
	
	//attribute
	private MutableValue<ContentPosition> contentPosition =
	new MutableValue<>(
		ContentPosition.TYPE_NAME,
		this::setContentPosition,
		ContentPosition::fromSpecification,
		ContentPosition::getSpecification
	);
	
	//attribute
	private final MutableOptionalValue<Integer> minWidth =
	new MutableOptionalValue<>(
		MIN_WIDTH_HEADER,
		this::setMinWidth,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableOptionalValue<Integer> minHeight =
	new MutableOptionalValue<>(
		MIN_HEIGHT_HEADER,
		this::setMinHeight,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableOptionalValue<Integer> maxWidth =
	new MutableOptionalValue<>(
		MAX_WIDTH_HEADER,
		this::setMaxWidth,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableOptionalValue<Integer> maxHeight =
	new MutableOptionalValue<>(
		MAX_HEIGHT_HEADER,
		this::setMaxHeight,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableOptionalValue<Integer> proposalWidth =
	new MutableOptionalValue<>(
		PROPOSAL_WIDTH_HEADER,
		this::setProposalWidth,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableOptionalValue<Integer> proposalHeight =
	new MutableOptionalValue<>(
		PROPOSAL_HEIGHT_HEADER,
		this::setProposalHeight,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableValue<Integer> showAreaXPositionOnScrolledArea =
	new MutableValue<>(
		SHOW_AREA_X_POSITION_ON_SCROLLED_AREA_HEADER,
		this::setShowAreaXPositionOnScrolledArea,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
	
	//attribute
	private final MutableValue<Integer> showAreaYPositionOnScrolledArea =
	new MutableValue<>(
		SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA_HEADER,
		this::setShowAreaYPositionOnScrolledArea,
		BaseNode::getOneAttributeAsInt,
		Node::withOneAttribute
	);
		
	//attributes
	private final BorderWidgetMainArea mainArea = new BorderWidgetMainArea(this);
	private final BorderWidgetBorderedArea<BW, BWL> borderedArea = new BorderWidgetBorderedArea<>(this);
	private final BorderWidgetShowArea<BW, BWL> showArea = new BorderWidgetShowArea<>(this);
	private final BorderWidgetScrolledArea<BW, BWL> scrolledArea = new BorderWidgetScrolledArea<>(this);
	private final BorderWidgetContentArea<BW, BWL> contentArea = new BorderWidgetContentArea<>(this);
	
	//attributes
	private boolean isMovingVerticalScrollBarCursor = false;
	private boolean isMovingHorizontalScrollBarCursor = false;
	
	//optional attributes
	private int verticalScrollingCursorStartYPosition;
	private int horizontalScrollingCursorStartXPosition;
	
	//method
	/**
	 * Lets the current {@link BorderWidget} active automatic size.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW activateAutomaticSize() {
		
		this.automaticSize.setValue(true);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} deactive automatic size.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW deactivateAutomaticSize() {
		
		this.automaticSize.setValue(false);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CursorIcon getCursorIcon() {
		
		if (anyScrollBarCursorIsUnderCursor()) {
			
			//TODO: Add cursorIcon attribute to BorderWidgetScrollBarLook.
			return CursorIcon.HAND;
		}
		
		if (showAreaIsUnderCursor()) {
			return super.getCursorIcon();
		}
		
		return CursorIcon.ARROW;
	}
	
	//method
	/**
	 * @return the bordered area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetBorderedArea<BW, BWL> getBorderedArea() {
		return borderedArea;
	}
	
	//method
	/**
	 * @return the content area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetContentArea<BW, BWL> getContentArea() {
		return contentArea;
	}
	
	//method
	/**
	 * @return the content position of the current {@link BorderWidget}.
	 */
	public final ContentPosition getContentPosition() {
		return contentPosition.getValue();
	}
	
	//method
	/**
	 * @return the color of the horizontal scroll bar of the current {@link BorderWidget}.
	 */
	public final Color getHorizontalScrollBarColor() {
		
		final var look = getRefLook();
		
		if (!isMovingHorizontalScrollBarCursor()) {
			
			if (!horizontalScrollBarCursorIsUnderCursor()) {
				return look.getRecursiveOrDefaultBaseScrollBarLook().getScrollBarColor();
			}
			
			return look.getRecursiveOrDefaultHoverScrollBarLook().getScrollBarColor();
		}
		
		return look.getRecursiveOrDefaultSelectionScrollBarLook().getScrollBarColor();
	}
	
	//method
	/**
	 * @return color of the horizontal scroll bar cursor of the current {@link BorderWidget}.
	 */
	public final Color getHorizontalScrollBarCursorColor() {
		
		final var look = getRefLook();
		
		if (!isMovingHorizontalScrollBarCursor()) {
			
			if (!horizontalScrollBarCursorIsUnderCursor()) {
				return look.getRecursiveOrDefaultBaseScrollBarLook().getScrollCursorColor();
			}
			
			return look.getRecursiveOrDefaultHoverScrollBarLook().getScrollCursorColor();
		}
		
		return look.getRecursiveOrDefaultSelectionScrollBarLook().getScrollCursorColor();
	}
	
	//method
	/**
	 * @return the thickness of the horizontal scroll bar of the current {@link BorderWidget}.
	 */
	public final int getHorizontalScrollBarThickness() {
		return (hasHorizontalScrollBar() ? SCROLL_BAR_THICKNESS : 0);
	}
	
	//method
	/**
	 * @return the main area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetMainArea getMainArea() {
		return mainArea;
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a max height.
	 */
	public final int getMaxHeight() {
		return maxHeight.getValue();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a max widt.
	 */
	public final int getMaxWidth() {
		return maxWidth.getValue();
	}
	
	//method
	/**
	 * @return the min height of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a min height.
	 */
	public final int getMinHeight() {
		return minHeight.getValue();
	}
	
	//method
	/**
	 * @return the min width of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a min width.
	 */
	public final int getMinWidth() {
		return minWidth.getValue();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidget}.
	 */
	public final int getNaturalHeight() {
		
		final var currentStructure = getRefLook();
		
		return
		currentStructure.getRecursiveOrDefaultTopBorderThickness()
		+ scrolledArea.getNaturalHeight()
		+ getHorizontalScrollBarThickness()
		+ currentStructure.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidget}.
	 */
	public final int getNaturalWidth() {
		
		final var currentStructure = getRefLook();
		
		return
		currentStructure.getRecursiveOrDefaultLeftBorderThickness()
		+ scrolledArea.getNaturalWidth()
		+ getVerticalScrollBarThickness()
		+ currentStructure.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a proposal height.
	 */
	public final int getProposalHeight() {
		return proposalHeight.getValue();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidget}.
	 * @throws ArgumentDoesNotHaveAttributeException if the current {@link BorderWidget} does not have a proposal width.
	 */
	public final int getProposalWidth() {
		return proposalWidth.getValue();
	}
	
	//method
	/**
	 * @return the scrolled area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetScrolledArea<BW, BWL> getScrolledArea() {
		return scrolledArea;
	}
	
	//method
	/**
	 * @return color of the vertical scroll bar of the current {@link BorderWidget}.
	 */
	public final Color getVerticalScrollBarColor() {
		
		final var look = getRefLook();
		
		if (!isMovingVerticalScrollBarCursor()) {
			
			if (!verticalScrollBarCursorIsUnderCursor()) {
				return look.getRecursiveOrDefaultBaseScrollBarLook().getScrollBarColor();
			}
			
			return look.getRecursiveOrDefaultHoverScrollBarLook().getScrollBarColor();
		}
		
		return look.getRecursiveOrDefaultSelectionScrollBarLook().getScrollBarColor();
	}
	
	//method
	/**
	 * @return color of the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	public final Color getVerticalScrollBarCursorColor() {
		
		final var look = getRefLook();
		
		if (!isMovingVerticalScrollBarCursor()) {
			
			if (!verticalScrollBarCursorIsUnderCursor()) {
				return look.getRecursiveOrDefaultBaseScrollBarLook().getScrollCursorColor();
			}
			
			return look.getRecursiveOrDefaultHoverScrollBarLook().getScrollCursorColor();
		}
			
		return look.getRecursiveOrDefaultSelectionScrollBarLook().getScrollCursorColor();
	}
	
	//method
	/**
	 * @return the thickness of the vertical scroll bar of the current {@link BorderWidget}.
	 */
	public final int getVerticalScrollBarThickness() {
		return (hasVerticalScrollBar() ? SCROLL_BAR_THICKNESS : 0);
	}
	
	//method
	/**
	 * @return the show area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetShowArea<BW, BWL> getShowArea() {
		return showArea;
	}
	
	//method
	/**
	 * @return x-position of the show area of the current {@link BorderWidget} on the scroll area.
	 */
	public final int getShowAreaXPositionOnScrolledArea() {
		return showAreaXPositionOnScrolledArea.getValue();
	}
	
	//method
	/**
	 * @return the y-position of the show area of the current {@link BorderWidget} on the scroll area.
	 */
	public final int getShowAreaYPositionOnScrolledArea() {
		return showAreaYPositionOnScrolledArea.getValue();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has any scroll bar.
	 */
	public final boolean hasAnyScrollBar() {
		return (hasVerticalScrollBar() || hasHorizontalScrollBar());
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidget} has automatic size.
	 */
	public final boolean hasAutomaticSize() {
		return automaticSize.getValue();
	}
	
	//method
	/**
	 * This method determines if the current {@link BorderWidget} has a horizontal scroll bar.
	 * 
	 * There can be the following issues.
	 * -The horizontal scroll bar is not there, but the user needs it.
	 * -The horizontal scroll bar is there, but the user does not need it.
	 * 
	 * No matter what result this method returns, the program works technically.
	 * This method makes a good but not absolute guess if the user needs a horizontal scroll bar.
	 * 
	 * @return true if the current {@link BorderWidget} has a horizontal scroll bar.
	 */
	public final boolean hasHorizontalScrollBar() {
		
		final var naturalScrolledAreaWidth = scrolledArea.getNaturalWidth();
		
		return
		(hasMaxWidth() && getMaxWidth() < naturalScrolledAreaWidth)
		|| (hasProposalWidth() && getProposalWidth() < naturalScrolledAreaWidth);
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
	 * @return true if the current {@link BorderWidget} has a vertical scroll bar.
	 */
	public final boolean hasVerticalScrollBar() {
		
		final var naturalScrolledAreaHeight = scrolledArea.getNaturalHeight();
		
		return
		(hasMaxHeight() && getMaxHeight() < naturalScrolledAreaHeight)
		|| (hasProposalHeight() && getProposalHeight() < naturalScrolledAreaHeight);
	}
	
	//method
	/**
	 * @return true if the user is moving any scroll bar cursor of the current {@link BorderWidget}.
	 */
	public final boolean isMovingAnyScrollBarCursor() {
		return (isMovingHorizontalScrollBarCursor() || isMovingVerticalScrollBarCursor);
	}
	
	//method
	/**
	 * @return true if the user is moving the horizontal scroll bar cursor of the current {@link BorderWidget}.
	 */
	public final boolean isMovingHorizontalScrollBarCursor() {
		return isMovingHorizontalScrollBarCursor;
	}
	
	//method
	/**
	 * @return true if the user is moving the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	public final boolean isMovingVerticalScrollBarCursor() {
		return isMovingVerticalScrollBarCursor;
	}
	
	//method
	/**
	 * Removes the max height of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMaxHeight() {
		
		maxHeight.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the max width of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMaxWidtht() {
		
		maxWidth.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the min height of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMinHeight() {
		
		minHeight.clear();
		
		return asConcrete();
	}
	
	//method
	/**
	 * Removes the min width of the current {@link BorderWidget}.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW removeMinWidth() {
		
		minWidth.clear();
		
		return asConcrete();
	}
	
	@Override
	public BW reset() {
		
		super.reset();
		
		deactivateAutomaticSize();
		showAreaXPositionOnScrolledArea.setValue(0);
		showAreaYPositionOnScrolledArea.setValue(0);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to bottom.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToBottom() {
		return setShowAreaYPositionOnScrolledArea(scrolledArea.getHeight());
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to left.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToLeft() {
		return setShowAreaXPositionOnScrolledArea(0);
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to right.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToRight() {
		return setShowAreaXPositionOnScrolledArea(scrolledArea.getWidth());
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to top.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToTop() {
		return setShowAreaYPositionOnScrolledArea(0);
	}
	
	//method
	/**
	 * Sets the content position of the current {@link BorderWidget}.
	 * 
	 * @param contentPosition
	 * @return the current {@link BorderWidget}.
	 * @throws ArgumentIsNullException if the given contentPosition is null.
	 */
	public final BW setContentPosition(final ContentPosition contentPosition) {
		
		//Sets the contentPosition of the current BorderWidget.
		this.contentPosition.setValue(contentPosition);
		
		return asConcrete();
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
		
		Validator.assertThat(maxHeight).thatIsNamed("max height").isPositive();
		
		this.maxHeight.setValue(maxHeight);
		
		return asConcrete();
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
		
		Validator.assertThat(maxWidth).thatIsNamed("max width").isPositive();
		
		this.maxWidth.setValue(maxWidth);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the min height of the current {@link BorderWidget}.
	 * 
	 * @param minHeight
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setMinHeight(final int minHeight) {
		
		this.minHeight.setValue(minHeight);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the min width of the current {@link BorderWidget}.
	 * 
	 * @param minWidth
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setMinWidth(final int minWidth) {
		
		this.minWidth.setValue(minWidth);
		
		return asConcrete();
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
		
		Validator.assertThat(proposalHeight).thatIsNamed("proposal height").isPositive();
		
		this.proposalHeight.setValue(proposalHeight);
		
		return asConcrete();
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
		
		return asConcrete();
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
		
		Validator.assertThat(proposalWidth).thatIsNamed("proposal with").isPositive();
		
		this.proposalWidth.setValue(proposalWidth);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the x-position of the show area on the scroll area of the current {@link BorderWidget}.
	 * 
	 * @param showAreaXPositionOnScrolledArea
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setShowAreaXPositionOnScrolledArea(int showAreaXPositionOnScrolledArea) {
		
		showAreaXPositionOnScrolledArea = Calculator.getMax(showAreaXPositionOnScrolledArea, 0);
		
		showAreaXPositionOnScrolledArea = Calculator.getMin(
			showAreaXPositionOnScrolledArea,
			scrolledArea.getWidth() - showArea.getWidth()
		);
		
		this.showAreaXPositionOnScrolledArea.setValue(showAreaXPositionOnScrolledArea);
		
		return asConcrete();
	}
	
	//method
	/**
	 * Sets the y-position of the show area on the scroll area of the current {@link BorderWidget}.
	 * 
	 * @param showAreaYPositionOnScrolledArea
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setShowAreaYPositionOnScrolledArea(int showAreaYPositionOnScrolledArea) {
		
		showAreaYPositionOnScrolledArea = Calculator.getMax(showAreaYPositionOnScrolledArea, 0);
		
		showAreaYPositionOnScrolledArea = Calculator.getMin(
			showAreaYPositionOnScrolledArea,
			scrolledArea.getHeight() - showArea.getHeight()
		);
		
		this.showAreaYPositionOnScrolledArea.setValue(showAreaYPositionOnScrolledArea);
		
		return asConcrete();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean showAreaIsUnderCursor() {
		return showArea.isUnderCursor();
	}
	
	//method declaration
	/**
	 * @return the height of the content area of the current {@link BorderWidget}.
	 */
	protected abstract int getContentAreaHeight();
	
	//method declaration
	/**
	 * @return the width of the content area of the current {@link BorderWidget}.
	 */
	protected abstract int getContentAreaWidth();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getContentAreaXPosition() {
		
		final BWL currentStructure = getRefLook();
		
		return
		currentStructure.getRecursiveOrDefaultLeftBorderThickness()
		- getShowAreaXPositionOnScrolledArea()
		+ contentArea.getXPositionOnScrolledArea();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getContentAreaYPosition() {
		
		final var look = getRefLook();
		
		return
		look.getRecursiveOrDefaultTopBorderThickness()
		- getShowAreaYPositionOnScrolledArea()
		+ contentArea.getYPositionOnScrolledArea();
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidget} when it is not collapsed.
	 */
	@Override
	protected final int getHeightWhenNotCollapsed() {
		
		final var look = getRefLook();
		
		return
		look.getRecursiveOrDefaultTopBorderThickness()		
		+ borderedArea.getHeight()
		+ look.getRecursiveOrDefaultBottomBorderThickness();
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
		
		final var look = getRefLook();
		
		return
		proposedContentAreaHeight
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultTopPadding()
		- look.getRecursiveOrDefaultBottomPadding()
		- look.getRecursiveOrDefaultBottomBorderThickness();
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
		
		final var look = getRefLook();
		
		return
		proposedContentAreaWidth
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultLeftPadding()
		- look.getRecursiveOrDefaultRightPadding()
		- look.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final int getWidthWhenExpanded() {
		
		final var look = getRefLook();
		
		return
		look.getRecursiveOrDefaultLeftBorderThickness()		
		+ borderedArea.getWidth()
		+ look.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * @return true if the cursor is over the horizontal scroll bar of the current {@link BorderWidget}.
	 */
	protected final boolean horizontalScrollBarIsUnderCursor() {
		
		//Handles the case that the current border widget does not have a horizontal scroll bar.
		if (!hasHorizontalScrollBar()) {
			return false;
		}
		
		//Handles the case that the current border widget has a horizontal scroll bar.
			final var cursorXPosition = getCursorXPosition();
			final var cursorYPosition = getCursorYPosition();
			final var horizontalScrollBarCursorXPosition = getHorizontalScrollBarCursorXPosition();
			final var horizontalScrollBarCursorYPosition = getHorizontalScrollBarCursorYPosition();
			
			return
			cursorXPosition >= horizontalScrollBarCursorXPosition
			&& cursorXPosition < horizontalScrollBarCursorXPosition + showArea.getWidth()
			&& cursorYPosition >= horizontalScrollBarCursorYPosition
			&& cursorYPosition < horizontalScrollBarCursorYPosition + getHorizontalScrollBarThickness();
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a key press on the content area
	 * for the case when the current {@link BorderWidget} is focused.
	 * 
	 * @param key
	 */
	protected abstract void noteKeyPressOnContentAreaWhenFocused(Key key);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyPressOnSelfWhenEnabled(final Key key) {
		if (isFocused()) {
			noteKeyPressOnContentAreaWhenFocused(key);
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyReleaseOnSelfWhenEnabled(final Key key) {
		if (isFocused()) {
			//TODO: noteKeyReleaseOnContentAreaWhenFocused(key)
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteKeyTypingOnSelfWhenEnabled(final Key key) {
		if (isFocused()) {
			//TODO: noteKeyReleaseOnContentAreaWhenFocused(key)
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a left mouse button click on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonClickOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonClickOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteLeftMouseButtonClickOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a left mouse button press on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonPressOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonPressOnSelfWhenEnabled() {
		
		//Handles the case that the vertical scroll bar cursor is under the cursor.
		if (verticalScrollBarCursorIsUnderCursor()) {
			
			isMovingVerticalScrollBarCursor = true;
			
			verticalScrollingCursorStartYPosition =
			getCursorYPosition()
			- getVerticalScrollBarCursorYPositionOnVerticalScrollBar();
		}
		
		//Handles the case that the horizontal scroll bar cursor is under the cursor.
		else if (horizontalScrollBarCursorIsUnderCursor()) {
			
			isMovingHorizontalScrollBarCursor = true;
			
			horizontalScrollingCursorStartXPosition =
			getCursorXPosition()
			- getHorizontalScrollBarCursorXPositionOnHorizontalScrollBar();
		}
		
		else if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteLeftMouseButtonPressOnContentAreaWhenEnabled();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteLeftMouseButtonReleaseOnSelfWhenEnabled() {
		
		isMovingHorizontalScrollBarCursor = false;
		isMovingVerticalScrollBarCursor = false;
		
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteLeftMouseButtonReleaseOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a left mouse button release on the show area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteLeftMouseButtonReleaseOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseMoveOnSelfWhenEnabled() {
				
		if (isMovingVerticalScrollBarCursor) {
			
			final var verticalScrollBarCursorYDelta =
			getCursorYPosition() - verticalScrollingCursorStartYPosition;
			
			final var showAreaHeight = showArea.getHeight();
			
			final var showAreaYDelta =
			(verticalScrollBarCursorYDelta * (scrolledArea.getHeight() - showAreaHeight))
			/ (showAreaHeight - getVerticalScrollBarCursorHeight());
			
			setShowAreaYPositionOnScrolledArea(showAreaYDelta);
		}
		
		else if (isMovingHorizontalScrollBarCursor) {
			
			final var horizontalScrollBarCursorXDelta =
			getCursorXPosition() - horizontalScrollingCursorStartXPosition;
			
			final var showAreaWidth = showArea.getWidth();
			
			final var showAreaXDelta =
			(horizontalScrollBarCursorXDelta * (scrolledArea.getWidth() - showAreaWidth))
			/ (showAreaWidth - getHorizontalScrollBarCursorWidth());
			
			setShowAreaXPositionOnScrolledArea(showAreaXDelta);
		}
		
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteMouseMoveOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a mouse move on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteMouseMoveOnContentAreaWhenEnabled();
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a mouse wheel click on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteMouseWheelClickOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelClickOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteMouseWheelClickOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a mouse wheel press on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteMouseWheelPressOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelPressOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteMouseWheelPressOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a mouse wheel release on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteMouseWheelReleaseOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void noteMouseWheelReleaseOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteMouseWheelReleaseOnContentAreaWhenEnabled();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelRotationStepOnSelfWhenEnabled(final RotationDirection rotationDirection) {
		if (isFocused()) {
			
			setShowAreaYPositionOnScrolledArea(
				getShowAreaYPositionOnScrolledArea()
				+ rotationDirection.toInt() * SHOW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP
			);
			
			//TODO: noteMouseWheelRotationStepOnSelfWhenFocused()
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a right mouse button click on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteRightMouseButtonClickOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonClickOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteRightMouseButtonClickOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a right mouse button press on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteRightMouseButtonPressOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonPressOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteRightMouseButtonPressOnContentAreaWhenEnabled();
		}
	}
	
	//method declaration
	/**
	 * Lets the current {@link BorderWidget} note a right mouse button release on the content area
	 * for the case when the current {@link BorderWidget} is enabled.
	 */
	protected abstract void noteRightMouseButtonReleaseOnContentAreaWhenEnabled();
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteRightMouseButtonReleaseOnSelfWhenEnabled() {
		if (showAreaIsUnderCursor() && getContentArea().isUnderCursor()) {
			noteRightMouseButtonReleaseOnContentAreaWhenEnabled();
		}
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		//Paints the left border if the given widget structure has an active left border thickness.
		if (borderWidgetLook.getRecursiveOrDefaultLeftBorderThickness() > 0) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultLeftBorderColor());
			
			painter.paintFilledRectangle(
				borderWidgetLook.getRecursiveOrDefaultLeftBorderThickness(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the right border if the given widget structure has an active right border thickness.
		if (borderWidgetLook.getRecursiveOrDefaultRightBorderThickness() > 0) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultRightBorderColor());
			
			painter.paintFilledRectangle(
				getWidth() - borderWidgetLook.getRecursiveOrDefaultLeftBorderThickness(),
				0,
				borderWidgetLook.getRecursiveOrDefaultLeftBorderThickness(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the top border if the given widget structure has an active top border thickness.
		if (borderWidgetLook.getRecursiveOrDefaultTopBorderThickness() > 0) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultTopBorderColor());
			
			painter.paintFilledRectangle(
				getWidth(),
				borderWidgetLook.getRecursiveOrDefaultTopBorderThickness()
			);
		}
		
		//Paints the bottom border if the given widget structure has an active bottom border thickness.
		if (borderWidgetLook.getRecursiveOrDefaultBottomBorderThickness() > 0) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultBottomBorderColor());
			
			painter.paintFilledRectangle(
				0,
				getHeightWhenNotCollapsed() - borderWidgetLook.getRecursiveOrDefaultBottomBorderThickness(),
				getWidth(),
				borderWidgetLook.getRecursiveOrDefaultBottomBorderThickness()
			);
		}
		
		//Paints the bordered area of the current border widget.
		borderedArea.paint(
			painter.createPainter(
				borderedArea.getXPosition(),
				borderedArea.getYPosition()
			),
			borderWidgetLook
		);
	}
	
	//method declaration
	/**
	 * Paints the content area of the current {@link BorderWidget} using the given borderWidgetLook and painter.
	 * 
	 * @param borderWidgetLook
	 * @param painter
	 */
	protected abstract void paintContentArea(BWL borderWidgetLook, IPainter painter);
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean paintsPaintableWidgetAPriori() {
		return false;
	}

	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean redirectsInputsToShownWidgets() {
		return (isEnabled() && (!hasAnyScrollBar() || showAreaIsUnderCursor()));
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void resetConfigurationOnSelf() {
		
		super.resetConfigurationOnSelf();
		
		setContentPosition(ContentPosition.LeftTop);
		removeMinWidth();
		removeMinHeight();
		removeMaxWidtht();
		removeMaxHeight();
		setShowAreaXPositionOnScrolledArea(0);
		setShowAreaYPositionOnScrolledArea(0);
	}
	
	//method
	/**
	 * @return true if the cursor is over the vertical scroll bar of the current {@link BorderWidget}.
	 */
	protected boolean verticalScrollBarIsUnderCursor() {
		
		//Handles the case that the current border widget does not have a vertical scroll bar.
		if (!hasVerticalScrollBar()) {
			return false;
		}
		
		//Handles the case that the current border widget has a vertical scroll bar.
			final var cursorXPosition = getCursorXPosition();
			final var cursorYPosition = getCursorYPosition();
			final var verticalScrollBarCursorXPosition = getVerticalScrollBarCursorXPosition();
			final var verticalScrollBarCursorYPosition = getVerticalScrollBarCursorYPosition();
						
			return
			cursorXPosition >= verticalScrollBarCursorXPosition
			&& cursorXPosition < verticalScrollBarCursorXPosition + getVerticalScrollBarThickness()
			&& cursorYPosition >= verticalScrollBarCursorYPosition
			&& cursorYPosition < verticalScrollBarCursorYPosition + showArea.getHeight();
	}
	
	//method
	int getHorizontalScrollBarCursorWidth() {
		return
		Calculator.getMax(
			(int)(Math.pow(showArea.getWidth(), 2) / scrolledArea.getWidth()),
			MIN_SCROLL_CURSOR_LENGTH
		);
	}
	
	//method
	/**
	 * @return the x-position of the horizontal scroll bar cursor.
	 */
	int getHorizontalScrollBarCursorXPosition() {
		return
		borderedArea.getXPosition()
		+ getHorizontalScrollBarCursorXPositionOnHorizontalScrollBar();
	}
	
	//method
	/**
	 * @return the x-position of the horizontal scroll bar cursor
	 * on the horizontal scroll bar of the current {@link BorderWidget}.
	 */
	int getHorizontalScrollBarCursorXPositionOnHorizontalScrollBar() {
				
		final var showAreaWidth = showArea.getWidth();
		
		return
		(showAreaWidth - getHorizontalScrollBarCursorWidth())
		* getShowAreaXPositionOnScrolledArea()
		/ (scrolledArea.getWidth() - showAreaWidth);
	}
	
	//method
	/**
	 * @return the y-position of the horizontal scroll bar cursor of the current {@link BorderWidget}
	 */
	int getHorizontalScrollBarCursorYPosition() {
		return (borderedArea.getYPosition() + getHorizontalScrollBarYPositionOnBorderedArea());
	}
	
	//method
	/**
	 * @return the y-position of the horizontal scroll bar
	 * on the bordered area of the current {@link BorderWidget}.
	 */
	int getHorizontalScrollBarYPositionOnBorderedArea() {
		return (borderedArea.getHeight() - getHorizontalScrollBarThickness());
	}
	
	//method
	/**
	 * @return the height of the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	int getVerticalScrollBarCursorHeight() {
		return
		Calculator.getMax(
			(int)(Math.pow(showArea.getHeight(), 2) / scrolledArea.getHeight()),
			MIN_SCROLL_CURSOR_LENGTH
		);
	}
	
	//method
	/**
	 * @return the x-position of the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	int getVerticalScrollBarCursorXPosition() {
		return
		borderedArea.getXPosition()
		+ getVerticalScrollBarXPositionOnBorderedArea();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	int getVerticalScrollBarCursorYPosition() {
		return
		borderedArea.getYPosition()
		+ getVerticalScrollBarCursorYPositionOnVerticalScrollBar();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scroll bar cursor
	 * on the vertical scroll bar of the current {@link BorderWidget}.
	 */
	int getVerticalScrollBarCursorYPositionOnVerticalScrollBar() {
		
		final var showAreaHeight = showArea.getHeight();
		
		return
		(showAreaHeight - getVerticalScrollBarCursorHeight())
		* getShowAreaYPositionOnScrolledArea()
		/ (scrolledArea.getHeight() - showAreaHeight);
	}
	
	//method
	/**
	 * @return the x-position of the vertical scroll bar
	 * on the bordered area of the current {@link BorderWidget}.
	 */
	int getVerticalScrollBarXPositionOnBorderedArea() {
		return (borderedArea.getWidth() - getVerticalScrollBarThickness());
	}
	
	//method
	/**
	 * @return true if a scroll bar cursor of the current {@link BorderWidget} is under the cursor.
	 */
	private boolean anyScrollBarCursorIsUnderCursor() {
		return (horizontalScrollBarCursorIsUnderCursor() || verticalScrollBarCursorIsUnderCursor());
	}
	
	//method
	/**
	 * @return true if the cursor is over the horizontal scroll bar cursor of the current {@link BorderWidget}.
	 */
	private boolean horizontalScrollBarCursorIsUnderCursor() {
		
		//Handles the case that the current border widget does not have a horizontal scroll bar.
		if (!hasHorizontalScrollBar()) {
			return false;
		}
		
		//Handles the case that the current border widget has a horizontal scroll bar.
			final var cursorXPosition = getCursorXPosition();
			final var cursorYPosition = getCursorYPosition();
			final var horizontalScrollBarCursorXPosition = getHorizontalScrollBarCursorXPosition();
			final var horizontalScrollBarCursorYPosition = getHorizontalScrollBarCursorYPosition();
			
			return
			cursorXPosition >= horizontalScrollBarCursorXPosition
			&& cursorXPosition < horizontalScrollBarCursorXPosition + getHorizontalScrollBarCursorWidth()
			&& cursorYPosition >= horizontalScrollBarCursorYPosition
			&& cursorYPosition < horizontalScrollBarCursorYPosition + getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return true if the cursor is over the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	private boolean verticalScrollBarCursorIsUnderCursor() {
		
		//Handles the case that the current border widget does not have a vertical scroll bar.
		if (!hasVerticalScrollBar()) {
			return false;
		}
		
		//Handles the case that the current border widget has a vertical scroll bar.
			final var cursorXPosition = getCursorXPosition();
			final var cursorYPosition = getCursorYPosition();
			final var verticalScrollBarCursorXPosition = getVerticalScrollBarCursorXPosition();
			final var verticalScrollBarCursorYPosition = getVerticalScrollBarCursorYPosition();
			
			return
			cursorXPosition >= verticalScrollBarCursorXPosition
			&& cursorXPosition < verticalScrollBarCursorXPosition + getVerticalScrollBarThickness()
			&& cursorYPosition >= verticalScrollBarCursorYPosition
			&& cursorYPosition < verticalScrollBarCursorYPosition + getVerticalScrollBarCursorHeight();
	}
}
