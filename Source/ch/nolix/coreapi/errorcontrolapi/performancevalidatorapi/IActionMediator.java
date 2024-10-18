package ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi;

import java.util.function.LongToDoubleFunction;

public interface IActionMediator {

  void hasConstantTimeComplexity();

  void hasGivenOrLowerTimeComplexity(LongToDoubleFunction timeComplexityFunction);

  void hasLinearOrLowerTimeComplexity();

  void hasQuadraticOrLowerTimeComplexity();
}
