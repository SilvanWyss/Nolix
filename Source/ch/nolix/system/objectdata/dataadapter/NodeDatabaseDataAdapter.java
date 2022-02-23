//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.builder.terminalargumentcapturer.UsingSchemaTerminalCapturer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.noderawobjectdata.dataandschemaadapter.DataAndSchemaAdapter;
import ch.nolix.system.objectdata.data.DataAdapter;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
public final class NodeDatabaseDataAdapter extends DataAdapter {
	
	//static method
	public static UsingSchemaTerminalCapturer<ISchema<DataImplementation>, NodeDatabaseDataAdapter>
	forNodeDatabase(final BaseNode nodeDatabase) {
		return new NodeDatabaseDataAdapterBuilder().getRefStart().withDatabase(nodeDatabase);
	}
	
	//constructor
	NodeDatabaseDataAdapter(
		final BaseNode nodeDatabase, 
		final ISchema<DataImplementation> schema
	) {
		super(DataAndSchemaAdapter.forNodeDatabase(nodeDatabase), schema);
	}
}
