//package declaration
package ch.nolix.system.databaseapplication.main;

import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.ButtonRole;

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
