//package declaration
package ch.nolix.techtest.mathtest.fractaltest;

//Java imports
import java.math.BigDecimal;

//own imports
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;
import ch.nolix.tech.math.fractal.FractalBuilder;
import ch.nolix.tech.math.fractal.FractalHelper;

//class
public final class FractalHelperTest extends Test {

  //method
  @TestCase
  public void testCase_getSquaredMinMagnitudeForDivergence_1() {

    //setup
    final var fractalBuilder = new FractalBuilder().setMinMagnitudeForDivergence(1.0).setBigDecimalScale(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalHelper();

    //execution
    final var result = testUnit.getSquaredMinMagnitudeForDivergence(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(1.0).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @TestCase
  public void testCase_getSquaredMinMagnitudeForDivergence_2() {

    //setup
    final var fractalBuilder = new FractalBuilder().setMinMagnitudeForDivergence(2.0).setBigDecimalScale(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalHelper();

    //execution
    final var result = testUnit.getSquaredMinMagnitudeForDivergence(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(4.0).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @TestCase
  public void testCase_getUnitsPerHorizontalPixel() {

    //setup
    final var fractalBuilder = //
    new FractalBuilder().setRealComponentInterval(0.0, 2.0).setWidthInPixel(400).setBigDecimalScale(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalHelper();

    //execution
    final var result = testUnit.getUnitsPerHorizontalPixel(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(0.005).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @TestCase
  public void testCase_getUnitsPerVerticalPixel() {

    //setup
    final var fractalBuilder = //
    new FractalBuilder().setImaginaryComponentInterval(0.0, 2.0).setHeightInPixel(400).setBigDecimalScale(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalHelper();

    //execution
    final var result = testUnit.getUnitsPerVerticalPixel(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(0.005).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }
}
