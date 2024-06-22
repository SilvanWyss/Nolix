//package declaration
package ch.nolix.coreapi.testingapi.performancetestapi;

//Java imports
import java.util.function.IntFunction;

//interface
public interface IPerformanceTest {

  //method declaration
  <O> IObjectSupplierMediator<O> onAnObjectFrom(IntFunction<O> objectSupplier);
}
