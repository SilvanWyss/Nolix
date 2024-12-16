package ch.nolix.systemapi.objectschemaapi.rawschemalinkerapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IRawSchemaLinkerAdapter {

  void addColumnToTable(ITable table, IColumn column);

  void addTable(ITable table);

  boolean columnIsEmpty(IColumn column);

  void deleteColumn(IColumn column);

  void deleteTable(ITable table);

  int getTableCount();

  IContainer<ColumnDto> loadColumnsOfTable(ITable table);

  IContainer<IFlatTableDto> loadFlatTables();

  ITime loadSchemaTimestamp();

  void saveChangesAndReset();

  void setColumnName(IColumn column, String columnName, String newColumnName);

  void setColumnParameterizedFieldType(IColumn column, IContentModel contentModel);

  void setTableName(String tableName, String newTableName);
}
