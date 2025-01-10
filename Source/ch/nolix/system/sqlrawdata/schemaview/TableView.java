package ch.nolix.system.sqlrawdata.schemaview;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;

public record TableView(String tableId, String tableName, ImmutableList<ColumnViewDto> columnInfos)
implements ITableView {

  private static final IStringTool STRING_TOOL = new StringTool();

  public TableView(
    final String tableId,
    final String tableName,
    final IContainer<ColumnViewDto> columnInfos) {
    this(tableId, tableName, ImmutableList.forIterable(columnInfos));
  }

  public TableView( //NOSONAR: This implementations checks the given arguments.
    final String tableId,
    final String tableName,
    final ImmutableList<ColumnViewDto> columnInfos) {

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
  public ColumnViewDto getColumnInfoByColumnId(final String columnId) {
    return getColumnInfos().getStoredFirst(ci -> ci.id().equals(columnId));
  }

  @Override
  public ColumnViewDto getColumnInfoByColumnName(final String columnName) {
    return getColumnInfos().getStoredFirst(cd -> cd.name().equals(columnName));
  }

  @Override
  public IContainer<ColumnViewDto> getColumnInfos() {
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
