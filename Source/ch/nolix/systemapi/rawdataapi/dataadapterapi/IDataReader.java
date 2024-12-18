package ch.nolix.systemapi.rawdataapi.dataadapterapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.rawdataapi.datadto.EntityLoadingDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

public interface IDataReader extends GroupCloseable {

  ITime getSchemaTimestamp();

  IContainer<EntityLoadingDto> loadEntitiesOfTable(String tableName);

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

  EntityLoadingDto loadEntity(String tableName, String id);

  boolean tableContainsEntityWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  boolean tableContainsEntityWithGivenValueAtGivenColumnIgnoringGivenEntities(
    String tableName,
    String columnName,
    String value, IContainer<String> entitiesToIgnoreIds);

  boolean tableContainsEntityWithGivenId(String tableName, String id);
}
