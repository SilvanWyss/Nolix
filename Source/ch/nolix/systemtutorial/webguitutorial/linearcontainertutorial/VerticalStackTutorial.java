package ch.nolix.systemtutorial.webguitutorial.linearcontainertutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class VerticalStackTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("VerticalStack tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Creates a VerticalStack.
			final var verticalStack = new VerticalStack();
			
			//Creates 4 Labels.
			final var label1 = new Label().setText("A");
			final var label2 = new Label().setText("B");
			final var label3 = new Label().setText("C");
			final var label4 = new Label().setText("D");
			
			//Adds the Labels to the VerticalStack.
			verticalStack.addControl(label1, label2, label3, label4);
			
			//Configures the style of the VerticalStack.
			verticalStack.getRefStyle().setChildControlMarginForState(ControlState.BASE, 20);
			
			//Configures the style of the Labels.
			label1.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			label2.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			label3.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			label4.getRefStyle().setTextSizeForState(ControlState.BASE, 50);
			
			//Adds the VerticalStack to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootControl(verticalStack);
		}
	}
	
	private VerticalStackTutorial() {}
}
