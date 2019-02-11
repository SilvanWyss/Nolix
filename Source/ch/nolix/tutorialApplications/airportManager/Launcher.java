//package declaration
package ch.nolix.tutorialApplications.airportManager;

//own imports
import ch.nolix.core.documentNode.SimplePersistentDocumentNode;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.documentNodeDatabaseAdapter.DocumentNodeDatabaseAdapter;
import ch.nolix.system.documentNodeDatabaseSchemaAdapter.DocumentNodeDatabaseSchemaAdapter;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;

//class
public final class Launcher {

	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var airportManagerDatabasePath = "airportManagerDatabase.database";
		final var airportManagerDatabase = new SimplePersistentDocumentNode(airportManagerDatabasePath);
		final var airportManagerSchema = new AirportManagerSchema();
		
		final var airportManagerDatabaseSchemaAdapter =
		new DocumentNodeDatabaseSchemaAdapter(airportManagerDatabase);
		
		if (!airportManagerDatabaseSchemaAdapter.containsEntitySet()) {
			
			airportManagerDatabaseSchemaAdapter
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
