package ch.nolix.system.noderawschema.databaseinitializer;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.noderawschemaapi.databaseinitializerapi.IDatabaseInitializer;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;

final class InternalDatabaseInitializer implements IDatabaseInitializer {

  @Override
  public void initializeDatabase(final IMutableNode<?> nodeDatabase) {
    nodeDatabase
      .setHeader(NodeHeaderCatalogue.DATABASE)
      .addChildNode(createDatabasePropertiesNode(), createEntityHeadsNode());
  }

  private Node createDatabasePropertiesNode() {
    return Node.withHeaderAndChildNode(NodeHeaderCatalogue.DATABASE_PROPERTIES, createSchemaTimestampNode());
  }

  private Node createSchemaTimestampNode() {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalogue.SCHEMA_TIMESTAMP,
      Time.ofNow().getSpecification().getStoredSingleChildNode());
  }

  private INode<?> createEntityHeadsNode() {
    return Node.withHeader(StructureHeaderCatalogue.ENTITY_HEADS);
  }
}
