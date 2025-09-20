package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.IColumnNodeMapper;
import ch.nolix.systemapi.nodemidschema.nodemapper.IContentModelNodeMapper;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class ColumnNodeMapper implements IColumnNodeMapper {
  private static final IContentModelNodeMapper CONTENT_MODEL_NODE_MAPPER = new ContentModelNodeMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToColumnNode(final ColumnDto columnDto) {
    final var idNode = ColumnNodeChildNodeMapper.mapColumnDtoToIdNode(columnDto);
    final var nameNode = ColumnNodeChildNodeMapper.mapColumnDtoToNameNode(columnDto);
    final var fieldTypeNode = ColumnNodeChildNodeMapper.mapColumnDtoToFieldTypeNode(columnDto);
    final var dataTypeNode = ColumnNodeChildNodeMapper.mapColumnDtoToDataTypeNode(columnDto);
    final var referenceableTableIdsNode = ColumnNodeChildNodeMapper.mapColumnDtoToReferenceableTableIdsNode(columnDto);

    final var backReferenceableColumnIdsNod = //
    ColumnNodeChildNodeMapper.mapColumnDtoToBackReferenceableColumnIdsNode(columnDto);

    final var contentModelNode = CONTENT_MODEL_NODE_MAPPER.mapColumnDtoToContentModelNode(columnDto);

    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.COLUMN,
      idNode,
      nameNode,
      fieldTypeNode,
      dataTypeNode,
      referenceableTableIdsNode,
      backReferenceableColumnIdsNod,
      contentModelNode);
  }
}
