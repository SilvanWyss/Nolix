//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.nodedatabaserawdata.dataandschemaadapter.DataAndSchemaAdapter;
import ch.nolix.system.objectdata.data.DataAdapter;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.system.objectschema.schemaadapter.NodeSchemaAdapter;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
public final class NodeDatabaseDataAdapter extends DataAdapter {
	
	//static method
	public static NodeDatabaseDataAdapterBuilder forNodeDatabase(final BaseNode nodeDatabase) {
		return new NodeDatabaseDataAdapterBuilder(nodeDatabase);
	}
	
	//constructor
	NodeDatabaseDataAdapter(
		final String databaseName,
		final BaseNode nodeDatabase, 
		final ISchema<DataImplementation> schema
	) {
		super(
			NodeSchemaAdapter.forDatabaseNode(databaseName, nodeDatabase),
			schema,
			() -> DataAndSchemaAdapter.forNodeDatabase(nodeDatabase)
		);
	}
}
