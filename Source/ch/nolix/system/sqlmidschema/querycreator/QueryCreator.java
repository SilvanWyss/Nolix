/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.querycreator;

import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ReferenceableTableColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.TableColumn;
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
    + FixTable.TABLE
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadJoinedColumns() {
    return //
    "SELECT "
    + FixTable.COLUMN + "." + ColumnColumn.ID
    + ", "
    + FixTable.COLUMN + "." + ColumnColumn.NAME
    + ", "
    + FixTable.TABLE + "." + TableColumn.ID
    + ", "
    + FixTable.TABLE + "." + TableColumn.NAME
    + ", "
    + ColumnColumn.FIELD_TYPE
    + ", "
    + ColumnColumn.DATA_TYPE
    + " FROM "
    + FixTable.COLUMN
    + " LEFT JOIN "
    + FixTable.TABLE
    + " ON "
    + FixTable.COLUMN + "." + ColumnColumn.PARENT_TABLE_ID
    + " = "
    + FixTable.TABLE + "." + TableColumn.ID
    + " LEFT JOIN (SELECT "
    + FixTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ", STRING_AGG("
    + FixTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID
    + ", ',') AS ReferenceableTableIds FROM"
    + FixTable.REFERENCEABLE_TABLE
    + " GROUP BY "
    + FixTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ")"
    + " ON "
    + FixTable.COLUMN + "." + ColumnColumn.ID
    + " = "
    + FixTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadJoinedColumns(String tableName) {
    return //
    "SELECT "
    + FixTable.COLUMN + "." + ColumnColumn.ID
    + ", "
    + FixTable.COLUMN + "." + ColumnColumn.NAME
    + ", "
    + FixTable.TABLE + "." + TableColumn.ID
    + ", "
    + FixTable.TABLE + "." + TableColumn.NAME
    + ", "
    + ColumnColumn.FIELD_TYPE
    + ", "
    + ColumnColumn.DATA_TYPE
    + " FROM "
    + FixTable.COLUMN
    + " LEFT JOIN "
    + FixTable.TABLE
    + " ON "
    + FixTable.COLUMN + "." + ColumnColumn.PARENT_TABLE_ID
    + " = "
    + FixTable.TABLE + "." + TableColumn.ID
    + " LEFT JOIN (SELECT "
    + FixTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ", STRING_AGG("
    + FixTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID
    + ", ',') AS ReferenceableTableIds FROM"
    + FixTable.REFERENCEABLE_TABLE
    + " GROUP BY "
    + FixTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ")"
    + " ON "
    + FixTable.COLUMN + "." + ColumnColumn.ID
    + " = "
    + FixTable.REFERENCEABLE_TABLE + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + " WHERE "
    + FixTable.TABLE + "." + TableColumn.NAME
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
    + FixTable.DATABASE_PROPERTY
    + " WHERE "
    + DatabasePropertyColumn.KEY
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes();
  }
}
