//package declaration
package ch.nolix.system.objectdata.propertytool;

//Java imports
import java.util.Optional;

import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalReference;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IOptionalReferenceTool;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//class
public final class OptionalReferenceTool extends PropertyTool implements IOptionalReferenceTool {

  //method
  @Override
  public boolean canClear(final IOptionalReference<?> optionalReference) {
    return optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }

  //method
  @Override
  public boolean canSetGivenEntity(final IOptionalReference<?> optionalReference, final IEntity entity) {
    return canSetEntity(optionalReference)
    && entity != null
    && entity.isOpen()
    && optionalReference.getReferencedTableName().equals(entity.getParentTableName());
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForClear(final IOptionalReference<?> optionalReference) {

    final var parentEntity = optionalReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      new ContentFieldDto(optionalReference.getName()));
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForSetEntity(
    final IOptionalReference<?> optionalReference,
    final IEntity entity) {

    final var parentEntity = optionalReference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      new ContentFieldDto(optionalReference.getName(), entity.getId()));
  }

  //method
  @Override
  public Optional<? extends IProperty> getOptionalStoredBackReferencingProperty(
    final IOptionalReference<?> optionalReference) {
    return optionalReference
      .getReferencedEntity()
      .technicalGetStoredProperties()
      .getOptionalStoredFirst(p -> p.referencesBackProperty(optionalReference));
  }

  //method
  private boolean canSetEntity(final IOptionalReference<?> optionalReference) {
    return optionalReference != null
    && optionalReference.belongsToEntity()
    && optionalReference.getStoredParentEntity().isOpen();
  }
}