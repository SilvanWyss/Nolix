//package declaration
package ch.nolix.system.objectdatabase.propertyhelper;

import ch.nolix.system.sqldatabaserawdata.databasedto.ContentFieldDto;
import ch.nolix.system.sqldatabaserawdata.databasedto.EntityUpdateDto;
import ch.nolix.systemapi.objectdatabaseapi.databaseapi.IValue;
import ch.nolix.systemapi.objectdatabaseapi.propertyhelperapi.IValueHelper;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class ValueHelper extends PropertyHelper implements IValueHelper {

  //method
  @Override
  public boolean canSetGivenValue(final IValue<?> value, final Object valueToSet) {
    return canSetValue(value)
        && valueToSet != null;
  }

  //method
  @Override
  public IEntityUpdateDto createEntityUpdateDtoForSetValue(final IValue<?> value, final Object setValue) {

    final var parentEntity = value.getStoredParentEntity();

    return new EntityUpdateDto(
        parentEntity.getId(),
        parentEntity.getSaveStamp(),
        new ContentFieldDto(value.getName(), setValue.toString()));
  }

  //method
  private boolean canSetValue(final IValue<?> value) {
    return value != null
        && value.isOpen();
  }
}
