//package declaration
package ch.nolix.core.programatom.function;

import java.util.function.BooleanSupplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class GlobalFunctionHelper {

  //static method
  public static BooleanSupplier createNegatorFor(final BooleanSupplier condition) {

    GlobalValidator.assertThat(condition).thatIsNamed(LowerCaseCatalogue.CONDITION).isNotNull();

    return (() -> !condition.getAsBoolean());
  }

  //constructor
  private GlobalFunctionHelper() {
  }
}
