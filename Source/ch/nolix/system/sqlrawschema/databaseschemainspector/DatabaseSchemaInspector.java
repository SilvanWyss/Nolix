package ch.nolix.system.sqlrawschema.databaseschemainspector;

import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseSchemaState;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MetaDataTableType;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaReader;

public final class DatabaseSchemaInspector {

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
