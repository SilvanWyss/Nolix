package ch.nolix.systemtutorial.clienttutorial.guiclienttutorial;

import ch.nolix.common.environment.localcomputer.ShellProvider;
import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.system.client.base.NetServer;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.client.guiclient.FrontGUIClient;

public final class ButtonTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Button tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		private int counter = 1;
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
						
			//Creates a Button.
			final var button =
			new Button()
			.setText("Change background color")
			.setLeftMouseButtonPressAction(this::changeBackgroundColor);
			
			//Adds the Button to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(button);
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
