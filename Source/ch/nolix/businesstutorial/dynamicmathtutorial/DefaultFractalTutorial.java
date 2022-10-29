package ch.nolix.businesstutorial.dynamicmathtutorial;

import ch.nolix.business.bigdecimalmath.FractalBuilder;
import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.ImageControl;

public final class DefaultFractalTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication(
			"Default fractal tutorial",
			MainSession.class,
			VoidApplicationContext.INSTANCE
		);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<VoidApplicationContext> {
		
		@Override
		protected void initialize() {
			
			getRefGUI()
			.pushLayerWithRootControl(
				new ImageControl().setImage(new FractalBuilder().build().startImageGeneration().getRefImage())
			);
			
			GlobalSequencer.asLongAs(this::isOpen).afterAllSeconds().runInBackground(this::updateCounterpart);
		}
	}
	
	private DefaultFractalTutorial() {}
}
