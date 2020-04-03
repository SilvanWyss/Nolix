//package declaration
package ch.nolix.element.widgets;

//own imports
import ch.nolix.common.invalidArgumentExceptions.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
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
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetContentArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
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
		return (parentBorderWidget.getScrolledArea().getCursorXPosition() - getXPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetContentArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getScrolledArea().getCursorYPosition() - getYPositionOnScrolledArea());
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
		
		final var look = parentBorderWidget.getRefLook();
		
		//Enumerates the content position of the current border widget.
		switch (parentBorderWidget.getContentPosition()) {
			case LEFT_TOP:
			case LEFT:
			case LEFT_BOTTOM:
				return look.getRecursiveOrDefaultLeftPadding();
			case TOP:
			case CENTER:
			case BOTTOM:
				return (parentBorderWidget.getScrolledArea().getWidth() - parentBorderWidget.getContentAreaWidth()) / 2;
			case RIGHT_TOP:
			case RIGHT:
			case RIGHT_BOTTOM:				
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
		
		final var look = parentBorderWidget.getRefLook();
		
		//Enumerates the content orientation of the current border widget.
		switch (parentBorderWidget.getContentPosition()) {
			case LEFT_TOP:
			case TOP:
			case RIGHT_TOP:
				return look.getRecursiveOrDefaultTopPadding();
			case LEFT:
			case CENTER:
			case RIGHT:
				return
				(parentBorderWidget.getScrolledArea().getHeight() - parentBorderWidget.getContentAreaHeight()) / 2;
			case LEFT_BOTTOM:
			case BOTTOM:
			case RIGHT_BOTTOM:
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
	
	//method
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		parentBorderWidget.paintContentArea(borderWidgetLook, painter);
		
		parentBorderWidget.getRefPaintableWidgets().forEach(cw -> cw.paintRecursively(painter));
	}
}
