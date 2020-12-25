//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.containerwidget.VerticalStack;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;

//class
public final class ErrorDialog extends Layer {
	
	//TODO: public ErrorDialog(final Exception exception) {...
	
	//constructor
	public ErrorDialog(final String errorMessage) {
		
		Validator.assertThat(errorMessage).thatIsNamed(VariableNameCatalogue.ERROR_MESSAGE).isNotBlank();
		
		setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addWidget(
				new Label()
				.setRole(LabelRole.ERROR_TEXT)
				.setText(errorMessage),
				new Button()
				.setText("Ok")
				.setRole(ButtonRole.CONFIRM_BUTTON)
				.setLeftMouseButtonPressAction(this::removeSelfFromGUI)
			)
		);
	}
}
