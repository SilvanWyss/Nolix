//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.baseguiapi.HoverableByCursor;

//class
/**
 * @author Silvan Wyss
 * @date 2019-06-26
 * @lines 90
 */
public final class BorderWidgetMainArea implements HoverableByCursor {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetMainArea} belongs to.
	 */
	private final BorderWidget<?, ?> parentBorderWidget;
	
	//constructor
	/**
	 * Creates a new {@link BorderWidgetMainArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws ArgumentIsNullException if the given parentBorderWidget is null.
	 */
	public BorderWidgetMainArea(final BorderWidget<?, ?> parentBorderWidget) {
		
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
}
