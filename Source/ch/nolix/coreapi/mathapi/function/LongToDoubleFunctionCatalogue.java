//package declaration
package ch.nolix.coreapi.mathapi.function;

//Java imports
import java.util.function.LongToDoubleFunction;

//class
public final class LongToDoubleFunctionCatalogue {

  //constant
  public static final LongToDoubleFunction CONSTANT_FUNCTION = n -> 1.0;

  //constant
  public static final LongToDoubleFunction LINEAR_FUNCTION = n -> n;

  //constant
  public static final LongToDoubleFunction QUADRATIC_FUNCTION = n -> n * n;

  //constructor
  private LongToDoubleFunctionCatalogue() {
  }
}
