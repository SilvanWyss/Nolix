//package declaration
package ch.nolix.element.GUI;

//own imports
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 150
 */
public final class BorderWidgetContentArea<BW extends BorderWidget<BW, BWL>, BWL extends BorderWidgetLook<BWL>> {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetContentArea} belongs to.
	 */
	private final BorderWidget<BW, BWL> parentBorderWidget;
	
	//package-visible constructor
	/**
	 * Creates a new {@link BorderWidgetContentArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws NullArgumentException if the given parentBorderWidget is null.
	 */
	BorderWidgetContentArea(final BorderWidget<BW, BWL> parentBorderWidget) {
		
		//Checks if the given parentBorderWidget is not null.
		Validator.suppose(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetContentArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetContentArea}.
	 */
	public int getCursorXPosition() {
		return (parentBorderWidget.getCursorXPositionOnScrolledArea() - getXPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetContentArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getCursorYPositionOnScrolledArea() - getYPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidgetContentArea}.
	 */
	public int getHeight() {
		return parentBorderWidget.getContentAreaHeight();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetContentArea}.
	 */
	public int getWidth() {
		return parentBorderWidget.getContentAreaWidth();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetContentArea} on the {@link BorderWidgetScrolledArea}
	 * of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnScrolledArea() {
		
		final var look = parentBorderWidget.getRefCurrentLook();
		
		//Enumerates the content position of the current border widget.
		switch (parentBorderWidget.getContentPosition()) {
			case LeftTop:
			case Left:
			case LeftBottom:
				return look.getRecursiveOrDefaultLeftPadding();
			case Top:
			case Center:
			case Bottom:
				return (parentBorderWidget.getScrolledArea().getWidth() - parentBorderWidget.getContentAreaWidth()) / 2;
			case RightTop:
			case Right:
			case RightBottom:				
				return
				parentBorderWidget.getScrolledArea().getWidth()
				- parentBorderWidget.getContentAreaWidth()
				- look.getRecursiveOrDefaultRightPadding();
		}
		
		throw new InvalidArgumentException(this);
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetContentArea} on the {@link BorderWidgetScrolledArea}
	 * of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnScrolledArea() {
		
		final var look = parentBorderWidget.getRefCurrentLook();
		
		//Enumerates the content orientation of the current border widget.
		switch (parentBorderWidget.getContentPosition()) {
			case LeftTop:
			case Top:
			case RightTop:
				return look.getRecursiveOrDefaultTopPadding();
			case Left:
			case Center:
			case Right:
				return
				(parentBorderWidget.getScrolledArea().getHeight() - parentBorderWidget.getContentAreaHeight()) / 2;
			case LeftBottom:
			case Bottom:
			case RightBottom:
				return
				parentBorderWidget.getScrolledArea().getHeight()
				- parentBorderWidget.getContentAreaHeight()
				- look.getRecursiveOrDefaultBottomPadding();
		}
		
		throw new InvalidArgumentException(this);
	}
	
	//method
	public boolean isUnderCursor() {		
		return
		getCursorXPosition() >= 1
		&& getCursorYPosition() >= 1
		&& getCursorXPosition() <= getWidth()
		&& getCursorYPosition() <= getHeight();
	}
	
	//package-visible method
	void paint(final BWL borderWidgetLook, final IPainter painter) {
		
		parentBorderWidget.paintContentArea(borderWidgetLook, painter);
		
		parentBorderWidget.getChildWidgets().forEach(cw -> cw.paint(painter));
	}
}
