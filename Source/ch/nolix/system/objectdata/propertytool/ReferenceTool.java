//package declaration
package ch.nolix.system.objectdata.propertytool;

import java.util.Optional;

import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IEntity;
import ch.nolix.systemapi.objectdataapi.dataapi.IProperty;
import ch.nolix.systemapi.objectdataapi.dataapi.IReference;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IReferenceTool;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//class
public final class ReferenceTool extends PropertyTool implements IReferenceTool {

  //method
  @Override
  public boolean canSetGivenEntity(final IReference<?> reference, final IEntity entity) {
    return canSetEntity(reference)
    && entity != null
    && entity.isOpen()
    && reference.getReferencedTableName().equals(entity.getParentTableName());
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForSetEntity(
    final IReference<?> reference,
    final IEntity entity) {

    final var parentEntity = reference.getStoredParentEntity();

    return new EntityUpdateDto(
      parentEntity.getId(),
      parentEntity.getSaveStamp(),
      new ContentFieldDto(reference.getName(), entity.getId()));
  }

  //method
  @Override
  public Optional<? extends IProperty> getOptionalStoredBackReferencingProperty(final IReference<?> reference) {
    return reference
      .getReferencedEntity()
      .technicalGetStoredProperties()
      .getOptionalStoredFirst(p -> p.referencesBackProperty(reference));
  }

  //method
  private boolean canSetEntity(final IReference<?> reference) {
    return //
    reference != null
    && reference.belongsToEntity()
    && reference.isOpen();
  }
}
