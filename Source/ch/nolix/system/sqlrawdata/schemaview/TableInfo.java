package ch.nolix.system.sqlrawdata.schemaview;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableInfo;

public record TableInfo(String tableId, String tableName, ImmutableList<IColumnInfo> columnInfos)
implements ITableInfo {

  private static final IStringTool STRING_TOOL = new StringTool();

  public TableInfo(
    final String tableId,
    final String tableName,
    final IContainer<IColumnInfo> columnInfos) {
    this(tableId, tableName, ImmutableList.forIterable(columnInfos));
  }

  public TableInfo( //NOSONAR: This implementations checks the given arguments.
    final String tableId,
    final String tableName,
    final ImmutableList<IColumnInfo> columnInfos) {

    if (tableId == null) {
      throw ArgumentIsNullException.forArgumentName("table id");
    }

    if (tableName == null) {
      throw ArgumentIsNullException.forArgumentName("table name");
    }

    if (columnInfos == null) {
      throw ArgumentIsNullException.forArgumentName("column definitions");
    }

    this.tableId = tableId;
    this.tableName = tableName;
    this.columnInfos = columnInfos;
  }

  @Override
  public IColumnInfo getColumnInfoByColumnId(final String columnId) {
    return getColumnInfos().getStoredFirst(ci -> ci.getColumnId().equals(columnId));
  }

  @Override
  public IColumnInfo getColumnInfoByColumnName(final String columnName) {
    return getColumnInfos().getStoredFirst(cd -> cd.getColumnName().equals(columnName));
  }

  @Override
  public IContainer<IColumnInfo> getColumnInfos() {
    return columnInfos;
  }

  @Override
  public String getTableId() {
    return tableId;
  }

  @Override
  public String getTableName() {
    return tableName;
  }

  @Override
  public String getTableNameInQuotes() {
    return STRING_TOOL.getInSingleQuotes(getTableName());
  }
}
