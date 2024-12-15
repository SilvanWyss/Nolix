package ch.nolix.system.noderawschema.schemareader;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.system.objectschema.schemadto.AbstractContentModelDto;

final class ColumnDtoMapper {

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final ParameterizedFieldTypeDtoMapper PARAMETERIZED_PROPERTY_TYPE_DTO_MAPPER = //
  new ParameterizedFieldTypeDtoMapper();

  public ColumnDto createColumnDtoFromColumnNode(final IMutableNode<?> columnNode) {
    return new ColumnDto(
      getIdFromColumnNode(columnNode),
      getNameFromColumnNode(columnNode),
      createParameterizedFieldTypeFromColumnNode(columnNode));
  }

  private String getIdFromColumnNode(final IMutableNode<?> columnNode) {
    return COLUMN_NODE_SEARCHER.getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  private String getNameFromColumnNode(final IMutableNode<?> columnNode) {
    return COLUMN_NODE_SEARCHER.getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  private AbstractContentModelDto createParameterizedFieldTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var parameterizedFieldTypeNode = COLUMN_NODE_SEARCHER
      .getStoredParameterizedFieldTypeNodeFromColumnNode(columnNode);

    return PARAMETERIZED_PROPERTY_TYPE_DTO_MAPPER.createParameterizedProeprtyTypeDtoFromParameterizedFieldTypeNode(
      parameterizedFieldTypeNode);
  }
}
