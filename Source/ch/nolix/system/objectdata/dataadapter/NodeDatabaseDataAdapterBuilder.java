//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.builder.argumentcapturer.WithNameCapturer;
import ch.nolix.core.builder.terminalargumentcapturer.UsingSchemaTerminalCapturer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
public final class NodeDatabaseDataAdapterBuilder
extends
WithNameCapturer<
	UsingSchemaTerminalCapturer<
		ISchema<DataImplementation>,
		NodeDatabaseDataAdapter
	>
> {
	
	//constructor
	public NodeDatabaseDataAdapterBuilder(final BaseNode nodeDatabase) {
		
		super(new UsingSchemaTerminalCapturer<>());
		
		setBuilder(() -> build(nodeDatabase));
	}
	
	//method
	private NodeDatabaseDataAdapter build(final BaseNode nodeDatabase) {
		return		
		new NodeDatabaseDataAdapter(
			getName(),
			nodeDatabase,
			n().getRefSchema()
		);
	}
}
