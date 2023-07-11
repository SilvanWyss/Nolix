package ch.nolix.systemtutorial.webguitutorial.dialogtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.dialog.EnterValueDialogFactory;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ILabel;

public final class EnterValueDialogFactoryTutorial {
	
	private static final EnterValueDialogFactory ENTER_VALUE_DIALOG_FACTORY = new EnterValueDialogFactory();
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forHttpPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplicationWithNameAndInitialSessionClassAndVoidContext(
			"Yes-no-dialog tutorial",
			MainSession.class
		);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startDefaultWebBrowserOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	private static final class MainSession //NOSONAR: A 1-file-tutorial is allowed to have a long static class.
	extends WebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			final var nameLabel = new Label().setText("Mister ?");
			
			getOriGui().pushLayerWithRootControl(
				new VerticalStack()
				.addControl(
					nameLabel,
					new Button()
					.setText("Edit your name")
					.setLeftMouseButtonPressAction(
						() ->
						getOriGui().pushLayer(
							ENTER_VALUE_DIALOG_FACTORY.createEnterValueDialogWithTextAndOriginalValueAndValueTaker(
								"Enter your name",
								nameLabel.getText(),
								n -> setNewName(n, nameLabel)
							)
						)
					)
				)
			);
		}
		
		private void setNewName(final String name, final ILabel nameLabel) {
			
			GlobalValidator
			.assertThat(name)
			.thatIsNamed(LowerCaseCatalogue.NAME)
			.isNotShorterThan(4);
			
			nameLabel.setText(name);
		}
	}
	
	private EnterValueDialogFactoryTutorial() {}
}
