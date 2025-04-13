package ch.nolix.systemapi.majorschemaapi.adapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;

public interface ISchemaWriter extends IResettableChangeSaver {

  void addColumn(String tableName, ColumnDto column);

  void addTable(TableDto table);

  void deleteColumn(String tableName, String columnName);

  void deleteTable(String tableName);

  void renameTable(String tableName, String newTableName);
}
