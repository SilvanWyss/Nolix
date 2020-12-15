//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseguiapi.HoverableByCursor;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link BorderWidgetBorderedArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2019-05-05
 * @lines 180
 */
public final class BorderWidgetBorderedArea<BW extends BorderWidget<BW, BWL>, BWL extends BorderWidgetLook<BWL>>
implements HoverableByCursor {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetBorderedArea} belongs to.
	 */
	private final BorderWidget<BW, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetBorderedArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetBorderedArea(final BorderWidget<BW, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorXPosition() {
		return (parentBorderWidget.getCursorXPosition() - getXPosition());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorYPosition() {
		return (parentBorderWidget.getCursorYPosition() - getYPosition());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMainArea().getHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMainArea().getWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness();
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
	
	//visibility-reduced method
	/**
	 * Paints the current {@link BorderWidgetBorderedArea} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		//Paints the vertical scroll bar if the BorderWidget of the current BorderWidgetBorderedArea
		//has a vertical scroll bar.
		if (parentBorderWidget.hasActivatedVerticalScrollBar()) {
			
			//Paints the vertical scroll bar of the BorderWidget of the current BorderWidgetBorderedArea.				
			painter.setColor(parentBorderWidget.getVerticalScrollBarColor());	
			painter.paintFilledRectangle(
				parentBorderWidget.getVerticalScrollBarXPositionOnBorderedArea(),
				0,
				parentBorderWidget.getVerticalScrollBarThickness(),
				parentBorderWidget.getShowArea().getHeight()
			);
			
			//Paints the vertical scroll bar cursor of the BorderWidget of the current BorderWidgetBorderedArea.				
			painter.setColor(parentBorderWidget.getVerticalScrollBarCursorColor());
			painter.paintFilledRectangle(
				parentBorderWidget.getVerticalScrollBarXPositionOnBorderedArea(),
				parentBorderWidget.getVerticalScrollBarCursorYPositionOnVerticalScrollBar(),
				parentBorderWidget.getVerticalScrollBarThickness(),
				parentBorderWidget.getVerticalScrollBarCursorHeight()
			);
		}
		
		//Paints the horizontal scroll bar if the BorderWidget of the current BorderWidgetBorderedArea
		//has a horizontal scroll bar.
		if (parentBorderWidget.hasActivatedHorizontalScrollBar()) {
			
			//Paints the horizontal scroll bar of the BorderWidget of the current BorderWidgetBorderedArea.	
			painter.setColor(parentBorderWidget.getHorizontalScrollBarColor());
			painter.paintFilledRectangle(
				0,
				parentBorderWidget.getHorizontalScrollBarYPositionOnBorderedArea(),
				parentBorderWidget.getShowArea().getWidth(),
				parentBorderWidget.getHorizontalScrollBarThickness()
			);
			
			//Paints the horizontal scroll bar cursor of the BorderWidget of the current BorderWidgetBorderedArea.			
			painter.setColor(parentBorderWidget.getHorizontalScrollBarCursorColor());
			painter.paintFilledRectangle(
				parentBorderWidget.getHorizontalScrollBarCursorXPositionOnHorizontalScrollBar(),
				parentBorderWidget.getHorizontalScrollBarYPositionOnBorderedArea(),
				parentBorderWidget.getHorizontalScrollBarCursorWidth(),
				parentBorderWidget.getHorizontalScrollBarThickness()
			);
		}
		
		//Paints the show area of the BorderWidget of the current BorderWidgetBorderedArea.
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
