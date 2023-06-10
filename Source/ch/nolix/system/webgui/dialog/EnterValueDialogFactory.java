//package declaration
package ch.nolix.system.webgui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.control.Textbox;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.controlapi.IButton;
import ch.nolix.systemapi.webguiapi.controlapi.ILabel;
import ch.nolix.systemapi.webguiapi.controlapi.ITextbox;
import ch.nolix.systemapi.webguiapi.controlapi.LabelRole;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class EnterValueDialogFactory {
	
	//method
	public Layer createEnterValueDialogWithTextAndOriginalValueAndValueTaker(
		final String text,
		final String originalValue,
		final IElementTaker<String> valueTaker
	) {
		
		GlobalValidator.assertThat(valueTaker).thatIsNamed("value taker").isNotNull();
		
		final var valueTextbox = new Textbox().setText(originalValue);
		final var errorLabel = new Label().setRole(LabelRole.ERROR_LABEL);
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootControl(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addControl(
				new Label()
				.setText(text),
				errorLabel,
				valueTextbox,
				new HorizontalStack()
				.addControl(
					new Button()
					.setRole(ButtonRole.CANCEL_BUTTON)
					.setText("Cancel")
					.setLeftMouseButtonPressAction(b -> b.getOriParentLayer().removeSelfFromGUI()),
					new Button()
					.setRole(ButtonRole.CONFIRM_BUTTON)
					.setText("Ok")
					.setLeftMouseButtonPressAction(b -> confirmNewValue(b, valueTextbox, errorLabel, valueTaker))
				)
			)
		);
	}
	
	//method
	private void confirmNewValue(
		final IButton<?, ?> confirmButton,
		final ITextbox valueTextbox,
		final ILabel<?, ?> errorLabel,
		final IElementTaker<String> valueTaker
	) {
		
		final var newValue = valueTextbox.getText();
		
		try {
			
			valueTaker.run(newValue);
			
			confirmButton.getOriParentLayer().removeSelfFromGUI();
		} catch (final Exception exception) {
			
			final var message = exception.getMessage();
			
			if (message != null) {
				errorLabel.setText(message);
			} else {
				errorLabel.setText("An error occured.");
			}
		}
	}
}
