package ch.nolix.system.sqlmidschema.querycreator;

import ch.nolix.systemapi.midschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ContentModelColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.DatabasePropertyColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.TableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.querycreatorapi.IQueryCreator;

/**
 * @author Silvan Wyss
 * @version 2021-09-02
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
  public String createQueryToLoadCoumnsByTableId(final String tableId) {
    return //
    "SELECT "
    + ColumnColumn.ID.getName()
    + ", "
    + ColumnColumn.NAME.getName()
    + ", "
    + ColumnColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnColumn.CONTENT_TYPE.getName()
    + ", "
    + ColumnColumn.DATA_TYPE.getName()
    + ", "
    + ColumnColumn.BACK_REFERENCED_COLUM_ID.getName()
    + " FROM "
    + FixTable.COLUMN.getName()
    + " WHERE "
    + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableId
    + "'";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadColumnsByTableName(final String tableName) {
    return //
    "SELECT "
    + ColumnColumn.ID.getName()
    + ", "
    + ColumnColumn.NAME.getName()
    + ", "
    + ColumnColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnColumn.CONTENT_TYPE.getName()
    + ", "
    + ColumnColumn.DATA_TYPE.getName()
    + ", "
    + ColumnColumn.BACK_REFERENCED_COLUM_ID.getName()
    + " FROM "
    + FixTable.COLUMN.getName()
    + " WHERE "
    + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableName
    + "'";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadFlatTableByName(final String name) {
    return //
    "SELECT "
    + TableColumn.ID.getName()
    + ", "
    + TableColumn.NAME.getName()
    + " FROM "
    + FixTable.TABLE.getName()
    + " WHERE "
    + TableColumn.NAME.getName()
    + " = '"
    + name
    + "'";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadFlatTables() {
    return //
    "SELECT "
    + TableColumn.ID.getName()
    + ", "
    + TableColumn.NAME.getName()
    + " FROM "
    + FixTable.TABLE.getName();
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
    + FixTable.CONTENT_MODEL.getName() + "." + ContentModelColumn.CONTENT_TYPE.getName()
    + ", "
    + FixTable.CONTENT_MODEL.getName() + "." + ContentModelColumn.DATA_TYPE.getName()
    + ", "
    + FixTable.CONTENT_MODEL.getName() + "." + ContentModelColumn.REFERENCED_TABLE_ID.getName()
    + ", "
    + FixTable.CONTENT_MODEL.getName() + "." + ContentModelColumn.BACK_REFERENCED_COLUM_ID.getName()
    + " FROM "
    + FixTable.COLUMN.name()
    + " INNER JOIN "
    + FixTable.TABLE.name()
    + " ON "
    + FixTable.COLUMN.getName() + "." + ColumnColumn.PARENT_TABLE_ID.name()
    + " = "
    + FixTable.TABLE.getName() + "." + TableColumn.ID.getName()
    + " INNER JOIN ON "
    + FixTable.CONTENT_MODEL.name()
    + " ON "
    + FixTable.COLUMN.name() + "." + ColumnColumn.CONTENT_MODEL.name()
    + " = "
    + FixTable.CONTENT_MODEL.getName() + "." + ContentModelColumn.ID.getName()
    + ";";
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
