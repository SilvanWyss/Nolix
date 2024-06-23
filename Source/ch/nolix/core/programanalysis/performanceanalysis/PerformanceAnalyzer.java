//package declaration
package ch.nolix.core.programanalysis.performanceanalysis;

//Java imports
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

//own imports
import ch.nolix.core.programcontrol.stopwatch.RuntimeMeter;
import ch.nolix.core.programcontrol.stopwatch.StopWatch;
import ch.nolix.coreapi.programanalysisapi.performanceanalysisapi.IPerformanceAnalyzer;

//class
public final class PerformanceAnalyzer implements IPerformanceAnalyzer {

  //constant
  private static final RuntimeMeter RUNTIME_METER = new RuntimeMeter();

  //method
  @Override
  public <O> boolean onObjectsFromObjectSupplierActionHasGivenOrLowerTimeComplexity(
    final IntFunction<O> objectSupplier,
    final Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction) {
    return //
    onObjectsFromObjectSupplierActionRunsAtLeastOnceWithGivenOrLowerTimeComplexity(
      objectSupplier,
      action,
      timeComplexityFunction);
  }

  //method
  private <O> boolean onObjectsFromObjectSupplierActionRunsAtLeastOnceWithGivenOrLowerTimeComplexity(
    final IntFunction<O> objectSupplier,
    final Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction) {

    final var stopWatch = StopWatch.createStartedStopWatch();
    var maxRunCount = 3;

    while (stopWatch.getTotalRunningTimeInMilliseconds() < 5_000) {

      final var passed = //
      onObjectsFromObjectSupplierActionRunsWithGivenOrLowerTimeComplexityUpToMaxRunCount(
        objectSupplier,
        action,
        timeComplexityFunction,
        maxRunCount);

      if (passed) {

        if (stopWatch.getTotalRunningTimeInMilliseconds() > 500 || maxRunCount > 387_420_489 /* 3^18 */) {
          return true;
        }

        maxRunCount *= 3;
      }
    }

    return false;
  }

  //method
  private <O> boolean onObjectsFromObjectSupplierActionRunsWithGivenOrLowerTimeComplexityUpToMaxRunCount(
    final IntFunction<O> objectSupplier,
    final Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction,
    final int maxRunCount) {

    var latestTimeComplexityInvariant = 0.0;

    for (var runCount = 1; runCount <= maxRunCount; runCount *= 3) {

      final var optionalTimeComplexityInvariant = //
      getOpionalTimeComplexityInvariantOfActionOnObjectFromObjectSupplierByRunCountAndTimeComplexityFunction(
        objectSupplier,
        maxRunCount,
        action,
        timeComplexityFunction);

      if (optionalTimeComplexityInvariant.isPresent()) {

        final var timeComplexityInvariant = optionalTimeComplexityInvariant.get();
        final var goodWillTimeComplexityInvariant = 1.1 * timeComplexityInvariant;

        if (latestTimeComplexityInvariant > 0.0 && goodWillTimeComplexityInvariant < latestTimeComplexityInvariant) {
          return false;
        }

        latestTimeComplexityInvariant = (0.25 * latestTimeComplexityInvariant) + (0.75 * timeComplexityInvariant);
      }
    }

    return true;
  }

  //method
  private <O> Optional<Double> //
  getOpionalTimeComplexityInvariantOfActionOnObjectFromObjectSupplierByRunCountAndTimeComplexityFunction(
    final IntFunction<O> objectSupplier,
    final int runCount,
    final Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction) {

    final var runtimeInMilliseconds = //
    getRuntimeInMillisecondsOfActionOnObjectFromObjectSupplierByRunCount(objectSupplier, runCount, action);

    if (runtimeInMilliseconds > 10) {

      final var timeComplexity = timeComplexityFunction.applyAsDouble(runCount);
      final var timeComplexityInvariant = timeComplexity / runtimeInMilliseconds;

      return Optional.of(timeComplexityInvariant);
    }

    return Optional.empty();
  }

  //method
  private <O> int getRuntimeInMillisecondsOfActionOnObjectFromObjectSupplierByRunCount(
    final IntFunction<O> objectSupplier,
    final int runCount,
    final Consumer<O> action) {

    final var object = objectSupplier.apply(runCount);
    final Runnable actionOnObject = () -> action.accept(object);

    return getRuntimeOfActionInMilliseconds(actionOnObject);
  }

  //method
  private int getRuntimeOfActionInMilliseconds(final Runnable actionOnObject) {
    return (int) RUNTIME_METER.getRuntimeOfActionInMilliseconds(actionOnObject);
  }
}
