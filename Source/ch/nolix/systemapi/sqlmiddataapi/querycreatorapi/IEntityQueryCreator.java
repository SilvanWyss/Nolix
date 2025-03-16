package ch.nolix.systemapi.sqlmiddataapi.querycreatorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.middataapi.schemaviewmodel.TableSchemaViewDto;

public interface IEntityQueryCreator {

  String createQueryToCountEntitiesWithGivenId(String tableName, String id);

  String createQueryToCountEntitiesWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  String createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
    String tableName,
    String columnName,
    String value,
    IContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadEntitiesOfTable(TableSchemaViewDto tableView);

  String createQueryToLoadEntity(String id, TableSchemaViewDto tableView);

  String createQueryToLoadSchemaTimestamp();
}
