/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.math.bigdecimalmath;

import java.math.BigDecimal;
import java.util.function.IntFunction;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.math.bigdecimalmath.IComplexNumber;

/**
 * @author Silvan Wyss
 */
public final class ExplicitComplexSequence extends AbstractSequence<IComplexNumber> {
  private final IntFunction<IComplexNumber> valueSupplier;

  private ExplicitComplexSequence(final IntFunction<IComplexNumber> valueSupplier) {
    Validator.assertThat(valueSupplier).thatIsNamed(LowerCaseVariableNameCatalog.VALUE_SUPPLIER).isNotNull();

    this.valueSupplier = valueSupplier;
  }

  public static ExplicitComplexSequence withValueSupplier(final IntFunction<IComplexNumber> valueSupplier) {
    return new ExplicitComplexSequence(valueSupplier);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected BigDecimal calculateSquaredMagnitudeForValue(final IComplexNumber value) {
    return value.getSquaredMagnitude();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected IComplexNumber calculateValue(final int index) {
    return valueSupplier.apply(index);
  }
}
