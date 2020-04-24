package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.element.color.Color;
import ch.nolix.element.widget.Label;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontCanvasGUIClient;
import ch.nolix.system.client.Application;

public final class FrontCanvasGUIClientTutorial {
	
	public static void main(String[] args) {
				
		//Creates an Application.
		final var application =	new Application<>("Application", BackGUIClient.class, MainSession.class);
		
		//Creates a FrontBrowserGUIClient that will connect to the application.
		new FrontCanvasGUIClient(application);
	}
	
	private static class MainSession extends BackGUIClientSession {
		
		@Override
		public void initialize() {
			getRefGUI()
			.setTitle("FrontBrowserGUIClient Tutorial")
			.setBackgroundColor(Color.STEEL_BLUE)
			.addLayerOnTop(new Label("Hello World!").applyOnBaseLook(bl -> bl.setTextSize(50)));
		}
	}
	
	private FrontCanvasGUIClientTutorial() {}
}
