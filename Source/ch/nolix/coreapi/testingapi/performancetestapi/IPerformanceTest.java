//package declaration
package ch.nolix.coreapi.testingapi.performancetestapi;

//Java imports
import java.util.function.IntFunction;

//own imports
import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IObjectSupplierMediator;

//interface
public interface IPerformanceTest {

  //method declaration
  <O> IObjectSupplierMediator<O> expectOnAnObjectFrom(IntFunction<O> objectSupplier);
}
