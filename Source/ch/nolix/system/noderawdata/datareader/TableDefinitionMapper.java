package ch.nolix.system.noderawdata.datareader;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.TableNodeSearcher;
import ch.nolix.system.sqlrawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.ITableInfo;

final class TableDefinitionMapper {

  private static final ColumnDefinitionMapper COLUMN_DEFINITION_MAPPER = new ColumnDefinitionMapper();

  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  public ITableInfo createTableDefinitionFromTableNode(final IMutableNode<?> tableNode) {
    return new TableInfo(
      getTableIdFromTableNode(tableNode),
      getTableNameFromTableNode(tableNode),
      getContentColumnDefinitionsFromTableNode(tableNode));
  }

  private IContainer<IColumnInfo> getContentColumnDefinitionsFromTableNode(IMutableNode<?> tableNode) {

    final ILinkedList<IColumnInfo> columnInfos = LinkedList.createEmpty();
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
