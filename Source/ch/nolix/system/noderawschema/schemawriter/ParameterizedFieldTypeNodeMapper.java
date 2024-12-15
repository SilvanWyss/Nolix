package ch.nolix.system.noderawschema.schemawriter;

import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.noderawschema.structure.StructureHeaderCatalogue;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IAbstractValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IContentModelDto;

public final class ParameterizedFieldTypeNodeMapper {

  public Node createParameterizedFieldTypeNodeFrom(final IContentModelDto parameterizedFieldType) {
    return switch (parameterizedFieldType.getFieldType().getBaseType()) {
      case BASE_VALUE ->
        createParameterizedFieldTypeNodeFrom((IAbstractValueModelDto) parameterizedFieldType);
      case BASE_REFERENCE ->
        createParameterizedFieldTypeNodeFrom((IAbstractReferenceModelDto) parameterizedFieldType);
      case BASE_BACK_REFERENCE ->
        createParameterizedFieldTypeNodeFrom((IAbstractBackReferenceModelDto) parameterizedFieldType);
      default ->
        throw InvalidArgumentException.forArgument(parameterizedFieldType);
    };
  }

  private Node createParameterizedFieldTypeNodeFrom(
    IAbstractBackReferenceModelDto baseParameterizedBackReferenceType) {
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

  private Node createParameterizedFieldTypeNodeFrom(
    final IAbstractReferenceModelDto baseParameterizedReferenceType) {
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

  private Node createParameterizedFieldTypeNodeFrom(
    final IAbstractValueModelDto baseParameterizedValueType) {
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
