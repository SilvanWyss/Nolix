//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.containerwidget.VerticalStack;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.gui.LayerRole;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.Label;

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
