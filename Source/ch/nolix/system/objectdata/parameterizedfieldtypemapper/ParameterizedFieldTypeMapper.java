package ch.nolix.system.objectdata.parameterizedfieldtypemapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawschemaapi.schemadto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.schemadto.ValueModelDto;

public final class ParameterizedFieldTypeMapper {

  private static final ParameterizedValueTypeMapper PARAMETERIZED_VALUE_TYPE_MAPPER = //
  new ParameterizedValueTypeMapper();

  private static final ParameterizedOptionalValueTypeMapper PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER = //
  new ParameterizedOptionalValueTypeMapper();

  private static final ParameterizedMultiValueTypeMapper PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER = //
  new ParameterizedMultiValueTypeMapper();

  private static final ParameterizedReferenceTypeMapper PARAMETERIZED_REFERENCE_TYPE_MAPPER = //
  new ParameterizedReferenceTypeMapper();

  private static final ParameterizedOptionalReferenceTypeMapper PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER = //
  new ParameterizedOptionalReferenceTypeMapper();

  private static final ParameterizedMultiReferenceTypeMapper PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER = //
  new ParameterizedMultiReferenceTypeMapper();

  private static final ParameterizedBackReferenceTypeMapper PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedBackReferenceTypeMapper();

  private static final ParameterizedOptionalBackReferenceTypeMapper //
  PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedOptionalBackReferenceTypeMapper();

  private static final ParameterizedMultiBackReferenceTypeMapper PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER = //
  new ParameterizedMultiBackReferenceTypeMapper();

  public IContentModel createParameterizedFieldTypeFromDto(
    final IContentModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return PARAMETERIZED_VALUE_TYPE_MAPPER.createParameterizedFieldTypeFromDto(valueModelDto, referencableTables);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER.createParameterizedFieldTypeFromDto(optionalValueModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER.createParameterizedFieldTypeFromDto(multiValueModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return PARAMETERIZED_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromDto(referenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromDto(optionalReferenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromDto(multiReferenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER.createParameterizedFieldTypeFromDto(backReferenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER
        .createParameterizedFieldTypeFromDto(optionalBackReferenceModelDto, referencableTables);
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER
        .createParameterizedFieldTypeFromDto(multiBackReferenceModelDto, referencableTables);
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
