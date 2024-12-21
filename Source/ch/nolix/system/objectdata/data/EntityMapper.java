package ch.nolix.system.objectdata.data;

import ch.nolix.system.objectdata.datatool.EntityCreator;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.datadto.ContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadto.EntityLoadingDto;

final class EntityMapper {

  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  public <E extends IEntity> E createLoadedEntityFromDto(
    final EntityLoadingDto loadedEntityDto,
    final ITable<E> table) {

    final var loadedEntity = ENTITY_CREATOR.createEmptyEntityForTable(table);

    final var concreteEntity = (BaseEntity) loadedEntity;
    concreteEntity.internalSetParentTable(table);
    concreteEntity.internalSetLoaded();
    addDataToEntityFromLoadedEntityDto(loadedEntityDto, concreteEntity);

    return loadedEntity;
  }

  private void addDataFromContentFieldToEntity(final ContentFieldDto<Object> contentField, final BaseEntity entity) {
    addDataFromContentFieldToField(
      contentField,
      entity.internalGetStoredFieldByName(contentField.columnName()));
  }

  private void addDataFromContentFieldToField(final ContentFieldDto<Object> contentField, final AbstractField field) {
    field.internalSetOrClearFromContent(contentField.optionalContent().orElse(null));
  }

  private void addDataToEntityFromLoadedEntityDto(final EntityLoadingDto loadedEntityDto, final BaseEntity entity) {

    entity.internalSetId(loadedEntityDto.id());
    entity.internalSetSaveStamp(loadedEntityDto.saveStamp());

    for (final var cf : loadedEntityDto.contentFields()) {
      addDataFromContentFieldToEntity(cf, entity);
    }
  }
}
