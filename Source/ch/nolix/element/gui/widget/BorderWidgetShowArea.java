//package declaration
package ch.nolix.element.gui.widget;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.element.gui.baseapi.HoverableByCursor;
import ch.nolix.element.gui.painterapi.IPainter;

//class
/**
 * A {@link BorderWidgetShowArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2019-05-9
 * @lines 230
 * @param <BWL>
 * is the type of the {@link OldBorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetShowArea}.
 */
public final class BorderWidgetShowArea<BWL extends OldBorderWidgetLook<BWL>> implements HoverableByCursor {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetShowArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//constructor
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
		parentBorderWidget.getBorderedArea().getHeight() - parentBorderWidget.getHorizontalScrollBar().getHeight();
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidgetShowArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a maximum height.
	 */
	public int getMaxHeight() {
		return
		parentBorderWidget.getBorderedArea().getMaxHeight()	- parentBorderWidget.getHorizontalScrollBar().getHeight();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidgetShowArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a maximum width.
	 */
	public int getMaxWidth() {
		return
		parentBorderWidget.getBorderedArea().getMaxWidth()
		- parentBorderWidget.getVerticalScrollBar().getWidth();
	}
	
	//method
	/**
	 * @return the minimum height of the current {@link BorderWidgetShowArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a minimum height.
	 */
	public int getMinHeight() {
		return
		parentBorderWidget.getBorderedArea().getMinHeight() - parentBorderWidget.getHorizontalScrollBar().getHeight();
	}
	
	//method
	/**
	 * @return the minimum width of the current {@link BorderWidgetShowArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a minimum width.
	 */
	public int getMinWidth() {
		return
		parentBorderWidget.getBorderedArea().getMinWidth() - parentBorderWidget.getVerticalScrollBar().getWidth();
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
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a proposal height.
	 */
	public int getProposalHeight() {
		return
		parentBorderWidget.getBorderedArea().getProposalHeight()
		- parentBorderWidget.getHorizontalScrollBar().getHeight();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetShowArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a proposal width.
	 */
	public int getProposalWidth() {
		return
		parentBorderWidget.getBorderedArea().getProposalWidth() - parentBorderWidget.getVerticalScrollBar().getWidth();
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
		- parentBorderWidget.getHorizontalScrollBar().getHeight();
	}
	
	//method
	/**
	 * @return the target width of the current {@link BorderWidgetShowArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetShowArea} does not have a target width.
	 */
	public int getTargetWidth() {
		return
		parentBorderWidget.getBorderedArea().getTargetWidth() - parentBorderWidget.getVerticalScrollBar().getWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return
		parentBorderWidget.getBorderedArea().getWidth() - parentBorderWidget.getVerticalScrollBar().getWidth();
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
	
	//method
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
