//package declaration
package ch.nolix.tutorialApplication.movieManager;

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
		
		final var movieManagerDatabasePath = "movieManagerDatabase.database";
		final var movieManagerDatabase = new SimplePersistentSpecification(movieManagerDatabasePath);
		final var movieManagerSchema = new MovieManagerSchema();
		
		final var movieManagerDatabaseSchemaConnector =
		new DatabaseSchemaAdapter(new SpecificationDatabaseSchemaConnector(movieManagerDatabase));
		
		if (!movieManagerDatabaseSchemaConnector.containsEntitySet()) {
			
			movieManagerDatabaseSchemaConnector
			.addSchema(movieManagerSchema)
			.saveChanges();
		
			new DataGenerator().generateAndSaveSampleData(
				new DatabaseAdapter(
					new SpecificationDatabaseConnector(movieManagerDatabase),
					movieManagerSchema
				)
			);
		}
		
		final var movieManager =
		new MovieManager(
			() ->
			new DatabaseAdapter(
				new SpecificationDatabaseConnector(movieManagerDatabase),
				movieManagerSchema
			),
			new AnthrazitGUILook()
		);
		
		new FrontGUIClient(movieManager);
	}
	
	//private constructor
	private Launcher() {}
}
