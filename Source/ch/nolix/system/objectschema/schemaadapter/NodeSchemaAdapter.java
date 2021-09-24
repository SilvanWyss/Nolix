//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.environment.filesystem.FileAccessor;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class NodeSchemaAdapter extends SchemaAdapter {
	
	//attribute
	private final BaseNode databaseNode;
	
	//static method
	public static NodeSchemaAdapter forDatabaseNode(final String databaseName, final BaseNode databaseNode) {
		return new NodeSchemaAdapter(databaseName, databaseNode);
	}
	
	//static method
	public static NodeSchemaAdapter forDatabaseNodeInFile(final String filePath) {
		
		final var databaseName = new FileAccessor(filePath).getName().split(".")[0];
		final var databaseNode = Node.fromFile(filePath);
		
		return new NodeSchemaAdapter(databaseName, databaseNode);
	}
	
	//constructor
	private NodeSchemaAdapter(final String databaseName, final BaseNode databaseNode) {
		
		super(databaseName);
		
		Validator.assertThat(databaseNode).thatIsNamed("database Node").isNotNull();
		
		this.databaseNode = databaseNode;
		
		initializeSession();
	}
	
	//method
	@Override
	protected ISchemaAdapter createIntermediateSchemaAdapter() {
		return ch.nolix.system.nodeintermediateschema.schemaadapter.SchemaAdapter.forDatabaseNode(databaseNode);
	}
}
