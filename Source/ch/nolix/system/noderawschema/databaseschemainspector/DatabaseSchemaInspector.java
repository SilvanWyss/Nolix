package ch.nolix.system.noderawschema.databaseschemainspector;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalog;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;

public final class DatabaseSchemaInspector {

  public DatabaseState getDatabaseSchemaState(final IMutableNode<?> nodeDatabase) {

    if (databaseIsInitialized(nodeDatabase)) {
      return DatabaseState.INITIALIZED;
    }

    if (databaseIsUnitialized(nodeDatabase)) {
      return DatabaseState.UNINITIALIZED;
    }

    return DatabaseState.INVALID;
  }

  private boolean databaseIsInitialized(final IMutableNode<?> nodeDatabase) {
    return //
    nodeDatabase.hasHeader(NodeHeaderCatalog.DATABASE)
    && nodeDatabase.containsChildNodeWithHeader(NodeHeaderCatalog.DATABASE_PROPERTIES);
  }

  private boolean databaseIsUnitialized(final IMutableNode<?> nodeDatabase) {
    return (!nodeDatabase.hasHeader() && !nodeDatabase.containsChildNodes());
  }
}
