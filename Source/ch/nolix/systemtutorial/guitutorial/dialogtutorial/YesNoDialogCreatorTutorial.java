package ch.nolix.systemtutorial.guitutorial.dialogtutorial;

import ch.nolix.system.configuration.Configuration;
import ch.nolix.system.configuration.DeepConfiguration;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.dialog.YesNoDialogCreator;
import ch.nolix.system.gui.main.Frame;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.ButtonRole;
import ch.nolix.system.gui.widget.LabelRole;

public final class YesNoDialogCreatorTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		@SuppressWarnings("resource")
		final var frame = new Frame().setTitle("YesNoDialog tutorial");
		
		//Adds a Button, that can open a YesNoDialog, to the Frame.
		frame.pushLayerWithRootWidget(
			new Button()
			.setText("Quit")
			.setLeftMouseButtonPressAction(
				() -> frame.pushLayer(
					new YesNoDialogCreator()
					.createWithYesNoQuestionAndConfirmAction("Do you want to quit the program?", frame::close)
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
					"MinWidth(200)",
					"CursorIcon(Hand)",
					"ContentPosition(Center)",
					"BaseBackground(Color(SkyBlue))",
					"HoverBackground(Color(Blue))",
					"BaseTextSize(30)"
				),
				new DeepConfiguration()
				.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
				.addAttachingAttribute(
					"ProposalHeight(200)",
					"BaseBackground(Color(Lavender))",
					"ContentPosition(Center)"
				)
				.addConfiguration(
					new DeepConfiguration()
					.addSelectorRole(LabelRole.MAIN_TEXT),
					new DeepConfiguration()
					.addSelectorRole(ButtonRole.CONFIRM_BUTTON)
					.addAttachingAttribute("BaseBackground(Color(LightGreen))", "HoverBackground(Color(Green))"),
					new DeepConfiguration()
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
	
	private YesNoDialogCreatorTutorial() {}
}
