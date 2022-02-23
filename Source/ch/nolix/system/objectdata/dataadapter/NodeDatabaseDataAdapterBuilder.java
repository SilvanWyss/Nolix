//package declaration
package ch.nolix.system.objectdata.dataadapter;

//own imports
import ch.nolix.core.builder.argumentcapturer.WithDatabaseCapturer;
import ch.nolix.core.builder.base.Builder;
import ch.nolix.core.builder.terminalargumentcapturer.UsingSchemaTerminalCapturer;
import ch.nolix.core.document.node.BaseNode;
import ch.nolix.system.objectdata.data.DataImplementation;
import ch.nolix.systemapi.objectdataapi.dataapi.ISchema;

//class
public final class NodeDatabaseDataAdapterBuilder extends Builder<
	WithDatabaseCapturer<
		BaseNode,
		UsingSchemaTerminalCapturer<
			ISchema<DataImplementation>,
			NodeDatabaseDataAdapter
		>
	>,
	NodeDatabaseDataAdapter
> {
	
	//method
	@Override
	protected NodeDatabaseDataAdapter build(
		final
		WithDatabaseCapturer<
			BaseNode,
			UsingSchemaTerminalCapturer<ISchema<DataImplementation>,
			NodeDatabaseDataAdapter>
		>
		startArgumentCapturer
	) {
		return
		new NodeDatabaseDataAdapter(startArgumentCapturer.getRefDatabase(), startArgumentCapturer.n().getRefSchema());
	}

	@Override
	protected
	WithDatabaseCapturer<
		BaseNode,
		UsingSchemaTerminalCapturer<
			ISchema<DataImplementation>,
			NodeDatabaseDataAdapter
		>
	>
	createStartArgumentCapturer() {
		return new WithDatabaseCapturer<>(new UsingSchemaTerminalCapturer<>());
	}
}
