//package declaration
package ch.nolix.element.widget;

//own import
import ch.nolix.element.painter.IPainter;

//abstract class
/**
 * @author Silvan Wyss
 * @month 2017-03
 * @lines 60
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
	@Override
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
		if (backgroundWidgetLook.hasRecursiveBackgroundColorGradient()) {
			
			painter.setColorGradient(
				backgroundWidgetLook.getRecursiveOrDefaultBackgroundColorGradient()
			);
			
			painter.paintFilledRectangle(getWidth(), getHeight());
		}
		
		//Handles the case that the given background widget look
		//has a recursive background image.
		if (backgroundWidgetLook.hasRecursiveBackgroundImage()) {
			painter.paintImage(
				backgroundWidgetLook.getRecursiveOrDefaultBackgroundImage(),
				getWidth(),
				getHeight()
			);
		}
	}
}
