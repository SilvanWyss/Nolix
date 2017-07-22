//package declaration
package ch.nolix.element.GUI;

//Java import
import java.awt.Graphics;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 30
 * @param <BW> The type of a background widget.
 * @param <BWS> The type of the widget structures of a background widget.
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
		
		//Handles the option that the given widget structure has a background color.
		if (widgetStructure.hasActiveBackgroundColor()) {
			graphics.setColor(widgetStructure.getActiveBackgroundColor().getJavaColor());
			graphics.fillRect(0, 0, getWidth(), getHeight());
		}
	}
}
