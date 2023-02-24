//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.databaseandschemaadapter.DataAndSchemaAdapter;
import ch.nolix.system.objectdatabase.database.DatabaseAdapter;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class NodeDatabaseAdapter extends DatabaseAdapter {
		
	//static method
	public static NodeDatabaseAdapterBuilder forNodeDatabase(final IMutableNode<?> nodeDatabase) {
		return new NodeDatabaseAdapterBuilder(nodeDatabase);
	}
	
	//attribute
	private final IMutableNode<?> nodeDatabase;
	
	//constructor
	NodeDatabaseAdapter(
		final String databaseName,
		final IMutableNode<?> nodeDatabase, 
		final ISchema schema
	) {
		
		super(
			databaseName,
			NodeSchemaAdapter.forDatabaseNode(databaseName, nodeDatabase),
			schema,
			() -> DataAndSchemaAdapter.forNodeDatabase(nodeDatabase)
		);
		
		this.nodeDatabase = nodeDatabase;
	}
	
	//method
	@Override
	public DatabaseAdapter getEmptyCopy() {
		return forNodeDatabase(nodeDatabase).withName(getDatabaseName()).usingSchema(getSchema());
	}
}
