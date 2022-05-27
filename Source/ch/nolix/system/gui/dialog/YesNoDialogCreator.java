//package declaration
package ch.nolix.system.gui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.functionuniversalapi.IAction;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.system.gui.base.LayerRole;
import ch.nolix.system.gui.containerwidget.AligningContainer;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.ButtonRole;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.LabelRole;

//class
public final class YesNoDialogCreator {
	
	//method
	public Layer createWithYesNoQuestionAndConfirmAction(final String yesNoQuestion, final IAction confirmAction) {
		
		Validator.assertThat(yesNoQuestion).thatIsNamed("yes-no-question").isNotBlank();
		Validator.assertThat(confirmAction).thatIsNamed("confirm action").isNotNull();
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootWidget(
			new AligningContainer()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.setOnTop(
				new Label()
				.setRole(LabelRole.MAIN_TEXT)
				.setText(yesNoQuestion)
			)
			.setOnBottomLeft(
				new Button()
				.setRole(ButtonRole.CANCEL_BUTTON)
				.setText("No")
				.setLeftMouseButtonPressAction(b -> b.getParentLayer().removeSelfFromGUI())
			)
			.setOnBottomRight(
				new Button()
				.setRole(ButtonRole.CONFIRM_BUTTON)
				.setText("Yes")
				.setLeftMouseButtonPressAction(
					b -> {
						b.getParentLayer().removeSelfFromGUI();
						confirmAction.run();
					}
				)
			)
		);
	}
}
