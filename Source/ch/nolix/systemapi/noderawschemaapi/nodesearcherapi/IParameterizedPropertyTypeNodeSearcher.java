//package declaration
package ch.nolix.systemapi.noderawschemaapi.nodesearcherapi;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//interface
public interface IParameterizedPropertyTypeNodeSearcher {

  //method declaration
  IMutableNode<?> getStoredBackReferencedColumnIdNodeFromPropertyTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode);

  //method declaration
  IMutableNode<?> getStoredDataTypeNodeFromParameterizedPropertyTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode);

  //method declaration
  IMutableNode<?> getStoredPropertyTypeNodeFromParameterizedPropertyTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode);

  //method declaration
  IMutableNode<?> getStoredReferencedTableIdNodeFromParameterizedPropertyTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode);
}
