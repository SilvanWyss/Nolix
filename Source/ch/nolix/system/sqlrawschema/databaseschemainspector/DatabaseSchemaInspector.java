//package declaration
package ch.nolix.system.sqlrawschema.databaseschemainspector;

//own imports
import ch.nolix.system.sqlrawschema.structure.MetaDataTableType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.DatabaseSchemaState;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaReader;

//class
public final class DatabaseSchemaInspector {

  //method
  public DatabaseSchemaState getDatabaseSchemaState(final ISchemaReader schemaReader) {

    if (schemaReader.tableExists(MetaDataTableType.DATABASE_PROPERTY.getQualifiedName())) {
      return DatabaseSchemaState.INITIALIZED;
    }

    if (schemaReader.loadFlatTables().isEmpty()) {
      return DatabaseSchemaState.UNINITIALIZED;
    }

    return DatabaseSchemaState.INVALID;
  }
}
