//package declaration
package ch.nolix.template.webgui.dialog;

//own imports
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;
import ch.nolix.system.webgui.atomiccontrol.Button;
import ch.nolix.system.webgui.atomiccontrol.Label;
import ch.nolix.system.webgui.linearcontainer.HorizontalStack;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.atomiccontrolapi.ButtonRole;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.mainapi.ILayer;
import ch.nolix.systemapi.webguiapi.mainapi.LayerRole;

//class
public final class ShowValueDialogFactory {
	
	//method
	public Layer createShowValueDialogForValueNameAndValue(final String valueName, final String value) {
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootControl(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addControl(
				new Label()
				.setText(valueName),
				new Label()
				.setText(value),
				new Button()
				.setRole(ButtonRole.CONFIRM_BUTTON)
				.setText("Ok")
				.setLeftMouseButtonPressAction(b -> cancel(b.getStoredParentLayer()))
			)
		);
	}
	
	//method
	public Layer createShowValueDialogForValueNameAndValueAndValueCopier(
		final String valueName,
		final String value,
		final IElementTaker<String> valueCopier
	) {
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootControl(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addControl(
				new Label()
				.setText(valueName),
				new HorizontalStack()
				.addControl(
					new Label()
					.setText(value),
					new Button()
					.setText("Copy")
					.setLeftMouseButtonPressAction(() -> valueCopier.run(value))
				),
				new Button()
				.setRole(ButtonRole.CONFIRM_BUTTON)
				.setText("Ok")
				.setLeftMouseButtonPressAction(b -> cancel(b.getStoredParentLayer()))
			)
		);
	}
	
	//method
	private void cancel(final ILayer<?> dialogLayer) {
		dialogLayer.removeSelfFromGui();
	}
}
