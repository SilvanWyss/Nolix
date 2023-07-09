package ch.nolix.systemtutorial.webguitutorial.dialogtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.webapplication.WebClientSession;
import ch.nolix.system.element.style.DeepSelectingStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.dialog.YesNoDialogFactory;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;

public final class YesNoDialogTutorial {
	
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
	
	public static final class MainSession extends WebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Adds a Button, that can open a YesNoDialog, to the GUI of the current MainSession.
			getOriGui().pushLayerWithRootControl(
				new Button()
				.setText("Click me")
				.setLeftMouseButtonPressAction(
					() -> 
					getOriGui()
					.pushLayer(
						new YesNoDialogFactory().createYesNoDialogWithYesNoQuestionAndConfirmAction(
							"Do you want to open nolix.ch?",
							() -> getOriGui().onFrontEnd().openNewTabWithUrl("nolix.ch")
						)
					)
				)
			);
			
			//Adds a Style to the GUI of the current MainSession.
			getOriGui().setStyle(
				new Style()
				.addConfiguration(
					new DeepSelectingStyle()
					.setSelectorType(Button.class)
					.addAttachingAttribute(
						"MinWidth(200)",
						"CursorIcon(Hand)",
						"BaseBackground(Color(SkyBlue))",
						"HoverBackground(Color(Blue))",
						"BaseTextSize(30)"
					),
					new DeepSelectingStyle()
					.setSelectorType(Layer.class)
					.addAttachingAttribute("Background(Color(White))"),
					new DeepSelectingStyle()
					.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
					.addAttachingAttribute(
						"BaseBackground(Color(Lavender))"
					)
					.addConfiguration(
						new DeepSelectingStyle()
						.addSelectorRole(ButtonRole.CONFIRM_BUTTON)
						.addAttachingAttribute("BaseBackground(Color(LightGreen))", "HoverBackground(Color(Green))"),
						new DeepSelectingStyle()
						.addSelectorRole(ButtonRole.CANCEL_BUTTON)
						.addAttachingAttribute(
							"CursorIcon(Hand)",
							"BaseBackground(Color(Salmon))",
							"HoverBackground(Color(Red))"
						)
					)
				)
			);
		}
	}
	
	private YesNoDialogTutorial() {}
}
