package ch.nolix.systemtutorial.clienttutorial.guiclienttutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.Sequencer;
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui.image.MutableImage;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.WidgetLookState;
import ch.nolix.system.client.base.Server;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.client.guiclient.FrontGUIClient;

public final class ImageWidgetTutorial {
	
	private static final class MainSession extends BackGUIClientSession {
		
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
		final var server = new Server();
		
		//Adds a default Application to the NetServer.
		server.addDefaultApplication("ImageWidget tutorial", MainSession.class);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontGUIClient();
		
		//Starts a web browser that will connect to the NetServer.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		Sequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private ImageWidgetTutorial() {}
}
