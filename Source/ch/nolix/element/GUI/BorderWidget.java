//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.entity.MutableOptionalProperty;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.element.color.Color;
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
 * The content of a border widget is the border widget without borders and paddings.
 * The methods concerning the content of a border widget are not public.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 770
 * @param <BW> The type of a border widget.
 * @param <BWS> The type of the widget structures of a border widget.
 */
public abstract class BorderWidget<
	BW extends BorderWidget<BW, BWS>,
	BWS extends BorderWidgetStructure<BWS>
>
extends BackgroundWidget<BW, BWS> {
	
	//constants
	public static final String TYPE_NAME = "Borderablewidget";
	private static final String MIN_WIDTH_HEADER = "MinWidth";
	private static final String MIN_HEIGHT_HEADER = "MinHeight";
	private static final String MAX_WIDTH_HEADER = "MaxWidth";
	private static final String MAX_HEIGHT_HEADER = "MaxHeight";
	
	//attribute
	private ContentPosition contentPosition;
	
	//attribute
	private final MutableOptionalProperty<PositiveInteger> minWidth =
	new MutableOptionalProperty<PositiveInteger>(
		MIN_WIDTH_HEADER,
		mw -> setMinHeight(mw.getValue()),
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
	 * @return the content orientation of this border widget.
	 */
	public final ContentPosition getContentPosition() {
		return contentPosition;
	}
	
	//method
	/**
	 * @return the height of this border widget when it is not collapsed.
	 */
	public final int getHeightWhenNotCollapsed() {
		
		var baseHeight = getBaseHeight();
		
		//Handles the case that this border widget has a min height.
		if (hasMinHeight()) {
			baseHeight = Calculator.getMax(baseHeight, getMinHeight());
		}
		
		//Handles the case that this border widget has a max height.
		if (hasMaxHeight()) {
			baseHeight = Calculator.getMin(baseHeight, getMaxHeight());
		}
		
		return baseHeight;
	}
	
	//method
	/**
	 * @return the thickness of the horizontal scrollbar of this border widget.
	 */
	public final int getHorizontalScrollbarThickness() {
		
		//Handles the case that this border widget has no max width.
		if (!hasMaxWidth()) {
			return 0;
		}
		
		//Handles the case that this border widget has a max width.
		//TODO
		return 10;
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
	 * @return the thickness of the vertical scrollbar of this border widget.
	 */
	public final int getVerticalScrollbarThickness() {
		
		//Handles the case that this border widget has no max width.
		if (!hasMaxHeight()) {
			return 0;
		}
		
		//Handles the case that this border widget has a max width.
		//TODO
		return 10;
	}
	
	//method
	/**
	 * @return the  width of this border widget when it is not collapsed.
	 */
	public final int getWidthWhenNotCollapsed() {
		
		var baseWidth = getBaseWidth();
		
		//Handles the case that this border widget has a min width.
		if (hasMinWidth()) {
			baseWidth = Calculator.getMax(baseWidth, getMinWidth());
		}
		
		//Handles the case that this border widget has a max width.
		if (hasMaxWidth()) {
			baseWidth = Calculator.getMin(baseWidth, getMaxWidth());
		}
		
		return baseWidth;
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
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class
		super.resetConfiguration();
		
		setContentPosition(ContentPosition.LeftTop);
		removeMinWidth();
		removeMinHeight();
		removeMaxWidtht();
		removeMaxHeight();
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
	 * @return true if the content of this border widget is under the cursor.
	 */
	protected final boolean contentIsUnderCursor() {
		return(
			getContentXPosition() <= getCursorXPosition()
			&& getContentXPosition() + getContentWidth() > getCursorXPosition()
			&& getContentYPosition() <= getCursorYPosition()
			&& getContentYPosition() + getContentHeight() < getCursorYPosition()
		);
	}
	
	//method
	/**
	 * @return the height of the content of this border widget.
	 */
	protected abstract int getContentHeight();
	
	//method
	/**
	 * @return the width of the content of this border widget.
	 */
	protected abstract int getContentWidth();
	
	//method
	/**
	 * @return the x-position of the content of this border widget.
	 */
	protected final int getContentXPosition() {
		
		final var currentStructure = getRefCurrentStructure();
		
		//Enumerates the content orientation of this border widget.
		switch (getContentPosition()) {
			case LeftTop:
			case Left:
			case LeftBottom:
				return
				currentStructure.getActiveLeftBorderSize()
				+ currentStructure.getActiveLeftPadding();	
			case Top:
			case Center:
			case Bottom:
				
				//Handles the case that this border widget has no min width.				
				if (!hasMinWidth()) {
					return
					currentStructure.getActiveLeftBorderSize()
					+ currentStructure.getActiveLeftPadding();
				}
				
				//Handles the case that this border widget has a min width.
				return
				(getMinWidth() - getContentWidth()) / 2;
			case RightTop:
			case Right:
			case RightBottom:
				
				return
				getWidth()
				- currentStructure.getActiveRightBorderSize()
				- currentStructure.getActiveRightPadding()
				- getContentWidth();
		}
		
		throw new InvalidStateException(this);
	}
	
	//method
	/**
	 * @return the y-position of the content of this border widget.
	 */
	protected final int getContentYPosition() {
		
		final var currentStructure = getRefCurrentStructure();
		
		//Enumerates the content orientation of this border widget.
		switch (getContentPosition()) {
			case LeftTop:
			case Top:
			case RightTop:
				return
				currentStructure.getActiveTopBorderSize()
				+ currentStructure.getActiveTopPadding();				
			case Left:
			case Center:
			case Right:
				
				//Handles the case that this border widget has no min height.				
				if (!hasMinHeight()) {
					return
					currentStructure.getActiveTopBorderSize()
					+ currentStructure.getActiveTopPadding();
				}
				
				//Handles the case that this border widget has a min height.
				return (getMinHeight() - getContentHeight()) / 2;
				
			case LeftBottom:
			case Bottom:
			case RightBottom:
				
				return
				getHeight()
				- currentStructure.getActiveBottomBorderSize()
				- currentStructure.getActiveBottomPadding()
				- getContentHeight();
		}
		
		throw new InvalidStateException(this);
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
		if (widgetStructure.getActiveLeftBorderSize() > 0) {
			
			painter.setColor(widgetStructure.getActiveLeftBorderColor());
			
			painter.paintFilledRectangle(
				widgetStructure.getActiveLeftBorderSize(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the right border if the given widget structure has an active right border thickness.
		if (widgetStructure.getActiveRightBorderSize() > 0) {
			
			painter.setColor(widgetStructure.getActiveRightBorderColor());
			
			painter.paintFilledRectangle(
				getWidth() - widgetStructure.getActiveLeftBorderSize(),
				0,
				widgetStructure.getActiveLeftBorderSize(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the top border if the given widget structure has an active top border thickness.
		if (widgetStructure.getActiveTopBorderSize() > 0) {
			
			painter.setColor(widgetStructure.getActiveTopBorderColor());
			
			painter.paintFilledRectangle(
				getWidth(),
				widgetStructure.getActiveTopBorderSize()
			);
		}
		
		//Paints the bottom border if the given widget structure has an active bottom border thickness.
		if (widgetStructure.getActiveBottomBorderSize() > 0) {
			
			painter.setColor(widgetStructure.getActiveBottomBorderColor());
			
			painter.paintFilledRectangle(
				0,
				getHeightWhenNotCollapsed() - widgetStructure.getActiveBottomBorderSize(),
				getWidth(),
				widgetStructure.getActiveBottomBorderSize()
			);
		}
		
		//Paints the vertical scroll bar if this border widget has a max width.
		if (hasMaxWidth()) {
			
			//Paints the vertical scrollbar.
				//TODO: Add scrollbar color to border widget structure.
				painter.setColor(Color.GREY);
				
				painter.paintFilledRectangle(
					getVerticalScrollbarXPosition(),
					getVerticalScrollbarYPosition(),
					getVerticalScrollbarThickness(),
					getVerticalScrollbarLength()
				);
			
			//TODO: Paint the vertical scrollbar cursor.
		}
		
		//Paints the horizontal scrollbar if this border widget has a max height.
		if (hasMaxHeight()) {
			
			//Paints the horizontal scrollbar.
				//TODO: Add scrollbar color to border widget structure.
				painter.setColor(Color.GREY);
				
				painter.paintFilledRectangle(
					getHorizontalScrollbarXPosition(),
					getHorizontalScrollbarYPosition(),
					getHorizontalScrollbarLength(),
					getHorizontalScrollbarThickness()
				);
			
			//TODO: Paint the horizontal scrollbar cursor.
		}

		//Paints the content of this border widget.
		paintContent(
			widgetStructure,
			painter.createTranslatedPainter(
				getContentXPosition(),
				getContentYPosition()
			)
		);
	}
	
	//abstract method
	/**
	 * Paints the content of this border widget using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	protected abstract void paintContent(BWS widgetStructure, IPainter painter);
	
	//method
	/**
	 * The base height of a border widget is its height when the border widget:
	 * -Is not collapsed.
	 * -Has no min height.
	 * -Has no max height.
	 * 
	 * @return the base height of this border widget.
	 */
	private int getBaseHeight() {
		
		final var currentStructure = getRefCurrentStructure();
		
		return
		currentStructure.getActiveTopBorderSize()
		+ currentStructure.getActiveTopPadding()
		+ getContentHeight()
		+ currentStructure.getActiveBottomPadding()
		+ getHorizontalScrollbarThickness()
		+ currentStructure.getActiveBottomBorderSize();
	}
	
	//method
	/**
	 * The base width of a border widget is its width when the border widget:
	 * -Is not collapsed.
	 * -Has no min width.
	 * -Has no max width.
	 * 
	 * @return the base width of this border widget.
	 */
	private int getBaseWidth() {
		
		final var currentStructure = getRefCurrentStructure();
		
		return
		currentStructure.getActiveLeftBorderSize()
		+ currentStructure.getActiveLeftPadding()
		+ getContentWidth()
		+ currentStructure.getActiveRightPadding()
		+ getVerticalScrollbarThickness()
		+ currentStructure.getActiveRightBorderSize();
	}
	
	//method
	/**
	 * @return the length of the horizontal scrollbar of this border widget.
	 */
	private int getHorizontalScrollbarLength() {

		final var currentStructure = getRefCurrentStructure();
		
		return
		getWidth()
		- currentStructure.getActiveLeftBorderSize()
		- currentStructure.getActiveRightBorderSize()
		- getVerticalScrollbarThickness();
	}
		
	//method
	/**
	 * @return the x-position of the horizontal scrollbar of this border widget.
	 */
	private int getHorizontalScrollbarXPosition() {
		
		final var currentStructure = getRefCurrentStructure();
		
		return currentStructure.getActiveLeftBorderSize();
	}
	
	//method
	/**
	 * @return the y-position of the horizontal scrollbar of this border widget.
	 */
	private int getHorizontalScrollbarYPosition() {
		
		final var currentStructure = getRefCurrentStructure();
		
		return
		getHeight()
		- currentStructure.getActiveBottomBorderSize()
		- getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the length of the vertical scrollbar of this border widget.
	 */
	private int getVerticalScrollbarLength() {

		final var currentStructure = getRefCurrentStructure();
		
		return
		getHeight()
		- currentStructure.getActiveTopBorderSize()
		- currentStructure.getActiveBottomBorderSize()
		- getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the x-position of the vertical scrollbar of this border widget.
	 */
	private int getVerticalScrollbarXPosition() {
		
		final var currentStructure = getRefCurrentStructure();
		
		return
		getWidth()
		- currentStructure.getActiveRightBorderSize()
		- getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the y-position of the vertical scrollbar of this border widget.
	 */
	private int getVerticalScrollbarYPosition() {
		
		final var currentStructure = getRefCurrentStructure();
		
		return currentStructure.getActiveTopBorderSize();
	}
}
