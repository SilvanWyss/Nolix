//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.invalidStateException.UnexistingAttributeException;
import ch.nolix.core.mathematics.Calculator;
import ch.nolix.core.specification.Specification;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.validator2.Validator;
import ch.nolix.element.color.Color;
import ch.nolix.element.intData.MinHeight;
import ch.nolix.element.intData.MinWidth;

//abstract class
/**
 * A border widget is a background widget
 * that can have individual borders at each of its 4 sides.
 * 
 * The content of a border widget is the border widget without borders and paddings.
 * The methods concerning the content of a border widget are not public.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 670
 * @param <BW> The type of a border widget.
 * @param <BWS> The type of the widget structures of a border widget.
 */
public abstract class BorderWidget<
	BW extends BorderWidget<BW, BWS>,
	BWS extends BorderWidgetStructure<BWS>
>
extends BackgroundWidget<BW, BWS> {
	
	//type name
	public static final String TYPE_NAME = "Borderablewidget";
	
	//attribute
	private ContentPosition contentOrientation = ContentPosition.Center;
	
	//optional attributes
	private MinWidth minWidth;
	private MinHeight minHeight;
	
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
				setContentOrientation(
					ContentPosition.valueOf(attribute.getOneAttributeAsString())
				);
				break;
			case MinWidth.TYPE_NAME:
				setMinWidth(attribute.getOneAttributeAsInt());
				break;
			case MinHeight.TYPE_NAME:
				setMinHeight(attribute.getOneAttributeAsInt());
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
		
		attributes.addAtEnd(contentOrientation.getSpecification());
		
		//Handles the case that this border widget has a min width.
		if (hasMinWidth()) {
			attributes.addAtEnd(minWidth.getSpecification());
		}
		
		//Handles the case that this border widget has a min height.
		if (hasMinHeight()) {
			attributes.addAtEnd(minHeight.getSpecification());
		}

		return attributes;
	}
	
	//method
	/**
	 * @return the content orientation of this border widget.
	 */
	public final ContentPosition getContentOrientation() {
		return contentOrientation;
	}
	
	//method
	/**
	 * @return the height of this border widget when it is not collapsed.
	 */
	public final int getHeightWhenNotCollapsed() {
		
		final BWS currentStructure = getRefCurrentStructure();
		
		final int baseHeight;
		if (!currentStructure.hasRecursiveScrollHeight()) {
			baseHeight =
			currentStructure.getActiveTopBorderSize()
			+ currentStructure.getActiveTopPadding()
			+ getContentHeight()
			+ currentStructure.getActiveBottomPadding()
			+ currentStructure.getActiveBottomBorderSize();
		}
		else {
			baseHeight = currentStructure.getActiveScrollHeight();
		}
		
		//final calculations
			//Handles the case that this border widget has no min height.
			if (!hasMinHeight()) {
				return baseHeight;
			}
			
			//Handles the case that this border widge has a min height.
			return Calculator.getMax(getMinHeight(), baseHeight);
	}
	
	//method
	/**
	 * @return the min height of this border widget.
	 * @throws UnexistingAttributeException if this border widget has no min height.
	 */
	public final int getMinHeight() {
		
		//Checks if this border widget has a min height.
		if (!hasMinHeight()) {
			throw new UnexistingAttributeException(this, "min height");
		}
		
		return minHeight.getValue();
	}
	
	//method
	/**
	 * @return the min width of this border widget.
	 * @throws UnexistingAttributeException if this border widget has no min width.
	 */
	public final int getMinWidth() {
		
		//Checks if this border widget has a min width.
		if (!hasMinWidth()) {
			throw new UnexistingAttributeException(this, "min width");
		}
		
		return minWidth.getValue();
	}
	
	//method
	/**
	 * @return the  width of this border widget when it is not collapsed.
	 */
	public final int getWidthWhenNotCollapsed() {
		
		final BWS currentStructure = getRefCurrentStructure();
		
		final int baseWidth
		= currentStructure.getActiveLeftBorderSize()
		+ currentStructure.getActiveLeftPadding()
		+ getContentWidth()
		+ currentStructure.getActiveRightPadding()
		+ currentStructure.getActiveRightBorderSize();
		
		//final calculations
			//Handles the case that this border widget has no min width.
			if (!hasMinWidth()) {
				return baseWidth;
			}
			
			//Handles the case that this border widget has a min width.
			return Calculator.getMax(getMinWidth(), baseWidth);
	}
	
	//method
	/**
	 * @return true if this border widget has a min height.
	 */	
	public final boolean hasMinHeight() {
		return (minHeight != null);
	}
	
	//method
	/**
	 * @return true if this border widget has a min width.
	 */
	public final boolean hasMinWidth() {
		return (minWidth != null);
	}
	
	//method
	/**
	 * Removes the min height of this border widget.
	 */
	public final void removeMinHeight() {
		minHeight = null;
	}
	
	//method
	/**
	 * Removes the min width of this border widget.
	 */
	public final void removeMinWidth() {
		minWidth = null;
	}
	
	//method
	/**
	 * Resets the configuration of this border widget.
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
	 * Sets the content position of this border widget.
	 * 
	 * @param contentPosition
	 * @return this border widget.
	 * @throws NullArgumentException if the given content position is null.
	 */
	@SuppressWarnings("unchecked")
	public final BW setContentOrientation(final ContentPosition contentPosition) {
		
		//Checks if the given content position is not null.
		Validator
		.suppose(contentPosition)
		.thatIsInstanceOf(ContentPosition.class)
		.isNotNull();

		//Sets the content position of this border widget.
		this.contentOrientation = contentPosition;
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the min height of this border widget.
	 * 
	 * @param minHeight
	 * @return this border widget.
	 * @throws NonPositiveArgumentException if the given min height is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setMinHeight(final int minHeight) {
		
		this.minHeight = new MinHeight(minHeight);
		
		return (BW)this;
	}
	
	//method
	/**
	 * Sets the min width of this border widget.
	 * 
	 * @param minWidth
	 * @return this border widget.
	 * @throws NonPositiveArgumentException if the given min width is not positive.
	 */
	@SuppressWarnings("unchecked")
	public final BW setMinWidth(final int minWidth) {
		
		this.minWidth = new MinWidth(minWidth);
		
		return (BW)this;
	}
	
	//method
	/**
	 * @return true if the content of this border widget is under mouse.
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
		
		final BWS currentStructure = getRefCurrentStructure();
		
		//Enumerates the content orientation of this border widget.
		switch (getContentOrientation()) {
			case LeftTop:
				return (
					currentStructure.getActiveLeftBorderSize()
					+ currentStructure.getActiveLeftPadding()
				);
			case Left:
				return (
					currentStructure .getActiveLeftBorderSize()
					+ currentStructure.getActiveLeftPadding()
				);
			case LeftBottom:
				return (
					currentStructure .getActiveLeftBorderSize()
					+ currentStructure.getActiveLeftPadding()
				);	
			case Top:
				
				if (!hasMinWidth()) {
					return (
						currentStructure .getActiveLeftBorderSize()
						+ currentStructure.getActiveLeftPadding()
					);
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
					return (
						getRefCurrentStructure().getActiveLeftBorderSize()
						+ currentStructure.getActiveLeftPadding()
					);
				}
				
				final int contentXPosition2
				= getMinWidth()
				- getContentWidth()
				+ currentStructure.getActiveLeftBorderSize()
				+ currentStructure.getActiveLeftPadding()
				- currentStructure.getActiveRightBorderSize()
				- currentStructure.getActiveRightPadding();
				
				return (contentXPosition2 / 2);
			case Bottom:
				
				if (!hasMinWidth()) {
					return (
						currentStructure.getActiveLeftBorderSize()
						+ currentStructure.getActiveLeftPadding()
					);
				}
				
				final int contentXPosition3
				= getMinWidth()
				- getContentWidth()
				+ currentStructure.getActiveLeftBorderSize()
				+ currentStructure.getActiveLeftPadding()
				- currentStructure.getActiveRightBorderSize()
				- currentStructure.getActiveRightPadding();
				
				return (contentXPosition3 / 2);
			case RightTop:
				
				if (!hasMinWidth()) {
					return (
						currentStructure.getActiveLeftBorderSize()
						+ currentStructure.getActiveLeftPadding()
					);
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- currentStructure.getActiveRightBorderSize()
					- currentStructure.getActiveRightPadding()
				);
			case Right:
			
				if (!hasMinWidth()) {
					return (
						currentStructure.getActiveLeftBorderSize()
						+ currentStructure.getActiveLeftPadding()
					);
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- currentStructure.getActiveRightBorderSize()
					- currentStructure.getActiveRightPadding()
				);
			case RightBottom:
				
				if (!hasMinWidth()) {
					return (
						currentStructure.getActiveLeftBorderSize()
						+ currentStructure.getActiveLeftPadding()
					);
				}
				
				return (
					getMinWidth()
					- getContentWidth()
					- currentStructure.getActiveRightBorderSize()
					- currentStructure.getActiveRightPadding()
				);
		}
		
		throw new RuntimeException();
	}
	
	//method
	/**
	 * @return the y-position of the content of this border widget.
	 */
	protected final int getContentYPosition() {
		
		final BWS currentStructure = getRefCurrentStructure();
		
		//Enumerates the content orientation of this border widget.
		switch (getContentOrientation()) {
			case LeftTop:
				return (
					currentStructure.getActiveTopBorderSize()
					+ currentStructure.getActiveTopPadding()
				);
			case Left:
				
				if (!hasMinHeight()) {
					return (
						currentStructure.getActiveTopBorderSize()
						+ currentStructure.getActiveTopPadding()
					);
				}
				
				final int contentYPosition1
				= getMinHeight()
				- getContentHeight()
				+ currentStructure.getActiveTopBorderSize()
				+ currentStructure.getActiveTopPadding()
				- currentStructure.getActiveBottomBorderSize()
				- currentStructure.getActiveBottomPadding();
				
				return (contentYPosition1 / 2);	
			case LeftBottom:
				
				if (!hasMinHeight()) {
					return (
						currentStructure.getActiveTopBorderSize()
						+ currentStructure.getActiveTopPadding()
					);
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- currentStructure.getActiveBottomBorderSize()
					- currentStructure.getActiveBottomPadding()
				);	
			case Top:
				return (
					currentStructure.getActiveTopBorderSize()
					+ currentStructure.getActiveTopPadding()
				);
			case Center:
				
				if (!hasMinHeight()) {
					return (
						currentStructure.getActiveTopBorderSize()
						+ currentStructure.getActiveTopPadding()
					);
				}
				
				final int contentYPosition2
				= getMinHeight()
				- getContentHeight()
				+ currentStructure.getActiveTopBorderSize()
				+ currentStructure.getActiveTopPadding()
				- currentStructure.getActiveBottomBorderSize()
				- currentStructure.getActiveBottomPadding();
				
				return (contentYPosition2 / 2);
			case Bottom:
				
				if (!hasMinHeight()) {
					return (
						currentStructure.getActiveTopBorderSize()
						+ currentStructure.getActiveTopPadding()
					);
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- currentStructure.getActiveBottomBorderSize()
					- currentStructure.getActiveBottomPadding()
				);
			case RightTop:
				return (
					currentStructure.getActiveTopBorderSize()
					+ currentStructure.getActiveTopPadding()
				);
			case Right:
				
				if (!hasMinHeight()) {
					return (
						currentStructure.getActiveTopBorderSize()
						+ currentStructure.getActiveTopPadding()
					);
				}
				
				final int contentYPosition3
				= getMinHeight()
				- getContentHeight()
				+ currentStructure.getActiveTopBorderSize()
				+ currentStructure.getActiveTopPadding()
				- currentStructure.getActiveBottomBorderSize()
				- currentStructure.getActiveBottomPadding();
				
				return (contentYPosition3 / 2);
			case RightBottom:
				
				if (!hasMinHeight()) {
					return (
						currentStructure.getActiveTopBorderSize()
						+ currentStructure.getActiveTopPadding()
					);
				}
				
				return (
					getMinHeight()
					- getContentHeight()
					- currentStructure.getActiveBottomBorderSize()
					- currentStructure.getActiveBottomPadding()
				);
		}
		
		throw new RuntimeException();
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
		
		//Paints the vertical scroll bar if the given widget structure has a recursive scroll height.
		if (widgetStructure.hasRecursiveScrollHeight()) {
			
			graphics.setColor(Color.LIGHT_GREY.getJavaColor());
			graphics.fillRect(getWidth() - 10, 0, 10, getHeight());
			
			final int scrollCursorHeight = (int)(getHeight() * (1.0 * widgetStructure.getActiveScrollHeight() / getContentHeight()));
			
			graphics.setColor(Color.BLACK.getJavaColor());
			graphics.fillRect(getWidth() - 10, 0, 10, scrollCursorHeight);
		}

		paintContent(
			widgetStructure,
			graphics.create(
				getContentXPosition(),
				getContentYPosition(),
				getContentWidth(),
				getContentHeight()
			)
		);
	}
	
	//abstract method
	/**
	 * Paints the content of this border widget using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected abstract void paintContent(BWS widgetStructure, Graphics graphics);
}
