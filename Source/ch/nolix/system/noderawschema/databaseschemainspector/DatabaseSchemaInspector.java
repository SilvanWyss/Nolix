package ch.nolix.system.noderawschema.databaseschemainspector;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.objectschemaapi.schemaapi.DatabaseSchemaState;

public final class DatabaseSchemaInspector {

  public DatabaseSchemaState getDatabaseSchemaState(final IMutableNode<?> databaseNode) {

    if (databaseIsInitialized(databaseNode)) {
      return DatabaseSchemaState.INITIALIZED;
    }

    if (databaseIsUnitialized(databaseNode)) {
      return DatabaseSchemaState.UNINITIALIZED;
    }

    return DatabaseSchemaState.INVALID;
  }

  private boolean databaseIsInitialized(final IMutableNode<?> databaseNode) {
    return databaseNode.hasHeader(StructureHeaderCatalogue.DATABASE)
    && databaseNode.containsChildNodeWithHeader(StructureHeaderCatalogue.DATABASE_PROPERTIES);
  }

  private boolean databaseIsUnitialized(final IMutableNode<?> databaseNode) {
    return (!databaseNode.hasHeader() && !databaseNode.containsChildNodes());
  }
}
