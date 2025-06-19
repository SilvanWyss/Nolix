package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.middataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReferenceEntry;

public final class MultiReferenceSaver {

  public void saveMultiReference(
    final IMultiReference<?> multiReference,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {
    for (final var le : multiReference.getStoredNewAndDeletedEntries()) {
      saveMultiReferenceEntry(le, dataAndSchemaAdapter);
    }
  }

  private void saveMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var state = multiReferenceEntry.getState();

    switch (state) {
      case NEW:
        insertMultiReferenceEntry(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      case LOADED:
        break;
      case DELETED:
        deleteMultiReferenceEntry(multiReferenceEntry, dataAndSchemaAdapter);
        break;
      default:
        throw InvalidArgumentException.forArgumentAndArgumentName(state, "state of multi reference");
    }
  }

  private void insertMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var multiReference = multiReferenceEntry.getStoredParentMultiReference();
    final var entity = multiReference.getStoredParentEntity();
    final var tableName = entity.getParentTableName();
    final var entityId = entity.getId();
    final var multiReferenceColumnId = multiReference.getStoredParentColumn().getId();
    final var referencedEntityId = multiReferenceEntry.getReferencedEntityId();
    final var referencedEntity = multiReference.getStoredParentEntity();
    final var referencedEntityTableId = referencedEntity.getStoredParentTable().getId();

    final var multiReferenceEntryDto = //
    new MultiReferenceEntryDto(
      tableName,
      entityId,
      multiReferenceColumnId,
      referencedEntityId,
      referencedEntityTableId);

    dataAndSchemaAdapter.insertMultiReferenceEntry(multiReferenceEntryDto);
  }

  private void deleteMultiReferenceEntry(
    final IMultiReferenceEntry<?> multiReferenceEntry,
    final IDataAdapterAndSchemaReader dataAndSchemaAdapter) {

    final var multiReference = multiReferenceEntry.getStoredParentMultiReference();
    final var entity = multiReference.getStoredParentEntity();
    final var entityId = entity.getId();
    final var tableName = entity.getParentTableName();
    final var multiReferenceColumn = multiReference.getStoredParentColumn();
    final var multiReferenceColumnName = multiReferenceColumn.getName();
    final var referencedEntityId = multiReferenceEntry.getReferencedEntityId();

    //TODO: Create MultiReferenceEntryDeletionDtoMapper
    final var multiReferenceEntryDeletionDto = //
    new MultiReferenceEntryDeletionDto(tableName, entityId, multiReferenceColumnName, referencedEntityId);

    dataAndSchemaAdapter.deleteMultiReferenceEntry(multiReferenceEntryDeletionDto);
  }
}
