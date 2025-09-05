package ch.nolix.core.errorcontrol.perfomancevalidator;

import java.util.function.Consumer;
import java.util.function.IntFunction;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.errorcontrol.performancevalidator.IActionMediator;
import ch.nolix.coreapi.errorcontrol.performancevalidator.IObjectSupplierMediator;

public final class ObjectSupplierMediator<O> implements IObjectSupplierMediator<O> {
  private final IntFunction<O> objectSupplier;

  private ObjectSupplierMediator(final IntFunction<O> objectSupplier) {
    Validator.assertThat(objectSupplier).thatIsNamed("object supplier").isNotNull();

    this.objectSupplier = objectSupplier;
  }

  public static <O2> IObjectSupplierMediator<O2> forObjectSupplier(final IntFunction<O2> objectSupplier) {
    return new ObjectSupplierMediator<>(objectSupplier);
  }

  @Override
  public IActionMediator running(final Consumer<O> action) {
    return ActionMediator.forObjectSupplierAndAction(objectSupplier, action);
  }
}
