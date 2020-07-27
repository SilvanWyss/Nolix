package ch.nolix.elementTutorial.dialogTutorial;

import ch.nolix.common.container.Stack;
import ch.nolix.element.GUI.Frame;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.dialog.YesNoDialog;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.LabelRole;

public final class YesNoDialogTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame("YesNoDialog Tutorial");
		
		//Adds a Button, that can open a YesNoDialog, to the Frame.
		frame.addLayerOnTop(
			new Button("Quit")
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
				.addSelectorRole(ContainerRole.DialogContainer)
				.addAttachingAttribute("BaseBackgroundColor(Lavender)", "ContentPosition(Center)")
				.addConfiguration(
					new DeepConfiguration()
					.setSelectorType(Stack.class)
					.addAttachingAttribute("ElementMargin(0)"),
					new DeepConfiguration()
					.addSelectorRole(LabelRole.MainText)
					.addAttachingAttribute("BaseBottomPadding(100)"),
					new DeepConfiguration()
					.addSelectorRole(ButtonRole.ConfirmButton)
					.addAttachingAttribute("BaseBackgroundColor(LightGreen)", "HoverBackgroundColor(Green)"),
					new DeepConfiguration()
					.addSelectorRole(ButtonRole.CancelButton)
					.addAttachingAttribute("CursorIcon(Hand)", "BaseBackgroundColor(Salmon)", "HoverBackgroundColor(Red)")
				)
			)
		);
	}
	
	private YesNoDialogTutorial() {}
}
