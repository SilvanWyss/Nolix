//package declaration
package ch.nolix.system.objectdata.data;

//own imports
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.ITable;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedContentFieldDto;
import ch.nolix.systemapi.rawdataapi.datadtoapi.ILoadedEntityDto;

//class
final class EntityMapper {

  //constant
  private static final EntityCreator ENTITY_CREATOR = new EntityCreator();

  //method
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

  //method
  private void addDataFromContentFieldToEntity(final ILoadedContentFieldDto contentField, final BaseEntity entity) {
    addDataFromContentFieldToProperty(
      contentField,
      entity.internalGetRefPropertyByName(contentField.getColumnName()));
  }

  //method
  private void addDataFromContentFieldToProperty(final ILoadedContentFieldDto contentField, final Property property) {
    property.internalSetOrClearFromContent(contentField.getOptionalValue().orElse(null));
  }

  //method
  private void addDataToEntityFromLoadedEntityDto(final ILoadedEntityDto loadedEntityDto, final BaseEntity entity) {

    entity.internalSetId(loadedEntityDto.getId());
    entity.internalSetSaveStamp(loadedEntityDto.getSaveStamp());

    for (final var cf : loadedEntityDto.getContentFields()) {
      addDataFromContentFieldToEntity(cf, entity);
    }
  }
}
