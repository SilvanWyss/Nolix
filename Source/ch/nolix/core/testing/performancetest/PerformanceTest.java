//package declaration
package ch.nolix.core.testing.performancetest;

//Java imports
import java.util.function.IntFunction;

//own imports
import ch.nolix.core.errorcontrol.perfomancevalidator.ObjectSupplierMediator;
import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IObjectSupplierMediator;
import ch.nolix.coreapi.testingapi.performancetestapi.IPerformanceTest;

//class
public abstract class PerformanceTest implements IPerformanceTest {

  //method
  @Override
  public final <O> IObjectSupplierMediator<O> expectOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
