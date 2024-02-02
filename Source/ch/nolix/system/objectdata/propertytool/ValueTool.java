//package declaration
package ch.nolix.system.objectdata.propertytool;

//own imports
import ch.nolix.system.sqlrawdatabase.databasedto.ContentFieldDto;
import ch.nolix.system.sqlrawdatabase.databasedto.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IValue;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IValueTool;
import ch.nolix.systemapi.rawdatabaseapi.databasedtoapi.IEntityUpdateDto;

//class
public final class ValueTool extends PropertyTool implements IValueTool {

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
