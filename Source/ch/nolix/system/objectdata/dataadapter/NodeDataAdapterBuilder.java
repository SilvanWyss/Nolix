//package declaration
package ch.nolix.system.objectdata.dataadapter;

import ch.nolix.core.argumentcaptor.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.argumentcaptor.forargumentcaptor.ForNodeDatabaseCaptor;
import ch.nolix.core.argumentcaptor.withargumentcaptor.WithNameCaptor;
import ch.nolix.systemapi.objectdataapi.schemaapi.ISchema;

//class
public final class NodeDataAdapterBuilder
extends ForNodeDatabaseCaptor<WithNameCaptor<AndSchemaCaptor<ISchema, NodeDataAdapter>>> {

  //constructor
  private NodeDataAdapterBuilder() {

    super(new WithNameCaptor<>(new AndSchemaCaptor<>()));

    setBuilder(this::buildNodeDataAdapter);
  }

  //static method
  public static NodeDataAdapterBuilder createNodeDataAdapter() {
    return new NodeDataAdapterBuilder();
  }

  //method
  private NodeDataAdapter buildNodeDataAdapter() {
    return NodeDataAdapter.forDatabaseNameAndNodeDatabaseAndSchema(
      nxtArgCpt().getName(),
      getStoredNodeDatabase(),
      nxtArgCpt().nxtArgCpt().getStoredSchema());
  }
}
