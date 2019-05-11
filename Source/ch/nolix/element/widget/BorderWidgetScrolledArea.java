//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.core.math.Calculator;
import ch.nolix.core.validator.Validator;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 200
 */
public final class BorderWidgetScrolledArea<BW extends BorderWidget<BW, BWL>, BWL extends BorderWidgetLook<BWL>> {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetScrolledArea} belongs to.
	 */
	private final BorderWidget<BW, BWL> parentBorderWidget;
	
	//package-visible constructor
	/**
	 * Creates a new {@link BorderWidgetScrolledArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws NullArgumentException if the given parentBorderWidget is null.
	 */
	BorderWidgetScrolledArea(final BorderWidget<BW, BWL> parentBorderWidget) {
		
		//Checks if the given parentBorderWidget is not null.
		Validator.suppose(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetScrolledArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetScrolledArea}.
	 */
	public int getCursorXPosition() {
		//TODO
		return 0;
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetScrolledArea}.
	 */
	public int getCursorYPosition() {
		//TODO
		return 0;
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getHeight() {
		
		var scrolledAreaHeight = getNaturalHeight();
		
		if (parentBorderWidget.hasProposalHeight()) {
			scrolledAreaHeight = Calculator.getMax(scrolledAreaHeight, getProposalHeight());
		}
		
		//Handles the case that the current border widget has a min height.
		if (parentBorderWidget.hasMinHeight()) {
			scrolledAreaHeight = Calculator.getMax(
				scrolledAreaHeight,
				getMinHeight()
			);
		}
		
		return scrolledAreaHeight;
	}
	
	//method
	/**
	 * @return the minimum height of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getMinHeight() {
		
		final var look= parentBorderWidget.getRefCurrentLook();
		
		return
		parentBorderWidget.getMinHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness()
		- parentBorderWidget.getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the minimum width of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getMinWidth() {
		
		final var look = parentBorderWidget.getRefCurrentLook();
		
		return
		parentBorderWidget.getMinWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness()
		- parentBorderWidget.getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getNaturalHeight() {
		
		final var look = parentBorderWidget.getRefCurrentLook();
		
		return
		look.getRecursiveOrDefaultTopPadding()
		+ parentBorderWidget.getContentArea().getHeight()
		+ look.getRecursiveOrDefaultBottomPadding();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getNaturalWidth() {
		
		final var look = parentBorderWidget.getRefCurrentLook();
		
		return
		look.getRecursiveOrDefaultLeftPadding()
		+ parentBorderWidget.getContentArea().getWidth()
		+ look.getRecursiveOrDefaultRightPadding();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getProposalHeight() {
		
		final var currentLook = parentBorderWidget.getRefCurrentLook();
		
		return
		getProposalHeight()
		- currentLook.getRecursiveOrDefaultTopBorderThickness()
		- currentLook.getRecursiveOrDefaultBottomBorderThickness()
		- parentBorderWidget.getHorizontalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getProposalWidth() {
		
		final var look = parentBorderWidget.getRefCurrentLook();
		
		return
		getProposalWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness()
		- parentBorderWidget.getVerticalScrollbarThickness();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getWidth() {
		
		var scrolledAreaWidth = getNaturalWidth();
		
		if (parentBorderWidget.hasProposalWidth()) {
			scrolledAreaWidth = Calculator.getMax(scrolledAreaWidth, getProposalWidth());
		}
		
		//Handles the case that the current border widget has a min width.
		if (parentBorderWidget.hasMinWidth()) {
			scrolledAreaWidth = Calculator.getMax(
				scrolledAreaWidth,
				getMinWidth()
			);
		}
		
		return scrolledAreaWidth;
	}
	
	//package-visible method
	void paint(final BWL borderWidgetLook, final IPainter painter) {
		
		final var contentArea = parentBorderWidget.getContentArea();
		
		contentArea.paint(
			borderWidgetLook,
			painter.createPainter(
				contentArea.getXPositionOnScrolledArea(),
				contentArea.getYPositionOnScrolledArea(),
				getWidth(),
				getHeight()
			)
		);
	}
}
