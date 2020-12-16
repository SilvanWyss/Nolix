//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.math.Calculator;
import ch.nolix.common.rasterapi.TopLeftPositionedRecangular;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 260
 */
public final class BorderWidgetShowArea<BW extends BorderWidget<BW, BWL>, BWL extends BorderWidgetLook<BWL>>
implements TopLeftPositionedRecangular {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetShowArea} belongs to.
	 */
	private final BorderWidget<BW, BWL> parentBorderWidget;
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetShowArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetShowArea(final BorderWidget<BW, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetShowArea}.
	 */
	public int getCursorXPosition() {
		return (parentBorderWidget.getCursorXPosition() - getXPosition());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetShowArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getCursorYPosition() - getYPosition());
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidgetShowArea}.
	 */
	public int getHeight() {
		
		var height = parentBorderWidget.hasProposalHeight() ? getProposalHeight() : getNaturalHeight();
		
		if (parentBorderWidget.hasMinHeight()) {
			height = Calculator.getMax(height, getMinHeight());
		}
		
		if (parentBorderWidget.hasMaxHeight()) {
			height = Calculator.getMin(height, getMaxHeight());
		}
		
		return height;
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidgetShowArea}.
	 */
	public int getMaxHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMaxHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness()
		- parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidgetShowArea}.
	 */
	public int getMaxWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMaxWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness()
		- parentBorderWidget.getVerticalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the min height of the current {@link BorderWidgetShowArea}.
	 */
	public int getMinHeight() {
		return
		parentBorderWidget.getBorderedArea().getMinHeight()
		- parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the min width of the current {@link BorderWidgetShowArea}.
	 */
	public int getMinWidth() {
		return parentBorderWidget.getBorderedArea().getMinWidth() - parentBorderWidget.getVerticalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetShowArea}.
	 */
	public int getNaturalHeight() {
		return parentBorderWidget.getScrolledArea().getNaturalHeight();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidgetShowArea}.
	 */
	public int getNaturalWidth() {
		return parentBorderWidget.getScrolledArea().getNaturalWidth();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidgetShowArea}.
	 */
	public int getProposalHeight() {
		return parentBorderWidget.getScrolledArea().getProposalHeight();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetShowArea}.
	 */
	public int getProposalWidth() {
		return parentBorderWidget.getScrolledArea().getProposalWidth();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetShowArea}.
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
	 * @return the x-position of the current {@link BorderWidgetShowArea} on the {@link BorderWidget} it belongs to.
	 */
	public int getXPosition() {
		return parentBorderWidget.getBorderedArea().getXPosition();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetShowArea} on the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		return parentBorderWidget.getBorderedArea().getYPosition();
	}
	
	//method
	/**
	 * @return true if the the current {@link BorderWidgetShowArea} has a max height.
	 */
	public boolean hasMaxHeight() {
		return parentBorderWidget.hasMaxHeight();
	}
	
	//method
	/**
	 * @return true if the the current {@link BorderWidgetShowArea} has a max width.
	 */
	public boolean hasMaxWidth() {
		return parentBorderWidget.hasMaxWidth();
	}
	
	//method
	/**
	 * @return true if the the current {@link BorderWidgetShowArea} has a min height.
	 */
	public boolean hasMinHeight() {
		return parentBorderWidget.hasMinHeight();
	}
	
	//method
	/**
	 * @return true if the the current {@link BorderWidgetShowArea} has a min width.
	 */
	public boolean hasMinWidth() {
		return parentBorderWidget.hasMinWidth();
	}
	
	//method
	public boolean hasProposalHeight() {
		return parentBorderWidget.hasProposalHeight();
	}
	
	//method
	public boolean hasProposalWidth() {
		return parentBorderWidget.hasProposalWidth();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetShowArea} is under the cursor.
	 */
	public boolean isUnderCursor() {
		return containsPointRelatively(getCursorXPosition(), getCursorYPosition());
	}
	
	//method
	/**
	 * Paints the current {@link BorderWidgetShowArea} using the given borderWidgetLook and painter.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		final var scrolledArea = parentBorderWidget.getScrolledArea();
		
		scrolledArea.paint(
			painter.createPainter(
				-parentBorderWidget.getShowAreaXPositionOnScrolledArea(),
				-parentBorderWidget.getShowAreaYPositionOnScrolledArea(),
				scrolledArea.getWidth(),
				scrolledArea.getHeight()
			),
			borderWidgetLook
		);
	}
}
