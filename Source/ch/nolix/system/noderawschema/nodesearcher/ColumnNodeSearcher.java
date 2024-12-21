package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IColumnNodeSearcher;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IContentModelNodeSearcher;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public final class ColumnNodeSearcher implements IColumnNodeSearcher {

  private final IContentModelNodeSearcher CONTENT_MODEL_NODE_SEARCHER = new ContentModelNodeSearcher();

  @Override
  public boolean columnNodeContainsEntityNode(final IMutableNode<?> columnNode) {
    return columnNode.containsChildNodeWithHeader(NodeHeaderCatalogue.ENTITY);
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
  public ContentType getContentTypeFromColumnNode(final IMutableNode<?> columnNode) {

    final var contentModelNode = getStoredParameterizedFieldTypeNodeFromColumnNode(columnNode);

    final var fieldTypeNode = //
    CONTENT_MODEL_NODE_SEARCHER.getStoredContentTypeNodeFromContentModelNode(contentModelNode);

    return ContentType.fromSpecification(fieldTypeNode);
  }

  @Override
  public IMutableNode<?> getStoredIdNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.ID);
  }

  @Override
  public IMutableNode<?> getStoredNameNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.NAME);
  }

  @Override
  public IMutableNode<?> getStoredParameterizedFieldTypeNodeFromColumnNode(final IMutableNode<?> columnNode) {
    return columnNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.CONTENT_MODEL);
  }
}
