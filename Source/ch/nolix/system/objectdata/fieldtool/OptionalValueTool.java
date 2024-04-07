//package declaration
package ch.nolix.system.objectdata.fieldtool;

//own imports
import ch.nolix.system.sqlrawdata.datadto.ContentFieldDto;
import ch.nolix.system.sqlrawdata.datadto.EntityUpdateDto;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.fieldtoolapi.IOptionalValueTool;
import ch.nolix.systemapi.rawdataapi.datadtoapi.IEntityUpdateDto;

//class
public final class OptionalValueTool extends FieldTool implements IOptionalValueTool {

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
