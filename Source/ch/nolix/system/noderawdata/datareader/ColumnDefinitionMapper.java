package ch.nolix.system.noderawdata.datareader;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.ContentModelNodeSearcher;
import ch.nolix.system.sqlrawdata.schemaview.ColumnView;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.rawdataapi.schemaviewapi.IColumnView;

public final class ColumnDefinitionMapper {

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final ContentModelNodeSearcher CONTENT_MODEL_NODE_SEARCHER = new ContentModelNodeSearcher();

  public IColumnView createColumnDefinitionFromColumnNode(
    final IMutableNode<?> columnNode,
    final int columnIndexOnEntityNode) {
    return //
    new ColumnView(
      COLUMN_NODE_SEARCHER.getColumnIdFromColumnNode(columnNode),
      COLUMN_NODE_SEARCHER.getColumnNameFromColumnNode(columnNode),
      COLUMN_NODE_SEARCHER.getContentTypeFromColumnNode(columnNode),
      getColumnDataTypeFromColumnNode(columnNode),
      columnIndexOnEntityNode);
  }

  private DataType getColumnDataTypeFromColumnNode(final IMutableNode<?> columnNode) {
    return getDataTypeFromContentModelNode(
      COLUMN_NODE_SEARCHER.getStoredContentModelNodeFromColumnNode(columnNode));
  }

  private DataType getDataTypeFromDataTypeNode(final IMutableNode<?> dataTypeNode) {
    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  private DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode) {
    return getDataTypeFromDataTypeNode(
      CONTENT_MODEL_NODE_SEARCHER.getStoredDataTypeNodeFromContentModelNode(
        contentModelNode));
  }
}
