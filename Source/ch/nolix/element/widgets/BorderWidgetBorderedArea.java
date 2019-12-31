//package declaration
package ch.nolix.element.widgets;

import ch.nolix.common.validator.Validator;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 160
 */
public final class BorderWidgetBorderedArea<BW extends BorderWidget<BW, BWL>, BWL extends BorderWidgetLook<BWL>> {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetBorderedArea} belongs to.
	 */
	private final BorderWidget<BW, BWL> parentBorderWidget;
	
	//package-visible constructor
	/**
	 * Creates a new {@link BorderWidgetBorderedArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetBorderedArea(final BorderWidget<BW, BWL> parentBorderWidget) {
		
		//Checks if the given parentBorderWidget is not null.
		Validator.suppose(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetBorderedArea}.
	 */
	public int getCursorXPosition() {
		return (parentBorderWidget.getCursorXPosition() - getXPosition());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetBorderedArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getCursorYPosition() - getYPosition());
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getHeight() {
		return parentBorderWidget.getViewArea().getHeight() + parentBorderWidget.getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getWidth() {
		return parentBorderWidget.getViewArea().getWidth() + parentBorderWidget.getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetBorderedArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getXPosition() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return look.getRecursiveOrDefaultLeftBorderThickness();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetBorderedArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return look.getRecursiveOrDefaultTopBorderThickness();
	}
	
	//method
	/**
	 * Paints the current {@link BorderWidgetBorderedArea} using the given borderWidgetLook and painter.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		//Paints the vertical scroll bar if the parent BorderWidget has a vertical scrollbar.
		if (parentBorderWidget.hasVerticalScrollbar()) {
			
			//Paints the vertical scrollbar.				
				painter.setColor(parentBorderWidget.getVerticalScrollbarColor());
				
				painter.paintFilledRectangle(
					parentBorderWidget.getVerticalScrollbarXPositionOnBorderedArea(),
					0,
					parentBorderWidget.getVerticalScrollbarThickness(),
					parentBorderWidget.getViewArea().getHeight()
				);
			
			//Paints the vertical scrollbar cursor.				
				painter.setColor(parentBorderWidget.getVerticalScrollbarCursorColor());
				
				painter.paintFilledRectangle(
					parentBorderWidget.getVerticalScrollbarXPositionOnBorderedArea(),
					parentBorderWidget.getVerticalScrollbarCursorYPositionOnVerticalScrollbar(),
					parentBorderWidget.getVerticalScrollbarThickness(),
					parentBorderWidget.getVerticalScrollbarCursorHeight()
				);
		}
		
		//Paints the horizontal scrollbar if the parent BorderWidget has a horizontal scrollbar.
		if (parentBorderWidget.hasHorizontalScrollbar()) {
			
			//Paints the horizontal scrollbar.	
				painter.setColor(parentBorderWidget.getHorizontalScrollbarColor());
				
				painter.paintFilledRectangle(
					0,
					parentBorderWidget.getHorizontalScrollbarYPositionOnBorderedArea(),
					parentBorderWidget.getViewArea().getWidth(),
					parentBorderWidget.getHorizontalScrollbarThickness()
				);
			
			//Paints the horizontal scrollbar cursor.			
				painter.setColor(parentBorderWidget.getHorizontalScrollbarCursorColor());
				
				painter.paintFilledRectangle(
					parentBorderWidget.getHorizontalScrollbarCursorXPositionOnHorizontalScrollbar(),
					parentBorderWidget.getHorizontalScrollbarYPositionOnBorderedArea(),
					parentBorderWidget.getHorizontalScrollbarCursorWidth(),
					parentBorderWidget.getHorizontalScrollbarThickness()
				);
		}
		
		//Paints the view area of the parent BorderWidget.
		parentBorderWidget.getViewArea().paint(
			painter.createPainter(
				0,
				0,
				parentBorderWidget.getViewArea().getWidth(),	
				parentBorderWidget.getViewArea().getHeight()
			),
			borderWidgetLook
		);
	}
}
