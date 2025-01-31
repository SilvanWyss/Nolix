package ch.nolix.system.noderawdata.schemaviewloader;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.schemaviewmodelmapper.TableSchemaViewDtoMapper;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.schemaviewloaderapi.ITableViewLoader;
import ch.nolix.systemapi.noderawdataapi.schemaviewmodelmapperapi.ITableSchemaViewDtoMapper;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IDatabaseNodeSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewmodel.TableSchemaViewDto;

/**
 * @author Silvan Wyss
 * @version 2021-10-19
 */
public final class TableViewLoader implements ITableViewLoader {

  private static final IDatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final ITableSchemaViewDtoMapper TABLE_VIEW_DTO_MAPPER = new TableSchemaViewDtoMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<TableSchemaViewDto> loadTableViewsFromNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromNodeDatabase(nodeDatabase);

    return tableNodes.to(TABLE_VIEW_DTO_MAPPER::mapTableNodeToTableViewDto);
  }
}
