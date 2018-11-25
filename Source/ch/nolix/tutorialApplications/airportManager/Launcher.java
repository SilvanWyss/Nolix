//package declaration
package ch.nolix.tutorialApplications.airportManager;

//own imports
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.documentNode.SimplePersistentDocumentNode;
import ch.nolix.core.documentNodeDatabaseAdapter.SpecificationDatabaseAdapter;
import ch.nolix.core.documentNodeDatabaseSchemaAdapter.SpecificationDatabaseSchemaAdapter;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;

//class
public final class Launcher {

	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var airportManagerDatabasePath = "airportManagerDatabase.database";
		final var airportManagerDatabase = new SimplePersistentDocumentNode(airportManagerDatabasePath);
		final var airportManagerSchema = new AirportManagerSchema();
		
		final var airportManagerDatabaseSchemaConnector =
		new DatabaseSchemaAdapter(new SpecificationDatabaseSchemaAdapter(airportManagerDatabase));
		
		if (!airportManagerDatabaseSchemaConnector.containsEntitySet()) {
			
			airportManagerDatabaseSchemaConnector
			.addSchema(airportManagerSchema)
			.saveChanges();
		
			new DataGenerator().generateAndSaveSampleData(
				new SpecificationDatabaseAdapter(
					airportManagerDatabase,
					airportManagerSchema
				)
			);
		}
		
		final var airportManager =
		new AirportManager(
			() ->
			new SpecificationDatabaseAdapter(
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
