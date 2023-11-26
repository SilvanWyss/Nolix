//package declaration
package ch.nolix.tech.math.fractal;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;

//own imports
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.fractalapi.IFractal;
import ch.nolix.techapi.mathapi.fractalapi.IFractalHelper;

//class
public final class FractalHelper implements IFractalHelper {

  //method
  @Override
  public BigDecimal getHeightInPixelAsBigDecimalOf(final IFractal fractal) {
    return BigDecimal.valueOf(fractal.getHeightInPixel());
  }

  //method
  @Override
  public BigDecimal getHeightInUnitsOf(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getLength();
  }

  //method
  @Override
  public int getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
    final IFractal fractal,
    final IComplexNumber complexNumber,
    final BigDecimal limit) {

    final var sequence = fractal.createSequenceFor(complexNumber);

    return sequence.getIterationCountUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
      limit,
      fractal.getMaxIterationCount());
  }

  //method
  @Override
  public BigDecimal getMaxXOf(final IFractal fractal) {
    return fractal.getRealComponentInterval().getMax();
  }

  //method
  @Override
  public BigDecimal getMaxYOf(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getMax();
  }

  //method
  @Override
  public BigDecimal getMinXOf(final IFractal fractal) {
    return fractal.getRealComponentInterval().getMin();
  }

  //method
  @Override
  public BigDecimal getMinYOf(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getMin();
  }

  //method
  @Override
  public BigDecimal getPixelCountPerHorizontalUnitOf(final IFractal fractal) {
    return getWidthInPixelAsBigDecimalOf(fractal).divide(getWidthInUnitsOf(fractal));
  }

  //method
  @Override
  public BigDecimal getPixelCountPerVerticalUnitOf(final IFractal fractal) {
    return getHeightInPixelAsBigDecimalOf(fractal).divide(getHeightInUnitsOf(fractal));
  }

  //method
  @Override
  public BigDecimal getSquaredMinMagnitudeForDivergenceOf(final IFractal fractal) {
    return fractal.getMinMagnitudeForDivergence().pow(2);
  }

  //method
  @Override
  public BigDecimal getUnitsPerHorizontalPixelOf(final IFractal fractal) {
    return getWidthInUnitsOf(fractal).divide(getWidthInPixelAsBigDecimalOf(fractal), RoundingMode.HALF_UP);
  }

  //method
  @Override
  public BigDecimal getUnitsPerVerticalPixelOf(final IFractal fractal) {
    return getHeightInUnitsOf(fractal).divide(getHeightInPixelAsBigDecimalOf(fractal), RoundingMode.HALF_UP);
  }

  //method
  @Override
  public BigDecimal getUnitsForHorizontalPixelCount(final IFractal fractal, final double horizontalPixelCount) {
    return getUnitsPerHorizontalPixelOf(fractal).multiply(BigDecimal.valueOf(horizontalPixelCount));
  }

  //method
  @Override
  public BigDecimal getUnitsForVerticalPixelCount(final IFractal fractal, final double verticalPixelCount) {
    return getUnitsPerVerticalPixelOf(fractal).multiply(BigDecimal.valueOf(verticalPixelCount));
  }

  //method
  @Override
  public BigDecimal getWidthInPixelAsBigDecimalOf(final IFractal fractal) {
    return BigDecimal.valueOf(fractal.getWidthInPixel());
  }

  //method
  @Override
  public BigDecimal getWidthInUnitsOf(final IFractal fractal) {
    return fractal.getRealComponentInterval().getLength();
  }
}
