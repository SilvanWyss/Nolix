/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.validation.performance;

import java.util.function.LongToDoubleFunction;

/**
 * @author Silvan Wyss
 */
public interface IActionMediator {
  /**
   * @throws RuntimeException if the action of the current {@link IActionMediator}
   *                          does not have a constant or lower time complexity
   */
  void hasConstantOrLowerTimeComplexity();

  /**
   * @param timeComplexityFunction
   * @throws RuntimeException if the action of the current {@link IActionMediator}
   *                          does not have the given timeComplexityFunction or a
   *                          lower time complexity
   */
  void hasGivenOrLowerTimeComplexity(LongToDoubleFunction timeComplexityFunction);

  /**
   * @throws RuntimeException if the action of the current {@link IActionMediator}
   *                          does not have a linear or lower time complexity
   */
  void hasLinearOrLowerTimeComplexity();

  /**
   * @throws RuntimeException if the action of the current {@link IActionMediator}
   *                          does not have a quadratic or lower time complexity
   */
  void hasQuadraticOrLowerTimeComplexity();
}
