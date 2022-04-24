//package declaration
package ch.nolix.system.gui.dialog;

//own imports
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.system.gui.base.Layer;
import ch.nolix.system.gui.base.LayerRole;
import ch.nolix.system.gui.containerwidget.ContainerRole;
import ch.nolix.system.gui.containerwidget.VerticalStack;
import ch.nolix.system.gui.widget.Button;
import ch.nolix.system.gui.widget.ButtonRole;
import ch.nolix.system.gui.widget.Label;

//class
public final class InfoDialogCreator {
	
	//method
	public Layer createInfoDialogWithInfo(final String info) {
		
		Validator.assertThat(info).thatIsNamed("info").isNotBlank();
		
		return
		new Layer()
		.setRole(LayerRole.DIALOG_LAYER)
		.setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.add(
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
