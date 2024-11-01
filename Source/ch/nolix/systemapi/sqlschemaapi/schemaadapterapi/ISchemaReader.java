package ch.nolix.systemapi.sqlschemaapi.schemaadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.sqlschemaapi.flatschemadtoapi.IFlatTableDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.IColumnDto;
import ch.nolix.systemapi.sqlschemaapi.schemadtoapi.ITableDto;

public interface ISchemaReader extends GroupCloseable {

  boolean columnsIsEmpty(String tableName, String columnName);

  IContainer<IColumnDto> loadColumns(String tableName);

  IContainer<IFlatTableDto> loadFlatTables();

  IContainer<ITableDto> loadTables();

  boolean tableExists(String tableName);
}
