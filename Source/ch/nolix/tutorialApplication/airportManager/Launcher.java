//package declaration
package ch.nolix.tutorialApplication.airportManager;

//own imports
import ch.nolix.core.databaseAdapter.DatabaseAdapter;
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.specification.StandardSpecification;
import ch.nolix.core.specificationDatabaseConnector.SpecificationDatabaseConnector;
import ch.nolix.core.specificationDatabaseSchemaConnector.SpecificationDatabaseSchemaConnector;
import ch.nolix.system.GUIClient.FrontGUIClient;

//class
public final class Launcher {

	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var airportManagerDatabase = new StandardSpecification();				
		final var airportManagerSchema = new AirportManagerSchema();
		
		new DatabaseSchemaAdapter(new SpecificationDatabaseSchemaConnector(airportManagerDatabase))
		.addSchema(airportManagerSchema)
		.saveChanges();		
		
		final var airportManager =
		new AirportManager(
			() ->
			new DatabaseAdapter(
				new SpecificationDatabaseConnector(airportManagerDatabase),
				airportManagerSchema
			)
		);
		
		new FrontGUIClient(airportManager);
	}
	
	//private constructor
	private Launcher() {}
}
