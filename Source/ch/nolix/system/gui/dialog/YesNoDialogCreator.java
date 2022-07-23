//package declaration
package ch.nolix.system.gui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.gui.containerwidget.AligningContainer;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.LabelRole;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.system.gui.widgetgui.LayerRole;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.guiapi.controlrole.ButtonRole;

//class
public final class YesNoDialogCreator {
	
	//method
	public Layer createWithYesNoQuestionAndConfirmAction(final String yesNoQuestion, final IAction confirmAction) {
		
		GlobalValidator.assertThat(yesNoQuestion).thatIsNamed("yes-no-question").isNotBlank();
		GlobalValidator.assertThat(confirmAction).thatIsNamed("confirm action").isNotNull();
		
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
					(final Button b) -> {
						b.getParentLayer().removeSelfFromGUI();
						confirmAction.run();
					}
				)
			)
		);
	}
}
