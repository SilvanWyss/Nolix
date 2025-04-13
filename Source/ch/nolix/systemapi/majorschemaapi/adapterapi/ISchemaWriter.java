package ch.nolix.systemapi.majorschemaapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.midschemaapi.modelapi.IContentModelDto;

public interface ISchemaWriter extends IResettableChangeSaver {

  void addColumn(String tableName, ColumnDto column);

  void addTable(TableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteTable(String tableName);

  void renameColumn(String tableName, String columnName, String newColumnName);

  void renameTable(String tableName, String newTableName);

  void setContentModels(String tableName, String columnName, IContainer<IContentModelDto> contentModels);
}
