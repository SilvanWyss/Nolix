//package declaration
package ch.nolix.system.webgui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.controlapi.IButton;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class YesNoDialogFactory {
	
	//method
	public Layer createYesNoDialogWithYesNoQuestionAndConfirmAction(
		final String yesNoQuestion,
		final IAction confirmAction
	) {
		
		GlobalValidator.assertThat(yesNoQuestion).thatIsNamed("yes-no-question").isNotBlank();
		GlobalValidator.assertThat(confirmAction).thatIsNamed("confirm action").isNotNull();
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootControl(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addControl(
				new Label()
				.setText(yesNoQuestion),
				new HorizontalStack()
				.addControl(
					new Button()
					.setRole(ButtonRole.CANCEL_BUTTON)
					.setText("No")
					.setLeftMouseButtonPressAction(b -> b.getOriParentLayer().removeSelfFromGui()),
					new Button()
					.setRole(ButtonRole.CONFIRM_BUTTON)
					.setText("Yes")
					.setLeftMouseButtonPressAction(
						(final IButton b) -> {
							b.getOriParentLayer().removeSelfFromGui();
							confirmAction.run();
						}
					)
				)
			)
		);
	}
}
