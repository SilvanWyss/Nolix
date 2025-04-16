package ch.nolix.systemapi.majordataapi.adapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.majordataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.majordataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiValueEntryDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDataWriter extends IResettableChangeSaver {

  void clearMultiReference(String tableName, String entityId, String multiReferenceColumnName);

  void clearMultiValue(String tableName, String entityId, String multiValueColumnName);

  void deleteEntity(String tableName, EntityDeletionDto entity);

  void deleteMultiReferenceEntry(MultiReferenceEntryDeletionDto multiReferenceEntry);

  void deleteMultiValueEntry(MultiValueEntryDto multiValueEntry);

  void expectSchemaTimestamp(ITime schemaTimestamp);

  void expectTableContainsEntity(String tableName, String entityId);

  void insertEntity(String tableName, EntityCreationDto entity);

  void insertMultiReferenceEntry(MultiReferenceEntryDto multiReferenceEntry);

  void insertMultiValueEntry(MultiValueEntryDto multiValueEntry);

  void updateEntity(String tableName, EntityUpdateDto entityUpdate);
}
