//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.element.GUI.Label;
import ch.nolix.element.GUI.LabelRole;
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
	protected final Label createTitleLabel() {
		return
		new Label(getRefContext().getTitle())
		.setRole(LabelRole.Title);
	}
	
	//method
	protected final DatabaseAdapter getRefDatabaseAdapter() {
		
		if (!hasDatabaseAdapter()) {
			databaseAdapter = getRefContext().createDatabaseAdapter();
		}
		
		return databaseAdapter;
	}
	
	//method
	private boolean hasDatabaseAdapter() {
		return (databaseAdapter != null);
	}
}
