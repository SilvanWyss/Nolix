package ch.nolix.tech.math.fractal;

import java.math.BigDecimal;
import java.math.RoundingMode;

import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;
import ch.nolix.techapi.math.fractal.IFractal;
import ch.nolix.techapi.math.fractal.IFractalTool;

public final class FractalTool implements IFractalTool {

  @Override
  public BigDecimal getHeightInPixelAsBigDecimal(final IFractal fractal) {
    return BigDecimal.valueOf(fractal.getHeightInPixel()).setScale(fractal.getDecimalPlaces());
  }

  @Override
  public BigDecimal getHeightInUnits(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getLength();
  }

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

  @Override
  public BigDecimal getMaxX(final IFractal fractal) {
    return fractal.getRealComponentInterval().getMax();
  }

  @Override
  public BigDecimal getMaxY(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getMax();
  }

  @Override
  public BigDecimal getMinX(final IFractal fractal) {
    return fractal.getRealComponentInterval().getMin();
  }

  @Override
  public BigDecimal getMinY(final IFractal fractal) {
    return fractal.getImaginaryComponentInterval().getMin();
  }

  @Override
  public BigDecimal getPixelCountPerHorizontalUnit(final IFractal fractal) {
    return getWidthInPixelAsBigDecimal(fractal).divide(getWidthInUnits(fractal), RoundingMode.HALF_UP);
  }

  @Override
  public BigDecimal getPixelCountPerVerticalUnit(final IFractal fractal) {
    return getHeightInPixelAsBigDecimal(fractal).divide(getHeightInUnits(fractal), RoundingMode.HALF_UP);
  }

  @Override
  public BigDecimal getSquaredMinMagnitudeForDivergence(final IFractal fractal) {
    return fractal.getMinMagnitudeForDivergence().pow(2).setScale(fractal.getDecimalPlaces());
  }

  @Override
  public BigDecimal getUnitsPerHorizontalPixel(final IFractal fractal) {
    return getWidthInUnits(fractal).divide(getWidthInPixelAsBigDecimal(fractal), RoundingMode.HALF_UP);
  }

  @Override
  public BigDecimal getUnitsPerVerticalPixel(final IFractal fractal) {
    return getHeightInUnits(fractal).divide(getHeightInPixelAsBigDecimal(fractal), RoundingMode.HALF_UP);
  }

  @Override
  public BigDecimal getUnitsForHorizontalPixelCount(final IFractal fractal, final double horizontalPixelCount) {
    return getUnitsPerHorizontalPixel(fractal).multiply(BigDecimal.valueOf(horizontalPixelCount));
  }

  @Override
  public BigDecimal getUnitsForVerticalPixelCount(final IFractal fractal, final double verticalPixelCount) {
    return getUnitsPerVerticalPixel(fractal).multiply(BigDecimal.valueOf(verticalPixelCount));
  }

  @Override
  public BigDecimal getWidthInPixelAsBigDecimal(final IFractal fractal) {
    return BigDecimal.valueOf(fractal.getWidthInPixel()).setScale(fractal.getDecimalPlaces());
  }

  @Override
  public BigDecimal getWidthInUnits(final IFractal fractal) {
    return fractal.getRealComponentInterval().getLength();
  }
}
