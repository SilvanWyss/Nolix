package ch.nolix.system.noderawdata.datareader;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.sqlrawdata.schemaview.ColumnView;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;

public final class ColumnDefinitionMapper {

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  public IColumnView createColumnDefinitionFromColumnNode(
    final IMutableNode<?> columnNode,
    final int columnIndexOnEntityNode) {
    return //
    new ColumnView(
      COLUMN_NODE_SEARCHER.getColumnIdFromColumnNode(columnNode),
      COLUMN_NODE_SEARCHER.getColumnNameFromColumnNode(columnNode),
      COLUMN_NODE_SEARCHER.getColumnContentTypeFromColumnNode(columnNode),
      COLUMN_NODE_SEARCHER.getColumnDataTypeFromColumnNode(columnNode),
      columnIndexOnEntityNode);
  }
}
