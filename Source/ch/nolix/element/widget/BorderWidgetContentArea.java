//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.painter.IPainter;

//class
/**
 * A {@link BorderWidgetContentArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2019-05-08
 * @lines 160
 * @param <BWL>
 * The type of the {@link BorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetContentArea}.
 */
public final class BorderWidgetContentArea<BWL extends BorderWidgetLook<BWL>> {
	
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
		return (parentBorderWidget.getScrolledArea().getCursorXPosition() - getXPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetContentArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getScrolledArea().getCursorYPosition() - getYPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidgetContentArea}.
	 */
	public int getHeight() {
		return parentBorderWidget.getContentAreaHeight();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetContentArea}.
	 */
	public int getWidth() {
		return parentBorderWidget.getContentAreaWidth();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetContentArea} on the {@link BorderWidgetScrolledArea}
	 * of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnScrolledArea() {
		
		final var look = parentBorderWidget.getRefLook();
		
		//Enumerates the content position of the BoderWidget of the current BorderWidgetContentArea.
		switch (parentBorderWidget.getContentPosition()) {
			case LEFT_TOP:
			case LEFT:
			case LEFT_BOTTOM:
				return look.getRecursiveOrDefaultLeftPadding();
			case TOP:
			case CENTER:
			case BOTTOM:
				return (parentBorderWidget.getScrolledArea().getWidth() - getWidth()) / 2;
			case RIGHT_TOP:
			case RIGHT:
			case RIGHT_BOTTOM:				
				return
				parentBorderWidget.getScrolledArea().getWidth()
				- parentBorderWidget.getContentAreaWidth()
				- look.getRecursiveOrDefaultRightPadding();
		}
		
		throw new InvalidArgumentException(this);
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetContentArea} on the {@link BorderWidgetScrolledArea}
	 * of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnScrolledArea() {
		
		final var look = parentBorderWidget.getRefLook();
		
		//Enumerates the content position of the BoderWidget of the current BorderWidgetContentArea.
		switch (parentBorderWidget.getContentPosition()) {
			case LEFT_TOP:
			case TOP:
			case RIGHT_TOP:
				return look.getRecursiveOrDefaultTopPadding();
			case LEFT:
			case CENTER:
			case RIGHT:
				return
				(parentBorderWidget.getScrolledArea().getHeight() - getHeight()) / 2;
			case LEFT_BOTTOM:
			case BOTTOM:
			case RIGHT_BOTTOM:
				return
				parentBorderWidget.getScrolledArea().getHeight()
				- parentBorderWidget.getContentAreaHeight()
				- look.getRecursiveOrDefaultBottomPadding();
		}
		
		throw new InvalidArgumentException(this);
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
