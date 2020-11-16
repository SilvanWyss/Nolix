//package declaration
package ch.nolix.element.widget;

//own imports
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
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetBorderedArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetBorderedArea(final BorderWidget<BW, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
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
		return parentBorderWidget.getShowArea().getHeight() + parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getWidth() {
		return parentBorderWidget.getShowArea().getWidth() + parentBorderWidget.getVerticalScrollBarThickness();
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
		
		//Paints the vertical scroll bar if the parent BorderWidget has a vertical scroll bar.
		if (parentBorderWidget.hasVerticalScrollBar()) {
			
			//Paints the vertical scroll bar.				
				painter.setColor(parentBorderWidget.getVerticalScrollBarColor());
				
				painter.paintFilledRectangle(
					parentBorderWidget.getVerticalScrollbarXPositionOnBorderedArea(),
					0,
					parentBorderWidget.getVerticalScrollBarThickness(),
					parentBorderWidget.getShowArea().getHeight()
				);
			
			//Paints the vertical scroll bar cursor.				
				painter.setColor(parentBorderWidget.getVerticalScrollBarCursorColor());
				
				painter.paintFilledRectangle(
					parentBorderWidget.getVerticalScrollbarXPositionOnBorderedArea(),
					parentBorderWidget.getVerticalScrollbarCursorYPositionOnVerticalScrollBar(),
					parentBorderWidget.getVerticalScrollBarThickness(),
					parentBorderWidget.getVerticalScrollBarCursorHeight()
				);
		}
		
		//Paints the horizontal scroll bar if the parent BorderWidget has a horizontal scroll bar.
		if (parentBorderWidget.hasHorizontalScrollBar()) {
			
			//Paints the horizontal scroll bar.	
				painter.setColor(parentBorderWidget.getHorizontalScrollBarColor());
				
				painter.paintFilledRectangle(
					0,
					parentBorderWidget.getHorizontalScrollBarYPositionOnBorderedArea(),
					parentBorderWidget.getShowArea().getWidth(),
					parentBorderWidget.getHorizontalScrollBarThickness()
				);
			
			//Paints the horizontal scroll bar cursor.			
				painter.setColor(parentBorderWidget.getHorizontalScrollBarCursorColor());
				
				painter.paintFilledRectangle(
					parentBorderWidget.getHorizontalScrollBarCursorXPositionOnHorizontalScrollbar(),
					parentBorderWidget.getHorizontalScrollBarYPositionOnBorderedArea(),
					parentBorderWidget.getHorizontalScrollBarCursorWidth(),
					parentBorderWidget.getHorizontalScrollBarThickness()
				);
		}
		
		//Paints the view area of the parent BorderWidget.
		parentBorderWidget.getShowArea().paint(
			painter.createPainter(
				0,
				0,
				parentBorderWidget.getShowArea().getWidth(),	
				parentBorderWidget.getShowArea().getHeight()
			),
			borderWidgetLook
		);
	}
}
