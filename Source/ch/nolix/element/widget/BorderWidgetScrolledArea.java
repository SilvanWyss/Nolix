//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.math.Calculator;
import ch.nolix.common.rasterapi.Rectangular;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * A {@link BorderWidgetScrolledArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2019-05-08
 * @lines 250
 * @param <BWL>
 * The type of the {@link BorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetScrolledArea}.
 */
public final class BorderWidgetScrolledArea<BWL extends BorderWidgetLook<BWL>> implements Rectangular {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetScrolledArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetScrolledArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetScrolledArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
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
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		
		var scrolledAreaHeight = getNaturalHeight();
		
		//Handles the case that the BorderWidget of the current BorderWidgetScrolledArea has a proposal height.
		if (parentBorderWidget.hasProposalHeight()) {
			scrolledAreaHeight = Calculator.getMax(getProposalHeight(), scrolledAreaHeight);
		}
		
		//Handles the case that the BorderWidget of the current BorderWidgetScrolledArea has a mininum height.
		if (parentBorderWidget.hasMinHeight()) {
			scrolledAreaHeight = Calculator.getMax(getMinHeight(), scrolledAreaHeight);
		}
		
		return scrolledAreaHeight;
	}
	
	//method
	/**
	 * @return the minimal height of the current {@link BorderWidgetScrolledArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetScrolledArea} does not have a minimal height.
	 */
	public int getMinHeight() {
		return parentBorderWidget.getShowArea().getMinHeight();
	}
	
	//method
	/**
	 * @return the minimal width of the current {@link BorderWidgetScrolledArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetScrolledArea} does not have a minimal width.
	 */
	public int getMinWidth() {
		return parentBorderWidget.getShowArea().getMinWidth();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getNaturalHeight() {
		return parentBorderWidget.getExtendedContentArea().getNaturalHeight();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidgetScrolledArea}.
	 */
	public int getNaturalWidth() {
		return parentBorderWidget.getExtendedContentArea().getNaturalWidth();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidgetScrolledArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetScrolledArea} does not have a proposal height.
	 */
	public int getProposalHeight() {
		return parentBorderWidget.getShowArea().getProposalHeight();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetScrolledArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetScrolledArea} does not have a proposal width.
	 */
	public int getProposalWidth() {
		return parentBorderWidget.getShowArea().getProposalWidth();
	}
	
	//method
	/**
	 * @return the target height of the current {@link BorderWidgetScrolledArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetScrolledArea} does not have a target height.
	 */
	public int getTargetHeight() {
		return parentBorderWidget.getShowArea().getTargetHeight();
	}
	
	//method
	/**
	 * @return the target width of the current {@link BorderWidgetScrolledArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetScrolledArea} does not have a target width.
	 */
	public int getTargetWidth() {
		return parentBorderWidget.getShowArea().getTargetWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		
		var scrolledAreaWidth = getNaturalWidth();
		
		//Handles the case that the BorderWidget of the current BorderWidgetScrolledArea has a proposal width.
		if (parentBorderWidget.hasProposalWidth()) {
			scrolledAreaWidth = Calculator.getMax(getProposalWidth(), scrolledAreaWidth);
		}
		
		//Handles the case that the BorderWidget of the current BorderWidgetScrolledArea has a mininum width.
		if (parentBorderWidget.hasMinWidth()) {
			scrolledAreaWidth = Calculator.getMax(getMinWidth(), scrolledAreaWidth);
		}
		
		return scrolledAreaWidth;
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetScrolledArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getXPosition() {
		return
		parentBorderWidget.getShowArea().getXPosition()
		- parentBorderWidget.getShowAreaXPositionOnScrolledArea();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetScrolledArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		return
		parentBorderWidget.getShowArea().getYPosition()
		- parentBorderWidget.getShowAreaYPositionOnScrolledArea();
	}
	
	//visibility-reduced method
	/**
	 * Paints the current {@link BorderWidgetScrolledArea} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		paintBackground(painter, borderWidgetLook);
		
		final var extendedContentArea = parentBorderWidget.getExtendedContentArea();	
		extendedContentArea.paint(
			painter.createPainter(
				extendedContentArea.getXPositionOnScrolledArea(),
				extendedContentArea.getYPositionOnScrolledArea(),
				extendedContentArea.getWidth(),
				extendedContentArea.getHeight()
			),
			borderWidgetLook
		);
	}
	
	//method
	/**
	 * Paints the background of the current {@link BorderWidgetScrolledArea} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	private void paintBackground(final IPainter painter, final BWL borderWidgetLook) {
		
		//Handles the case that the given borderWidgetLook has a recursive background color.
		if (borderWidgetLook.hasRecursiveBackgroundColor()) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultBackgroundColor());
			
			painter.paintFilledRectangle(getWidth(), getHeight());
			
		//Handles the case that the given borderWidgetLook has a recursive background color gradient.
		} else if (borderWidgetLook.hasRecursiveBackgroundColorGradient()) {
			
			painter.setColorGradient(borderWidgetLook.getRecursiveOrDefaultBackgroundColorGradient());
			
			painter.paintFilledRectangle(getWidth(), getHeight());
			
		//Handles the case that the given borderWidgetLook has a recursive background image.
		} else if (borderWidgetLook.hasRecursiveBackgroundImage()) {
			painter.paintImage(borderWidgetLook.getRecursiveOrDefaultBackgroundImage(),	getWidth(),	getHeight());
		}
	}
}
