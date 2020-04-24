//package declaration
package ch.nolix.element.widget;

//own imports
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2019-06
 * @lines 60
 */
public final class BorderWidgetMainArea {
	
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
		
		Validator.assertThat(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidgetMainArea}.
	 */
	public int getHeight() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		look.getRecursiveOrDefaultTopBorderThickness()		
		+ parentBorderWidget.getBorderedArea().getHeight()
		+ look.getRecursiveOrDefaultBottomBorderThickness();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetMainArea}.
	 */
	public int getWidth() {
		
		final var look = parentBorderWidget.getRefLook();
		
		return
		look.getRecursiveOrDefaultLeftBorderThickness()		
		+ parentBorderWidget.getBorderedArea().getWidth()
		+ look.getRecursiveOrDefaultRightBorderThickness();
	}
}
