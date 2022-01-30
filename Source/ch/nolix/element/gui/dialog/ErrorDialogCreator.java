//package declaration
package ch.nolix.element.gui.dialog;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.element.gui.base.Layer;
import ch.nolix.element.gui.base.LayerRole;
import ch.nolix.element.gui.containerwidget.AligningContainer;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.ButtonRole;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.LabelRole;

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
		
		Validator.assertThat(errorMessage).thatIsNamed(LowerCaseCatalogue.ERROR_MESSAGE).isNotBlank();
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
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
