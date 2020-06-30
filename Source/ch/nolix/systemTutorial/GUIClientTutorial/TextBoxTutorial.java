package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.element.color.Color;
import ch.nolix.element.widget.TextBox;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.NetServer;

public final class TextBoxTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer with an Application for BackGUIClients.
		new NetServer("TextBox Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
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
