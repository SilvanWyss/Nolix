//package declaration
package ch.nolix.system.objectschema.schemadto;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.ISaveStampConfigurationDto;
import ch.nolix.systemapi.rawschemaapi.schemadtoapi.SaveStampStrategy;

//class
public final class SaveStampConfigurationDto implements ISaveStampConfigurationDto {

  // attribute
  private final SaveStampStrategy saveStampStrategy;

  // optional attribute
  private final String baseTableName;

  // constructor
  // For a better performance, this implementation does not use all comfortable
  // methods.
  public SaveStampConfigurationDto(final SaveStampStrategy saveStampStrategy) {

    if (saveStampStrategy == null) {
      throw ArgumentIsNullException.forArgumentType(SaveStampStrategy.class);
    }

    this.saveStampStrategy = saveStampStrategy;
    baseTableName = null;
  }

  // constructor
  public SaveStampConfigurationDto(final SaveStampStrategy saveStampStrategy, final String baseTableName) {

    if (saveStampStrategy == null) {
      throw ArgumentIsNullException.forArgumentType(SaveStampStrategy.class);
    }

    if (baseTableName == null) {
      throw ArgumentIsNullException.forArgumentName("base table name");
    }

    this.saveStampStrategy = saveStampStrategy;
    this.baseTableName = baseTableName;
  }

  // method
  @Override
  public String getBaseTableName() {

    assertHasBaseTableName();

    return baseTableName;
  }

  // method
  @Override
  public SaveStampStrategy getSaveStampStrategy() {
    return saveStampStrategy;
  }

  // method
  private void assertHasBaseTableName() {
    if (!hasBaseTableName()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "base table name");
    }
  }

  // method
  private boolean hasBaseTableName() {
    return (baseTableName != null);
  }
}
