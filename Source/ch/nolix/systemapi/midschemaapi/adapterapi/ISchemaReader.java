package ch.nolix.systemapi.midschemaapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.midschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.midschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface ISchemaReader extends GroupCloseable {

  boolean columnIsEmpty(String tableName, String columnName);

  int getTableCount();

  IContainer<ColumnDto> loadColumnsByTableName(String tableName);

  IContainer<ColumnDto> loadColumnsByTableId(String tableId);

  FlatTableDto loadFlatTableById(String id);

  FlatTableDto loadFlatTableByName(String name);

  IContainer<FlatTableDto> loadFlatTables();

  TableDto loadTableById(String id);

  TableDto loadTableByName(String name);

  IContainer<TableDto> loadTables();

  ITime loadSchemaTimestamp();
}
