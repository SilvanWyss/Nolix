//package declaration
package ch.nolix.system.databaseapplication.main;

import ch.nolix.element.gui.base.Widget;
import ch.nolix.element.gui.containerwidget.ContainerRole;
import ch.nolix.element.gui.containerwidget.HorizontalStack;
import ch.nolix.element.gui.containerwidget.VerticalStack;
import ch.nolix.element.gui.widget.ImageWidget;
import ch.nolix.element.gui.widget.Label;
import ch.nolix.element.gui.widget.LabelRole;
import ch.nolix.system.client.guiclient.BackGUIClientSession;
import ch.nolix.system.database.databaseadapter.DatabaseAdapter;

//class
public abstract class DatabaseApplicationSession extends BackGUIClientSession {
	
	//attribute
	private DatabaseAdapter databaseAdapter;
	
	//method
	@Override
	protected final void initializeBaseBackGUIClientSession() {
		
		createNewDatabaseAdapter();
		
		getRefGUI()
		.addLayerOnTop(
			new VerticalStack()
			.addWidget(
				createTitleWidget(),
				createContentWidget()
			)
			.setRole(ContainerRole.OVERALL_CONTAINTER)
		);
		
		if (getRefApplicationContextAs(DatabaseApplicationContext.class).hasGUILook()) {
			getRefGUI().setConfiguration(
				getRefApplicationContextAs(DatabaseApplicationContext.class).getGUILook()
			);
		}
	}
	
	//method declaration
	protected abstract Widget<?, ?> createContentWidget();
	
	//method
	protected final DatabaseAdapter getRefDatabaseAdapter() {
		
		if (!hasDatabaseAdapter()) {
			createNewDatabaseAdapter();
		}
		
		return databaseAdapter;
	}
	
	//method
	private void createNewDatabaseAdapter() {
		databaseAdapter = getRefApplicationContextAs(DatabaseApplicationContext.class).createDatabaseAdapter();
	}
	
	//method
	private Label createTitleLabel() {
		return
		new Label()
		.setRole(LabelRole.TITLE)
		.setText(getApplicationName());
	}
	
	//method
	private HorizontalStack createTitleWidget() {
		
		final var titleHorizontalStack = new HorizontalStack();
		
		titleHorizontalStack.addWidget(createTitleLabel());
		
		final var parentDatabaseApplication = getParentApplication().as(DatabaseApplication.class);
		if (parentDatabaseApplication.hasPageLogo()) {
			titleHorizontalStack.addWidget(
				new ImageWidget().setImage(parentDatabaseApplication.getPageLogo())
			);
		}

		return titleHorizontalStack;
	}
	
	//method
	private boolean hasDatabaseAdapter() {
		return (databaseAdapter != null);
	}
}
