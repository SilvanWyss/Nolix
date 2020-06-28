package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.common.localComputer.ShellProvider;
import ch.nolix.element.color.Color;
import ch.nolix.element.graphic.Image;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.client.NetServer;

public final class ImageWidgetTutorial {
	
	public static void main(String[] args) {
		
		//Creates a NetServer with an Application for BackGUIClients.
		new NetServer("ImageWidget Tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the NetServer.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxToLoopBackAddress();
	}
	
	private static final class MainSession extends BackGUIClientSession {
		
		@Override
		protected void initializeStage2() {
			
			//Creates an Image.
			final var image = Image.fromResource("ch/nolix/elementTutorial/widgetTutorial/resource/Singer_Building.jpg");
			
			//Creates an ImageWidget with the Image.
			final var imageWidget = new ImageWidget(image);
			
			//Configures the look of the ImageWidget.
			imageWidget.applyOnBaseLook(
				bl ->
				bl
				.setBorderThicknesses(5)
				.setBackgroundColor(Color.LAVENDER)
				.setPaddings(5)
			);
			
			//Adds the ImageWidget to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(imageWidget);
		}
	}
	
	private ImageWidgetTutorial() {}
}
