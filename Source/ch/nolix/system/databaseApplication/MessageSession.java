//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.VerticalStack;

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
		new VerticalStack(
			new Label(message),
			new Button("Ok")
			.setLeftMouseButtonPressCommand(() -> ok())
		);
	}
	
	//method
	private void ok() {
		pop();
	}
}
