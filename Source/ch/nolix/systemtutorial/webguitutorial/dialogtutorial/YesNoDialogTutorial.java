package ch.nolix.systemtutorial.webguitutorial.dialogtutorial;

import ch.nolix.core.environment.localcomputer.ShellProvider;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.system.application.main.Server;
import ch.nolix.system.application.main.VoidApplicationContext;
import ch.nolix.system.application.webapplication.BackendWebClientSession;
import ch.nolix.system.element.style.DeepStyle;
import ch.nolix.system.element.style.Style;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.dialog.YesNoDialogFactory;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.ButtonRole;

public final class YesNoDialogTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Server.
		final var server = Server.forDefaultPort();
		
		//Adds a default Application to the Server.
		server.addDefaultApplication("Yes-no-dialog tutorial", MainSession.class, VoidApplicationContext.INSTANCE);
		
		//Starts a web browser that will connect to the Server.
		ShellProvider.startFirefoxOpeningLoopBackAddress();
		
		//Closes the Server as soon as it does not have a client connected any more.
		GlobalSequencer.waitForSeconds(2);
		GlobalSequencer.asSoonAsNoMore(server::hasClientConnected).runInBackground(server::close);
	}
	
	public static final class MainSession extends BackendWebClientSession<Object> {
		
		@Override
		protected void initialize() {
			
			//Adds a Button, that can open a YesNoDialog, to the GUI of the current MainSession.
			getRefGUI().pushLayerWithRootControl(
				new Button()
				.setText("Click me")
				.setLeftMouseButtonPressAction(
					() -> 
					getRefGUI()
					.pushLayer(
						YesNoDialogFactory
						.INSTANCE
						.createYesNoDialogWithYesNoQuestionAndConfirmAction(
							"Do you want to open nolix.ch?",
							() -> getRefGUI().onFrontEnd().openNewTabWithURL("nolix.ch")
						)
					)
				)
			);
			
			//Adds a Style to the GUI of the current MainSession.
			getRefGUI().setStyle(
				new Style()
				.addConfiguration(
					new DeepStyle()
					.setSelectorType(Button.class)
					.addAttachingAttribute(
						"MinWidth(200)",
						"CursorIcon(Hand)",
						"BaseBackground(Color(SkyBlue))",
						"HoverBackground(Color(Blue))",
						"BaseTextSize(30)"
					),
					new DeepStyle()
					.setSelectorType(Layer.class)
					.addAttachingAttribute("Background(Color(White))"),
					new DeepStyle()
					.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
					.addAttachingAttribute(
						"BaseBackground(Color(Lavender))"
					)
					.addConfiguration(
						new DeepStyle()
						.addSelectorRole(ButtonRole.CONFIRM_BUTTON)
						.addAttachingAttribute("BaseBackground(Color(LightGreen))", "HoverBackground(Color(Green))"),
						new DeepStyle()
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
