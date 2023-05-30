package ch.nolix.systemtutorial.webguitutorial.maintutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.graphic.color.Color;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class LayerTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Layer tutorial", MainSession.class, new VoidObject());
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			getOriGUI().setTitle("Layer tutorial");
			
			//Creates labelA.
			final var labelA = new Label().setText("A");
			
			//Creates labelB.
			final var labelB = new Label().setText("B");
			
			//Configures the style of labelA.
			labelA.getOriStyle()
			.setTextSizeForState(ControlState.BASE, 200)
			.setTextColorForState(ControlState.BASE, Color.GREY);
			
			//Configures the look of labelB.
			labelB.getOriStyle()
			.setTextSizeForState(ControlState.BASE, 180)
			.setTextColorForState(ControlState.BASE, Color.BLACK);
			
			//Adds a new layer with labelA to the Frame.
			getOriGUI().pushLayerWithRootControl(labelA);
			
			//Adds a new layer with labelB to the Frame.
			getOriGUI().pushLayerWithRootControl(labelB);
		}
	}
	
	private LayerTutorial() {}
}

