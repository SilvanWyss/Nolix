package ch.nolix.system.objectdata.dtotocontentmodelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IContentModel;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.rawschemaapi.dto.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.dto.ValueModelDto;

public final class ContentModelMapper {

  private static final ParameterizedValueTypeMapper PARAMETERIZED_VALUE_TYPE_MAPPER = //
  new ParameterizedValueTypeMapper();

  private static final OptionalValueModelMapper PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER = //
  new OptionalValueModelMapper();

  private static final MultiValueModelMapper PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER = //
  new MultiValueModelMapper();

  private static final ReferenceModelMapper PARAMETERIZED_REFERENCE_TYPE_MAPPER = //
  new ReferenceModelMapper();

  private static final OptionalReferenceModelMapper PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER = //
  new OptionalReferenceModelMapper();

  private static final MultiReferenceModelMapper PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER = //
  new MultiReferenceModelMapper();

  private static final BackReferenceModelMapper PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER = //
  new BackReferenceModelMapper();

  private static final OptionalBackReferenceModelMapper //
  PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER = //
  new OptionalBackReferenceModelMapper();

  private static final MultiBackReferenceModelMapper PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER = //
  new MultiBackReferenceModelMapper();

  public IContentModel mapContentModelDtoToContentModel(
    final IContentModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return PARAMETERIZED_VALUE_TYPE_MAPPER.mapContentModelDtoToContentModel(valueModelDto, referencableTables);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return PARAMETERIZED_OPTIONAL_VALUE_TYPE_MAPPER.mapContentModelDtoToContentModel(optionalValueModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return PARAMETERIZED_MULTI_VALUE_TYPE_MAPPER.mapContentModelDtoToContentModel(multiValueModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return PARAMETERIZED_REFERENCE_TYPE_MAPPER.mapContentModelDtoToContentModel(referenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return PARAMETERIZED_OPTIONAL_REFERENCE_TYPE_MAPPER.mapContentModelDtoToContentModel(optionalReferenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return PARAMETERIZED_MULTI_REFERENCE_TYPE_MAPPER.mapContentModelDtoToContentModel(multiReferenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return PARAMETERIZED_BACK_REFERENCE_TYPE_MAPPER.mapContentModelDtoToContentModel(backReferenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return PARAMETERIZED_OPTIONAL_BACK_REFERENCE_TYPE_MAPPER
        .mapContentModelDtoToContentModel(optionalBackReferenceModelDto, referencableTables);
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return PARAMETERIZED_MULTI_BACK_REFERENCE_TYPE_MAPPER
        .mapContentModelDtoToContentModel(multiBackReferenceModelDto, referencableTables);
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
