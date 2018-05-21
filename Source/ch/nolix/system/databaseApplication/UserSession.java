//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.GUI.Button;
import ch.nolix.element.GUI.HorizontalStack;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.Widget;

//abstract class
public abstract class UserSession extends DatabaseApplicationSession {

	//constructor
	public UserSession(
		final DatabaseApplicationContext databaseApplicationContext
	) {
		super(databaseApplicationContext);
	}
	
	//method
	protected final VerticalStack createContentWidget() {	
		return
		new VerticalStack(
			createNavigationWidget(),
			createSubContentWidget()
		);
	}
	
	//abstract method
	protected abstract List<Button> createLinkButtons();
	
	//abstract method
	protected abstract Widget<?, ?> createSubContentWidget();
	
	//method
	private HorizontalStack createNavigationWidget() {
		return new HorizontalStack(createLinkButtons());
	}
}
