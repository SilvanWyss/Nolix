package ch.nolix.systemapi.majordataapi.adapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.majordataapi.modelapi.EntityLoadingDto;
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

  boolean tableContainsEntityWithValueInColumn(String tableName, String columnName, String value);

  boolean tableContainsEntityWithValueInColumnIgnoringEntities(
    String tableName,
    String columnName,
    IContainer<String> entitiesToIgnoreIds,
    String value);
}
