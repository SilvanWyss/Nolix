//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.core.controllerInterfaces.IController;
import ch.nolix.core.specification.Statement;
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
		.setLeftMouseButtonPressCommand("Quit");
		
		//Configures the look of the button.
		button
		.getRefBaseLook()
		.setBorderThicknesses(5)
		.setBorderColors(Color.DARK_BLUE)
		.setBackgroundColor(Color.ALICE_BLUE)
		.setPaddings(5);
		
		//Creates a frame with the button.
		final var frame = new Frame()
		.setTitle("Button Tutorial")
		.setRootWidget(button);
		
		frame.setController(
			new IController() {
			
				//method
				public void run(final Statement command) {
					if (command.toString().equals("Quit")) {
						frame.close();
					}
				}
			}
		);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
