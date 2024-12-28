package ch.nolix.systemapi.rawdataapi.schemaviewapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ITableView {

  IColumnView getColumnInfoByColumnId(String columnId);

  IColumnView getColumnInfoByColumnName(String columnName);

  IContainer<IColumnView> getColumnInfos();

  String getTableId();

  String getTableName();

  String getTableNameInQuotes();
}
