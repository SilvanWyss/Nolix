package ch.nolix.systemapi.rawdataapi.adapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityCreationDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityDeletionDto;
import ch.nolix.systemapi.rawdataapi.modelapi.EntityUpdateDto;
import ch.nolix.systemapi.rawdataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2021-09-19
 */
public interface IDataWriter extends IResettableChangeSaver {

  /**
   * Causes an error if the concerned entity was deleted or changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entity
   */
  void deleteEntity(String tableName, EntityDeletionDto entity);

  /**
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnId
   * @param backReferencedEntityId
   */
  void deleteMultiBackReferenceEntry(
    String tableName,
    String entityId,
    String multiBackReferenceColumnId,
    String backReferencedEntityId);

  /**
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnName
   */
  void deleteMultiReferenceEntries(String tableName, String entityId, String multiReferenceColumnName);

  /**
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiRefereceColumnName
   * @param referencedEntityId
   */
  void deleteMultiReferenceEntry(
    String tableName,
    String entityId,
    String multiRefereceColumnName,
    String referencedEntityId);

  /**
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnName
   */
  void deleteMultiValueEntries(String tableName, String entityId, String multiValueColumnName);

  /**
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnName
   * @param entry
   */
  void deleteMultiValueEntry(
    String tableName,
    String entityId,
    String multiValueColumnName,
    String entry);

  /**
   * Will cause an error if the concerned table does not contain an entity with
   * the given entityId. This method can be used to prevent from referencing an
   * entity that was deleted in the meanwhile.
   * 
   * @param tableName
   * @param entityId
   */
  void expectTableContainsEntity(String tableName, String entityId);

  /**
   * Will cause an error if the database does not have the given schema timestamp.
   * 
   * @param schemaTimestamp
   */
  void expectGivenSchemaTimestamp(ITime schemaTimestamp);

  /**
   * Inserts the given newEntity into the table with the given tableName.
   * 
   * @param tableName
   * @param newEntity
   */
  void insertEntity(String tableName, EntityCreationDto newEntity);

  /**
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
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
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param multiReferenceEntry
   */
  void insertMultiReferenceEntry(MultiReferenceEntryDto multiReferenceEntry);

  /**
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnName
   * @param entry
   */
  void insertMultiValueEntry(String tableName, String entityId, String multiValueColumnName, String entry);

  /**
   * There is not validated that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityUpdate
   */
  void updateEntity(String tableName, EntityUpdateDto entityUpdate);
}
