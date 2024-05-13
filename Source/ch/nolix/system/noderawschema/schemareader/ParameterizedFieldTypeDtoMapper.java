//package declaration
package ch.nolix.system.noderawschema.schemareader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.datamodelapi.fieldproperty.DataType;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ParameterizedFieldTypeNodeSearcher;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ParameterizedFieldTypeDto;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//class
public class ParameterizedFieldTypeDtoMapper {

  //constant
  private static final ParameterizedFieldTypeNodeSearcher PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER = //
  new ParameterizedFieldTypeNodeSearcher();

  //method
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

  //method
  private ParameterizedFieldTypeDto createBaseParameterizedBackReferenceTypeDtoFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode,
    final FieldType fieldType) {
    return new BaseParameterizedBackReferenceTypeDto(
      fieldType,
      getDataTypeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode),
      getBackReferencedColumnIdFromParameterizedFieldTypeNode(parameterizedFieldTypeNode));
  }

  //method
  private ParameterizedFieldTypeDto createBaseParameterizedReferenceTypeDtoFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode,
    final FieldType fieldType) {
    return new BaseParameterizedReferenceTypeDto(
      fieldType,
      getDataTypeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode),
      getReferencedTableIdFromParameterizedFieldTypeNode(parameterizedFieldTypeNode));
  }

  //method
  private BaseParameterizedValueTypeDto createBaseParameterizedValueTypeDtoFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode,
    final FieldType fieldType) {
    return new BaseParameterizedValueTypeDto(
      fieldType,
      getDataTypeFromParameterizedFieldTypeNode(parameterizedFieldTypeNode));
  }

  //method
  private String getBackReferencedColumnIdFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var backReferencedColumnNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredBackReferencedColumnIdNodeFromFieldTypeNode(
        parameterizedFieldTypeNode);

    return backReferencedColumnNode.getSingleChildNodeHeader();
  }

  //method
  private DataType getDataTypeFromParameterizedFieldTypeNode(final IMutableNode<?> parameterizedFieldTypeNode) {

    final var dataTypeNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredDataTypeNodeFromParameterizedFieldTypeNode(
        parameterizedFieldTypeNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  //method
  private FieldType getPropertyTypeFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var fieldTypeNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredFieldTypeNodeFromParameterizedFieldTypeNode(
        parameterizedFieldTypeNode);

    return FieldType.fromSpecification(fieldTypeNode);
  }

  //method
  private String getReferencedTableIdFromParameterizedFieldTypeNode(
    final IMutableNode<?> parameterizedFieldTypeNode) {

    final var referencedTableIdNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredReferencedTableIdNodeFromParameterizedFieldTypeNode(
        parameterizedFieldTypeNode);

    return referencedTableIdNode.getSingleChildNodeHeader();
  }
}
