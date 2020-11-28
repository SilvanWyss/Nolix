//package declaration
package ch.nolix.element.dialog;

//own imports
import ch.nolix.common.validator.Validator;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.gui.LayerRole;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.VerticalStack;

//class
public final class InfoDialog extends Layer {
	
	//constructor
	public InfoDialog(final String info) {
		
		Validator.assertThat(info).thatIsNamed("info").isNotBlank();
		
		setRole(LayerRole.DIALOG_LAYER);
		
		setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.DialogContainer)
			.addWidget(
				new Label()
				.setText(info),
				new Button()
				.setRole(ButtonRole.ConfirmButton)
				.setText("Ok")
				.setLeftMouseButtonPressAction(this::removeSelfFromGUI)
			)
		);
	}
}
