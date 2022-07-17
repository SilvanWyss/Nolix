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
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.imageapi.ImageApplication;
import ch.nolix.systemapi.guiapi.structureproperty.ExtendedContentPosition;
import ch.nolix.systemapi.guiapi.widgetguiapi.ControlState;

public final class TransparentLayerTutorial {
	
	private static final class MainSession extends BackendGUIClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			
			//Loads an Image and sets it as background to the GUI of the current MainSession.
			getRefGUI().setBackgroundImage(
				MutableImage.fromResource("ch/nolix/systemTutorial/guiTutorial/maintutorial/resource/Pilatus.jpg"),
				ImageApplication.SCALE_TO_FRAME
			);
						
			/*
			 * Creates a transparent Layer with a bar, that is basically black, and
			 * adds it to the GUI of the current MainSession.
			 */
			getRefGUI().pushLayer(
				new Layer()
				.setOpacityPercentage(0.8)
				.setContentPosition(ExtendedContentPosition.BOTTOM)
				.setRootWidget(
					new Label()
					.setText("Nolix")
					.setMinWidthInPercentOfGUIViewAreaWidth(1.0)
					.onLook(
						l ->
						l
						.setBackgroundColorForState(ControlState.BASE, Color.BLACK)
						.setPaddingForState(ControlState.BASE, 50)
						.setTextSizeForState(ControlState.BASE, 100)
						.setTextColorForState(ControlState.BASE, Color.WHITE)
					)
				)
			);
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Transparent Layer Tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Creates a FrontGUIClient that will connect to the Server.
		new FrontendGUIClient();
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private TransparentLayerTutorial() {}
}
