//package declaration
package ch.nolix.coretest.mathtest.algebratest.matrixtest;

//JUnit imports
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//own imports
import ch.nolix.core.math.algebra.Matrix;
import ch.nolix.core.testing.standardtest.StandardTest;

//class
final class ToStringMethodTest extends StandardTest {

  //method
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

  //method
  @Test
  void testCase_toString_whenMatrixIs1x1Matrix() {

    //setup
    final var testUnit = new Matrix(1, 1);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0]");
  }

  //method
  @Test
  void testCase_toString_whenMatrixIs2x2Matrix() {

    //setup
    final var testUnit = new Matrix(2, 2);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0;0,0]");
  }

  //method
  @Test
  void testCase_toString_whenMatrixIs3x3Matrix() {

    //setup
    final var testUnit = new Matrix(3, 3);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0,0;0,0,0;0,0,0]");
  }

  //method
  @Test
  void testCase_toString_whenMatrixIs4x4Matrix() {

    //setup
    final var testUnit = new Matrix(4, 4);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0,0,0;0,0,0,0;0,0,0,0;0,0,0,0]");
  }
}
