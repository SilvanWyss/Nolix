//package declaration
package ch.nolix.system.objectdata.parameterizedpropertytypemapper;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IParameterizedPropertyType;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedBackReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedReferenceTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IBaseParameterizedValueTypeDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.IParameterizedPropertyTypeDto;

//class
public final class ParameterizedPropertyTypeMapper {

  //constant
  private static final ParameterizedValueTypeMapper PARAMETERIZED_VALUE_TYPE_MAPPER = //
  new ParameterizedValueTypeMapper();

  //constant
  private static final ParameterizedOptionalValueTypeMapper PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER = //
  new ParameterizedOptionalValueTypeMapper();

  //constant
  private static final ParameterizedMultiValueTypeMapper PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER = //
  new ParameterizedMultiValueTypeMapper();

  //constant
  private static final ParameterizedReferenceTypeMapper PARAMETERIZED_REFERENCE_TYPE_MAPPER = //
  new ParameterizedReferenceTypeMapper();

  //constant
  private static final ParameterizedOptionalReferenceTypeMapper PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER = //
  new ParameterizedOptionalReferenceTypeMapper();

  //constant
  private static final ParameterizedMultiReferenceTypeMapper PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER = //
  new ParameterizedMultiReferenceTypeMapper();

  //constant
  private static final ParameterizedBackReferenceTypeMapper PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedBackReferenceTypeMapper();

  //constant
  private static final ParameterizedOptionalBackReferenceTypeMapper //
  PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedOptionalBackReferenceTypeMapper();

  //constant
  private static final ParameterizedMultiBackReferenceTypeMapper PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedMultiBackReferenceTypeMapper();

  //method
  public IParameterizedPropertyType createParameterizedPropertyTypeFromDto(
    final IParameterizedPropertyTypeDto parameterizedPropertyTypeDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    final var propertyType = parameterizedPropertyTypeDto.getPropertyType();

    return //
    switch (propertyType) {
      case VALUE ->
        PARAMETERIZED_VALUE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedValueTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      case OPTIONAL_VALUE ->
        PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedValueTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      case MULTI_VALUE ->
        PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedValueTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      case REFERENCE ->
        PARAMETERIZED_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedReferenceTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      case OPTIONAL_REFERENCE ->
        PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedReferenceTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      case MULTI_REFERENCE ->
        PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedReferenceTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      case BACK_REFERENCE ->
        PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      case OPTIONAL_BACK_REFERENCE ->
        PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      case MULTI_BACK_REFERENCE ->
        PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER.createParameterizedPropertyTypeFromDto(
          (IBaseParameterizedBackReferenceTypeDto) parameterizedPropertyTypeDto,
          referencableTables);
      default ->
        throw InvalidArgumentException.forArgument(parameterizedPropertyTypeDto);
    };
  }
}
