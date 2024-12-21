package ch.nolix.system.noderawdata.datareader;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.ContentModelNodeSearcher;
import ch.nolix.system.sqlrawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

public final class ColumnDefinitionMapper {

  private static final IColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final ContentModelNodeSearcher PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER = //
  new ContentModelNodeSearcher();

  public IColumnInfo createColumnDefinitionFromColumnNode(
    final IMutableNode<?> columnNode,
    final int columnIndexOnEntityNode) {
    return //
    new ColumnInfo(
      COLUMN_NODE_SEARCHER.getColumnIdFromColumnNode(columnNode),
      COLUMN_NODE_SEARCHER.getColumnNameFromColumnNode(columnNode),
      COLUMN_NODE_SEARCHER.getContentTypeFromColumnNode(columnNode),
      getColumnDataTypeFromColumnNode(columnNode),
      columnIndexOnEntityNode);
  }

  private DataType getColumnDataTypeFromColumnNode(final IMutableNode<?> columnNode) {
    return getDataTypeFromParameterizedFieldTypeNode(
      COLUMN_NODE_SEARCHER.getStoredParameterizedFieldTypeNodeFromColumnNode(columnNode));
  }

  private DataType getDataTypeFromDataTypeNode(final IMutableNode<?> dataTypeNode) {
    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  private DataType getDataTypeFromParameterizedFieldTypeNode(IMutableNode<?> parameterizedFieldTypeNode) {
    return getDataTypeFromDataTypeNode(
      PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER.getStoredDataTypeNodeFromContentModelNode(
        parameterizedFieldTypeNode));
  }
}
