package ch.nolix.systemtutorial.webguitutorial.controltutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;

public final class ButtonTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forHttpPort();
		
		//Adds a default Application to the Server.
		//server.addDefaultApplicationWithNameAndInitialSessionClassAndContext("Button tutorial", MainSession.class, new VoidObject());
		server.addDefaultApplicationWithNameAndInitialSessionClassAndVoidContext("Button tutorial", MainSession.class);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends WebClientSession<Object> {
		
		private int count;
		
		private final Label countLabel = new Label().setText(String.valueOf(count));
		
		@Override
		protected void initialize() {
			getOriGui().pushLayerWithRootControl(
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
