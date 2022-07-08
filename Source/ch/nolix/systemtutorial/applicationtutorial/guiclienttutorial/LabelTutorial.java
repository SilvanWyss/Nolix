package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.containerwidget.HorizontalStack;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.WidgetLookState;
import ch.nolix.system.time.moment.Time;

public final class LabelTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
				
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Label tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	public static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		@SuppressWarnings("resource")
		protected void initialize() {
						
			//Creates clockCaptionLabel.
			final var clockCaptionLabel = new Label().setText("Time:");
			
			//Configures the look of the clockCaptionLabel.
			clockCaptionLabel.getRefActiveLook().setTextSizeForState(WidgetLookState.BASE, 50);
			
			//Creates clockLabel.
			final var clockLabel = new Label();
			
			//Configures the look of the clockLabel.
			clockLabel
			.getRefActiveLook()
			.setBackgroundColorForState(WidgetLookState.BASE, Color.YELLOW)
			.setTextSizeForState(WidgetLookState.BASE, 50);
			
			//Creates mainHorizontalStack.
			final var mainHorizontalStack = new HorizontalStack().addWidget(clockCaptionLabel, clockLabel);
			
			//Configures the look of the mainHorizontalStack.
			mainHorizontalStack.setElementMargin(50);
			
			//Adds the mainHorizontalStack to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootWidget(mainHorizontalStack);
			
			//Starts a background job that updates constantly the text of the clockLabel.
			GlobalSequencer
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
			final var currentTime = Time.ofNow();
			
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
