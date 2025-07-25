package ch.nolix.techapi.math.fractal;

import java.math.BigDecimal;

import ch.nolix.techapi.math.bigdecimalmath.IComplexNumber;

public interface IFractalTool {

  BigDecimal getHeightInPixelAsBigDecimal(IFractal fractal);

  BigDecimal getHeightInUnits(IFractal fractal);

  int getIterationCountForStartNumberWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(
    IFractal fractal,
    IComplexNumber startNumber,
    BigDecimal limit);

  BigDecimal getMaxX(IFractal fractal);

  BigDecimal getMaxY(IFractal fractal);

  BigDecimal getMinX(IFractal fractal);

  BigDecimal getMinY(IFractal fractal);

  BigDecimal getPixelCountPerHorizontalUnit(IFractal fractal);

  BigDecimal getPixelCountPerVerticalUnit(IFractal fractal);

  BigDecimal getSquaredMinMagnitudeForDivergence(IFractal fractal);

  BigDecimal getUnitsPerHorizontalPixel(IFractal fractal);

  BigDecimal getUnitsPerVerticalPixel(IFractal fractal);

  BigDecimal getUnitsForHorizontalPixelCount(IFractal fractal, double horizontalPixelCount);

  BigDecimal getUnitsForVerticalPixelCount(IFractal fractal, double verticalPixelCount);

  BigDecimal getWidthInPixelAsBigDecimal(IFractal fractal);

  BigDecimal getWidthInUnits(IFractal fractal);
}
