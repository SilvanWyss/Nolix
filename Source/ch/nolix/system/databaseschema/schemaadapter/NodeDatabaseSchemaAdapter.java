//package declaration
package ch.nolix.system.databaseschema.schemaadapter;

//own imports
import ch.nolix.common.document.node.BaseNode;
import ch.nolix.common.document.node.Node;
import ch.nolix.common.environment.filesystem.FileAccessor;
import ch.nolix.common.errorcontrol.validator.Validator;
import ch.nolix.techapi.intermediateschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class NodeDatabaseSchemaAdapter extends SchemaAdapter {
	
	//attribute
	private final BaseNode databaseNode;
	
	//static method
	public static NodeDatabaseSchemaAdapter forDatabaseNode(final String databaseName, final BaseNode databaseNode) {
		return new NodeDatabaseSchemaAdapter(databaseName, databaseNode);
	}
	
	//static method
	public static NodeDatabaseSchemaAdapter forDatabaseNodeInFile(final String filePath) {
		
		final var databaseName = new FileAccessor(filePath).getName().split(".")[0];
		final var databaseNode = Node.fromFile(filePath);
		
		return new NodeDatabaseSchemaAdapter(databaseName, databaseNode);
	}
	
	//constructor
	private NodeDatabaseSchemaAdapter(final String databaseName, final BaseNode databaseNode) {
		
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
