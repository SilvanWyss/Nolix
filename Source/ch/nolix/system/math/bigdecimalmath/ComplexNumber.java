/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.math.bigdecimalmath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import ch.nolix.base.math.main.Calculator;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.math.bigdecimalmath.IComplexNumber;

/**
 * A {@link ComplexNumber} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class ComplexNumber implements IComplexNumber {
  private final BigDecimal realComponent;

  private final BigDecimal imaginaryComponent;

  private ComplexNumber(final BigDecimal realComponent, final BigDecimal imaginaryComponent) {
    Validator.assertThat(realComponent).thatIsNamed("real component").isNotNull();
    Validator.assertThat(imaginaryComponent).thatIsNamed("imaginary component").isNotNull();

    final var decimalPlaceCount = Calculator.getMax(realComponent.scale(), imaginaryComponent.scale());

    this.realComponent = realComponent.setScale(decimalPlaceCount, RoundingMode.HALF_UP);
    this.imaginaryComponent = imaginaryComponent.setScale(decimalPlaceCount, RoundingMode.HALF_UP);
  }

  private ComplexNumber(
    final BigDecimal realComponent,
    final BigDecimal imaginaryComponent,
    final int decimalPlaceCount) {
    Validator.assertThat(realComponent).thatIsNamed("real component").isNotNull();
    Validator.assertThat(imaginaryComponent).thatIsNamed("imaginary component").isNotNull();
    Validator.assertThat(decimalPlaceCount).thatIsNamed("decimal place count").isPositive();

    this.realComponent = realComponent.setScale(decimalPlaceCount, RoundingMode.HALF_UP);
    this.imaginaryComponent = imaginaryComponent.setScale(decimalPlaceCount, RoundingMode.HALF_UP);
  }

  private ComplexNumber(final double realComponent, final double imaginaryComponent) {
    this(BigDecimal.valueOf(realComponent), BigDecimal.valueOf(imaginaryComponent), 20);
  }

  private ComplexNumber(final double realComponent, final double imaginaryComponent, final int decimalPlaces) {
    this(BigDecimal.valueOf(realComponent), BigDecimal.valueOf(imaginaryComponent), decimalPlaces);
  }

  public static ComplexNumber withRealComponentAndImaginaryComponent(
    final BigDecimal realComponent,
    final BigDecimal imaginaryComponent) {
    return new ComplexNumber(realComponent, imaginaryComponent);
  }

  public static ComplexNumber withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
    final BigDecimal realComponent,
    final BigDecimal imaginaryComponent,
    final int decimalPlaceCount) {
    return new ComplexNumber(realComponent, imaginaryComponent, decimalPlaceCount);
  }

  public static ComplexNumber withRealComponentAndImaginaryComponent(
    final double realComponent,
    final double imaginaryComponent) {
    return new ComplexNumber(realComponent, imaginaryComponent);
  }

  public static ComplexNumber withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
    final double realComponent,
    final double imaginaryComponent,
    final int decimalPlaceCount) {
    return new ComplexNumber(realComponent, imaginaryComponent, decimalPlaceCount);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int compareTo(final IComplexNumber complexNumber) {
    return getMagnitude().compareTo(complexNumber.getMagnitude());
  }

  @Override
  public boolean equals(final Object object) {
    return //
    object instanceof final IComplexNumber complexNumber
    && realComponent.equals(complexNumber.getRealComponent())
    && imaginaryComponent.equals(complexNumber.getImaginaryComponent());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComplexNumber getConjugate() {
    return //
    withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
      realComponent,
      imaginaryComponent.negate(),
      getDecimalPlaceCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getDecimalPlaceCount() {
    return realComponent.scale();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getImaginaryComponent() {
    return imaginaryComponent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getMagnitude() {
    return //
    realComponent
      .pow(2)
      .add(imaginaryComponent.pow(2))
      .sqrt(MathContext.DECIMAL128)
      .setScale(getDecimalPlaceCount(), RoundingMode.HALF_UP);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComplexNumber getPower(final int exponent) {
    Validator.assertThat(exponent).thatIsNamed(LowerCaseVariableNameCatalog.EXPONENT).isPositive();

    var complexNumber = this;

    for (var i = 2; i <= exponent; i++) {
      complexNumber = complexNumber.getProduct(this);
    }

    return complexNumber;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComplexNumber getPower2() {
    return //
    withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
      realComponent
        .multiply(realComponent)
        .subtract(imaginaryComponent.multiply(imaginaryComponent)),
      BigDecimal.valueOf(2.0)
        .multiply(realComponent)
        .multiply(imaginaryComponent),
      getDecimalPlaceCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComplexNumber getPower3() {
    return //
    withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
      realComponent
        .pow(3)
        .subtract(BigDecimal.valueOf(3.0).multiply(realComponent).multiply(imaginaryComponent.pow(2))),
      BigDecimal.valueOf(3.0)
        .multiply(realComponent)
        .pow(2)
        .multiply(imaginaryComponent)
        .subtract(imaginaryComponent.pow(3)),
      getDecimalPlaceCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComplexNumber getPower4() {
    return //
    withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
      realComponent
        .pow(4)
        .subtract(BigDecimal.valueOf(6.0).multiply(realComponent.pow(2)).multiply(imaginaryComponent.pow(2)))
        .add(imaginaryComponent.pow(4)),
      BigDecimal.valueOf(4.0)
        .multiply(realComponent.pow(3))
        .multiply(imaginaryComponent)
        .subtract(BigDecimal.valueOf(4.0).multiply(realComponent).multiply(imaginaryComponent.pow(3))),
      getDecimalPlaceCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComplexNumber getProduct(final BigDecimal number) {
    return //
    withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
      realComponent.multiply(number),
      imaginaryComponent.multiply(number),
      getDecimalPlaceCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IComplexNumber getProduct(final double number) {
    return getSum(BigDecimal.valueOf(number));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComplexNumber getProduct(final IComplexNumber complexNumber) {
    return //
    withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
      realComponent
        .multiply(complexNumber.getRealComponent())
        .subtract(imaginaryComponent.multiply(complexNumber.getImaginaryComponent()))
        .setScale(getDecimalPlaceCount(), RoundingMode.HALF_UP),
      realComponent
        .multiply(complexNumber.getImaginaryComponent())
        .add(imaginaryComponent.multiply(complexNumber.getRealComponent()))
        .setScale(getDecimalPlaceCount(), RoundingMode.HALF_UP),
      getDecimalPlaceCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getRealComponent() {
    return realComponent;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal getSquaredMagnitude() {
    return //
    realComponent
      .multiply(realComponent)
      .add(imaginaryComponent.multiply(imaginaryComponent))
      .setScale(getDecimalPlaceCount(), RoundingMode.HALF_UP);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IComplexNumber getSum(final BigDecimal number) {
    return //
    withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
      realComponent.add(number),
      imaginaryComponent,
      getDecimalPlaceCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ComplexNumber getSum(final IComplexNumber complexNumber) {
    return //
    withRealComponentAndImaginaryComponentAndDecimalPlaceCount(
      realComponent.add(complexNumber.getRealComponent()),
      imaginaryComponent.add(complexNumber.getImaginaryComponent()),
      getDecimalPlaceCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IComplexNumber getSum(final double number) {
    return getSum(BigDecimal.valueOf(number));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isPureImaginary() {
    return realComponent.compareTo(BigDecimal.ZERO) == 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isPureReal() {
    return imaginaryComponent.compareTo(BigDecimal.ZERO) == 0;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    if (isPureReal()) {
      return realComponent.toString();
    }

    if (isPureImaginary()) {
      return imaginaryComponent + "i";
    }

    return realComponent + " + " + imaginaryComponent + "i";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IComplexNumber withDecimalPlaceCount(final int decimalPlaces) {
    return withRealComponentAndImaginaryComponentAndDecimalPlaceCount(realComponent, imaginaryComponent, decimalPlaces);
  }
}
