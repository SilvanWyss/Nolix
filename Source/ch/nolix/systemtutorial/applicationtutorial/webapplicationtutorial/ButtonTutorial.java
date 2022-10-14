package ch.nolix.systemtutorial.applicationtutorial.webapplicationtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;

public final class ButtonTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort(true);
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Button tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<VoidApplicationContext> {
		
		private int count;
		
		private final Text countLabel = new Text().setText(String.valueOf(count));
		
		@Override
		protected void initialize() {
			getRefGUI().pushLayerWithRootControl(
				new VerticalStack()
				.addControl(
					countLabel,
					new Button().setText("Increment").setLeftMouseButtonPressAction(this::incrementCount)
				)
			);
		}
		
		private void incrementCount() {
			count++;
			countLabel.setText(String.valueOf(count));
			updateCounterpart();
		}
	}
	
	private ButtonTutorial() {}
}
