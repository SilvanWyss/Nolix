//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.airportManager;

import ch.nolix.core.fileNode.FileNode;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.fileNodeDatabaseAdapter.FileNodeDatabaseAdapter;
import ch.nolix.system.fileNodeDatabaseSchemaAdapter.FileNodeDatabaseSchemaAdapter;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		
		final var airportManagerDatabasePath = "airportManager.database";
		final var airportManagerDatabase = new FileNode(airportManagerDatabasePath);
		final var airportManagerSchema = new AirportManagerSchema();
		
		final var airportManagerDatabaseSchemaAdapter =
		new FileNodeDatabaseSchemaAdapter(airportManagerDatabase);
		
		if (!airportManagerDatabaseSchemaAdapter.databaseIsInitialized()) {
			
			airportManagerDatabaseSchemaAdapter
			.initializeDatabase()
			.addSchema(airportManagerSchema)
			.saveChanges();
			
			new DataGenerator().generateAndSaveSampleData(
				new FileNodeDatabaseAdapter(
					airportManagerDatabase,
					airportManagerSchema
				)
			);
		}
		
		final var airportManager =
		new AirportManager(
			() ->
			new FileNodeDatabaseAdapter(
				airportManagerDatabase,
				airportManagerSchema
			),
			new AnthrazitGUILook()
		);
		
		new FrontGUIClient(airportManager);
	}
	
	//private constructor
	private Launcher() {}
}
