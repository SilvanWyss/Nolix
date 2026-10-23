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

  /**
   * @param objectSupplier
   * @param <O>            the type of the objects the given objectSupplier
   *                       supplies
   * @return a new {@link IObjectSupplierMediator} for the given objectSupplier
   */
  public static <O> IObjectSupplierMediator<O> assertThatOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
