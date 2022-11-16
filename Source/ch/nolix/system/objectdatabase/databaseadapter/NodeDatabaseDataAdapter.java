//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawdata.dataandschemaadapter.DataAndSchemaAdapter;
import ch.nolix.system.objectdatabase.database.DataAdapter;
import ch.nolix.system.objectdatabase.database.DataImplementation;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ISchema;

//class
public final class NodeDatabaseDataAdapter extends DataAdapter {
	
	//static method
	public static NodeDatabaseDataAdapterBuilder forNodeDatabase(final IMutableNode<?> nodeDatabase) {
		return new NodeDatabaseDataAdapterBuilder(nodeDatabase);
	}
	
	//constructor
	NodeDatabaseDataAdapter(
		final String databaseName,
		final IMutableNode<?> nodeDatabase, 
		final ISchema<DataImplementation> schema
	) {
		super(
			NodeSchemaAdapter.forDatabaseNode(databaseName, nodeDatabase),
			schema,
			() -> DataAndSchemaAdapter.forNodeDatabase(nodeDatabase)
		);
	}
}
