package ch.nolix.system.objectdata.model;

import ch.nolix.system.objectdata.datafiller.EntityFiller;
import ch.nolix.system.objectdata.datatool.EntityCreator;
import ch.nolix.systemapi.objectdataapi.datafillerapi.IEntityFiller;
import ch.nolix.systemapi.objectdataapi.modelapi.IEntity;
import ch.nolix.systemapi.objectdataapi.modelapi.ITable;
import ch.nolix.systemapi.rawdataapi.dataadapterapi.IDataReader;

public final class EntityLoader {

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  private static final IEntityFiller ENTITY_FILLER = new EntityFiller();

  public <E extends IEntity> E loadEntityById(final ITable<E> table, final String id, final IDataReader dataReader) {

    final var entity = ENTITY_CREATOR.createEmptyEntityForTable(table);
    entity.internalSetParentTable(table);
    entity.internalSetLoaded();

    final var tableName = table.getName();
    final var entityLoadingDto = dataReader.loadEntity(tableName, id);
    ENTITY_FILLER.fillUpEntityFromEntityLoadingDto(entity, entityLoadingDto);

    return entity;
  }
}
