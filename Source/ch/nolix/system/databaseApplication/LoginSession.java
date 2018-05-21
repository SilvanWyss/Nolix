//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.VerticalStack;

//class
public final class LoginSession extends DatabaseApplicationSession {

	//constructor
	public LoginSession(
		final DatabaseApplicationContext databaseApplicationContext
	) {
		super(databaseApplicationContext);
	}
	
	//method
	public void Login() {
		getRefClient()
		.setSession(new HomeSession(getRefContext()));
	}
	
	//method
	protected VerticalStack createContentWidget() {
		return
		new VerticalStack(
			new Button("Login")
			.setLeftMouseButtonPressCommand("Login")
		);
	}
}
