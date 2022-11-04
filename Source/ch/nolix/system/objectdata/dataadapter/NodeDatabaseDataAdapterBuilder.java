//package declaration
package ch.nolix.system.objectdata.dataadapter;

import ch.nolix.core.builder.usingargumentcapturer.UsingSchemaCapturer;
import ch.nolix.core.builder.withargumentcapturer.WithNameCapturer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

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
