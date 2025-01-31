package ch.nolix.system.sqlrawdata.databaseinspector;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.system.rawdata.schemaviewmapper.TableViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;
import ch.nolix.systemapi.rawschemaapi.adapterapi.ISchemaReader;

public final class DatabaseInspector {

  private static final TableViewDtoMapper TABLE_DEFINITION_MAPPER = new TableViewDtoMapper();

  public IContainer<TableSchemaViewDto> createTableDefinitionsFrom(final ISchemaReader schemaAdapter) {
    return schemaAdapter.loadTables().to(TABLE_DEFINITION_MAPPER::mapTableDtoToTableViewDto);
  }
}
