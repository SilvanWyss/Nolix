//package declaration
package ch.nolix.systemTutorial.databaseApplicationTutorial.airportManager;

//own imports
import ch.nolix.core.documentNode.FileDocumentNode;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.documentNodeDatabaseAdapter.DocumentNodeDatabaseAdapter;
import ch.nolix.system.documentNodeDatabaseSchemaAdapter.DocumentNodeDatabaseSchemaAdapter;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		
		final var airportManagerDatabasePath = "airportManager.database";
		final var airportManagerDatabase = new FileDocumentNode(airportManagerDatabasePath);
		final var airportManagerSchema = new AirportManagerSchema();
		
		final var airportManagerDatabaseSchemaAdapter =
		new DocumentNodeDatabaseSchemaAdapter(airportManagerDatabase);
		
		if (!airportManagerDatabaseSchemaAdapter.databaseIsInitialized()) {
			
			airportManagerDatabaseSchemaAdapter
			.initializeDatabase()
			.addSchema(airportManagerSchema)
			.saveChanges();
			
			new DataGenerator().generateAndSaveSampleData(
				new DocumentNodeDatabaseAdapter(
					airportManagerDatabase,
					airportManagerSchema
				)
			);
		}
		
		final var airportManager =
		new AirportManager(
			() ->
			new DocumentNodeDatabaseAdapter(
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
