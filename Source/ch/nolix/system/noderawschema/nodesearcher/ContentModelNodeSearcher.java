package ch.nolix.system.noderawschema.nodesearcher;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.databasestructureapi.NodeHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IContentModelNodeSearcher;

public final class ContentModelNodeSearcher implements IContentModelNodeSearcher {

  @Override
  public DataType getDataTypeFromContentModelNode(final IMutableNode<?> contentModelNode) {

    final var dataTypeNode = getStoredDataTypeNodeFromContentModelNode(contentModelNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  @Override
  public IMutableNode<?> getStoredBackReferencedColumnIdNodeFromContentModelNode(
    final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID);
  }

  @Override
  public IMutableNode<?> getStoredContentTypeNodeFromContentModelNode(
    final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.CONTENT_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalogue.DATA_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredReferencedTableIdNodeFromContentModelNode(
    final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.REFERENCED_TABLE_ID);
  }
}
