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
public final class ParameterizedFieldTypeNodeMapper {

  //method
  public Node createParameterizedFieldTypeNodeFrom(final IParameterizedFieldTypeDto parameterizedFieldType) {
    return switch (parameterizedFieldType.getFieldType().getBaseType()) {
      case BASE_VALUE ->
        createParameterizedFieldTypeNodeFrom((IBaseParameterizedValueTypeDto) parameterizedFieldType);
      case BASE_REFERENCE ->
        createParameterizedFieldTypeNodeFrom((IBaseParameterizedReferenceTypeDto) parameterizedFieldType);
      case BASE_BACK_REFERENCE ->
        createParameterizedFieldTypeNodeFrom((IBaseParameterizedBackReferenceTypeDto) parameterizedFieldType);
      default ->
        throw InvalidArgumentException.forArgument(parameterizedFieldType);
    };
  }

  //method
  private Node createParameterizedFieldTypeNodeFrom(
    IBaseParameterizedBackReferenceTypeDto baseParameterizedBackReferenceType) {
    return Node.withHeaderAndChildNode(
      StructureHeaderCatalogue.PARAMETERIZED_FIELD_TYPE,
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.FIELD_TYPE,
        baseParameterizedBackReferenceType.getFieldType().toString()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.DATA_TYPE,
        baseParameterizedBackReferenceType.getDataType().name()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.BACK_REFERENCED_COLUMN_ID,
        baseParameterizedBackReferenceType.getBackReferencedColumnId()));
  }

  //method
  private Node createParameterizedFieldTypeNodeFrom(
    final IBaseParameterizedReferenceTypeDto baseParameterizedReferenceType) {
    return Node.withHeaderAndChildNode(
      StructureHeaderCatalogue.PARAMETERIZED_FIELD_TYPE,
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.FIELD_TYPE,
        baseParameterizedReferenceType.getFieldType().toString()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.DATA_TYPE,
        baseParameterizedReferenceType.getDataType().name()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.REFERENCED_TABLE_ID,
        baseParameterizedReferenceType.getReferencedTableId()));
  }

  //method
  private Node createParameterizedFieldTypeNodeFrom(
    final IBaseParameterizedValueTypeDto baseParameterizedValueType) {
    return Node.withHeaderAndChildNode(
      StructureHeaderCatalogue.PARAMETERIZED_FIELD_TYPE,
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.FIELD_TYPE,
        baseParameterizedValueType.getFieldType().toString()),
      Node.withHeaderAndChildNode(
        StructureHeaderCatalogue.DATA_TYPE,
        baseParameterizedValueType.getDataType().name()));
  }
}
