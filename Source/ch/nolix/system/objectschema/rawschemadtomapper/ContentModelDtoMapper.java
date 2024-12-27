package ch.nolix.system.objectschema.rawschemadtomapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectschemaapi.rawschemadtomapperapi.IContentModelDtoMapper;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IContentModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IMultiBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IMultiReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IMultiValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IOptionalBackReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IOptionalReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IOptionalValueModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IReferenceModel;
import ch.nolix.systemapi.objectschemaapi.schemaapi.ITable;
import ch.nolix.systemapi.objectschemaapi.schemaapi.IValueModel;
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
      final var referencedTableIds = referenceModel.getReferencedTables().to(ITable::getId);

      return new ReferenceModelDto(dataType, referencedTableIds);
    }

    if (contentModel instanceof final IOptionalReferenceModel optionalReferenceModel) {

      final var dataType = optionalReferenceModel.getDataType();
      final var referencedTableIds = optionalReferenceModel.getReferencedTables().to(ITable::getId);

      return new OptionalReferenceModelDto(dataType, referencedTableIds);
    }

    if (contentModel instanceof final IMultiReferenceModel multiReferenceModel) {

      final var dataType = multiReferenceModel.getDataType();
      final var referencedTableIds = multiReferenceModel.getReferencedTables().to(ITable::getId);

      return new MultiReferenceModelDto(dataType, referencedTableIds);
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
