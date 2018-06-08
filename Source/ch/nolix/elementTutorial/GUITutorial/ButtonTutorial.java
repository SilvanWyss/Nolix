//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link ButtonTutorial} provides a tutorial for the {@link Button}.
 * Of the {@link ButtonTutorial} no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2018-05
 * @lines 70
 */
public final class ButtonTutorial {
	
	//static attribute
	private static Frame frame;

	//main method
	/**
	 * Creates a {@link Frame} with a {@link Button}.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a button.
		final var button =
		new Button("Quit")
		.setMinWidth(200)
		.setCursorIcon(CursorIcon.Hand)
		.setLeftMouseButtonPressCommand(() -> quit());
		
		//Configures the look of the button.
		button
		.getRefBaseLook()
		.setBorderThicknesses(5)
		.setBorderColors(Color.DARK_BLUE)
		.setBackgroundColor(Color.ALICE_BLUE)
		.setPaddings(5);
		
		//Creates a frame with the button.
		frame =
		new Frame()
		.setTitle("Button Tutorial")
		.setRootWidget(button);
	}
	
	//static method
	private static void quit() {
		frame.close();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
