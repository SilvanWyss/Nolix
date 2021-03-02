//package declaration
package ch.nolix.element.widget;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.baseguiapi.HoverableByCursor;
import ch.nolix.element.color.Color;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * A {@link BorderWidgetVerticalScrollBar} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2020-02-02
 * @lines 220
 * @param <BWL> is the type of the {@link BorderWidgetLook}
 * of the {@link BorderWidget} a {@link BorderWidgetVerticalScrollBar} belongs to.
 */
public final class BorderWidgetVerticalScrollBar<BWL extends BorderWidgetLook<BWL>> implements HoverableByCursor {
	
	//constant
	public static final int THICKNESS = 20;
	
	//constant
	private static final int Y_POSITION_ON_BORDERED_AREA = 0;
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetVerticalScrollBar} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetVerticalScrollBar} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetVerticalScrollBar(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetVerticalScrollBar.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the color of the current {@link BorderWidgetVerticalScrollBar}.
	 */
	public Color getColor() {
		
		if (!parentBorderWidget.isMovingVerticalScrollBarCursor()) {
			return getColorWhenVerticalScrollBarCursorIsNotMoved();
		}
		
		return getColorWhenVerticalScrollBarCursorIsMoved();
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
		return parentBorderWidget.getShowArea().getHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		
		if (!isVisible()) {
			return 0;
		}
		
		return THICKNESS;
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetVerticalScrollBar}
	 * on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getXPosition() {
		return (parentBorderWidget.getBorderedArea().getXPosition() + getXPositionOnBorderedArea());
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetVerticalScrollBar}
	 * on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getYPosition() {
		return (parentBorderWidget.getBorderedArea().getYPosition() + getYPositionOnBorderedArea());
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetVerticalScrollBar}
	 * on the {@link BorderWidgetBorderedArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnBorderedArea() {
		return (parentBorderWidget.getBorderedArea().getWidth() - getWidth());
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetVerticalScrollBar}
	 * on the {@link BorderWidgetBorderedArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnBorderedArea() {
		return Y_POSITION_ON_BORDERED_AREA;
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
	 * This method determines if the current {@link BorderWidgetVerticalScrollBar} is visible.
	 * 
	 * There can be the following issues.
	 * -The current {@link BorderWidgetVerticalScrollBar} is not visible, but is expected to be visible.
	 * -The current {@link BorderWidgetVerticalScrollBar} is visible, but is expected not to be visible.
	 * 
	 * No matter which result this method returns, the program works.
	 * This method makes a good, but not absolute guess,
	 * if the current {@link BorderWidgetVerticalScrollBar} is visible.
	 * 
	 * @return true if the current {@link BorderWidgetVerticalScrollBar} is visible.
	 */
	public boolean isVisible() {
		
		final var naturalHeight = parentBorderWidget.getScrolledArea().getNaturalHeight();
		
		return
		(parentBorderWidget.hasMaxHeight() && parentBorderWidget.getMaxHeight() < naturalHeight)
		|| (parentBorderWidget.hasProposalHeight() && parentBorderWidget.getProposalHeight() < naturalHeight);
	}
	
	//visibility-reduced method
	/**
	 * Paints the current {@link BorderWidgetVerticalScrollBar} using the given painter.
	 * 
	 * @param painter
	 */
	void paint(final IPainter painter) {
		if (isVisible()) {
			paintWhenVisible(painter);
		}		
	}
	
	//method
	private Color getColorWhenVerticalScrollBarCursorIsMoved() {
		return parentBorderWidget.getRefLook().getRecursiveOrDefaultSelectionScrollBarLook().getScrollBarColor();
	}
	
	//method
	private Color getColorWhenVerticalScrollBarCursorIsNotMoved() {
		
		final var look = parentBorderWidget.getRefLook();
		
		if (!parentBorderWidget.getVerticalScrollBarCursor().isUnderCursor()) {
			return look.getRecursiveOrDefaultBaseScrollBarLook().getScrollBarColor();
		}
		
		return look.getRecursiveOrDefaultHoverScrollBarLook().getScrollBarColor();
	}
	
	//method
	private void paintVerticalScrollBarCursor(final IPainter painter) {
		
		final var verticalScrollBarCursor = parentBorderWidget.getVerticalScrollBarCursor();
		
		verticalScrollBarCursor.paint(
			painter.createPainter(
				verticalScrollBarCursor.getXPositionOnVerticalScrollBar(),
				verticalScrollBarCursor.getYPositionOnVerticalScrollBar()
			)
		);
	}
	
	//method
	private void paintWhenVisible(IPainter painter) {
		
		painter.setColor(getColor());
		painter.paintFilledRectangle(getWidth(), getHeight());
		
		paintVerticalScrollBarCursor(painter);
	}
}
