package ch.nolix.system.objectdata.modelmapper;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.system.objectdata.schemaview.BackReferenceModelView;
import ch.nolix.system.objectdata.schemaview.MultiBackReferenceModelView;
import ch.nolix.system.objectdata.schemaview.MultiReferenceModelView;
import ch.nolix.system.objectdata.schemaview.MultiValueModelView;
import ch.nolix.system.objectdata.schemaview.OptionalBackReferenceModelView;
import ch.nolix.system.objectdata.schemaview.OptionalReferenceModelView;
import ch.nolix.system.objectdata.schemaview.OptionalValueModelView;
import ch.nolix.system.objectdata.schemaview.ReferenceModelView;
import ch.nolix.system.objectdata.schemaview.ValueModelView;
import ch.nolix.systemapi.midschema.model.ContentModelDto;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IContentModelView;

public final class ContentModelMapper {
  public IContentModelView<ITable<IEntity>> //
  mapContentModelDtoToContentModelView( //NOSONAR: This switch statement must handle all cases.
    final ContentModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> tables) {
    final var fieldType = contentModelDto.fieldType();

    switch (fieldType) {
      case VALUE_FIELD:
        final var valueType = contentModelDto.dataType().getDataTypeClass();

        return ValueModelView.forValueType(valueType);
      case OPTIONAL_VALUE_FIELD:
        final var valueType2 = contentModelDto.dataType().getDataTypeClass();

        return OptionalValueModelView.forValueType(valueType2);
      case MULTI_VALUE_FIELD:
        final var valueType3 = contentModelDto.dataType().getDataTypeClass();

        return MultiValueModelView.forValueType(valueType3);
      case REFERENCE:
        final var referenceableTableIds = contentModelDto.referenceableTableIds();
        final var referenceableTables = tables.getStoredSelected(t -> referenceableTableIds.containsAny(t::hasId));

        return ReferenceModelView.forReferenceableTables(referenceableTables);
      case OPTIONAL_REFERENCE:
        final var referenceableTableIds2 = contentModelDto.referenceableTableIds();
        final var referenceableTables2 = tables.getStoredSelected(t -> referenceableTableIds2.containsAny(t::hasId));

        return OptionalReferenceModelView.forReferenceableTables(referenceableTables2);
      case MULTI_REFERENCE:
        final var referenceableTableIds3 = contentModelDto.referenceableTableIds();
        final var referenceableTables3 = tables.getStoredSelected(t -> referenceableTableIds3.containsAny(t::hasId));

        return MultiReferenceModelView.forReferenceableTables(referenceableTables3);
      case BACK_REFERENCE:
        final var backReferenceableColumnIds = contentModelDto.backReferenceableColumnIds();
        final var columns = tables.toMultiples(ITable::getStoredColumns);

        final var backReferenceableColumns = //
        columns.getStoredSelected(c -> backReferenceableColumnIds.contains(c.getId()));

        return BackReferenceModelView.forBackReferenceableColumnViews(backReferenceableColumns);
      case OPTIONAL_BACK_REFERENCE:
        final var backReferenceableColumnIds2 = contentModelDto.backReferenceableColumnIds();
        final var columns2 = tables.toMultiples(ITable::getStoredColumns);

        final var backReferenceableColumns2 = //
        columns2.getStoredSelected(c -> backReferenceableColumnIds2.contains(c.getId()));

        return OptionalBackReferenceModelView.forBackReferenceableColumnViews(backReferenceableColumns2);
      case MULTI_BACK_REFERENCE:
        final var backReferenceableColumnIds3 = contentModelDto.backReferenceableColumnIds();
        final var columns3 = tables.toMultiples(ITable::getStoredColumns);

        final var backReferenceableColumns3 = //
        columns3.getStoredSelected(c -> backReferenceableColumnIds3.contains(c.getId()));

        return MultiBackReferenceModelView.forBackReferenceableColumnViews(backReferenceableColumns3);
      default:
        throw InvalidArgumentException.forArgument(contentModelDto);
    }
  }
}
