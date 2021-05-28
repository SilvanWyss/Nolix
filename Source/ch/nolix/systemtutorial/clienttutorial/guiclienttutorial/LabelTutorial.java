package ch.nolix.systemtutorial.clienttutorial.guiclienttutorial;

import ch.nolix.common.environment.localcomputer.ShellProvider;
import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.time.Time;
import ch.nolix.system.client.base.NetServer;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.client.guiclient.FrontGUIClient;

public final class LabelTutorial {
	
	public static void main(String[] args) {
				
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Label tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
						
			//Creates clockCaptionLabel.
			final var clockCaptionLabel = new Label().setText("Time:");
			
			//Configures the look of the clockCaptionLabel.
			clockCaptionLabel.getRefLook().setTextSizeForState(WidgetLookState.BASE, 50);
			
			//Creates clockLabel.
			final var clockLabel = new Label();
			
			//Configures the look of the clockLabel.
			clockLabel
			.getRefLook()
			.setBackgroundColorForState(WidgetLookState.BASE, Color.YELLOW)
			.setTextSizeForState(WidgetLookState.BASE, 50);
			
			//Adds the clockCaptionLabel and clockLabel to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(
				new HorizontalStack()
				.addWidget(clockCaptionLabel, clockLabel)
			);
			
			//Starts a background job that updates constantly the text of the clockLabel.
			Sequencer
			.asLongAs(getRefGUI()::isOpen)
			.afterAllMilliseconds(100)
			.runInBackground(
				() -> {
					
					//Sets the text to the clockLabel.
					clockLabel.setText(getCurrentTimeAsText());
					
					//Updates the counterpart of the BackGUIClient of the current current MainSession.
					updateCounterpart();
				}
			);
		}
		
		private String getCurrentTimeAsText() {
			
			//Gets the current time.
			final var currentTime = Time.fromCurrentTime();
			
			//Returns the current time as text.
			return
			String.format(
				"%02d:%02d:%02d",
				currentTime.getHourOfDay(),
				currentTime.getMinuteOfHour(),
				currentTime.getSecondOfMinute()
			);
		}
	}
	
	private LabelTutorial() {}
}
