package ch.nolix.system.nodemidschema.nodesearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;
import ch.nolix.systemapi.nodemidschema.databasestructure.NodeHeaderCatalog;
import ch.nolix.systemapi.nodemidschema.nodesearcher.IContentModelNodeSearcher;

/**
 * @author Silvan Wyss
 */
public final class ContentModelNodeSearcher implements IContentModelNodeSearcher {
  @Override
  public IContainer<String> getBackReferenceableColumnIdsFromContentModelNode(final IMutableNode<?> contentModelNode) {
    final var backReferenceableColumnIdsNode = //
    getStoredBackReferenceableColumnIdsNodeFromContentModelNode(contentModelNode);

    return backReferenceableColumnIdsNode.getChildNodesHeaders();
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
  public IContainer<String> getReferenceableTableIdsFromContentModelNode(IMutableNode<?> contentModelNode) {
    final var referenceableTableIdsNode = getStoredReferenceableTableIdsNodeFromContentModelNode(contentModelNode);

    return referenceableTableIdsNode.getChildNodesHeaders();
  }

  @Override
  public IMutableNode<?> getStoredBackReferenceableColumnIdsNodeFromContentModelNode(
    final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.BACK_REFERENCEABLE_COLUMN_IDS);
  }

  @Override
  public IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.DATA_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredFieldTypeNodeFromContentModelNode(
    final IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.FIELD_TYPE);
  }

  @Override
  public IMutableNode<?> getStoredReferenceableTableIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode) {
    return contentModelNode.getStoredFirstChildNodeWithHeader(NodeHeaderCatalog.REFERENCEABLE_TABLE_IDS);
  }
}
