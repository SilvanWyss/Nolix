package ch.nolix.system.sqlrawdata.querycreator;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlrawdataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.FixTableType;
import ch.nolix.systemapi.sqlrawschemaapi.databasestructure.TableNameQualifyingPrefix;

public final class EntityQueryCreator implements IEntityQueryCreator {

  @Override
  public String createQueryToCountEntitiesWithGivenId(final String tableName, final String id) {
    return "SELECT COUNT(Id) FROM "
    + TableNameQualifyingPrefix.E
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
    + TableNameQualifyingPrefix.E
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
    + TableNameQualifyingPrefix.E
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
  public String createQueryToLoadEntitiesOfTable(final TableSchemaViewDto tableView) {
    return "SELECT Id, SaveStamp, "
    + tableView.columnSchemaViews().to(ColumnSchemaViewDto::name).toStringWithSeparator(", ")
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + TableNameQualifyingPrefix.E + tableView.name()
    + ";";
  }

  @Override
  public String createQueryToLoadEntity(String id, TableSchemaViewDto tableView) {
    return "SELECT Id, SaveStamp, "
    + tableView.columnSchemaViews().to(ColumnSchemaViewDto::name).toStringWithSeparator(", ")
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + TableNameQualifyingPrefix.E + tableView.name()
    + " WHERE Id = '"
    + id
    + "';";
  }

  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return "SELECT "
    + DatabasePropertyTableColumn.VALUE.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTableType.DATABASE_PROPERTY.getQualifiedName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + DatabasePropertyTableColumn.KEY.getName()
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP.getLabel()
    + "';";
  }
}
