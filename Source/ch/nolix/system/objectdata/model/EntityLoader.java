/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.objectdata.model;

import ch.nolix.system.objectdata.entitytool.EntityCreator;
import ch.nolix.system.objectdata.entitytool.EntityFiller;
import ch.nolix.systemapi.middata.loader.IDataReader;
import ch.nolix.systemapi.objectdata.model.Entity;
import ch.nolix.systemapi.objectdata.model.ITable;

/**
 * @author Silvan Wyss
 */
public final class EntityLoader {
  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final EntityFiller ENTITY_FILLER = new EntityFiller();

  private EntityLoader() {
  }

  /**
   * @param table
   * @param id
   * @param dataReader
   * @param <E>        the type of the {@link Entity}s of the given table
   * @return a new {@link Entity} of the given table, loaded with the given
   *         dataReader, with the given id
   * @throws RuntimeException if the given dataReader cannot load a {@link Entity}
   *                          with the given id from the given table
   */
  public static <E extends Entity> E loadEntityById(
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
