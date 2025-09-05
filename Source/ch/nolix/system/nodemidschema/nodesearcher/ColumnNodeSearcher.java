package ch.nolix.system.nodemidschema.nodesearcher;

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
    final var contentModelNode = getStoredContentModelNodeFromColumnNode(columnNode);

    return CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode);
  }

  @Override
  public FieldType getColumnFieldTypeFromColumnNode(final IMutableNode<?> columnNode) {
    final var contentModelNode = getStoredContentModelNodeFromColumnNode(columnNode);

    final var fieldTypeNode = //
    CONTENT_MODEL_NODE_SEARCHER.getStoredFieldTypeNodeFromContentModelNode(contentModelNode);

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
  public IMutableNode<?> getStoredContentModelNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.CONTENT_MODEL);
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.ID);
  }

  @Override
  public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.NAME);
  }
}
