//package declaration
package ch.nolix.element.GUI;

//own import
import ch.nolix.element.painter.IPainter;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 50
 * @param <BW> The type of a {@link BackgroundWidget}.
 * @param <BWS> The type of the {@link BackgroundWidgetLook} of a {@link BackgroundWidget}.
 */
public abstract class BackgroundWidget<
	BW extends BackgroundWidget<BW, BWS>,
	BWS extends BackgroundWidgetLook<BWS>
>
extends Widget<BW, BWS> {
	
	//method
	/**
	 * Paints the current {@link BackgroundWidget}
	 * using the given background widget look and painter.
	 * 
	 * @param backgroundWidgetLook
	 * @param painter
	 */
	protected void paint(final BWS backgroundWidgetLook, final IPainter painter) {
		
		//Handles the case that the given background widget look
		//has a recursive background color.
		if (backgroundWidgetLook.hasRecursiveBackgroundColor()) {
			
			painter.setColor(
				backgroundWidgetLook.getRecursiveOrDefaultBackgroundColor()
			);
			
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
		
		//Handles the case that the given background widget look
		//has a recursive background color gradient.
		else if (backgroundWidgetLook.hasRecursiveBackgroundColorGradient()) {
			
			painter.setColorGradient(
				backgroundWidgetLook.getRecursiveOrDefaultBackgroundColorGradient()
			);
			
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
	}
}
