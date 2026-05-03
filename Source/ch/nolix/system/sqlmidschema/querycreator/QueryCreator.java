/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.querycreator;

import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.ColumnColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.ReferenceableTableColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.column.TableColumn;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.MetaDataTable;
import ch.nolix.systemapi.sqlmiddatabasestructure.table.SchemaTable;
import ch.nolix.systemapi.sqlmidschema.querycreator.IQueryCreator;

/**
 * @author Silvan Wyss
 */
public final class QueryCreator implements IQueryCreator {
  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToGetTableCount() {
    return //
    "SELECT COUNT("
    + TableColumn.ID
    + ") FROM "
    + SchemaTable.TABLE
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadJoinedColumns() {
    return //
    "SELECT "
    + SchemaTable.COLUMN + "." + ColumnColumn.ID
    + ", "
    + SchemaTable.COLUMN + "." + ColumnColumn.NAME
    + ", "
    + SchemaTable.TABLE + "." + TableColumn.ID
    + ", "
    + SchemaTable.TABLE + "." + TableColumn.NAME
    + ", "
    + ColumnColumn.FIELD_TYPE
    + ", "
    + ColumnColumn.DATA_TYPE
    + " FROM "
    + SchemaTable.COLUMN
    + " LEFT JOIN "
    + SchemaTable.TABLE
    + " ON "
    + SchemaTable.COLUMN + "." + ColumnColumn.PARENT_TABLE_ID
    + " = "
    + SchemaTable.TABLE + "." + TableColumn.ID
    + " LEFT JOIN (SELECT "
    + SchemaTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ", STRING_AGG("
    + SchemaTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID
    + ", ',') AS ReferenceableTableIds FROM"
    + SchemaTable.REFERENCEABLE_TABLE
    + " GROUP BY "
    + SchemaTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ")"
    + " ON "
    + SchemaTable.COLUMN + "." + ColumnColumn.ID
    + " = "
    + SchemaTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadJoinedColumns(String tableName) {
    return //
    "SELECT "
    + SchemaTable.COLUMN + "." + ColumnColumn.ID
    + ", "
    + SchemaTable.COLUMN + "." + ColumnColumn.NAME
    + ", "
    + SchemaTable.TABLE + "." + TableColumn.ID
    + ", "
    + SchemaTable.TABLE + "." + TableColumn.NAME
    + ", "
    + ColumnColumn.FIELD_TYPE
    + ", "
    + ColumnColumn.DATA_TYPE
    + " FROM "
    + SchemaTable.COLUMN
    + " LEFT JOIN "
    + SchemaTable.TABLE
    + " ON "
    + SchemaTable.COLUMN + "." + ColumnColumn.PARENT_TABLE_ID
    + " = "
    + SchemaTable.TABLE + "." + TableColumn.ID
    + " LEFT JOIN (SELECT "
    + SchemaTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ", STRING_AGG("
    + SchemaTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID
    + ", ',') AS ReferenceableTableIds FROM"
    + SchemaTable.REFERENCEABLE_TABLE
    + " GROUP BY "
    + SchemaTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ")"
    + " ON "
    + SchemaTable.COLUMN + "." + ColumnColumn.ID
    + " = "
    + SchemaTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + " WHERE "
    + SchemaTable.TABLE + "." + TableColumn.NAME
    + " = '"
    + tableName
    + "';";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return //
    "SELECT "
    + DatabasePropertyColumn.VALUE
    + " FROM "
    + MetaDataTable.DATABASE_PROPERTY
    + " WHERE "
    + DatabasePropertyColumn.KEY
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes();
  }
}
