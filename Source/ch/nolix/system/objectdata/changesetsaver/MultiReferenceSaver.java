package ch.nolix.system.objectdata.changesetsaver;

import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReference;
import ch.nolix.systemapi.objectdataapi.modelapi.IMultiReferenceEntry;
import ch.nolix.systemapi.rawdataapi.adapterapi.IDataAdapterAndSchemaReader;
import ch.nolix.systemapi.rawdataapi.modelapi.MultiReferenceEntryDto;

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
        throw InvalidArgumentException.forArgumentNameAndArgument("state of multi reference", state);
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

    dataAndSchemaAdapter.deleteMultiReferenceEntry(
      entity.getParentTableName(),
      entity.getId(),
      multiReference.getName(),
      multiReferenceEntry.getReferencedEntityId());
  }
}
