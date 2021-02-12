//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.containerwidget.AligningContainer;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;

//class
public final class ErrorDialogCreator {
	
	//static method
	private static String getErrorMessageOf(final Exception exception) {
		
		Validator.assertThat(exception).thatIsNamed(Exception.class).isNotNull();
		
		final var exceptionName = exception.getClass().getSimpleName();
		
		final var message = exception.getMessage();
		if (message == null || message.isBlank()) {
			return exceptionName;
		}
		
		return (exceptionName + ": " + message);
	}
	
	//method
	public Layer createWithErrorMessage(final String errorMessage) {
		
		Validator.assertThat(errorMessage).thatIsNamed(VariableNameCatalogue.ERROR_MESSAGE).isNotBlank();
		
		return
		new Layer()
		.setRootWidget(
			new AligningContainer()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.setOnTop(
				new Label()
				.setRole(LabelRole.ERROR_TEXT)
				.setText(errorMessage)
			)
			.setOnBottom(
				new Button()
				.setText("Ok")
				.setRole(ButtonRole.CONFIRM_BUTTON)
				.setLeftMouseButtonPressAction(b -> b.getParentLayer().removeSelfFromGUI())
			)
		);
	}
	
	//constructor
	public Layer createWithException(final Exception exception) {
		return createWithErrorMessage(getErrorMessageOf(exception));
	}
}
