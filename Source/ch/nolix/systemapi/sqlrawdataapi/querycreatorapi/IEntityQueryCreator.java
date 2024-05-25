//package declaration
package ch.nolix.systemapi.sqlrawdataapi.querycreatorapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

//interface
public interface IEntityQueryCreator {

  //method declaration
  String createQueryToCountEntitiesWithGivenId(String tableName, String id);

  //method declaration
  String createQueryToCountEntitiesWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  //method declaration
  String createQueryToCountEntitiesWithGivenValueAtGivenColumnIgnoringGivenEntities(
    String tableName,
    String columnName,
    String value,
    IContainer<String> entitiesToIgnoreIds);

  //method declaration
  String createQueryToLoadEntitiesOfTable(ITableInfo tableInfo);

  //method declaration
  String createQueryToLoadEntity(String id, ITableInfo tableInfo);

  //method declaration
  String createQueryToLoadSchemaTimestamp();
}
