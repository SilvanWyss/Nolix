//package declaration
package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//interface
public interface IParameterizedFieldTypeNodeSearcher {

  //method declaration
  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromFieldTypeNode(
    IMutableNode<?> parameterizedFieldTypeNode);

  //method declaration
  IMutableNode<?> getStoredDataTypeNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedFieldTypeNode);

  //method declaration
  IMutableNode<?> getStoredFieldTypeNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedFieldTypeNode);

  //method declaration
  IMutableNode<?> getStoredReferencedTableIdNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedFieldTypeNode);
}
