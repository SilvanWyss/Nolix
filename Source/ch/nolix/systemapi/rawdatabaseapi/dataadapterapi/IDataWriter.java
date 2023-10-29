//package declaration
package ch.nolix.systemapi.rawdatabaseapi.dataadapterapi;

import ch.nolix.coreapi.programcontrolapi.savecontrolapi.IResettableChangeSaver;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityHeadDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.INewEntityDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
/**
 * @author Silvan Wyss
 * @date 2021-09-19
 */
public interface IDataWriter extends IResettableChangeSaver {

  //method declaration
  /**
   * There is not asserted that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnName
   */
  void deleteMultiReferenceEntries(String tableName, String entityId, String multiReferenceColumnName);

  //method declaration
  /**
   * There is not asserted that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnName
   */
  void deleteMultiValueEntries(String tableName, String entityId, String multiValueColumnName);

  //method declaration
  /**
   * There is not asserted that the concerned entity was not changed in the
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

  //method declaration
  /**
   * There is not asserted that the concerned entity was not changed in the
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

  //method declaration
  /**
   * Causes an error if the concerned entity was deleted or changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entity
   */
  void deleteEntity(String tableName, IEntityHeadDto entity);

  //method declaration
  /**
   * Will cause an error if the concerned table does not contain an entity with
   * the given entityId. This method can be used to prevent from referencing an
   * entity that was deleted in the meanwhile.
   * 
   * @param tableName
   * @param entityId
   */
  void expectTableContainsEntity(String tableName, String entityId);

  //method declaration
  /**
   * Will cause an error if the database does not have the given schema timestamp.
   * 
   * @param schemaTimestamp
   */
  void expectGivenSchemaTimestamp(ITime schemaTimestamp);

  //method declaration
  /**
   * There is not asserted that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnName
   * @param referencedEntityId
   */
  void insertMultiReferenceEntry(
    String tableName,
    String entityId,
    String multiReferenceColumnName,
    String referencedEntityId);

  //method declaration
  /**
   * There is not asserted that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityId
   * @param multiValueColumnName
   * @param entry
   */
  void insertMultiValueEntry(String tableName, String entityId, String multiValueColumnName, String entry);

  //method declaration
  /**
   * Inserts the given newEntity into the table with the given tableName.
   * 
   * @param tableName
   * @param newEntity
   */
  void insertNewEntity(String tableName, INewEntityDto newEntity);

  //method declaration
  /**
   * Sets the concerned entity as updated. Will cause an error if the concerned
   * entity was deleted or changed in the meanwhile.
   * 
   * @param tableName
   * @param entity
   */
  void setEntityAsUpdated(String tableName, IEntityHeadDto entity);

  //method declaration
  /**
   * There is not asserted that the concerned entity was not changed in the
   * meanwhile.
   * 
   * @param tableName
   * @param entityUpdate
   */
  void updateEntity(String tableName, IEntityUpdateDto entityUpdate);
}
