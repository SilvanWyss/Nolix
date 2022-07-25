//package declaration
package ch.nolix.system.gui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.Label;
import ch.nolix.system.gui.widgetgui.Layer;
import ch.nolix.systemapi.guiapi.containercontrolproperty.ContainerRole;
import ch.nolix.systemapi.guiapi.controlrole.ButtonRole;
import ch.nolix.systemapi.guiapi.widgetguiapi.LayerRole;

//class
public final class InfoDialogCreator {
	
	//method
	public Layer createInfoDialogWithInfo(final String info) {
		
		GlobalValidator.assertThat(info).thatIsNamed("info").isNotBlank();
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addWidget(
				new Label()
				.setText(info),
				new Button()
				.setRole(ButtonRole.CONFIRM_BUTTON)
				.setText("Ok")
				.setLeftMouseButtonPressAction(b -> b.getParentLayer().removeSelfFromGUI())
			)
		);
	}
}
