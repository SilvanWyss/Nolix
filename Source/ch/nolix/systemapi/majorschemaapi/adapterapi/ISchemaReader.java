package ch.nolix.systemapi.majorschemaapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.majorschemaapi.modelapi.ColumnDto;
import ch.nolix.systemapi.majorschemaapi.modelapi.TableDto;
import ch.nolix.systemapi.midschemaapi.flatmodelapi.FlatTableDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface ISchemaReader extends GroupCloseable {

  boolean columnIsEmpty(String tableName, String columnName);

  IContainer<ColumnDto> loadColumns(String tableName);

  FlatTableDto loadFlatTable(String tableName);

  IContainer<FlatTableDto> loadFlatTables();

  TableDto loadTable(String tableName);

  int loadTableCount();

  IContainer<TableDto> loadTables();

  ITime loadSchemaTimestamp();
}
