//package declaration
package ch.nolix.coreapi.testingapi.performancetestapi;

//Java imports
import java.util.function.IntToDoubleFunction;

//interface
public interface IActionExpector {

  //method declaration
  void hasLinearOrLowerTimeComplexity();

  //method declaration
  void hasGivenOrLowerTimeComplexity(IntToDoubleFunction timeComplexityFunction);
}
