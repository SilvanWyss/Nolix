//package declaration
package ch.nolix.system.noderawschema.structure;

//own imports
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;

//class
public final class ParameterizedPropertyTypeNodeSearcher {

  //method
  public IMutableNode<?> getStoredBackReferencedColumnIdNodeFromPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(
      StructureHeaderCatalogue.BACK_REFERENCED_COLUMN_ID);
  }

  //method
  public IMutableNode<?> getStoredDataTypeNodeFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.DATA_TYPE);
  }

  //method
  public IMutableNode<?> getStoredPropertyTypeNodeFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode.getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.PROPERTY_TYPE);
  }

  //method
  public IMutableNode<?> getStoredReferencedTableIdNodeFromParameterizedPropertyTypeNode(
    IMutableNode<?> parameterizedPropertyTypeNode) {
    return parameterizedPropertyTypeNode
      .getStoredFirstChildNodeWithHeader(StructureHeaderCatalogue.REFERENCED_TABLE_ID);
  }
}
