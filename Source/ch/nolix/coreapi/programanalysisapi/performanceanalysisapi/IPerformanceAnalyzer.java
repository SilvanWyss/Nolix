//package declaration
package ch.nolix.coreapi.programanalysisapi.performanceanalysisapi;

//Java imports
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

//interface
public interface IPerformanceAnalyzer {

  //method declaration
  <O> boolean onObjectsFromObjectSupplierActionHasGivenOrLowerTimeComplexity(
    IntFunction<O> objectSupplier,
    Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction);
}
