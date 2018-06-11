//package declaration
package ch.nolix.system.databaseApplication;

//own imports
import ch.nolix.system.GUIClient.BackGUIClient;
import ch.nolix.system.client.Application;

//abstract class
public abstract class DatabaseApplication
extends Application<BackGUIClient> {

	//constructor
	public DatabaseApplication(
		final String name,
		final DatabaseApplicationContext context
	) {
		super(
			name,
			BackGUIClient.class,
			LoginSession.class,
			context
		);
	}
}
