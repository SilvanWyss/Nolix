//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.dataandschemaadapterapi.IDataAndSchemaAdapter;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;

//class
public final class EntityLoader {

  //constant
  private static final EntityMapper ENTITY_MAPPER = new EntityMapper();

  //method
  public <E extends IEntity> E loadEntityById(
    final ITable<E> table,
    final String id,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {

    final var loadedEntityDto = loadEntityDtoById(table, id, dataAndSchemaAdapter);

    return ENTITY_MAPPER.createLoadedEntityFromDto(loadedEntityDto, table);
  }

  //method
  private ILoadedEntityDto loadEntityDtoById(
    final ITable<? extends IEntity> table,
    final String id,
    final IDataAndSchemaAdapter dataAndSchemaAdapter) {
    return dataAndSchemaAdapter.loadEntity(table.getName(), id);
  }
}
