//package declaration
package ch.nolix.system.objectdatabase.database;

//own imports
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.ITable;

//class
public final class EntityCreator {

  //method
  public <E extends IEntity> E createEmptyEntityFor(final ITable<E> table) {
    return createEmptyEntityOf(table.getEntityType());
  }

  //method
  public <E extends IEntity> E createEmptyEntityOf(final Class<E> entityType) {
    return GlobalClassTool.createInstanceFromDefaultConstructorOf(entityType);
  }
}
