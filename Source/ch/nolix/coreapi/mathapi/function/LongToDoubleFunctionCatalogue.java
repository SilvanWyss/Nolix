package ch.nolix.coreapi.mathapi.function;

import java.util.function.LongToDoubleFunction;

public final class LongToDoubleFunctionCatalogue {

  public static final LongToDoubleFunction CONSTANT_FUNCTION = n -> 1.0;

  public static final LongToDoubleFunction LINEAR_FUNCTION = n -> n;

  public static final LongToDoubleFunction QUADRATIC_FUNCTION = n -> n * n;

  private LongToDoubleFunctionCatalogue() {
  }
}
