package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.element.time.Time;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.Label;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.NetServer;

public class LabelTutorial {
	
	public static void main(String[] args) {
				
		//Creates a NetServer with an Application for BackGUIClients.
		new NetServer("Label Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxToLoopBackAddress();
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeStage2() {
						
			//Creates clockCaptionLabel.
			final var clockCaptionLabel = new Label("Time:");
			
			//Configures the look of the clockCaptionLabel.
			clockCaptionLabel.applyOnBaseLook(bl -> bl.setTextSize(50));
			
			//Creates clockLabel.
			final var clockLabel = new Label();
			
			//Configures the look of the clockLabel.
			clockLabel.applyOnBaseLook(bl -> bl.setBackgroundColor(Color.YELLOW).setTextSize(50));
			
			//Adds the clockCaptionLabel and clockLabel to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(new HorizontalStack(clockCaptionLabel, clockLabel));
			
			//Starts a background job that updates constantly the text of the clockLabel.
			Sequencer
			.asLongAs(getRefGUI()::isOpen)
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
					
					//Updates the counterpart of the BackGUIClient of the current current MainSession.
					updateCounterpart();
				}
			);
		}
	}
	
	private LabelTutorial() {}
}
