package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.FieldType;

public interface IContentModelNodeSearcher {
  String getBackReferencedColumnIdFromContentModelNode(IMutableNode<?> contentModelNode);

  DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  FieldType getFieldTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  IContainer<String> getReferenceableTableIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredFieldTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredReferenceableTableIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);
}
