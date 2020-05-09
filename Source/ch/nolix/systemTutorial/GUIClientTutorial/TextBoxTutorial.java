package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.TextBox;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;

public final class TextBoxTutorial {
	
	public static void main(String[] args) {
		
		//Defines port.
		final var port = 50000;
		
		//Creates a NetServer with an Application for BackGUIClients.
		new NetServer(port, new Application<>("TextBox Tutorial", BackGUIClient.class, MainSession.class));
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient(port);
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefox(port);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		public void initialize() {
						
			//Creates a TextBox.
			final var textBox = new TextBox();
			
			//Configures the look of the TextBox.
			textBox
			.setProposalWidth(200)
			.applyOnBaseLook(
				bl ->
				bl
				.setBorderThicknesses(5)
				.setBorderColors(Color.REBECCA_PURPLE)
				.setBackgroundColor(Color.WHITE_SMOKE)
				.setPaddings(5)
			);
			
			//Adds the TextBox to the GUI of the current BackGUIClientSession.
			getRefGUI().addLayerOnTop(textBox);
		}
	}
	
	private TextBoxTutorial() {}
}
