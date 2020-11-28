package ch.nolix.elementTutorial.containerWidgetTutorial;

import ch.nolix.element.color.Color;
import ch.nolix.element.containerwidget.Accordion;
import ch.nolix.element.containerwidget.AccordionTab;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.Area;

/**
 * The {@link AccordionTutorial} is a tutorial for {@link Accordion}s.
 * Of the {@link AccordionTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
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
		final var frame =
		new Frame()
		.setTitle("Accordion Container Tutorial");
		
		//Creates an Accordion.
		final var accordion =
		new Accordion()
		.addTab(
			new AccordionTab("A", new Area().setSize(500,  200).setBackgroundColor(Color.BLUE)),
			new AccordionTab("B", new Area().setSize(500,  200).setBackgroundColor(Color.GREEN)),
			new AccordionTab("C", new Area().setSize(500,  200).setBackgroundColor(Color.BLUE)),
			new AccordionTab("D", new Area().setSize(500,  200).setBackgroundColor(Color.GREEN))
		);
		
		//Configures the look of the Accordion.
		accordion.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBackgroundColor(Color.LAVENDER)
		);
		
		//Adds the Accordion to the Frame.
		frame.addLayerOnTop(accordion);
	}
	
	/**
	 * Avoids that an instance of the {@link AccordionTutorial} can be created.
	 */
	private AccordionTutorial() {}
}
