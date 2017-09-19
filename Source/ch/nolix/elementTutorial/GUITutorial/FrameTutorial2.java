//package declaration
package ch.nolix.elementTutorial.GUITutorial;

//own imports
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.color.Color;
import ch.nolix.element.core.Time;

//class
/**
 * This class provides a tutorial for the frame class.
 * Of this class no instance can be created.
 *
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 70
 */
public final class FrameTutorial2 {

	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
	
		//Creates clock caption label.
		final Label clockCaptionLabel = new Label("Time:");		
		
		//Creates clock label.
		final Label clockLabel = new Label();
		
		//Sets a yellow background color to the clock label.
		clockLabel
		.getRefNormalStructure()
		.setBackgroundColor(Color.YELLOW);
	
		//Creates a frame that contains the clock caption label and the clock label.
		final Frame frame
		= new Frame()
		.setRootWidget(
			new HorizontalStack(
				clockCaptionLabel,
				clockLabel
			)
		);
		
		//Defines and starts the background job that updates the text of the clock label.
		Sequencer
		.asLongAs(() -> frame.isAlive())
		.afterAllMilliseconds(100)
		.runInBackground(
			() -> {
				
				//Gets the current time.
				final Time currentTime = Time.createCurrentTime();
				
				final String text
				= String.format(
					"%02d:%02d:%02d",
					currentTime.getHourOfDay(),
					currentTime.getMinuteOfHour(),
					currentTime.getSecondOfMinute()
				);
				
				//Sets the new text to the clock label.
				clockLabel.setText(text);
				
				frame.refresh();
			}
		);
	}
}
