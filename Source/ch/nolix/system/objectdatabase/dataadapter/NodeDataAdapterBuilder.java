//package declaration
package ch.nolix.system.objectdatabase.dataadapter;

import ch.nolix.core.builder.usingargumentcapturer.UsingSchemaCapturer;
import ch.nolix.core.builder.withargumentcapturer.WithNameCapturer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class NodeDataAdapterBuilder
extends
WithNameCapturer<
	UsingSchemaCapturer<
		ISchema,
		NodeDataAdapter
	>
> {
	
	//constructor
	public NodeDataAdapterBuilder(final IMutableNode<?> nodeDatabase) {
		
		super(new UsingSchemaCapturer<>(null));
		
		setBuilder(() -> build(nodeDatabase));
	}
	
	//method
	private NodeDataAdapter build(final IMutableNode<?> nodeDatabase) {
		return		
		new NodeDataAdapter(
			getName(),
			nodeDatabase,
			next().getStoredSchema()
		);
	}
}
