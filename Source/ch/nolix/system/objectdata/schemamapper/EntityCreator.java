//package declaration
package ch.nolix.system.objectdata.schemamapper;

//own imports
import ch.nolix.core.reflection.GlobalReflectionTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;

//class
final class EntityCreator {

  //method
  public <E extends IEntity> E createEmptyEntityOf(final Class<E> entityType) {
    return GlobalReflectionTool.createInstanceFromDefaultConstructorOfClass(entityType);
  }
}
