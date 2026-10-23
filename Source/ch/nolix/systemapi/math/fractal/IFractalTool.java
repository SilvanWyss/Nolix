/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.systemapi.math.fractal;

import java.math.BigDecimal;

import ch.nolix.systemapi.math.bigdecimalmath.IComplexNumber;

/**
 * @author Silvan Wyss
 */
public interface IFractalTool {
  /**
   * @param fractal
   * @return the height of the given fractal in pixel as {@link BigDecimal}
   */
  BigDecimal getHeightInPixelAsBigDecimal(IFractal fractal);

  /**
   * @param fractal
   * @return the height of the given fractal in units
   */
  BigDecimal getHeightInUnits(IFractal fractal);

  /**
   * @param fractal
   * @param startNumber
   * @param limit
   * @return the iteration count of the sequence of the given fractal for the
   *         given startNumber where the squared magnitude of the value exceeds
   *         the given limit, -1 if the squared magnitude does not exceed the
   *         given limit within the max iteration count of the given fractal
   */
  int getIterationCountForStartNumberWhereSquaredMagnitudeOfValueExceedsLimitOrMinusOne(
    IFractal fractal,
    IComplexNumber startNumber,
    BigDecimal limit);

  /**
   * @param fractal
   * @return the max x of the given fractal
   */
  BigDecimal getMaxX(IFractal fractal);

  /**
   * @param fractal
   * @return the max y of the given fractal
   */
  BigDecimal getMaxY(IFractal fractal);

  /**
   * @param fractal
   * @return the min x of the given fractal
   */
  BigDecimal getMinX(IFractal fractal);

  /**
   * @param fractal
   * @return the min y of the given fractal
   */
  BigDecimal getMinY(IFractal fractal);

  /**
   * @param fractal
   * @return the number of pixels per horizontal unit of the given fractal
   */
  BigDecimal getPixelCountPerHorizontalUnit(IFractal fractal);

  /**
   * @param fractal
   * @return the number of pixels per vertical unit of the given fractal
   */
  BigDecimal getPixelCountPerVerticalUnit(IFractal fractal);

  /**
   * @param fractal
   * @return the squared min magnitude for divergence of the given fractal
   */
  BigDecimal getSquaredMinMagnitudeForDivergence(IFractal fractal);

  /**
   * @param fractal
   * @param horizontalPixelCount
   * @return the number of units of the given fractal for the given
   *         horizontalPixelCount
   */
  BigDecimal getUnitsForHorizontalPixelCount(IFractal fractal, double horizontalPixelCount);

  /**
   * @param fractal
   * @param verticalPixelCount
   * @return the number of units of the given fractal for the given
   *         verticalPixelCount
   */
  BigDecimal getUnitsForVerticalPixelCount(IFractal fractal, double verticalPixelCount);

  /**
   * @param fractal
   * @return the number of units per horizontal pixel of the given fractal
   */
  BigDecimal getUnitsPerHorizontalPixel(IFractal fractal);

  /**
   * @param fractal
   * @return the number of units per vertical pixel of the given fractal
   */
  BigDecimal getUnitsPerVerticalPixel(IFractal fractal);

  /**
   * @param fractal
   * @return the width of the given fractal in pixel as {@link BigDecimal}
   */
  BigDecimal getWidthInPixelAsBigDecimal(IFractal fractal);

  /**
   * @param fractal
   * @return the width of the given fractal in units
   */
  BigDecimal getWidthInUnits(IFractal fractal);
}
