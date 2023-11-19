//package declaration
package ch.nolix.systemapi.sqlschemaapi.sqlsyntaxapi;

//interface
public interface ISchemaQueryCreator {

  //method declaration
  String createQueryToLoadNameAndDataTypeOfColumns(String tableName);

  //method declaration
  String createQueryToLoadNameOfTables();

  //method declaration
  String createQueryToLoadTable(String tableName);

  //method declaration
  String createQueryToLoadTopFirstRecordWhereColumnIsNotNull(String tableName, String columnName);
}
