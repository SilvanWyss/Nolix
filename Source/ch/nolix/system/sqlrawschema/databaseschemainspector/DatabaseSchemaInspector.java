//package declaration
package ch.nolix.system.sqlrawschema.databaseschemainspector;

//own imports
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.DatabaseSchemaState;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaAdapter;

//class
public final class DatabaseSchemaInspector {

  //method
  public DatabaseSchemaState getDatabaseSchemaState(final ISchemaAdapter schemaAdapter) {

    if (schemaAdapter.tableExists(SchemaTableType.DATABASE_PROPERTY.getQualifiedName())) {
      return DatabaseSchemaState.INITIALIZED;
    }

    if (schemaAdapter.loadFlatTables().isEmpty()) {
      return DatabaseSchemaState.UNINITIALIZED;
    }

    return DatabaseSchemaState.INVALID;
  }
}
