//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

import ch.nolix.core.builder.usingargumentcapturer.UsingSchemaCapturer;
import ch.nolix.core.builder.withargumentcapturer.WithNameCapturer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.objectdatabase.database.DataImplementation;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ISchema;

//class
public final class NodeDatabaseDataAdapterBuilder
extends
WithNameCapturer<
	UsingSchemaCapturer<
		ISchema<DataImplementation>,
		NodeDatabaseDataAdapter
	>
> {
	
	//constructor
	public NodeDatabaseDataAdapterBuilder(final IMutableNode<?> nodeDatabase) {
		
		super(new UsingSchemaCapturer<>(null));
		
		setBuilder(() -> build(nodeDatabase));
	}
	
	//method
	private NodeDatabaseDataAdapter build(final IMutableNode<?> nodeDatabase) {
		return		
		new NodeDatabaseDataAdapter(
			getName(),
			nodeDatabase,
			next().getRefSchema()
		);
	}
}
