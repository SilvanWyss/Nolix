package ch.nolix.system.objectdata.schemamapper;

import ch.nolix.core.structurecontrol.reflectiontool.ReflectionTool;
import ch.nolix.systemapi.objectdata.model.IEntity;

final class EntityCreator {

  public <E extends IEntity> E createEmptyEntityOf(final Class<E> entityType) {
    return ReflectionTool.createInstanceFromDefaultConstructorOfClass(entityType);
  }
}
