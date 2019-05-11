//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.element.widget.Button;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.element.widget.Widget;

//abstract class
public abstract class UserSession extends DatabaseApplicationSession {
	
	//method
	@Override
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
