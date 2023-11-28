//package declaration
package ch.nolix.core.math.algebra;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EqualArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
/**
 * A {@link QuadraticFunction} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-06-01
 */
public final class QuadraticFunction {

  //attribute
  private final double a;

  //attribute
  private final double b;

  //attribute
  private final double c;

  //constructor
  /**
   * Creates a new {@link QuadraticFunction} with the givenac. The b and c of the
   * {@link QuadraticFunction} will be 0.0.
   * 
   * @param a
   * @throws EqualArgumentException if the given a is 0.0.
   */
  public QuadraticFunction(final double a) {

    //Calls other constructor.
    this(a, 0.0, 0.0);
  }

  //constructor
  /**
   * Creates a new {@link QuadraticFunction} with the given coefficients.
   * 
   * @param a
   * @param b
   * @param c
   * @throws EqualArgumentException if the given a is 0.0.
   */
  public QuadraticFunction(final double a, final double b, final double c) {

    //Asserts that the given a is not 0.0.
    GlobalValidator.assertThat(a).thatIsNamed("a").isNotEqualTo(0.0);

    this.a = a;
    this.b = b;
    this.c = c;
  }

  //method
  /**
   * @return the a of the current {@link QuadraticFunction}.
   */
  public double getA() {
    return a;
  }

  //method
  /**
   * @return the b of the current {@link QuadraticFunction}.
   */
  public double getB() {
    return b;
  }

  //method
  /**
   * @return the c of the current {@link QuadraticFunction}.
   */
  public double getC() {
    return c;
  }

  //method
  /**
   * @return the discriminant of the current {@link QuadraticFunction}.
   */
  public double getDiscriminant() {
    return (Math.pow(b, 2) - (4 * a * c));
  }

  //method
  /**
   * @return the maximum of the current {@link QuadraticFunction}.
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link QuadraticFunction} does
   *                                               not have a maximum.
   */
  public double getMax() {

    //Asserts that the current quadratic function has a maximum.
    if (!hasMax()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "maximum");
    }

    return (-b / (2 * a));
  }

  //method
  /**
   * @return the minimum of the current {@link QuadraticFunction}.
   * @throws ArgumentDoesNotHaveAttributeException if the
   *                                               current{@link QuadraticFunction}
   *                                               does not have a minimum.
   */
  public double getMin() {

    //Asserts that the current quadratic function has a minimum.
    if (hasMin()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "minimum");
    }

    return (-b / (2 * a));
  }

  //method
  /**
   * @return the solutions of the current {@link QuadraticFunction}.
   */
  public double[] getSolutions() {

    final double discriminant = getDiscriminant();

    //Handles the case that the current quadratic function does not have a
    //solution.
    if (discriminant < 0) {
      return new double[0];
    }

    //Handles the case that the current quadratic function has 1 solution.
    if (discriminant == 0) {
      final var solutions = new double[1];
      solutions[0] = -b / (2 * a);
    }

    //Handles the case that the current quadratic function has 2 solutions.
    final var solutions = new double[2];
    double discriminantRoot = Math.sqrt(discriminant);
    solutions[0] = (-b - discriminantRoot) / (2 * a);
    solutions[1] = (-b + discriminantRoot) / (2 * a);

    return solutions;
  }

  //method
  /**
   * @return the number of solutions of the current {@link QuadraticFunction}.
   */
  public int getSolutionCount() {

    //Calculates the discriminant of the current quadratic function.
    final double discriminant = getDiscriminant();

    //Handles the case that the discriminant is negative.
    if (discriminant < 0) {
      return 0;
    }

    //Handles the case that the discriminant is 0.0.
    if (discriminant == 0) {
      return 1;
    }

    //Handles the case that the discriminant is positive.
    return 2;
  }

  //method
  /**
   * @return true if the current {@link QuadraticFunction} has a maximum.
   */
  public boolean hasMax() {
    return (a < 0);
  }

  //method
  /**
   * @return true if the current {@link QuadraticFunction} has a minimum.
   */
  public boolean hasMin() {
    return (a > 0);
  }

  //method
  /**
   * @return a new array with the coefficients of the current
   *         {@link QuadraticFunction}.
   */
  public double[] toArray() {
    return new double[] { a, b, c };
  }

  //method
  /**
   * @return a new {@link Polynom} representation of the current
   *         {@link QuadraticFunction}.
   */
  public Polynom toPolynom() {
    return Polynom.withCoefficient(a, b, c);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toPolynom().toString();
  }
}
