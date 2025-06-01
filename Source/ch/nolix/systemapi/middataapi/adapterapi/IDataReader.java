package ch.nolix.systemapi.middataapi.adapterapi;

import ch.nolix.coreapi.attributeapi.mandatoryattributeapi.IDatabaseNameHolder;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
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
   * @param id
   * @return the entity, that is in the table with the given tableName and has the
   *         given id, from the database.
   * @throws RuntimeException if the current {@link IDataReader} is closed.
   */
  EntityLoadingDto loadEntity(String tableName, String id);

  IContainer<String> loadMultiBackReferenceEntries(
    String tableName,
    String entityId,
    String multiBackReferenceColumnName);

  IContainer<String> loadMultiReferenceEntries(
    String tableName,
    String entityId,
    String multiReferenceColumnName);

  IContainer<Object> loadMultiValueEntries(
    String tableName,
    String entityId,
    String multiValueColumnName);

  boolean tableContainsEntity(String tableName, String id);

  boolean tableContainsEntityWithValueAtColumn(String tableName, String columnName, String value);

  boolean tableContainsEntityWithValueAtColumnIgnoringEntities(
    String tableName,
    String columnName,
    String value, IContainer<String> entitiesToIgnoreIds);
}
