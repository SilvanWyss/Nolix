//package declaration
package ch.nolix.systemapi.rawdataapi.dataadapterapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;
import ch.nolix.systemapi.timeapi.momentapi.ITime;

//interface
public interface IDataReader extends GroupCloseable {

  //method
  ITime getSchemaTimestamp();

  //method declaration
  IContainer<ILoadedEntityDto> loadEntitiesOfTable(String tableName);

  //method declaration
  IContainer<String> loadMultiReferenceEntries(
    String tableName,
    String entityId,
    String multiReferenceColumnName);

  //method declaration
  IContainer<Object> loadMultiValueEntries(
    String tableName,
    String entityId,
    String multiValueColumnName);

  //method declaration
  ILoadedEntityDto loadEntity(String tableName, String id);

  //method declaration
  boolean multiValueIsEmpty(String tableName, String entityId, String multiValueColumnId);

  //method declaration
  boolean tableContainsEntityWithGivenValueAtGivenColumn(String tableName, String columnName, String value);

  //method declaration
  boolean tableContainsEntityWithGivenId(String tableName, String id);
}
