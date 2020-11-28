//package declaration
package ch.nolix.system.databaseapplication;

//own imports
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.ButtonRole;
import ch.nolix.element.widget.VerticalStack;

//class
public final class LoginSession extends DatabaseApplicationSession {
	
	//method
	@Override
	protected VerticalStack createContentWidget() {
		return
		new VerticalStack()
		.addWidget(
			new Button()
			.setRole(ButtonRole.ACTION_BUTTON)
			.setText("Login")
			.setLeftMouseButtonPressAction(this::login)
		);
	}
	
	//method
	private void login() {
		push(new HomeSession());
	}
}
