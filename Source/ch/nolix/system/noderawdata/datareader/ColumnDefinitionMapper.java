//package declaration
package ch.nolix.system.noderawdata.datareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.noderawschema.nodesearcher.ParameterizedPropertyTypeNodeSearcher;
import ch.nolix.system.sqlrawdata.schemainfo.ColumnInfo;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;
import ch.nolix.systemapi.rawdataapi.schemainfoapi.IColumnInfo;

//class
public final class ColumnDefinitionMapper {

  //constant
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  //constant
  private static final ParameterizedPropertyTypeNodeSearcher PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER = //
  new ParameterizedPropertyTypeNodeSearcher();

  //method
  public IColumnInfo createColumnDefinitionFromColumnNode(
    final IMutableNode<?> columnNode,
    final int columnIndexOnEntityNode) {
    return new ColumnInfo(
      getColumnIdFromColumnNode(columnNode),
      getColumnNameFromColumnNode(columnNode),
      getColumnPropertyTypeFromColumnNode(columnNode),
      getColumnDataTypeFromColumnNode(columnNode),
      columnIndexOnEntityNode);
  }

  //method
  private DataType getColumnDataTypeFromColumnNode(final IMutableNode<?> columnNode) {
    return getDataTypeFromParameterizedPropertyTypeNode(
      COLUMN_NODE_SEARCHER.getStoredParameterizedPropertyTypeNodeFromColumnNode(columnNode));
  }

  //method
  private String getColumnIdFromColumnNode(final IMutableNode<?> columnNode) {
    return COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  //method
  private String getColumnNameFromColumnNode(final IMutableNode<?> columnNode) {
    return COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  //method
  private FieldType getColumnPropertyTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var parameterizedPropertyTypeNode = COLUMN_NODE_SEARCHER
      .getStoredParameterizedPropertyTypeNodeFromColumnNode(columnNode);

    final var propertyTypeNode = PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER
      .getStoredPropertyTypeNodeFromParameterizedPropertyTypeNode(
        parameterizedPropertyTypeNode);

    return FieldType.fromSpecification(propertyTypeNode);
  }

  //method
  private DataType getDataTypeFromDataTypeNode(final IMutableNode<?> dataTypeNode) {
    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  //method
  private DataType getDataTypeFromParameterizedPropertyTypeNode(IMutableNode<?> parameterizedPropertyTypeNode) {
    return getDataTypeFromDataTypeNode(
      PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER.getStoredDataTypeNodeFromParameterizedPropertyTypeNode(
        parameterizedPropertyTypeNode));
  }
}
