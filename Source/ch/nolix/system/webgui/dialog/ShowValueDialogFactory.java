//package declaration
package ch.nolix.system.webgui.dialog;

//own imports
import ch.nolix.system.webgui.control.Button;
import ch.nolix.system.webgui.control.Label;
import ch.nolix.system.webgui.linearcontainer.VerticalStack;
import ch.nolix.system.webgui.main.Layer;
import ch.nolix.systemapi.webguiapi.containerapi.ContainerRole;
import ch.nolix.systemapi.webguiapi.controlapi.ButtonRole;
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
				.setLeftMouseButtonPressAction(b -> cancel(b.getOriParentLayer()))
			)
		);
	}
	
	//method
	private void cancel(final ILayer<?> dialogLayer) {
		dialogLayer.removeSelfFromGui();
	}
}
