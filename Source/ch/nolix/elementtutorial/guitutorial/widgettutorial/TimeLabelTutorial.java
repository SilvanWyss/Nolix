package ch.nolix.elementtutorial.guitutorial.widgettutorial;

import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.WidgetLookState;
import ch.nolix.element.time.Time;

/**
 * The {@link TimeLabelTutorial} is a tutorial for {@link Label}s.
 * Of the {@link TimeLabelTutorial} an instance cannot be created.
 *
 * @author Silvan Wyss
 * @date 2017-09-09
 * @lines 80
 */
public final class TimeLabelTutorial {
	
	/**
	 * Creates a {@link Frame} with 2 {@link Label}s.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("Time Label tutorial");
		
		//Creates clockCaptionLabel.
		final var clockCaptionLabel = new Label().setText("Time:");
		
		//Configures the look of the clockCaptionLabel.
		clockCaptionLabel.getRefLook().setTextSizeForState(WidgetLookState.BASE, 50);
		
		//Creates clockLabel.
		final var clockLabel = new Label();
		
		//Configures the look of the clockLabel.
		clockLabel.getRefLook()
		.setBackgroundColorForState(WidgetLookState.BASE, Color.YELLOW)
		.setTextSizeForState(WidgetLookState.BASE, 50);
	
		//Adds the clockCaptionLabel and clockLabel to the Frame.
		frame.addLayerOnTop(new HorizontalStack().addWidget(clockCaptionLabel, clockLabel));
		
		//Starts a background job that updates constantly the text of the clockLabel.
		Sequencer
		.asLongAs(frame::isOpen)
		.afterAllMilliseconds(100)
		.runInBackground(
			() -> {		
				
				//Sets the text to the clockLabel.
				clockLabel.setText(getCurrentTimeText());
				
				//Refreshes the Frame.
				frame.refresh();
			}
		);
	}
	
	//method
	/**
	 * @return the current time as text.
	 */
	private static String getCurrentTimeText() {
		
		final var currentTime = Time.fromCurrentTime();
		
		return
		String.format(
			"%02d:%02d:%02d",
			currentTime.getHourOfDay(),
			currentTime.getMinuteOfHour(),
			currentTime.getSecondOfMinute()
		);
	}
	
	/**
	 * Prevents that an instance of the {@link TimeLabelTutorial} can be created.
	 */
	private TimeLabelTutorial() {}
}
