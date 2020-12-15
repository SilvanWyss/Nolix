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
 * @lines 280
 * @param <BWL>
 * The type of the {@link BorderWidgetLook} of the {@link BorderWidget}
 * of the current {@link BorderWidgetBorderedArea}.
 */
public final class BorderWidgetBorderedArea<BWL extends BorderWidgetLook<BWL>> implements HoverableByCursor {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetBorderedArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetBorderedArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetBorderedArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
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
	 * @return the max height of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getMaxHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMainArea().getMaxHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getMaxWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMainArea().getMaxWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * @return the min height of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getMinHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMainArea().getMinHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the min width of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getMinWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMainArea().getMinWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getNaturalHeight() {
		return
		parentBorderWidget.getShowArea().getNaturalHeight()
		+ parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getNaturalWidth() {
		return
		parentBorderWidget.getShowArea().getNaturalWidth()
		+ parentBorderWidget.getVerticalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getProposalHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMainArea().getProposalHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getProposalWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMainArea().getProposalWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness();
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
		
		//Paints the vertical scroll bar of the BorderWidget of the current BorderWidgetBorderedArea if
		//the BorderWidget of the current BorderWidgetBorderedArea has a vertical scroll bar.
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
		
		//Paints the horizontal scroll bar of the BorderWidget of the current BorderWidgetBorderedArea if
		//the BorderWidget of the current BorderWidgetBorderedArea has a horizontal scroll bar.
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
