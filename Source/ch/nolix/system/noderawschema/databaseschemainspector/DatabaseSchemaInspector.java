package ch.nolix.system.noderawschema.databaseschemainspector;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseSchemaState;

public final class DatabaseSchemaInspector {

  public DatabaseSchemaState getDatabaseSchemaState(final IMutableNode<?> nodeDatabase) {

    if (databaseIsInitialized(nodeDatabase)) {
      return DatabaseSchemaState.INITIALIZED;
    }

    if (databaseIsUnitialized(nodeDatabase)) {
      return DatabaseSchemaState.UNINITIALIZED;
    }

    return DatabaseSchemaState.INVALID;
  }

  private boolean databaseIsInitialized(final IMutableNode<?> nodeDatabase) {
    return //
    nodeDatabase.hasHeader(NodeHeaderCatalogue.DATABASE)
    && nodeDatabase.containsChildNodeWithHeader(NodeHeaderCatalogue.DATABASE_PROPERTIES);
  }

  private boolean databaseIsUnitialized(final IMutableNode<?> nodeDatabase) {
    return (!nodeDatabase.hasHeader() && !nodeDatabase.containsChildNodes());
  }
}
