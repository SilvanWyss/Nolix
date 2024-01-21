//package declaration
package ch.nolix.tech.math.bigdecimalmath;

//Java imports
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.math.main.GlobalCalculator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;

//class
/**
 * A {@link ComplexNumber} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2019-02-19
 */
public final class ComplexNumber implements IComplexNumber {

  //attribute
  private final BigDecimal realComponent;

  //attribute
  private final BigDecimal imaginaryComponent;

  //constructor
  public ComplexNumber(final BigDecimal realComponent, final BigDecimal imaginaryComponent) {

    GlobalValidator.assertThat(realComponent).thatIsNamed("real component").isNotNull();
    GlobalValidator.assertThat(imaginaryComponent).thatIsNamed("imaginary component").isNotNull();

    final var decimalPlaces = GlobalCalculator.getMax(realComponent.scale(), imaginaryComponent.scale(), 10);
    this.realComponent = realComponent.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.imaginaryComponent = imaginaryComponent.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  //constructor
  public ComplexNumber(
    final BigDecimal realComponent,
    final BigDecimal imaginaryComponent,
    final int decimalPlaces) {

    GlobalValidator.assertThat(realComponent).thatIsNamed("real component").isNotNull();
    GlobalValidator.assertThat(imaginaryComponent).thatIsNamed("imaginary component").isNotNull();
    GlobalValidator.assertThat(decimalPlaces).thatIsNamed("big decimal scale").isPositive();

    this.realComponent = realComponent.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.imaginaryComponent = imaginaryComponent.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  //constructor
  public ComplexNumber(final double realComponent, final double imaginaryComponent) {

    final var realComponentBigDecimal = BigDecimal.valueOf(realComponent);
    final var imaginaryComponentBigDecimal = BigDecimal.valueOf(imaginaryComponent);

    final var decimalPlaces = GlobalCalculator.getMax(realComponentBigDecimal.scale(),
      imaginaryComponentBigDecimal.scale(), 10);

    this.realComponent = realComponentBigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP);
    this.imaginaryComponent = imaginaryComponentBigDecimal.setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  //constructor
  public ComplexNumber(final double realComponent, final double imaginaryComponent, final int decimalPlaces) {

    GlobalValidator.assertThat(decimalPlaces).thatIsNamed("big decimal scale").isPositive();

    this.realComponent = BigDecimal.valueOf(realComponent).setScale(decimalPlaces, RoundingMode.HALF_UP);

    this.imaginaryComponent = BigDecimal.valueOf(imaginaryComponent).setScale(decimalPlaces, RoundingMode.HALF_UP);
  }

  //method
  @Override
  public int compareTo(final IComplexNumber complexNumber) {

    GlobalValidator.assertThat(complexNumber).thatIsNamed("complex number").isNotNull();

    return getMagnitude().compareTo(complexNumber.getMagnitude());
  }

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  @Override
  public boolean equals(final Object object) {

    if (!(object instanceof ComplexNumber)) {
      return false;
    }

    final var complexNumber = (ComplexNumber) object;

    return realComponent.equals(complexNumber.realComponent)
    && imaginaryComponent.equals(complexNumber.imaginaryComponent);
  }

  //method
  @Override
  public ComplexNumber getConjugate() {
    return new ComplexNumber(realComponent, imaginaryComponent.negate(), getDecimalPlaces());
  }

  //method
  @Override
  public int getDecimalPlaces() {
    return realComponent.scale();
  }

  //method
  @Override
  public BigDecimal getImaginaryComponent() {
    return imaginaryComponent;
  }

  //method
  @Override
  public BigDecimal getMagnitude() {
    return realComponent
      .pow(2)
      .add(imaginaryComponent.pow(2))
      .sqrt(MathContext.DECIMAL128)
      .setScale(getScale(), RoundingMode.HALF_UP);
  }

  //method
  @Override
  public ComplexNumber getPower(final int exponent) {

    GlobalValidator.assertThat(exponent).thatIsNamed(LowerCaseVariableCatalogue.EXPONENT).isPositive();

    var complexNumber = this;
    for (var i = 2; i <= exponent; i++) {
      complexNumber = complexNumber.getProduct(this);
    }

    return complexNumber;
  }

  //method
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

  //method
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

  //method
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

  //method
  @Override
  public ComplexNumber getProduct(final BigDecimal number) {
    return new ComplexNumber(realComponent.multiply(number), imaginaryComponent.multiply(number), getDecimalPlaces());
  }

  //method
  @Override
  public IComplexNumber getProduct(final double number) {
    return getSum(BigDecimal.valueOf(number));
  }

  //method
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

  //method
  @Override
  public BigDecimal getRealComponent() {
    return realComponent;
  }

  //method
  @Override
  public int getScale() {
    return realComponent.scale();
  }

  //method
  @Override
  public BigDecimal getSquaredMagnitude() {
    return realComponent
      .multiply(realComponent)
      .add(imaginaryComponent.multiply(imaginaryComponent))
      .setScale(getScale(), RoundingMode.HALF_UP);
  }

  //method
  @Override
  public IComplexNumber getSum(final BigDecimal number) {
    return new ComplexNumber(realComponent.add(number), imaginaryComponent, getDecimalPlaces());
  }

  //method
  @Override
  public ComplexNumber getSum(final IComplexNumber complexNumber) {
    return new ComplexNumber(
      realComponent.add(complexNumber.getRealComponent()),
      imaginaryComponent.add(complexNumber.getImaginaryComponent()),
      getDecimalPlaces());
  }

  //method
  @Override
  public IComplexNumber getSum(final double number) {
    return getSum(BigDecimal.valueOf(number));
  }

  //method
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  //method
  @Override
  public IComplexNumber inDecimalPlaces(final int decimalPlaces) {
    return new ComplexNumber(realComponent, imaginaryComponent, decimalPlaces);
  }

  //method
  @Override
  public boolean isPureImaginary() {
    return (realComponent.compareTo(BigDecimal.ZERO) == 0);
  }

  //method
  @Override
  public boolean isPureReal() {
    return (imaginaryComponent.compareTo(BigDecimal.ZERO) == 0);
  }

  //method
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
}
