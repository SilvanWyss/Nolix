//package declaration
package ch.nolix.tutorialApplication.airportManager;

//own imports
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.specification.SimplePersistentSpecification;
import ch.nolix.core.specificationDatabaseConnector.SpecificationDatabaseConnector;
import ch.nolix.core.specificationDatabaseSchemaConnector.SpecificationDatabaseSchemaConnector;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;

//class
public final class Launcher {

	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var airportManagerDatabasePath = "airportManagerDatabase.database";
		final var airportManagerDatabase = new SimplePersistentSpecification(airportManagerDatabasePath);
		final var airportManagerSchema = new AirportManagerSchema();
		
		final var airportManagerDatabaseSchemaConnector =
		new DatabaseSchemaAdapter(new SpecificationDatabaseSchemaConnector(airportManagerDatabase));
		
		if (!airportManagerDatabaseSchemaConnector.containsEntitySet()) {
			
			airportManagerDatabaseSchemaConnector
			.addSchema(airportManagerSchema)
			.saveChanges();
		
			new DataGenerator().generateAndSaveSampleData(
				new DatabaseAdapter(
					new SpecificationDatabaseConnector(airportManagerDatabase),
					airportManagerSchema
				)
			);
		}
		
		final var airportManager =
		new AirportManager(
			() ->
			new DatabaseAdapter(
				new SpecificationDatabaseConnector(airportManagerDatabase),
				airportManagerSchema
			),
			new AnthrazitGUILook()
		);
		
		new FrontGUIClient(airportManager);
	}
	
	//private constructor
	private Launcher() {}
}
