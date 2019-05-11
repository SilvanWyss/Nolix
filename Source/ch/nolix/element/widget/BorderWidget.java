//package declaration
package ch.nolix.element.widget;

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
 * 4. bordered area: Contains the probable scrollbars and view area.
 * 5. view area: Is over the scroll area and is like a hole to look on the scroll area below.
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
extends BackgroundWidget<BW, BWL> {
	
	//constant
	public static final String TYPE_NAME = "Borderablewidget";
	
	//constant
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
	private static final String VIEW_AREA_X_POSITION_ON_SCROLLED_AREA_HEADER = "ViewAreaXPositionOnScrolledArea";
	private static final String VIEW_AREA_Y_POSITION_ON_SCROLLED_AREA_HEADER = "ViewAreaYPositionOnScrolledArea";
	
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
	private final MutableProperty<NonNegativeInteger> viewAreaXPositionOnScrolledArea =
	new MutableProperty<NonNegativeInteger>(
		VIEW_AREA_X_POSITION_ON_SCROLLED_AREA_HEADER,
		x -> setViewAreaXPositionOnScrolledArea(x.getValue()),
		s -> NonNegativeInteger.createFromSpecification(s),
		x -> x.getSpecification()
	);
	
	//attribute
	private final MutableProperty<NonNegativeInteger> viewAreaYPositionOnScrolledArea =
	new MutableProperty<NonNegativeInteger>(
		VIEW_AREA_Y_POSITION_ON_SCROLLED_AREA_HEADER,
		y -> setViewAreaYPositionOnScrolledArea(y.getValue()),
		s -> NonNegativeInteger.createFromSpecification(s),
		y -> y.getSpecification()
	);
	
	//attributes
	private final BorderWidgetScrolledArea<BW, BWL> scrolledArea = new BorderWidgetScrolledArea<>(this);
	private final BorderWidgetContentArea<BW, BWL> contentArea = new BorderWidgetContentArea<>(this);
	private final BorderWidgetViewArea<BW, BWL> viewArea = new BorderWidgetViewArea<>(this);
	
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
	 * @return the x-position of the cursor on the scroll area of the current {@link BorderWidget}.
	 */
	public final int getCursorXPositionOnScrolledArea() {
		return (getCursorXPosition() - getViewAreaXPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the scroll area of the current {@link BorderWidget}.
	 */
	public final int getCursorYPositionOnScrolledArea() {
		return (getCursorYPosition() - getViewAreaYPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return color of the horizontal scrollbar of the current {@link BorderWidget}.
	 */
	public final Color getHorizontalScrollbarColor() {
		
		final BWL currentLook = getRefCurrentLook();
		
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
		
		final BWL currentLook = getRefCurrentLook();
		
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
		
		final BWL currentStructure = getRefCurrentLook();
		
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
		
		final BWL currentStructure = getRefCurrentLook();
		
		return
		currentStructure.getRecursiveOrDefaultLeftBorderThickness()
		+ scrolledArea.getNaturalWidth()
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
		
		final BWL currentLook = getRefCurrentLook();
		
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
		
		final BWL currentLook = getRefCurrentLook();
		
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
	 * @return the view area of the current {@link BorderWidget}.
	 */
	public final BorderWidgetViewArea<BW, BWL> getViewArea() {
		return viewArea;
	}
	
	//method
	/**
	 * @return x-position of the view area of the current {@link BorderWidget} on the scroll area.
	 */
	public final int getViewAreaXPositionOnScrolledArea() {
		return viewAreaXPositionOnScrolledArea.getValue().getValue();
	}
	
	//method
	/**
	 * @return the y-position of the view area of the current {@link BorderWidget} on the scroll area.
	 */
	public final int getViewAreaYPositionOnScrolledArea() {
		return viewAreaYPositionOnScrolledArea.getValue().getValue();
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
			
			final var viewAreaHeight = viewArea.getHeight();
			
			final var viewAreaYDelta =
			(verticalScrollbarCursorYDelta * (scrolledArea.getHeight() - viewAreaHeight))
			/ (viewAreaHeight - getVerticalScrollbarCursorHeight());
			
			final var viewAreaYPositionOnScrolledArea = viewAreaYDelta;
			
			setViewAreaYPositionOnScrolledArea(viewAreaYPositionOnScrolledArea);
		}
		
		if (isMovingHorizontalScrollbarCursor) {
			
			final var horizontalScrollbarCursorXDelta =
			getCursorXPosition() - horizontalScrollingCursorStartXPosition;
			
			final var viewAreaWidth = viewArea.getWidth();
			
			final var viewAreaXDelta =
			(horizontalScrollbarCursorXDelta * (scrolledArea.getWidth() - viewAreaWidth))
			/ (viewAreaWidth - getHorizontalScrollbarCursorWidth());
			
			final var viewAreaXPositionOnScrolledArea = viewAreaXDelta;
			
			setViewAreaXPositionOnScrolledArea(viewAreaXPositionOnScrolledArea);
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
				setViewAreaYPositionOnScrolledArea(
					getViewAreaYPositionOnScrolledArea()
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
		setViewAreaXPositionOnScrolledArea(0);
		setViewAreaYPositionOnScrolledArea(0);
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to bottom.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToBottom() {
		return setViewAreaYPositionOnScrolledArea(scrolledArea.getHeight());
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to left.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToLeft() {
		return setViewAreaXPositionOnScrolledArea(0);
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to right.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToRight() {
		return setViewAreaXPositionOnScrolledArea(scrolledArea.getWidth());
	}
	
	//method
	/**
	 * Lets the current {@link BorderWidget} scroll to top.
	 * 
	 * @return the current {@link BorderWidget}.
	 */
	public final BW scrollToTop() {
		return setViewAreaYPositionOnScrolledArea(0);
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
	public final void setCursorPosition(final int cursorXPosition, final int cursorYPosition) {
		
		this.cursorXPosition = cursorXPosition;
		this.cursorYPosition = cursorYPosition;
		
		getTriggerableChildWidgets()
		.forEach(
			cw -> cw.setParentCursorPosition(getCursorXPositionOnContentArea(), getCursorYPositionOnContentArea())
		);
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
	 * @param viewAreaXPositionOnScrolledArea
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setViewAreaXPositionOnScrolledArea(int viewAreaXPositionOnScrolledArea) {
		
		viewAreaXPositionOnScrolledArea = Calculator.getMax(viewAreaXPositionOnScrolledArea, 0);
		
		viewAreaXPositionOnScrolledArea = Calculator.getMin(
			viewAreaXPositionOnScrolledArea,
			scrolledArea.getWidth() - viewArea.getWidth()
		);
		
		this
		.viewAreaXPositionOnScrolledArea
		.setValue(new NonNegativeInteger(viewAreaXPositionOnScrolledArea));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * Sets the y-position of the view area on the scroll area of the current {@link BorderWidget}.
	 * 
	 * @param viewAreaYPositionOnScrolledArea
	 * @return the current {@link BorderWidget}.
	 */
	public final BW setViewAreaYPositionOnScrolledArea(int viewAreaYPositionOnScrolledArea) {
		
		viewAreaYPositionOnScrolledArea = Calculator.getMax(viewAreaYPositionOnScrolledArea, 0);
		
		viewAreaYPositionOnScrolledArea = Calculator.getMin(
			viewAreaYPositionOnScrolledArea,
			scrolledArea.getHeight() - viewArea.getHeight()
		);
		
		this
		.viewAreaYPositionOnScrolledArea
		.setValue(new NonNegativeInteger(viewAreaYPositionOnScrolledArea));
		
		return asConcreteType();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void fillUpTriggerableChildWidgets(final List<Widget<?, ?>> list) {
		
		//Handles the case that the view area of the current BorderWidget is under the cursor.
		if (viewAreaIsUnderCursor()) {
			
			//Calls method of the base class.
			super.fillUpTriggerableChildWidgets(list);
		}
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
		
		final BWL currentStructure = getRefCurrentLook();
		
		return
		currentStructure.getRecursiveOrDefaultLeftBorderThickness()
		- getViewAreaXPositionOnScrolledArea()
		+ contentArea.getXPositionOnScrolledArea();
	}
	
	//method
	/**
	 * @return the y-position of the content area of the current {@link BorderWidget}.
	 */
	protected final int getContentAreaYPosition() {
		
		final BWL currentStructure = getRefCurrentLook();
		
		return
		currentStructure.getRecursiveOrDefaultTopBorderThickness()
		- getViewAreaYPositionOnScrolledArea()
		+ contentArea.getYPositionOnScrolledArea();
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
		
		final BWL currentLook = getRefCurrentLook();
		
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
		
		final BWL currentLook = getRefCurrentLook();
		
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
		
		final BWL currentLook = getRefCurrentLook();
		
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
		
		final BWL currentLook = getRefCurrentLook();
		
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
		final BWL widgetStructure,
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
	protected void paintContentArea(BWL borderWidgetLook, IPainter painter) {}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final boolean viewAreaIsUnderCursor() {
		return viewArea.isUnderCursor();
	}
	
	//method
	/**
	 * @return the height of the bordered area of the current {@link BorderWidget}.
	 */
	private int getBorderedAreaHeight() {
		return 
		viewArea.getHeight()
		+ getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the width of the bordered area of the current {@link BorderWidget}.
	 */
	private int getBorderedAreaWidth() {
		return 
		viewArea.getWidth()
		+ getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the x-position of the bordered area of the current {@link BorderWidget}.
	 */
	private int getBorderedAreaXPosition() {
		
		final BWL currentStructure = getRefCurrentLook();
		
		return currentStructure.getRecursiveOrDefaultLeftBorderThickness();
	}
	
	//method
	/**
	 * @return the y-position of the bordered area of the current {@link BorderWidget}.
	 */
	private int getBorderedAreaYPosition() {
		
		final BWL currentStructure = getRefCurrentLook();
		
		return currentStructure.getRecursiveOrDefaultTopBorderThickness();
	}
	
	//method
	private int getHorizontalScrollbarCursorWidth() {
		return
		Calculator.getMax(
			(int)(Math.pow(viewArea.getWidth(), 2) / scrolledArea.getWidth()),
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
				
		final var viewAreaWidth = viewArea.getWidth();
		
		return
		(viewAreaWidth - getHorizontalScrollbarCursorWidth())
		* getViewAreaXPositionOnScrolledArea()
		/ (scrolledArea.getWidth() - viewAreaWidth);
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
	/**
	 * @return the height of the vertical scrollbar cursor of the current {@link BorderWidget}.
	 */
	private int getVerticalScrollbarCursorHeight() {
		return
		Calculator.getMax(
			(int)(Math.pow(viewArea.getHeight(), 2) / scrolledArea.getHeight()),
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
		
		final var viewAreaHeight = viewArea.getHeight();
		
		return
		(viewAreaHeight - getVerticalScrollbarCursorHeight())
		* getViewAreaYPositionOnScrolledArea()
		/ (scrolledArea.getHeight() - viewAreaHeight);
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
			&& cursorXPosition < horizontalScrollbarCursorXPosition + viewArea.getWidth()
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
		final BWL borderWidgetStructure,
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
					viewArea.getHeight()
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
					viewArea.getWidth(),
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
		viewArea.paint(
			borderWidgetStructure,
			painter.createPainter(
				0,
				0,
				viewArea.getWidth(),				
				viewArea.getHeight()
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
			&& cursorYPosition < verticalScrollbarCursorYPosition + viewArea.getHeight();
	}
}
