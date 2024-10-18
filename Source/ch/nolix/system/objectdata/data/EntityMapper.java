package ch.nolix.system.objectdata.data;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;

final class EntityMapper {

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  public <E extends IEntity> E createLoadedEntityFromDto(
    final ILoadedEntityDto loadedEntityDto,
    final ITable<E> table) {

    final var loadedEntity = ENTITY_CREATOR.createEmptyEntityForTable(table);

    final var concreteEntity = (BaseEntity) loadedEntity;
    concreteEntity.internalSetParentTable(table);
    concreteEntity.internalSetLoaded();
    addDataToEntityFromLoadedEntityDto(loadedEntityDto, concreteEntity);

    return loadedEntity;
  }

  private void addDataFromContentFieldToEntity(final ILoadedContentFieldDto contentField, final BaseEntity entity) {
    addDataFromContentFieldToProperty(
      contentField,
      entity.internalGetStoredFieldByName(contentField.getColumnName()));
  }

  private void addDataFromContentFieldToProperty(final ILoadedContentFieldDto contentField, final Field field) {
    field.internalSetOrClearFromContent(contentField.getOptionalValue().orElse(null));
  }

  private void addDataToEntityFromLoadedEntityDto(final ILoadedEntityDto loadedEntityDto, final BaseEntity entity) {

    entity.internalSetId(loadedEntityDto.getId());
    entity.internalSetSaveStamp(loadedEntityDto.getSaveStamp());

    for (final var cf : loadedEntityDto.getContentFields()) {
      addDataFromContentFieldToEntity(cf, entity);
    }
  }
}
