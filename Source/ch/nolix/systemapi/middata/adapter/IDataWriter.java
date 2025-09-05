package ch.nolix.systemapi.middata.adapter;

import ch.nolix.coreapi.resourcecontrol.savecontrol.IResettableChangeSaver;
import ch.nolix.systemapi.middata.model.EntityCreationDto;
import ch.nolix.systemapi.middata.model.EntityDeletionDto;
import ch.nolix.systemapi.middata.model.EntityUpdateDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDeletionDto;
import ch.nolix.systemapi.middata.model.MultiBackReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDeletionDto;
import ch.nolix.systemapi.middata.model.MultiReferenceEntryDto;
import ch.nolix.systemapi.middata.model.MultiValueEntryDto;
import ch.nolix.systemapi.time.moment.ITime;

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
   */
  void deleteEntity(String tableName, EntityDeletionDto entity);

  /**
   * Deletes the given multiBackReferenceEntry from the database.
   * 
   * @param multiBackReferenceEntry
   */
  void deleteMultiBackReferenceEntry(MultiBackReferenceEntryDeletionDto multiBackReferenceEntry);

  /**
   * Deletes the given multiReferenceEntry from the database.
   * 
   * @param multiReferenceEntry
   */
  void deleteMultiReferenceEntry(MultiReferenceEntryDeletionDto multiReferenceEntry);

  /**
   * Deletes the given multiValueEntry from the database.
   * 
   * @param multiValueEntry
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
   * Inserts the given multiBackReferenceEntry into the database.
   * 
   * @param multiBackReferenceEntry
   */
  void insertMultiBackReferenceEntry(MultiBackReferenceEntryDto multiBackReferenceEntry);

  /**
   * Inserts the given multiReferenceEntry into the database.
   * 
   * @param multiReferenceEntry
   */
  void insertMultiReferenceEntry(MultiReferenceEntryDto multiReferenceEntry);

  /**
   * Inserts the given multiValueEntry into the database.
   * 
   * @param multiValueEntry
   */
  void insertMultiValueEntry(MultiValueEntryDto multiValueEntry);

  /**
   * Updates the entity, that belongs to the table with the given tableName, in
   * the database according to the given entityUpdate.
   * 
   * @param tableName
   * @param entityUpdate
   */
  void updateEntity(String tableName, EntityUpdateDto entityUpdate);
}
