//package declaration
package ch.nolix.system.objectdatabase.schemamapper;

//own imports
import ch.nolix.core.reflection.GlobalClassHelper;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;

//class
final class EntityCreator {

  //method
  public <E extends IEntity> E createEmptyEntityOf(final Class<E> entityType) {
    return GlobalClassHelper.createInstanceFromDefaultConstructorOf(entityType);
  }
}
