//package declaration
package ch.nolix.system.objectdata.dataadapter;

import ch.nolix.core.programstructure.builder.andargumentcaptor.AndSchemaCaptor;
import ch.nolix.core.programstructure.builder.forargumentcaptor.ForNodeDatabaseCaptor;
import ch.nolix.core.programstructure.builder.withargumentcaptor.WithNameCaptor;
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
      next().getName(),
      getStoredNodeDatabase(),
      next().next().getStoredSchema());
  }
}
