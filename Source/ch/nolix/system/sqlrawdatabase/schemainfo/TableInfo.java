//package declaration
package ch.nolix.system.sqlrawdatabase.schemainfo;

import ch.nolix.core.commontypetool.GlobalStringHelper;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
public record TableInfo(String tableId, String tableName, ImmutableList<IColumnInfo> columnInfos)
implements ITableInfo {

  //constructor
  public TableInfo(
    final String tableId,
    final String tableName,
    final IContainer<IColumnInfo> columnInfos) {
    this(tableId, tableName, ImmutableList.forIterable(columnInfos));
  }

  //constructor
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

  //method
  @Override
  public IColumnInfo getColumnInfoByColumnName(final String columnName) {
    return getColumnInfos().getStoredFirst(cd -> cd.getColumnName().equals(columnName));
  }

  //method
  @Override
  public IContainer<IColumnInfo> getColumnInfos() {
    return columnInfos;
  }

  //method
  @Override
  public String getTableId() {
    return tableId;
  }

  //method
  @Override
  public String getTableName() {
    return tableName;
  }

  //method
  @Override
  public String getTableNameInQuotes() {
    return GlobalStringHelper.getInQuotes(getTableName());
  }
}
