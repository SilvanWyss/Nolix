package ch.nolix.tech.math.bigdecimalmath;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.math.main.Calculator;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;

/**
 * A {@link ComplexNumber} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2019-02-19
 */
public final class ComplexNumber implements IComplexNumber {

  private final BigDecimal realComponent;

  private final BigDecimal imaginaryComponent;

  public ComplexNumber(final BigDecimal realComponent, final BigDecimal imaginaryComponent) {

    Validator.assertThat(realComponent).thatIsNamed("real component").isNotNull();
    Validator.assertThat(imaginaryComponent).thatIsNamed("imaginary component").isNotNull();

    final var decimalPlaces = Calculator.getMax(realComponent.scale(), imaginaryComponent.scale(), 10);
    this.realComponent = realComponent.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.imaginaryComponent = imaginaryComponent.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  public ComplexNumber(
    final BigDecimal realComponent,
    final BigDecimal imaginaryComponent,
    final int decimalPlaces) {

    Validator.assertThat(realComponent).thatIsNamed("real component").isNotNull();
    Validator.assertThat(imaginaryComponent).thatIsNamed("imaginary component").isNotNull();
    Validator.assertThat(decimalPlaces).thatIsNamed("big decimal scale").isPositive();

    this.realComponent = realComponent.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.imaginaryComponent = imaginaryComponent.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  public ComplexNumber(final double realComponent, final double imaginaryComponent) {

    final var realComponentBigDecimal = BigDecimal.valueOf(realComponent);
    final var imaginaryComponentBigDecimal = BigDecimal.valueOf(imaginaryComponent);

    final var decimalPlaces = Calculator.getMax(realComponentBigDecimal.scale(),
      imaginaryComponentBigDecimal.scale(), 10);

    this.realComponent = realComponentBigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.imaginaryComponent = imaginaryComponentBigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  public ComplexNumber(final double realComponent, final double imaginaryComponent, final int decimalPlaces) {

    Validator.assertThat(decimalPlaces).thatIsNamed("big decimal scale").isPositive();

    this.realComponent = BigDecimal.valueOf(realComponent).setScale(decimalPlaces, RoundingMode.HALF_UP);

    this.imaginaryComponent = BigDecimal.valueOf(imaginaryComponent).setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  @Override
  public int compareTo(final IComplexNumber complexNumber) {

    Validator.assertThat(complexNumber).thatIsNamed("complex number").isNotNull();

    return getMagnitude().compareTo(complexNumber.getMagnitude());
  }

  //For a better performance, this implementation does not use all available comfort methods.
  @Override
  public boolean equals(final Object object) {

    if (object instanceof final IComplexNumber complexNumber) {
      return equalsComplexNumber(complexNumber);
    }

    return false;
  }

  @Override
  public ComplexNumber getConjugate() {
    return new ComplexNumber(realComponent, imaginaryComponent.negate(), getDecimalPlaces());
  }

  @Override
  public int getDecimalPlaces() {
    return realComponent.scale();
  }

  @Override
  public BigDecimal getImaginaryComponent() {
    return imaginaryComponent;
  }

  @Override
  public BigDecimal getMagnitude() {
    return realComponent
      .pow(2)
      .add(imaginaryComponent.pow(2))
      .sqrt(MathContext.DECIMAL128)
      .setScale(getScale(), RoundingMode.HALF_UP);
  }

  @Override
  public ComplexNumber getPower(final int exponent) {

    Validator.assertThat(exponent).thatIsNamed(LowerCaseVariableCatalog.EXPONENT).isPositive();

    var complexNumber = this;
    for (var i = 2; i <= exponent; i++) {
      complexNumber = complexNumber.getProduct(this);
    }

    return complexNumber;
  }

  @Override
  public ComplexNumber getPower2() {
    return new ComplexNumber(
      realComponent
        .multiply(realComponent)
        .subtract(imaginaryComponent.multiply(imaginaryComponent)),
      BigDecimal.valueOf(2.0)
        .multiply(realComponent)
        .multiply(imaginaryComponent),
      getDecimalPlaces());
  }

  @Override
  public ComplexNumber getPower3() {
    return new ComplexNumber(
      realComponent
        .pow(3)
        .subtract(BigDecimal.valueOf(3.0).multiply(realComponent).multiply(imaginaryComponent.pow(2))),
      BigDecimal.valueOf(3.0)
        .multiply(realComponent)
        .pow(2)
        .multiply(imaginaryComponent)
        .subtract(imaginaryComponent.pow(3)),
      getDecimalPlaces());
  }

  @Override
  public ComplexNumber getPower4() {
    return new ComplexNumber(
      realComponent
        .pow(4)
        .subtract(BigDecimal.valueOf(6.0).multiply(realComponent.pow(2)).multiply(imaginaryComponent.pow(2)))
        .add(imaginaryComponent.pow(4)),
      BigDecimal.valueOf(4.0)
        .multiply(realComponent.pow(3))
        .multiply(imaginaryComponent)
        .subtract(BigDecimal.valueOf(4.0).multiply(realComponent).multiply(imaginaryComponent.pow(3))),
      getDecimalPlaces());
  }

  @Override
  public ComplexNumber getProduct(final BigDecimal number) {
    return new ComplexNumber(realComponent.multiply(number), imaginaryComponent.multiply(number), getDecimalPlaces());
  }

  @Override
  public IComplexNumber getProduct(final double number) {
    return getSum(BigDecimal.valueOf(number));
  }

  @Override
  public ComplexNumber getProduct(final IComplexNumber complexNumber) {
    return new ComplexNumber(
      realComponent
        .multiply(complexNumber.getRealComponent())
        .subtract(imaginaryComponent.multiply(complexNumber.getImaginaryComponent()))
        .setScale(getScale(), RoundingMode.HALF_UP),
      realComponent
        .multiply(complexNumber.getImaginaryComponent())
        .add(imaginaryComponent.multiply(complexNumber.getRealComponent()))
        .setScale(getScale(), RoundingMode.HALF_UP),
      getDecimalPlaces());
  }

  @Override
  public BigDecimal getRealComponent() {
    return realComponent;
  }

  @Override
  public int getScale() {
    return realComponent.scale();
  }

  @Override
  public BigDecimal getSquaredMagnitude() {
    return realComponent
      .multiply(realComponent)
      .add(imaginaryComponent.multiply(imaginaryComponent))
      .setScale(getScale(), RoundingMode.HALF_UP);
  }

  @Override
  public IComplexNumber getSum(final BigDecimal number) {
    return new ComplexNumber(realComponent.add(number), imaginaryComponent, getDecimalPlaces());
  }

  @Override
  public ComplexNumber getSum(final IComplexNumber complexNumber) {
    return new ComplexNumber(
      realComponent.add(complexNumber.getRealComponent()),
      imaginaryComponent.add(complexNumber.getImaginaryComponent()),
      getDecimalPlaces());
  }

  @Override
  public IComplexNumber getSum(final double number) {
    return getSum(BigDecimal.valueOf(number));
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  @Override
  public IComplexNumber inDecimalPlaces(final int decimalPlaces) {
    return new ComplexNumber(realComponent, imaginaryComponent, decimalPlaces);
  }

  @Override
  public boolean isPureImaginary() {
    return (realComponent.compareTo(BigDecimal.ZERO) == 0);
  }

  @Override
  public boolean isPureReal() {
    return (imaginaryComponent.compareTo(BigDecimal.ZERO) == 0);
  }

  @Override
  public String toString() {

    if (isPureReal()) {
      return realComponent.toString();
    }

    if (isPureImaginary()) {
      return imaginaryComponent + "i";
    }

    return (realComponent + " + " + imaginaryComponent + "i");
  }

  private boolean equalsComplexNumber(final IComplexNumber complexNumber) {
    return //
    realComponent.equals(complexNumber.getRealComponent())
    && imaginaryComponent.equals(complexNumber.getImaginaryComponent());
  }
}
