package ch.nolix.systemapi.nodemidschema.nodesearcher;

import ch.nolix.coreapi.datamodel.fieldproperty.DataType;
import ch.nolix.coreapi.document.node.IMutableNode;
import ch.nolix.systemapi.midschema.fieldproperty.ContentType;

public interface IColumnNodeSearcher {

  ContentType getColumnContentTypeFromColumnNode(IMutableNode<?> columnNode);

  DataType getColumnDataTypeFromColumnNode(IMutableNode<?> columnNode);

  String getColumnIdFromColumnNode(IMutableNode<?> columnNode);

  String getColumnNameFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredContentModelNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredIdNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredNameNodeFromColumnNode(IMutableNode<?> columnNode);
}
