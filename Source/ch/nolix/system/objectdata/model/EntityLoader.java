package ch.nolix.system.objectdata.model;

import ch.nolix.system.objectdata.entitytool.EntityCreator;
import ch.nolix.system.objectdata.entitytool.EntityFiller;
import ch.nolix.systemapi.middata.adapter.IDataReader;
import ch.nolix.systemapi.objectdata.entitytool.IEntityCreator;
import ch.nolix.systemapi.objectdata.entitytool.IEntityFiller;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;

public final class EntityLoader {

  private static final IEntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IEntityFiller ENTITY_FILLER = new EntityFiller();

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
