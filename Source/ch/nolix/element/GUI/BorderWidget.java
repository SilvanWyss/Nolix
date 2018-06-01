//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.entity.MutableOptionalProperty;
import ch.nolix.core.entity.MutableProperty;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.NonNegativeInteger;
import ch.nolix.element.core.PositiveInteger;
import ch.nolix.element.painter.IPainter;
import ch.nolix.primitive.invalidStateException.InvalidStateException;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.validator2.Validator;

//abstract class
/**
 * A border widget is a background widget that:
 * -Can have an individual border at each of its 4 sides.
 * -Can have a min size
 *  that has the effect that the content of the border widget is moved inside its borders.
 * -Can have a max size
 *  that has the effect that the border widget becomes scrollable.
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
 * @lines 1560
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
		s -> PositiveInteger.createFromSpecification(s)
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> minHeight =
	new MutableOptionalProperty<PositiveInteger>(
		MIN_HEIGHT_HEADER,
		mh -> setMinHeight(mh.getValue()),
		s -> PositiveInteger.createFromSpecification(s)
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> maxWidth =
	new MutableOptionalProperty<PositiveInteger>(
		MAX_WIDTH_HEADER,
		mw -> setMaxWidth(mw.getValue()),
		s -> PositiveInteger.createFromSpecification(s)
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> maxHeight =
	new MutableOptionalProperty<PositiveInteger>(
		MAX_HEIGHT_HEADER,
		mh -> setMaxHeight(mh.getValue()),
		s -> PositiveInteger.createFromSpecification(s)
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> proposalWidthProperty =
	new MutableOptionalProperty<PositiveInteger>(
		PROPOSAL_WIDTH_HEADER,
		pw -> setProposalWidth(pw.getValue()),
		s -> PositiveInteger.createFromSpecification(s)
	);
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> proposalHeightProperty =
	new MutableOptionalProperty<PositiveInteger>(
		PROPOSAL_HEIGHT_HEADER,
		ph -> setProposalWidth(ph.getValue()),
		s -> PositiveInteger.createFromSpecification(s)
	);
	
	//attribute
	private final MutableProperty<NonNegativeInteger> viewAreaXPositionOnScrollArea =
	new MutableProperty<NonNegativeInteger>(
		VIEW_AREA_X_POSITION_ON_SCROLL_AREA_HEADER,
		x -> setViewAreaXPositionOnScrollArea(x.getValue()),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//attribute
	private final MutableProperty<NonNegativeInteger> viewAreaYPositionOnScrollArea =
	new MutableProperty<NonNegativeInteger>(
		VIEW_AREA_Y_POSITION_ON_SCROLL_AREA_HEADER,
		y -> setViewAreaYPositionOnScrollArea(y.getValue()),
		s -> NonNegativeInteger.createFromSpecification(s)
	);
	
	//attributes
	private boolean isMovingVerticalScrollbarCursor = false;
	private boolean isMovingHorizontalScrollbarCursor = false;
	
	//optional attributes
	private int verticalScrollingCursorStartYPosition;
	private int horizontalScrollingCursorStartXPosition;
	
	//method
	/**
	 * Adds or changes the given attribute to this border widget.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
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
	 * @return the attributes of this border widget.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class
		final List<StandardSpecification> attributes = super.getAttributes();
		
		attributes.addAtEnd(contentPosition.getSpecification());

		return attributes;
	}
	
	//method
	/**
	 * @return the content position of this border widget.
	 */
	public final ContentPosition getContentPosition() {
		return contentPosition;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the scroll area of this border widget.
	 */
	public final int getCursorXPositionOnScrollArea() {
		return (getCursorXPosition() - getViewAreaXPositionOnScrollArea());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the scroll area of this border widget.
	 */
	public final int getCursorYPositionOnScrollArea() {
		return (getCursorYPosition() - getViewAreaYPositionOnScrollArea());
	}
	
	//method
	/**
	 * @return the thickness of the horizontal scrollbar of this border widget.
	 */
	public final int getHorizontalScrollbarThickness() {
		
		//Handles the case that this border widget has no horizontal scrollbar.
		if (!hasHorizontalScrollbar()) {
			return 0;
		}
		
		//Handles the case that this border widget has a horizontal scrollbar.
		//TODO: Add scrollbar thickness to border widget structure.
		return 20;
	}
	
	//method
	/**
	 * @return the max height of this border widget.
	 * @throws UnexistingAttributeException if this border widget has no max height.
	 */
	public final int getMaxHeight() {
		return maxHeight.getValue().getValue();
	}
	
	//method
	/**
	 * @return the max width of this border widget.
	 * @throws UnexistingAttributeException if this border widget has no max widt.
	 */
	public final int getMaxWidth() {
		return maxWidth.getValue().getValue();
	}
	
	//method
	/**
	 * @return the min height of this border widget.
	 * @throws UnexistingAttributeException if this border widget has no min height.
	 */
	public final int getMinHeight() {
		return minHeight.getValue().getValue();
	}
	
	//method
	/**
	 * @return the min width of this border widget.
	 * @throws UnexistingAttributeException if this border widget has no min width.
	 */
	public final int getMinWidth() {
		return minWidth.getValue().getValue();
	}
	
	//method
	/**
	 * The natural height of a border widget is its height when the border widget:
	 * -Is not collapsed.
	 * -Has no min height.
	 * -Has no max height.
	 * 
	 * @return the natural height of this border widget.
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
	 * -Has no min width.
	 * -Has no max width.
	 * 
	 * @return the natural width of this border widget.
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
	 * @return the proposal height of this border widget.
	 * @throws UnexistingAttributeException if this border widget has no proposal height.
	 */
	public final int getProposalHeight() {
		return proposalHeightProperty.getValue().getValue();
	}
	
	//method
	/**
	 * @return the proposal width of this border widget.
	 * @throws UnexistingAttributeException if this border widget has no proposal width.
	 */
	public final int getProposalWidth() {
		return proposalWidthProperty.getValue().getValue();
	}
	
	//method
	/**
	 * @return the thickness of the vertical scrollbar of this border widget.
	 */
	public final int getVerticalScrollbarThickness() {
		
		//Handles the case that this border widget has no vertical scrollbar.
		if (!hasVerticalScrollbar()) {
			return 0;
		}
		
		//Handles the case that this border widget has a vertical scroll bar.
		//TODO: Add scrollbar thickness to border widget structure.
		return 20;
	}
	
	//method
	/**
	 * @return x-position of the view area of this border widget on the scroll area.
	 */
	public final int getViewAreaXPositionOnScrollArea() {
		return viewAreaXPositionOnScrollArea.getValue().getValue();
	}
	
	//method
	/**
	 * @return the y-position of the view area of this border widget on the scroll area.
	 */
	public final int getViewAreaYPositionOnScrollArea() {
		return viewAreaYPositionOnScrollArea.getValue().getValue();
	}
	
	//method
	/**
	 * This method determines if this border widget has a horizontal scroll bar.
	 * 
	 * There can be the following issues.
	 * -The horizontal scrollbar is not there, but the user needs it.
	 * -The horizontal scrollbar is there, but the user does not need it.
	 * 
	 * No matter what result this method returns, the program works technically.
	 * This method makes a good but not absolute guess if the user needs a horizontal scrollbar.
	 * 
	 * @return true if this border widget has a horizontal scrollbar.
	 */
	public final boolean hasHorizontalScrollbar() {
		
		final var naturalScrollAreaWidth = getNaturalScrollAreaWidth();
		
		return
		(hasMaxWidth() && getMaxWidth() < naturalScrollAreaWidth)
		|| (hasProposalWidth() && getProposalWidth() < naturalScrollAreaWidth);
	}
	
	//method
	/**
	 * @return true if this border widget has a max height.
	 */
	public final boolean hasMaxHeight() {
		return maxHeight.hasValue();
	}
	
	//method
	/**
	 * @return true if this border widget has a max width.
	 */
	public final boolean hasMaxWidth() {
		return maxWidth.hasValue();
	}
	
	//method
	/**
	 * @return true if this border widget has a min height.
	 */	
	public final boolean hasMinHeight() {
		return minHeight.hasValue();
	}
	
	//method
	/**
	 * @return true if this border widget has a min width.
	 */
	public final boolean hasMinWidth() {
		return minWidth.hasValue();
	}
	
	/**
	 * @return true if this border widget has a proposal height.
	 */
	public final boolean hasProposalHeight() {
		return proposalHeightProperty.hasValue();
	}
	
	//method
	/**
	 * @return true if this border widget has a proposal width.
	 */
	public final boolean hasProposalWidth() {
		return proposalWidthProperty.hasValue();
	}
	
	//method
	/**
	 * @return true if this border widget has a vertical scrollbar.
	 */
	public final boolean hasVerticalScrollbar() {
		
		final var naturalScrollAreaHeight = getNaturalScrollAreaHeight();
		
		return
		(hasMaxHeight() && getMaxHeight() < naturalScrollAreaHeight)
		|| (hasProposalHeight() && getProposalHeight() < naturalScrollAreaHeight);
	}
	
	//method
	/**
	 * Lets this border widget note a left mouse button press.
	 */
	public void noteLeftMouseButtonPress() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonPress();
		
		//Handles the case that the current widget has a left mouse button press command.
		if (viewAreaIsUnderCursor() && hasLeftMouseButtonPressCommand()) {
			getParentGUI().getRefController().run(getLeftMouseButtonPressCommand());
		}		
		
		//Handles the case that the cursor is over the vertical scrollbar cursor.
		if (verticalScrollbarCursorIsUnderCursor()) {
			
			isMovingVerticalScrollbarCursor = true;
			verticalScrollingCursorStartYPosition =
			getCursorYPosition() - getVerticalScrollbarCursorYPositionOnVerticalScrollbar();
		}
		
		//Handles the case that the cursor is over the horizontal scrollbar cursor.
		else if (horizontalScrollbarCursorIsUnderCursor()) {
			
			isMovingHorizontalScrollbarCursor = true;
			
			horizontalScrollingCursorStartXPosition =
			getCursorXPosition() - getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar();
		}
	}
	
	//method
	/**
	 * Lets this border widget note a left mouse button release.
	 */
	public void noteLeftMouseButtonRelease() {
		
		//Calls method of the base class.
		super.noteLeftMouseButtonRelease();
		
		isMovingHorizontalScrollbarCursor = false;
		isMovingVerticalScrollbarCursor = false;
	}
	
	//method
	/**
	 * Lets this border widget note a mouse move
	 */
	public void noteMouseMove() {
		
		//Calls method of the base class.
		super.noteMouseMove();
		
		if (isMovingVerticalScrollbarCursor) {
			
			final var verticalScrollbarCursorYDelta =
			getCursorYPosition() - verticalScrollingCursorStartYPosition;
			
			final var viewAreaYDelta =
			(verticalScrollbarCursorYDelta * (getScrollAreaHeight() - getViewAreaHeight()))
			/ (getViewAreaHeight() - getVerticalScrollbarCursorHeight());
			
			final var viewAreaYPositionOnScrollArea = viewAreaYDelta;
			
			setViewAreaYPositionOnScrollArea(viewAreaYPositionOnScrollArea);
		}
		
		if (isMovingHorizontalScrollbarCursor) {
			
			final var horizontalScrollbarCursorXDelta =
			getCursorXPosition() - horizontalScrollingCursorStartXPosition;
			
			final var viewAreaXDelta =
			(horizontalScrollbarCursorXDelta * (getScrollAreaWidth() - getViewAreaWidth()))
			/ (getViewAreaWidth() - getHorizontalScrollbarCursorWidth());
			
			final var viewAreaXPositionOnScrollArea = viewAreaXDelta;
			
			setViewAreaXPositionOnScrollArea(viewAreaXPositionOnScrollArea);
		}
	}
	
	//method
	/**
	 * Lets this widget note the given mouse wheel rotation steps.
	 * The given number of mouse wheel rotation steps is positive if the mouse wheel was rotated forward.
	 * The given number mouse wheel rotation steps is negative if the mouse wheel was rotated backward.
	 * 
	 * @param rotationSteps
	 */
	public void noteMouseWheelRotationSteps(final int mouseWheelRotationSteps) {
		
		//Calls method of the base class.
		super.noteMouseWheelRotationSteps(mouseWheelRotationSteps);
		
		setViewAreaYPositionOnScrollArea(
			getViewAreaYPositionOnScrollArea()
			+ VIEW_AREA_X_DELTA_PER_MOUSE_WHEEL_ROTATION_STEP * mouseWheelRotationSteps
		);
	}

	//method
	/**
	 * Removes the max height of this border widget.
	 * 
	 * @return this border widget.
	 */
	public final BW removeMaxHeight() {
		
		maxHeight.clear();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the max width of this border widget.
	 * 
	 * @return this border widget.
	 */
	public final BW removeMaxWidtht() {
		
		maxWidth.clear();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the min height of this border widget.
	 * 
	 * @return this border widget.
	 */
	public final BW removeMinHeight() {
		
		minHeight.clear();
		
		return getInstance();
	}
	
	//method
	/**
	 * Removes the min width of this border widget.
	 * 
	 * @return this border widget.
	 */
	public final BW removeMinWidth() {
		
		minWidth.clear();
		
		return getInstance();
	}
	
	//method
	/**
	 * Resets the configuration of this border widget.
	 * 
	 * @return this border widget.
	 */
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
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the content position of this border widget.
	 * 
	 * @param contentPosition
	 * @return this border widget.
	 * @throws NullArgumentException if the given content position is null.
	 */
	public final BW setContentPosition(final ContentPosition contentPosition) {
		
		//Checks if the given content position is not null.
		Validator
		.suppose(contentPosition)
		.thatIsOfType(ContentPosition.class)
		.isNotNull();

		//Sets the content position of this border widget.
		this.contentPosition = contentPosition;
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the max height of this border widget.
	 * 
	 * @param maxHeight
	 * @return this border widget.
	 * @throws NonPositiveArgumentException if the given max height is not positive.
	 */
	public final BW setMaxHeight(final int maxHeight) {
		
		//TODO: Check conditions.
		this.maxHeight.setValue(new PositiveInteger(maxHeight));
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the max width of this border widget.
	 * 
	 * @param maxWidth
	 * @return this border widget.
	 * @throws NonPositiveArgumentException if the given max width is not positive.
	 */
	public final BW setMaxWidth(final int maxWidth) {
		
		//TODO: Check conditions.
		this.maxWidth.setValue(new PositiveInteger(maxWidth));
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the min height of this border widget.
	 * 
	 * @param minHeight
	 * @return this border widget.
	 * @throws NonPositiveArgumentException if the given min height is not positive.
	 */
	public final BW setMinHeight(final int minHeight) {
		
		//TODO: Check conditions.
		this.minHeight.setValue(new PositiveInteger(minHeight));
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the min width of this border widget.
	 * 
	 * @param minWidth
	 * @return this border widget.
	 * @throws NonPositiveArgumentException if the given min width is not positive.
	 */
	public final BW setMinWidth(final int minWidth) {
		
		//TODO: Check conditions.
		this.minWidth.setValue(new PositiveInteger(minWidth));
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the cursor position of the parent of the current {@link TabContainer}.
	 * 
	 * @param parentCursorXPosition
	 * @param parentCursorYPosition
	 */
	public final void setParentCursorPosition(
		int parentCursorXPosition,
		int parentCursorYPosition
	) {
		
		//Calls method of the base class.
		super.setParentCursorPosition(parentCursorXPosition, parentCursorYPosition);
		
		setCursorPositionOnContentArea(
			getCursorXPositionOnContentArea(),
			getCursorYPositionOnContentArea()
		);
	}
	
	//method
	/**
	 * Sets the proposal height of this border widget.
	 * 
	 * @param proposalHeight
	 * @return this border widget.
	 * @throws NonPositiveArgumentException if the given proposal height is not positive.
	 */
	public final BW setProposalHeight(final int proposalHeight) {
		
		proposalHeightProperty.setValue(new PositiveInteger(proposalHeight));
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the proposal with of this border widget.
	 * 
	 * @param proposalWidth
	 * @return this border widget.
	 * @throws NonPositiveArgumentException if the given proposal width is not positive.
	 */
	public final BW setProposalWidth(final int proposalWidth) {
		
		proposalWidthProperty.setValue(new PositiveInteger(proposalWidth));
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the x-position of the view area on the scroll area of this border widget.
	 * 
	 * @param viewAreaXPositionOnScrollArea
	 * @return this border widget.
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
		
		return getInstance();
	}
	
	//method
	/**
	 * Sets the y-position of the view area on the scroll area of this border widget.
	 * 
	 * @param viewAreaYPositionOnScrollArea
	 * @return this border widget.
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
		
		return getInstance();
	}
	
	//method
	/**
	 * @return true if the content area of this border widget is under the cursor.
	 */
	protected final boolean contentAreaIsUnderCursor() {
		return (
			getCursorXPositionOnContentArea() >= 0
			&& getCursorXPositionOnContentArea() < getContentAreaWidth()
			&& getCursorYPositionOnContentArea() >= 0
			&& getCursorYPositionOnContentArea() < getContentAreaHeight()
		);
	}
	
	//method
	/**
	 * @return the height of the content area of this border widget.
	 */
	protected abstract int getContentAreaHeight();
	
	//method
	/**
	 * @return the width of the content area of this border widget.
	 */
	protected abstract int getContentAreaWidth();
	
	//method
	/**
	 * @return the x-position of the content area of this border widget.
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
	 * @return the y-position of the content area of this border widget.
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
	 * @return the x-position of the cursor on the content area of this border widget.
	 */
	protected final int getCursorXPositionOnContentArea() {
		return (getCursorXPosition() - getContentAreaXPosition());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the content area of this border widget.
	 */
	protected final int getCursorYPositionOnContentArea() {
		return (getCursorYPosition() - getContentAreaYPosition());
	}
	
	//method
	/**
	 * @return the height of this border widget when it is not collapsed.
	 */
	protected final int getHeightWhenNotCollapsed() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		currentLook.getRecursiveOrDefaultTopBorderThickness()		
		+ getBorderedAreaHeight()
		+ currentLook.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the  width of this border widget when it is not collapsed.
	 */
	protected final int getWidthWhenNotCollapsed() {
		
		final var currentLook = getRefCurrentLook();
		
		return
		currentLook.getRecursiveOrDefaultLeftBorderThickness()		
		+ getBorderedAreaWidth()
		+ currentLook.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * Paints this border widget using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
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
		
		//Paints the bordered area of this border widget.
		paintBorderedArea(
			widgetStructure,
			painter.createTranslatedPainter(
				getBorderedAreaXPosition(),
				getBorderedAreaYPosition()
			)
		);
	}
	
	//abstract method
	/**
	 * Paints the content area of this border widget using the given border widget look and painter.
	 * 
	 * @param borderWidgetLook
	 * @param painter
	 */
	protected abstract void paintContentArea(BWS borderWidgetLook, IPainter painter);
	
	//method
	/**
	 * Sets the cursor position on the content area of this border widget.
	 * 
	 * @param cursorXPositionOnContent
	 * @param cursorYPositionOnContent
	 */
	protected void setCursorPositionOnContentArea(
		int cursorXPositionOnContent,
		int cursorYPositionOnContent
	) {}

	//method
	/**
	 * @return the height of the bordered area of this border widget.
	 */
	private int getBorderedAreaHeight() {
		return 
		getViewAreaHeight()
		+ getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the width of the bordered area of this border widget.
	 */
	private int getBorderedAreaWidth() {
		return 
		getViewAreaWidth()
		+ getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the x-position of the bordered area of this border widget.
	 */
	private int getBorderedAreaXPosition() {
		
		final var currentStructure = getRefCurrentLook();
		
		return currentStructure.getRecursiveOrDefaultLeftBorderThickness();
	}
	
	//method
	/**
	 * @return the y-position of the bordered area of this border widget.
	 */
	private int getBorderedAreaYPosition() {
		
		final var currentStructure = getRefCurrentLook();
		
		return currentStructure.getRecursiveOrDefaultTopBorderThickness();
	}
	
	//method
	/**
	 * @return the x-position of the content area of this border widget
	 * on the scroll area of this border widget.
	 */
	private int getContentAreaXPositionOnScrollArea() {
		
		final var currentStructure = getRefCurrentLook();
		
		//Enumerates the content position of this border widget.
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
		
		throw new InvalidStateException(this);
	}
	
	//method
	/**
	 * @return the y-position of the content area of this border widget
	 * on the scroll area of this border widget.
	 */
	private int getContentAreaYPositionOnScrollArea() {
		
		final var currentStructure = getRefCurrentLook();
		
		//Enumerates the content orientation of this border widget.
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
		
		throw new InvalidStateException(this);
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
	 * @return the y-position of the horizontal scrollbar cursor of this border widget
	 */
	private int getHorizontalScrollbarCursorYPosition() {
		return
		getBorderedAreaYPosition()
		+ getHorizontalScrollbarYPositionOnBorderedArea();
	}
	
	//method
	/**
	 * @return the y-position of the horizontal scrollbar
	 * on the bordered area of this border widget.
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
	 * @return the height of the scroll area of this border widget.
	 */
	private int getScrollAreaHeight() {
		
		var scrollAreaHeight = getNaturalScrollAreaHeight();
		
		if (hasProposalHeight()) {
			scrollAreaHeight = Calculator.getMax(scrollAreaHeight, getProposalScrollAreaHeight());
		}
		
		//Handles the case that this border widget has a min height.
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
	 * @return the width of the scroll area of this border widget.
	 */
	private int getScrollAreaWidth() {
			
		var scrollAreaWidth = getNaturalScrollAreaWidth();
		
		if (hasProposalWidth()) {
			scrollAreaWidth = Calculator.getMax(scrollAreaWidth, getProposalScrollAreaWith());
		}
		
		//Handles the case that this border widget has a min width.
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
	 * @return the height of the vertical scrollbar cursor of this border widget.
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
	 * @return the x-position of the vertical scroll bar cursor of this border widget.
	 */
	private int getVerticalScrollbarCursorXPosition() {
		return
		getBorderedAreaXPosition()
		+ getVerticalScrollbarXPositionOnBorderedArea();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scroll bar cursor of this border widget.
	 */
	private int getVerticalScrollbarCursorYPosition() {
		return
		getBorderedAreaYPosition()
		+ getVerticalScrollbarCursorYPositionOnVerticalScrollbar();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scrollbar cursor
	 * on the vertical scrollbar of this border widget.
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
	 * on the bordered area of this border widget.
	 */
	private int getVerticalScrollbarXPositionOnBorderedArea() {
		return (getBorderedAreaWidth() - getVerticalScrollbarThickness());
	}
	
	//method
	/**
	 * @return the height of the view area of this border widget.
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
	 * @return the width of the view area of this border widget.
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
	 * @return true if the cursor is over the horizontal scrollbar cursor of this border widget.
	 */
	private boolean horizontalScrollbarCursorIsUnderCursor() {
		
		//Handles the case that this border widget has no horizontal scrollbar.
		if (!hasHorizontalScrollbar()) {
			return false;
		}
		
		//Handles the case that this border widget has a horizontal scrollbar.
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
	 * Paints the bordered area of this border widget
	 * using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	private void paintBorderedArea(
		final BWS widgetStructure,
		final IPainter painter
	) {
		
		//Paints the vertical scroll bar if this border widget has a vertical scrollbar.
		if (hasVerticalScrollbar()) {
			
			//Paints the vertical scrollbar.				
				//TODO: Add scrollbar color to border widget structure.
				painter.setColor(Color.LIGHT_GREY);
				
				painter.paintFilledRectangle(
					getVerticalScrollbarXPositionOnBorderedArea(),
					0,
					getVerticalScrollbarThickness(),
					getViewAreaHeight()
				);
			
			//Paints the vertical scrollbar cursor.				
				//TODO: Add scrollbar cursor color to border widget structure.
				painter.setColor(Color.DARK_GREY);
				
				painter.paintFilledRectangle(
					getVerticalScrollbarXPositionOnBorderedArea(),
					getVerticalScrollbarCursorYPositionOnVerticalScrollbar(),
					getVerticalScrollbarThickness(),
					getVerticalScrollbarCursorHeight()
				);
		}
		
		//Paints the horizontal scrollbar if this border widget has a horizontal scrollbar.
		if (hasHorizontalScrollbar()) {
			
			//Paints the horizontal scrollbar.	
				//TODO: Add scrollbar color to border widget structure.
				painter.setColor(Color.LIGHT_GREY);
				
				painter.paintFilledRectangle(
					0,
					getHorizontalScrollbarYPositionOnBorderedArea(),
					getViewAreaWidth(),
					getHorizontalScrollbarThickness()
				);
			
			//Paints the horizontal scrollbar cursor.			
				//TODO: Add scrollbar cursor color to border widget structure.
				painter.setColor(Color.DARK_GREY);
				
				painter.paintFilledRectangle(
					getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar(),
					getHorizontalScrollbarYPositionOnBorderedArea(),
					getHorizontalScrollbarCursorWidth(),
					getHorizontalScrollbarThickness()
				);
		}
		
		//Paints the view area of this border widget.
		paintViewArea(
			widgetStructure,
			painter.createTranslatedPainter(
				0,
				0,
				getViewAreaWidth(),				
				getViewAreaHeight()
			)
		);
	}
	
	//method
	/**
	 * Paints the view area of this border widget
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
			painter.createTranslatedPainter(
				-getViewAreaXPositionOnScrollArea(),
				-getViewAreaYPositionOnScrollArea(),
				getScrollAreaWidth(),
				getScrollAreaHeight()
			)
		);
	}
	
	//method
	/**
	 * Lets this border widget paint its scroll area
	 * using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	private void paintScrollArea(
		final BWS widgetStructure,
		final IPainter painter
	) {
		paintContentArea(
			widgetStructure,
			painter.createTranslatedPainter(
				getContentAreaXPositionOnScrollArea(),
				getContentAreaYPositionOnScrollArea(),
				getScrollAreaWidth(),
				getScrollAreaHeight()
			)
		);
	}
	
	//method
	/**
	 * @return true if the cursor is over the vertical scrollbar cursor of this border widget.
	 */
	private boolean verticalScrollbarCursorIsUnderCursor() {
		
		//Handles the case that this border widget has no vertical scrollbar.
		if (!hasVerticalScrollbar()) {
			return false;
		}
		
		//Handles the case that this border widget has a vertical scrollbar.
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
	 * @return true if the view area of this border widget is under the cursor.
	 */
	private boolean viewAreaIsUnderCursor() {
		
		//TODO: Better ask if the cursor is under the view area directly.
		return
		getCursorXPositionOnScrollArea() > 0
		&& getCursorXPositionOnScrollArea() < getScrollAreaWidth()
		&& getCursorYPositionOnScrollArea() > 0
		&& getCursorYPositionOnContentArea() < getScrollAreaHeight();
	}
}
