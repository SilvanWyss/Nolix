//package declaration
package ch.nolix.techapi.mathapi.fractalapi;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.techapi.mathapi.bigdecimalmathapi.IComplexNumber;

//interface
public interface IFractalHelper {

  //method declaration
  BigDecimal getHeightInPixelAsBigDecimal(IFractal fractal);

  //method declaration
  BigDecimal getHeightInUnits(IFractal fractal);

  //method declaration
  int getIterationCountForComplexNumberUntilValueSquaredMagnitudeExceedsLimitOrMinusOne(
    IFractal fractal,
    IComplexNumber complexNumber,
    BigDecimal limit);

  //method declaration
  BigDecimal getMaxX(IFractal fractal);

  //method declaration
  BigDecimal getMaxY(IFractal fractal);

  //method declaration
  BigDecimal getMinX(IFractal fractal);

  //method declaration
  BigDecimal getMinY(IFractal fractal);

  //method declaration
  BigDecimal getPixelCountPerHorizontalUnit(IFractal fractal);

  //method declaration
  BigDecimal getPixelCountPerVerticalUnit(IFractal fractal);

  //method declaration
  BigDecimal getSquaredMinMagnitudeForDivergence(IFractal fractal);

  //method declaration
  BigDecimal getUnitsPerHorizontalPixel(IFractal fractal);

  //method declaration
  BigDecimal getUnitsPerVerticalPixel(IFractal fractal);

  //method declaration
  BigDecimal getUnitsForHorizontalPixelCount(IFractal fractal, double horizontalPixelCount);

  //method declaration
  BigDecimal getUnitsForVerticalPixelCount(IFractal fractal, double verticalPixelCount);

  //method declaration
  BigDecimal getWidthInPixelAsBigDecimal(IFractal fractal);

  //method declaration
  BigDecimal getWidthInUnits(IFractal fractal);
}
