package ch.nolix.core.errorcontrol.perfomancevalidator;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

import ch.nolix.core.errorcontrol.generalexception.GeneralException;
import ch.nolix.core.errorcontrol.performanceanalysis.PerformanceAnalyzer;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.errorcontrolapi.performanceanalysisapi.IPerformanceAnalyzer;
import ch.nolix.coreapi.errorcontrolapi.performanceanalysisapi.TimeComplexityFunctionCatalog;
import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IActionMediator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class ActionMediator<O> implements IActionMediator {

  private static final IPerformanceAnalyzer PERFORMANCE_ANALYZER = new PerformanceAnalyzer();

  private final IntFunction<O> objectSupplier;

  private final Consumer<O> action;

  private ActionMediator(final IntFunction<O> objectSupplier, final Consumer<O> action) {

    Validator.assertThat(objectSupplier).thatIsNamed("object supplier").isNotNull();
    Validator.assertThat(action).thatIsNamed(LowerCaseVariableCatalog.ACTION).isNotNull();

    this.objectSupplier = objectSupplier;
    this.action = action;
  }

  public static <O2> IActionMediator forObjectSupplierAndAction(
    final IntFunction<O2> objectSupplier,
    final Consumer<O2> action) {
    return new ActionMediator<>(objectSupplier, action);
  }

  @Override
  public void hasConstantOrLowerTimeComplexity() {
    hasGivenOrLowerTimeComplexity(TimeComplexityFunctionCatalog.CONSTANT);
  }

  @Override
  public void hasGivenOrLowerTimeComplexity(final LongToDoubleFunction timeComplexityFunction) {

    final var passed = //
    PERFORMANCE_ANALYZER.onObjectsFromObjectSupplierActionHasGivenOrLowerTimeComplexity(
      objectSupplier,
      action,
      timeComplexityFunction);

    if (!passed) {
      throw //
      GeneralException.withErrorMessage(
        "The action of the current ActionMediator does not have the given or a lower time complexity.");
    }
  }

  @Override
  public void hasLinearOrLowerTimeComplexity() {
    hasGivenOrLowerTimeComplexity(TimeComplexityFunctionCatalog.LINEAR);
  }

  @Override
  public void hasQuadraticOrLowerTimeComplexity() {
    hasGivenOrLowerTimeComplexity(TimeComplexityFunctionCatalog.QUADRATIC);
  }
}
