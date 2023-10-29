//package declaration
package ch.nolix.system.sqldatabaserawdata.sqlsyntax;

//own imports
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabaseProperty;
import ch.nolix.system.sqldatabaserawschema.databasepropertytable.DatabasePropertySystemTableColumn;
import ch.nolix.system.sqldatabaserawschema.structure.SystemDataTable;
import ch.nolix.system.sqldatabaserawschema.structure.TableType;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;
import ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi.IEntityQueryCreator;

//class
public final class EntityQueryCreator implements IEntityQueryCreator {

  //method
  @Override
  public String createQueryToCountEntitiesWithGivenId(final String tableName, final String id) {
    return "SELECT COUNT(Id) FROM "
    + TableType.ENTITY_TABLE.getNamePrefix()
    + tableName
    + " WHERE Id = '"
    + id
    + "';";
  }

  //method
  @Override
  public String createQueryToCountEntitiesWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return "SELECT COUNT("
    + columnName
    + ") FROM "
    + TableType.ENTITY_TABLE.getNamePrefix()
    + tableName
    + " WHERE "
    + columnName
    + " = '"
    + value
    + "';";
  }

  //method
  @Override
  public String createQueryToLoadEntitiesOfTable(final ITableInfo tableInfo) {
    return "SELECT Id, SaveStamp, "
    + tableInfo.getColumnInfos().to(IColumnInfo::getColumnName).toStringWithSeparator(", ")
    + " FROM "
    + TableType.ENTITY_TABLE.getNamePrefix() + tableInfo.getTableName()
    + ";";
  }

  //method
  @Override
  public String createQueryToLoadEntity(String id, ITableInfo tableInfo) {
    return "SELECT Id, SaveStamp, "
    + tableInfo.getColumnInfos().to(IColumnInfo::getColumnName).toStringWithSeparator(", ")
    + " FROM "
    + TableType.ENTITY_TABLE.getNamePrefix() + tableInfo.getTableName()
    + " WHERE Id = '"
    + id
    + "';";
  }

  //method
  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return "SELECT "
    + DatabasePropertySystemTableColumn.VALUE.getLabel()
    + " FROM "
    + SystemDataTable.DATABASE_PROPERTY.getQualifiedName()
    + " WHERE "
    + DatabasePropertySystemTableColumn.KEY.getLabel()
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
    + "';";
  }
}
