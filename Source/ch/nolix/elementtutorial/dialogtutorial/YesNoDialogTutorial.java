package ch.nolix.elementtutorial.dialogtutorial;

import ch.nolix.common.container.Stack;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.dialog.YesNoDialog;
import ch.nolix.element.gui.Frame;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.LabelRole;

public final class YesNoDialogTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame =
		new Frame()
		.setTitle("YesNoDialog Tutorial");
		
		//Adds a Button, that can open a YesNoDialog, to the Frame.
		frame.addLayerOnTop(
			new Button()
			.setText("Quit")
			.setLeftMouseButtonPressAction(
				() -> frame.addLayerOnTop(new YesNoDialog("Do you want to quit the program?", frame::close))
			)
		);
		
		//Sets a Configuration to the Frame.
		frame.setConfiguration(
			new Configuration()
			.addConfiguration(
				new DeepConfiguration()
				.setSelectorType(Button.class)
				.addAttachingAttribute(
					"MinWidth(200)",
					"CursorIcon(Hand)",
					"ContentPosition(Center)",
					"BaseBackgroundColor(SkyBlue)",
					"HoverBackgroundColor(Blue)",
					"BaseTextSize(30)"
				),
				new DeepConfiguration()
				.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
				.addAttachingAttribute("BaseBackgroundColor(Lavender)", "ContentPosition(Center)")
				.addConfiguration(
					new DeepConfiguration()
					.setSelectorType(Stack.class)
					.addAttachingAttribute("ElementMargin(0)"),
					new DeepConfiguration()
					.addSelectorRole(LabelRole.MAIN_TEXT)
					.addAttachingAttribute("BaseBottomPadding(100)"),
					new DeepConfiguration()
					.addSelectorRole(ButtonRole.CONFIRM_BUTTON)
					.addAttachingAttribute("BaseBackgroundColor(LightGreen)", "HoverBackgroundColor(Green)"),
					new DeepConfiguration()
					.addSelectorRole(ButtonRole.CANCEL_BUTTON)
					.addAttachingAttribute(
						"CursorIcon(Hand)",
						"BaseBackgroundColor(Salmon)",
						"HoverBackgroundColor(Red)"
					)
				)
			)
		);
	}
	
	private YesNoDialogTutorial() {}
}
