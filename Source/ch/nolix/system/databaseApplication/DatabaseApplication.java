//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.client.ContextApplication;

//abstract class
public abstract class DatabaseApplication
extends ContextApplication<BackGUIClient, DatabaseApplicationContext> {

	//constructor
	public DatabaseApplication(
		final String name,
		final DatabaseApplicationContext databaseApplicationContext
	) {
		super(
			name,
			databaseApplicationContext,
			BackGUIClient.class,
			LoginSession.class
		);
	}
}
