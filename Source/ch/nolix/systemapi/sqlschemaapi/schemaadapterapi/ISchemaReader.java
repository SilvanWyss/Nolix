package ch.nolix.systemapi.sqlschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.sqlschemaapi.flatschemadto.FlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.ColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadto.TableDto;

public interface ISchemaReader extends GroupCloseable {

  boolean columnsIsEmpty(String tableName, String columnName);

  IContainer<ColumnDto> loadColumns(String tableName);

  IContainer<FlatTableDto> loadFlatTables();

  IContainer<TableDto> loadTables();

  boolean tableExists(String tableName);
}
