//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.primitive.validator2.Validator;

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
