//package declaration
package ch.nolix.system.objectdatabase.dataadapter;

//own imports
import ch.nolix.core.programstructure.builder.andargumentcapturer.AndSchemaCapturer;
import ch.nolix.core.programstructure.builder.forargumentcapturer.ForNodeDatabaseCapturer;
import ch.nolix.core.programstructure.builder.withargumentcapturer.WithNameCapturer;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class NodeDataAdapterBuilder
extends ForNodeDatabaseCapturer<WithNameCapturer<AndSchemaCapturer<ISchema, NodeDataAdapter>>> {

  //constructor
  private NodeDataAdapterBuilder() {

    super(new WithNameCapturer<>(new AndSchemaCapturer<>()));

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
