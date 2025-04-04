package ch.nolix.system.sqlmidschema.querycreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.midschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnColumn;
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
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
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
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + ColumnColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableName
    + "'";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadFlatTableById(final String id) {
    return //
    "SELECT "
    + TableColumn.ID.getName()
    + ", "
    + TableColumn.NAME.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableColumn.ID.getName()
    + " = '"
    + id
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
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
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
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.TABLE.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return //
    "SELECT "
    + DatabasePropertyColumn.VALUE.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.DATABASE_PROPERTY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + DatabasePropertyColumn.KEY.getName()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes();
  }
}
