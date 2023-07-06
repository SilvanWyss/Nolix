//package declaration
package ch.nolix.system.webgui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.atomiccontrol.Textbox;
import ch.nolix.system.webgui.atomiccontrol.ValidationLabel;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.IButton;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ITextbox;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
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
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootControl(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addControl(
				new Label()
				.setText(text),
				new ValidationLabel(),
				valueTextbox,
				new HorizontalStack()
				.addControl(
					new Button()
					.setRole(ButtonRole.CANCEL_BUTTON)
					.setText("Cancel")
					.setLeftMouseButtonPressAction(b -> cancel(b.getOriParentLayer())),
					new Button()
					.setRole(ButtonRole.CONFIRM_BUTTON)
					.setText("Ok")
					.setLeftMouseButtonPressAction(b -> confirmNewValue(b, valueTextbox, valueTaker))
				)
			)
		);
	}
	
	//method
	private void cancel(final ILayer<?> dialogLayer) {
		dialogLayer.removeSelfFromGui();
	}
	
	//method
	private void confirmNewValue(
		final IButton confirmButton,
		final ITextbox valueTextbox,
		final IElementTaker<String> valueTaker
	) {
		
		final var newValue = valueTextbox.getText();
		
		valueTaker.run(newValue);
		
		confirmButton.getOriParentLayer().removeSelfFromGui();
	}
}
