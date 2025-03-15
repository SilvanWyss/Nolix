package ch.nolix.systemapi.rawschemaapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.rawschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.TableDto;

public interface ISchemaWriter extends IResettableChangeSaver {

  void addColumn(String tableName, ColumnDto column);

  void addColumns(String tableName, IContainer<ColumnDto> columns);

  void addTable(TableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteTable(String tableName);

  void renameColumn(String tableName, String columnName, String newColumnName);

  void renameTable(String tableName, String newTableName);

  void setContentModel(String tableName, String columnName, IContentModelDto contentModel);
}
