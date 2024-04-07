//package declaration
package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//interface
public interface IParameterizedFieldTypeNodeSearcher {

  //method declaration
  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromFieldTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode);

  //method declaration
  IMutableNode<?> getStoredDataTypeNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode);

  //method declaration
  IMutableNode<?> getStoredFieldTypeNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode);

  //method declaration
  IMutableNode<?> getStoredReferencedTableIdNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode);
}
