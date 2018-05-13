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
	public void initialize() {		
		getRefClient()
		.getRefGUI()
		.setRootWidget(
			new VerticalStack(
				createTitleLabel(),
				new Button("Login")
				.setLeftMouseButtonPressCommand("Login")
			)
		);
	}
	
	//method
	public void Login() {
		getRefClient()
		.setSession(new RootSession(getRefContext()));
	}
}
