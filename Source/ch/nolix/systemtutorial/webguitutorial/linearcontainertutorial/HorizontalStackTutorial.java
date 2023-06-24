package ch.nolix.systemtutorial.webguitutorial.linearcontainertutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class HorizontalStackTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forHttpPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("HorizontalStack tutorial", MainSession.class, new VoidObject());
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends WebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Creates a HorizontalStack.
			final var horizontalStack = new HorizontalStack();
			
			//Creates 4 Labels.
			final var label1 = new Label().setText("A");
			final var label2 = new Label().setText("B");
			final var label3 = new Label().setText("C");
			final var label4 = new Label().setText("D");
			
			//Adds the Labels to the HorizontalStack.
			horizontalStack.addControl(label1, label2, label3, label4);
			
			//Configures the style of the HorizontalStack.
			horizontalStack.getOriStyle().setChildControlMarginForState(ControlState.BASE, 20);
			
			//Configures the style of the Labels.
			label1.getOriStyle().setTextSizeForState(ControlState.BASE, 50);
			label2.getOriStyle().setTextSizeForState(ControlState.BASE, 50);
			label3.getOriStyle().setTextSizeForState(ControlState.BASE, 50);
			label4.getOriStyle().setTextSizeForState(ControlState.BASE, 50);
			
			//Adds the HorizontalStack to the GUI of the current MainSession.
			getOriGui().pushLayerWithRootControl(horizontalStack);
		}
	}
	
	private HorizontalStackTutorial() {}
}
