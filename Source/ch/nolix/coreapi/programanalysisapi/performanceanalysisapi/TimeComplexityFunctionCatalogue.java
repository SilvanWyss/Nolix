package ch.nolix.coreapi.programanalysisapi.performanceanalysisapi;

import java.util.function.LongToDoubleFunction;

public final class TimeComplexityFunctionCatalogue {

  public static final LongToDoubleFunction CONSTANT = n -> 1.0;

  public static final LongToDoubleFunction LOGARITHM = Math::log;

  public static final LongToDoubleFunction LINEAR = n -> n;

  public static final LongToDoubleFunction QUADRATIC = n -> n * n;

  public static final LongToDoubleFunction CUBIC = n -> n * n * n;

  private TimeComplexityFunctionCatalogue() {
  }
}
