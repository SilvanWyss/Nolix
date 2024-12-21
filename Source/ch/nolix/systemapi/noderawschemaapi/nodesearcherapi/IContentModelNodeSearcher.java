package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IContentModelNodeSearcher {

  DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredContentTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredReferencedTableIdNodeFromContentModelNode(IMutableNode<?> contentModelNode);
}
