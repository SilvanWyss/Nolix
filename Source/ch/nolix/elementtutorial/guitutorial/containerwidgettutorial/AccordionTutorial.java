package ch.nolix.elementtutorial.guitutorial.containerwidgettutorial;

import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.Accordion;
import ch.nolix.element.gui.widget.Area;

/**
 * The {@link AccordionTutorial} is a tutorial for {@link Accordion}s.
 * Of the {@link AccordionTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2018-08-13
 * @lines 50
 */
public final class AccordionTutorial {
	
	/**
	 * Creates a {@link Frame} with a {@link Accordion}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
				
		//Creates a Frame.
		final var frame = new Frame().setTitle("Accordion Tutorial");
		
		//Creates an Accordion.
		final var accordion =
		new Accordion()
		.addTab("A", new Area().setSize(500,  200).setBackgroundColor(Color.PINK))
		.addTab("B", new Area().setSize(450,  200).setBackgroundColor(Color.ORANGE))
		.addTab("C", new Area().setSize(400,  200).setBackgroundColor(Color.PINK))
		.addTab("D", new Area().setSize(350,  200).setBackgroundColor(Color.ORANGE));
		
		//Configures the look of the Accordion.
		accordion
		.getRefLook()
		.setBorderThicknessForState(WidgetLookState.BASE, 5)
		.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER);
		
		//Adds the Accordion to the Frame.
		frame.addLayerOnTop(accordion);
	}
	
	/**
	 * Prevents that an instance of the {@link AccordionTutorial} can be created.
	 */
	private AccordionTutorial() {}
}
