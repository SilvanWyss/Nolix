//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.element.configuration.StandardConfiguration;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.client.Application;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;

//class
public abstract class DatabaseApplication extends Application<BackGUIClient> {
	
	//constructor
	public DatabaseApplication(final String name, final DatabaseAdapter databaseAdapter) {
		super(
			name,
			LoginSession.class,
			new DatabaseApplicationContext(databaseAdapter)
		);
	}
	
	//constructor
	public DatabaseApplication(
		final String name,
		final DatabaseAdapter databaseAdapter,
		final StandardConfiguration pGUILook
	) {
		super(
			name,
			LoginSession.class,
			new DatabaseApplicationContext(databaseAdapter, pGUILook)
		);
	}
}
