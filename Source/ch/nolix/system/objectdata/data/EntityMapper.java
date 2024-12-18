package ch.nolix.system.objectdata.data;

import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;
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

  private void addDataFromContentFieldToEntity(final ContentFieldDto<Object> contentField, final BaseEntity entity) {
    addDataFromContentFieldToProperty(
      contentField,
      entity.internalGetStoredFieldByName(contentField.columnName()));
  }

  private void addDataFromContentFieldToProperty(final ContentFieldDto<Object> contentField, final Field field) {
    field.internalSetOrClearFromContent(contentField.optionalContent().orElse(null));
  }

  private void addDataToEntityFromLoadedEntityDto(final ILoadedEntityDto loadedEntityDto, final BaseEntity entity) {

    entity.internalSetId(loadedEntityDto.getId());
    entity.internalSetSaveStamp(loadedEntityDto.getSaveStamp());

    for (final var cf : loadedEntityDto.getContentFields()) {
      addDataFromContentFieldToEntity(cf, entity);
    }
  }
}
