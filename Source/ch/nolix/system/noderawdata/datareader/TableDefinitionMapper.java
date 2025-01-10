package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawdata.schemaviewdtomapper.ColumnViewDtoMapper;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.systemapi.noderawdataapi.schemaviewdtomapperapi.IColumnViewDtoMapper;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.ColumnViewDto;
import ch.nolix.systemapi.rawdataapi.schemaviewdto.TableViewDto;

final class TableDefinitionMapper {

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  private static final IColumnViewDtoMapper COLUMN_VIEW_DTO_MAPPER = new ColumnViewDtoMapper();

  public TableViewDto createTableDefinitionFromTableNode(final IMutableNode<?> tableNode) {
    return new TableViewDto(
      getTableIdFromTableNode(tableNode),
      getTableNameFromTableNode(tableNode),
      getContentColumnDefinitionsFromTableNode(tableNode));
  }

  private IContainer<ColumnViewDto> getContentColumnDefinitionsFromTableNode(IMutableNode<?> tableNode) {

    final ILinkedList<ColumnViewDto> columnInfos = LinkedList.createEmpty();
    var columnIndexOnEntityNode = 3;
    for (final var cn : getStoredColumnNodesInOrderFromTableNode(tableNode)) {

      columnInfos.addAtEnd(
        COLUMN_VIEW_DTO_MAPPER.mapColumnNodeToColumnViewDto(
          cn,
          columnIndexOnEntityNode));

      columnIndexOnEntityNode++;
    }

    return columnInfos;
  }

  private IContainer<? extends IMutableNode<?>> getStoredColumnNodesInOrderFromTableNode(
    final IMutableNode<?> tableNode) {
    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);
  }

  private String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
    return TABLE_NODE_SEARCHER.getTableIdFromTableNode(tableNode);
  }

  private String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
    return TABLE_NODE_SEARCHER.getTableNameFromTableNode(tableNode);
  }
}
