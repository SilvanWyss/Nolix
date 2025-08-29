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
    final IContainer<? extends ITable<IEntity>> referencableTables) {

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

      final var referencableTableIds = referenceModelDto.referenceableTableIds();

      //TODO: Update ReferenceModelView
      final var table = referencableTables.getStoredFirst(t -> t.hasId(referencableTableIds.getStoredFirst()));

      return ReferenceModelView.forReferencedTable(table);
    }

    if (contentModelDto instanceof final OptionalReferenceModelDto optionalReferenceModelDto) {

      final var referencableTableIds = optionalReferenceModelDto.referenceableTableIds();

      //TODO: Update OptionalReferenceModelView
      final var table = referencableTables.getStoredFirst(t -> t.hasId(referencableTableIds.getStoredFirst()));

      return OptionalReferenceModelView.forReferencedTable(table);
    }

    if (contentModelDto instanceof final MultiReferenceModelDto multiReferenceModelDto) {

      final var referencableTableIds = multiReferenceModelDto.referenceableTableIds();

      //TODO: Update MultiReferenceModelView
      final var table = referencableTables.getStoredFirst(t -> t.hasId(referencableTableIds.getStoredFirst()));

      return MultiReferenceModelView.forReferencedTable(table);
    }

    if (contentModelDto instanceof final BackReferenceModelDto backReferenceModelDto) {

      final var backReferencedColumnId = backReferenceModelDto.backReferencedColumnId();
      final var referencableColumns = referencableTables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return BackReferenceModelView.forBackReferencedColumn(backReferencedColumn);
    }

    if (contentModelDto instanceof final OptionalBackReferenceModelDto optionalBackReferenceModelDto) {

      final var backReferencedColumnId = optionalBackReferenceModelDto.backReferencedColumnId();
      final var referencableColumns = referencableTables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return OptionalBackReferenceModelView.forBackReferencedColumn(backReferencedColumn);
    }

    if (contentModelDto instanceof final MultiBackReferenceModelDto multiBackReferenceModelDto) {

      final var backReferencedColumnId = multiBackReferenceModelDto.backReferencedColumnId();
      final var referencableColumns = referencableTables.toMultiples(ITable::getStoredColumns);
      final var backReferencedColumn = referencableColumns.getStoredFirst(c -> c.hasId(backReferencedColumnId));

      return MultiBackReferenceModelView.forBackReferencedColumn(backReferencedColumn);
    }

    throw InvalidArgumentException.forArgument(contentModelDto);
  }
}
