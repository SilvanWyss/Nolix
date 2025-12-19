package ch.nolix.coreapi.errorcontrol.performanceanalysis;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

/**
 * @author Silvan Wyss
 */
public interface IPerformanceAnalyzer {
  <O> boolean onObjectsFromObjectSupplierActionHasGivenOrLowerTimeComplexity(
    IntFunction<O> objectSupplier,
    Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction);
}
