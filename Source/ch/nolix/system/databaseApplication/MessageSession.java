//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator.Validator;
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
		.suppose(message)
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
		getParentClient().popSession();
	}
}
