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
 * A {@link BorderWidgetMainArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2019-06-26
 * @lines 270
 * @param <BWL>
 * is the type of the {@link BorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetMainArea}.
 */
public final class BorderWidgetMainArea<BWL extends BorderWidgetLook<BWL>> implements HoverableByCursor {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetMainArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetMainArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetMainArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetMainArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorXPosition() {
		return parentBorderWidget.getCursorXPosition();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCursorYPosition() {
		return parentBorderWidget.getCursorXPosition();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		return parentBorderWidget.getHeight();
	}
	
	//method
	/**
	 * @return the max height of the current {@link BorderWidgetMainArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetMainArea} does not have a max height.
	 */
	public int getMaxHeight() {
		return parentBorderWidget.getMaxHeight();
	}
	
	//method
	/**
	 * @return the max width of the current {@link BorderWidgetMainArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetMainArea} does not have a max width.
	 */
	public int getMaxWidth() {
		return parentBorderWidget.getMaxWidth();
	}
	
	//method
	/**
	 * @return the min height of the current {@link BorderWidgetMainArea}.
	 */
	public int getMinHeight() {
		return parentBorderWidget.getMinHeight();
	}
	
	//method
	/**
	 * @return the min width of the current {@link BorderWidgetMainArea}.
	 */
	public int getMinWidth() {
		return parentBorderWidget.getMinWidth();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetMainArea}.
	 */
	public int getNaturalHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getBorderedArea().getNaturalHeight()
		+ look.getRecursiveOrDefaultTopBorderThickness()
		+ look.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the natural width of the current {@link BorderWidgetMainArea}.
	 */
	public int getNaturalWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getBorderedArea().getNaturalWidth()
		+ look.getRecursiveOrDefaultLeftBorderThickness()
		+ look.getRecursiveOrDefaultRightBorderThickness();
	}
	
	//method
	/**
	 * @return the proposal height of the current {@link BorderWidgetMainArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetMainArea} does not have a proposal height.
	 */
	public int getProposalHeight() {
		return parentBorderWidget.getProposalHeight();
	}
	
	//method
	/**
	 * @return the proposal width of the current {@link BorderWidgetMainArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetMainArea} does not have a proposal width.
	 */
	public int getProposalWidth() {
		return parentBorderWidget.getProposalWidth();
	}
	
	//method
	/**
	 * @return the target height of the current {@link BorderWidgetMainArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetMainArea} does not have a target height.
	 */
	public int getTargetHeight() {
		return parentBorderWidget.getTargetHeight();
	}
	
	//method
	/**
	 * @return the target width of the current {@link BorderWidgetMainArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetMainArea} does not have a target width.
	 */
	public int getTargetWidth() {
		return parentBorderWidget.getTargetWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return parentBorderWidget.getWidth();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetMainArea} on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getXPosition() {
		return 0;
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetMainArea} on the {@link BorderWidget} it belongs to.
	 */
	@Override
	public int getYPosition() {
		return 0;
	}
	
	//visibility-reduced method
	/**
	 * Paints the current {@link BorderWidgetMainArea} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		//Paints the left border of the BorderWidget of the current BorderWidgetMainArea if
		//the BorderWidget of the current BorderWidgetMainArea has a left border.
		if (borderWidgetLook.getRecursiveOrDefaultLeftBorderThickness() > 0) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultLeftBorderColor());
			
			painter.paintFilledRectangle(
				borderWidgetLook.getRecursiveOrDefaultLeftBorderThickness(),
				parentBorderWidget.getHeightWhenExpanded()
			);
		}
		
		//Paints the right border of the BorderWidget of the current BorderWidgetMainArea if
		//the BorderWidget of the current BorderWidgetMainArea has a left border.
		if (borderWidgetLook.getRecursiveOrDefaultRightBorderThickness() > 0) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultRightBorderColor());
			
			painter.paintFilledRectangle(
				getWidth() - borderWidgetLook.getRecursiveOrDefaultRightBorderThickness(),
				0,
				borderWidgetLook.getRecursiveOrDefaultLeftBorderThickness(),
				parentBorderWidget.getHeightWhenExpanded()
			);
		}
		
		//Paints the top border of the BorderWidget of the current BorderWidgetMainArea if
		//the BorderWidget of the current BorderWidgetMainArea has a left border.
		if (borderWidgetLook.getRecursiveOrDefaultTopBorderThickness() > 0) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultTopBorderColor());
			
			painter.paintFilledRectangle(
				getWidth(),
				borderWidgetLook.getRecursiveOrDefaultTopBorderThickness()
			);
		}
		
		//Paints the bottom border of the BorderWidget of the current BorderWidgetMainArea if
		//the BorderWidget of the current BorderWidgetMainArea has a left border.
		if (borderWidgetLook.getRecursiveOrDefaultBottomBorderThickness() > 0) {
			
			painter.setColor(borderWidgetLook.getRecursiveOrDefaultBottomBorderColor());
			
			painter.paintFilledRectangle(
				0,
				parentBorderWidget.getHeightWhenExpanded() - borderWidgetLook.getRecursiveOrDefaultBottomBorderThickness(),
				getWidth(),
				borderWidgetLook.getRecursiveOrDefaultBottomBorderThickness()
			);
		}
		
		//Paints the bordered area of the current border widget.
		final var borderedArea = parentBorderWidget.getBorderedArea();
		borderedArea.paint(
			painter.createPainter(borderedArea.getXPosition(), borderedArea.getYPosition()),
			borderWidgetLook
		);
	}
}
