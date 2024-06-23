//package declaration
package ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi;

import java.util.function.LongToDoubleFunction;

//interface
public interface IActionMediator {

  //method declaration
  void hasConstantTimeComplexity();

  //method declaration
  void hasGivenOrLowerTimeComplexity(LongToDoubleFunction timeComplexityFunction);

  //method declaration
  void hasLinearOrLowerTimeComplexity();
}
