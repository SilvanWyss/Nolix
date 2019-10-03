//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.element.GUI.Widget;
import ch.nolix.element.containerWidgets.ContainerRole;
import ch.nolix.element.widgets.Label;
import ch.nolix.element.widgets.LabelRole;
import ch.nolix.element.widgets.VerticalStack;
import ch.nolix.system.GUIClient.BackGUIClientSession;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;

//abstract class
public abstract class DatabaseApplicationSession extends BackGUIClientSession {
	
	//attribute
	private DatabaseAdapter databaseAdapter;
	
	//method
	@Override
	public final void initialize() {
		
		createNewDatabaseAdapter();
		
		getRefGUI()
		.setTitle(getApplicationName())
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
	
	//abstract method
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
	private Label createTitleWidget() {
		return
		new Label()
		.setText(getApplicationName())
		.setRole(LabelRole.Title);
	}
	
	//method
	private boolean hasDatabaseAdapter() {
		return (databaseAdapter != null);
	}
}
