//package declaration
package ch.nolix.system.noderawschema.schemawriter;

//own imports
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedFieldTypeDto;

//class
public final class ParameterizedPropertyTypeNodeMapper {

  //method
  public Node createParameterizedPropertyTypeNodeFrom(final IParameterizedFieldTypeDto parameterizedPropertyType) {
    return switch (parameterizedPropertyType.getFieldType().getBaseType()) {
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
      StructureHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.PROPERTY_TYPE,
        baseParameterizedBackReferenceType.getFieldType().toString()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.DATA_TYPE,
        baseParameterizedBackReferenceType.getDataType().name()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.BACK_REFERENCED_COLUMN_ID,
        baseParameterizedBackReferenceType.getBackReferencedColumnId()));
  }

  //method
  private Node createParameterizedPropertyTypeNodeFrom(
    final IBaseParameterizedReferenceTypeDto baseParameterizedReferenceType) {
    return Node.withHeaderAndChildNode(
      StructureHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.PROPERTY_TYPE,
        baseParameterizedReferenceType.getFieldType().toString()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.DATA_TYPE,
        baseParameterizedReferenceType.getDataType().name()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.REFERENCED_TABLE_ID,
        baseParameterizedReferenceType.getReferencedTableId()));
  }

  //method
  private Node createParameterizedPropertyTypeNodeFrom(
    final IBaseParameterizedValueTypeDto baseParameterizedValueType) {
    return Node.withHeaderAndChildNode(
      StructureHeaderCatalogue.PARAMETERIZED_PROPERTY_TYPE,
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.PROPERTY_TYPE,
        baseParameterizedValueType.getFieldType().toString()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.DATA_TYPE,
        baseParameterizedValueType.getDataType().name()));
  }
}
