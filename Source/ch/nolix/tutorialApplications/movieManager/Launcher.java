//package declaration
package ch.nolix.tutorialApplications.movieManager;

//own imports
import ch.nolix.core.documentNode.FileDocumentNode;
import ch.nolix.system.GUIClient.FrontGUIClient;
import ch.nolix.system.documentNodeDatabaseAdapter.DocumentNodeDatabaseAdapter;
import ch.nolix.system.documentNodeDatabaseSchemaAdapter.DocumentNodeDatabaseSchemaAdapter;
import ch.nolix.templates.GUILooks.AnthrazitGUILook;

//class
public final class Launcher {

	//main method
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var movieManagerDatabasePath = "movieManagerDatabase.database";
		final var movieManagerDatabase = new FileDocumentNode(movieManagerDatabasePath);
		final var movieManagerSchema = new MovieManagerSchema();
		
		final var movieManagerDatabaseSchemaAdapter =
		new DocumentNodeDatabaseSchemaAdapter(movieManagerDatabase);
		
		if (!movieManagerDatabaseSchemaAdapter.containsEntitySet()) {
			
			movieManagerDatabaseSchemaAdapter
			.addSchema(movieManagerSchema)
			.saveChanges();
		
			new DataGenerator().generateAndSaveSampleData(
				new DocumentNodeDatabaseAdapter(
					movieManagerDatabase,
					movieManagerSchema
				)
			);
		}
		
		final var movieManager =
		new MovieManager(
			() ->
			new DocumentNodeDatabaseAdapter(
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
