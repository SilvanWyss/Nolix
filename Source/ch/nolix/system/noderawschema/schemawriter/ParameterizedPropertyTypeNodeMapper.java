//package declaration
package ch.nolix.system.noderawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.noderawschema.structure.SubNodeHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParameterizedPropertyTypeNodeMapper {

  //method
  public Node createParameterizedPropertyTypeNodeFrom(final IParameterizedPropertyTypeDto parameterizedPropertyType) {
    return switch (parameterizedPropertyType.getPropertyType().getBaseType()) {
      case BASE_VALUE ->
        createParameterizedPropertyTypeNodeFrom((IBaseParameterizedValueTypeDto) parameterizedPropertyType);
      case BASE_REFERENCE ->
        createParameterizedPropertyTypeNodeFrom((IBaseParameterizedReferenceTypeDto) parameterizedPropertyType);
      case BASE_BACK_REFERENCE ->
        createParameterizedPropertyTypeNodeFrom((IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyType);
      default ->
        throw InvalidArgumentException.forArgument(parameterizedPropertyType);
    };
  }

  //method
  private Node createParameterizedPropertyTypeNodeFrom(
    IBaseParameterizedBackReferenceTypeDto baseParameterizedBackReferenceType) {
    return Node.withHeaderAndChildNode(
      SubNodeHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
      Node.withHeaderAndChildNode(
        SubNodeHeaderCatalogue.PROPERTY_TYPE,
        baseParameterizedBackReferenceType.getPropertyType().toString()),
      Node.withHeaderAndChildNode(
        SubNodeHeaderCatalogue.DATA_TYPE,
        baseParameterizedBackReferenceType.getDataType().name()),
      Node.withHeaderAndChildNode(
        SubNodeHeaderCatalogue.BACK_REFERENCED_COLUMN_ID,
        baseParameterizedBackReferenceType.getBackReferencedColumnId()));
  }

  //method
  private Node createParameterizedPropertyTypeNodeFrom(
    final IBaseParameterizedReferenceTypeDto baseParameterizedReferenceType) {
    return Node.withHeaderAndChildNode(
      SubNodeHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
      Node.withHeaderAndChildNode(
        SubNodeHeaderCatalogue.PROPERTY_TYPE,
        baseParameterizedReferenceType.getPropertyType().toString()),
      Node.withHeaderAndChildNode(
        SubNodeHeaderCatalogue.DATA_TYPE,
        baseParameterizedReferenceType.getDataType().name()),
      Node.withHeaderAndChildNode(
        SubNodeHeaderCatalogue.REFERENCED_TABLE_ID,
        baseParameterizedReferenceType.getReferencedTableId()));
  }

  //method
  private Node createParameterizedPropertyTypeNodeFrom(
    final IBaseParameterizedValueTypeDto baseParameterizedValueType) {
    return Node.withHeaderAndChildNode(
      SubNodeHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
      Node.withHeaderAndChildNode(
        SubNodeHeaderCatalogue.PROPERTY_TYPE,
        baseParameterizedValueType.getPropertyType().toString()),
      Node.withHeaderAndChildNode(
        SubNodeHeaderCatalogue.DATA_TYPE,
        baseParameterizedValueType.getDataType().name()));
  }
}
