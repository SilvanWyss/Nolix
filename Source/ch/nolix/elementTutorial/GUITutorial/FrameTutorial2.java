//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.core.controllerInterfaces.ILevel1Controller;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.specification.Statement;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.basic.Color;
import ch.nolix.element.basic.Time;

//class
/**
 * This class provides a tutorial for the frame class.
 * Of this class no instance can be created.
 *
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 60
 */
public final class FrameTutorial2 {

	//main method
	public static void main(String[] args) {
	
		//Creates clock caption label.
		final Label clockCaptionLabel = new Label("Time:");		
		
		//Creates clock label.
		final Label clockLabel = new Label();
		clockLabel
		.getRefNormalStructure()
		.setBackgroundColor(new Color(Color.YELLOW));
	
		//Creates frame.
		final Frame frame
		= new Frame()
		.setRootWidget(
			new HorizontalStack(
				clockCaptionLabel,
				clockLabel
			)
		)
		.setController(new ILevel1Controller() {			
			public void run(Statement command) {
				System.exit(0);				
			}
		})
		.setCloseCommand("Quit")
		.setBackgroundColor(new Color(Color.VERY_LIGHT_BLUE));
		
		//Defines and starts the background job that updates the text of the clock label.
		Sequencer
		.afterAllMilliseconds(100)
		.runInBackground(
			() -> {
				
				//Gets the current time.
				final Time currentTime = Time.createCurrentTime();
				
				final String text
				= String.format(
					"%02d : %02d : %02d",
					currentTime.getHourOfDay(),
					currentTime.getMinuteOfHour(),
					currentTime.getSecondOfMinute()
				);
				
				clockLabel.setText(text);
				
				frame.refresh();
			}
		);
	}
}
