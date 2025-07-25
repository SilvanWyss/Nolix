package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

public interface IContentModelNodeSearcher {

  String getBackReferencedColumnIdFromContentModelNode(IMutableNode<?> contentModelNode);

  ContentType getContentTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  String getReferencedTableIdFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredContentTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredReferencedTableIdNodeFromContentModelNode(IMutableNode<?> contentModelNode);
}
