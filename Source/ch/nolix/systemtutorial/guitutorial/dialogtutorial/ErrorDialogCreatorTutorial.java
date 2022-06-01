package ch.nolix.systemtutorial.guitutorial.dialogtutorial;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.configuration.DeepConfiguration;
import ch.nolix.system.gui.base.Frame;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.dialog.ErrorDialogCreator;
import ch.nolix.system.gui.widget.Button;

public final class ErrorDialogCreatorTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame();
		
		//Sets the title of the Frame.
		frame.setTitle("ErrorDialog tutorial");
		
		//Adds a Button, that can open an ErrorDialog, to the Frame.
		frame.pushLayerWithRootWidget(
			new Button()
			.setText("Fix everything")
			.setLeftMouseButtonPressAction(
				() -> 
				frame.pushLayer(
					new ErrorDialogCreator().createWithErrorMessage("There is not enough time to fix everything.")
				)
			)
		);
		
		//Creates a Configuration and sets it to the Frame.
		frame.setConfiguration(
			new Configuration()
			.addConfiguration(
				new DeepConfiguration()
				.setSelectorType(Button.class)
				.addAttachingAttribute(
					"MinWidth(500)",
					"CursorIcon(Hand)",
					"ContentPosition(Center)",
					"BaseBackground(Color(SkyBlue))",
					"HoverBackground(Color(Blue))",
					"BaseTextSize(30)"
				),
				new DeepConfiguration()
				.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
				.addAttachingAttribute("ProposalHeight(200)", "BaseBackground(Color(Salmon))", "ContentPosition(Center)")
			)
		);
	}
	
	private ErrorDialogCreatorTutorial() {}
}
