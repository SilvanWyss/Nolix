package ch.nolix.system.objectschema.midschemamodelmapper;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
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
  public ContentModelDto mapContentModelToContentModelDto(final IContentModel contentModel) {
    final var fieldType = contentModel.getFieldType();

    if (contentModel instanceof final IValueModel<?> valueModel) {
      return //
      new ContentModelDto(fieldType, valueModel.getDataType(), ImmutableList.createEmpty(),
        ImmutableList.createEmpty());
    }

    if (contentModel instanceof final IOptionalValueModel<?> optionalValueModel) {
      return new ContentModelDto(fieldType, optionalValueModel.getDataType(), ImmutableList.createEmpty(),
        ImmutableList.createEmpty());
    }

    if (contentModel instanceof final IMultiValueModel<?> multiValueModel) {
      return new ContentModelDto(fieldType, multiValueModel.getDataType(), ImmutableList.createEmpty(),
        ImmutableList.createEmpty());
    }

    if (contentModel instanceof final IReferenceModel referenceModel) {
      final var dataType = referenceModel.getDataType();
      final var referenceableTableIds = referenceModel.getReferenceableTables().to(ITable::getId);

      return new ContentModelDto(fieldType, dataType, referenceableTableIds, ImmutableList.createEmpty());
    }

    if (contentModel instanceof final IOptionalReferenceModel optionalReferenceModel) {
      final var dataType = optionalReferenceModel.getDataType();
      final var referenceableTableIds = optionalReferenceModel.getReferenceableTables().to(ITable::getId);

      return new ContentModelDto(fieldType, dataType, referenceableTableIds, ImmutableList.createEmpty());
    }

    if (contentModel instanceof final IMultiReferenceModel multiReferenceModel) {
      final var dataType = multiReferenceModel.getDataType();
      final var referenceableTableIds = multiReferenceModel.getReferenceableTables().to(ITable::getId);

      return new ContentModelDto(fieldType, dataType, referenceableTableIds, ImmutableList.createEmpty());
    }

    if (contentModel instanceof final IBackReferenceModel backReferenceModel) {
      final var dataType = backReferenceModel.getDataType();
      final var backReferencedColumnId = backReferenceModel.getBackReferencedColumn().getId();

      //TODO: Add getBackReferencedColumns method to IBaseBackReferenceModel
      return new ContentModelDto(fieldType, dataType, ImmutableList.createEmpty(),
        ImmutableList.withElement(backReferencedColumnId));
    }

    if (contentModel instanceof final IOptionalBackReferenceModel optionalBackReferenceModel) {
      final var dataType = optionalBackReferenceModel.getDataType();
      final var backReferencedColumnId = optionalBackReferenceModel.getBackReferencedColumn().getId();

      //TODO: Add getBackReferencedColumns method to IBaseBackReferenceModel
      return new ContentModelDto(fieldType, dataType, ImmutableList.createEmpty(),
        ImmutableList.withElement(backReferencedColumnId));
    }

    if (contentModel instanceof final IMultiBackReferenceModel multiBackReferenceModel) {
      final var dataType = multiBackReferenceModel.getDataType();
      final var backReferencedColumnId = multiBackReferenceModel.getBackReferencedColumn().getId();

      //TODO: Add getBackReferencedColumns method to IBaseBackReferenceModel
      return new ContentModelDto(fieldType, dataType, ImmutableList.createEmpty(),
        ImmutableList.withElement(backReferencedColumnId));
    }

    throw InvalidArgumentException.forArgument(contentModel);
  }
}
