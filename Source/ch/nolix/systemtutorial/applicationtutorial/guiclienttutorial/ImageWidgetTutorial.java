package ch.nolix.systemtutorial.applicationtutorial.guiclienttutorial;

//own imports
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.guiapplication.BackendGUIClientSession;
import ch.nolix.system.application.guiapplication.FrontendGUIClient;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.gui.color.Color;
import ch.nolix.system.gui.image.MutableImage;
import ch.nolix.system.gui.widget.ImageWidget;
import ch.nolix.system.gui.widget.WidgetLookState;

public final class ImageWidgetTutorial {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("ImageWidget tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			
			//Loads an Image.
			final var image =
			MutableImage.fromResource(
				"ch/nolix/systemtutorial/guitutorial/widgettutorial/resource/Singer_Building.jpg"
			);
			
			//Creates an ImageWidget with the Image.
			final var imageWidget = new ImageWidget().setImage(image);
			
			//Configures the look of the ImageWidget.
			imageWidget.getRefActiveLook()
			.setBorderThicknessForState(WidgetLookState.BASE, 5)
			.setBackgroundColorForState(WidgetLookState.BASE, Color.LAVENDER)
			.setPaddingForState(WidgetLookState.BASE, 5);
			
			//Adds the ImageWidget to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootWidget(imageWidget);
		}
	}
	
	private ImageWidgetTutorial() {}
}
