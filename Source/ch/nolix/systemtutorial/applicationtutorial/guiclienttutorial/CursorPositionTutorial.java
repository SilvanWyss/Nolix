package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.elementenum.ExtendedContentPosition;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.WidgetLookState;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;

public final class CursorPositionTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a NetServer.
		final var netServer = Server.forDefaultPort();
		
		//Adds a default Application to the NetServer.
		netServer.addDefaultApplication("Cursor position tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the NetServer as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(netServer::hasClientConnected).runInBackground(netServer::close);
	}
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
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
