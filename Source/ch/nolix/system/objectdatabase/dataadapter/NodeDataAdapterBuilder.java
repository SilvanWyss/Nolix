//package declaration
package ch.nolix.system.objectdatabase.dataadapter;

//own imports
import ch.nolix.core.builder.andargumentcapturer.AndSchemaCapturer;
import ch.nolix.core.builder.withargumentcapturer.WithNameCapturer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.objectdatabaseapi.schemaapi.ISchema;

//class
public final class NodeDataAdapterBuilder
    extends
    WithNameCapturer<AndSchemaCapturer<ISchema, NodeDataAdapter>> {

  //constructor
  public NodeDataAdapterBuilder(final IMutableNode<?> nodeDatabase) {

    super(new AndSchemaCapturer<>(null));

    setBuilder(() -> build(nodeDatabase));
  }

  //method
  private NodeDataAdapter build(final IMutableNode<?> nodeDatabase) {
    return new NodeDataAdapter(
        getName(),
        nodeDatabase,
        next().getStoredSchema());
  }
}
