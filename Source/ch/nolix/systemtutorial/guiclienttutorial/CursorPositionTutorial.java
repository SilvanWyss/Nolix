package ch.nolix.systemtutorial.guiclienttutorial;

//own imports
import ch.nolix.common.localcomputer.ShellProvider;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.widget.Label;
import ch.nolix.system.client.NetServer;
import ch.nolix.system.guiclient.BackGUIClientSession;
import ch.nolix.system.guiclient.FrontGUIClient;

public final class CursorPositionTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer.
		final var netServer = new NetServer();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Cursor position Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		private final Label cursorPositionLabel = new Label();
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
									
			//Configures the look of the cursorPositionLabel.
			cursorPositionLabel.applyOnBaseLook(bl -> bl.setPaddings(5));
			
			//Creates Layer.
			final var layer = new Layer()
			.setContentPosition(ExtendedContentPosition.TOP_LEFT)
			.setRootWidget(cursorPositionLabel);
			
			//Sets mouse move action to Layer.
			layer.setMouseMoveAction(this::updateCursorPosition);
			
			//Adds the Layer to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(layer);	
		}
		
		private void updateCursorPosition() {
			cursorPositionLabel.setText(
				"Cursor position: x="
				+ getRefGUI().getCursorXPositionOnViewArea()
				+ " y="
				+ getRefGUI().getCursorYPositionOnViewArea()
			);
		}
	}
	
	/**
	 * Avoids that an instance of the {@link CursorPositionTutorial} can be created.
	 */
	private CursorPositionTutorial() {}
}
