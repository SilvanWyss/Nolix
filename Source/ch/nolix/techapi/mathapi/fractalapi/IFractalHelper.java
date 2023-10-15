//package declaration
package ch.nolix.techapi.mathapi.fractalapi;

//Java imports
import java.math.BigDecimal;

import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;

//interface
public interface IFractalHelper {

  // method declaration
  BigDecimal getHeightInPixelAsBigDecimalOf(IFractal fractal);

  // method declaration
  BigDecimal getHeightInUnitsOf(IFractal fractal);

  // method declaration
  int getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
      IFractal fractal,
      IComplexNumber complexNumber,
      BigDecimal limit);

  // method declaration
  BigDecimal getMaxXOf(IFractal fractal);

  // method declaration
  BigDecimal getMaxYOf(IFractal fractal);

  // method declaration
  BigDecimal getMinXOf(IFractal fractal);

  // method declaration
  BigDecimal getMinYOf(IFractal fractal);

  // method declaration
  BigDecimal getPixelCountPerHorizontalUnitOf(IFractal fractal);

  // method declaration
  BigDecimal getPixelCountPerVerticalUnitOf(IFractal fractal);

  // method declaration
  BigDecimal getSquaredMinMagnitudeForDivergenceOf(IFractal fractal);

  // method declaration
  BigDecimal getUnitsPerHorizontalPixelOf(IFractal fractal);

  // method declaration
  BigDecimal getUnitsPerVerticalPixelOf(IFractal fractal);

  // method declaration
  BigDecimal getUnitsForHorizontalPixelCount(IFractal fractal, double horizontalPixelCount);

  // method declaration
  BigDecimal getUnitsForVerticalPixelCount(IFractal fractal, double verticalPixelCount);

  // method declaration
  BigDecimal getWidthInPixelAsBigDecimalOf(IFractal fractal);

  // method declaration
  BigDecimal getWidthInUnitsOf(IFractal fractal);
}
