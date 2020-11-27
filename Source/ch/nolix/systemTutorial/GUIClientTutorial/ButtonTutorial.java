package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.GUI.CursorIcon;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Button;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.NetServer;

public final class ButtonTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Button Tutorial", MainSession.class);
		
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
		protected void initializeStage2() {
						
			//Creates a Button.
			final var button =
			new Button()
			.setText("Change background color")
			.setLeftMouseButtonPressAction(this::changeBackgroundColor);
			
			//Configures the look of the Button.
			button
			.setCustomCursorIcon(CursorIcon.HAND)
			.applyOnBaseLook(
				bl ->
				bl
				.setBorderThicknesses(5)
				.setBackgroundColor(Color.LAVENDER)
				.setPaddings(5)
				.setTextSize(50)
			)
			.applyOnHoverLook(hl -> hl.setBackgroundColor(Color.LAVENDER))
			.applyOnFocusLook(fl -> fl.setBackgroundColor(Color.LAVENDER));
			
			//Adds the Button to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(button);
		}
		
		private void changeBackgroundColor() {
			
			getRefGUI().setBackgroundColor(counter % 2 == 0 ? Color.WHITE : Color.BLUE);
			
			counter++;
		}
	}
	
	/**
	 * Avoids that an instance of the {@link ButtonTutorial} can be created.
	 */
	private ButtonTutorial() {}
}
