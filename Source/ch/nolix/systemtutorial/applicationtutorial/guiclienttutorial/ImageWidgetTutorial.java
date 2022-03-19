package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.WidgetLookState;
import ch.nolix.system.application.guiclient.BackendGUIClientSession;
import ch.nolix.system.application.guiclient.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;

public final class ImageWidgetTutorial {
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initializeBaseBackGUIClientSession() {
			
			//Creates an Image.
			final var image =
			MutableImage.fromResource(
				"ch/nolix/elementtutorial/guitutorial/widgettutorial/resource/Singer_Building.jpg"
			);
			
			//Creates an ImageWidget with the Image.
			final var imageWidget = new ImageWidget().setImage(image);
			
			//Configures the look of the ImageWidget.
			imageWidget.getRefLook()
			.setBorderThicknessForState(WidgetLookState.BASE, 5)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 5);
			
			//Adds the ImageWidget to the GUI of the current MainSession.
			getRefGUI().addLayerOnTop(imageWidget);
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the NetServer.
		server.addDefaultApplication("ImageWidget tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private ImageWidgetTutorial() {}
}
