package ch.nolix.system.objectdata.adapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.argumentcaptor.forargumentcaptor.ForNodeDatabaseCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.systemapi.objectdata.model.IEntityTypeSet;

public final class NodeDataAdapterBuilder
extends ForNodeDatabaseCaptor<WithNameCaptor<AndSchemaCaptor<IEntityTypeSet, NodeDataAdapter>>> {
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
