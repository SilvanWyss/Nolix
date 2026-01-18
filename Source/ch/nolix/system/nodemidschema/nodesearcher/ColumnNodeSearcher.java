package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IColumnNodeSearcher;

/**
 * @author Silvan Wyss
 */
public final class ColumnNodeSearcher implements IColumnNodeSearcher {

  @Override
  public IContainer<String> getBackReferenceableColumnIdsFromColumnNode(final IMutableNode<?> columnNode) {
    final var backReferenceableColumnIdsNode = getStoredBackReferenceableColumnIdsNodeFromColumnNode(columnNode);

    return backReferenceableColumnIdsNode.getChildNodesHeaders();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public DataType getColumnDataTypeFromColumnNode(final IMutableNode<?> columnNode) {
    final var dataTypeNode = getStoredDataTypeNodeFromColumnNode(columnNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FieldType getColumnFieldTypeFromColumnNode(final IMutableNode<?> columnNode) {
    final var fieldTypeNode = getStoredFieldTypeNodeFromColumnNode(columnNode);

    return FieldType.fromSpecification(fieldTypeNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColumnIdFromColumnNode(final IMutableNode<?> columnNode) {
    final var idNode = getStoredIdNodeFromColumnNode(columnNode);

    return idNode.getSingleChildNodeHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getColumnNameFromColumnNode(final IMutableNode<?> columnNode) {
    final var columnNameNode = getStoredNameNodeFromColumnNode(columnNode);

    return columnNameNode.getSingleChildNodeHeader();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<String> getReferenceableTableIdsFromColumnNode(IMutableNode<?> columnNode) {
    final var referenceableTableIdsNode = getStoredReferenceableTableIdsNodeFromColumnNode(columnNode);

    return referenceableTableIdsNode.getChildNodesHeaders();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredBackReferenceableColumnIdsNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.BACK_REFERENCEABLE_COLUMN_IDS);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredContentModelNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.CONTENT_MODEL);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredDataTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.DATA_TYPE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredFieldTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.FIELD_TYPE);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ID);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IMutableNode<?> getStoredReferenceableTableIdsNodeFromColumnNode(IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.REFERENCEABLE_TABLE_IDS);
  }
}
