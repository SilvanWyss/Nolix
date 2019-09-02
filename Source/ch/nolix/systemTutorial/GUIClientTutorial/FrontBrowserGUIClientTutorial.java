package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.element.color.Color;
import ch.nolix.element.widgets.Label;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontBrowserGUIClient;
import ch.nolix.system.client.Application;

public final class FrontBrowserGUIClientTutorial {
	
	public static void main(String[] args) {
				
		//Creates an Application.
		final var application =	new Application<>("Application", BackGUIClient.class, MainSession.class);
		
		//Creates a FrontBrowserGUIClient that will connect to the application.
		new FrontBrowserGUIClient(application);
	}
	
	private static class MainSession extends BackGUIClientSession {
		
		@Override
		public void initialize() {
			internal_getRefGUI()
			.setTitle("FrontBrowserGUIClient Tutorial")
			.setBackgroundColor(Color.STEEL_BLUE)
			.addLayerOnTop(new Label("Hello World!").applyOnBaseLook(bl -> bl.setTextSize(50)));
		}
	}
	
	private FrontBrowserGUIClientTutorial() {}
}
