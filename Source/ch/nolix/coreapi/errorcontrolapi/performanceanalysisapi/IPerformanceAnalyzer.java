package ch.nolix.coreapi.errorcontrolapi.performanceanalysisapi;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

public interface IPerformanceAnalyzer {

  <O> boolean onObjectsFromObjectSupplierActionHasGivenOrLowerTimeComplexity(
    IntFunction<O> objectSupplier,
    Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction);
}