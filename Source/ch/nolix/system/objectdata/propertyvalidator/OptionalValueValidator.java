//package declaration
package ch.nolix.system.objectdata.propertyvalidator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.system.objectdata.propertytool.OptionalValueTool;
import ch.nolix.systemapi.objectdataapi.dataapi.IOptionalValue;
import ch.nolix.systemapi.objectdataapi.propertytoolapi.IOptionalValueTool;
import ch.nolix.systemapi.objectdataapi.propertyvalidatorapi.IOptionalValueValidator;

//class
public final class OptionalValueValidator extends PropertyValidator implements IOptionalValueValidator {

  //constant
  private static final IOptionalValueTool OPTIONAL_VALUE_TOOL = new OptionalValueTool();

  //method
  @Override
  public <V> void assertCanSetGivenValue(final IOptionalValue<V> optionalValue, final V value) {
    if (!OPTIONAL_VALUE_TOOL.canSetGivenValue(optionalValue, value)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(optionalValue, "cannot set the given value");
    }
  }

  //method
  @Override
  public void assertHasValue(final IOptionalValue<?> optionalValue) {
    if (optionalValue.isEmpty()) {
      throw EmptyArgumentException.forArgument(optionalValue);
    }
  }
}