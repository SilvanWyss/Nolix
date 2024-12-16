package ch.nolix.systemapi.rawschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.TableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface ISchemaReader extends GroupCloseable {

  boolean columnIsEmpty(String tableName, String columnName);

  int getTableCount();

  IContainer<ColumnDto> loadColumnsByTableName(String tableName);

  IContainer<ColumnDto> loadColumnsByTableId(String tableId);

  IFlatTableDto loadFlatTableById(String id);

  IFlatTableDto loadFlatTableByName(String name);

  IContainer<IFlatTableDto> loadFlatTables();

  TableDto loadTableById(String id);

  TableDto loadTableByName(String name);

  IContainer<TableDto> loadTables();

  ITime loadSchemaTimestamp();
}
