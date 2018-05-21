//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.LabelRole;
import ch.nolix.element.GUI.VerticalStack;
import ch.nolix.element.GUI.Widget;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.client.ContextSession;

//abstract class
public abstract class DatabaseApplicationSession
extends ContextSession<BackGUIClient, DatabaseApplicationContext> {
	
	//attribute
	private DatabaseAdapter databaseAdapter;
	
	//constructor
	public DatabaseApplicationSession(
		final DatabaseApplicationContext databaseApplicationContext
	) {
		super(databaseApplicationContext);
	}
	
	//method
	public final void initialize() {
		
		getRefClient()
		.getRefGUI()
		.setTitle(getRefContext().getTitle())
		.setRootWidget(
			new VerticalStack(
				createTitleWidget(),
				createContentWidget()
			)
		);
		
		if (getRefContext().hasGUILook()) {
			getRefClient().getRefGUI().setConfiguration(
				getRefContext().getGUILook()
			);
		}
	}
	
	//abstract method
	protected abstract Widget<?, ?> createContentWidget();
	
	//method
	protected final DatabaseAdapter getRefDatabaseAdapter() {
		
		if (!hasDatabaseAdapter()) {
			databaseAdapter = getRefContext().createDatabaseAdapter();
		}
		
		return databaseAdapter;
	}
	
	//method
	private Label createTitleWidget() {
		return
		new Label()
		.setText(getRefContext().getTitle())
		.setRole(LabelRole.Title);
	}
	
	//method
	private boolean hasDatabaseAdapter() {
		return (databaseAdapter != null);
	}
}
