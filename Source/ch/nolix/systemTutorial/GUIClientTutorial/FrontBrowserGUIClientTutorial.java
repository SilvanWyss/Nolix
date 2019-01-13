package ch.nolix.systemTutorial.GUIClientTutorial;

import ch.nolix.element.GUI.Label;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.GUIClient.FrontBrowserGUIClient;
import ch.nolix.system.client.Application;

public final class FrontBrowserGUIClientTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates an application.
		final var application =
		new Application<>("Application", BackGUIClient.class, MainSession.class);
		
		//Creates a front browser GUI client that will connect to the application.
		new FrontBrowserGUIClient(application);
	}
	
	private static class MainSession extends BackGUIClientSession {
		
		@Override
		public void initialize() {
			getRefGUI().setRootWidget(new Label("Hello World!"));
		}
	}
	
	private FrontBrowserGUIClientTutorial() {}
}
