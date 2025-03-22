package ch.nolix.system.sqlmiddata.querycreator;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.sqlapi.syntaxapi.SpaceEnclosedSqlKeywordCatalog;
import ch.nolix.systemapi.middataapi.schemaviewmodel.ColumnSchemaViewDto;
import ch.nolix.systemapi.middataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.midschemaapi.databaseproperty.DatabaseProperty;
import ch.nolix.systemapi.sqlmiddataapi.querycreatorapi.IEntityQueryCreator;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.DatabasePropertyTableColumn;
import ch.nolix.systemapi.sqlmidschemaapi.databasestructure.FixTable;

public final class EntityQueryCreator implements IEntityQueryCreator {

  @Override
  public String createQueryToCountEntitiesWithGivenId(final String tableName, final String id) {
    return "SELECT COUNT(Id) FROM "
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
    + tableView.name()
    + ";";
  }

  @Override
  public String createQueryToLoadEntity(String id, TableSchemaViewDto tableView) {
    return "SELECT Id, SaveStamp, "
    + tableView.columnSchemaViews().to(ColumnSchemaViewDto::name).toStringWithSeparator(", ")
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + tableView.name()
    + " WHERE Id = '"
    + id
    + "';";
  }

  @Override
  public String createQueryToLoadSchemaTimestamp() {
    return "SELECT "
    + DatabasePropertyTableColumn.VALUE.getName()
    + SpaceEnclosedSqlKeywordCatalog.FROM
    + FixTable.DATABASE_PROPERTY.getName()
    + SpaceEnclosedSqlKeywordCatalog.WHERE
    + DatabasePropertyTableColumn.KEY.getName()
    + " = '"
    + DatabaseProperty.SCHEMA_TIMESTAMP.getName()
    + "';";
  }
}
