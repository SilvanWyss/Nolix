//package declaration
package ch.nolix.coreapi.testingapi.performancetestapi;

//Java imports
import java.util.function.IntConsumer;

//interface
public interface IPerformanceTest {

  //method declaration
  IActionExpector expect(IntConsumer action);
}
