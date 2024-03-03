//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.core.reflection.GlobalClassTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;

//class
public final class EntityCreator {

  //method
  public <E extends IEntity> E createEmptyEntityForTable(final ITable<E> table) {

    final var entityType = table.getEntityType();

    return createEmptyEntityForEntityType(entityType);
  }

  //method
  public <E extends IEntity> E createEmptyEntityForEntityType(final Class<E> entityType) {
    return GlobalClassTool.createInstanceFromDefaultConstructorOf(entityType);
  }
}
