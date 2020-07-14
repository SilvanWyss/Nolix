//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.common.invalidArgumentException.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentException.InvalidArgumentException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.node.BaseNode;
import ch.nolix.common.node.Node;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.ValueCatalogue;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.base.MutableOptionalProperty;
import ch.nolix.element.base.MutableProperty;
import ch.nolix.element.color.Color;
import ch.nolix.element.elementEnum.ContentPosition;
import ch.nolix.element.elementEnum.DirectionOfRotation;
import ch.nolix.element.input.Key;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link BorderWidget} is a {@link BackgroundWidget} that:
 * -Can have an individual border at each of its 4 sides.
 * -Can have a min size,
 *  that has the effect that the content of the {@link BackgroundWidget is moved inside its borders.
 * -Can have a max size, that has the effect that the {@link BackgroundWidget becomes scrollable.
 * 
 * A {@link BackgroundWidget consists of the following areas from outer to inner.
 * 1. widget area: Contains the probable shadows area and caption area.
 * 2. caption area: Contains the probable captions and main area.
 * 3. main area: Contains the probable borders and bordered area.
 * 4. bordered area: Contains the probable scrollbars and show area.
 * 5. show area: Is over the scrolled area and is like a hole to look on the scrolled area below.
 * 6. scrolled area: Contains the probable paddings and content area.
 * 7. content area: Contains the content.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 1560
 * @param <BW> The type of a {@link BackgroundWidget.
 * @param <BWL> The type of the {@link BorderWidgetLook}s of a {@link BackgroundWidget.
 */
public abstract class BorderWidget<BW extends BorderWidget<BW, BWL>,BWL extends BorderWidgetLook<BWL>>
extends Widget<BW, BWL> {
	
	//constant
	public static final String TYPE_NAME = "Borderablewidget";
	
	//constant
	private static final int SHOW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP = 50;
	
	//constant
	private static final int MIN_SCROLL_CURSOR_LENGTH = 20;
	
	//constants
	private static final String MIN_WIDTH_HEADER = "MinWidth";
	private static final String MIN_HEIGHT_HEADER = "MinHeight";
	private static final String MAX_WIDTH_HEADER = "MaxWidth";
	private static final String MAX_HEIGHT_HEADER = "MaxHeight";
	private static final String PROPOSAL_WIDTH_HEADER = "ProposalWidth";
	private static final String PROPOSAL_HEIGHT_HEADER = "ProposalHeight";
	private static final String SHOW_AREA_X_POSITION_ON_SCROLLED_AREA_HEADER = "ShowAreaXPositionOnScrolledArea";
	private static final String SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA_HEADER = "ShowAreaYPositionOnScrolledArea";
	
	//attribute
	private ContentPosition contentPosition;
	
	//attribute
	private final MutableOptionalProperty<Integer> minWidth =
	new MutableOptionalProperty<>(
		MIN_WIDTH_HEADER,
		mw -> setMinWidth(mw),
		BaseNode::getOneAttributeAsInt,
		mw -> Node.withOneAttribute(mw)
	);
	
	//attribute
	private final MutableOptionalProperty<Integer> minHeight =
	new MutableOptionalProperty<>(
		MIN_HEIGHT_HEADER,
		mh -> setMinHeight(mh),
		BaseNode::getOneAttributeAsInt,
		mh -> Node.withOneAttribute(mh)
	);
	
	//attribute
	private final MutableOptionalProperty<Integer> maxWidth =
	new MutableOptionalProperty<>(
		MAX_WIDTH_HEADER,
		mw -> setMaxWidth(mw),
		BaseNode::getOneAttributeAsInt,
		mw -> Node.withOneAttribute(mw)
	);
	
	//attribute
	private final MutableOptionalProperty<Integer> maxHeight =
	new MutableOptionalProperty<>(
		MAX_HEIGHT_HEADER,
		mh -> setMaxHeight(mh),
		BaseNode::getOneAttributeAsInt,
		mh -> Node.withOneAttribute(mh)
	);
	
	//attribute
	private final MutableOptionalProperty<Integer> proposalWidth =
	new MutableOptionalProperty<>(
		PROPOSAL_WIDTH_HEADER,
		pw -> setProposalWidth(pw),
		BaseNode::getOneAttributeAsInt,
		pw ->  Node.withOneAttribute(pw)
	);
	
	//attribute
	private final MutableOptionalProperty<Integer> proposalHeight =
	new MutableOptionalProperty<>(
		PROPOSAL_HEIGHT_HEADER,
		ph -> setProposalHeight(ph),
		BaseNode::getOneAttributeAsInt,
		ph ->  Node.withOneAttribute(ph)
	);
	
	//attribute
	private final MutableProperty<Integer> showAreaXPositionOnScrolledArea =
	new MutableProperty<>(
		SHOW_AREA_X_POSITION_ON_SCROLLED_AREA_HEADER,
		x -> setShowAreaXPositionOnScrolledArea(x),
		BaseNode::getOneAttributeAsInt,
		x -> Node.withOneAttribute(x)
	);
	
	//attribute
	private final MutableProperty<Integer> showAreaYPositionOnScrolledArea =
	new MutableProperty<>(
		SHOW_AREA_Y_POSITION_ON_SCROLLED_AREA_HEADER,
		y -> setShowAreaYPositionOnScrolledArea(y),
		BaseNode::getOneAttributeAsInt,
		y -> Node.withOneAttribute(y)
	);
	
	//attributes
	private final BorderWidgetMainArea mainArea = new BorderWidgetMainArea(this);
	private final BorderWidgetBorderedArea<BW, BWL> borderedArea = new BorderWidgetBorderedArea<>(this);
	private final BorderWidgetShowArea<BW, BWL> showArea = new BorderWidgetShowArea<>(this);
	private final BorderWidgetScrolledArea<BW, BWL> scrolledArea = new BorderWidgetScrolledArea<>(this);
	private final BorderWidgetContentArea<BW, BWL> contentArea = new BorderWidgetContentArea<>(this);
	
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
	public void addOrChangeAttribute(final BaseNode attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case ContentPosition.TYPE_NAME:
				
				setContentPosition(
					ContentPosition.fromSpecification(attribute)
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
		
		if (!showArea.isUnderCursor()) {
			return CursorIcon.Arrow;
		}
		
		return super.getCursorIcon();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public LinkedList<Node> getAttributes() {
		
		//Calls method of the base class
		final LinkedList<Node> attributes = super.getAttributes();
		
		attributes.addAtEnd(contentPosition.getSpecification());

		return attributes;
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
		return contentPosition;
	}
	
	//method
	/**
	 * @return color of the horizontal scrollbar of the current {@link BorderWidget}.
	 */
	public final Color getHorizontalScrollbarColor() {
		
		final BWL look = getRefLook();
		
		if (!isMovingHorizontalScrollbarCursor()) {
			if (!horizontalScrollbarCursorIsUnderCursor()) {
				return
				look.getRecursiveOrDefaultBaseScrollbarLook().getScrollbarColor();
			}
			else {
				return
				look.getRecursiveOrDefaultHoverScrollbarLook().getScrollbarColor();
			}
		}
		else {
			return
			look.getRecursiveOrDefaultSelectionScrollbarLook().getScrollbarColor();
		}
	}
	
	//method
	/**
	 * @return color of the horizontal scrollbar cursor of the current {@link BorderWidget}.
	 */
	public final Color getHorizontalScrollbarCursorColor() {
		
		final var look = getRefLook();
		
		if (!isMovingHorizontalScrollbarCursor()) {
			if (!horizontalScrollbarCursorIsUnderCursor()) {
				return look.getRecursiveOrDefaultBaseScrollbarLook().getScrollbarCursorColor();
			}
			else {
				return look.getRecursiveOrDefaultHoverScrollbarLook().getScrollbarCursorColor();
			}
		}
		else {
			return look.getRecursiveOrDefaultSelectionScrollbarLook().getScrollbarCursorColor();
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
	 * The natural height of a border widget is its height when the border widget:
	 * -Is not collapsed.
	 * -Does not have a min height.
	 * -Does not have a max height.
	 * 
	 * @return the natural height of the current {@link BorderWidget}.
	 */
	public final int getNaturalHeight() {
		
		final BWL currentStructure = getRefLook();
		
		return
		currentStructure.getRecursiveOrDefaultTopBorderThickness()
		+ scrolledArea.getNaturalHeight()
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
		
		final BWL currentStructure = getRefLook();
		
		return
		currentStructure.getRecursiveOrDefaultLeftBorderThickness()
		+ scrolledArea.getNaturalWidth()
		+ getVerticalScrollbarThickness()
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
	 * @return color of the vertical scrollbar of the current {@link BorderWidget}.
	 */
	public final Color getVerticalScrollbarColor() {
		
		final var look = getRefLook();
		
		if (!isMovingVerticalScrollbarCursor()) {
			if (!verticalScrollbarCursorIsUnderCursor()) {
				return look.getRecursiveOrDefaultBaseScrollbarLook().getScrollbarColor();
			}
			else {
				return look.getRecursiveOrDefaultHoverScrollbarLook().getScrollbarColor();
			}
		}
		else {
			return look.getRecursiveOrDefaultSelectionScrollbarLook().getScrollbarColor();
		}
	}
	
	//method
	/**
	 * @return color of the vertical scrollbar cursor of the current {@link BorderWidget}.
	 */
	public final Color getVerticalScrollbarCursorColor() {
		
		final var look = getRefLook();
		
		if (!isMovingVerticalScrollbarCursor()) {
			if (!verticalScrollbarCursorIsUnderCursor()) {
				return look.getRecursiveOrDefaultBaseScrollbarLook().getScrollbarCursorColor();
			}
			else {
				return look.getRecursiveOrDefaultHoverScrollbarLook().getScrollbarCursorColor();
			}
		}
		else {
			return look.getRecursiveOrDefaultSelectionScrollbarLook().getScrollbarCursorColor();
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
	 * @return true if the current {@link BorderWidget} has any scrollbar.
	 */
	public final boolean hasAnyScrollbar() {
		return (hasVerticalScrollbar() || hasHorizontalScrollbar());
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
	 * @return true if the current {@link BorderWidget} has a vertical scrollbar.
	 */
	public final boolean hasVerticalScrollbar() {
		
		final var naturalScrolledAreaHeight = scrolledArea.getNaturalHeight();
		
		return
		(hasMaxHeight() && getMaxHeight() < naturalScrolledAreaHeight)
		|| (hasProposalHeight() && getProposalHeight() < naturalScrolledAreaHeight);
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
		
		showAreaXPositionOnScrolledArea.setValue(0);
		showAreaYPositionOnScrolledArea.setValue(0);
		
		return asConcrete();
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
		setShowAreaXPositionOnScrolledArea(0);
		setShowAreaYPositionOnScrolledArea(0);
		
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
	 * @throws ArgumentIsNullException if the given content position is null.
	 */
	public final BW setContentPosition(final ContentPosition contentPosition) {
		
		//Asserts that the given content position is not null.
		Validator.assertThat(contentPosition).isOfType(ContentPosition.class);

		//Sets the content position of the current border widget.
		this.contentPosition = contentPosition;
		
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
	 * @return the width of the current {@link BorderWidget} when it is not collapsed.
	 */
	@Override
	protected final int getWidthWhenNotCollapsed() {
		
		final var look = getRefLook();
		
		return
		look.getRecursiveOrDefaultLeftBorderThickness()		
		+ borderedArea.getWidth()
		+ look.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * @return true if the cursor is over the horizontal scrollbar of the current {@link BorderWidget}.
	 */
	protected final boolean horizontalScrollbarIsUnderCursor() {
		
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
			&& cursorXPosition < horizontalScrollbarCursorXPosition + showArea.getWidth()
			&& cursorYPosition >= horizontalScrollbarCursorYPosition
			&& cursorYPosition < horizontalScrollbarCursorYPosition + getHorizontalScrollbarThickness();
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
				
		//Handles the case that the vertical scrollbar cursor is under the cursor.
		if (verticalScrollbarCursorIsUnderCursor()) {
			
			isMovingVerticalScrollbarCursor = true;
			
			verticalScrollingCursorStartYPosition =
			getCursorYPosition()
			- getVerticalScrollbarCursorYPositionOnVerticalScrollbar();
		}
		
		//Handles the case that the horizontal scrollbar cursor is under the cursor.
		else if (horizontalScrollbarCursorIsUnderCursor()) {
			
			isMovingHorizontalScrollbarCursor = true;
			
			horizontalScrollingCursorStartXPosition =
			getCursorXPosition()
			- getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar();
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
		
		isMovingHorizontalScrollbarCursor = false;
		isMovingVerticalScrollbarCursor = false;
		
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
				
		if (isMovingVerticalScrollbarCursor) {
			
			final var verticalScrollbarCursorYDelta =
			getCursorYPosition() - verticalScrollingCursorStartYPosition;
			
			final var showAreaHeight = showArea.getHeight();
			
			final var showAreaYDelta =
			(verticalScrollbarCursorYDelta * (scrolledArea.getHeight() - showAreaHeight))
			/ (showAreaHeight - getVerticalScrollbarCursorHeight());
			
			setShowAreaYPositionOnScrolledArea(showAreaYDelta);
		}
		
		else if (isMovingHorizontalScrollbarCursor) {
			
			final var horizontalScrollbarCursorXDelta =
			getCursorXPosition() - horizontalScrollingCursorStartXPosition;
			
			final var showAreaWidth = showArea.getWidth();
			
			final var showAreaXDelta =
			(horizontalScrollbarCursorXDelta * (scrolledArea.getWidth() - showAreaWidth))
			/ (showAreaWidth - getHorizontalScrollbarCursorWidth());
			
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
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final void noteMouseWheelRotationStepOnSelfWhenEnabled(final DirectionOfRotation directionOfRotation) {
		if (isFocused()) {
			
			setShowAreaYPositionOnScrolledArea(
				getShowAreaYPositionOnScrolledArea()
				+ directionOfRotation.toInt() * SHOW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP
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
		return (isEnabled() && (!hasAnyScrollbar() || showAreaIsUnderCursor()));
	}
	
	//method
	/**
	 * @return true if the cursor is over the vertical scrollbar of the current {@link BorderWidget}.
	 */
	protected boolean verticalScrollbarIsUnderCursor() {
		
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
			&& cursorYPosition < verticalScrollbarCursorYPosition + showArea.getHeight();
	}
	
	//method
	int getHorizontalScrollbarCursorWidth() {
		return
		Calculator.getMax(
			(int)(Math.pow(showArea.getWidth(), 2) / scrolledArea.getWidth()),
			MIN_SCROLL_CURSOR_LENGTH
		);
	}
	
	//method
	/**
	 * @return the x-position of the horizontal scrollbar cursor.
	 */
	int getHorizontalScrollbarCursorXPosition() {
		return
		borderedArea.getXPosition()
		+ getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar();
	}
	
	//method
	/**
	 * @return the x-position of the horizontal scrollbar cursor
	 * on the horizontal scrollbar of this broder widget.
	 */
	int getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar() {
				
		final var showAreaWidth = showArea.getWidth();
		
		return
		(showAreaWidth - getHorizontalScrollbarCursorWidth())
		* getShowAreaXPositionOnScrolledArea()
		/ (scrolledArea.getWidth() - showAreaWidth);
	}
	
	//method
	/**
	 * @return the y-position of the horizontal scrollbar cursor of the current {@link BorderWidget}
	 */
	int getHorizontalScrollbarCursorYPosition() {
		return
		borderedArea.getYPosition()
		+ getHorizontalScrollbarYPositionOnBorderedArea();
	}
	
	//method
	/**
	 * @return the y-position of the horizontal scrollbar
	 * on the bordered area of the current {@link BorderWidget}.
	 */
	int getHorizontalScrollbarYPositionOnBorderedArea() {
		return (borderedArea.getHeight() - getHorizontalScrollbarThickness());
	}
	
	//method
	/**
	 * @return the height of the vertical scrollbar cursor of the current {@link BorderWidget}.
	 */
	int getVerticalScrollbarCursorHeight() {
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
	int getVerticalScrollbarCursorXPosition() {
		return
		borderedArea.getXPosition()
		+ getVerticalScrollbarXPositionOnBorderedArea();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scroll bar cursor of the current {@link BorderWidget}.
	 */
	int getVerticalScrollbarCursorYPosition() {
		return
		borderedArea.getYPosition()
		+ getVerticalScrollbarCursorYPositionOnVerticalScrollbar();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scrollbar cursor
	 * on the vertical scrollbar of the current {@link BorderWidget}.
	 */
	int getVerticalScrollbarCursorYPositionOnVerticalScrollbar() {
		
		final var showAreaHeight = showArea.getHeight();
		
		return
		(showAreaHeight - getVerticalScrollbarCursorHeight())
		* getShowAreaYPositionOnScrolledArea()
		/ (scrolledArea.getHeight() - showAreaHeight);
	}
	
	//method
	/**
	 * @return the x-position of the vertical scrollbar
	 * on the bordered area of the current {@link BorderWidget}.
	 */
	int getVerticalScrollbarXPositionOnBorderedArea() {
		return (borderedArea.getWidth() - getVerticalScrollbarThickness());
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
}
