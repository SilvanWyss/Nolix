/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.testing.performancetest;

import java.util.function.IntFunction;

import ch.nolix.base.validation.perfomance.ObjectSupplierMediator;
import ch.nolix.baseapi.validation.performance.IObjectSupplierMediator;

/**
 * @author Silvan Wyss
 */
public abstract class PerformanceTest { // NOSONAR: PerformanceTest does not have abstract methods.

  protected static final <O> IObjectSupplierMediator<O> expectOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
