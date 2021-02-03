//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseguiapi.HoverableByCursor;
import ch.nolix.element.color.Color;
import ch.nolix.element.painterapi.IPainter;

//class
/**
* A {@link BorderWidgetHorizontalScrollBarCursor} does not store or mutate data.
* 
* @author Silvan Wyss
* @date 2020-02-03
* @lines 200
* @param <BWL> is the type of the {@link BorderWidgetLook}
* of the {@link BorderWidget} a {@link BorderWidgetHorizontalScrollBarCursor} belongs to.
*/
public final class BorderWidgetHorizontalScrollBarCursor<BWL extends BorderWidgetLook<BWL>>
implements HoverableByCursor {
	
	//constant
	public static final int MIN_WIDTH = 10;
	
	//constant
	private static final int Y_POSITION_ON_HORIZONTAL_SCROLL_BAR = 0;
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetHorizontalScrollBarCursor} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetHorizontalScrollBarCursor} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetHorizontalScrollBarCursor(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetHorizontalScrollBarCursor.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the color of the current {@link BorderWidgetHorizontalScrollBarCursor}.
	 */
	public Color getColor() {
		
		if (!parentBorderWidget.isMovingHorizontalScrollBarCursor()) {
			return getColorWhenIsNotMoved();
		}
		
		return getColorWhenIsMoved();
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
		return parentBorderWidget.getHorizontalScrollBar().getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return Calculator.getMax(MIN_WIDTH, getNaturalWidth());
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetHorizontalScrollBarCursor}
	 * on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getXPosition() {
		return (parentBorderWidget.getHorizontalScrollBar().getXPosition() + getXPositionOnHorizontalScrollBar());
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetHorizontalScrollBarCursor}
	 * on the {@link BorderWidgetHorizontalScrollBar} of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnHorizontalScrollBar() {
		
		final var showAreaWidth = parentBorderWidget.getShowArea().getWidth();
		
		return
		(showAreaWidth - getWidth())
		* parentBorderWidget.getShowAreaXPositionOnScrolledArea()
		/ (parentBorderWidget.getScrolledArea().getWidth() - showAreaWidth);
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetHorizontalScrollBarCursor}
	 * on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getYPosition() {
		return (parentBorderWidget.getHorizontalScrollBar().getYPosition() + getYPositionOnHorizontalScrollBar());
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetHorizontalScrollBarCursor}
	 * on the {@link BorderWidgetHorizontalScrollBar} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnHorizontalScrollBar() {
		return Y_POSITION_ON_HORIZONTAL_SCROLL_BAR;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUnderCursor() {
		return isVisible() && HoverableByCursor.super.isUnderCursor();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetHorizontalScrollBarCursor} is visible.
	 */
	public boolean isVisible() {
		return parentBorderWidget.getHorizontalScrollBar().isVisible();
	}
	
	//visibility-reduced method
	/**
	 * Paints the current {@link BorderWidgetHorizontalScrollBarCursor} using the given painter.
	 * 
	 * @param painter
	 */
	void paint(final IPainter painter) {
		
		painter.setColor(getColor());
		
		painter.paintFilledRectangle(getWidth(), getHeight());
	}
	
	//method
	private Color getColorWhenIsMoved() {
		return parentBorderWidget.getRefLook().getRecursiveOrDefaultSelectionScrollBarLook().getScrollCursorColor();
	}
	
	//method
	private Color getColorWhenIsNotMoved() {
		
		final var look = parentBorderWidget.getRefLook();
		
		if (!isUnderCursor()) {
			return look.getRecursiveOrDefaultBaseScrollBarLook().getScrollCursorColor();
		}
		
		return look.getRecursiveOrDefaultHoverScrollBarLook().getScrollCursorColor();
	}

	//method
	private int getNaturalWidth() {
		return
		(int)(
			Math.pow(parentBorderWidget.getShowArea().getWidth(), 2)
			/ parentBorderWidget.getScrolledArea().getWidth()
		);
	}
}
