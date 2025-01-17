package ch.nolix.system.noderawschema.databaseinitializer;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalog;
import ch.nolix.system.time.moment.Time;
import ch.nolix.systemapi.noderawschemaapi.databaseinitializerapi.IDatabaseInitializer;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;

final class InternalDatabaseInitializer implements IDatabaseInitializer {

  @Override
  public void initializeDatabase(final IMutableNode<?> nodeDatabase) {
    nodeDatabase
      .setHeader(NodeHeaderCatalog.DATABASE)
      .addChildNode(createDatabasePropertiesNode(), createEntityHeadsNode());
  }

  private Node createDatabasePropertiesNode() {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.DATABASE_PROPERTIES, createSchemaTimestampNode());
  }

  private Node createSchemaTimestampNode() {
    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.SCHEMA_TIMESTAMP,
      Time.ofNow().getSpecification().getStoredSingleChildNode());
  }

  private INode<?> createEntityHeadsNode() {
    return Node.withHeader(StructureHeaderCatalog.ENTITY_INDEXES);
  }
}
