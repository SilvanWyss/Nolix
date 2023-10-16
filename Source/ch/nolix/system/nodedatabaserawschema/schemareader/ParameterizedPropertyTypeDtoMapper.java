//package declaration
package ch.nolix.system.nodedatabaserawschema.schemareader;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.documentapi.nodeapi.IMutableNode;
import ch.nolix.system.nodedatabaserawschema.structure.ParameterizedPropertyTypeNodeSearcher;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedBackReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedReferenceTypeDto;
import ch.nolix.system.objectschema.schemadto.BaseParameterizedValueTypeDto;
import ch.nolix.system.objectschema.schemadto.ParameterizedPropertyTypeDto;
import ch.nolix.systemapi.databaseapi.datatypeapi.DataType;
import ch.nolix.systemapi.databaseapi.propertytypeapi.PropertyType;

//class
public class ParameterizedPropertyTypeDtoMapper {

  //constant
  private static final ParameterizedPropertyTypeNodeSearcher PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER = //
      new ParameterizedPropertyTypeNodeSearcher();

  //method
  public ParameterizedPropertyTypeDto createParameterizedProeprtyTypeDtoFromParameterizedPropertyTypeNode(
      final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var propertyType = getPropertyTypeFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode);

    return switch (propertyType.getBaseType()) {
      case BASE_VALUE ->
        createBaseParameterizedValueTypeDtoFromParameterizedPropertyTypeNode(
            parameterizedPropertyTypeNode,
            propertyType);
      case BASE_REFERENCE ->
        createBaseParameterizedReferenceTypeDtoFromParameterizedPropertyTypeNode(
            parameterizedPropertyTypeNode,
            propertyType);
      case BASE_BACK_REFERENCE ->
        createBaseParameterizedBackReferenceTypeDtoFromParameterizedPropertyTypeNode(
            parameterizedPropertyTypeNode,
            propertyType);
      default ->
        throw InvalidArgumentException.forArgument(propertyType);
    };
  }

  //method
  private ParameterizedPropertyTypeDto createBaseParameterizedBackReferenceTypeDtoFromParameterizedPropertyTypeNode(
      final IMutableNode<?> parameterizedPropertyTypeNode,
      final PropertyType propertyType) {
    return new BaseParameterizedBackReferenceTypeDto(
        propertyType,
        getDataTypeFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode),
        getBackReferencedColumnIdFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode));
  }

  //method
  private ParameterizedPropertyTypeDto createBaseParameterizedReferenceTypeDtoFromParameterizedPropertyTypeNode(
      final IMutableNode<?> parameterizedPropertyTypeNode,
      final PropertyType propertyType) {
    return new BaseParameterizedReferenceTypeDto(
        propertyType,
        getDataTypeFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode),
        getReferencedTableIdFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode));
  }

  //method
  private BaseParameterizedValueTypeDto createBaseParameterizedValueTypeDtoFromParameterizedPropertyTypeNode(
      final IMutableNode<?> parameterizedPropertyTypeNode,
      final PropertyType propertyType) {
    return new BaseParameterizedValueTypeDto(
        propertyType,
        getDataTypeFromParameterizedPropertyTypeNode(parameterizedPropertyTypeNode));
  }

  //method
  private String getBackReferencedColumnIdFromParameterizedPropertyTypeNode(
      final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var backReferencedColumnNode = PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER
        .getStoredBackReferencedColumnIdNodeFromPropertyTypeNode(
            parameterizedPropertyTypeNode);

    return backReferencedColumnNode.getSingleChildNodeHeader();
  }

  //method
  private DataType getDataTypeFromParameterizedPropertyTypeNode(final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var dataTypeNode = PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER
        .getStoredDataTypeNodeFromParameterizedPropertyTypeNode(
            parameterizedPropertyTypeNode);

    return DataType.valueOf(dataTypeNode.getSingleChildNodeHeader());
  }

  //method
  private PropertyType getPropertyTypeFromParameterizedPropertyTypeNode(
      final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var propertyTypeNode = PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER
        .getStoredPropertyTypeNodeFromParameterizedPropertyTypeNode(
            parameterizedPropertyTypeNode);

    return PropertyType.fromSpecification(propertyTypeNode);
  }

  //method
  private String getReferencedTableIdFromParameterizedPropertyTypeNode(
      final IMutableNode<?> parameterizedPropertyTypeNode) {

    final var referencedTableIdNode = PARAMETERIZED_PROPERTY_TYPE_NODE_SEARCHER
        .getStoredReferencedTableIdNodeFromParameterizedPropertyTypeNode(
            parameterizedPropertyTypeNode);

    return referencedTableIdNode.getSingleChildNodeHeader();
  }
}
