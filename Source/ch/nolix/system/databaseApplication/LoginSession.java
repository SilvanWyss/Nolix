//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.VerticalStack;

//class
public final class LoginSession extends DatabaseApplicationSession {
	
	//method
	public void Login() {
		getParentClient()
		.setSession(new HomeSession());
	}
	
	//method
	protected VerticalStack createContentWidget() {
		return
		new VerticalStack(
			new Button("Login")
			.setLeftMouseButtonPressCommand(() -> Login())
		);
	}
}
