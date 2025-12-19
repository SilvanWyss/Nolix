package ch.nolix.core.math.algebra;

import java.util.Arrays;

import ch.nolix.core.commontypetool.arraytool.ArrayTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.core.independent.math.NumberComparator;

/**
 * A {@link Vector} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class Vector {
  public static final Vector EMPTY_VECTOR = new Vector();

  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  private final double[] values;

  /**
   * Creates a new empty {@link Vector}.
   */
  private Vector() {
    values = new double[0];
  }

  /**
   * Creates a new {@link Vector} with the given values.
   * 
   * @param values
   */
  private Vector(final double[] values) {
    this.values = Arrays.copyOf(values, values.length);
  }

  /**
   * @param values
   * @return a new {@link Vector} with the given values.
   */
  public static Vector fromArray(final double[] values) {
    return new Vector(values);
  }

  /**
   * @param value
   * @param values
   * @return a new {@link Vector} with the given values.
   */
  public static Vector withValue(final double value, final double... values) {
    return new Vector(ARRAY_TOOL.createArrayWithValue(value, values));
  }

  /**
   * @param values
   * @return a new {@link Vector} with the given values.
   */
  public static Vector withValues(final double[] values) {
    return new Vector(values);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object object) {
    return (object instanceof final Vector vector && equalsVector(vector));
  }

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

  /**
   * @return the size of the current {@link Vector}.
   */
  public int getSize() {
    return values.length;
  }

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

  /**
   * @param oneBasedIndex
   * @return the value of the current {@link Vector} at the given oneBasedIndex.
   * @throws ArgumentIsOutOfRangeException if the given oneBasedIndex is not
   *                                       positive or the given oneBasedIndex is
   *                                       bigger than the size of the current
   *                                       {@link Vector}.
   */
  public double getValueAtOneBasedIndex(final int oneBasedIndex) {
    Validator.assertThat(oneBasedIndex).thatIsNamed("1-based index").isBetween(1, getSize());

    return values[oneBasedIndex - 1];
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  public boolean hasSameSizeAs(final Vector vector) {
    return (getSize() == vector.getSize());
  }

  /**
   * @return an array with the values of the current {@link Vector}
   */
  public double[] toArray() {
    return Arrays.copyOf(values, values.length);
  }

  //For a better performance, this implementation does not use all available comfort methods.
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

  /**
   * @param vector
   * @return true if the current {@link Vector} can equal the given vector because
   *         of the size.
   */
  private boolean canEqualVectorBecauseOfSize(final Vector vector) {
    return vector != null
    && getSize() == vector.getSize();
  }

  /**
   * @param vector
   * @return true if the current {@link Vector} equals the given vector, false
   *         otherwise.
   */
  private boolean equalsVector(final Vector vector) {
    if (!canEqualVectorBecauseOfSize(vector)) {
      return false;
    }

    final var size = getSize();

    for (var i = 0; i < size; i++) {
      if (!NumberComparator.areEqual(values[i], vector.values[i])) {
        return false;
      }
    }

    return true;
  }
}
