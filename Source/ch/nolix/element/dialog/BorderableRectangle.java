/*
 * file:	BorderableRectangle.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	540
 */

//package declaration
package ch.nolix.element.dialog;

//Java import
import java.awt.Graphics;


//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.mathematics.Calculator;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.util.Validator;
import ch.nolix.element.basic.PositiveInteger;
import ch.nolix.element.data.MinHeight;
import ch.nolix.element.data.MinWidth;

//class
public abstract class BorderableRectangle<BR extends BorderableRectangle<BR, ?>, BRS extends BorderableRectangleStructure<?>>
extends Rectangle<BR, BRS> {
	
	//constant
	public final static String SIMPLE_CLASS_NAME = "BorderableRectangle";
	
	//attribute headers
	private final static String PADDING = "Padding";
	private final static String LEFT_PADDING = "LeftPadding";
	private final static String RIGHT_PADDING = "RightPadding";
	private final static String BOTTOM_PADDING = "BottomPadding";
	private final static String TOP_PADDING = "TopPadding";
	
	//attributes
	private ContentOrientation contentOrientation = ContentOrientation.Center;
	
	//optional attributes
	private MinWidth minWidth;
	private MinHeight minHeight;
	private PositiveInteger leftPadding;
	private PositiveInteger rightPadding;
	private PositiveInteger topPadding;
	private PositiveInteger bottomPadding;
	
	//constructor
	/**
	 * Creates new borderable rectangle with the given rectangle structures.
	 * 
	 * @param normalStructure
	 * @param hoverStructure
	 * @param focusStructure
	 */
	public BorderableRectangle(
		BRS normalStructure,
		BRS hoverStructure,
		BRS focusStructure
	) {
		
		//Calls constructor of the base class.
		super(normalStructure, hoverStructure, focusStructure);
	}
	
	//method
	/**
	 * @return the attributes of this borderable rectangle
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class
		List<Specification> attributes = super.getAttributes();
		
		attributes.addAtEnd(contentOrientation.getSpecification());
		
		if (hasMinWidth()) {
			attributes.addAtEnd(minWidth.getSpecification());
		}
		
		if (hasMinHeight()) {
			attributes.addAtEnd(minHeight.getSpecification());
		}
			
		if (hasLeftPadding() && hasSamePaddingAtEachSide()) {
			attributes.addAtEnd(leftPadding.getSpecificationAs(PADDING));
		}
		else {
			if (hasLeftPadding()) {
				attributes.addAtEnd(new Specification(LEFT_PADDING, leftPadding.getAttributes()));
			}
			if (hasRightPadding()) {
				attributes.addAtEnd(new Specification(RIGHT_PADDING, rightPadding.getAttributes()));
			}
			if (hasTopPadding()) {
				attributes.addAtEnd(new Specification(TOP_PADDING, topPadding.getAttributes()));
			}
			if (hasBottomPadding()) {
				attributes.addAtEnd(new Specification(BOTTOM_PADDING, bottomPadding.getAttributes()));
			}
		}

		return attributes;
	}
	
	//method
	/**
	 * @return the current bottom padding of this borderable rectangle
	 */
	public final int getBottomPadding() {
		
		if (hasBottomPadding()) {
			return bottomPadding.getValue();
		}
		
		return 0;
	}
	
	//method
	/**
	 * @return the content orientation of this borderable rectangle
	 */
	public final ContentOrientation getContentOrientation() {
		return contentOrientation;
	}
	
	//method
	/**
	 * @return the current left padding of this borderable rectangle
	 */
	public final int getLeftPadding() {
		
		if (hasLeftPadding()) {
			return leftPadding.getValue();
		}
		
		return 0;
	}
	
	//method
	/**
	 * @return the min height of this borderable rectangle
	 * @throws Exception if this borderable rectangle has no min height
	 */
	public final int getMinHeight() {
		
		if (!hasMinHeight()) {
			throw new UnexistingAttributeException(this, "min height");
		}
		
		return minHeight.getValue();
	}
	
	//method
	/**
	 * @return the min width of this borderable rectangle
	 * @throws Exception if this borderable rectangle has no min widht
	 */
	public final int getMinWidth() {
		
		if (!hasMinWidth()) {
			throw new UnexistingAttributeException(this, "min width");
		}
		
		return minWidth.getValue();
	}
	
	//method
	/**
	 * @return the current right padding of this borderable rectangle
	 */
	public final int getRightPadding() {
		
		if (hasRightPadding()) {
			return rightPadding.getValue();
		}
		
		return 0;
	}
	
	//method
	/**
	 * @return the current top padding of this borderable rectangle
	 */
	public final int getTopPadding() {
		
		if (hasTopPadding()) {
			return topPadding.getValue();
		}
		
		return 0;
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a bottom padding
	 */
	public final boolean hasBottomPadding() {
		return (bottomPadding != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a left padding
	 */
	public final boolean hasLeftPadding() {
		return (leftPadding != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a min height
	 */	
	public final boolean hasMinHeight() {
		return (minHeight != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a min width
	 */
	public final boolean hasMinWidth() {
		return (minWidth != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a right padding
	 */
	public final boolean hasRightPadding() {
		return (rightPadding != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has the same padding at each side
	 */
	public final boolean hasSamePaddingAtEachSide() {
		
		int currentLeftPadding = getLeftPadding();
		
		return (
			getRightPadding() == currentLeftPadding &&
			getTopPadding() ==  currentLeftPadding &&
			getBottomPadding() == currentLeftPadding
		);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a top padding
	 */
	public final boolean hasTopPadding() {
		return (topPadding != null);
	}
	
	//method
	/**
	 * Removes the bottom padding of this borderable rectangle.
	 */
	public final void removeBottomPadding() {
		bottomPadding = null;
	}
	
	//method
	/**
	 * Removes the left padding of this borderable rectangle.
	 */
	public final void removeLeftPadding() {
		leftPadding = null;
	}
	
	//method
	/**
	 * Removes the min height of this borderable rectangle.
	 */
	public final void removeMinHeight() {
		minHeight = null;
	}
	
	//method
	/**
	 * Removes the min width of this borderable rectangle.
	 */
	public final void removeMinWidth() {
		minWidth = null;
	}
	
	//method
	/**
	 * Removes the padding of this borderable rectangle.
	 */
	public final void removePadding() {
		removeBottomPadding();
		removeLeftPadding();
		removeRightPadding();
		removeTopPadding();
	}
	
	//method
	/**
	 * Removes the right padding of this borderable rectangle.
	 */
	public final void removeRightPadding() {
		rightPadding = null;
	}
	
	//method
	/**
	 * Removes the top padding of this borderable rectangle.
	 */
	public final void removeTopPadding() {
		topPadding = null;
	}
	
	public void resetConfiguration() {
		
		//Calls method of the base class
		super.resetConfiguration();
		
		setContentOrientation(ContentOrientation.LeftTop);
		removeMinWidth();
		removeMinHeight();
		removePadding();
		
		getRefNormalStructure().removeBackgroundColor();
		getRefNormalStructure().removeBorder();
		
		getRefHoverStructure().removeBackgroundColor();
		getRefHoverStructure().removeBorder();
		
		getRefFocusStructure().removeBackgroundColor();
		getRefFocusStructure().removeBorder();
	}
	
	//method
	/**
	 * Sets the given attribute to this borderable rectangle.
	 * 
	 * @param attribute
	 * @throws Exception if the given attribute is not valid
	 */
	public void addOrChangeAttribute(Specification attribute) {
		switch (attribute.getHeader()) {
			case ContentOrientation.SIMPLE_CLASS_NAME:
				setContentOrientation(ContentOrientation.valueOf(attribute.getOneAttributeToString()));
				break;
			case MinWidth.SIMPLE_CLASS_NAME:
				setMinWidth(attribute.getOneAttributeToInteger());
				break;
			case MinHeight.SIMPLE_CLASS_NAME:
				setMinHeight(attribute.getOneAttributeToInteger());
				break;
			case PADDING:
				setPadding(attribute.getOneAttributeToInteger());
				break;
			case LEFT_PADDING:
				setLeftPadding(attribute.getOneAttributeToInteger());
				break;
			case RIGHT_PADDING:
				setRightPadding(attribute.getOneAttributeToInteger());
				break;
			case TOP_PADDING:
				setTopPadding(attribute.getOneAttributeToInteger());
				break;
			case BOTTOM_PADDING:
				setBottomPadding(attribute.getOneAttributeToInteger());
				break;			
			default:
				
				//Calls method of the base class.
				super.addOrChangeAttribute(attribute);
		}
	}
	
	//method
	/**
	 * Sets the bottom padding of this borderable rectangle.
	 * 
	 * @param bottomPadding
	 * @throws Exception if the given bottom padding is not positive
	 */
	public final void setBottomPadding(int bottomPadding) {
		this.bottomPadding = new PositiveInteger(bottomPadding);
	}
	
	//method
	/**
	 * Sets the content orientation of this borderable rectangle.
	 * 
	 * @param contentOrientation
	 * @return this borderable rectangle
	 */
	@SuppressWarnings("unchecked")
	public final BR setContentOrientation(final ContentOrientation contentOrientation) {
		
		//Checks the given content orientation.
		Validator.throwExceptionIfValueIsNull("content orientation", contentOrientation);
		
		this.contentOrientation = contentOrientation;
		
		return (BR)this;
	}
	
	//method
	/**
	 * Sets the left padding of this borderable rectangle.
	 * 
	 * @param leftPadding
	 * @throws Exception if the given left padding is not positive
	 */
	public final void setLeftPadding(int leftPadding) {
		this.leftPadding = new PositiveInteger(leftPadding);
	}
	
	//method
	/**
	 * Sets the min height of this borderable rectangle structure.
	 * 
	 * @param minHeight
	 * @throws Exception if the given min height is not positive
	 */
	public final void setMinHeight(final int minHeight) {
		this.minHeight = new MinHeight(minHeight);
	}
	
	//method
	/**
	 * Sets the min width of this borderable rectangle structure.
	 * 
	 * @param minWidth
	 * @throws Exception if the given min width is not positive
	 */
	public final void setMinWidth(final int minWidth) {
		this.minWidth = new MinWidth(minWidth);
	}
	
	//method
	/**
	 * Sets the padding of this borderale rectangle.
	 * 
	 * @param padding
	 * @return this borderable rectangle.
	 * @throws Exception if the given padding is not positive
	 */
	@SuppressWarnings("unchecked")
	public final BR setPadding(int padding) {
		
		setLeftPadding(padding);
		setRightPadding(padding);
		setTopPadding(padding);
		setBottomPadding(padding);
		
		return (BR)this;
	}
	
	//method
	/**
	 * Sets the right padding of this borderable rectangle.
	 * 
	 * @param rightPadding
	 * @throws Exception if the given right padding is not positive
	 */
	public final void setRightPadding(int rightPadding) {
		this.rightPadding = new PositiveInteger(rightPadding);
	}
	
	//method
	/**
	 * Sets the top padding of this borderable rectangle.
	 * 
	 * @param leftPadding
	 * @throws Exception if the given top padding is not positive
	 */
	public final void setTopPadding(int topPadding) {
		this.topPadding = new PositiveInteger(topPadding);
	}
	
	protected final boolean contentIsPointed() {
		return(
			getContentXPosition() <= getMouseXPosition() &&
			getContentXPosition() + getContentWidth() > getMouseXPosition() &&
			getContentYPosition() <= getMouseYPosition() &&
			getContentYPosition() + getContentHeight() < getMouseYPosition()
		);	
	}
	
	protected final boolean contentSurrounds(int xPosition, int yPosition) {
		return (
			getContentXPosition() <= xPosition &&
			getContentXPosition() + getContentWidth() > xPosition &&
			getContentYPosition() >= yPosition &&
			getContentYPosition() + getContentHeight() < yPosition
		);
	}
	
	//method
	/**
	 * @return the current distance of the content of this borderable rectangle from the left border of the panel this borderable rectangle is painted on
	 */
	protected final int getContentXPosition() {
		
		//Enumerates the probable content orientations.
		switch (getContentOrientation()) {
			case LeftTop:
				return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
			case Left:
				return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
			case LeftBottom:
				return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
			case Top:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
				}
				
				final int temp1
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getRecLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getRecRightBorderSize()
				- getRightPadding();
				
				return (temp1 / 2);
			case Center:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
				}
				
				final int temp2
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getRecLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getRecRightBorderSize()
				- getRightPadding();
				
				return (temp2 / 2);
			case Bottom:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
				}
				
				final int temp3
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getRecLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getRecRightBorderSize()
				- getRightPadding();
				
				return (temp3 / 2);
			case RightTop:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getRecRightBorderSize()
					- getRightPadding()
				);
			case Right:
			
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getRecRightBorderSize()
					- getRightPadding()
				);
			case RightBottom:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getRecRightBorderSize()
					- getRightPadding()
				);
		}
		
		throw new RuntimeException();
	}
	
	//method
	/**
	 * @return the current distance of the content of this borderable rectangle from the top border of the panel this borderable rectangle is painted on
	 */
	protected final int getContentYPosition() {
		
		//Enumerates the probable content orientations
		switch (getContentOrientation()) {
			case LeftTop:
				return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
			case Left:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
				}
				
				final int temp1
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getRecTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getRecBottomBorderSize()
				- getBottomPadding();
				
				return temp1 / 2;	
			case LeftBottom:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getRecBottomBorderSize()
					- getBottomPadding()
				);	
			case Top:
				return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
			case Center:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
				}
				
				final int temp2
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getRecTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getRecBottomBorderSize()
				- getBottomPadding();
				
				return temp2 / 2;
			case Bottom:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getRecBottomBorderSize()
					- getBottomPadding()
				);
			case RightTop:
				return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
			case Right:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
				}
				
				final int temp3
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getRecTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getRecBottomBorderSize()
				- getBottomPadding();
				
				return temp3 / 2;
			case RightBottom:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getRecBottomBorderSize()
					- getBottomPadding()
				);
		}
		
		throw new RuntimeException();
	}
	
	//method
	/**
	 * @return the current height of the content of this borderable rectangle
	 */
	protected abstract int getContentHeight();
	
	//method
	/**
	 * @return the current width of the content of this borderable rectangle
	 */
	protected abstract int getContentWidth();
	
	//method
	/**
	 * @return the current height of this rectangle when it is not collapsed
	 */
	protected final int getHeightWhenNotCollapsed() {
		
		final BRS rectangleStructure = getRefCurrentStructure();
		
		final int baseHeight
		= rectangleStructure.getRecTopBorderSize()
		+ getTopPadding()
		+ getContentHeight()
		+ getBottomPadding()
		+ rectangleStructure.getRecBottomBorderSize();
		
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
		
		final BRS rectangleStructure = getRefCurrentStructure();
		
		final int baseWidth
		= rectangleStructure.getRecLeftBorderSize()
		+ getLeftPadding()
		+ getContentWidth()
		+ getRightPadding()
		+ rectangleStructure.getRecRightBorderSize();
		
		if (!hasMinWidth()) {
			return baseWidth;
		}
		
		return Calculator.getMax(getMinWidth(), baseWidth);
	}
	
	//method
	/**
	 * Paints this rectangle using the given rectangle structure and graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected final void paint(BRS rectangleStructure, Graphics graphics) {
		
		//Paints background color if the given rectangle structure has a background color.
		if (rectangleStructure.hasRecBackgroundColor()) {
			rectangleStructure.getRefRecBackgroundColor().paintRectangle(graphics, 0, 0, getWidth(), getHeight());
		}
		
		//Paints left border if the given rectangle structure has a left border.
		if (rectangleStructure.hasRecLeftBorder()) {
			graphics.setColor(rectangleStructure.getRefRecLeftBorderColor().getJavaColor());
			graphics.fillRect(
				0,
				0,
				rectangleStructure.getRecLeftBorderSize(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints right border if the given rectangle structure has a right border.
		if (rectangleStructure.hasRecRightBorder()) {
			graphics.setColor(rectangleStructure.getRefRecRightBorderColor().getJavaColor());
			graphics.fillRect(
				getWidth() - rectangleStructure.getRecLeftBorderSize(),
				0,
				rectangleStructure.getRecLeftBorderSize(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints top border if the given rectangle structure has a top border.
		if (rectangleStructure.hasRecTopBorder()) {
			graphics.setColor(rectangleStructure.getRefRecTopBorderColor().getJavaColor());
			graphics.fillRect(
				0,
				0,
				getWidth(),
				rectangleStructure.getRecTopBorderSize()
			);
		}
		
		//Paints bottom border if the given rectangle structure has a bottom border.
		if (rectangleStructure.hasRecBottomBorder()) {
			graphics.setColor(rectangleStructure.getRefRecBottomBorderColor().getJavaColor());
			graphics.fillRect(
				0,
				getHeightWhenNotCollapsed() - rectangleStructure.getRecBottomBorderSize(),
				getWidth(),
				rectangleStructure.getRecBottomBorderSize()
			);
		}

		graphics.translate(getContentXPosition(), getContentYPosition());
		paintContent(rectangleStructure, graphics);
		graphics.translate(-getContentXPosition(), -getContentYPosition());
	}
	
	//abstract method
	/**
	 * Paints the content of this borderable rectangle using the given rectangle structure and graphics.
	 * 
	 * @param rectangleStructure
	 * @param graphics
	 */
	protected abstract void paintContent(BRS rectangleStructure, Graphics graphics);
}
