//package declaration
package ch.nolix.system.noderawschema.databaseschemainspector;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.objectschemaapi.schemaapi.DatabaseSchemaState;

//class
public final class DatabaseSchemaInspector {

  //method
  public DatabaseSchemaState getDatabaseSchemaState(final IMutableNode<?> databaseNode) {

    if (databaseIsInitialized(databaseNode)) {
      return DatabaseSchemaState.INITIALIZED;
    }

    if (databaseIsUnitialized(databaseNode)) {
      return DatabaseSchemaState.UNINITIALIZED;
    }

    return DatabaseSchemaState.INVALID;
  }

  //method
  private boolean databaseIsInitialized(final IMutableNode<?> databaseNode) {
    return databaseNode.hasHeader(StructureHeaderCatalogue.DATABASE)
    && databaseNode.containsChildNodeWithHeader(StructureHeaderCatalogue.DATABASE_PROPERTIES);
  }

  //method
  private boolean databaseIsUnitialized(final IMutableNode<?> databaseNode) {
    return (!databaseNode.hasHeader() && !databaseNode.containsChildNodes());
  }
}
