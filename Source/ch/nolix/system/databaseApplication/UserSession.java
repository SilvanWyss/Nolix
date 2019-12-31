//package declaration
package ch.nolix.system.databaseApplication;

import ch.nolix.common.containers.LinkedList;
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.widgets.Button;
import ch.nolix.element.widgets.HorizontalStack;
import ch.nolix.element.widgets.VerticalStack;

//class
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
	
	//method declaration
	protected abstract LinkedList<Button> createLinkButtons();
	
	//method declaration
	protected abstract Widget<?, ?> createSubContentWidget();
	
	//method
	private HorizontalStack createNavigationWidget() {
		return new HorizontalStack(createLinkButtons());
	}
}
