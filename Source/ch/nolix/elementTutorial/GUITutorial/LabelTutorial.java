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
 * The {@link LabelTutorial} provides a tutorial for a {@link Label}.
 * Of the {@link LabelTutorial} no instance can be created.
 *
 * @author Silvan Wyss
 * @month 2017-09
 * @lines 80
 */
public final class LabelTutorial {

	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
	
		//Creates clock caption label.
		final var clockCaptionLabel = new Label("Time:");
		
		//Sets the look of the clock caption label.
		clockCaptionLabel
		.getRefBaseLook()
		.setTextSize(50);
		
		//Creates clock label.
		final var clockLabel = new Label();
		
		//Sets the look of the clock label.
		clockLabel
		.getRefBaseLook()
		.setBackgroundColor(Color.YELLOW)
		.setTextSize(50);
	
		//Creates a frame that will contain the clock caption label and the clock label.
		final var frame =
		new Frame()
		.setTitle("Label Tutorial")
		.setRootWidget(
			new HorizontalStack(
				clockCaptionLabel,
				clockLabel
			)
		);
		
		//Starts the background job that updates the text of the clock label.
		Sequencer
		.asLongAs(() -> frame.isAlive())
		.afterAllMilliseconds(100)
		.runInBackground(
			() -> {
				
				//Gets the current time.
				final var currentTime = Time.createCurrentTime();
				
				//Creates text.
				final var text =
				String.format(
					"%02d:%02d:%02d",
					currentTime.getHourOfDay(),
					currentTime.getMinuteOfHour(),
					currentTime.getSecondOfMinute()
				);
				
				//Sets the text to the clock label.
				clockLabel.setText(text);
				
				frame.refresh();
			}
		);
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link LabelTutorial} can be created.
	 */
	private LabelTutorial() {}
}
