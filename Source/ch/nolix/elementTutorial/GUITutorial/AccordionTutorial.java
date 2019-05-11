//package declaration
package ch.nolix.elementTutorial.GUITutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Accordion;
import ch.nolix.element.widget.AccordionTab;
import ch.nolix.element.widget.Area;

//class
/**
 * The {@link AccordionTutorial} provides a tutorial for a {@link Accordion}.
 * Of the {@link AccordionTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-08
 * @lines 80
 */
public final class AccordionTutorial {
	
	//main method
	/**
	 * Creates a {@link Frame} with a {@link Accordion}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates an accordion.
		final var accordion =
		new Accordion(
			new AccordionTab(
				"Tab1",
				new Area()
				.setBackgroundColor(Color.DARK_BLUE)
				.setWidth(500)
				.setHeight(200)
			),
			new AccordionTab(
				"Tab2",
				new Area()
				.setBackgroundColor(Color.BLUE)
				.setWidth(200)
				.setHeight(100)
			),
			new AccordionTab(
				"Tab3",
				new Area()
				.setBackgroundColor(Color.DARK_BLUE)
				.setWidth(500)
				.setHeight(200)
			),
			new AccordionTab(
				"Tab4",
				new Area()
				.setBackgroundColor(Color.BLUE)
				.setWidth(200)
				.setHeight(100)
			)
		);
		
		//Configures the look of the accordion.
		accordion.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.ALICE_BLUE)
		);
		
		//Creates a frame with the accordion.
		new Frame()
		.setTitle("Accordion Container Tutorial")
		.setRootWidget(accordion);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link AccordionTutorial} can be created.
	 */
	private AccordionTutorial() {}
}
