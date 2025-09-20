package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IColumnNodeSearcher;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IContentModelNodeSearcher;

public final class ColumnNodeSearcher implements IColumnNodeSearcher {
  private static final IContentModelNodeSearcher CONTENT_MODEL_NODE_SEARCHER = new ContentModelNodeSearcher();

  @Override
  public DataType getColumnDataTypeFromColumnNode(final IMutableNode<?> columnNode) {
    final var dataTypeNode = getStoredDataTypeNodeFromColumnNode(columnNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  @Override
  public FieldType getColumnFieldTypeFromColumnNode(final IMutableNode<?> columnNode) {
    final var fieldTypeNode = getStoredFieldTypeNodeFromColumnNode(columnNode);

    return FieldType.fromSpecification(fieldTypeNode);
  }

  @Override
  public String getColumnIdFromColumnNode(final IMutableNode<?> columnNode) {
    final var idNode = getStoredIdNodeFromColumnNode(columnNode);

    return idNode.getSingleChildNodeHeader();
  }

  @Override
  public String getColumnNameFromColumnNode(final IMutableNode<?> columnNode) {
    final var columnNameNode = getStoredNameNodeFromColumnNode(columnNode);

    return columnNameNode.getSingleChildNodeHeader();
  }

  @Override
  public IContainer<String> getReferenceableTableIdsFromColumnNode(IMutableNode<?> columnNode) {
    final var referenceableTableIdsNode = getStoredReferenceableTableIdsNodeFromColumnNode(columnNode);

    return referenceableTableIdsNode.getChildNodesHeaders();
  }

  @Override
  public IMutableNode<?> getStoredContentModelNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.CONTENT_MODEL);
  }

  @Override
  public IMutableNode<?> getStoredDataTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.DATA_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredFieldTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.FIELD_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ID);
  }

  @Override
  public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME);
  }

  @Override
  public IMutableNode<?> getStoredReferenceableTableIdsNodeFromColumnNode(IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.REFERENCEABLE_TABLE_IDS);
  }
}
