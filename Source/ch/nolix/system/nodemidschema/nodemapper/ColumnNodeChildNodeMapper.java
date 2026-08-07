/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.systemapi.midschema.model.ColumnDto;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodemapper.IColumnNodeChildNodeMapper;

/**
 * @author Silvan Wyss
 */
public final class ColumnNodeChildNodeMapper implements IColumnNodeChildNodeMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> mapColumnDtoToBackReferenceableColumnIdsNode(final ColumnDto columnDto) {
    return //
    ImmutableNode.withHeaderAndChildNodes(
      NodeHeaderCatalog.BACK_REFERENCEABLE_COLUMN_IDS,
      columnDto.backReferenceableColumnIds().to(ImmutableNode::withHeader));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> mapColumnDtoToDataTypeNode(final ColumnDto columnDto) {
    return ImmutableNode.withHeaderAndChildNode(NodeHeaderCatalog.DATA_TYPE, columnDto.dataType().name());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> mapColumnDtoToFieldTypeNode(final ColumnDto columnDto) {
    return ImmutableNode.withHeaderAndChildNode(NodeHeaderCatalog.FIELD_TYPE, columnDto.fieldType().name());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> mapColumnDtoToIdNode(final ColumnDto columnDto) {
    return ImmutableNode.withHeaderAndChildNode(NodeHeaderCatalog.ID, columnDto.id());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> mapColumnDtoToNameNode(final ColumnDto columnDto) {
    return ImmutableNode.withHeaderAndChildNode(NodeHeaderCatalog.NAME, columnDto.name());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Node<?> mapColumnDtoToReferenceableTableIdsNode(final ColumnDto columnDto) {
    return //
    ImmutableNode.withHeaderAndChildNodes(
      NodeHeaderCatalog.REFERENCEABLE_TABLE_IDS,
      columnDto.referenceableTableIds().to(ImmutableNode::withHeader));
  }
}
