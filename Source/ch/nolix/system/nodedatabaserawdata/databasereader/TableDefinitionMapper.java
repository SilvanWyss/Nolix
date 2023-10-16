//package declaration
package ch.nolix.system.nodedatabaserawdata.databasereader;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.TableNodeSearcher;
import ch.nolix.system.sqldatabaserawdata.schemainfo.TableInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.IColumnInfo;
import ch.nolix.systemapi.rawdatabaseapi.schemainfoapi.ITableInfo;

//class
final class TableDefinitionMapper {

  //constant
  private static final ColumnDefinitionMapper COLUMN_DEFINITION_MAPPER = new ColumnDefinitionMapper();

  //constant
  private static final TableNodeSearcher TABLE_NODE_SEARCHER = new TableNodeSearcher();

  //method
  public ITableInfo createTableDefinitionFromTableNode(final IMutableNode<?> tableNode) {
    return new TableInfo(
        getTableIdFromTableNode(tableNode),
        getTableNameFromTableNode(tableNode),
        getContentColumnDefinitionsFromTableNode(tableNode));
  }

  //method
  private IContainer<IColumnInfo> getContentColumnDefinitionsFromTableNode(IMutableNode<?> tableNode) {

    final var columnInfos = new LinkedList<IColumnInfo>();
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

  //method
  private IContainer<? extends IMutableNode<?>> getStoredColumnNodesInOrderFromTableNode(
      final IMutableNode<?> tableNode) {
    return TABLE_NODE_SEARCHER.getStoredColumnNodesFromTableNode(tableNode);
  }

  //method
  private String getTableIdFromTableNode(final IMutableNode<?> tableNode) {
    return TABLE_NODE_SEARCHER.getTableIdFromTableNode(tableNode);
  }

  //method
  private String getTableNameFromTableNode(final IMutableNode<?> tableNode) {
    return TABLE_NODE_SEARCHER.getTableNameFromTableNode(tableNode);
  }
}
