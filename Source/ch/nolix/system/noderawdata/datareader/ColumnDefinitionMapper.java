package ch.nolix.system.noderawdata.datareader;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.ParameterizedFieldTypeNodeSearcher;
import ch.nolix.system.sqlrawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

public final class ColumnDefinitionMapper {

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final ParameterizedFieldTypeNodeSearcher PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER = //
  new ParameterizedFieldTypeNodeSearcher();

  public IColumnInfo createColumnDefinitionFromColumnNode(
    final IMutableNode<?> columnNode,
    final int columnIndexOnEntityNode) {
    return new ColumnInfo(
      getColumnIdFromColumnNode(columnNode),
      getColumnNameFromColumnNode(columnNode),
      getColumnFieldTypeFromColumnNode(columnNode),
      getColumnDataTypeFromColumnNode(columnNode),
      columnIndexOnEntityNode);
  }

  private DataType getColumnDataTypeFromColumnNode(final IMutableNode<?> columnNode) {
    return getDataTypeFromParameterizedFieldTypeNode(
      COLUMN_NODE_SEARCHER.getStoredParameterizedFieldTypeNodeFromColumnNode(columnNode));
  }

  private ContentType getColumnFieldTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var parameterizedFieldTypeNode = //
    COLUMN_NODE_SEARCHER.getStoredParameterizedFieldTypeNodeFromColumnNode(columnNode);

    final var fieldTypeNode = //
    PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredFieldTypeNodeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode);

    return ContentType.fromSpecification(fieldTypeNode);
  }

  private String getColumnIdFromColumnNode(final IMutableNode<?> columnNode) {
    return COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  private String getColumnNameFromColumnNode(final IMutableNode<?> columnNode) {
    return COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  private DataType getDataTypeFromDataTypeNode(final IMutableNode<?> dataTypeNode) {
    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  private DataType getDataTypeFromParameterizedFieldTypeNode(IMutableNode<?> parameterizedFieldTypeNode) {
    return getDataTypeFromDataTypeNode(
      PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER.getStoredDataTypeNodeFromParameterizedFieldTypeNode(
        parameterizedFieldTypeNode));
  }
}
