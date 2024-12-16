package ch.nolix.system.noderawschema.schemareader;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ColumnNodeSearcher;
import ch.nolix.system.objectschema.schemadto.ColumnDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;

final class ColumnDtoMapper {

  private static final ColumnNodeSearcher COLUMN_NODE_SEARCHER = new ColumnNodeSearcher();

  private static final ContentModelDtoMapper PARAMETERIZED_PROPERTY_TYPE_DTO_MAPPER = //
  new ContentModelDtoMapper();

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

  private IContentModelDto createParameterizedFieldTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var parameterizedFieldTypeNode = COLUMN_NODE_SEARCHER
      .getStoredParameterizedFieldTypeNodeFromColumnNode(columnNode);

    return PARAMETERIZED_PROPERTY_TYPE_DTO_MAPPER.createContentModelDtoFromContentModelNode(
      parameterizedFieldTypeNode);
  }
}
