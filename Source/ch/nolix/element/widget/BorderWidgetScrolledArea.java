//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.math.Calculator;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.painter.IPainter;

//class
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 230
 */
public final class BorderWidgetScrolledArea<BW extends BorderWidget<BW, BWL>, BWL extends BorderWidgetLook<BWL>> {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetScrolledArea} belongs to.
	 */
	private final BorderWidget<BW, BWL> parentBorderWidget;
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetScrolledArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetScrolledArea(final BorderWidget<BW, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetScrolledArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetScrolledArea}.
	 */
	public int getCursorXPosition() {
		return (parentBorderWidget.getCursorXPosition() + parentBorderWidget.getShowAreaXPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetScrolledArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getCursorYPosition() + parentBorderWidget.getShowAreaYPositionOnScrolledArea());
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
		
		final var look= parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMinHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness()
		- parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the minimum width of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getMinWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getMinWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness()
		- parentBorderWidget.getVerticalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getNaturalHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
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
		
		final var look = parentBorderWidget.getRefLook();
		
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
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getProposalHeight()
		- look.getRecursiveOrDefaultTopBorderThickness()
		- look.getRecursiveOrDefaultBottomBorderThickness()
		- parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getProposalWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getProposalWidth()
		- look.getRecursiveOrDefaultLeftBorderThickness()
		- look.getRecursiveOrDefaultRightBorderThickness()
		- parentBorderWidget.getVerticalScrollBarThickness();
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
	
	//method
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		paintBackground(painter, borderWidgetLook);
		
		final var contentArea = parentBorderWidget.getContentArea();	
		contentArea.paint(
			painter.createPainter(
				contentArea.getXPositionOnScrolledArea(),
				contentArea.getYPositionOnScrolledArea(),
				getWidth(),
				getHeight()
			),
			borderWidgetLook
		);
	}
	
	//method
	private void paintBackground(final IPainter painter, final BWL borderWidgetLook) {
		
		//Handles the case that the given borderWidgetLook has a recursive background color.
		if (borderWidgetLook.hasRecursiveBackgroundColor()) {
			
			painter.setColor(
					borderWidgetLook.getRecursiveOrDefaultBackgroundColor()
			);
			
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
		
		//Handles the case that the given borderWidgetLook has a recursive background color gradient.
		else if (borderWidgetLook.hasRecursiveBackgroundColorGradient()) {
			
			painter.setColorGradient(
				borderWidgetLook.getRecursiveOrDefaultBackgroundColorGradient()
			);
			
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
		
		//Handles the case that the given borderWidgetLook has a recursive background image.
		else if (borderWidgetLook.hasRecursiveBackgroundImage()) {
			painter.paintImage(
				borderWidgetLook.getRecursiveOrDefaultBackgroundImage(),
				getWidth(),
				getHeight()
			);
		}
	}
}
