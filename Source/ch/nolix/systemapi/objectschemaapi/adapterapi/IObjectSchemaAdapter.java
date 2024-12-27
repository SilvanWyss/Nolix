package ch.nolix.systemapi.objectschemaapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectschemaapi.modelapi.IColumn;
import ch.nolix.systemapi.objectschemaapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.modelapi.ITable;
import ch.nolix.systemapi.rawschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.flatdto.FlatTableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IObjectSchemaAdapter {

  void addColumnToTable(ITable table, IColumn column);

  void addTable(ITable table);

  boolean columnIsEmpty(IColumn column);

  void deleteColumn(IColumn column);

  void deleteTable(ITable table);

  int getTableCount();

  IContainer<ColumnDto> loadColumnsOfTable(ITable table);

  IContainer<FlatTableDto> loadFlatTables();

  ITime loadSchemaTimestamp();

  void saveChangesAndReset();

  void setColumnName(IColumn column, String columnName, String newColumnName);

  void setColumnContentModel(IColumn column, IContentModel contentModel);

  void setTableName(String tableName, String newTableName);
}
