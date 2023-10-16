//package declaration
package ch.nolix.systemapi.rawdatabaseapi.sqlsyntaxapi;

//own imports
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//interface
public interface IEntityQueryCreator {

  //method declaration
  String createQueryToCountEntitiesWithGivenId(String tableName, String id);

  //method declaration
  String createQueryToCountEntitiesWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  //method declaration
  String createQueryToLoadEntitiesOfTable(ITableInfo tableInfo);

  //method declaration
  String createQueryToLoadEntity(String id, ITableInfo tableInfo);

  //method declaration
  String createQueryToLoadSchemaTimestamp();
}
