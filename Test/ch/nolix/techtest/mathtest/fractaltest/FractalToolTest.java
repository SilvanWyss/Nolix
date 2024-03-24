//package declaration
package ch.nolix.techtest.mathtest.fractaltest;

//Java imports
import java.math.BigDecimal;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.testing.test.StandardTest;
import ch.nolix.tech.math.fractal.FractalBuilder;
import ch.nolix.tech.math.fractal.FractalTool;

//class
final class FractalToolTest extends StandardTest {

  //method
  @Test
  void testCase_getMaxX() {

    //setup
    final var fractalBuilder = new FractalBuilder().setRealComponentInterval(-1.5, 2.5).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getMaxX(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(2.5).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getMaxY() {

    //setup
    final var fractalBuilder = new FractalBuilder().setImaginaryComponentInterval(-1.5, 2.5).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getMaxY(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(2.5).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getMinX() {

    //setup
    final var fractalBuilder = new FractalBuilder().setRealComponentInterval(-1.5, 2.5).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getMinX(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(-1.5).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getMinY() {

    //setup
    final var fractalBuilder = new FractalBuilder().setImaginaryComponentInterval(-1.5, 2.5).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getMinY(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(-1.5).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getPixelCountPerHorizontalUnit() {

    //setup
    final var fractalBuilder = //
    new FractalBuilder().setRealComponentInterval(0.0, 2.0).setWidthInPixel(500).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getPixelCountPerHorizontalUnit(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(250).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getPixelCountPerVerticalUnit() {

    //setup
    final var fractalBuilder = //
    new FractalBuilder().setImaginaryComponentInterval(0.0, 2.0).setHeightInPixel(500).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getPixelCountPerVerticalUnit(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(250).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getSquaredMinMagnitudeForDivergence_1() {

    //setup
    final var fractalBuilder = new FractalBuilder().setMinMagnitudeForDivergence(1.0).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getSquaredMinMagnitudeForDivergence(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(1.0).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getSquaredMinMagnitudeForDivergence_2() {

    //setup
    final var fractalBuilder = new FractalBuilder().setMinMagnitudeForDivergence(2.0).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getSquaredMinMagnitudeForDivergence(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(4.0).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getUnitsPerHorizontalPixel() {

    //setup
    final var fractalBuilder = //
    new FractalBuilder().setRealComponentInterval(0.0, 2.0).setWidthInPixel(400).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getUnitsPerHorizontalPixel(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(0.005).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  void testCase_getUnitsPerVerticalPixel() {

    //setup
    final var fractalBuilder = //
    new FractalBuilder().setImaginaryComponentInterval(0.0, 2.0).setHeightInPixel(400).setDecimalPlaces(12);
    final var fractal = fractalBuilder.build();
    final var testUnit = new FractalTool();

    //execution
    final var result = testUnit.getUnitsPerVerticalPixel(fractal);

    //verification
    final var expectedResult = BigDecimal.valueOf(0.005).setScale(12);
    expect(result).isEqualTo(expectedResult);
  }
}
