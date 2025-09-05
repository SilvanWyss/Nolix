package ch.nolix.core.errorcontrol.perfomancevalidator;

import java.util.function.IntFunction;

import ch.nolix.coreapi.errorcontrol.performancevalidator.IObjectSupplierMediator;

public final class PerformanceValidator {
  private PerformanceValidator() {
  }

  public static <O> IObjectSupplierMediator<O> assertThatOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
