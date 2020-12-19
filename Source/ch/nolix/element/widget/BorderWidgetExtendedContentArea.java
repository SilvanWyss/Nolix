//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.InvalidArgumentException;
import ch.nolix.common.rasterapi.Rectangular;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.painterapi.IPainter;

//class
/**
 * A {@link BorderWidgetExtendedContentArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2020-12-19
 * @lines 180
 * @param <BWL>
 * The type of the {@link BorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetExtendedContentArea}.
 */
public final class BorderWidgetExtendedContentArea<BWL extends BorderWidgetLook<BWL>> implements Rectangular {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetExtendedContentArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link BorderWidgetExtendedContentArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetExtendedContentArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetExtendedArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetExtendedContentArea}.
	 */
	public int getCursorXPosition() {
		return (parentBorderWidget.getScrolledArea().getCursorXPosition() - getXPositionOnScrolledArea());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetExtendedContentArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getScrolledArea().getCursorYPosition() - getYPositionOnScrolledArea());
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getContentArea().getHeight()
		+ look.getRecursiveOrDefaultTopPadding()
		+ look.getRecursiveOrDefaultBottomPadding();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		parentBorderWidget.getContentArea().getWidth()
		+ look.getRecursiveOrDefaultLeftPadding()
		+ look.getRecursiveOrDefaultRightPadding();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetExtendedContentArea} on the {@link BorderWidgetScrolledArea}
	 * of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnScrolledArea() {
		
		//Enumerates the content position of the BoderWidget of the current BorderWidgetExtendedArea.
		switch (parentBorderWidget.getContentPosition()) {
			case LEFT_TOP:
			case LEFT:
			case LEFT_BOTTOM:
				return 0;
			case TOP:
			case CENTER:
			case BOTTOM:
				return ((parentBorderWidget.getScrolledArea().getWidth() - getWidth()) / 2);
			case RIGHT_TOP:
			case RIGHT:
			case RIGHT_BOTTOM:				
				return (parentBorderWidget.getScrolledArea().getWidth() - getWidth());
		}
		
		throw new InvalidArgumentException(this);
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetExtendedContentArea} on the {@link BorderWidgetScrolledArea}
	 * of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnScrolledArea() {
		
		//Enumerates the content position of the BoderWidget of the current BorderWidgetExtendedArea.
		switch (parentBorderWidget.getContentPosition()) {
			case LEFT_TOP:
			case TOP:
			case RIGHT_TOP:
				return 0;
			case LEFT:
			case CENTER:
			case RIGHT:
				return ((parentBorderWidget.getScrolledArea().getHeight() - getHeight()) / 2);
			case LEFT_BOTTOM:
			case BOTTOM:
			case RIGHT_BOTTOM:
				return (parentBorderWidget.getScrolledArea().getHeight() - getHeight());
		}
		
		throw new InvalidArgumentException(this);
	}
	
	//method
	/**
	 * @return true if the current {@link BorderWidgetExtendedContentArea} is under the cursor.
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
	 * Paints the current {@link BorderWidgetExtendedContentArea} using the given painter and borderWidgetLook.
	 * 
	 * @param painter
	 * @param borderWidgetLook
	 */
	void paint(final IPainter painter, final BWL borderWidgetLook) {
		
		final var contentArea = parentBorderWidget.getContentArea();
		
		contentArea.paint(
			painter.createPainter(
				borderWidgetLook.getRecursiveOrDefaultLeftPadding(),
				borderWidgetLook.getRecursiveOrDefaultTopPadding(),
				contentArea.getWidth(),
				contentArea.getHeight()
			),
			borderWidgetLook
		);
	}
}
