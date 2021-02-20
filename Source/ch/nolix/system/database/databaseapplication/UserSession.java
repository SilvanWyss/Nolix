//package declaration
package ch.nolix.system.database.databaseapplication;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.containerwidget.HorizontalStack;
import ch.nolix.element.containerwidget.VerticalStack;
import ch.nolix.element.gui.Widget;
import ch.nolix.element.widget.Button;

//class
public abstract class UserSession extends DatabaseApplicationSession {
	
	//method
	@Override
	protected final VerticalStack createContentWidget() {
		return
		new VerticalStack()
		.addWidget(
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
