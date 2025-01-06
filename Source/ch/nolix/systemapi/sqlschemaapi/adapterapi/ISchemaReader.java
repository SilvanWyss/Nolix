package ch.nolix.systemapi.sqlschemaapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.sqlschemaapi.dto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.dto.TableDto;
import ch.nolix.systemapi.sqlschemaapi.flatschemadto.FlatTableDto;

public interface ISchemaReader extends GroupCloseable {

  boolean columnsIsEmpty(String tableName, String columnName);

  IContainer<ColumnDto> loadColumns(String tableName);

  IContainer<FlatTableDto> loadFlatTables();

  IContainer<TableDto> loadTables();

  boolean tableExists(String tableName);
}
