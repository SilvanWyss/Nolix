//package declaration
package ch.nolix.systemapi.objectdataapi.datatoolapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.systemapi.databaseobjectapi.databaseobjecttoolapi.IDatabaseObjectTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IColumn;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//interface
public interface ITableTool extends IDatabaseObjectTool {

  //method declaration
  boolean allNewAndEditedMandatoryPropertiesAreSet(ITable<?> table);

  //method declaration
  boolean canInsertEntity(ITable<?> table);

  //method declaration
  boolean canInsertGivenEntity(ITable<?> table, IEntity entity);

  //method declaration
  boolean containsEntityWithGivenIdInLocalData(ITable<?> table, String id);

  //method declaration
  <E extends IEntity> IContainer<IColumn> getColumsThatReferenceGivenTable(ITable<E> table);

  //method declaration
  boolean hasChanges(ITable<?> table);

  //method declaration
  boolean hasInsertedGivenEntityInLocalData(ITable<?> table, IEntity entity);
}
