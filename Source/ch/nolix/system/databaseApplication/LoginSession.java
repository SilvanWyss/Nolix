//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.VerticalStack;

//class
public final class LoginSession extends DatabaseApplicationSession {
	
	//method
	protected VerticalStack createContentWidget() {
		return
		new VerticalStack(
			new Button("Login")
			.setLeftMouseButtonPressCommand(() -> login())
		);
	}
	
	//method
	private void login() {
		getParentClient()
		.pushSession(new HomeSession());
	}
}
