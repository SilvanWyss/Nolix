package ch.nolix.system.objectschema.model;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.objectschema.model.IContentModel;
import ch.nolix.systemapi.objectschema.model.ITable;

public final class ContentModelMapper {
  private ContentModelMapper() {
  }

  public static IContentModel mapMidSchemaContentModelDtoToContentModel(
    final ContentModelDto midSchemaContentModelDto,
    final IContainer<? extends ITable> tables) {
    final var fieldType = midSchemaContentModelDto.fieldType();
    final var dataType = midSchemaContentModelDto.dataType();

    switch (fieldType) {
      case VALUE_FIELD:
        return ValueModel.forDataType(dataType);
      case OPTIONAL_VALUE_FIELD:
        return OptionalValueModel.forDataType(dataType);
      case MULTI_VALUE_FIELD:
        return MultiValueModel.forDataType(dataType);
      case REFERENCE:
        final var referenceableTableIds = midSchemaContentModelDto.referenceableTableIds();
        final var referenceableTables = tables.getStoredSelected(t -> referenceableTableIds.containsAny(t::hasId));

        return ReferenceModel.forReferenceableTables(referenceableTables);
      case OPTIONAL_REFERENCE:
        final var referenceableTableIds2 = midSchemaContentModelDto.referenceableTableIds();
        final var referenceableTables2 = tables.getStoredSelected(t -> referenceableTableIds2.containsAny(t::hasId));

        return OptionalReferenceModel.forReferenceableTables(referenceableTables2);
      case MULTI_REFERENCE:
        final var referenceableTableIds3 = midSchemaContentModelDto.referenceableTableIds();
        final var referenceableTables3 = tables.getStoredSelected(t -> referenceableTableIds3.containsAny(t::hasId));

        return MultiReferenceModel.forReferenceableTables(referenceableTables3);
      case BACK_REFERENCE:
        final var backReferenceableColumnIds = midSchemaContentModelDto.backReferenceableColumnIds();
        final var columns = tables.toMultiples(ITable::getStoredColumns);

        final var backReferenceableColumns = //
        columns.getStoredSelected(c -> backReferenceableColumnIds.containsAny(c::hasId));

        return BackReferenceModel.forBackReferenceableColumns(backReferenceableColumns);
      case OPTIONAL_BACK_REFERENCE:
        final var backReferenceableColumnIds2 = midSchemaContentModelDto.backReferenceableColumnIds();
        final var columns2 = tables.toMultiples(ITable::getStoredColumns);

        final var backReferenceableColumns2 = //
        columns2.getStoredSelected(c -> backReferenceableColumnIds2.containsAny(c::hasId));

        return OptionalBackReferenceModel.forBackReferenceableColumns(backReferenceableColumns2);
      case MULTI_BACK_REFERENCE:
        final var backReferenceableColumnIds3 = midSchemaContentModelDto.backReferenceableColumnIds();
        final var columns3 = tables.toMultiples(ITable::getStoredColumns);

        final var backReferenceableColumns3 = //
        columns3.getStoredSelected(c -> backReferenceableColumnIds3.containsAny(c::hasId));

        return MultiBackReferenceModel.forBackReferenceableColumns(backReferenceableColumns3);
      default:
        throw InvalidArgumentException.forArgument(fieldType);
    }
  }
}
