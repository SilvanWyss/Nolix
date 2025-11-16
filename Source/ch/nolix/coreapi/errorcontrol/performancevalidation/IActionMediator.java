package ch.nolix.coreapi.errorcontrol.performancevalidation;

import java.util.function.LongToDoubleFunction;

public interface IActionMediator {
  void hasConstantOrLowerTimeComplexity();

  void hasGivenOrLowerTimeComplexity(LongToDoubleFunction timeComplexityFunction);

  void hasLinearOrLowerTimeComplexity();

  void hasQuadraticOrLowerTimeComplexity();
}
