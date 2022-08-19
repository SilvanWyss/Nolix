package ch.nolix.systemtutorial.applicationtutorial.guiapplicationtutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.structureproperty.ExtendedContentPosition;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

public final class CursorPositionTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Cursor position tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	public static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		private final Label cursorPositionLabel = new Label();
		
		@Override
		protected void initialize() {
									
			//Configures the style of the cursorPositionLabel.
			cursorPositionLabel.onStyle(l -> l.setPaddingForState(ControlState.BASE, 5));
			
			//Creates Layer.
			final var layer = new Layer()
			.setContentPosition(ExtendedContentPosition.TOP_LEFT)
			.setRootWidget(cursorPositionLabel);
			
			//Sets mouse move action to Layer.
			layer.setMouseMoveAction(this::updateCursorPosition);
			
			//Adds the Layer to the GUI of the current MainSession.
			getRefGUI().pushLayer(layer);	
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
