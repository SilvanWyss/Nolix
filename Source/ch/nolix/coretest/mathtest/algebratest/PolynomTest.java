//package declaration
package ch.nolix.coretest.mathtest.algebratest;

//own imports
import ch.nolix.core.math.algebra.Polynom;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.core.testing.test.Test;

//class
public final class PolynomTest extends Test {

  //method
  @TestCase
  public void testCase_getDerived_1A() {

    //setup
    final var testUnit = Polynom.withCoefficient(3.0, 0.0, 0.0);

    //setup verification
    expect(testUnit).hasStringRepresentation("x->3x^2");

    //execution
    final var result = testUnit.getDerived();

    //verification
    expect(result).hasStringRepresentation("x->6x");
  }

  //method
  @TestCase
  public void testCase_getDerived_1B() {

    //setup
    final var testUnit = Polynom.withCoefficient(3.0, 3.0, 3.0);

    //setup verification
    expect(testUnit).hasStringRepresentation("x->3x^2+3x+3");

    //execution
    final var result = testUnit.getDerived();

    //verification
    expect(result).hasStringRepresentation("x->6x+3");
  }

  //method
  @TestCase
  public void testCase_getDerived_1C() {

    //setup
    final var testUnit = Polynom.withCoefficient(3.0, 2.0, 1.0);

    //setup verification
    expect(testUnit).hasStringRepresentation("x->3x^2+2x+1");

    //execution
    final var result = testUnit.getDerived();

    //verification
    expect(result).hasStringRepresentation("x->6x+2");
  }

  //method
  @TestCase
  public void testCase_getIntegrated_1A() {

    //setup
    final var testUnit = Polynom.withCoefficient(3.0, 0.0, 0.0);

    //setup verification
    expect(testUnit).hasStringRepresentation("x->3x^2");

    //execution
    final var result = testUnit.getIntegrated();

    //verification
    expect(result).hasStringRepresentation("x->x^3");
  }

  //method
  @TestCase
  public void testCase_getIntegrated_1B() {

    //setup
    final var testUnit = Polynom.withCoefficient(3.0, 2.0, 1.0);

    //setup verification
    expect(testUnit).hasStringRepresentation("x->3x^2+2x+1");

    //execution
    final var result = testUnit.getIntegrated();

    //verification
    expect(result).hasStringRepresentation("x->x^3+x^2+1x");
  }

  //method
  @TestCase
  public void testCase_toString_whenIsEmpty() {

    //setup
    final var testUnit = Polynom.EMPTY_POLYNOM;

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("x->0.0");
  }

  //method
  @TestCase
  public void testCase_toString_whenThereIsGiven1Coefficient() {

    //setup
    final var testUnit = Polynom.withCoefficient(1.0);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("x->1");
  }

  //method
  @TestCase
  public void testCase_toString_whenThereAreGiven2Coefficients() {

    //setup
    final var testUnit = Polynom.withCoefficient(2.0, 1.0);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("x->2x+1");
  }

  //method
  @TestCase
  public void testCase_toString_whenThereAreGiven3Coefficients() {

    //setup
    final var testUnit = Polynom.withCoefficient(3.0, 2.0, 1.0);

    //execution
    final var result = testUnit.toString();

    //execution
    expect(result).isEqualTo("x->3x^2+2x+1");
  }

  //method
  @TestCase
  public void testCase_withCoefficient_whenThereIsGiven1Coefficient() {

    //execution
    final var result = Polynom.withCoefficient(1.0);

    //verification
    expect(result.getDegree()).isEqualTo(0);
    expect(result.getCoefficientForDegree(0)).isEqualTo(1.0);
  }

  //method
  @TestCase
  public void testCase_withCoefficient_whenThereAreGiven2Coefficients() {

    //execution
    final var result = Polynom.withCoefficient(2.0, 1.0);

    //verification
    expect(result.getDegree()).isEqualTo(1);
    expect(result.getCoefficientForDegree(0)).isEqualTo(1.0);
    expect(result.getCoefficientForDegree(1)).isEqualTo(2.0);
  }

  //method
  @TestCase
  public void testCase_withCoefficient_whenThereAreGiven3Coefficients() {

    //execution
    final var result = Polynom.withCoefficient(3.0, 2.0, 1.0);

    //verification
    expect(result.getDegree()).isEqualTo(2);
    expect(result.getCoefficientForDegree(0)).isEqualTo(1.0);
    expect(result.getCoefficientForDegree(1)).isEqualTo(2.0);
    expect(result.getCoefficientForDegree(2)).isEqualTo(3.0);
  }
}
