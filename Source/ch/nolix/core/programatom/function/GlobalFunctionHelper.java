//package declaration
package ch.nolix.core.programatom.function;

//Java imports
import java.util.function.BooleanSupplier;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class GlobalFunctionHelper {

  //constructor
  private GlobalFunctionHelper() {
  }

  //static method
  public static BooleanSupplier createNegatorForBooleanSupplier(final BooleanSupplier booleanSupplier) {

    GlobalValidator.assertThat(booleanSupplier).thatIsNamed(BooleanSupplier.class).isNotNull();

    return (() -> !booleanSupplier.getAsBoolean());
  }
}
