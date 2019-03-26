//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.ContentPosition;
import ch.nolix.element.GUI.FloatContainer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link FloatContainerTutorial} provides a tutorial for a {@link FloatContainer}.
 * Of the {@link FloatContainerTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 70
 */
public final class FloatContainerTutorial {
	
	//main method
	/**
	 * Creates a {@link Frame} with a {@link FloatContainerTutorial}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a float container.
		final var floatContainer =
		new FloatContainer(
			new Label("A"),
			new Label("B"),
			new Label("C"),
			new Label("D"),
			new Label("E"),
			new Label("F")
		);
		
		//Configures the look of the float container.
		floatContainer
		.setProposalWidth(1000)
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.GREY)
			.setPaddings(20)
			.setProposeContentWidth(2100)
		);
		
		//Configures the look of the widgets of the float container.
		for (final var w : floatContainer.getChildWidgets()) {
			w
			.as(Label.class)
			.setProposalSize(500, 200)
			.setContentPosition(ContentPosition.Center)
			.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.LIGHT_GREEN).setTextSize(50))
			.applyOnHoverLook(bl -> bl.setBackgroundColor(Color.GREEN))
			.applyOnFocusLook(bl -> bl.setBackgroundColor(Color.GREEN));
		}
		
		//Creates a frame with the float container.
		new Frame("Float Container Tutorial", floatContainer);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link FloatContainerTutorial} can be created.
	 */
	private FloatContainerTutorial() {}
}
