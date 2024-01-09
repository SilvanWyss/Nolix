//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

//own imports
import ch.nolix.system.sqlrawdatabase.databasedto.ContentFieldDto;
import ch.nolix.system.sqlrawdatabase.databasedto.EntityUpdateDto;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IEntity;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IProperty;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IReference;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IReferenceHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class ReferenceHelper extends PropertyHelper implements IReferenceHelper {

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
  public IProperty getStoredBackReferencingPropertyOrNull(final IReference<?> reference) {
    return reference
      .getReferencedEntity()
      .technicalGetStoredProperties()
      .getStoredFirstOrNull(p -> p.referencesBackProperty(reference));
  }

  //method
  private boolean canSetEntity(final IReference<?> reference) {
    return //
    reference != null
    && reference.belongsToEntity()
    && reference.isOpen();
  }
}
