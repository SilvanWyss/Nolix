package ch.nolix.systemapi.sqlrawdataapi.querycreatorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

public interface IEntityQueryCreator {

  String createQueryToCountEntitiesWithGivenId(String tableName, String id);

  String createQueryToCountEntitiesWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  String createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
    String tableName,
    String columnName,
    String value,
    IContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadEntitiesOfTable(TableViewDto tableView);

  String createQueryToLoadEntity(String id, TableViewDto tableView);

  String createQueryToLoadSchemaTimestamp();
}
