//package declaration
package ch.nolix.system.sqlrawschema.schemareader;

//own imports
import ch.nolix.system.sqlrawschema.columntable.ColumnTableColumn;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqlrawschema.metadatatable.MetaDataTableType;
import ch.nolix.system.sqlrawschema.structure.SchemaTableType;
import ch.nolix.system.sqlrawschema.tabletable.TableTableColumn;

//class
final class QueryCreator {

  //method
  public String createQueryToGetTableCount() {
    return "SELECT COUNT("
    + TableTableColumn.ID.getName()
    + ") FROM "
    + SchemaTableType.TABLE.getQualifiedName()
    + ";";
  }

  //method
  public String createQueryToLoadCoumnsByTableId(final String tableId) {
    return "SELECT "
    + ColumnTableColumn.ID.getName()
    + ", "
    + ColumnTableColumn.NAME.getName()
    + ", "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.PROPERTY_TYPE.getName()
    + ", "
    + ColumnTableColumn.DATA_TYPE.getName()
    + ", "
    + ColumnTableColumn.REFERENCED_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + " FROM "
    + SchemaTableType.COLUMN.getQualifiedName()
    + " WHERE "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableId
    + "'";
  }

  //method
  public String createQueryToLoadCoumnsByTableName(final String tableName) {
    return "SELECT "
    + ColumnTableColumn.ID.getName()
    + ", "
    + ColumnTableColumn.NAME.getName()
    + ", "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.PROPERTY_TYPE.getName()
    + ", "
    + ColumnTableColumn.DATA_TYPE.getName()
    + ", "
    + ColumnTableColumn.REFERENCED_TABLE_ID.getName()
    + ", "
    + ColumnTableColumn.BACK_REFERENCED_COLUM_ID.getName()
    + " FROM "
    + SchemaTableType.COLUMN.getQualifiedName()
    + " WHERE "
    + ColumnTableColumn.PARENT_TABLE_ID.getName()
    + " = '"
    + tableName
    + "'";
  }

  //method
  public String createQueryToLoadFlatTableById(final String id) {
    return "SELECT "
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + " FROM "
    + SchemaTableType.TABLE.getQualifiedName()
    + " WHERE "
    + TableTableColumn.ID.getName()
    + " = '"
    + id
    + "'";
  }

  //method
  public String createQueryToLoadFlatTableByName(final String name) {
    return "SELECT "
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + " FROM "
    + SchemaTableType.TABLE.getQualifiedName()
    + " WHERE "
    + TableTableColumn.NAME.getName()
    + " = '"
    + name
    + "'";
  }

  //method
  public String createQueryToLoadFlatTables() {
    return "SELECT "
    + TableTableColumn.ID.getName()
    + ", "
    + TableTableColumn.NAME.getName()
    + " FROM "
    + SchemaTableType.TABLE.getQualifiedName();
  }

  //method
  public String createQueryToLoadSchemaTimestamp() {
    return "SELECT "
    + DatabasePropertySystemTableColumn.VALUE.getLabel()
    + " FROM "
    + MetaDataTableType.DATABASE_PROPERTY.getQualifiedName()
    + " WHERE "
    + DatabasePropertySystemTableColumn.KEY.getLabel()
    + " = "
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabelInQuotes();
  }
}
