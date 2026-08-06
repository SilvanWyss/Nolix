/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.perfomance;

import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongToDoubleFunction;

import ch.nolix.base.errorcontrol.generalexception.GeneralException;
import ch.nolix.base.validation.performanceanalysis.PerformanceAnalyzer;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.validation.performance.IActionMediator;
import ch.nolix.baseapi.validation.performanceanalysis.TimeComplexityFunctionCatalog;

/**
 * @author Silvan Wyss
 * @param <O> the type of the {@link Object}s a {@link ActionMediator} is for.
 */
public final class ActionMediator<O> implements IActionMediator {
  private static final PerformanceAnalyzer PERFORMANCE_ANALYZER = new PerformanceAnalyzer();

  private final IntFunction<O> objectSupplier;

  private final Consumer<O> action;

  private ActionMediator(final IntFunction<O> objectSupplier, final Consumer<O> action) {
    Validator.assertThat(objectSupplier).thatIsNamed("object supplier").isNotNull();
    Validator.assertThat(action).thatIsNamed(LowerCaseVariableNameCatalog.ACTION).isNotNull();

    this.objectSupplier = objectSupplier;
    this.action = action;
  }

  public static <O2> IActionMediator forObjectSupplierAndAction(
    final IntFunction<O2> objectSupplier,
    final Consumer<O2> action) {
    return new ActionMediator<>(objectSupplier, action);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hasConstantOrLowerTimeComplexity() {
    hasGivenOrLowerTimeComplexity(TimeComplexityFunctionCatalog.CONSTANT);
  }

  /**
   * {@inheritDoc}
   */
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

  /**
   * {@inheritDoc}
   */
  @Override
  public void hasLinearOrLowerTimeComplexity() {
    hasGivenOrLowerTimeComplexity(TimeComplexityFunctionCatalog.LINEAR);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void hasQuadraticOrLowerTimeComplexity() {
    hasGivenOrLowerTimeComplexity(TimeComplexityFunctionCatalog.QUADRATIC);
  }
}
