//package declaration
package ch.nolix.coretest.mathtest.algebratest;

import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.UnrepresentingArgumentException;
import ch.nolix.core.math.algebra.Matrix;
import ch.nolix.core.math.algebra.Vector;
import ch.nolix.core.math.main.GlobalCalculator;
import ch.nolix.core.testing.test.StandardTest;

//class
public final class MatrixTest extends StandardTest {

  //method
  @Test
  public void testCase_add() {

    //setup
    final var testUnit = new Matrix(2, 3).setValues(1.0, 1.0, 1.0, 2.0, 2.0, 2.0);
    final var matrix = new Matrix(2, 3).setValues(5.0, 5.0, 5.0, 6.0, 6, 6.0);

    //execution
    final var result = testUnit.getSum(matrix);

    //verification
    final var expectedResult = new Matrix(2, 3).setValues(6.0, 6.0, 6.0, 8.0, 8.0, 8.0);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  public void testCase_appendAtRight() {

    //setup
    final var testUnit = new Matrix(2, 2).setValues(1.0, 1.0, 2.0, 2.0);
    final var matrix = new Matrix(2, 2).setValues(5.0, 5.0, 6.0, 6.0);

    //execution
    testUnit.appendAtRight(matrix);

    //verification
    final var expectedResult = new Matrix(2, 4).setValues(1.0, 1.0, 5.0, 5.0, 2.0, 2.0, 6.0, 6.0);
    expect(testUnit).isEqualTo(expectedResult);
  }

  //method
  @Test
  public void testCase_createIdendityMatrix_with1Row() {

    //execution
    final var result = Matrix.createIdendityMatrix(1);

    //verification
    expect(result.getRowCount()).isEqualTo(1);
    expect(result.getColumnCount()).isEqualTo(1);
    expect(result.getValue(1, 1)).isEqualTo(1.0);
  }

  //method
  @Test
  public void testCase_createIdendityMatrix_with10Rows() {

    //execution
    final var result = Matrix.createIdendityMatrix(10);

    //verification
    expect(result.getRowCount()).isEqualTo(10);
    expect(result.getColumnCount()).isEqualTo(10);
    for (var i = 1; i <= 10; i++) {
      for (var j = 1; j <= 10; j++) {
        if (i != j) {
          expect(result.getValue(i, j)).isEqualTo(0.0);
        } else {
          expect(result.getValue(i, j)).isEqualTo(1.0);
        }
      }
    }
  }

  //method
  @Test
  public void testCase_getInverse_whenMatrixIs2x2Matrix() {

    //setup
    final var testUnit = new Matrix(2, 2).setValues(1.0, 2.0, 3.0, 4.0);

    //execution
    final var result = testUnit.getInverse();

    //verification
    final var expectedResult = new Matrix(2, 2).setValues(1.0, 0.0, 0.0, 1.0);
    expect(testUnit.getProduct(result)).isEqualTo(expectedResult);
  }

  //method
  @Test
  public void testCase_getInverse_whenMatrixIs3x3Matrix() {

    //setup
    final var testUnit = new Matrix(3, 3).setValues(2.0, 6.0, 4.0, 1.0, 5.0, 9.0, 3.0, 7.0, 8.0);

    //execution
    final var result = testUnit.getInverse();

    //verification
    final var expectedResult = new Matrix(3, 3).setValues(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0);
    expect(testUnit.getProduct(result).equalsApproximatively(expectedResult, GlobalCalculator.DEFAULT_MAX_DEVIATION));
  }

  //method
  @Test
  public void testCase_getInverse_whenMatrixIs4x4Matrix() {

    //setup
    final var testUnit = new Matrix(4, 4).setValues(3.0, 1.0, 7.0, 3.0, 5.0, 9.0, 8.0, 7.0, 8.0, 6.0, 8.0, 4.0, 5.0,
      9.0, 3.0, 2.0);

    //execution
    final var result = testUnit.getInverse();

    //verification
    final var expectedResult = new Matrix(4, 4).setValues(1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
      0.0, 0.0, 0.0, 1.0);
    expect(testUnit.getProduct(result).equalsApproximatively(expectedResult, GlobalCalculator.DEFAULT_MAX_DEVIATION));
  }

  //method
  @Test
  public void testCase_getProduct() {

    //setup
    final var testUnit = new Matrix(2, 3).setValues(1, 1, 1, 2, 2, 2);
    final var matrix = new Matrix(3, 2).setValues(1, 1, 2, 2, 3, 3);

    //execution
    final var result = testUnit.getProduct(matrix);

    //verification
    final Matrix expectedProduct = new Matrix(2, 2).setValues(6, 6, 12, 12);
    expect(result).isEqualTo(expectedProduct);
  }

  //method
  @Test
  public void testCase_getRank_whenMatrixIsIdentityMatrix() {

    //parameter definition
    final var n = 10;

    //test loop
    for (var k = 1; k <= n; k++) {

      //setup
      final var testUnit = Matrix.createIdendityMatrix(k);

      //execution
      final var result = testUnit.getRank();

      //verification
      expect(result).isEqualTo(k);
    }
  }

  //method
  @Test
  public void testCase_getSolutionAsExtendedMatrix_whenMatrixIs2x3Matrix() {

    //setup
    final var testUnit = new Matrix(2, 3).setValues(4.0, 4.0, 30.0, 0.0, 2.0, 10.0);

    //execution
    final var result = testUnit.getSolutionAsExtendedMatrix();

    //verification
    expect(result.length).isEqualTo(2);
    expect(result[0]).isEqualTo(2.5);
    expect(result[1]).isEqualTo(5.0);
  }

  //method
  @Test
  public void testCase_getSolutionAsExtendedMatrix_whenMatrixIs3x4Matrix() {

    //setup
    final var testUnit = new Matrix(3, 4).setValues(1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0);

    //execution
    final var result = testUnit.getSolutionAsExtendedMatrix();

    //verification
    expect(result.length).isEqualTo(3);
    expect(result[0]).isEqualTo(1.0);
    expect(result[1]).isEqualTo(1.0);
    expect(result[2]).isEqualTo(1.0);
  }

  //method
  @Test
  public void testCase_getTrace_whenMatrixIsIdentityMatrix() {

    //parameter definition
    final var n = 100;

    //test loop
    for (var k = 1; k <= n; k++) {

      //setup
      final var testUnit = Matrix.createIdendityMatrix(k);

      //execution
      final var result = testUnit.getTrace();

      //verification
      expect(result).isEqualTo(k);
    }
  }

  //method
  @Test
  public void testCase_getTransposed_whenGivenMatrixIs4x3Matrix() {

    //setup
    final var testUnit = new Matrix(4, 3).setValues(20.0, 10.0, 1.0, 10.0, 20.0, 1.0, 20.0, 10.0, 1.0, 10.0, 20.0, 1.0);

    //execution
    final var result = testUnit.getTransposed();

    //verification
    final var expectedResult = new Matrix(3, 4).setValues(20.0, 10.0, 20.0, 10.0, 10.0, 20.0, 10.0, 20.0, 1.0, 1.0, 1.0,
      1.0);
    expect(result).isEqualTo(expectedResult);
  }

  //method
  @Test
  public void testCase_toString_whenMatrixIs1x1IdentityMatrix() {

    //setup
    final var testUnit = Matrix.createIdendityMatrix(1);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[1]");
  }

  //method
  @Test
  public void testCase_toString_whenMatrixIs2x2IdentityMatrix() {

    //setup
    final var testUnit = Matrix.createIdendityMatrix(2);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[1,0;0,1]");
  }

  //method
  @Test
  public void testCase_toString_whenMatrixIs3x3IdentityMatrix() {

    //setup
    final var testUnit = Matrix.createIdendityMatrix(3);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[1,0,0;0,1,0;0,0,1]");
  }

  //method
  @Test
  public void testCase_toString_whenMatrixIs4x4IdentityMatrix() {

    //setup
    final var testUnit = Matrix.createIdendityMatrix(4);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[1,0,0,0;0,1,0,0;0,0,1,0;0,0,0,1]");
  }

  //method
  @Test
  public void testCase_toString_whenMatrixIs1x1Matrix() {

    //setup
    final var testUnit = new Matrix(1, 1);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0]");
  }

  //method
  @Test
  public void testCase_toString_whenMatrixIs2x2Matrix() {

    //setup
    final var testUnit = new Matrix(2, 2);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0;0,0]");
  }

  //method
  @Test
  public void testCase_toString_whenMatrixIs3x3Matrix() {

    //setup
    final var testUnit = new Matrix(3, 3);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0,0;0,0,0;0,0,0]");
  }

  //method
  @Test
  public void testCase_toString_whenMatrixIs4x4Matrix() {

    //setup
    final var testUnit = new Matrix(4, 4);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("[0,0,0,0;0,0,0,0;0,0,0,0;0,0,0,0]");
  }

  //method
  @Test
  public void testCase_toVector_whenContains1Row() {

    //setup
    final var testUnit = new Matrix(1, 4).setValues(1.0, 2.0, 3.0, 4.0);

    //execution
    final var result = testUnit.toVector();

    //verification
    expect(result).isEqualTo(Vector.withValue(1.0, 2.0, 3.0, 4.0));
  }

  //method
  @Test
  public void testCase_toVector_whenContains1Column() {

    //setup
    final var testUnit = new Matrix(4, 1).setValues(1.0, 2.0, 3.0, 4.0);

    //execution
    final var result = testUnit.toVector();

    //verification
    expect(result).isEqualTo(Vector.withValue(1.0, 2.0, 3.0, 4.0));
  }

  //method
  @Test
  public void testCase_toVector_whenIs2x2Matrix() {

    //setup
    final var testUnit = new Matrix(2, 2).setValues(1.0, 2.0, 3.0, 4.0);

    //execution & verification
    expectRunning(testUnit::toVector)
      .throwsException()
      .ofType(UnrepresentingArgumentException.class)
      .withMessage("The given Matrix '[1,2;3,4]' does not represent a Vector.");
  }
}
