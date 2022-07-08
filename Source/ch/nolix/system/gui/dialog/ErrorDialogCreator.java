//package declaration
package ch.nolix.system.gui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.system.gui.containerwidget.AligningContainer;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.main.LayerRole;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.ButtonRole;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widget.LabelRole;
import ch.nolix.system.gui.widgetgui.Layer;

//class
public final class ErrorDialogCreator {
	
	//static method
	private static String getErrorMessageOf(final Exception exception) {
		
		GlobalValidator.assertThat(exception).thatIsNamed(Exception.class).isNotNull();
		
		final var exceptionName = exception.getClass().getSimpleName();
		
		final var message = exception.getMessage();
		if (message == null || message.isBlank()) {
			return exceptionName;
		}
		
		return (exceptionName + ": " + message);
	}
	
	//method
	public Layer createWithErrorMessage(final String errorMessage) {
		
		GlobalValidator.assertThat(errorMessage).thatIsNamed(LowerCaseCatalogue.ERROR_MESSAGE).isNotBlank();
		
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
