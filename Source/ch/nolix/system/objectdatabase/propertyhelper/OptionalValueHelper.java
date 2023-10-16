//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityUpdateDto;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IOptionalValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IOptionalValueHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class OptionalValueHelper extends PropertyHelper implements IOptionalValueHelper {

  @Override
  public boolean canSetGivenValue(final IOptionalValue<?> optionalValue, final Object value) {
    return canSetValue(optionalValue)
        && value != null;
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForSetValue(
      final IOptionalValue<?> optionalValue,
      final Object value) {

    final var parentEntity = optionalValue.getStoredParentEntity();

    return new EntityUpdateDto(
        parentEntity.getId(),
        parentEntity.getSaveStamp(),
        new ContentFieldDto(optionalValue.getName(), value.toString()));
  }

  //method
  private boolean canSetValue(final IOptionalValue<?> optionalValue) {
    return optionalValue != null
        && optionalValue.isOpen();
  }
}
