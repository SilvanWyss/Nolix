//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.rasterapi.Rectangular;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2019-06-26
 * @lines 50
 */
public final class BorderWidgetMainArea implements Rectangular {
	
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
		
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent BorderWidget").isNotNull();
		
		this.parentBorderWidget = parentBorderWidget;
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
}
