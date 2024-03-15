//package declaration
package ch.nolix.system.objectdata.propertytool;

//own imports
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IMultiValue;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IMultiValueTool;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//class
public final class MultiValueTool extends PropertyTool implements IMultiValueTool {

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
