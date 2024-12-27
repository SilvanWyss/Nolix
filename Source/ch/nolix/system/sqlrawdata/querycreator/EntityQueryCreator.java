package ch.nolix.system.sqlrawdata.querycreator;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalogue;
import ch.nolix.system.sqlrawschema.databasepropertytable.DatabasePropertyTableColumn;
import ch.nolix.system.sqlrawschema.structure.MetaDataTableType;
import ch.nolix.system.sqlrawschema.structure.TableType;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableInfo;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;

public final class EntityQueryCreator implements IEntityQueryCreator {

  @Override
  public String createQueryToCountEntitiesWithGivenId(final String tableName, final String id) {
    return "SELECT COUNT(Id) FROM "
    + TableType.ENTITY_TABLE.getQualifyingPrefix()
    + tableName
    + " WHERE Id = '"
    + id
    + "';";
  }

  @Override
  public String createQueryToCountEntitiesWithGivenValueAtGivenColumn(
    final String tableName,
    final String columnName,
    final String value) {
    return //
    "SELECT COUNT("
    + columnName
    + ") FROM "
    + TableType.ENTITY_TABLE.getQualifyingPrefix()
    + tableName
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + columnName
    + " = '"
    + value
    + "';";
  }

  @Override
  public String createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
    final String tableName,
    final String columnName,
    final String value,
    final IContainer<String> entitiesToIgnoreIds) {
    return //
    "SELECT COUNT("
    + columnName
    + ") FROM "
    + TableType.ENTITY_TABLE.getQualifyingPrefix()
    + tableName
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + columnName
    + " = '"
    + value
    + "' AND Id NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  @Override
  public String createQueryToLoadEntitiesOfTable(final ITableInfo tableInfo) {
    return "SELECT Id, SaveStamp, "
    + tableInfo.getColumnInfos().to(IColumnInfo::getColumnName).toStringWithSeparator(", ")
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableInfo.getTableName()
    + ";";
  }

  @Override
  public String createQueryToLoadEntity(String id, ITableInfo tableInfo) {
    return "SELECT Id, SaveStamp, "
    + tableInfo.getColumnInfos().to(IColumnInfo::getColumnName).toStringWithSeparator(", ")
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + TableType.ENTITY_TABLE.getQualifyingPrefix() + tableInfo.getTableName()
    + " WHERE Id = '"
    + id
    + "';";
  }

  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return "SELECT "
    + DatabasePropertyTableColumn.VALUE.getLabel()
    + SpaceEnclosedSqlKeywordCatalogue.FROM
    + MetaDataTableType.DATABASE_PROPERTY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalogue.WHERE
    + DatabasePropertyTableColumn.KEY.getLabel()
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
    + "';";
  }
}
