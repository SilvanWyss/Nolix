package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.core.reflection.reflectiontool.ReflectionTool;
import ch.nolix.systemapi.objectdata.model.IEntity;

final class EntityCreator {

  public <E extends IEntity> E createEmptyEntityOf(final Class<E> entityType) {
    return ReflectionTool.createInstanceFromDefaultConstructorOfClass(entityType);
  }
}
