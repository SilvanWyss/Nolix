package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.widget.Button;

public final class ButtonTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Button tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		private int counter = 1;
		
		@Override
		protected void initialize() {
						
			//Creates a Button.
			final var button =
			new Button()
			.setText("Change background color")
			.setLeftMouseButtonPressAction(this::changeBackgroundColor);
			
			//Adds the Button to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootWidget(button);
		}
		
		private void changeBackgroundColor() {
			
			if (counter % 2 == 0) {
				getRefGUI().setBackgroundColor(Color.WHITE);
			} else {
				getRefGUI().setBackgroundColor(Color.BLUE);
			}
						
			counter++;
		}
	}
	
	/**
	 * Prevents that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
