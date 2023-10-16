//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityUpdateDto;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IMultiValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IMultiValueHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class MultiValueHelper extends PropertyHelper implements IMultiValueHelper {

  //method
  @Override
  public boolean canAddGivenValue(final IMultiValue<?> multiValue, final Object value) {
    return assertCanAddValue(multiValue)
        && value != null;
  }

  //method
  @Override
  public boolean canClear(final IMultiValue<?> multiValue) {
    return multiValue != null
        && multiValue.belongsToEntity()
        && multiValue.getStoredParentEntity().isOpen();
  }

  //method
  @Override
  public <V> boolean canRemoveValue(final IMultiValue<V> multiValue, final V value) {
    return canRemoveValue(multiValue)
        && value != null;
  }

  //method
  @Override
  public <V> IEntityUpdateDto createEntityUpdateDtoForAddedValue(
      final IMultiValue<V> multiValue,
      final V addedValue) {

    final var parentEntity = multiValue.getStoredParentEntity();

    return new EntityUpdateDto(
        parentEntity.getId(),
        parentEntity.getSaveStamp(),
        new ContentFieldDto(multiValue.getName(), ""));
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForClear(final IMultiValue<?> multiValue) {

    final var parentEntity = multiValue.getStoredParentEntity();

    return new EntityUpdateDto(
        parentEntity.getId(),
        parentEntity.getSaveStamp(),
        new ContentFieldDto(multiValue.getName()));
  }

  //method
  private boolean assertCanAddValue(final IMultiValue<?> multiValue) {
    return multiValue != null
        && multiValue.belongsToEntity()
        && multiValue.getStoredParentEntity().isOpen();
  }

  //method
  private boolean canRemoveValue(final IMultiValue<?> multiValue) {
    return multiValue != null
        && multiValue.isOpen();
  }
}
