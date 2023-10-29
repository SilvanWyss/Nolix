//package declaration
package ch.nolix.coretest.mathtest.algebratest;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.math.algebra.Vector;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class VectorTest extends Test {

  //method
  @TestCase
  public void testCase_equals_whenEquals() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);
    final var vector = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution
    final var result = testUnit.equals(vector);

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_equals_whenDoesNotEqual() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);
    final var vector = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 1.0);

    //execution
    final var result = testUnit.equals(vector);

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_equals_whenIsEmptyAndEquals() {

    //setup
    final var testUnit = Vector.EMPTY_VECTOR;
    final var vector = Vector.EMPTY_VECTOR;

    //execution
    final var result = testUnit.equals(vector);

    //verification
    expect(result);
  }

  //method
  @TestCase
  public void testCase_equals_whenIsEmptyAndDoesNotEqual() {

    //setup
    final var testUnit = Vector.EMPTY_VECTOR;
    final var vector = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 1.0);

    //execution
    final var result = testUnit.equals(vector);

    //verification
    expectNot(result);
  }

  //method
  @TestCase
  public void testCase_getEuclidNorm() {

    //setup
    final var testUnit = Vector.withValue(6.0, 8.0);

    //execution
    final var result = testUnit.getEuclidNorm();

    //verification
    expect(result).isEqualTo(10.0);
  }

  //method
  @TestCase
  public void testCase_getProduct() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution
    final var result = testUnit.getProduct(2.5);

    //verification
    expect(result).hasStringRepresentation("(5.0,12.5,25.0,-7.5,-20.0,0.0)");
  }

  //method
  @TestCase
  public void testCase_getProduct_whenIsEmpty() {

    //setup
    final var testUnit = Vector.EMPTY_VECTOR;

    //execution
    final var result = testUnit.getProduct(2.5);

    //verification
    expect(result).hasStringRepresentation("()");
  }

  //method
  @TestCase
  public void testCase_getSize() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution
    final var result = testUnit.getSize();

    //verification
    expect(result).isEqualTo(6);
  }

  //method
  @TestCase
  public void testCase_getSize_whenIsEmpty() {

    //setup
    final var testUnit = Vector.EMPTY_VECTOR;

    //execution
    final var result = testUnit.getSize();

    //verification
    expect(result).isEqualTo(0);
  }

  //method
  @TestCase
  public void testCase_getSum_1A() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);
    final var addend = Vector.withValue(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

    //execution
    final var result = testUnit.getSum(addend);

    //verification
    expect(result).hasStringRepresentation("(2.0,5.0,10.0,-3.0,-8.0,0.0)");
  }

  //method
  @TestCase
  public void testCase_getSum_1B() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);
    final var addend = Vector.withValue(1.0, 1.0, 1.0, 1.0, 1.0, 1.0);

    //execution
    final var result = testUnit.getSum(addend);

    //verification
    expect(result).hasStringRepresentation("(3.0,6.0,11.0,-2.0,-7.0,1.0)");
  }

  //method
  @TestCase
  public void testCase_getSum_whenGivenAddendHasNotSameSize() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);
    final var addend = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0);

    //execution & verification
    expectRunning(() -> testUnit.getSum(addend))
      .throwsException()
      .ofType(InvalidArgumentException.class)
      .withMessage(
        "The given Vector '(2.0,5.0,10.0,-3.0,-8.0)' does not have the same size (which is 5) as "
        + "the current Vector (which has the size 6).");
  }

  //method
  @TestCase
  public void testCase_getSum_whenIsEmpty() {

    //setup
    final var testUnit = Vector.EMPTY_VECTOR;
    final var addend = Vector.EMPTY_VECTOR;

    //execution
    final var result = testUnit.getSum(addend);

    //verification
    expect(result).hasStringRepresentation("()");
  }

  //method
  @TestCase
  public void testCase_toArray() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution
    final var result = testUnit.toArray();

    //verification
    expect(result.length).isEqualTo(6);
    expect(result[0]).isEqualTo(2.0);
    expect(result[1]).isEqualTo(5.0);
    expect(result[2]).isEqualTo(10.0);
    expect(result[3]).isEqualTo(-3.0);
    expect(result[4]).isEqualTo(-8.0);
    expect(result[5]).isEqualTo(0.0);
  }

  //method
  @TestCase
  public void testCase_toArray_whenIsEmpty() {

    //setup
    final var testUnit = Vector.EMPTY_VECTOR;

    //execution
    final var result = testUnit.toArray();

    //verification
    expect(result.length).isEqualTo(0);
  }

  //method
  @TestCase
  public void testCase_toString() {

    //setup
    final var testUnit = Vector.withValue(2.0, 5.0, 10.0, -3.0, -8.0, 0.0);

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("(2.0,5.0,10.0,-3.0,-8.0,0.0)");
  }

  //method
  @TestCase
  public void testCase_toString_whenIsEmpty() {

    //setup
    final var testUnit = Vector.EMPTY_VECTOR;

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("()");
  }
}
