package ch.nolix.coretest.mathtest.algebratest.matrixtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.nolix.core.math.algebra.Matrix;
import ch.nolix.core.testing.standardtest.StandardTest;

final class ToStringMethodTest extends StandardTest {

  @ParameterizedTest
  @CsvSource({
  "1, '[1]'",
  "2, '[1,0;0,1]'",
  "3, '[1,0,0;0,1,0;0,0,1]'",
  "4, '[1,0,0,0;0,1,0,0;0,0,1,0;0,0,0,1]'"
  })
  void testCase_toString_whenIsIdentityMatrix(final int size, final String expectedResult) {

    //setup
    final var testUnit = Matrix.createIdendityMatrix(size);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo(expectedResult);
  }

  @Test
  void testCase_toString_whenMatrixIs1x1Matrix() {

    //setup
    final var testUnit = Matrix.createMatrixWithRowCountAndColumnCount(1, 1);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0]");
  }

  @Test
  void testCase_toString_whenMatrixIs2x2Matrix() {

    //setup
    final var testUnit = Matrix.createMatrixWithRowCountAndColumnCount(2, 2);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0;0,0]");
  }

  @Test
  void testCase_toString_whenMatrixIs3x3Matrix() {

    //setup
    final var testUnit = Matrix.createMatrixWithRowCountAndColumnCount(3, 3);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0,0;0,0,0;0,0,0]");
  }

  @Test
  void testCase_toString_whenMatrixIs4x4Matrix() {

    //setup
    final var testUnit = Matrix.createMatrixWithRowCountAndColumnCount(4, 4);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0,0,0;0,0,0,0;0,0,0,0;0,0,0,0]");
  }
}
