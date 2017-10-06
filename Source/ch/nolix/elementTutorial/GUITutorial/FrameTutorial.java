//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.core.controllerInterfaces.ILevel1Controller;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//class
/**
 * This class provides a tutorial for the frame class.
 * Of this class no instance can be created.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 90
 */
public final class FrameTutorial {

	//main method
	/**
	 * 1. Creates a frame.
	 *    A frame is a 2D GUI, that is a common window.
	 * 2. Sets a root widget to the frame.
	 *    A widget is a GUI element. Certain types of widgets can contain other widgets.
	 * 3. Sets a controller to the frame.
	 *    A controller is an object that executes the GUI commands.
	 * 4. Sets a configuration to the frame.
	 *    A configuration can configure a configurable element like a frame.
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new Frame()
		.setTitle("Frame Tutorial")
		.setRootWidget(
			new VerticalStack()
			.addWidget(
				new Button()
				.setText("~ Quit ~")
				.setLeftMouseButtonPressCommand("Quit")
			)
		)
		.setController(
				
			//anonymous class
			new ILevel1Controller() {
			
				//method
				public void run(Statement command) {
					if (command.toString().equals("Quit")) {
						System.exit(0);
					}
				}
			}
		)
		.setConfiguration(
			new StandardConfiguration()
			.addAttachingAttribute("ContentPosition(Center)")
			.addConfiguration(
				new DeepConfiguration()
				.setSelectorType(VerticalStack.TYPE_NAME)
				.addAttachingAttribute(
					"ContentPosition(Center)",
					"ElementMargin(50)"
				),
				new DeepConfiguration()
				.setSelectorType(Button.TYPE_NAME)
				.addAttachingAttribute(
					"CursorIcon(Hand)",
					"NormalPadding(10)",
					"NormalTextSize(50)",
					"NormalBackgroundColor(LightGrey)",
					"HoverBackgroundColor(Grey)"	
				)
			)
		);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private FrameTutorial() {}
}
