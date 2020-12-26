//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.containerwidget.AligningContainer;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;

//class
public final class YesNoDialog extends Layer {
	
	//constructor
	public YesNoDialog(final String yesNoQuestion, final IAction confirmAction) {
		
		Validator.assertThat(yesNoQuestion).thatIsNamed("yes-no-question").isNotBlank();
		Validator.assertThat(confirmAction).thatIsNamed("confirm action").isNotNull();
		
		setRootWidget(
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
				.setLeftMouseButtonPressAction(this::removeSelfFromGUI)
			)
			.setOnBottomRight(
				new Button()
				.setRole(ButtonRole.CONFIRM_BUTTON)
				.setText("Yes")
				.setLeftMouseButtonPressAction(
					l -> {
						removeSelfFromGUI();
						confirmAction.run();
					}
				)
			)
		);
	}
}
