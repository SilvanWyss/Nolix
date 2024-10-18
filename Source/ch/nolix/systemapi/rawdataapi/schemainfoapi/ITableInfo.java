package ch.nolix.systemapi.rawdataapi.schemainfoapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public interface ITableInfo {

  IColumnInfo getColumnInfoByColumnId(String columnId);

  IColumnInfo getColumnInfoByColumnName(String columnName);

  IContainer<IColumnInfo> getColumnInfos();

  String getTableId();

  String getTableName();

  String getTableNameInQuotes();
}
