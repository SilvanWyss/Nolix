package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public interface IColumnNodeSearcher {

  ContentType getColumnContentTypeFromColumnNode(IMutableNode<?> columnNode);

  String getColumnIdFromColumnNode(IMutableNode<?> columnNode);

  String getColumnNameFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredContentModelNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredIdNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredNameNodeFromColumnNode(IMutableNode<?> columnNode);
}
