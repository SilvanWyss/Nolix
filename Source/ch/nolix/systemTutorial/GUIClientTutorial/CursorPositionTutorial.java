package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.element.GUI.Layer;
import ch.nolix.element.elementEnum.ExtendedContentPosition;
import ch.nolix.element.widget.Label;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.Application;
import ch.nolix.system.client.NetServer;

/**
 * The {@link CursorPositionTutorial} is a tutorial for {@link FrontGUIClient}s.
 * Of the {@link CursorPositionTutorial} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2020-02
 * @lines 70
 */
public final class CursorPositionTutorial {
	
	/**
	 * 1. Creates a {@link Application} for {@link BackGUIClient}s.
	 * 2. Creates a {@link FrontGUIClient} that will connect to the {@link Application}.
	 * 
	 * @param arguments
	 */
	public static void main(String[] args) {
		
		//Creates a NetServer with an Application for BackGUIClients.
		new NetServer("Cursor position Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		private final Label cursorPositionLabel = new Label();
		
		@Override
		protected void initializeStage2() {
									
			//Configures the look of the cursorPositionLabel.
			cursorPositionLabel.applyOnBaseLook(bl -> bl.setPaddings(5));
			
			//Creates Layer
			final var layer = new Layer(ExtendedContentPosition.LeftTop, cursorPositionLabel);
			
			//Sets mouse move action to Layer.
			layer.setMouseMoveAction(this::updateCursorPosition);
			
			//Adds the Layer to the GUI of the current MainSession
			getRefGUI().addLayerOnTop(layer);	
		}
		
		private void updateCursorPosition() {
			cursorPositionLabel.setText(
				"Cursor position: x="
				+ getRefGUI().getViewAreaCursorXPosition()
				+ " y="
				+ getRefGUI().getViewAreaCursorYPosition()
			);
		}
	}
	
	/**
	 * Avoids that an instance of the {@link CursorPositionTutorial} can be created.
	 */
	private CursorPositionTutorial() {}
}
