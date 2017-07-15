//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;


//own imports




import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.basic.PositiveInteger;
import ch.nolix.element.data.MinHeight;
import ch.nolix.element.data.MinWidth;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 890
 * @param <BWS> - The type of the widget structures of a borderable widget.
 * @param <BW> - The type of a borderable widget.
 */
public abstract class BorderWidget<
	BWS extends BorderWidgetStructure<BWS>,
	BW extends BorderWidget<BWS, BW>
>
extends BackgroundWidget<BWS, BW> {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Borderablewidget";
	
	//attribute headers
	private static final String PADDING_HEADER = "Padding";
	private static final String LEFT_PADDING_HEADER = "LeftPadding";
	private static final String RIGHT_PADDING_HEADER = "RightPadding";
	private static final String BOTTOM_PADDING_HEADER = "BottomPadding";
	private static final String TOP_PADDING_HEADER = "TopPadding";
	
	//attribute
	private ContentPosition contentOrientation = ContentPosition.Center;
	
	//optional attributes
	private MinWidth minWidth;
	private MinHeight minHeight;
	private PositiveInteger leftPadding;
	private PositiveInteger rightPadding;
	private PositiveInteger topPadding;
	private PositiveInteger bottomPadding;
	
	//method
	/**
	 * Adds or changes the given attribute to this borderable widget.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final StandardSpecification attribute) {
		
		//Enumerates the header of the given attribute.
		switch (attribute.getHeader()) {
			case ContentPosition.SIMPLE_CLASS_NAME:
				setContentOrientation(ContentPosition.valueOf(attribute.getOneAttributeToString()));
				break;
			case MinWidth.SIMPLE_CLASS_NAME:
				setMinWidth(attribute.getOneAttributeToInteger());
				break;
			case MinHeight.SIMPLE_CLASS_NAME:
				setMinHeight(attribute.getOneAttributeToInteger());
				break;
			case PADDING_HEADER:
				setPadding(attribute.getOneAttributeToInteger());
				break;
			case LEFT_PADDING_HEADER:
				setLeftPadding(attribute.getOneAttributeToInteger());
				break;
			case RIGHT_PADDING_HEADER:
				setRightPadding(attribute.getOneAttributeToInteger());
				break;
			case TOP_PADDING_HEADER:
				setTopPadding(attribute.getOneAttributeToInteger());
				break;
			case BOTTOM_PADDING_HEADER:
				setBottomPadding(attribute.getOneAttributeToInteger());
				break;			
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * @return the attributes of this borderable widget.
	 */
	public List<StandardSpecification> getAttributes() {
		
		//Calls method of the base class
		final List<StandardSpecification> attributes = super.getAttributes();
		
		attributes.addAtEnd(contentOrientation.getSpecification());
		
		if (hasMinWidth()) {
			attributes.addAtEnd(minWidth.getSpecification());
		}
		
		if (hasMinHeight()) {
			attributes.addAtEnd(minHeight.getSpecification());
		}
			
		if (hasAPadding() && hasSamePaddingAtEachSide()) {
			attributes.addAtEnd(leftPadding.getSpecificationAs(PADDING_HEADER));
		}
		else {
			if (hasLeftPadding()) {
				attributes.addAtEnd(new StandardSpecification(LEFT_PADDING_HEADER, leftPadding.getAttributes()));
			}
			if (hasRightPadding()) {
				attributes.addAtEnd(new StandardSpecification(RIGHT_PADDING_HEADER, rightPadding.getAttributes()));
			}
			if (hasTopPadding()) {
				attributes.addAtEnd(new StandardSpecification(TOP_PADDING_HEADER, topPadding.getAttributes()));
			}
			if (hasBottomPadding()) {
				attributes.addAtEnd(new StandardSpecification(BOTTOM_PADDING_HEADER, bottomPadding.getAttributes()));
			}
		}

		return attributes;
	}
	
	//method
	/**
	 * @return the bottom padding of this borderable widget.
	 */
	public final int getBottomPadding() {
		
		if (hasBottomPadding()) {
			return bottomPadding.getValue();
		}
		
		return 0;
	}
	
	//method
	/**
	 * @return the content orientation of this borderable widget.
	 */
	public final ContentPosition getContentOrientation() {
		return contentOrientation;
	}
	
	//method
	/**
	 * @return the left padding of this borderable widget.
	 */
	public final int getLeftPadding() {
		
		//Handles the case if this borderable widget has actually a left padding.
		if (hasLeftPadding()) {
			return leftPadding.getValue();
		}
		
		//Handles the case if this borderable widget has actually no left padding.
		return 0;
	}
	
	//method
	/**
	 * @return the min height of this borderable widget.
	 * @throws UnexistingAttributeException if this borderable widget has no min height.
	 */
	public final int getMinHeight() {
		
		//Checks if this borderable widget has a min height.
		if (!hasMinHeight()) {
			throw new UnexistingAttributeException(this, "min height");
		}
		
		return minHeight.getValue();
	}
	
	//method
	/**
	 * @return the min width of this borderable widget.
	 * @throws UnexistingAttributeException if this borderable widget has no min width.
	 */
	public final int getMinWidth() {
		
		//Checks if this borderable widget has a min width.
		if (!hasMinWidth()) {
			throw new UnexistingAttributeException(this, "min width");
		}
		
		return minWidth.getValue();
	}
	
	//method
	/**
	 * @return the right padding of this borderable widget.
	 */
	public final int getRightPadding() {
		
		//Handles the case if this borderable widget has actually a right padding.
		if (hasRightPadding()) {
			return rightPadding.getValue();
		}
		
		//Handles the case if this borderable widget has actually no right padding.
		return 0;
	}
	
	//method
	/**
	 * @return the top padding of this borderable widget.
	 */
	public final int getTopPadding() {
		
		//Handles the case if this borderable widget has actually a top padding.
		if (hasTopPadding()) {
			return topPadding.getValue();
		}
		
		//Handles the case if this borderable widget has actually no top padding.
		return 0;
	}
	
	//method
	/**
	 * @return true if this borderable widget has a padding.
	 */
	public final boolean hasAPadding() {
		return (
			hasLeftPadding()
			|| hasRightPadding()
			|| hasTopPadding()
			|| hasBottomPadding()
		);
	}
	
	//method
	/**
	 * @return true if this borderable widget has a bottom padding.
	 */
	public final boolean hasBottomPadding() {
		return (bottomPadding != null);
	}
	
	//method
	/**
	 * @return true if this borderable widget has a left padding.
	 */
	public final boolean hasLeftPadding() {
		return (leftPadding != null);
	}
	
	//method
	/**
	 * @return true if this borderable widget has a min height.
	 */	
	public final boolean hasMinHeight() {
		return (minHeight != null);
	}
	
	//method
	/**
	 * @return true if this borderable widget has a min width.
	 */
	public final boolean hasMinWidth() {
		return (minWidth != null);
	}
	
	//method
	/**
	 * @return true if this borderable widget has a right padding
	 */
	public final boolean hasRightPadding() {
		return (rightPadding != null);
	}
	
	//method
	/**
	 * @return true if this borderable widget has the same padding at each side.
	 */
	public final boolean hasSamePaddingAtEachSide() {
		
		final int currentLeftPadding = getLeftPadding();
		
		return (
			getRightPadding() == currentLeftPadding &&
			getTopPadding() ==  currentLeftPadding &&
			getBottomPadding() == currentLeftPadding
		);
	}
	
	//method
	/**
	 * @return true if this borderable widget has a top padding
	 */
	public final boolean hasTopPadding() {
		return (topPadding != null);
	}
	
	//method
	/**
	 * Removes the bottom padding of this borderable widget.
	 */
	public final void removeBottomPadding() {
		bottomPadding = null;
	}
	
	//method
	/**
	 * Removes the left padding of this borderable widget.
	 */
	public final void removeLeftPadding() {
		leftPadding = null;
	}
	
	//method
	/**
	 * Removes the min height of this borderable widget.
	 */
	public final void removeMinHeight() {
		minHeight = null;
	}
	
	//method
	/**
	 * Removes the min width of this borderable widget.
	 */
	public final void removeMinWidth() {
		minWidth = null;
	}
	
	//method
	/**
	 * Removes the padding of this borderable widget.
	 */
	public final void removePadding() {
		removeBottomPadding();
		removeLeftPadding();
		removeRightPadding();
		removeTopPadding();
	}
	
	//method
	/**
	 * Removes the right padding of this borderable widget.
	 */
	public final void removeRightPadding() {
		rightPadding = null;
	}
	
	//method
	/**
	 * Removes the top padding of this borderable widget.
	 */
	public final void removeTopPadding() {
		topPadding = null;
	}
	
	//method
	/**
	 * Resets the configuration of this borderable widget.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class
		super.resetConfiguration();
		
		setContentOrientation(ContentPosition.LeftTop);
		removeMinWidth();
		removeMinHeight();
		removePadding();
	}
	
	//method
	/**
	 * Sets the bottom padding of this borderable widget.
	 * 
	 * @param bottomPadding
	 * @return this borderable widget.
	 * @throws NonPositiveArgumentException if the given bottom padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setBottomPadding(final int bottomPadding) {
		
		this.bottomPadding = new PositiveInteger(bottomPadding);
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the content orientation of this borderable widget.
	 * 
	 * @param contentOrientation
	 * @return this borderable widget.
	 * @throws NullArgumentException if the given content orientation is null.
	 */
	@SuppressWarnings("unchecked")
	public final BW setContentOrientation(final ContentPosition contentOrientation) {
		
		//Checks the given content orientation.
		Validator
		.supposeThat(contentOrientation)
		.thatIsInstanceOf(ContentPosition.class)
		.isNotNull();

		//Sets the content orientation of this borderable widget.
		this.contentOrientation = contentOrientation;
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the left padding of this borderable widget.
	 * 
	 * @param leftPadding
	 * @return this borderable widget.
	 * @throws NonPositiveArgumentException if the given left padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setLeftPadding(final int leftPadding) {
		
		this.leftPadding = new PositiveInteger(leftPadding);
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the min height of this borderable widget structure.
	 * 
	 * @param minHeight
	 * @return this borderable widget.
	 * @throws NonPositiveArgumentException if the given min height is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setMinHeight(final int minHeight) {
		
		this.minHeight = new MinHeight(minHeight);
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the min width of this borderable widget structure.
	 * 
	 * @param minWidth
	 * @return this borderable widget.
	 * @throws NonPositiveArgumentException if the given min width is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setMinWidth(final int minWidth) {
		
		this.minWidth = new MinWidth(minWidth);
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the padding of this borderale widget.
	 * 
	 * @param padding
	 * @return this borderable widget.
	 * @throws NonPositiveArgumentException if the given padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setPadding(final int padding) {
		
		setLeftPadding(padding);
		setRightPadding(padding);
		setTopPadding(padding);
		setBottomPadding(padding);
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the right padding of this borderable widget.
	 * 
	 * @param rightPadding
	 * @return this borderable widget.
	 * @throws NonPositiveArgumentException if the given right padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setRightPadding(final int rightPadding) {
		
		this.rightPadding = new PositiveInteger(rightPadding);
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the top padding of this borderable widget.
	 * 
	 * @param leftPadding
	 * @return this borderable widget.
	 * @throws NonPositiveArgumentException if the given top padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setTopPadding(final int topPadding) {
		
		this.topPadding = new PositiveInteger(topPadding);
		
		return (BW)this;
	}
	
	//method
	/**
	 * @return true if the content of this borderable widget is under mouse.
	 */
	protected final boolean contentIsUnderMouse() {
		return(
			getContentXPosition() <= getMouseXPosition()
			&& getContentXPosition() + getContentWidth() > getMouseXPosition()
			&& getContentYPosition() <= getMouseYPosition()
			&& getContentYPosition() + getContentHeight() < getMouseYPosition()
		);	
	}
	
	//method
	/**
	 * @param xPosition
	 * @param yPosition
	 * @return true if the content of this borderable widget surrounds the given position.
	 */
	protected final boolean contentSurrounds(final int xPosition, final int yPosition) {
		return (
			getContentXPosition() <= xPosition
			&& getContentXPosition() + getContentWidth() > xPosition
			&& getContentYPosition() >= yPosition
			&& getContentYPosition() + getContentHeight() < yPosition
		);
	}
	
	//method
	/**
	 * @return the height of the content of this borderable widget.
	 */
	protected abstract int getContentHeight();
	
	//method
	/**
	 * @return the width of the content of this borderable widget.
	 */
	protected abstract int getContentWidth();
	
	//method
	/**
	 * @return the x-position of the content of this borderable widget.
	 */
	protected final int getContentXPosition() {
		
		//Enumerates the content orientation of this borderable widget.
		switch (getContentOrientation()) {
			case LeftTop:
				return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());
			case Left:
				return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());
			case LeftBottom:
				return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());	
			case Top:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());
				}
				
				final int contentXPosition1
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getActiveLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getActiveRightBorderSize()
				- getRightPadding();
				
				return (contentXPosition1 / 2);
			case Center:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());
				}
				
				final int contentXPosition2
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getActiveLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getActiveRightBorderSize()
				- getRightPadding();
				
				return (contentXPosition2 / 2);
			case Bottom:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());
				}
				
				final int contentXPosition3
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getActiveLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getActiveRightBorderSize()
				- getRightPadding();
				
				return (contentXPosition3 / 2);
			case RightTop:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getActiveRightBorderSize()
					- getRightPadding()
				);
			case Right:
			
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getActiveRightBorderSize()
					- getRightPadding()
				);
			case RightBottom:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + getLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getActiveRightBorderSize()
					- getRightPadding()
				);
		}
		
		throw new RuntimeException();
	}
	
	//method
	/**
	 * @return the y-position of the content of this borderable widget.
	 */
	protected final int getContentYPosition() {
		
		//Enumerates the content orientation of this borderable widget.
		switch (getContentOrientation()) {
			case LeftTop:
				return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
			case Left:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
				}
				
				final int contentYPosition1
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getActiveTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getActiveBottomBorderSize()
				- getBottomPadding();
				
				return (contentYPosition1 / 2);	
			case LeftBottom:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getActiveBottomBorderSize()
					- getBottomPadding()
				);	
			case Top:
				return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
			case Center:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
				}
				
				final int contentYPosition2
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getActiveTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getActiveBottomBorderSize()
				- getBottomPadding();
				
				return (contentYPosition2 / 2);
			case Bottom:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getActiveBottomBorderSize()
					- getBottomPadding()
				);
			case RightTop:
				return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
			case Right:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
				}
				
				final int contentYPosition3
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getActiveTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getActiveBottomBorderSize()
				- getBottomPadding();
				
				return (contentYPosition3 / 2);
			case RightBottom:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + getTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getActiveBottomBorderSize()
					- getBottomPadding()
				);
		}
		
		throw new RuntimeException();
	}
	

	
	//method
	/**
	 * @return the current height of this widget when it is not collapsed
	 */
	protected final int getHeightWhenNotCollapsed() {
		
		final BWS widgetStructure = getRefCurrentStructure();
		
		final int baseHeight
		= widgetStructure.getActiveTopBorderSize()
		+ getTopPadding()
		+ getContentHeight()
		+ getBottomPadding()
		+ widgetStructure.getActiveBottomBorderSize();
		
		if (!hasMinHeight()) {
			return baseHeight;
		}
		
		return Calculator.getMax(getMinHeight(), baseHeight);
	}
	
	//method
	/**
	 * @return the current width of this rectanlge if it is not collapsed
	 */
	protected final int getWidthWhenNotCollapsed() {
		
		final BWS widgetStructure = getRefCurrentStructure();
		
		final int baseWidth
		= widgetStructure.getActiveLeftBorderSize()
		+ getLeftPadding()
		+ getContentWidth()
		+ getRightPadding()
		+ widgetStructure.getActiveRightBorderSize();
		
		if (!hasMinWidth()) {
			return baseWidth;
		}
		
		return Calculator.getMax(getMinWidth(), baseWidth);
	}
	
	//method
	/**
	 * Paints this border widget using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected final void paint(final BWS widgetStructure, final Graphics graphics) {
				
		//Calls method of the base class.
		super.paint(widgetStructure, graphics);
		
		//Paints the left border if the given widget structure has a left border.
		if (widgetStructure.getActiveLeftBorderSize() > 0) {
			graphics.setColor(widgetStructure.getActiveLeftBorderColor().getJavaColor());
			graphics.fillRect(
				0,
				0,
				widgetStructure.getActiveLeftBorderSize(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the right border if the given widget structure has a right border.
		if (widgetStructure.getActiveRightBorderSize() > 0) {
			graphics.setColor(widgetStructure.getActiveRightBorderColor().getJavaColor());
			graphics.fillRect(
				getWidth() - widgetStructure.getActiveLeftBorderSize(),
				0,
				widgetStructure.getActiveLeftBorderSize(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the top border if the given widget structure has a top border.
		if (widgetStructure.getActiveTopBorderSize() > 0) {
			graphics.setColor(widgetStructure.getActiveTopBorderColor().getJavaColor());
			graphics.fillRect(
				0,
				0,
				getWidth(),
				widgetStructure.getActiveTopBorderSize()
			);
		}
		
		//Paints the bottom border if the given widget structure has a bottom border.
		if (widgetStructure.getActiveBottomBorderSize() > 0) {
			graphics.setColor(widgetStructure.getActiveBottomBorderColor().getJavaColor());
			graphics.fillRect(
				0,
				getHeightWhenNotCollapsed() - widgetStructure.getActiveBottomBorderSize(),
				getWidth(),
				widgetStructure.getActiveBottomBorderSize()
			);
		}

		graphics.translate(getContentXPosition(), getContentYPosition());
		paintContent(widgetStructure, graphics);
		graphics.translate(-getContentXPosition(), -getContentYPosition());
	}
	
	//abstract method
	/**
	 * Paints the content of this borderable widget using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected abstract void paintContent(BWS widgetStructure, Graphics graphics);
}
