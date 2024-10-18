package ch.nolix.core.testing.performancetest;

import java.util.function.IntFunction;

import ch.nolix.core.errorcontrol.perfomancevalidator.ObjectSupplierMediator;
import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IObjectSupplierMediator;
import ch.nolix.coreapi.testingapi.performancetestapi.IPerformanceTest;

public abstract class PerformanceTest implements IPerformanceTest {

  @Override
  public final <O> IObjectSupplierMediator<O> expectOnAnObjectFrom(final IntFunction<O> objectSupplier) {
    return ObjectSupplierMediator.forObjectSupplier(objectSupplier);
  }
}
