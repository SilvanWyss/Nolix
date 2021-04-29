//package declaration
package ch.nolix.element.gui.widget;

import ch.nolix.common.container.SingleContainer;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.baseapi.HoverableByCursor;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
* A {@link BorderWidgetHorizontalScrollBar} does not store or mutate data.
* 
* @author Silvan Wyss
* @date 2020-02-03
* @lines 220
* @param <BWL> is the type of the {@link BorderWidgetLook}
* of the {@link BorderWidget} a {@link BorderWidgetHorizontalScrollBar} belongs to.
*/
public class BorderWidgetHorizontalScrollBar<BWL extends BorderWidgetLook<BWL>> implements HoverableByCursor {
	
	//constant
	public static final int THICKNESS = 20;
	
	//constant
	private static final int X_POSITION_ON_BORDERED_AREA = 0;
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetHorizontalScrollBar} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetHorizontalScrollBar} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetHorizontalScrollBar(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetHorizontalScrollBar.
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
		
		if (!isVisible()) {
			return 0;
		}
		
		return THICKNESS;
	}
	
	//method
	/**
	 * @return the color of the current {@link BorderWidgetHorizontalScrollBarCursor}.
	 */
	public SingleContainer<Color> getOptionalColor() {
		
		if (!parentBorderWidget.isMovingHorizontalScrollBarCursor()) {
			return getOptionalColorWhenHorizontalScrollBarCursorIsNotMoved();
		}
		
		return getOptionalColorWhenHorizontalScrollBarCursorIsMoved();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return parentBorderWidget.getShowArea().getWidth();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetHorizontalScrollBar}
	 * on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getXPosition() {
		return parentBorderWidget.getBorderedArea().getXPosition();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetHorizontalScrollBar}
	 * on the {@link BorderWidgetBorderedArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		return (parentBorderWidget.getBorderedArea().getYPosition() + parentBorderWidget.getShowArea().getHeight());
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetHorizontalScrollBar}
	 * on the {@link BorderWidgetBorderedArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnBorderedArea() {
		return X_POSITION_ON_BORDERED_AREA;
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetHorizontalScrollBar}
	 * on the {@link BorderWidgetBorderedArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnBorderedArea() {
		return (parentBorderWidget.getBorderedArea().getHeight() - getHeight());
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
	 * This method determines if the current {@link BorderWidgetHorizontalScrollBar} is visible.
	 * 
	 * There can be the following issues.
	 * -The current {@link BorderWidgetHorizontalScrollBar} is not visible, but is expected to be visible.
	 * -The current {@link BorderWidgetHorizontalScrollBar} is visible, but is expected not to be visible.
	 * 
	 * No matter which result this method returns, the program works.
	 * This method makes a good, but not absolute guess,
	 * if the current {@link BorderWidgetHorizontalScrollBar} is visible.
	 * 
	 * @return true if the current {@link BorderWidgetHorizontalScrollBar} is visible.
	 */
	public boolean isVisible() {
		
		final var naturalWidth = parentBorderWidget.getScrolledArea().getNaturalWidth();
		
		return
		(parentBorderWidget.hasMaxWidth() && parentBorderWidget.getMaxWidth() < naturalWidth)
		|| (parentBorderWidget.hasProposalWidth() && parentBorderWidget.getProposalWidth() < naturalWidth);
	}
	
	//method
	/**
	 * Paints the current {@link BorderWidgetHorizontalScrollBar} using the given painter.
	 * 
	 * @param painter
	 */
	void paint(final IPainter painter) {
		if (isVisible()) {
			paintWhenVisible(painter);
		}		
	}
	
	//method
	private SingleContainer<Color> getOptionalColorWhenHorizontalScrollBarCursorIsMoved() {
		return parentBorderWidget.getRefLook().getOptionalScrollBarMoveColor();
	}
	
	//method
	private SingleContainer<Color> getOptionalColorWhenHorizontalScrollBarCursorIsNotMoved() {
		
		final var look = parentBorderWidget.getRefLook();
		
		if (!parentBorderWidget.getHorizontalScrollBarCursor().isUnderCursor()) {
			return look.getOptionalScrollBarColor();
		}
		
		return look.getOptionalScrollBarMoveColor();
	}
	
	//method
	private void paintHorizontalScrollBarCursor(final IPainter painter) {
		
		final var horizontalScrollBarCursor = parentBorderWidget.getHorizontalScrollBarCursor();
		
		horizontalScrollBarCursor.paint(
			painter.createPainter(
				horizontalScrollBarCursor.getXPositionOnHorizontalScrollBar(),
				horizontalScrollBarCursor.getYPositionOnHorizontalScrollBar()
			)
		);
	}
	
	//method
	private void paintWhenVisible(final IPainter painter) {
		
		final var color = getOptionalColor();
		if (color.containsAny()) {
			painter.setColor(color.getRefElement());
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
		
		paintHorizontalScrollBarCursor(painter);
	}
}
