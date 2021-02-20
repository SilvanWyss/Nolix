//package declaration
package ch.nolix.system.database.nodedatabaseadapter;

//own imports
import ch.nolix.common.functionapi.IElementTakerElementGetter;
import ch.nolix.common.node.BaseNode;
import ch.nolix.system.database.databaseadapter.DatabaseAdapter;
import ch.nolix.system.database.databaseadapter.IDatabaseAdapterCreator;
import ch.nolix.system.database.databaseadapter.Schema;

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
