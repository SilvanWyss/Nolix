//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 50
 * @param <BW> The type of a background widget.
 * @param <BWS> The type of the background widget structures of a background widget.
 */
public abstract class BackgroundWidget<
	BW extends BackgroundWidget<BW, BWS>,
	BWS extends BackgroundWidgetStructure<BWS>
>
extends Widget<BW, BWS> {
	
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
