package ch.nolix.core.testing.performancetest;

import java.util.function.IntFunction;

import ch.nolix.core.errorcontrol.perfomancevalidator.ObjectSupplierMediator;
import ch.nolix.coreapi.errorcontrol.performancevalidation.IObjectSupplierMediator;

public abstract class PerformanceTest { //NOSONAR: PerformanceTest does not have abstract methods.

  protected static final <O> IObjectSupplierMediator<O> expectOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
