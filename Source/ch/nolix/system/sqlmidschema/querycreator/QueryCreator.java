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
    + TableColumn.ID.getName()
    + ") FROM "
    + FixTable.TABLE.getName()
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadJoinedColumns() {
    return //
    "SELECT "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.ID.getName()
    + ", "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.NAME.getName()
    + ", "
    + FixTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + ", "
    + FixTable.TABLE.getName() + "." + TableColumn.NAME.getName()
    + ", "
    + ColumnColumn.FIELD_TYPE.getName()
    + ", "
    + ColumnColumn.DATA_TYPE.getName()
    + " FROM "
    + FixTable.COLUMN.getName()
    + " LEFT JOIN "
    + FixTable.TABLE.getName()
    + " ON "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = "
    + FixTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + " LEFT JOIN (SELECT "
    + FixTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + ", STRING_AGG("
    + FixTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID.getName()
    + ", ',') AS ReferenceableTableIds FROM"
    + FixTable.REFERENCEABLE_TABLE.getName()
    + " GROUP BY "
    + FixTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ")"
    + " ON "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.ID.getName()
    + " = "
    + FixTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadJoinedColumns(String tableName) {
    return //
    "SELECT "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.ID.getName()
    + ", "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.NAME.getName()
    + ", "
    + FixTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + ", "
    + FixTable.TABLE.getName() + "." + TableColumn.NAME.getName()
    + ", "
    + ColumnColumn.FIELD_TYPE.getName()
    + ", "
    + ColumnColumn.DATA_TYPE.getName()
    + " FROM "
    + FixTable.COLUMN.getName()
    + " LEFT JOIN "
    + FixTable.TABLE.getName()
    + " ON "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = "
    + FixTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + " LEFT JOIN (SELECT "
    + FixTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + ", STRING_AGG("
    + FixTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.REFERENCEABLE_TABLE_ID.getName()
    + ", ',') AS ReferenceableTableIds FROM"
    + FixTable.REFERENCEABLE_TABLE.getName()
    + " GROUP BY "
    + FixTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID
    + ")"
    + " ON "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.ID.getName()
    + " = "
    + FixTable.REFERENCEABLE_TABLE.getName() + "." + ReferenceableTableColumn.PARENT_BASE_REFERENCE_COLUMN_ID.getName()
    + " WHERE "
    + FixTable.TABLE.getName() + "." + TableColumn.NAME.getName()
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
    + FixTable.DATABASE_PROPERTY.getName()
    + " WHERE "
    + DatabasePropertyColumn.KEY.getName()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes();
  }
}
