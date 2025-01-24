package ch.nolix.system.sqlrawschema.querycreator;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.ColumnTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableReferenceTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.querycreatorapi.IQueryCreator;

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
    + FixTableType.TABLE.getQualifiedName()
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
    + FixTableType.COLUMN.getQualifiedName()
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
  public String createQueryToLoadCoumnsByTableName(final String tableName) {
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
    + FixTableType.COLUMN.getQualifiedName()
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
    + FixTableType.TABLE.getQualifiedName()
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
    + FixTableType.TABLE.getQualifiedName()
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
    + FixTableType.TABLE.getQualifiedName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return //
    "SELECT "
    + DatabasePropertyTableColumn.VALUE.getLabel()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.DATABASE_PROPERTY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + DatabasePropertyTableColumn.KEY.getLabel()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createQueryToLoadTableReferences(final String referenceColumnId) {
    return //
    "SELECT "
    + TableReferenceTableColumn.REFERENCE_COLUMN_ID.getName()
    + ", "
    + TableReferenceTableColumn.REFERENCED_TABLIE_ID.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.TABLE_REFERENCE.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + TableReferenceTableColumn.REFERENCE_COLUMN_ID.getName()
    + " = '"
    + referenceColumnId
    + "';";
  }
}
