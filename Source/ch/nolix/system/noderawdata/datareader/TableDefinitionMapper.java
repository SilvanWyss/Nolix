package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.sqlrawdata.schemaview.TableView;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.ITableView;

final class TableDefinitionMapper {

  private static final ColumnDefinitionMapper COLUMN_DEFINITION_MAPPER = new ColumnDefinitionMapper();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  public ITableView createTableDefinitionFromTableNode(final IMutableNode<?> tableNode) {
    return new TableView(
      getTableIdFromTableNode(tableNode),
      getTableNameFromTableNode(tableNode),
      getContentColumnDefinitionsFromTableNode(tableNode));
  }

  private IContainer<IColumnView> getContentColumnDefinitionsFromTableNode(IMutableNode<?> tableNode) {

    final ILinkedList<IColumnView> columnInfos = LinkedList.createEmpty();
    var columnIndexOnEntityNode = 3;
    for (final var cn : getStoredColumnNodesInOrderFromTableNode(tableNode)) {

      columnInfos.addAtEnd(
        COLUMN_DEFINITION_MAPPER.createColumnDefinitionFromColumnNode(
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
