package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.objectdataapi.schemaviewapi.IContentModelView;
import ch.nolix.systemapi.rawschemaapi.modelapi.BackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.IContentModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.MultiValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.OptionalValueModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ReferenceModelDto;
import ch.nolix.systemapi.rawschemaapi.modelapi.ValueModelDto;

public final class ContentModelMapper {

  private static final ValueModelMapper VALUE_MODEL_MAPPER = new ValueModelMapper();

  private static final OptionalValueModelMapper OPTIONAL_VALUE_MODEL_MAPPER = new OptionalValueModelMapper();

  private static final MultiValueModelMapper MULTI_VALUE_MODEL_MAPPER = new MultiValueModelMapper();

  private static final ReferenceModelMapper REFERENCE_MODEL_MAPPER = new ReferenceModelMapper();

  private static final OptionalReferenceModelMapper OPTIONAL_REFERENCE_MODEL_MAPPER = //
  new OptionalReferenceModelMapper();

  private static final MultiReferenceModelMapper MULTI_REFERENCE_MODEL_MAPPER = new MultiReferenceModelMapper();

  private static final BackReferenceModelMapper BACK_REFERENCE_MODEL_MAPPER = new BackReferenceModelMapper();

  private static final OptionalBackReferenceModelMapper OPTIONAL_BACK_REFERENCE_MODEL_MAPPER = //
  new OptionalBackReferenceModelMapper();

  private static final MultiBackReferenceModelMapper MULTI_BACK_REFERENCE_MODEL_MAPPER = //
  new MultiBackReferenceModelMapper();

  public IContentModelView<ITable<IEntity>> mapContentModelDtoToContentModel(
    final IContentModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> referencableTables) {

    if (contentModelDto instanceof ValueModelDto valueModelDto) {
      return VALUE_MODEL_MAPPER.mapContentModelDtoToContentModelView(valueModelDto, referencableTables);
    }

    if (contentModelDto instanceof OptionalValueModelDto optionalValueModelDto) {
      return //
      OPTIONAL_VALUE_MODEL_MAPPER.mapContentModelDtoToContentModelView(optionalValueModelDto, referencableTables);
    }

    if (contentModelDto instanceof MultiValueModelDto multiValueModelDto) {
      return MULTI_VALUE_MODEL_MAPPER.mapContentModelDtoToContentModelView(multiValueModelDto, referencableTables);
    }

    if (contentModelDto instanceof ReferenceModelDto referenceModelDto) {
      return REFERENCE_MODEL_MAPPER.mapContentModelDtoToContentModelView(referenceModelDto, referencableTables);
    }

    if (contentModelDto instanceof OptionalReferenceModelDto optionalReferenceModelDto) {
      return //
      OPTIONAL_REFERENCE_MODEL_MAPPER.mapContentModelDtoToContentModelView(
        optionalReferenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof MultiReferenceModelDto multiReferenceModelDto) {
      return //
      MULTI_REFERENCE_MODEL_MAPPER.mapContentModelDtoToContentModelView(multiReferenceModelDto, referencableTables);
    }

    if (contentModelDto instanceof BackReferenceModelDto backReferenceModelDto) {
      return //
      BACK_REFERENCE_MODEL_MAPPER.mapContentModelDtoToContentModelView(backReferenceModelDto, referencableTables);
    }

    if (contentModelDto instanceof OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      return //
      OPTIONAL_BACK_REFERENCE_MODEL_MAPPER.mapContentModelDtoToContentModelView(
        optionalBackReferenceModelDto,
        referencableTables);
    }

    if (contentModelDto instanceof MultiBackReferenceModelDto multiBackReferenceModelDto) {
      return //
      MULTI_BACK_REFERENCE_MODEL_MAPPER.mapContentModelDtoToContentModelView(
        multiBackReferenceModelDto,
        referencableTables);
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
