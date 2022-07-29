//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.systemapi.guiapi.mainapi.HoverableTopLeftPositionedRectangular;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
/**
 * A {@link BorderWidgetBorderedArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2019-05-05
 * @param <BWL>
 * is the type of the {@link BorderWidgetStyle} of the {@link BorderWidget} of a {@link BorderWidgetBorderedArea}.
 */
public final class BorderWidgetBorderedArea<BWL extends BorderWidgetStyle<BWL>>
implements HoverableTopLeftPositionedRectangular {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetBorderedArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetBorderedArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetBorderedArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		GlobalValidator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetArea.
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
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getHeight()
		- look.getTopBorderThickness()
		- look.getBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidgetBorderedArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetBorderedArea} does not have a maximum height.
	 */
	public int getMaxHeight() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getMaxHeight()
		- look.getTopBorderThickness()
		- look.getBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidgetBorderedArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetBorderedArea} does not have a maximum width.
	 */
	public int getMaxWidth() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getMaxWidth()
		- look.getLeftBorderThickness()
		- look.getRightBorderThickness();
	}
	
	//method
	/**
	 * @return the mininum height of the current {@link BorderWidgetBorderedArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetBorderedArea} does not have a minimum height.
	 */
	public int getMinHeight() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getMinHeight()
		- look.getTopBorderThickness()
		- look.getBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the mininum width of the current {@link BorderWidgetBorderedArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetBorderedArea} does not have a minimum width.
	 */
	public int getMinWidth() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getMinWidth()
		- look.getLeftBorderThickness()
		- look.getRightBorderThickness();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getNaturalHeight() {
		return
		parentBorderWidget.getShowArea().getNaturalHeight()
		+ parentBorderWidget.getHorizontalScrollBar().getHeight();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getNaturalWidth() {
		return
		parentBorderWidget.getShowArea().getNaturalWidth()
		+ parentBorderWidget.getVerticalScrollBar().getWidth();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidgetBorderedArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetBorderedArea} does not have a proposal height.
	 */
	public int getProposalHeight() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getProposalHeight()
		- look.getTopBorderThickness()
		- look.getBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetBorderedArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetBorderedArea} does not have a proposal width.
	 */
	public int getProposalWidth() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getProposalWidth()
		- look.getLeftBorderThickness()
		- look.getRightBorderThickness();
	}
	
	//method
	/**
	 * @return the target height of the current {@link BorderWidgetBorderedArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetBorderedArea} does not have a target height.
	 */
	public int getTargetHeight() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getTargetHeight()
		- look.getTopBorderThickness()
		- look.getBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the target width of the current {@link BorderWidgetBorderedArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetBorderedArea} does not have a target width.
	 */
	public int getTargetWidth() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getTargetWidth()
		- look.getLeftBorderThickness()
		- look.getRightBorderThickness();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return
		parentBorderWidget.getMainArea().getWidth()
		- look.getLeftBorderThickness()
		- look.getRightBorderThickness();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetBorderedArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getXPosition() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return parentBorderWidget.getMainArea().getXPosition() + look.getLeftBorderThickness();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetBorderedArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		
		final var look = parentBorderWidget.getRefStyle();
		
		return parentBorderWidget.getMainArea().getYPosition() +  look.getTopBorderThickness();
	}
	
	//method
	/**
	 * Paints the current {@link BorderWidgetBorderedArea} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		paintScrollBars(painter, borderWidgetLook);			
		paintShowArea(painter, borderWidgetLook);
	}
	
	//method
	private void paintInterScrollArea(IPainter painter, BWL borderWidgetLook) {
		
		final var interScrollBarArea = parentBorderWidget.getInterScrollBarArea();
		
		interScrollBarArea.paint(
			painter.createPainter(
				interScrollBarArea.getXPositionOnBorderedArea(),
				interScrollBarArea.getYPositionOnBorderedArea()
			),
			borderWidgetLook
		);
	}

	//method
	private void paintHorizontalScrollBar(final IPainter painter) {
		
		final var horizontalScrollBar = parentBorderWidget.getHorizontalScrollBar();
		
		horizontalScrollBar.paint(
			painter.createPainter(
				horizontalScrollBar.getXPositionOnBorderedArea(),
				horizontalScrollBar.getYPositionOnBorderedArea()
			)
		);
	}
	
	//method
	private void paintScrollBars(final IPainter painter, final BWL borderWidgetLook) {
		paintVerticalScrollBar(painter);
		paintHorizontalScrollBar(painter);
		paintInterScrollArea(painter, borderWidgetLook);
	}
		
	//method
	private void paintShowArea(final IPainter painter, final BWL borderWidgetLook) {
		parentBorderWidget.getShowArea().paint(
			painter.createPainter(
				0,
				0,
				parentBorderWidget.getShowArea().getWidth(),	
				parentBorderWidget.getShowArea().getHeight()
			),
			borderWidgetLook
		);
	}
	
	//method
	private void paintVerticalScrollBar(final IPainter painter) {
		
		final var verticalScrollBar = parentBorderWidget.getVerticalScrollBar();
		
		verticalScrollBar.paint(
			painter.createPainter(
				verticalScrollBar.getXPositionOnBorderedArea(),
				verticalScrollBar.getYPositionOnBorderedArea()
			)
		);
	}
}
