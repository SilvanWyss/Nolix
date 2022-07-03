//package declaration
package ch.nolix.system.gui.widget;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.geometryapi.griduniversalapi.Rectangular;
import ch.nolix.systemapi.guiapi.painterapi.IPainter;

//class
/**
 * A {@link BorderWidgetExtendedContentArea} does not store or mutate data.
 * 
 * @author Silvan Wyss
 * @date 2020-12-19
 * @param <BWL> is the type
 * of the {@link BorderWidgetLook} of the {@link BorderWidget} of a {@link BorderWidgetExtendedContentArea}.
 */
public final class BorderWidgetExtendedContentArea<BWL extends BorderWidgetLook<BWL>> implements Rectangular {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetExtendedContentArea} belongs to.
	 */
	private final BorderWidget<?, BWL> parentBorderWidget;
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetExtendedContentArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	BorderWidgetExtendedContentArea(final BorderWidget<?, BWL> parentBorderWidget) {
		
		//Asserts that the given parentBorderWidget is not null.
		GlobalValidator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
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
		
		final var look = parentBorderWidget.getRefActiveLook();
		
		return
		parentBorderWidget.getContentArea().getHeight()
		+ look.getTopPadding()
		+ look.getBottomPadding();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetExtendedContentArea}.
	 */
	public int getNaturalHeight() {
		
		final var look = parentBorderWidget.getRefActiveLook();
		
		return
		parentBorderWidget.getContentArea().getNaturalHeight()
		+ look.getTopPadding()
		+ look.getBottomPadding();
	}
	
	//method
	/**
	 * @return the natural height of the current {@link BorderWidgetExtendedContentArea}.
	 */
	public int getNaturalWidth() {
		
		final var look = parentBorderWidget.getRefActiveLook();
		
		return
		parentBorderWidget.getContentArea().getNaturalWidth()
		+ look.getLeftPadding()
		+ look.getRightPadding();
	}
	
	//method
	/**
	 * @return the target height of the current {@link BorderWidgetExtendedContentArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetExtendedContentArea} does not have a target height.
	 */
	public int getTargetHeight() {
		return parentBorderWidget.getScrolledArea().getTargetHeight();
	}
	
	//method
	/**
	 * @return the target width of the current {@link BorderWidgetExtendedContentArea}.
	 * @throws ArgumentDoesNotHaveAttributeException
	 * if the current {@link BorderWidgetExtendedContentArea} does not have a target width.
	 */
	public int getTargetWidth() {
		return parentBorderWidget.getScrolledArea().getTargetWidth();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getWidth() {
		
		final var look = parentBorderWidget.getRefActiveLook();
		
		return
		parentBorderWidget.getContentArea().getWidth()
		+ look.getLeftPadding()
		+ look.getRightPadding();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetExtendedContentArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getXPosition() {
		return parentBorderWidget.getScrolledArea().getXPosition() + getXPositionOnScrolledArea();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetExtendedContentArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		return parentBorderWidget.getScrolledArea().getYPosition() + getYPositionOnScrolledArea();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetExtendedContentArea} on the {@link BorderWidgetScrolledArea}
	 * of the {@link BorderWidget} it belongs to.
	 */
	public int getXPositionOnScrolledArea() {
		
		//Enumerates the content position of the BoderWidget of the current BorderWidgetExtendedArea.
		switch (parentBorderWidget.getContentPosition()) {
			case TOP_LEFT:
			case LEFT:
			case BOTTOM_LEFT:
				return 0;
			case TOP:
			case CENTER:
			case BOTTOM:
				return ((parentBorderWidget.getScrolledArea().getWidth() - getWidth()) / 2);
			case TOP_RIGHT:
			case RIGHT:
			case BOTTOM_RIGHT:				
				return (parentBorderWidget.getScrolledArea().getWidth() - getWidth());
		}
		
		throw InvalidArgumentException.forArgument(this);
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetExtendedContentArea} on the {@link BorderWidgetScrolledArea}
	 * of the {@link BorderWidget} it belongs to.
	 */
	public int getYPositionOnScrolledArea() {
		
		//Enumerates the content position of the BoderWidget of the current BorderWidgetExtendedArea.
		switch (parentBorderWidget.getContentPosition()) {
			case TOP_LEFT:
			case TOP:
			case TOP_RIGHT:
				return 0;
			case LEFT:
			case CENTER:
			case RIGHT:
				return ((parentBorderWidget.getScrolledArea().getHeight() - getHeight()) / 2);
			case BOTTOM_LEFT:
			case BOTTOM:
			case BOTTOM_RIGHT:
				return (parentBorderWidget.getScrolledArea().getHeight() - getHeight());
		}
		
		throw InvalidArgumentException.forArgument(this);
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
	
	//method
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
				contentArea.getXPositionOnExtendedContentArea(),
				contentArea.getYPositionOnExtendedContentArea(),
				contentArea.getWidth(),
				contentArea.getHeight()
			),
			borderWidgetLook
		);
	}
}
