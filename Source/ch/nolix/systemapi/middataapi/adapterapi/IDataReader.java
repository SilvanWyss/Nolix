package ch.nolix.systemapi.middataapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.middataapi.modelapi.EntityLoadingDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDataReader extends GroupCloseable {

  String getDatabaseName();

  ITime getSchemaTimestamp();

  IContainer<EntityLoadingDto> loadEntities(String tableName);

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
