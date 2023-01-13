package ch.nolix.systemtutorial.webguitutorial.linearcontainertutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class HorizontalStackTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("HorizontalStack tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Creates a HorizontalStack.
			final var horizontalStack = new HorizontalStack();
			
			//Creates 4 Labels.
			final var label1 = new Text().setText("A");
			final var label2 = new Text().setText("B");
			final var label3 = new Text().setText("C");
			final var label4 = new Text().setText("D");
			
			//Adds the Labels to the HorizontalStack.
			horizontalStack.addControl(label1, label2, label3, label4);
			
			//Configures the style of the HorizontalStack.
			horizontalStack.getRefStyle().setChildControlMarginForState(ControlState.BASE, 20);
			
			//Configures the style of the Labels.
			label1.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			label2.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			label3.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			label4.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			
			//Adds the HorizontalStack to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootControl(horizontalStack);
		}
	}
	
	private HorizontalStackTutorial() {}
}
