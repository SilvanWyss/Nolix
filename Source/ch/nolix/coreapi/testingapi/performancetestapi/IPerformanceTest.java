//package declaration
package ch.nolix.coreapi.testingapi.performancetestapi;

//Java imports
import java.util.function.IntConsumer;

//interface
public interface IPerformanceTest {

  //method declaration
  IActionMediator expect(IntConsumer action);
}
