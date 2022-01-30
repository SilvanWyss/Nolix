//package declaration
package ch.nolix.system.databaseapplication.main;

import ch.nolix.core.container.LinkedList;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Button;
import ch.nolix.element.gui.widget.Widget;

//class
public abstract class UserSession extends DatabaseApplicationSession {
	
	//method
	@Override
	protected final VerticalStack createContentWidget() {
		return
		new VerticalStack()
		.add(
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
		return
		new HorizontalStack()
		.addWidgets(createLinkButtons());
	}
}
