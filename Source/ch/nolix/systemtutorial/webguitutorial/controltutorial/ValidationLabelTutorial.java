package ch.nolix.systemtutorial.webguitutorial.controltutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.control.Textbox;
import ch.nolix.system.webgui.control.ValidationLabel;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;

public final class ValidationLabelTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("ValidationLabel tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession extends BackendWebClientSession<Object> {
		
		private final Textbox positiveNumberTextbox = new Textbox();
		
		@Override
		protected void initialize() {
			getOriGUI().pushLayerWithRootControl(
				new VerticalStack()
				.addControl(
					new Label().setText("Enter a positive number"),
					new ValidationLabel(), 
					positiveNumberTextbox,
					new Button().setText("Ok").setLeftMouseButtonPressAction(this::enterPositiveNumber)
				)
			);
		}
		
		private void enterPositiveNumber() {
			final var number = Integer.parseInt(positiveNumberTextbox.getText());
			GlobalValidator.assertThat(number).thatIsNamed(LowerCaseCatalogue.NUMBER).isPositive();
		}
	}
	
	private ValidationLabelTutorial() {}
}
