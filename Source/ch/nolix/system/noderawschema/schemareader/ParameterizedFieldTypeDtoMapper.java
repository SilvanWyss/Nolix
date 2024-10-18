package ch.nolix.system.noderawschema.schemareader;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ParameterizedFieldTypeNodeSearcher;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ParameterizedFieldTypeDto;
import ch.nolix.systemapi.objectdataapi.fieldproperty.ContentType;

public class ParameterizedFieldTypeDtoMapper {

  private static final ParameterizedFieldTypeNodeSearcher PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER = //
  new ParameterizedFieldTypeNodeSearcher();

  public ParameterizedFieldTypeDto createParameterizedProeprtyTypeDtoFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var fieldType = getPropertyTypeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode);

    return switch (fieldType.getBaseType()) {
      case BASE_VALUE ->
        createBaseParameterizedValueTypeDtoFromParameterizedFieldTypeNode(
          parameterizedFieldTypeNode,
          fieldType);
      case BASE_REFERENCE ->
        createBaseParameterizedReferenceTypeDtoFromParameterizedFieldTypeNode(
          parameterizedFieldTypeNode,
          fieldType);
      case BASE_BACK_REFERENCE ->
        createBaseParameterizedBackReferenceTypeDtoFromParameterizedFieldTypeNode(
          parameterizedFieldTypeNode,
          fieldType);
      default ->
        throw InvalidArgumentException.forArgument(fieldType);
    };
  }

  private ParameterizedFieldTypeDto createBaseParameterizedBackReferenceTypeDtoFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode,
    final ContentType contentType) {
    return new BaseParameterizedBackReferenceTypeDto(
      contentType,
      getDataTypeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode),
      getBackReferencedColumnIdFromParameterizedFieldTypeNode(parameterizedFieldTypeNode));
  }

  private ParameterizedFieldTypeDto createBaseParameterizedReferenceTypeDtoFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode,
    final ContentType contentType) {
    return new BaseParameterizedReferenceTypeDto(
      contentType,
      getDataTypeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode),
      getReferencedTableIdFromParameterizedFieldTypeNode(parameterizedFieldTypeNode));
  }

  private BaseParameterizedValueTypeDto createBaseParameterizedValueTypeDtoFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode,
    final ContentType contentType) {
    return new BaseParameterizedValueTypeDto(
      contentType,
      getDataTypeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode));
  }

  private String getBackReferencedColumnIdFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var backReferencedColumnNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredBackReferencedColumnIdNodeFromFieldTypeNode(
        parameterizedFieldTypeNode);

    return backReferencedColumnNode.getSingleChildNodeHeader();
  }

  private DataType getDataTypeFromParameterizedFieldTypeNode(final IMutableNode<?> parameterizedFieldTypeNode) {

    final var dataTypeNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredDataTypeNodeFromParameterizedFieldTypeNode(
        parameterizedFieldTypeNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  private ContentType getPropertyTypeFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var fieldTypeNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredFieldTypeNodeFromParameterizedFieldTypeNode(
        parameterizedFieldTypeNode);

    return ContentType.fromSpecification(fieldTypeNode);
  }

  private String getReferencedTableIdFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var referencedTableIdNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredReferencedTableIdNodeFromParameterizedFieldTypeNode(
        parameterizedFieldTypeNode);

    return referencedTableIdNode.getSingleChildNodeHeader();
  }
}
