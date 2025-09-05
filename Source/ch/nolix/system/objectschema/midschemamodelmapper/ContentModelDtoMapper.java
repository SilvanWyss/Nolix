package ch.nolix.system.objectschema.midschemamodelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.midschema.model.BackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.IContentModelDto;
import ch.nolix.systemapi.midschema.model.MultiBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiReferenceModelDto;
import ch.nolix.systemapi.midschema.model.MultiValueModelDto;
import ch.nolix.systemapi.midschema.model.OptionalBackReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalReferenceModelDto;
import ch.nolix.systemapi.midschema.model.OptionalValueModelDto;
import ch.nolix.systemapi.midschema.model.ReferenceModelDto;
import ch.nolix.systemapi.midschema.model.ValueModelDto;
import ch.nolix.systemapi.objectschema.midschemamodelmapper.IContentModelDtoMapper;
import ch.nolix.systemapi.objectschema.model.IBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.IMultiBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IMultiReferenceModel;
import ch.nolix.systemapi.objectschema.model.IMultiValueModel;
import ch.nolix.systemapi.objectschema.model.IOptionalBackReferenceModel;
import ch.nolix.systemapi.objectschema.model.IOptionalReferenceModel;
import ch.nolix.systemapi.objectschema.model.IOptionalValueModel;
import ch.nolix.systemapi.objectschema.model.IReferenceModel;
import ch.nolix.systemapi.objectschema.model.ITable;
import ch.nolix.systemapi.objectschema.model.IValueModel;

/**
 * @author Silvan Wyss
 * @version 2024-12-27
 */
public final class ContentModelDtoMapper implements IContentModelDtoMapper {
  /**
   * {@inheritDoc}
   */
  @Override
  public IContentModelDto mapContentModelToContentModelDto(final IContentModel contentModel) {
    if (contentModel instanceof final IValueModel<?> valueModel) {
      return new ValueModelDto(valueModel.getDataType());
    }

    if (contentModel instanceof final IOptionalValueModel<?> optionalValueModel) {
      return new OptionalValueModelDto(optionalValueModel.getDataType());
    }

    if (contentModel instanceof final IMultiValueModel<?> multiValueModel) {
      return new MultiValueModelDto(multiValueModel.getDataType());
    }

    if (contentModel instanceof final IReferenceModel referenceModel) {
      final var dataType = referenceModel.getDataType();
      final var referenceableTableIds = referenceModel.getReferenceableTables().to(ITable::getId);

      return new ReferenceModelDto(dataType, referenceableTableIds);
    }

    if (contentModel instanceof final IOptionalReferenceModel optionalReferenceModel) {
      final var dataType = optionalReferenceModel.getDataType();
      final var referenceableTableIds = optionalReferenceModel.getReferenceableTables().to(ITable::getId);

      return new OptionalReferenceModelDto(dataType, referenceableTableIds);
    }

    if (contentModel instanceof final IMultiReferenceModel multiReferenceModel) {
      final var dataType = multiReferenceModel.getDataType();
      final var referenceableTableIds = multiReferenceModel.getReferenceableTables().to(ITable::getId);

      return new MultiReferenceModelDto(dataType, referenceableTableIds);
    }

    if (contentModel instanceof final IBackReferenceModel backReferenceModel) {
      final var dataType = backReferenceModel.getDataType();
      final var backReferencedColumnId = backReferenceModel.getBackReferencedColumn().getId();

      return new BackReferenceModelDto(dataType, backReferencedColumnId);
    }

    if (contentModel instanceof final IOptionalBackReferenceModel optionalBackReferenceModel) {
      final var dataType = optionalBackReferenceModel.getDataType();
      final var backReferencedColumnId = optionalBackReferenceModel.getBackReferencedColumn().getId();

      return new OptionalBackReferenceModelDto(dataType, backReferencedColumnId);
    }

    if (contentModel instanceof final IMultiBackReferenceModel multiBackReferenceModel) {
      final var dataType = multiBackReferenceModel.getDataType();
      final var backReferencedColumnId = multiBackReferenceModel.getBackReferencedColumn().getId();

      return new MultiBackReferenceModelDto(dataType, backReferencedColumnId);
    }

    throw InvalidArgumentException.forArgument(contentModel);
  }
}
