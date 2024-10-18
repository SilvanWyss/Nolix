package ch.nolix.coreapi.testingapi.performancetestapi;

import java.util.function.IntFunction;

import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IObjectSupplierMediator;

public interface IPerformanceTest {

  <O> IObjectSupplierMediator<O> expectOnAnObjectFrom(IntFunction<O> objectSupplier);
}
