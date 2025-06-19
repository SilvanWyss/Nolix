package ch.nolix.systemapi.middataapi.adapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.middataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiValueEntryDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2021-09-19
 */
public interface IDataWriter extends IResettableChangeSaver {

  /**
   * Deletes the references of the multi reference, that is in the multi reference
   * column with the given multiReferenceColumnName and belongs to the entity,
   * that has the given entityId and belongs to the table with the given
   * tableName, from the database.
   * 
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnName
   * @throws RuntimeException if the current {@link IDataWriter} is closed.
   */
  void clearMultiReference(String tableName, String entityId, String multiReferenceColumnName);

  /**
   * Deletes the values of the multi value, that is in the multi value column with
   * the given multiValueColumnName and belongs to the entity, that has the given
   * entityId and belongs to the table with the given tableName, from the
   * database.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnName
   * @throws RuntimeException if the current {@link IDataWriter} is closed.
   */
  void clearMultiValue(String tableName, String entityId, String multiValueColumnName);

  /**
   * Deletes the given entity from the database.
   * 
   * Will cause an error by saving if the given entity was deleted or changed on
   * the database in the meanwhile.
   * 
   * @param tableName
   * @param entity
   * @throws RuntimeException if the current {@link IDataWriter} is closed.
   */
  void deleteEntity(String tableName, EntityDeletionDto entity);

  /**
   * Deletes the given multiBackReferenceEntry from the database.
   * 
   * @param multiBackReferenceEntry
   * @throws RuntimeException if the current {@link IDataWriter} is closed.
   */
  void deleteMultiBackReferenceEntry(MultiBackReferenceEntryDeletionDto multiBackReferenceEntry);

  /**
   * Deletes the given multiReferenceEntry from the database.
   * 
   * @param multiReferenceEntry
   * @throws RuntimeException if the current {@link IDataWriter} is closed.
   */
  void deleteMultiReferenceEntry(MultiReferenceEntryDeletionDto multiReferenceEntry);

  /**
   * Deletes the given multiValueEntry from the database.
   * 
   * @param multiValueEntry
   * @throws RuntimeException if the current {@link IDataWriter} is closed.
   */
  void deleteMultiValueEntry(MultiValueEntryDto multiValueEntry);

  /**
   * Will cause an error by saving if the database does not have the given
   * schemaTimestamp.
   * 
   * This method can be used to prevent from saving changes when the
   * schemaTimestamp was changed in the meanwhile
   * 
   * @param schemaTimestamp
   */
  void expectSchemaTimestamp(ITime schemaTimestamp);

  /**
   * Will cause an error by saving if on the database the table with the given
   * tableName does not contain an entity with the given entityId.
   * 
   * This method can be used to prevent from referencing an entity that was
   * deleted in the meanwhile.
   * 
   * @param tableName
   * @param entityId
   */
  void expectTableContainsEntity(String tableName, String entityId);

  /**
   * Inserts the given entity into the table with the given tableName on the
   * database.
   * 
   * @param tableName
   * @param entity
   */
  void insertEntity(String tableName, EntityCreationDto entity);

  /**
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnId
   * @param backReferencedEntityId
   */
  void insertMultiBackReferenceEntry(
    String tableName,
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId);

  /**
   * @param multiReferenceEntry
   */
  void insertMultiReferenceEntry(MultiReferenceEntryDto multiReferenceEntry);

  /**
   * @param tableName
   * @param entityId
   * @param multiValueColumnName
   * @param entry
   */
  void insertMultiValueEntry(String tableName, String entityId, String multiValueColumnName, String entry);

  /**
   * @param tableName
   * @param entityUpdate
   */
  void updateEntity(String tableName, EntityUpdateDto entityUpdate);
}
