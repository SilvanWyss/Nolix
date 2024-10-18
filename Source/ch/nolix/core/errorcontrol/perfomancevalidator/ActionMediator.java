package ch.nolix.core.errorcontrol.perfomancevalidator;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

import ch.nolix.core.errorcontrol.exception.GeneralException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programanalysis.performanceanalysis.PerformanceAnalyzer;
import ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi.IActionMediator;
import ch.nolix.coreapi.mathapi.function.LongToDoubleFunctionCatalogue;
import ch.nolix.coreapi.programanalysisapi.performanceanalysisapi.IPerformanceAnalyzer;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class ActionMediator<O> implements IActionMediator {

  private static final IPerformanceAnalyzer PERFORMANCE_ANALYZER = new PerformanceAnalyzer();

  private final IntFunction<O> objectSupplier;

  private final Consumer<O> action;

  private ActionMediator(final IntFunction<O> objectSupplier, final Consumer<O> action) {

    GlobalValidator.assertThat(objectSupplier).thatIsNamed("object supplier").isNotNull();
    GlobalValidator.assertThat(action).thatIsNamed(LowerCaseVariableCatalogue.ACTION).isNotNull();

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
    hasGivenOrLowerTimeComplexity(LongToDoubleFunctionCatalogue.CONSTANT_FUNCTION);
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
    hasGivenOrLowerTimeComplexity(LongToDoubleFunctionCatalogue.LINEAR_FUNCTION);
  }

  @Override
  public void hasQuadraticOrLowerTimeComplexity() {
    hasGivenOrLowerTimeComplexity(LongToDoubleFunctionCatalogue.QUADRATIC_FUNCTION);
  }
}
