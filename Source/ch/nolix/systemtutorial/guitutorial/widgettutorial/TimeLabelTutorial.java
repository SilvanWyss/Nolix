package ch.nolix.systemtutorial.guitutorial.widgettutorial;

//own imports
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.system.time.base.Time;

/**
 * The {@link TimeLabelTutorial} is a tutorial for {@link Label}s.
 * Of the {@link TimeLabelTutorial} an instance cannot be created.
 *
 * @author Silvan Wyss
 * @date 2017-09-09
 */
public final class TimeLabelTutorial {
	
	/**
	 * Creates a {@link Frame} with 2 {@link Label}s.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("Time Label tutorial");
		
		//Creates clockCaptionLabel.
		final var clockCaptionLabel = new Label().setText("Time:");
		
		//Configures the look of the clockCaptionLabel.
		clockCaptionLabel.getRefActiveLook().setTextSizeForState(WidgetLookState.BASE, 50);
		
		//Creates clockLabel.
		final var clockLabel = new Label();
		
		//Configures the look of the clockLabel.
		clockLabel.getRefActiveLook()
		.setBackgroundColorForState(WidgetLookState.BASE, Color.YELLOW)
		.setTextSizeForState(WidgetLookState.BASE, 50);
		
		//Creates mainHorizontalStack.
		final var mainHorizontalStack = new HorizontalStack().add(clockCaptionLabel, clockLabel);
		
		//Configures the look of the mainHorizontalStack.
		mainHorizontalStack.setElementMargin(50);
		
		//Adds the mainHorizontalStack to the Frame.
		frame.pushLayerWithWidget(mainHorizontalStack);
		
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
