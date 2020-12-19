//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.rasterapi.Rectangular;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * A {@link BorderWidgetContentArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2019-05-08
 * @lines 140
 * @param <BWL>
 * The type of the {@link BorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetContentArea}.
 */
public final class BorderWidgetContentArea<BWL extends BorderWidgetLook<BWL>> implements Rectangular {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetContentArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetContentArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetContentArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetContentArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetContentArea}.
	 */
	public int getCursorXPosition() {
		return 
		parentBorderWidget.getExtendedContentArea().getCursorXPosition()
		- getXPositionOnExtendedContentArea();
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetContentArea}.
	 */
	public int getCursorYPosition() {
		return
		parentBorderWidget.getExtendedContentArea().getCursorYPosition()
		- getYPositionOnExtendedContentArea();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		return parentBorderWidget.getContentAreaHeight();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		return parentBorderWidget.getContentAreaWidth();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetContentArea}
	 * on the {@link BorderWidgetExtendedContentArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnExtendedContentArea() {
		return parentBorderWidget.getRefLook().getRecursiveOrDefaultLeftPadding();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetContentArea}
	 * on the {@link BorderWidgetScrolledArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnScrolledArea() {
		return
		parentBorderWidget.getExtendedContentArea().getXPositionOnScrolledArea()
		+ parentBorderWidget.getRefLook().getRecursiveOrDefaultLeftPadding();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetContentArea}
	 * on the {@link BorderWidgetExtendedContentArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnExtendedContentArea() {
		return parentBorderWidget.getRefLook().getRecursiveOrDefaultTopPadding();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetContentArea}
	 * on the {@link BorderWidgetScrolledArea} of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnScrolledArea() {
		return
		parentBorderWidget.getExtendedContentArea().getYPositionOnScrolledArea()
		+ parentBorderWidget.getRefLook().getRecursiveOrDefaultTopPadding();
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetContentArea} is under the cursor.
	 */
	public boolean isUnderCursor() {
				
		final var cursorXPosition = getCursorXPosition();
		final var cursorYPosition = getCursorYPosition();
		
		return
		cursorXPosition >= 0
		&& cursorYPosition >= 0
		&& cursorXPosition < getWidth()
		&& cursorYPosition < getHeight();
	}
	
	//visibility-reduced method
	/**
	 * Paints the current {@link BorderWidgetContentArea} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		parentBorderWidget.paintContentArea(borderWidgetLook, painter.createPainter());
		
		parentBorderWidget.getRefPaintableWidgets().forEach(cw -> cw.paintRecursively(painter));
	}
}
