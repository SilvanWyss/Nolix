package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IContentModelNodeSearcher {

  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromContentModelNode(IMutableNode<?> parameterizedFieldTypeNode);

  IMutableNode<?> getStoredContentTypeNodeFromContentModelNode(IMutableNode<?> parameterizedFieldTypeNode);

  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> parameterizedFieldTypeNode);

  IMutableNode<?> getStoredReferencedTableIdNodeFromContentModelNode(IMutableNode<?> parameterizedFieldTypeNode);
}
