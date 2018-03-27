//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.element.painter.IPainter;

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
	 * Paints this background widget using the given widget structure and painter.
	 * 
	 * @param widgetStructure
	 * @param painter
	 */
	protected void paint(final BWS widgetStructure, final IPainter painter) {
		
		//Handles the case that the given widget structure has a background color.
		if (widgetStructure.hasRecursiveBackgroundColor()) {
			painter.setColor(widgetStructure.getActiveBackgroundColor());
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
		
		//Handles the case that the given widget structure has a background color gradient.
		if (widgetStructure.hasRecursiveBackgroundColorGradient()) {
			
			painter.setColorGradient(widgetStructure.getActiveBackgroundColorGradient());
			
			painter.paintFilledRectangle(
				getWidth(),
				getHeight()
			);
		}
	}
}
