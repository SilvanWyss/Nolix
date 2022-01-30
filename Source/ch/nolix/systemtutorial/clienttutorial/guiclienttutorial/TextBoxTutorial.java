package ch.nolix.systemtutorial.clienttutorial.guiclienttutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.textbox.TextBox;
import ch.nolix.element.gui.widget.WidgetLookState;
import ch.nolix.system.client.base.Server;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.client.guiclient.FrontGUIClient;

public final class TextBoxTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer.
		final var netServer = new Server();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("TextBox tutorial", MainSession.class);
		
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
			
			//Creates a TextBox.
			final var textBox = new TextBox();
			
			//Configures the look of the TextBox.
			textBox
			.setProposalWidth(200)
			.onLook(
				l ->
				l
				.setBorderThicknessForState(WidgetLookState.BASE, 5)
				.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
				.setPaddingForState(WidgetLookState.BASE, 5)
			);
			
			//Adds the TextBox to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(textBox);
		}
	}
	
	private TextBoxTutorial() {}
}
