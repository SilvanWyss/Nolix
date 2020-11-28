//package declaration
package ch.nolix.element.dialog;

import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.containerwidget.ContainerRole;
import ch.nolix.element.gui.Layer;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.element.widget.VerticalStack;

//class
public final class YesNoDialog extends Layer {
	
	//constructor
	public YesNoDialog(final String yesNoQuestion, final IAction confirmAction) {
		
		Validator.assertThat(yesNoQuestion).thatIsNamed("yes-no-question").isNotBlank();
		Validator.assertThat(confirmAction).thatIsNamed("confirm action").isNotNull();
		
		setRootWidget(
			new VerticalStack()
			.setRole(ContainerRole.DIALOG_CONTAINER)
			.addWidget(
				new Label()
				.setRole(LabelRole.MAIN_TEXT)
				.setText(yesNoQuestion),
				new HorizontalStack()
				.addWidget(
					new Button()
					.setRole(ButtonRole.CANCEL_BUTTON)
					.setText("No")
					.setLeftMouseButtonPressAction(l -> l.getParentGUI().removeLayer(this)),
					new Button()
					.setRole(ButtonRole.CONFIRM_BUTTON)
					.setText("Yes")
					.setLeftMouseButtonPressAction(
						l -> {
							l.getParentGUI().removeLayer(this);
							confirmAction.run();
						}
					)
				)
			)
		);
	}
}
