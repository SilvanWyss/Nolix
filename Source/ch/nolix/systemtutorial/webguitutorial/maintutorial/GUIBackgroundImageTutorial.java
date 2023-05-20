package ch.nolix.systemtutorial.webguitutorial.maintutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.graphic.image.Image;
import ch.nolix.systemapi.graphicapi.imageapi.ImageApplication;

public final class GUIBackgroundImageTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Background image tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Loads an Image.
			final var image =
			Image.fromResource("ch/nolix/systemtutorial/webguitutorial/resource/pilatus.jpg");
			
			//Sets the Image as background image to the GUI of the current MainSession.
			getOriGUI().setBackgroundImage(image, ImageApplication.SCALE_TO_FRAME);
		}
	}
	
	private GUIBackgroundImageTutorial() {}
}
