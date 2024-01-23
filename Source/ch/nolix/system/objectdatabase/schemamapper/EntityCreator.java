//package declaration
package ch.nolix.system.objectdatabase.schemamapper;

//own imports
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;

//class
final class EntityCreator {

  //method
  public <E extends IEntity> E createEmptyEntityOf(final Class<E> entityType) {
    return GlobalClassTool.createInstanceFromDefaultConstructorOf(entityType);
  }
}
