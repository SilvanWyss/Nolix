package ch.nolix.templatetutorial.webapplicationtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.template.webapplication.UnderConstructionPageSession;

public final class UnderConstructionPageTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forHttpPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication(
			"Under construction page tutorial",
			MainSession.class,
			new VoidObject()
		);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	public static final class MainSession extends WebClientSession<Object> {
		
		@Override
		protected void initialize() {
			getOriGui()
			.pushLayerWithRootControl(
				new VerticalStack()
				.addControl(
					new Label()
					.setText("Page 1"),
					new Button()
					.setText("Go")
					.setLeftMouseButtonPressAction(() -> push(new UnderConstructionPageSession()))
				)
			);
		}
	}
	
	private UnderConstructionPageTutorial() {}
}
