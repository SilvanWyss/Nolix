//package declaration
package ch.nolix.system.noderawschema.nodesearcher;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.noderawschemaapi.nodesearcherapi.IParameterizedFieldTypeNodeSearcher;

//class
public final class ParameterizedFieldTypeNodeSearcher implements IParameterizedFieldTypeNodeSearcher {

  //method
  @Override
  public IMutableNode<?> getStoredBackReferencedColumnIdNodeFromFieldTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(
      StructureHeaderCatalogue.BACK_REFERENCED_COLUMN_ID);
  }

  //method
  @Override
  public IMutableNode<?> getStoredDataTypeNodeFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.DATA_TYPE);
  }

  //method
  @Override
  public IMutableNode<?> getStoredFieldTypeNodeFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.PROPERTY_TYPE);
  }

  //method
  @Override
  public IMutableNode<?> getStoredReferencedTableIdNodeFromParameterizedFieldTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode
      .getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.REFERENCED_TABLE_ID);
  }
}
