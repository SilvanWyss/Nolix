/*
 * file:	FrameTutorial.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	90
 */

//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.core.controllerInterfaces.ILevel1Controller;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.Console;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.TextBox;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.StandardConfiguration;

//package-visible class
/**
 * This class provides a tutorial for the frame class.
 */
final class FrameTutorial {

	//main method
	/**
	 * Creates new frame, sets its root rectangle, controller and configuration.
	 * A frame is a 2D GUI, that is a common window.
	 * A rectangle is a GUI element. Certain types of rectangles can contain other rectangles themself.
	 * A controller is an instance the GUI commands are leaded to and executed.
	 * A configuration configures a configurable element like a frame.
	 */
	public static void main(String[] args) {
		new Frame()
		.setTitle("Tutorial")
		.setRootWidget(
			new VerticalStack()
			.addWidget(
				new Label()
				.setText("Tutorial"),
				new Button()
				.setText("~ Quit ~")
				.setLeftMouseButtonPressCommand("Quit"),
				new TextBox().setText("test"),
				new Console().writeTextLine("test", "test2")
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
			.addAttachingAttribute("ContentOrientation(Center)")
			.addConfiguration(
				new DeepConfiguration()
				.setSelectorType("VerticalStack")
				.addAttachingAttribute(
					"ContentOrientation(Center)",
					"ElementMargin(50)"
				),
				new DeepConfiguration()
				.setSelectorType("Label")
				.addAttachingAttribute("NormalTextSize(100)"),
				new DeepConfiguration()
				.setSelectorType("Button")
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
