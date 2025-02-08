package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.core.structurecontrol.reflection.GlobalReflectionTool;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;

final class EntityCreator {

  public <E extends IEntity> E createEmptyEntityOf(final Class<E> entityType) {
    return GlobalReflectionTool.createInstanceFromDefaultConstructorOfClass(entityType);
  }
}
