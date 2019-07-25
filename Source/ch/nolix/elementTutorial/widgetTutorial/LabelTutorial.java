package ch.nolix.elementTutorial.widgetTutorial;

import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.color.Color;
import ch.nolix.element.time.Time;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.Label;

/**
 * The {@link LabelTutorial} is a tutorial for {@link Label}s.
 * Of the {@link LabelTutorial} an instance cannot be created.
 *
 * @author Silvan Wyss
 * @month 2017-09
 */
public final class LabelTutorial {
	
	/**
	 * Creates a {@link Frame} with 2 {@link Label}s.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("Label Tutorial");
		
		//Creates clockCaptionLabel.
		final var clockCaptionLabel = new Label("Time:");
		
		//Configures the look of clockCaptionLabel.
		clockCaptionLabel.applyOnBaseLook(bl -> bl.setTextSize(50));
		
		//Creates clockLabel.
		final var clockLabel = new Label();
		
		//Configures the look of the clockLabel.
		clockLabel.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.YELLOW).setTextSize(50));
	
		//Adds the clockCaptionLabel and clockLabel to the frame.
		frame.addLayerOnTop(new HorizontalStack(clockCaptionLabel, clockLabel));
		
		//Starts a background job that updates constantly the text of the clockLabel.
		Sequencer
		.asLongAs(() -> frame.isOpen())
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
				
				//Sets the text to the clockLabel.
				clockLabel.setText(text);
				
				//Refreshes the frame.
				frame.refresh();
			}
		);
	}
	
	/**
	 * Avoids that an instance of the {@link LabelTutorial} can be created.
	 */
	private LabelTutorial() {}
}
