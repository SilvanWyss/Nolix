package ch.nolix.systemapi.middataapi.adapterapi;

import ch.nolix.coreapi.attribute.mandatoryattribute.IDatabaseNameHolder;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.middataapi.modelapi.MultiReferenceEntryDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

/**
 * @author Silvan Wyss
 * @version 2021-09-18
 */
public interface IDataReader extends GroupCloseable, IDatabaseNameHolder {

  /**
   * @return the schema timestamp from the database.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  ITime getSchemaTimestamp();

  /**
   * @param tableName
   * @return the entities, that are in the table with the given tableName, from
   *         the database.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  IContainer<EntityLoadingDto> loadEntities(String tableName);

  /**
   * @param tableName
   * @param entityId
   * @return the entity, that is in the table with the given tableName and has the
   *         given entityId, from the database.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  EntityLoadingDto loadEntity(String tableName, String entityId);

  /**
   * @param tableName
   * @param entityId
   * @param multiBackReferenceColumnName
   * @return the entity ids of the multi back reference, that is in the multi back
   *         reference column with the given multiBackReferenceColumnName and
   *         belongs to the entity, that has the given entityId and is in the
   *         table with the given tableName, from the database.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  IContainer<String> loadMultiBackReferenceBackReferencedEntityIds(
    String tableName,
    String entityId,
    String multiBackReferenceColumnName);

  /**
   * @param tableName
   * @param entityId
   * @param multiReferenceColumnName
   * @return the multi reference entries of the the multi reference field, that is
   *         in the multi reference column with the given multiReferenceColumnName
   *         and belongs to the entity, that has the given entityId and is in the
   *         table with the given tableName, from the database.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  IContainer<MultiReferenceEntryDto> loadMultiReferenceEntries(
    String tableName,
    String entityId,
    String multiReferenceColumnName);

  /**
   * @param tableName
   * @param entityId
   * @param multiValueColumnName
   * @return the values of the multi value, that is in the multi value column with
   *         the given multiValueColumnName and belongs to the entity, that has
   *         the given entityId and is in the table with the given tableName, from
   *         the database.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  IContainer<Object> loadMultiValueValues(String tableName, String entityId, String multiValueColumnName);

  /**
   * @param tableName
   * @param entityId
   * @return true if the table with the given tableName contains an entity with
   *         the given entityId on the database, false otherwise.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  boolean tableContainsEntity(String tableName, String entityId);

  /**
   * @param tableName
   * @param columnName
   * @param value
   * @return true if the table with the given tableName contains in the column
   *         with the given columnName the given value on the database, false
   *         otherwise.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  boolean tableContainsEntityWithValueAtColumn(String tableName, String columnName, String value);

  /**
   * @param tableName
   * @param columnName
   * @param value
   * @param entitiesToIgnoreIds
   * @return true if the table with the given tableName contains in the column
   *         with the given columnName the given value ignoring the entities with
   *         the given entitiesToIgnoreIds on the database, false otherwise.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  boolean tableContainsEntityWithValueAtColumnIgnoringEntities(
    String tableName,
    String columnName,
    String value,
    IContainer<String> entitiesToIgnoreIds);
}
