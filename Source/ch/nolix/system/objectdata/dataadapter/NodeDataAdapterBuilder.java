package ch.nolix.system.objectdata.dataadapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.argumentcaptor.forargumentcaptor.ForNodeDatabaseCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

public final class NodeDataAdapterBuilder
extends ForNodeDatabaseCaptor<WithNameCaptor<AndSchemaCaptor<ISchema, NodeDataAdapter>>> {

  private NodeDataAdapterBuilder() {

    super(new WithNameCaptor<>(new AndSchemaCaptor<>()));

    setBuilder(this::buildNodeDataAdapter);
  }

  public static NodeDataAdapterBuilder createNodeDataAdapter() {
    return new NodeDataAdapterBuilder();
  }

  private NodeDataAdapter buildNodeDataAdapter() {
    return NodeDataAdapter.forDatabaseNameAndNodeDatabaseAndSchema(
      nxtArgCpt().getName(),
      getStoredNodeDatabase(),
      nxtArgCpt().nxtArgCpt().getStoredSchema());
  }
}
