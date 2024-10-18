package ch.nolix.core.errorcontrol.perfomancevalidator;

import java.util.function.IntFunction;

import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IObjectSupplierMediator;

public final class GlobalPerformanceValidator {

  private GlobalPerformanceValidator() {
  }

  public static <O> IObjectSupplierMediator<O> assertThatOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
