package ch.nolix.systemapi.rawschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.rawschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ITableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface ISchemaReader extends GroupCloseable {

  boolean columnIsEmpty(String tableName, String columnName);

  int getTableCount();

  IContainer<IColumnDto> loadColumnsByTableName(String tableName);

  IContainer<IColumnDto> loadColumnsByTableId(String tableId);

  IFlatTableDto loadFlatTableById(String id);

  IFlatTableDto loadFlatTableByName(String name);

  IContainer<IFlatTableDto> loadFlatTables();

  ITableDto loadTableById(String id);

  ITableDto loadTableByName(String name);

  IContainer<ITableDto> loadTables();

  ITime loadSchemaTimestamp();
}
