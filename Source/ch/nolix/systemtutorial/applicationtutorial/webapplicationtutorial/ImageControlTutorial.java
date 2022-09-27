package ch.nolix.systemtutorial.applicationtutorial.webapplicationtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.webgui.control.ImageControl;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

public final class ImageControlTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort(true);
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("ImageControl tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
				
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			
			//Loads an Image.
			final var image =
			MutableImage.fromResource(
				"ch/nolix/systemtutorial/guitutorial/widgettutorial/resource/Singer_Building.jpg"
			);
			
			//Creates an ImageControl with the Image.
			final var imageControl = new ImageControl().setImage(image);
			
			//Configures the style of the ImageControl.
			imageControl.getRefStyle()
			.setBorderThicknessForState(ControlState.BASE, 5)
			.setBackgroundColorForState(ControlState.BASE, Color.LAVENDER)
			.setPaddingForState(ControlState.BASE, 5);
			
			//Adds the ImageControl to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootControl(imageControl);
		}
	}
	
	private ImageControlTutorial() {}
}
