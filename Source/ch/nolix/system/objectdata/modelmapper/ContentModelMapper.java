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
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.schemaview.IContentModelView;

public final class ContentModelMapper {
  public IContentModelView<ITable<IEntity>> //
  mapContentModelDtoToContentModel( //NOSONAR: This switch statement must handle all cases.
    final IContentModelDto contentModelDto,
    final IContainer<? extends ITable<IEntity>> tables) {
    if (contentModelDto instanceof ValueModelDto) {
      final var valueType = contentModelDto.getDataType().getDataTypeClass();

      return ValueModelView.forValueType(valueType);
    }

    if (contentModelDto instanceof OptionalValueModelDto) {
      final var valueType = contentModelDto.getDataType().getDataTypeClass();

      return OptionalValueModelView.forValueType(valueType);
    }

    if (contentModelDto instanceof MultiValueModelDto) {
      final var valueType = contentModelDto.getDataType().getDataTypeClass();

      return MultiValueModelView.forValueType(valueType);
    }

    if (contentModelDto instanceof final ReferenceModelDto referenceModelDto) {
      final var referenceableTableIds = referenceModelDto.referenceableTableIds();
      final var referenceableTables = tables.getStoredSelected(t -> referenceableTableIds.containsAny(t::hasId));

      return ReferenceModelView.forReferenceableTables(referenceableTables);
    }

    if (contentModelDto instanceof final OptionalReferenceModelDto optionalReferenceModelDto) {
      final var referenceableTableIds = optionalReferenceModelDto.referenceableTableIds();
      final var referenceableTables = tables.getStoredSelected(t -> referenceableTableIds.containsAny(t::hasId));

      return OptionalReferenceModelView.forReferenceableTables(referenceableTables);
    }

    if (contentModelDto instanceof final MultiReferenceModelDto multiReferenceModelDto) {
      final var referenceableTableIds = multiReferenceModelDto.referenceableTableIds();
      final var referenceableTables = tables.getStoredSelected(t -> referenceableTableIds.containsAny(t::hasId));

      return MultiReferenceModelView.forReferenceableTables(referenceableTables);
    }

    if (contentModelDto instanceof final BackReferenceModelDto backReferenceModelDto) {
      final var backReferencedColumnId = backReferenceModelDto.backReferencedColumnId();
      final var referencableColumns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return BackReferenceModelView.forBackReferencedColumn(backReferencedColumn);
    }

    if (contentModelDto instanceof final OptionalBackReferenceModelDto optionalBackReferenceModelDto) {
      final var backReferencedColumnId = optionalBackReferenceModelDto.backReferencedColumnId();
      final var referencableColumns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return OptionalBackReferenceModelView.forBackReferencedColumn(backReferencedColumn);
    }

    if (contentModelDto instanceof final MultiBackReferenceModelDto multiBackReferenceModelDto) {
      final var backReferencedColumnId = multiBackReferenceModelDto.backReferencedColumnId();
      final var referencableColumns = tables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return MultiBackReferenceModelView.forBackReferencedColumn(backReferencedColumn);
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
