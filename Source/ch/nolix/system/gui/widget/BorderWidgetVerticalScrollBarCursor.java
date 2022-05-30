//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.Calculator;
import ch.nolix.system.gui.color.Color;
import ch.nolix.systemapi.guiapi.baseapi.HoverableByCursor;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
/**
 * A {@link BorderWidgetVerticalScrollBarCursor} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2020-02-02
 * @param <BWL> is the type of the {@link BorderWidgetLook}
 * of the {@link BorderWidget} a {@link BorderWidgetVerticalScrollBarCursor} belongs to.
 */
public final class BorderWidgetVerticalScrollBarCursor<BWL extends BorderWidgetLook<BWL>>
implements HoverableByCursor {
	
	//constant
	public static final int MIN_HEIGHT = 10;
	
	//constant
	private static final int X_POSITION_ON_VERTICAL_SCROLL_BAR = 0;
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetVerticalScrollBarCursor} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetVerticalScrollBarCursor} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetVerticalScrollBarCursor(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		GlobalValidator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetVerticalScrollBarCursor.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the color of the current {@link BorderWidgetVerticalScrollBarCursor}.
	 */
	public Color getColor() {
		
		if (!parentBorderWidget.isMovingVerticalScrollBarCursor()) {
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
		return Calculator.getMax(MIN_HEIGHT, getNaturalHeight());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return parentBorderWidget.getVerticalScrollBar().getWidth();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetVerticalScrollBarCursor}
	 * on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getXPosition() {
		return (parentBorderWidget.getVerticalScrollBar().getXPosition() + getXPositionOnVerticalScrollBar());
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetVerticalScrollBarCursor}
	 * on the {@link BorderWidgetVerticalScrollBar} of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnVerticalScrollBar() {
		return X_POSITION_ON_VERTICAL_SCROLL_BAR;
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetVerticalScrollBarCursor}
	 * on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getYPosition() {
		return (parentBorderWidget.getVerticalScrollBar().getYPosition() + getYPositionOnVerticalScrollBar());
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetVerticalScrollBarCursor}
	 * on the {@link BorderWidgetVerticalScrollBar} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnVerticalScrollBar() {
		
		final var showAreaHeight = parentBorderWidget.getShowArea().getHeight();
		
		return
		(showAreaHeight - getHeight())
		* parentBorderWidget.getShowAreaYPositionOnScrolledArea()
		/ (parentBorderWidget.getScrolledArea().getHeight() - showAreaHeight);
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
	 * @return true if the current {@link BorderWidgetVerticalScrollBarCursor} is visible.
	 */
	public boolean isVisible() {
		return parentBorderWidget.getVerticalScrollBar().isVisible();
	}
	
	//method
	/**
	 * Paints the current {@link BorderWidgetVerticalScrollBarCursor} using the given painter.
	 * 
	 * @param painter
	 */
	void paint(final IPainter painter) {
		
		painter.setColor(getColor());
		
		painter.paintFilledRectangle(getWidth(), getHeight());
	}
	
	//method
	private Color getColorWhenIsMoved() {
		return parentBorderWidget.getRefActiveLook().getScrollCursorMoveColor();
	}
	
	//method
	private Color getColorWhenIsNotMoved() {
		
		final var look = parentBorderWidget.getRefActiveLook();
		
		if (!isUnderCursor()) {
			return look.getScrollCursorColor();
		}
		
		return look.getScrollCursorHoverColor();
	}
	
	//method
	private int getNaturalHeight() {
		return
		(int)(
			Math.pow(parentBorderWidget.getShowArea().getHeight(), 2)
			/ parentBorderWidget.getScrolledArea().getHeight()
		);
	}
}
