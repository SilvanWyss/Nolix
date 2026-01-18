/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.nodemidschema.nodemapper;

import ch.nolix.core.document.node.Node;
import ch.nolix.coreapi.document.node.INode;
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
  public INode<?> mapColumnDtoToBackReferenceableColumnIdsNode(final ColumnDto columnDto) {
    return //
    Node.withHeaderAndChildNodes(
      NodeHeaderCatalog.BACK_REFERENCEABLE_COLUMN_IDS,
      columnDto.backReferenceableColumnIds().to(Node::withHeader));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToDataTypeNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.DATA_TYPE, columnDto.dataType().name());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToFieldTypeNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.FIELD_TYPE, columnDto.fieldType().name());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToIdNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.ID, columnDto.id());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToNameNode(final ColumnDto columnDto) {
    return Node.withHeaderAndChildNode(NodeHeaderCatalog.NAME, columnDto.name());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> mapColumnDtoToReferenceableTableIdsNode(final ColumnDto columnDto) {
    return //
    Node.withHeaderAndChildNodes(
      NodeHeaderCatalog.REFERENCEABLE_TABLE_IDS,
      columnDto.referenceableTableIds().to(Node::withHeader));
  }
}
