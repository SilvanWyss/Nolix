//package declaration
package ch.nolix.tutorialApplications.movieManager;

//own imports
import ch.nolix.core.databaseSchemaAdapter.DatabaseSchemaAdapter;
import ch.nolix.core.documentNode.SimplePersistentDocumentNode;
import ch.nolix.core.specificationDatabaseAdapter.SpecificationDatabaseAdapter;
import ch.nolix.core.specificationDatabaseSchemaConnector.SpecificationDatabaseSchemaConnector;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;

//class
public final class Launcher {

	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var movieManagerDatabasePath = "movieManagerDatabase.database";
		final var movieManagerDatabase = new SimplePersistentDocumentNode(movieManagerDatabasePath);
		final var movieManagerSchema = new MovieManagerSchema();
		
		final var movieManagerDatabaseSchemaConnector =
		new DatabaseSchemaAdapter(new SpecificationDatabaseSchemaConnector(movieManagerDatabase));
		
		if (!movieManagerDatabaseSchemaConnector.containsEntitySet()) {
			
			movieManagerDatabaseSchemaConnector
			.addSchema(movieManagerSchema)
			.saveChanges();
		
			new DataGenerator().generateAndSaveSampleData(
				new SpecificationDatabaseAdapter(
					movieManagerDatabase,
					movieManagerSchema
				)
			);
		}
		
		final var movieManager =
		new MovieManager(
			() ->
			new SpecificationDatabaseAdapter(
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
