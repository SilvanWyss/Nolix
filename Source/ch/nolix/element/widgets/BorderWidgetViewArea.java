//package declaration
package ch.nolix.element.widgets;

import ch.nolix.common.math.Calculator;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 220
 */
public final class BorderWidgetViewArea<BW extends BorderWidget<BW, BWL>, BWL extends BorderWidgetLook<BWL>> {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetViewArea} belongs to.
	 */
	private final BorderWidget<BW, BWL> parentBorderWidget;
	
	//package-visible constructor
	/**
	 * Creates a new {@link BorderWidgetViewArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetViewArea(final BorderWidget<BW, BWL> parentBorderWidget) {
		
		//Checks if the given parentBorderWidget is not null.
		Validator.suppose(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetViewArea}.
	 */
	public int getCursorXPosition() {
		return (parentBorderWidget.getCursorXPosition() - getXPosition());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetViewArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getCursorYPosition() - getYPosition());
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidgetViewArea}.
	 */
	public int getHeight() {
		
		var viewAreaHeight = parentBorderWidget.hasProposalHeight() ? getProposalHeight() : getNaturalHeight();
		
		if (parentBorderWidget.hasMinHeight()) {
			viewAreaHeight = Calculator.getMax(viewAreaHeight, getMinHeight());
		}
		
		if (parentBorderWidget.hasMaxHeight()) {
			viewAreaHeight = Calculator.getMin(viewAreaHeight, getMaxHeight());
		}
		
		return viewAreaHeight;
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidgetViewArea}.
	 */
	public int getMaxHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMaxHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness()
		- parentBorderWidget.getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidgetViewArea}.
	 */
	public int getMaxWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMaxWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness()
		- parentBorderWidget.getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the min height of the current {@link BorderWidgetViewArea}.
	 */
	public int getMinHeight() {
		return parentBorderWidget.getScrolledArea().getMinHeight();
	}
	
	//method
	/**
	 * @return the min width of the current {@link BorderWidgetViewArea}.
	 */
	public int getMinWidth() {
		return parentBorderWidget.getScrolledArea().getMinWidth();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetViewArea}.
	 */
	public int getNaturalHeight() {
		return parentBorderWidget.getScrolledArea().getNaturalHeight();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidgetViewArea}.
	 */
	public int getNaturalWidth() {
		return parentBorderWidget.getScrolledArea().getNaturalWidth();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidgetViewArea}.
	 */
	public int getProposalHeight() {
		return parentBorderWidget.getScrolledArea().getProposalHeight();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetViewArea}.
	 */
	public int getProposalWidth() {
		return parentBorderWidget.getScrolledArea().getProposalWidth();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetViewArea}.
	 */
	public int getWidth() {
		
		var viewAreaWidth = parentBorderWidget.hasProposalWidth() ? getProposalWidth() : getNaturalWidth();
		
		if (parentBorderWidget.hasMinWidth()) {
			viewAreaWidth = Calculator.getMax(viewAreaWidth, getMinWidth());
		}
		
		if (parentBorderWidget.hasMaxWidth()) {
			viewAreaWidth = Calculator.getMin(viewAreaWidth, getMaxWidth());
		}
		
		return viewAreaWidth;
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetViewArea} on the {@link BorderWidget} it belongs to.
	 */
	public int getXPosition() {
		return parentBorderWidget.getBorderedArea().getXPosition();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetViewArea} on the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		return parentBorderWidget.getBorderedArea().getYPosition();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetViewArea} is under the cursor.
	 */
	public boolean isUnderCursor() {
		
		final var cursorXPosition = getCursorXPosition();
		final var cursorYPosition = getCursorYPosition();
		
		return
		cursorXPosition >= 1
		&& cursorYPosition >= 1
		&& cursorXPosition <= getWidth()
		&& cursorYPosition <= getHeight();
	}
	
	//package-visible method
	/**
	 * Paints the current {@link BorderWidgetViewArea} using the given borderWidgetLook and painter.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		final var scrolledArea = parentBorderWidget.getScrolledArea();
		
		scrolledArea.paint(
			painter.createPainter(
				-parentBorderWidget.getViewAreaXPositionOnScrolledArea(),
				-parentBorderWidget.getViewAreaYPositionOnScrolledArea(),
				scrolledArea.getWidth(),
				scrolledArea.getHeight()
			),
			borderWidgetLook
		);
	}
}
