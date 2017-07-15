//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 50
 * @param <BWS> - The type of the background widget structures of a background widget.
 * @param <BS> - The type of a background widget.
 */
public abstract class BackgroundWidget<
	BWS extends BackgroundWidgetStructure<BWS>,
	BS extends BackgroundWidget<BWS, BS>
>
extends Widget<BWS, BS> {

	//constructor
	/**
	 * Creates new background widget with the given structures.
	 * 
	 * @param normalStructure
	 * @param hoverStructure
	 * @param focusStructure
	 * @throws NullArgumentException if one of the given structures is null.
	 */
	public BackgroundWidget(
		final BWS normalStructure,
		final BWS hoverStructure,
		final BWS focusStructure
	) {
	
		//Calls constructor of the base class.
		super(normalStructure, hoverStructure, focusStructure);
	}
	
	//method
	/**
	 * Paints this background widget using the given widget structure and graphics.
	 * 
	 * @param widgetStructure
	 * @param graphics
	 */
	protected void paint(final BWS widgetStructure, final Graphics graphics) {
		
		//Paints the background color of this background widget if the given widget structure has a background color.
		if (widgetStructure.hasActiveBackgroundColor()) {
			widgetStructure.getActiveBackgroundColor().paintRectangle(graphics, 0, 0, getWidth(), getHeight());
		}
	}
}
