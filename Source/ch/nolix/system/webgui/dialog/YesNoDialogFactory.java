//package declaration
package ch.nolix.system.webgui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Text;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.guiapi.controlrole.ButtonRole;
import ch.nolix.systemapi.webguiapi.controlapi.IButton;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class YesNoDialogFactory {
	
	//static attribute
	public static final YesNoDialogFactory INSTANCE = new YesNoDialogFactory();
	
	//constructor
	private YesNoDialogFactory() {}
	
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
				new Text()
				.setText(yesNoQuestion),
				new HorizontalStack()
				.addControl(
					new Button()
					.setRole(ButtonRole.CANCEL_BUTTON)
					.setText("No")
					.setLeftMouseButtonPressAction(b -> b.getRefParentLayer().removeSelfFromGUI()),
					new Button()
					.setRole(ButtonRole.CONFIRM_BUTTON)
					.setText("Yes")
					.setLeftMouseButtonPressAction(
						(final IButton<?, ?> b) -> {
							b.getRefParentLayer().removeSelfFromGUI();
							confirmAction.run();
						}
					)
				)
			)
		);
	}
}
