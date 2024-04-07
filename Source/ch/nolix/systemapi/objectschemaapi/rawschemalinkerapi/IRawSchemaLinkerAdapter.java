//package declaration
package ch.nolix.systemapi.objectschemaapi.rawschemalinkerapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IParameterizedFieldType;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IRawSchemaLinkerAdapter {

  //method declaration
  void addColumnToTable(ITable table, IColumn column);

  //method declaration
  void addTable(ITable table);

  //method declaration
  boolean columnIsEmpty(IColumn column);

  //method declaration
  void deleteColumn(IColumn column);

  //method declaration
  void deleteTable(ITable table);

  //method declaration
  int getTableCount();

  //method declaration
  IContainer<IColumnDto> loadColumnsOfTable(ITable table);

  //method declaration
  IContainer<IFlatTableDto> loadFlatTables();

  //method declaration
  ITime loadSchemaTimestamp();

  //method declaration
  void saveChangesAndReset();

  //method declaration
  void setColumnName(IColumn column, String columnName, String newColumnName);

  //method declaration
  void setColumnParameterizedPropertyType(IColumn column, IParameterizedFieldType parameterizedFieldType);

  //method declaration
  void setTableName(String tableName, String newTableName);
}
