package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IContentModelNodeSearcher {

  String getBackReferencedColumnIdFromContentModelNode(IMutableNode<?> contentModelNode);

  ContentType getContentTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  DataType getDataTypeFromContentModelNode(IMutableNode<?> contentModelNode);

  IContainer<String> getReferencedTableIdsFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredContentTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredDataTypeNodeFromContentModelNode(IMutableNode<?> contentModelNode);

  IMutableNode<?> getStoredReferencedTableIdsNodeFromContentModelNode(IMutableNode<?> contentModelNode);
}
