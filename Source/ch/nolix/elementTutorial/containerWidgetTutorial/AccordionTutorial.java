package ch.nolix.elementTutorial.containerWidgetTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.containerWidget.Accordion;
import ch.nolix.element.containerWidget.AccordionTab;
import ch.nolix.element.frameVisualizer.FrameVisualizer;
import ch.nolix.element.widgets.Area;

/**
 * The {@link AccordionTutorial} is a tutorial for {@link Accordion}s.
 * Of the {@link AccordionTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 */
public final class AccordionTutorial {
	
	/**
	 * Creates a {@link FrameVisualizer} with a {@link Accordion}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		//Creates a Frame.
		final var frame = new Frame("Accordion Container Tutorial");
		
		//Creates an Accordion.
		final var accordion =
		new Accordion(
			new AccordionTab("A", new Area(500, 200, Color.LIGHT_GREEN)),
			new AccordionTab("B", new Area(500, 200, Color.GREEN)),
			new AccordionTab("C", new Area(500, 200, Color.LIGHT_GREEN)),
			new AccordionTab("D", new Area(500, 200, Color.GREEN))
		);
		
		//Configures the look of the accordion.
		accordion.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.ALICE_BLUE)
		);
		
		//Adds the accordion to the frame.
		frame.addLayerOnTop(accordion);
	}
	
	/**
	 * Avoids that an instance of the {@link AccordionTutorial} can be created.
	 */
	private AccordionTutorial() {}
}
