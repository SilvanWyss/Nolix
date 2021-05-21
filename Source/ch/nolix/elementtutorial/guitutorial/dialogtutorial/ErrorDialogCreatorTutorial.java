package ch.nolix.elementtutorial.guitutorial.dialogtutorial;

import ch.nolix.element.configuration.Configuration;
import ch.nolix.element.configuration.DeepConfiguration;
import ch.nolix.element.gui.base.Frame;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.dialog.ErrorDialogCreator;
import ch.nolix.element.gui.widget.Button;

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
