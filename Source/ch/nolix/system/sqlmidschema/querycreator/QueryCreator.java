/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.sqlmidschema.querycreator;

import ch.nolix.systemapi.midschema.databasestructure.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschema.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmidschema.databasestructure.MetaTable;
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
    + TableColumn.ID.getName()
    + ") FROM "
    + MetaTable.TABLE.getName()
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadJoinedColumns() {
    return //
    "SELECT "
    + MetaTable.COLUMN.getName() + "." + ColumnColumn.ID.getName()
    + ", "
    + MetaTable.COLUMN.getName() + "." + ColumnColumn.NAME.getName()
    + ", "
    + MetaTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + ", "
    + MetaTable.TABLE.getName() + "." + TableColumn.NAME.getName()
    + ", "
    + ColumnColumn.FIELD_TYPE.getName()
    + ", "
    + ColumnColumn.DATA_TYPE.getName()
    + " FROM "
    + MetaTable.COLUMN.getName()
    + " LEFT JOIN "
    + MetaTable.TABLE.getName()
    + " ON "
    + MetaTable.COLUMN.getName() + "." + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = "
    + MetaTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + " LEFT JOIN (SELECT "
    + MetaTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + ", STRING_AGG("
    + MetaTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID.getName()
    + ", ',') AS ReferenceableTableIds FROM"
    + MetaTable.REFERENCEABLE_TABLE.getName()
    + " GROUP BY "
    + MetaTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ")"
    + " ON "
    + MetaTable.COLUMN.getName() + "." + ColumnColumn.ID.getName()
    + " = "
    + MetaTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadJoinedColumns(String tableName) {
    return //
    "SELECT "
    + MetaTable.COLUMN.getName() + "." + ColumnColumn.ID.getName()
    + ", "
    + MetaTable.COLUMN.getName() + "." + ColumnColumn.NAME.getName()
    + ", "
    + MetaTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + ", "
    + MetaTable.TABLE.getName() + "." + TableColumn.NAME.getName()
    + ", "
    + ColumnColumn.FIELD_TYPE.getName()
    + ", "
    + ColumnColumn.DATA_TYPE.getName()
    + " FROM "
    + MetaTable.COLUMN.getName()
    + " LEFT JOIN "
    + MetaTable.TABLE.getName()
    + " ON "
    + MetaTable.COLUMN.getName() + "." + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = "
    + MetaTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + " LEFT JOIN (SELECT "
    + MetaTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + ", STRING_AGG("
    + MetaTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID.getName()
    + ", ',') AS ReferenceableTableIds FROM"
    + MetaTable.REFERENCEABLE_TABLE.getName()
    + " GROUP BY "
    + MetaTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ")"
    + " ON "
    + MetaTable.COLUMN.getName() + "." + ColumnColumn.ID.getName()
    + " = "
    + MetaTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + " WHERE "
    + MetaTable.TABLE.getName() + "." + TableColumn.NAME.getName()
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
    + DatabasePropertyColumn.VALUE.getName()
    + " FROM "
    + MetaTable.DATABASE_PROPERTY.getName()
    + " WHERE "
    + DatabasePropertyColumn.KEY.getName()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes();
  }
}
