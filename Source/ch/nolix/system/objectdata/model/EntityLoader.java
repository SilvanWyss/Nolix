/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.system.objectdata.entitytool.EntityCreator;
import ch.nolix.system.objectdata.entitytool.EntityFiller;
import ch.nolix.systemapi.middata.loader.IDataReader;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class EntityLoader {
  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final EntityFiller ENTITY_FILLER = new EntityFiller();

  private EntityLoader() {
  }

  public static <E extends IEntity> E loadEntityById(
    final ITable<E> table,
    final String id,
    final IDataReader dataReader) {
    final var entityType = table.getEntityType();
    final var entity = ENTITY_CREATOR.createEmptyEntityForEntityType(entityType);
    final var tableName = table.getName();
    final var entityLoadingDto = dataReader.loadEntity(tableName, id);

    entity.internalSetParentTable(table);
    ENTITY_FILLER.fillUpEntityFromEntityLoadingDto(entity, entityLoadingDto);

    return entity;
  }
}
