package ch.nolix.system.sqlrawschema.schemareader;

import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalogue;
import ch.nolix.system.sqlrawschema.columntable.ColumnTableColumn;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertyTableColumn;
import ch.nolix.system.sqlrawschema.structure.MetaDataTableType;
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;
import ch.nolix.system.sqlrawschema.tabletable.TableTableColumn;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;

final class QueryCreator {

  public String createQueryToGetTableCount() {
    return "SELECT COUNT("
    + TableTableColumn.ID.getName()
    + ") FROM "
    + SchemaTableType.TABLE.getQualifiedName()
    + ";";
  }

  public String createQueryToLoadCoumnsByTableId(final String tableId) {
    return "SELECT "
    + ColumnTableColumn.ID.getName()
    + ", "
    + ColumnTableColumn.NAME.getName()
    + ", "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.FIELD_TYPE.getName()
    + ", "
    + ColumnTableColumn.DATA_TYPE.getName()
    + ", "
    + ColumnTableColumn.REFERENCED_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + SchemaTableType.COLUMN.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableId
    + "'";
  }

  public String createQueryToLoadCoumnsByTableName(final String tableName) {
    return "SELECT "
    + ColumnTableColumn.ID.getName()
    + ", "
    + ColumnTableColumn.NAME.getName()
    + ", "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.FIELD_TYPE.getName()
    + ", "
    + ColumnTableColumn.DATA_TYPE.getName()
    + ", "
    + ColumnTableColumn.REFERENCED_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + SchemaTableType.COLUMN.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableName
    + "'";
  }

  public String createQueryToLoadFlatTableById(final String id) {
    return "SELECT "
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + SchemaTableType.TABLE.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + TableTableColumn.ID.getName()
    + " = '"
    + id
    + "'";
  }

  public String createQueryToLoadFlatTableByName(final String name) {
    return "SELECT "
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + SchemaTableType.TABLE.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + TableTableColumn.NAME.getName()
    + " = '"
    + name
    + "'";
  }

  public String createQueryToLoadFlatTables() {
    return "SELECT "
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + SchemaTableType.TABLE.getQualifiedName();
  }

  public String createQueryToLoadSchemaTimestamp() {
    return "SELECT "
    + DatabasePropertyTableColumn.VALUE.getLabel()
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + MetaDataTableType.DATABASE_PROPERTY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + DatabasePropertyTableColumn.KEY.getLabel()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes();
  }
}
