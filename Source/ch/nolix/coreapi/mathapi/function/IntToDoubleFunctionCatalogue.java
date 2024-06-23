//package declaration
package ch.nolix.coreapi.mathapi.function;

//Java imports
import java.util.function.IntToDoubleFunction;

//class
public final class IntToDoubleFunctionCatalogue {

  //constant
  public static final IntToDoubleFunction CONSTANT_FUNCTION = n -> 1.0;

  //constant
  public static final IntToDoubleFunction LINEAR_FUNCTION = n -> n;

  //constructor
  private IntToDoubleFunctionCatalogue() {
  }
}
