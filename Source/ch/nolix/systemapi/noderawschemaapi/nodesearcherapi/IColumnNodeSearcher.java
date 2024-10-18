package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IColumnNodeSearcher {

  boolean columnNodeContainsEntityNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredIdNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredNameNodeFromColumnNode(IMutableNode<?> columnNode);

  IMutableNode<?> getStoredParameterizedFieldTypeNodeFromColumnNode(IMutableNode<?> columnNode);
}
