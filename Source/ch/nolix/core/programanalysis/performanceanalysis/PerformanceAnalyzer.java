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
    var maxRunCount = 5;

    while (stopWatch.getTotalRunningTimeInMilliseconds() < 5_000) {

      final var passed = //
      onObjectsFromObjectSupplierUpToMaxRunCountActionRunsWithGivenOrLowerTimeComplexity(
        objectSupplier,
        maxRunCount,
        action,
        timeComplexityFunction);

      if (passed) {

        if (stopWatch.getTotalRunningTimeInMilliseconds() > 500 || maxRunCount > 244_140_625 /* 5^12 */) {
          return true;
        }

        maxRunCount *= 5;
      }
    }

    return false;
  }

  //method
  private <O> boolean onObjectsFromObjectSupplierUpToMaxRunCountActionRunsWithGivenOrLowerTimeComplexity(
    final IntFunction<O> objectSupplier,
    final int maxRunCount,
    final Consumer<O> action,
    final LongToDoubleFunction timeComplexityFunction) {

    var latestTimeComplexityInvariant = 0.0;

    for (var runCount = 5; runCount <= maxRunCount; runCount *= 5) {

      final var optionalTimeComplexityInvariant = //
      getOpionalTimeComplexityInvariantOfActionOnObjectFromObjectSupplierByRunCountAndTimeComplexityFunction(
        objectSupplier,
        runCount,
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
      final double timeComplexityInvariant = timeComplexity / runtimeInMilliseconds;

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
