package ch.nolix.elementTutorial.dialogTutorial;

import ch.nolix.element.GUI.Frame;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.dialog.ErrorDialog;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.LabelRole;

public final class ErrorDialogTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("ErrorDialog Tutorial");
		
		//Adds a Button, that can open a ErrorDialog, to the Frame.
		frame.addLayerOnTop(
			new Button()
			.setText("Fix everything")
			.setLeftMouseButtonPressAction(
				() -> frame.addLayerOnTop(new ErrorDialog("There is not enough time to fix everything."))
			)
		);
		
		//Sets a Configuration to the Frame.
		frame.setConfiguration(
			new Configuration()
			.addConfiguration(
				new DeepConfiguration()
				.setSelectorType(Button.class)
				.addAttachingAttribute(
					"MinWidth(500)",
					"CursorIcon(Hand)",
					"ContentPosition(Center)",
					"BaseBackgroundColor(SkyBlue)",
					"HoverBackgroundColor(Blue)",
					"BaseTextSize(30)"
				),
				new DeepConfiguration()
				.addSelectorRole(ContainerRole.DialogContainer)
				.addAttachingAttribute("BaseBackgroundColor(Salmon)", "ContentPosition(Center)"),
				new DeepConfiguration()
				.addSelectorRole(LabelRole.ErrorText)
				.addAttachingAttribute("BaseBottomPadding(100)")
			)
		);
	}
	
	private ErrorDialogTutorial() {}
}
