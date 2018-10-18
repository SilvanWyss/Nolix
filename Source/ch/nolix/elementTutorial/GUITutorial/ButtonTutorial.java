//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link ButtonTutorial} provides a tutorial for a {@link Button}.
 * Of the {@link ButtonTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 60
 */
public final class ButtonTutorial {
	
	//main method
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a frame.
		final var frame = new Frame("Button Tutorial");
		
		//Creates a button.
		final var button =
		new Button("Quit")
		.setMinWidth(200)
		.setCustomCursorIcon(CursorIcon.Hand)
		.setLeftMouseButtonReleaseCommand(() -> frame.close());
		
		//Configures the look of the button.
		button
		.applyOnBaseLook(
			bl ->
			bl
			.setBorderThicknesses(5)
			.setBorderColors(Color.DARK_BLUE)
			.setBackgroundColor(Color.ALICE_BLUE)
			.setPaddings(5)
			.setTextSize(50)
		)
		.applyOnHoverLook(hl -> hl.setBackgroundColor(Color.GREY))
		.applyOnFocusLook(fl -> fl.setBackgroundColor(Color.GREY));
		
		//Adds the button to the frame.
		frame.setRootWidget(button);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
