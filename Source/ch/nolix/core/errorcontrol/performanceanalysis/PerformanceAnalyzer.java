/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.errorcontrol.performanceanalysis;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

import ch.nolix.coreapi.errorcontrol.performanceanalysis.IPerformanceAnalyzer;

/**
 * @author Silvan Wyss
 */
public final class PerformanceAnalyzer implements IPerformanceAnalyzer {
  /**
   * {@inheritDoc}
   */
  @Override
  public <O> boolean onObjectsFromObjectSupplierActionHasGivenOrLowerTimeComplexity(
    final IntFunction<O> objectSupplier,
    final Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction) {
    return //
    PerformanceAnalyzerHelper.onObjectsFromObjectSupplierActionRunsAtLeastOnceWithGivenOrLowerTimeComplexity(
      objectSupplier,
      action,
      timeComplexityFunction);
  }
}
