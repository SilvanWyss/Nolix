package ch.nolix.systemtutorial.webguitutorial.maintutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Textbox;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.mainapi.ControlState;

public final class CookieTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Cookie tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	public static final class MainSession extends BackendWebClientSession<Object> {
		
		private final ITextbox textbox =
		new Textbox().editStyle(ts -> ts.setBorderThicknessForState(ControlState.BASE, 1));
		
		@Override
		protected void initialize() {
			
			final var input = getRefParentClient().getCookieValueByCookieNameOrNull("input");
			if (input != null) {
				textbox.setText(input);
			}
			
			getRefGUI().pushLayerWithRootControl(
				new HorizontalStack()
				.addControl(
					textbox,
					new Button()
					.setText("Save input in cookie")
					.setLeftMouseButtonPressAction(this::saveInputInCookie)
				)
			);
		}
		
		private void saveInputInCookie() {
			
			getRefParentClient().setOrAddCookieWithNameAndValue("input", textbox.getText());
			
			ShellProvider.startFirefoxOpeningLoopBackAddress();
		}
	}
	
	private CookieTutorial() {}
}
