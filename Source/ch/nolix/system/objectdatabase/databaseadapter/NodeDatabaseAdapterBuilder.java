//package declaration
package ch.nolix.system.objectdatabase.databaseadapter;

import ch.nolix.core.builder.usingargumentcapturer.UsingSchemaCapturer;
import ch.nolix.core.builder.withargumentcapturer.WithNameCapturer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class NodeDatabaseAdapterBuilder
extends
WithNameCapturer<
	UsingSchemaCapturer<
		ISchema,
		NodeDatabaseAdapter
	>
> {
	
	//constructor
	public NodeDatabaseAdapterBuilder(final IMutableNode<?> nodeDatabase) {
		
		super(new UsingSchemaCapturer<>(null));
		
		setBuilder(() -> build(nodeDatabase));
	}
	
	//method
	private NodeDatabaseAdapter build(final IMutableNode<?> nodeDatabase) {
		return		
		new NodeDatabaseAdapter(
			getName(),
			nodeDatabase,
			next().getOriSchema()
		);
	}
}
