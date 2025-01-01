package ch.nolix.system.sqlrawschema.databaseschemainspector;

import ch.nolix.systemapi.objectschemaapi.databaseproperty.DatabaseState;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MetaDataTableType;
import ch.nolix.systemapi.sqlschemaapi.schemaadapterapi.ISchemaReader;

public final class DatabaseSchemaInspector {

  public DatabaseState getDatabaseSchemaState(final ISchemaReader schemaReader) {

    if (schemaReader.tableExists(MetaDataTableType.DATABASE_PROPERTY.getQualifiedName())) {
      return DatabaseState.INITIALIZED;
    }

    if (schemaReader.loadFlatTables().isEmpty()) {
      return DatabaseState.UNINITIALIZED;
    }

    return DatabaseState.INVALID;
  }
}
