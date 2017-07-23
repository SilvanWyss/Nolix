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
import ch.nolix.element.data.MinHeight;
import ch.nolix.element.data.MinWidth;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 890
 * @param <BW> The type of a border widget.
 * @param <BWS> The type of the widget structures of a border widget.
 */
public abstract class BorderWidget<
	BW extends BorderWidget<BW, BWS>,
	BWS extends BorderWidgetStructure<BWS>
>
extends BackgroundWidget<BW, BWS> {
	
	//constant
	public static final String SIMPLE_CLASS_NAME = "Borderablewidget";
	
	//attribute
	private ContentPosition contentOrientation = ContentPosition.Center;
	
	//optional attributes
	private MinWidth minWidth;
	private MinHeight minHeight;
	
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

		return attributes;
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
	 * Resets the configuration of this borderable widget.
	 */
	public void resetConfiguration() {
		
		//Calls method of the base class
		super.resetConfiguration();
		
		setContentOrientation(ContentPosition.LeftTop);
		removeMinWidth();
		removeMinHeight();
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
		
		final BWS currentStructure = getRefCurrentStructure();
		
		//Enumerates the content orientation of this borderable widget.
		switch (getContentOrientation()) {
			case LeftTop:
				return (currentStructure.getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());
			case Left:
				return (currentStructure .getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());
			case LeftBottom:
				return (currentStructure .getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());	
			case Top:
				
				if (!hasMinWidth()) {
					return (currentStructure .getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());
				}
				
				final int contentXPosition1
				= getMinWidth()
				- getContentWidth()
				+ currentStructure .getActiveLeftBorderSize()
				+ currentStructure.getActiveLeftPadding()
				- currentStructure.getActiveRightBorderSize()
				- currentStructure.getActiveRightPadding();
				
				return (contentXPosition1 / 2);
			case Center:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());
				}
				
				final int contentXPosition2
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getActiveLeftBorderSize()
				+ currentStructure.getActiveLeftPadding()
				- getRefCurrentStructure().getActiveRightBorderSize()
				- currentStructure.getActiveRightPadding();
				
				return (contentXPosition2 / 2);
			case Bottom:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());
				}
				
				final int contentXPosition3
				= getMinWidth()
				- getContentWidth()
				+ getRefCurrentStructure().getActiveLeftBorderSize()
				+ currentStructure.getActiveLeftPadding()
				- getRefCurrentStructure().getActiveRightBorderSize()
				- currentStructure.getActiveRightPadding();
				
				return (contentXPosition3 / 2);
			case RightTop:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getActiveRightBorderSize()
					- currentStructure.getActiveRightPadding()
				);
			case Right:
			
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getActiveRightBorderSize()
					- currentStructure.getActiveRightPadding()
				);
			case RightBottom:
				
				if (!hasMinWidth()) {
					return (getRefCurrentStructure().getActiveLeftBorderSize() + currentStructure.getActiveLeftPadding());
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- getRefCurrentStructure().getActiveRightBorderSize()
					- currentStructure.getActiveRightPadding()
				);
		}
		
		throw new RuntimeException();
	}
	
	//method
	/**
	 * @return the y-position of the content of this borderable widget.
	 */
	protected final int getContentYPosition() {
		
		final BWS currentStructure = getRefCurrentStructure();
		
		//Enumerates the content orientation of this borderable widget.
		switch (getContentOrientation()) {
			case LeftTop:
				return (currentStructure.getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
			case Left:
				
				if (!hasMinHeight()) {
					return (currentStructure.getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
				}
				
				final int contentYPosition1
				= getMinHeight()
				- getContentHeight()
				+ currentStructure.getActiveTopBorderSize()
				+ currentStructure.getActiveTopPadding()
				- getRefCurrentStructure().getActiveBottomBorderSize()
				- currentStructure.getActiveBottomPadding();
				
				return (contentYPosition1 / 2);	
			case LeftBottom:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getActiveBottomBorderSize()
					- currentStructure.getActiveBottomPadding()
				);	
			case Top:
				return (getRefCurrentStructure().getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
			case Center:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
				}
				
				final int contentYPosition2
				= getMinHeight()
				- getContentHeight()
				+ currentStructure.getActiveTopBorderSize()
				+ currentStructure.getActiveTopPadding()
				- getRefCurrentStructure().getActiveBottomBorderSize()
				- currentStructure.getActiveBottomPadding();
				
				return (contentYPosition2 / 2);
			case Bottom:
				
				if (!hasMinHeight()) {
					return (currentStructure.getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getActiveBottomBorderSize()
					- currentStructure.getActiveBottomPadding()
				);
			case RightTop:
				return (getRefCurrentStructure().getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
			case Right:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
				}
				
				final int contentYPosition3
				= getMinHeight()
				- getContentHeight()
				+ getRefCurrentStructure().getActiveTopBorderSize()
				+ currentStructure.getActiveTopPadding()
				- getRefCurrentStructure().getActiveBottomBorderSize()
				- currentStructure.getActiveBottomPadding();
				
				return (contentYPosition3 / 2);
			case RightBottom:
				
				if (!hasMinHeight()) {
					return (getRefCurrentStructure().getActiveTopBorderSize() + currentStructure.getActiveTopPadding());
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- getRefCurrentStructure().getActiveBottomBorderSize()
					- currentStructure.getActiveBottomPadding()
				);
		}
		
		throw new RuntimeException();
	}
	

	
	//method
	/**
	 * @return the current height of this widget when it is not collapsed
	 */
	public final int getHeightWhenNotCollapsed() {
		
		final BWS currentStructure = getRefCurrentStructure();
		
		final int baseHeight
		= currentStructure.getActiveTopBorderSize()
		+ currentStructure.getActiveTopPadding()
		+ getContentHeight()
		+ currentStructure.getActiveBottomPadding()
		+ currentStructure.getActiveBottomBorderSize();
		
		if (!hasMinHeight()) {
			return baseHeight;
		}
		
		return Calculator.getMax(getMinHeight(), baseHeight);
	}
	
	//method
	/**
	 * @return the current width of this rectanlge if it is not collapsed
	 */
	public final int getWidthWhenNotCollapsed() {
		
		final BWS currentStructure = getRefCurrentStructure();
		
		final int baseWidth
		= currentStructure.getActiveLeftBorderSize()
		+ currentStructure.getActiveLeftPadding()
		+ getContentWidth()
		+ currentStructure.getActiveRightPadding()
		+ currentStructure.getActiveRightBorderSize();
		
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
