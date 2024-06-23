//package declaration
package ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi;

//Java imports
import java.util.function.LongToDoubleFunction;

//interface
public interface IActionMediator {

  //method declaration
  void hasConstantTimeComplexity();

  //method declaration
  void hasGivenOrLowerTimeComplexity(LongToDoubleFunction timeComplexityFunction);

  //method declaration
  void hasLinearOrLowerTimeComplexity();

  //method declaration
  void hasQuadraticOrLowerTimeComplexity();
}
