//package declaration
package ch.nolix.system.noderawschema.nodesearcher;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IParameterizedPropertyTypeNodeSearcher;

//class
public final class ParameterizedPropertyTypeNodeSearcher implements IParameterizedPropertyTypeNodeSearcher {

  //method
  @Override
  public IMutableNode<?> getStoredBackReferencedColumnIdNodeFromPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(
      StructureHeaderCatalogue.BACK_REFERENCED_COLUMN_ID);
  }

  //method
  @Override
  public IMutableNode<?> getStoredDataTypeNodeFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.DATA_TYPE);
  }

  //method
  @Override
  public IMutableNode<?> getStoredPropertyTypeNodeFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.PROPERTY_TYPE);
  }

  //method
  @Override
  public IMutableNode<?> getStoredReferencedTableIdNodeFromParameterizedPropertyTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode
      .getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.REFERENCED_TABLE_ID);
  }
}
