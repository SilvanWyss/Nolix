/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.perfomance;

import java.util.function.IntFunction;

import ch.nolix.baseapi.validation.performance.IObjectSupplierMediator;

/**
 * @author Silvan Wyss
 */
public final class PerformanceValidator {
  private PerformanceValidator() {
  }

  public static <O> IObjectSupplierMediator<O> assertThatOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
