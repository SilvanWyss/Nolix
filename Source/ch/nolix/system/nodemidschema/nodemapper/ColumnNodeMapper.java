package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.IColumnNodeChildNodeMapper;
import ch.nolix.systemapi.nodemidschema.nodemapper.IColumnNodeMapper;

/**
 * @author Silvan Wyss
 * @version 2021-09-12
 */
public final class ColumnNodeMapper implements IColumnNodeMapper {
  private static final IColumnNodeChildNodeMapper COLUMN_NODE_CHILD_NODE_MAPPER = new ColumnNodeChildNodeMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToColumnNode(final ColumnDto columnDto) {
    final var idNode = COLUMN_NODE_CHILD_NODE_MAPPER.mapColumnDtoToIdNode(columnDto);
    final var nameNode = COLUMN_NODE_CHILD_NODE_MAPPER.mapColumnDtoToNameNode(columnDto);
    final var fieldTypeNode = COLUMN_NODE_CHILD_NODE_MAPPER.mapColumnDtoToFieldTypeNode(columnDto);
    final var dataTypeNode = COLUMN_NODE_CHILD_NODE_MAPPER.mapColumnDtoToDataTypeNode(columnDto);
    final var referenceableTableIdsNode = COLUMN_NODE_CHILD_NODE_MAPPER
      .mapColumnDtoToReferenceableTableIdsNode(columnDto);

    final var backReferenceableColumnIdsNod = //
    COLUMN_NODE_CHILD_NODE_MAPPER.mapColumnDtoToBackReferenceableColumnIdsNode(columnDto);

    return //
    Node.withHeaderAndChildNode(
      NodeHeaderCatalog.COLUMN,
      idNode,
      nameNode,
      fieldTypeNode,
      dataTypeNode,
      referenceableTableIdsNode,
      backReferenceableColumnIdsNod);
  }
}
