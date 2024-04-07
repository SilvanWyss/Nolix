//package declaration
package ch.nolix.system.noderawschema.schemareader;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.system.objectschema.schemadto.ParameterizedFieldTypeDto;

//class
final class ColumnDtoMapper {

  //constant
  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  //constant
  private static final ParameterizedPropertyTypeDtoMapper PARAMETERIZED_PROPERTY_TYPE_DTO_MAPPER = //
  new ParameterizedPropertyTypeDtoMapper();

  //method
  public ColumnDto createColumnDtoFromColumnNode(final IMutableNode<?> columnNode) {
    return new ColumnDto(
      getIdFromColumnNode(columnNode),
      getNameFromColumnNode(columnNode),
      createParameterizedPropertyTypeFromColumnNode(columnNode));
  }

  //method
  private String getIdFromColumnNode(final IMutableNode<?> columnNode) {
    return COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  //method
  private String getNameFromColumnNode(final IMutableNode<?> columnNode) {
    return COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  //method
  private ParameterizedFieldTypeDto createParameterizedPropertyTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var parameterizedPropertyTypeNode = COLUMN_NODE_SEARCHER
      .getStoredParameterizedFieldTypeNodeFromColumnNode(columnNode);

    return PARAMETERIZED_PROPERTY_TYPE_DTO_MAPPER.createParameterizedProeprtyTypeDtoFromParameterizedPropertyTypeNode(
      parameterizedPropertyTypeNode);
  }
}
