//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

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