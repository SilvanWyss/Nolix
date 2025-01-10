package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IContentModelNodeSearcher;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ColumnNodeSearcher implements IColumnNodeSearcher {

  private static final IContentModelNodeSearcher CONTENT_MODEL_NODE_SEARCHER = new ContentModelNodeSearcher();

  @Override
  public ContentType getColumnContentTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var contentModelNode = getStoredContentModelNodeFromColumnNode(columnNode);

    final var fieldTypeNode = //
    CONTENT_MODEL_NODE_SEARCHER.getStoredContentTypeNodeFromContentModelNode(contentModelNode);

    return ContentType.fromSpecification(fieldTypeNode);
  }

  @Override
  public DataType getColumnDataTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var contentModelNode = getStoredContentModelNodeFromColumnNode(columnNode);

    return CONTENT_MODEL_NODE_SEARCHER.getDataTypeFromContentModelNode(contentModelNode);
  }

  @Override
  public String getColumnIdFromColumnNode(final IMutableNode<?> columnNode) {
    return getStoredIdNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  @Override
  public String getColumnNameFromColumnNode(final IMutableNode<?> columnNode) {
    return getStoredNameNodeFromColumnNode(columnNode).getSingleChildNodeHeader();
  }

  @Override
  public IMutableNode<?> getStoredContentModelNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.CONTENT_MODEL);
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.ID);
  }

  @Override
  public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.NAME);
  }
}
