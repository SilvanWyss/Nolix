//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.movieManager;

import ch.nolix.core.fileNode.FileNode;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.fileNodeDatabaseAdapter.FileNodeDatabaseAdapter;
import ch.nolix.system.fileNodeDatabaseSchemaAdapter.FileNodeDatabaseSchemaAdapter;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		
		final var movieManagerDatabasePath = "movieManager.database";
		final var movieManagerDatabase = new FileNode(movieManagerDatabasePath);
		final var movieManagerSchema = new MovieManagerSchema();
		
		final var movieManagerDatabaseSchemaAdapter =
		new FileNodeDatabaseSchemaAdapter(movieManagerDatabase);
		
		if (!movieManagerDatabaseSchemaAdapter.databaseIsInitialized()) {
			
			movieManagerDatabaseSchemaAdapter
			.initializeDatabase()
			.addSchema(movieManagerSchema)
			.saveChanges();
		
			new DataGenerator().generateAndSaveSampleData(
				new FileNodeDatabaseAdapter(
					movieManagerDatabase,
					movieManagerSchema
				)
			);
		}
		
		final var movieManager =
		new MovieManager(
			() ->
			new FileNodeDatabaseAdapter(
				movieManagerDatabase,
				movieManagerSchema
			),
			new AnthrazitGUILook()
		);
		
		new FrontGUIClient(movieManager);
	}
	
	//private constructor
	private Launcher() {}
}
