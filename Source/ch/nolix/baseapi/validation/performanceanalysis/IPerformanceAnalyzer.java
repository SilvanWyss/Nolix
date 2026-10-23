/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.validation.performanceanalysis;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

/**
 * @author Silvan Wyss
 */
public interface IPerformanceAnalyzer {
  /**
   * @param objectSupplier
   * @param action
   * @param timeComplexityFunction
   * @param <O>                    the type of the objects the given
   *                               objectSupplier supplies
   * @return true if the given action has the given timeComplexityFunction or a
   *         lower time complexity on the objects from the given objectSupplier,
   *         false otherwise
   */
  <O> boolean onObjectsFromObjectSupplierActionHasGivenOrLowerTimeComplexity(
    IntFunction<O> objectSupplier,
    Consumer<O> action,
    LongToDoubleFunction timeComplexityFunction);
}
