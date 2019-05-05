//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.core.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2019-05
 * @lines 90
 */
public final class BorderWidgetBorderedArea {
	
	//attribute
	/**
	 * The {@link BorderWidget} the current {@link BorderWidgetBorderedArea} belongs to.
	 */
	private final BorderWidget<?, ?> parentBorderWidget;
	
	//package-visible constructor
	/**
	 * Creates a new {@link BorderWidgetBorderedArea} that will belong to the given parentBorderWidget.
	 * 
	 * @param parentBorderWidget
	 * @throws NullArgumentException if the given parentBorderWidget is null.
	 */
	BorderWidgetBorderedArea(final BorderWidget<?, ?> parentBorderWidget) {
		
		//Checks if the given parentBorderWidget is not null.
		Validator.suppose(parentBorderWidget).thatIsNamed("parent border widget").isNotNull();
		
		//Sets the parentBorderWidget of the current BorderWidgetArea.
		this.parentBorderWidget = parentBorderWidget;
	}
	
	//method
	/**
	 * @return the x-position of the cursor on the current {@link BorderWidgetBorderedArea}.
	 */
	public int getCursorXPosition() {
		return (parentBorderWidget.getCursorXPosition() - getXPosition());
	}
	
	//method
	/**
	 * @return the y-position of the cursor on the current {@link BorderWidgetBorderedArea}.
	 */
	public int getCursorYPosition() {
		return (parentBorderWidget.getCursorYPosition() - getYPosition());
	}
	
	//method
	/**
	 * @return the height of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getHeight() {
		return parentBorderWidget.getContentAreaHeight();
	}
	
	//method
	/**
	 * @return the width of the current {@link BorderWidgetBorderedArea}.
	 */
	public int getWidth() {
		return parentBorderWidget.getContentAreaWidth();
	}
	
	//method
	/**
	 * @return the x-position of the current {@link BorderWidgetBorderedArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getXPosition() {
		
		final var look = parentBorderWidget.getRefCurrentLook();
		
		return look.getRecursiveOrDefaultLeftBorderThickness();
	}
	
	//method
	/**
	 * @return the y-position of the current {@link BorderWidgetBorderedArea}
	 * on the {@link BorderWidget} it belongs to.
	 */
	public int getYPosition() {
		
		final var look = parentBorderWidget.getRefCurrentLook();
		
		return look.getRecursiveOrDefaultTopBorderThickness();
	}
}
