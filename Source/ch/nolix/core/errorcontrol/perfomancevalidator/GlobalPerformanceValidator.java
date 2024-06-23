//package declaration
package ch.nolix.core.errorcontrol.perfomancevalidator;

//Java imports
import java.util.function.IntFunction;

//own imports
import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IObjectSupplierMediator;

//class
public final class GlobalPerformanceValidator {

  //constructor
  private GlobalPerformanceValidator() {
  }

  //static method
  public static <O> IObjectSupplierMediator<O> assertThatOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
