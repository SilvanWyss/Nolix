package ch.nolix.elementtutorial.dialogtutorial;

import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.dialog.YesNoDialogCreator;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.ButtonRole;
import ch.nolix.element.gui.widget.LabelRole;

public final class YesNoDialogCreatorTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame().setTitle("YesNoDialog Tutorial");
		
		//Adds a Button, that can open a YesNoDialog, to the Frame.
		frame.addLayerOnTop(
			new Button()
			.setText("Quit")
			.setLeftMouseButtonPressAction(
				() -> frame.addLayerOnTop(
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
