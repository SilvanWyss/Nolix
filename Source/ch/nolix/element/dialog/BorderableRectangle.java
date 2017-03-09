//package declaration
package ch.nolix.element.dialog;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.common.container.List;
import ch.nolix.common.exception.UnexistingAttributeException;
import ch.nolix.common.mathematics.Calculator;
import ch.nolix.common.specification.Specification;
import ch.nolix.common.zetaValidator.ZetaValidator;
import ch.nolix.element.basic.PositiveInteger;
import ch.nolix.element.data.MinHeight;
import ch.nolix.element.data.MinWidth;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 900
 * @param <BRS> - The type of the rectangle structures of a borderable rectangle.
 * @param <BR> - The type of a borderable rectangle.
 */
public abstract class BorderableRectangle<
	BRS extends BorderableRectangleStructure<BRS>,
	BR extends BorderableRectangle<BRS, BR>
>
extends Rectangle<BRS, BR> {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "BorderableRectangle";
	
	//attribute headers
	private static final String PADDING_HEADER = "Padding";
	private static final String LEFT_PADDING_HEADER = "LeftPadding";
	private static final String RIGHT_PADDING_HEADER = "RightPadding";
	private static final String BOTTOM_PADDING_HEADER = "BottomPadding";
	private static final String TOP_PADDING_HEADER = "TopPadding";
	
	//attribute
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
	 * Creates new borderable rectangle with the given structures.
	 * 
	 * @param normalStructure
	 * @param hoverStructure
	 * @param focusStructure
	 * @throws NullArgumentException if one of the given structures is null.
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
	 * Adds or changes the given attribute to this borderable rectangle.
	 * 
	 * @param attribute
	 * @throws InvalidArgumentException if the given attribute is not valid.
	 */
	public void addOrChangeAttribute(final Specification attribute) {
		
		//Enumerates the header of the given attribute.
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
	 * @return the attributes of this borderable rectangle.
	 */
	public List<Specification> getAttributes() {
		
		//Calls method of the base class
		final List<Specification> attributes = super.getAttributes();
		
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
				attributes.addAtEnd(new Specification(LEFT_PADDING_HEADER, leftPadding.getAttributes()));
			}
			if (hasRightPadding()) {
				attributes.addAtEnd(new Specification(RIGHT_PADDING_HEADER, rightPadding.getAttributes()));
			}
			if (hasTopPadding()) {
				attributes.addAtEnd(new Specification(TOP_PADDING_HEADER, topPadding.getAttributes()));
			}
			if (hasBottomPadding()) {
				attributes.addAtEnd(new Specification(BOTTOM_PADDING_HEADER, bottomPadding.getAttributes()));
			}
		}

		return attributes;
	}
	
	//method
	/**
	 * @return the bottom padding of this borderable rectangle.
	 */
	public final int getBottomPadding() {
		
		if (hasBottomPadding()) {
			return bottomPadding.getValue();
		}
		
		return 0;
	}
	
	//method
	/**
	 * @return the content orientation of this borderable rectangle.
	 */
	public final ContentOrientation getContentOrientation() {
		return contentOrientation;
	}
	
	//method
	/**
	 * @return the left padding of this borderable rectangle.
	 */
	public final int getLeftPadding() {
		
		//Handles the case if this borderable rectangle has actually a left padding.
		if (hasLeftPadding()) {
			return leftPadding.getValue();
		}
		
		//Handles the case if this borderable rectangle has actually no left padding.
		return 0;
	}
	
	//method
	/**
	 * @return the min height of this borderable rectangle.
	 * @throws UnexistingAttributeException if this borderable rectangle has no min height.
	 */
	public final int getMinHeight() {
		
		//Checks if this borderable rectangle has a min height.
		if (!hasMinHeight()) {
			throw new UnexistingAttributeException(this, "min height");
		}
		
		return minHeight.getValue();
	}
	
	//method
	/**
	 * @return the min width of this borderable rectangle.
	 * @throws UnexistringAttributeException if this borderable rectangle has no min width.
	 */
	public final int getMinWidth() {
		
		//Checks if this borderable rectangle has a min width.
		if (!hasMinWidth()) {
			throw new UnexistingAttributeException(this, "min width");
		}
		
		return minWidth.getValue();
	}
	
	//method
	/**
	 * @return the right padding of this borderable rectangle.
	 */
	public final int getRightPadding() {
		
		//Handles the case if this borderable rectangle has actually a right padding.
		if (hasRightPadding()) {
			return rightPadding.getValue();
		}
		
		//Handles the case if this borderable rectangle has actually no right padding.
		return 0;
	}
	
	//method
	/**
	 * @return the top padding of this borderable rectangle.
	 */
	public final int getTopPadding() {
		
		//Handles the case if this borderable rectangle has actually a top padding.
		if (hasTopPadding()) {
			return topPadding.getValue();
		}
		
		//Handles the case if this borderable rectangle has actually no top padding.
		return 0;
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a padding.
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
	 * @return true if this borderable rectangle has a bottom padding.
	 */
	public final boolean hasBottomPadding() {
		return (bottomPadding != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a left padding.
	 */
	public final boolean hasLeftPadding() {
		return (leftPadding != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a min height.
	 */	
	public final boolean hasMinHeight() {
		return (minHeight != null);
	}
	
	//method
	/**
	 * @return true if this borderable rectangle has a min width.
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
	 * @return true if this borderable rectangle has the same padding at each side.
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
	
	//method
	/**
	 * Resets the configuration of this borderable rectangle.
	 */
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
	 * Sets the bottom padding of this borderable rectangle.
	 * 
	 * @param bottomPadding
	 * @return this borderable rectangle.
	 * @throws NonPositiveArgumentException if the given bottom padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BR setBottomPadding(final int bottomPadding) {
		
		this.bottomPadding = new PositiveInteger(bottomPadding);
		
		return (BR)this;
	}
	
	//method
	/**
	 * Sets the content orientation of this borderable rectangle.
	 * 
	 * @param contentOrientation
	 * @return this borderable rectangle.
	 * @throws NullArgumentException if the given content orientation is null.
	 */
	@SuppressWarnings("unchecked")
	public final BR setContentOrientation(final ContentOrientation contentOrientation) {
		
		//Checks the given content orientation.
		ZetaValidator
		.supposeThat(contentOrientation)
		.thatIsInstanceOf(ContentOrientation.class)
		.isNotNull();

		//Sets the content orientation of this borderable rectangle.
		this.contentOrientation = contentOrientation;
		
		return (BR)this;
	}
	
	//method
	/**
	 * Sets the left padding of this borderable rectangle.
	 * 
	 * @param leftPadding
	 * @return this borderable rectangle.
	 * @throws NonPositiveArgumentException if the given left padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BR setLeftPadding(final int leftPadding) {
		
		this.leftPadding = new PositiveInteger(leftPadding);
		
		return (BR)this;
	}
	
	//method
	/**
	 * Sets the min height of this borderable rectangle structure.
	 * 
	 * @param minHeight
	 * @return this borderable rectangle.
	 * @throws NonPositiveArgumentException if the given min height is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BR setMinHeight(final int minHeight) {
		
		this.minHeight = new MinHeight(minHeight);
		
		return (BR)this;
	}
	
	//method
	/**
	 * Sets the min width of this borderable rectangle structure.
	 * 
	 * @param minWidth
	 * @return this borderable rectangle.
	 * @throws NonPositiveArgumentException if the given min width is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BR setMinWidth(final int minWidth) {
		
		this.minWidth = new MinWidth(minWidth);
		
		return (BR)this;
	}
	
	//method
	/**
	 * Sets the padding of this borderale rectangle.
	 * 
	 * @param padding
	 * @return this borderable rectangle.
	 * @throws NonPositiveArgumentException if the given padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BR setPadding(final int padding) {
		
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
	 * @return this borderable rectangle.
	 * @throws NonPositiveArgumentException if the given right padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BR setRightPadding(final int rightPadding) {
		
		this.rightPadding = new PositiveInteger(rightPadding);
		
		return (BR)this;
	}
	
	//method
	/**
	 * Sets the top padding of this borderable rectangle.
	 * 
	 * @param leftPadding
	 * @return this borderable rectangle.
	 * @throws NonPositiveArgumentException if the given top padding is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BR setTopPadding(final int topPadding) {
		
		this.topPadding = new PositiveInteger(topPadding);
		
		return (BR)this;
	}
	
	//method
	/**
	 * @return true if the content of this borderable rectangle is under mouse.
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
	 * @return true if the content of this borderable rectangle surrounds the given position.
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
	 * @return the height of the content of this borderable rectangle.
	 */
	protected abstract int getContentHeight();
	
	//method
	/**
	 * @return the width of the content of this borderable rectangle.
	 */
	protected abstract int getContentWidth();
	
	//method
	/**
	 * @return the x-position of the content of this borderable rectangle.
	 */
	protected final int getContentXPosition() {
		
		//Enumerates the content orientation of this borderable rectangle.
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
				
				final int contentXPosition1
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getRecLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getRecRightBorderSize()
				- getRightPadding();
				
				return (contentXPosition1 / 2);
			case Center:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
				}
				
				final int contentXPosition2
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getRecLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getRecRightBorderSize()
				- getRightPadding();
				
				return (contentXPosition2 / 2);
			case Bottom:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getRecLeftBorderSize() + getLeftPadding());
				}
				
				final int contentXPosition3
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getRecLeftBorderSize()
				+ getLeftPadding()
				- getRefCurrentStructure().getRecRightBorderSize()
				- getRightPadding();
				
				return (contentXPosition3 / 2);
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
	 * @return the y-position of the content of this borderable rectangle.
	 */
	protected final int getContentYPosition() {
		
		//Enumerates the content orientation of this borderable rectangle.
		switch (getContentOrientation()) {
			case LeftTop:
				return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
			case Left:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getRecTopBorderSize() + getTopPadding());
				}
				
				final int contentYPosition1
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getRecTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getRecBottomBorderSize()
				- getBottomPadding();
				
				return (contentYPosition1 / 2);	
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
				
				final int contentYPosition2
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getRecTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getRecBottomBorderSize()
				- getBottomPadding();
				
				return (contentYPosition2 / 2);
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
				
				final int contentYPosition3
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getRecTopBorderSize()
				+ getTopPadding()
				- getRefCurrentStructure().getRecBottomBorderSize()
				- getBottomPadding();
				
				return (contentYPosition3 / 2);
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
		
		//Paints the background color  if the given rectangle structure has a background color.
		if (rectangleStructure.hasRecBackgroundColor()) {
			rectangleStructure.getRefRecBackgroundColor().paintRectangle(graphics, 0, 0, getWidth(), getHeight());
		}
		
		//Paints the left border if the given rectangle structure has a left border.
		if (rectangleStructure.hasRecLeftBorder()) {
			graphics.setColor(rectangleStructure.getRefRecLeftBorderColor().getJavaColor());
			graphics.fillRect(
				0,
				0,
				rectangleStructure.getRecLeftBorderSize(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the right border if the given rectangle structure has a right border.
		if (rectangleStructure.hasRecRightBorder()) {
			graphics.setColor(rectangleStructure.getRefRecRightBorderColor().getJavaColor());
			graphics.fillRect(
				getWidth() - rectangleStructure.getRecLeftBorderSize(),
				0,
				rectangleStructure.getRecLeftBorderSize(),
				getHeightWhenNotCollapsed()
			);
		}
		
		//Paints the top border if the given rectangle structure has a top border.
		if (rectangleStructure.hasRecTopBorder()) {
			graphics.setColor(rectangleStructure.getRefRecTopBorderColor().getJavaColor());
			graphics.fillRect(
				0,
				0,
				getWidth(),
				rectangleStructure.getRecTopBorderSize()
			);
		}
		
		//Paints the bottom border if the given rectangle structure has a bottom border.
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
