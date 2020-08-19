//package declaration
package ch.nolix.system.nodeDatabaseAdapter;

//own imports
import ch.nolix.common.functionAPI.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.system.databaseAdapter.DatabaseAdapter;
import ch.nolix.system.databaseAdapter.IDatabaseAdapterCreator;
import ch.nolix.system.databaseAdapter.Schema;

//class
public final class NodeDatabaseAdapterCreator implements IDatabaseAdapterCreator {
	
	//attribute
	private final IElementTakerElementGetter<Schema, NodeDatabaseAdapter> internalNodeDatabaseAdapterCreator;
	
	//constructor
	public NodeDatabaseAdapterCreator(final BaseNode databaseNode) {
		internalNodeDatabaseAdapterCreator = s -> new NodeDatabaseAdapter(databaseNode, s);
	}
	
	//constructor
	public NodeDatabaseAdapterCreator(final String databaseFilePath) {
		internalNodeDatabaseAdapterCreator = s -> new NodeDatabaseAdapter(databaseFilePath, s);
	}
	
	//method
	@Override
	public DatabaseAdapter createDatabaseAdapter(final Schema schema) {		
		return internalNodeDatabaseAdapterCreator.getOutput(schema);
	}
}
