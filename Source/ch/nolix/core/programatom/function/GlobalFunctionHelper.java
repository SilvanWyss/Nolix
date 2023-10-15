//package declaration
package ch.nolix.core.programatom.function;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IBooleanGetter;

//class
public final class GlobalFunctionHelper {

  // static method
  public static IBooleanGetter createNegatorFor(final IBooleanGetter condition) {

    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();

    return (() -> !condition.getOutput());
  }

  // constructor
  private GlobalFunctionHelper() {
  }
}
