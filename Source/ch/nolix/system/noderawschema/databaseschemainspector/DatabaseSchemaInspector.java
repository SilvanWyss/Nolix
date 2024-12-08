package ch.nolix.system.noderawschema.databaseschemainspector;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.objectschemaapi.schemaapi.DatabaseSchemaState;

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
    return nodeDatabase.hasHeader(StructureHeaderCatalogue.DATABASE)
    && nodeDatabase.containsChildNodeWithHeader(StructureHeaderCatalogue.DATABASE_PROPERTIES);
  }

  private boolean databaseIsUnitialized(final IMutableNode<?> nodeDatabase) {
    return (!nodeDatabase.hasHeader() && !nodeDatabase.containsChildNodes());
  }
}
