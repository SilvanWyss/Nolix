//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.VerticalStack;

//class
public final class MessageSession extends DatabaseApplicationSession {

	//attribute
	private final String message;
	
	//constructor
	public MessageSession(final String message) {
	
		Validator
		.assertThat(message)
		.thatIsNamed(VariableNameCatalogue.MESSAGE)
		.isNotEmpty();
		
		this.message = message;
	}
	
	//method
	@Override
	protected VerticalStack createContentWidget() {
		return
		new VerticalStack()
		.addWidget(
			new Label()
			.setText(message),
			new Button()
			.setText("Ok")
			.setLeftMouseButtonPressAction(() -> ok())
		);
	}
	
	//method
	private void ok() {
		pop();
	}
}
