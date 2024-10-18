package ch.nolix.systemapi.sqlrawdataapi.querycreatorapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

public interface IEntityQueryCreator {

  String createQueryToCountEntitiesWithGivenId(String tableName, String id);

  String createQueryToCountEntitiesWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  String createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
    String tableName,
    String columnName,
    String value,
    IContainer<String> entitiesToIgnoreIds);

  String createQueryToLoadEntitiesOfTable(ITableInfo tableInfo);

  String createQueryToLoadEntity(String id, ITableInfo tableInfo);

  String createQueryToLoadSchemaTimestamp();
}
