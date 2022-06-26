//package declaration
package ch.nolix.system.objectschema.schemaadapter;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.environment.filesystem.FileAccessor;

//class
public final class NodeSchemaAdapter extends SchemaAdapter {
	
	//static method
	public static NodeSchemaAdapter forDatabaseNode(final String databaseName, final BaseNode<?> databaseNode) {
		return new NodeSchemaAdapter(databaseName, databaseNode);
	}
	
	//static method
	public static NodeSchemaAdapter forDatabaseNodeInFile(final String filePath) {
		
		final var databaseName = new FileAccessor(filePath).getName().split(".")[0];
		final var databaseNode = Node.fromFile(filePath);
		
		return new NodeSchemaAdapter(databaseName, databaseNode);
	}
	
	//constructor
	private NodeSchemaAdapter(final String databaseName, final BaseNode<?> databaseNode) {
		super(databaseName, ch.nolix.system.nodedatabaserawschema.schemaadapter.SchemaAdapter.forDatabaseNode(databaseNode));
	}
}
