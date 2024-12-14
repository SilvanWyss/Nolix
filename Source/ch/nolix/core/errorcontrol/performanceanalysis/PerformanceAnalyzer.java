package ch.nolix.core.errorcontrol.performanceanalysis;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

import ch.nolix.core.math.main.GlobalNumberComparator;
import ch.nolix.core.programcontrol.stopwatch.RuntimeMeter;
import ch.nolix.core.programcontrol.stopwatch.StopWatch;
import ch.nolix.coreapi.errorcontrolapi.performanceanalysisapi.IPerformanceAnalyzer;

public final class PerformanceAnalyzer implements IPerformanceAnalyzer {

  private static final RuntimeMeter RUNTIME_METER = new RuntimeMeter();

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

        if (stopWatch.getTotalRunningTimeInMilliseconds() > 500 || maxRunCount >= 9_765_625 /* 5^10 */) {
          return true;
        }

        maxRunCount *= 5;
      }
    }

    return false;
  }

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

        if (GlobalNumberComparator.isZero(latestTimeComplexityInvariant)) {
          latestTimeComplexityInvariant = timeComplexityInvariant;
        } else {
          latestTimeComplexityInvariant = (0.2 * latestTimeComplexityInvariant) + (0.8 * timeComplexityInvariant);
        }
      }
    }

    return true;
  }

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

  private <O> int getRuntimeInMillisecondsOfActionOnObjectFromObjectSupplierByRunCount(
    final IntFunction<O> objectSupplier,
    final int runCount,
    final Consumer<O> action) {

    final var object = objectSupplier.apply(runCount);
    final Runnable actionOnObject = () -> action.accept(object);

    return getRuntimeOfActionInMilliseconds(actionOnObject);
  }

  private int getRuntimeOfActionInMilliseconds(final Runnable actionOnObject) {
    return (int) RUNTIME_METER.getRuntimeOfActionInMilliseconds(actionOnObject);
  }
}
