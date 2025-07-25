package ch.nolix.system.nodemidschema.nodeexaminer;

import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodeexaminer.INodeDatabaseExaminer;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 */
public final class NodeDatabaseExaminer implements INodeDatabaseExaminer {

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean nodeDatabaseIsInitialized(final IMutableNode<?> nodeDatabase) {
    return //
    nodeDatabase != null
    && nodeDatabase.hasHeader(NodeHeaderCatalog.DATABASE)
    && nodeDatabase.containsChildNodeWithHeader(NodeHeaderCatalog.DATABASE_PROPERTIES);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean nodeDatabaseIsUninitialized(final IMutableNode<?> nodeDatabase) {
    return //
    nodeDatabase != null
    && nodeDatabase.isBlank();
  }
}
