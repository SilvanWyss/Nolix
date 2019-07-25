//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.ButtonRole;
import ch.nolix.element.widgets.VerticalStack;

//class
public final class LoginSession extends DatabaseApplicationSession {
	
	//method
	@Override
	protected VerticalStack createContentWidget() {
		return
		new VerticalStack(
			new Button("Login")
			.setRole(ButtonRole.ActionButton)
			.setLeftMouseButtonPressCommand(() -> login())
		);
	}
	
	//method
	private void login() {
		getParentClient()
		.pushSession(new HomeSession());
	}
}
