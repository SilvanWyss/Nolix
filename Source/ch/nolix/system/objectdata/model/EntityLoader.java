package ch.nolix.system.objectdata.model;

import ch.nolix.system.objectdata.datatool.EntityCreator;
import ch.nolix.system.objectdata.modelfiller.EntityFiller;
import ch.nolix.systemapi.middata.adapter.IDataReader;
import ch.nolix.systemapi.objectdata.datatool.IEntityCreator;
import ch.nolix.systemapi.objectdata.model.IEntity;
import ch.nolix.systemapi.objectdata.model.ITable;
import ch.nolix.systemapi.objectdata.modelfiller.IEntityFiller;

public final class EntityLoader {

  private static final IEntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IEntityFiller ENTITY_FILLER = new EntityFiller();

  private EntityLoader() {
  }

  public static <E extends IEntity> E loadEntityById(
    final ITable<E> table,
    final String id,
    final IDataReader dataReader) {

    final var entity = ENTITY_CREATOR.createEmptyEntityForTable(table);
    entity.internalSetParentTable(table);

    final var tableName = table.getName();
    final var entityLoadingDto = dataReader.loadEntity(tableName, id);
    ENTITY_FILLER.fillUpEntityFromEntityLoadingDto(entity, entityLoadingDto);

    return entity;
  }
}
