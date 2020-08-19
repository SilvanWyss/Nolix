//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.element.configuration.Configuration;
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.client.Application;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;
import ch.nolix.system.databaseAdapter.IDatabaseAdapterCreator;
import ch.nolix.system.databaseAdapter.Schema;

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
		final Configuration pGUILook
	) {
		super(
			name,
			LoginSession.class,
			new DatabaseApplicationContext(databaseAdapter, pGUILook)
		);
	}
	
	//constructor
	public DatabaseApplication(
		final String name,
		final IDatabaseAdapterCreator databaseAdapterCreator,
		final Schema schema
	) {
		super(
			name,
			LoginSession.class,
			new DatabaseApplicationContext(databaseAdapterCreator.createDatabaseAdapter(schema))
		);
	}
}
