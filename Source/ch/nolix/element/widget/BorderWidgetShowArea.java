//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseguiapi.HoverableByCursor;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * @author Silvan Wyss
 * @date 2019-05-9
 * @lines 220
 * @param <BWL>
 * The type of the {@link BorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetShowArea}.
 */
public final class BorderWidgetShowArea<BWL extends BorderWidgetLook<BWL>> implements HoverableByCursor {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetShowArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetShowArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetShowArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetShowArea.
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
		return
		parentBorderWidget.getBorderedArea().getHeight()
		- parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidgetShowArea}.
	 */
	public int getMaxHeight() {
		return
		parentBorderWidget.getBorderedArea().getMaxHeight()
		- parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidgetShowArea}.
	 */
	public int getMaxWidth() {
		return
		parentBorderWidget.getBorderedArea().getMaxWidth()
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
		return
		parentBorderWidget.getBorderedArea().getProposalHeight()
		- parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetShowArea}.
	 */
	public int getProposalWidth() {
		return
		parentBorderWidget.getBorderedArea().getProposalWidth()
		- parentBorderWidget.getVerticalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the target height of the current {@link BorderWidgetShowArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a target height.
	 */
	public int getTargetHeight() {
		return
		parentBorderWidget.getBorderedArea().getTargetHeight()
		- parentBorderWidget.getHorizontalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the target width of the current {@link BorderWidgetShowArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a target width.
	 */
	public int getTargetWidth() {
		return
		parentBorderWidget.getBorderedArea().getTargetWidth()
		- parentBorderWidget.getVerticalScrollBarThickness();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return
		parentBorderWidget.getBorderedArea().getWidth()
		- parentBorderWidget.getVerticalScrollBarThickness();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetShowArea} on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getXPosition() {
		return parentBorderWidget.getBorderedArea().getXPosition();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetShowArea} on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getYPosition() {
		return parentBorderWidget.getBorderedArea().getYPosition();
	}
	
	//visibility-reduced method
	/**
	 * Paints the current {@link BorderWidgetShowArea} using the given painter and borderWidgetLook.
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
