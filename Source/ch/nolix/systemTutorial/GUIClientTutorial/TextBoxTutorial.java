package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.TextBox;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.NetServer;

public final class TextBoxTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer with an Application for BackGUIClients.
		final var netServer = new NetServer("TextBox Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeStage2() {
			
			//Creates a TextBox.
			final var textBox = new TextBox();
			
			//Configures the look of the TextBox.
			textBox
			.setProposalWidth(200)
			.applyOnBaseLook(
				bl ->
				bl
				.setBorderThicknesses(5)
				.setBackgroundColor(Color.LAVENDER)
				.setPaddings(5)
			);
			
			//Adds the TextBox to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(textBox);
		}
	}
	
	private TextBoxTutorial() {}
}
