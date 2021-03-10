//package declaration
package ch.nolix.system.databaseapplication.main;

//own imports
import ch.nolix.common.container.LinkedList;
import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.Button;

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
