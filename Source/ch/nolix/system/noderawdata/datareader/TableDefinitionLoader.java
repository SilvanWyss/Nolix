package ch.nolix.system.noderawdata.datareader;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.schemaviewdtomapper.TableViewDtoMapper;
import ch.nolix.system.noderawschema.nodesearcher.DatabaseNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.schemaviewdtomapperapi.ITableViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

public final class TableDefinitionLoader {

  private static final DatabaseNodeSearcher DATABASE_NODE_SEARCHER = new DatabaseNodeSearcher();

  private static final ITableViewDtoMapper TABLE_VIEW_DTO_MAPPER = new TableViewDtoMapper();

  public IContainer<TableViewDto> loadTableDefinitionsFromNodeDatabase(final IMutableNode<?> nodeDatabase) {

    final var tableNodes = DATABASE_NODE_SEARCHER.getStoredTableNodesFromNodeDatabase(nodeDatabase);

    return tableNodes.to(TABLE_VIEW_DTO_MAPPER::mapTableNodeToTableViewDto);
  }
}
