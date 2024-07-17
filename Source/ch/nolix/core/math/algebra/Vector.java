//package declaration
package ch.nolix.core.math.algebra;

//Java imports
import java.util.Arrays;

import ch.nolix.core.commontypetool.arraytool.GlobalArrayTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * A {@link Vector} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-02-01
 */
public final class Vector {

  //constant
  public static final Vector EMPTY_VECTOR = new Vector();

  //multi-attribute
  private final double[] values;

  //constructor
  /**
   * Creates a new empty {@link Vector}.
   */
  private Vector() {
    values = new double[0];
  }

  //constructor
  /**
   * Creates a new {@link Vector} with the given values.
   * 
   * @param values
   */
  private Vector(final double[] values) {
    this.values = Arrays.copyOf(values, values.length);
  }

  //static method
  /**
   * @param values
   * @return a new {@link Vector} with the given values.
   */
  public static Vector fromArray(final double[] values) {
    return new Vector(values);
  }

  //static method
  /**
   * @param value
   * @param values
   * @return a new {@link Vector} with the given values.
   */
  public static Vector withValue(final double value, final double... values) {
    return new Vector(GlobalArrayTool.createArrayWithValue(value, values));
  }

  //static method
  /**
   * @param values
   * @return a new {@link Vector} with the given values.
   */
  public static Vector withValues(final double[] values) {
    return new Vector(values);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object object) {
    return (object instanceof Vector vector && equalsVector(vector));
  }

  //method
  /**
   * @return the euclid norm of the current {@link Vector}.
   */
  public double getEuclidNorm() {

    var sum = 0.0;
    for (final var v : values) {
      sum += Math.pow(v, 2);
    }

    return Math.sqrt(sum);
  }

  //method
  /**
   * @return the Manhattan norm of the current {@link Vector}.
   */
  public double getManhattanNorm() {

    var manhattanNorm = 0.0;

    for (final var v : values) {
      manhattanNorm += Math.abs(v);
    }

    return manhattanNorm;
  }

  //method
  /**
   * @param factor
   * @return a new {@link Vector} that is the product of the current
   *         {@link Vector} with the given factor.
   */
  public Vector getProduct(final double factor) {

    final var size = getSize();
    final var productValues = new double[size];

    for (var i = 0; i < size; i++) {
      productValues[i] = factor * values[i];
    }

    return new Vector(productValues);
  }

  //method
  /**
   * @return the size of the current {@link Vector}.
   */
  public int getSize() {
    return values.length;
  }

  //method
  /**
   * @param vector
   * @return a new {@link Vector} that is the sum of the current {@link Vector}
   *         and the given vector.
   */
  public Vector getSum(final Vector vector) {

    if (!vector.hasSameSizeAs(this)) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        vector,
        "does not have the same size (which is "
        + vector.getSize()
        + ") as the current Vector (which has the size "
        + getSize()
        + ")");
    }

    final var size = getSize();
    final var sumValues = new double[size];

    for (var i = 0; i < size; i++) {
      sumValues[i] = values[i] + vector.values[i];
    }

    return new Vector(sumValues);
  }

  //method
  /**
   * @param p1BasedIndex
   * @return the value at the given index
   * @throws ArgumentIsOutOfRangeException if the given index is not positive or
   *                                       the given p1BasedIndex is bigger than
   *                                       the size of the current {@link Vector}.
   */
  public double getValueAt1BasedIndex(int p1BasedIndex) {

    GlobalValidator.assertThat(p1BasedIndex).thatIsNamed(LowerCaseVariableCatalogue.INDEX).isBetween(1, getSize());

    return values[p1BasedIndex - 1];
  }

  //method
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  //method
  public boolean hasSameSizeAs(final Vector vector) {
    return (getSize() == vector.getSize());
  }

  //method
  /**
   * @return an array with the values of the current {@link Vector}
   */
  public double[] toArray() {
    return Arrays.copyOf(values, values.length);
  }

  //method
  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {

    final var stringBuilder = new StringBuilder();

    stringBuilder.append("(");

    for (var i = 0; i < values.length; i++) {

      if (i > 0) {
        stringBuilder.append(",");
      }

      stringBuilder.append(values[i]);
    }

    stringBuilder.append(")");

    return stringBuilder.toString();
  }

  //method
  /**
   * @param vector
   * @return true if the current {@link Vector} can equal the given vector because
   *         of the size.
   */
  private boolean canEqualVectorBecauseOfSize(final Vector vector) {
    return vector != null
    && getSize() == vector.getSize();
  }

  //method
  /**
   * @param vector
   * @return true if the current {@link Vector} equals the given vector.
   */
  private boolean equalsVector(final Vector vector) {

    if (!canEqualVectorBecauseOfSize(vector)) {
      return false;
    }

    final var size = getSize();
    for (var i = 0; i < size; i++) {
      if (values[i] != vector.values[i]) {
        return false;
      }
    }

    return true;
  }
}
