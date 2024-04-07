//package declaration
package ch.nolix.system.noderawschema.schemareader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.noderawschema.nodesearcher.ParameterizedFieldTypeNodeSearcher;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ParameterizedFieldTypeDto;
import ch.nolix.systemapi.fieldapi.datatypeapi.DataType;
import ch.nolix.systemapi.fieldapi.mainapi.FieldType;

//class
public class ParameterizedFieldTypeDtoMapper {

  //constant
  private static final ParameterizedFieldTypeNodeSearcher PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER = //
  new ParameterizedFieldTypeNodeSearcher();

  //method
  public ParameterizedFieldTypeDto createParameterizedProeprtyTypeDtoFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var fieldType = getPropertyTypeFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode);

    return switch (fieldType.getBaseType()) {
      case BASE_VALUE ->
        createBaseParameterizedValueTypeDtoFromParameterizedPropertyTypeNode(
          parameterizedPropertyTypeNode,
          fieldType);
      case BASE_REFERENCE ->
        createBaseParameterizedReferenceTypeDtoFromParameterizedPropertyTypeNode(
          parameterizedPropertyTypeNode,
          fieldType);
      case BASE_BACK_REFERENCE ->
        createBaseParameterizedBackReferenceTypeDtoFromParameterizedPropertyTypeNode(
          parameterizedPropertyTypeNode,
          fieldType);
      default ->
        throw InvalidArgumentException.forArgument(fieldType);
    };
  }

  //method
  private ParameterizedFieldTypeDto createBaseParameterizedBackReferenceTypeDtoFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode,
    final FieldType fieldType) {
    return new BaseParameterizedBackReferenceTypeDto(
      fieldType,
      getDataTypeFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode),
      getBackReferencedColumnIdFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode));
  }

  //method
  private ParameterizedFieldTypeDto createBaseParameterizedReferenceTypeDtoFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode,
    final FieldType fieldType) {
    return new BaseParameterizedReferenceTypeDto(
      fieldType,
      getDataTypeFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode),
      getReferencedTableIdFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode));
  }

  //method
  private BaseParameterizedValueTypeDto createBaseParameterizedValueTypeDtoFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode,
    final FieldType fieldType) {
    return new BaseParameterizedValueTypeDto(
      fieldType,
      getDataTypeFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode));
  }

  //method
  private String getBackReferencedColumnIdFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var backReferencedColumnNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredBackReferencedColumnIdNodeFromFieldTypeNode(
        parameterizedPropertyTypeNode);

    return backReferencedColumnNode.getSingleChildNodeHeader();
  }

  //method
  private DataType getDataTypeFromParameterizedPropertyTypeNode(final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var dataTypeNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredDataTypeNodeFromParameterizedFieldTypeNode(
        parameterizedPropertyTypeNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  //method
  private FieldType getPropertyTypeFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var fieldTypeNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredFieldTypeNodeFromParameterizedFieldTypeNode(
        parameterizedPropertyTypeNode);

    return FieldType.fromSpecification(fieldTypeNode);
  }

  //method
  private String getReferencedTableIdFromParameterizedPropertyTypeNode(
    final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var referencedTableIdNode = PARAMETERIZED_FIELD_TYPE_NODE_SEARCHER
      .getStoredReferencedTableIdNodeFromParameterizedFieldTypeNode(
        parameterizedPropertyTypeNode);

    return referencedTableIdNode.getSingleChildNodeHeader();
  }
}
