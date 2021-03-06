package ch.nolix.elementtutorial.dialogtutorial;

import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.dialog.ErrorDialogCreator;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.widget.Button;

public final class ErrorDialogCreatorTutorial {
	
	public static void main(String[] args) {
		
		//Creates a Frame.
		final var frame = new Frame();
		
		//Sets the title of the Frame.
		frame.setTitle("ErrorDialog Tutorial");
		
		//Adds a Button, that can open an ErrorDialog, to the Frame.
		frame.addLayerOnTop(
			new Button()
			.setText("Fix everything")
			.setLeftMouseButtonPressAction(
				() -> 
				frame.addLayerOnTop(
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
					"BaseBackgroundColor(SkyBlue)",
					"HoverBackgroundColor(Blue)",
					"BaseTextSize(30)"
				),
				new DeepConfiguration()
				.addSelectorRole(ContainerRole.DIALOG_CONTAINER)
				.addAttachingAttribute("ProposalHeight(200)", "BaseBackgroundColor(Salmon)", "ContentPosition(Center)")
			)
		);
	}
	
	private ErrorDialogCreatorTutorial() {}
}
