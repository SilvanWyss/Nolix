package ch.nolix.system.sqlmidschema.querycreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.midschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.ColumnTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.TableTableColumn;
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
    + TableTableColumn.ID.getName()
    + ") FROM "
    + FixTableType.TABLE.getName()
    + ";";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadCoumnsByTableId(final String tableId) {
    return //
    "SELECT "
    + ColumnTableColumn.ID.getName()
    + ", "
    + ColumnTableColumn.NAME.getName()
    + ", "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.CONTENT_TYPE.getName()
    + ", "
    + ColumnTableColumn.DATA_TYPE.getName()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
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
    + ColumnTableColumn.ID.getName()
    + ", "
    + ColumnTableColumn.NAME.getName()
    + ", "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.CONTENT_TYPE.getName()
    + ", "
    + ColumnTableColumn.DATA_TYPE.getName()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.COLUMN.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
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
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableTableColumn.ID.getName()
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
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.TABLE.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableTableColumn.NAME.getName()
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
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.TABLE.getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return //
    "SELECT "
    + DatabasePropertyTableColumn.VALUE.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.DATABASE_PROPERTY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + DatabasePropertyTableColumn.KEY.getName()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getNameInQuotes();
  }
}
