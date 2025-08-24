package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IContentModelNodeSearcher;

public final class ContentModelNodeSearcher implements IContentModelNodeSearcher {

  @Override
  public String getBackReferencedColumnIdFromContentModelNode(final IMutableNode<?> contentModelNode) {

    final var backReferencedColumnIdNode = getStoredBackReferencedColumnIdNodeFromContentModelNode(contentModelNode);

    return backReferencedColumnIdNode.getSingleChildNodeHeader();
  }

  @Override
  public DataType getDataTypeFromContentModelNode(final IMutableNode<?> contentModelNode) {

    final var dataTypeNode = getStoredDataTypeNodeFromContentModelNode(contentModelNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  @Override
  public FieldType getFieldTypeFromContentModelNode(final IMutableNode<?> contentModelNode) {

    final var fieldTypeNode = getStoredFieldTypeNodeFromContentModelNode(contentModelNode);

    return FieldType.fromSpecification(fieldTypeNode);
  }

  @Override
  public String getReferencedTableIdFromContentModelNode(final IMutableNode<?> contentModelNode) {

    final var referencedTableIdsNode = getStoredReferencedTableIdNodeFromContentModelNode(contentModelNode);

    return referencedTableIdsNode.getSingleChildNodeHeader();
  }

  @Override
  public IMutableNode<?> getStoredBackReferencedColumnIdNodeFromContentModelNode(
    final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.BACK_REFERENCED_COLUMN_ID);
  }

  @Override
  public IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.DATA_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredFieldTypeNodeFromContentModelNode(
    final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.CONTENT_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredReferencedTableIdNodeFromContentModelNode(final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.REFERENCED_TABLE_ID);
  }
}
