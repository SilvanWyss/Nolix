//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.containerWidget.ContainerRole;
import ch.nolix.element.widget.HorizontalStack;
import ch.nolix.element.widget.ImageWidget;
import ch.nolix.element.widget.Label;
import ch.nolix.element.widget.LabelRole;
import ch.nolix.element.widget.VerticalStack;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;

//class
public abstract class DatabaseApplicationSession extends BackGUIClientSession {
	
	//attribute
	private DatabaseAdapter databaseAdapter;
	
	//method
	@Override
	protected final void initializeStage2() {
		
		createNewDatabaseAdapter();
		
		getRefGUI()
		.addLayerOnTop(
			new VerticalStack(
				createTitleWidget(),
				createContentWidget()
			)
			.setRole(ContainerRole.OverallContainer)
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
		.setRole(LabelRole.Title)
		.setText(getApplicationName());
	}
	
	//method
	private HorizontalStack createTitleWidget() {
		
		final var titleHorizontalStack = new HorizontalStack();
		
		titleHorizontalStack.addWidget(createTitleLabel());
		
		final var parentDatabaseApplication = getParentApplication().as(DatabaseApplication.class);
		if (parentDatabaseApplication.hasPageLogo()) {
			titleHorizontalStack.addWidget(
				new ImageWidget(parentDatabaseApplication.getPageLogo())
			);
		}

		return titleHorizontalStack;
	}
	
	//method
	private boolean hasDatabaseAdapter() {
		return (databaseAdapter != null);
	}
}
