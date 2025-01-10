package ch.nolix.systemapi.rawdataapi.schemaviewapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;

public interface ITableView {

  ColumnViewDto getColumnInfoByColumnId(String columnId);

  ColumnViewDto getColumnInfoByColumnName(String columnName);

  IContainer<ColumnViewDto> getColumnInfos();

  String getTableId();

  String getTableName();

  String getTableNameInQuotes();
}
