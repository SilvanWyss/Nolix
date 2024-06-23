//package declaration
package ch.nolix.core.errorcontrol.perfomancevalidator;

//Java imports
import java.util.function.Consumer;
import java.util.function.IntFunction;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IActionMediator;
import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IObjectSupplierMediator;

//class
public final class ObjectSupplierMediator<O> implements IObjectSupplierMediator<O> {

  //attribute
  private final IntFunction<O> objectSupplier;

  //constructor
  private ObjectSupplierMediator(final IntFunction<O> objectSupplier) {

    GlobalValidator.assertThat(objectSupplier).thatIsNamed("object supplier").isNotNull();

    this.objectSupplier = objectSupplier;
  }

  //static method
  public static <O2> IObjectSupplierMediator<O2> forObjectSupplier(final IntFunction<O2> objectSupplier) {
    return new ObjectSupplierMediator<>(objectSupplier);
  }

  //method
  @Override
  public IActionMediator running(final Consumer<O> action) {
    return ActionMediator.forObjectSupplierAndAction(objectSupplier, action);
  }
}
