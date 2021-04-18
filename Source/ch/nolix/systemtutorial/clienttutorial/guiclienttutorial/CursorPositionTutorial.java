package ch.nolix.systemtutorial.clienttutorial.guiclienttutorial;

import ch.nolix.common.environment.localcomputer.ShellProvider;
import ch.nolix.common.programcontrol.sequencer.Sequencer;
import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.WidgetLookState;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.system.client.base.NetServer;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.client.guiclient.FrontGUIClient;

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
			cursorPositionLabel.onLook(l -> l.setPaddingForState(WidgetLookState.BASE, 5));
			
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
	 * Prevents that an instance of the {@link CursorPositionTutorial} can be created.
	 */
	private CursorPositionTutorial() {}
}
