package ch.nolix.systemapi.sqlmiddata.querycreator;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschemaview.model.TableViewDto;

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
