/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.errorcontrol.performancevalidation;

import java.util.function.LongToDoubleFunction;

/**
 * @author Silvan Wyss
 */
public interface IActionMediator {
  void hasConstantOrLowerTimeComplexity();

  void hasGivenOrLowerTimeComplexity(LongToDoubleFunction timeComplexityFunction);

  void hasLinearOrLowerTimeComplexity();

  void hasQuadraticOrLowerTimeComplexity();
}
