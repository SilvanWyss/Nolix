package ch.nolix.system.sqlrawdata.querycreator;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.MetaDataTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableType;

public final class EntityQueryCreator implements IEntityQueryCreator {

  @Override
  public String createQueryToCountEntitiesWithGivenId(final String tableName, final String id) {
    return "SELECT COUNT(Id) FROM "
    + TableType.ENTITY_TABLE.getTableNameQualifyingPrefix()
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
    + TableType.ENTITY_TABLE.getTableNameQualifyingPrefix()
    + tableName
    + SpaceEnclosedSqlKeywordCatalog.WHERE
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
    + TableType.ENTITY_TABLE.getTableNameQualifyingPrefix()
    + tableName
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + columnName
    + " = '"
    + value
    + "' AND Id NOT IN ("
    + entitiesToIgnoreIds.toString()
    + ");";
  }

  @Override
  public String createQueryToLoadEntitiesOfTable(final TableViewDto tableView) {
    return "SELECT Id, SaveStamp, "
    + tableView.columnViews().to(ColumnViewDto::name).toStringWithSeparator(", ")
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + TableType.ENTITY_TABLE.getTableNameQualifyingPrefix() + tableView.name()
    + ";";
  }

  @Override
  public String createQueryToLoadEntity(String id, TableViewDto tableView) {
    return "SELECT Id, SaveStamp, "
    + tableView.columnViews().to(ColumnViewDto::name).toStringWithSeparator(", ")
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + TableType.ENTITY_TABLE.getTableNameQualifyingPrefix() + tableView.name()
    + " WHERE Id = '"
    + id
    + "';";
  }

  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return "SELECT "
    + DatabasePropertyTableColumn.VALUE.getLabel()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + MetaDataTableType.DATABASE_PROPERTY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + DatabasePropertyTableColumn.KEY.getLabel()
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
    + "';";
  }
}
