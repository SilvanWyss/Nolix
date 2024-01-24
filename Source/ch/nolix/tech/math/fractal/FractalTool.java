//package declaration
package ch.nolix.tech.math.fractal;

//Java imports
import java.math.BigDecimal;
import java.math.RoundingMode;

//own imports
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;
import ch.nolix.techapi.mathapi.fractalapi.IFractal;
import ch.nolix.techapi.mathapi.fractalapi.IFractalTool;

//class
public final class FractalTool implements IFractalTool {

  //method
  @Override
  public BigDecimal getHeightInPixelAsBigDecimal(final IFractal fractal) {
    return BigDecimal.valueOf(fractal.getHeightInPixel()).setScale(fractal.getDecimalPlaces());
  }

  //method
  @Override
  public BigDecimal getHeightInUnits(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getLength();
  }

  //method
  @Override
  public int getIterationCountForStartNumberWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(
    final IFractal fractal,
    final IComplexNumber startNumber,
    final BigDecimal limit) {

    final var sequence = fractal.createSequenceFor(startNumber);

    return sequence.getIterationCountWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(
      limit,
      fractal.getMaxIterationCount());
  }

  //method
  @Override
  public BigDecimal getMaxX(final IFractal fractal) {
    return fractal.getRealComponentInterval().getMax();
  }

  //method
  @Override
  public BigDecimal getMaxY(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getMax();
  }

  //method
  @Override
  public BigDecimal getMinX(final IFractal fractal) {
    return fractal.getRealComponentInterval().getMin();
  }

  //method
  @Override
  public BigDecimal getMinY(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getMin();
  }

  //method
  @Override
  public BigDecimal getPixelCountPerHorizontalUnit(final IFractal fractal) {
    return getWidthInPixelAsBigDecimal(fractal).divide(getWidthInUnits(fractal), RoundingMode.HALF_UP);
  }

  //method
  @Override
  public BigDecimal getPixelCountPerVerticalUnit(final IFractal fractal) {
    return getHeightInPixelAsBigDecimal(fractal).divide(getHeightInUnits(fractal), RoundingMode.HALF_UP);
  }

  //method
  @Override
  public BigDecimal getSquaredMinMagnitudeForDivergence(final IFractal fractal) {
    return fractal.getMinMagnitudeForDivergence().pow(2).setScale(fractal.getDecimalPlaces());
  }

  //method
  @Override
  public BigDecimal getUnitsPerHorizontalPixel(final IFractal fractal) {
    return getWidthInUnits(fractal).divide(getWidthInPixelAsBigDecimal(fractal), RoundingMode.HALF_UP);
  }

  //method
  @Override
  public BigDecimal getUnitsPerVerticalPixel(final IFractal fractal) {
    return getHeightInUnits(fractal).divide(getHeightInPixelAsBigDecimal(fractal), RoundingMode.HALF_UP);
  }

  //method
  @Override
  public BigDecimal getUnitsForHorizontalPixelCount(final IFractal fractal, final double horizontalPixelCount) {
    return getUnitsPerHorizontalPixel(fractal).multiply(BigDecimal.valueOf(horizontalPixelCount));
  }

  //method
  @Override
  public BigDecimal getUnitsForVerticalPixelCount(final IFractal fractal, final double verticalPixelCount) {
    return getUnitsPerVerticalPixel(fractal).multiply(BigDecimal.valueOf(verticalPixelCount));
  }

  //method
  @Override
  public BigDecimal getWidthInPixelAsBigDecimal(final IFractal fractal) {
    return BigDecimal.valueOf(fractal.getWidthInPixel()).setScale(fractal.getDecimalPlaces());
  }

  //method
  @Override
  public BigDecimal getWidthInUnits(final IFractal fractal) {
    return fractal.getRealComponentInterval().getLength();
  }
}