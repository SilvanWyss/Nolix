package ch.nolix.coreapi.errorcontrol.performanceanalysis;

import java.util.function.LongToDoubleFunction;

/**
 * @author Silvan Wyss
 */
public final class TimeComplexityFunctionCatalog {
  public static final LongToDoubleFunction CONSTANT = _ -> 1.0;

  public static final LongToDoubleFunction LOGARITHM = Math::log;

  public static final LongToDoubleFunction LINEAR = n -> n;

  public static final LongToDoubleFunction QUADRATIC = n -> n * n;

  public static final LongToDoubleFunction CUBIC = n -> n * n * n;

  private TimeComplexityFunctionCatalog() {
  }
}
