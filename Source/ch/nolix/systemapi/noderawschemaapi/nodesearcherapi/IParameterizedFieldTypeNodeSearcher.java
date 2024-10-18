package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

public interface IParameterizedFieldTypeNodeSearcher {

  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromFieldTypeNode(
    IMutableNode<?> parameterizedFieldTypeNode);

  IMutableNode<?> getStoredDataTypeNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedFieldTypeNode);

  IMutableNode<?> getStoredFieldTypeNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedFieldTypeNode);

  IMutableNode<?> getStoredReferencedTableIdNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedFieldTypeNode);
}
