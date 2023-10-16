//package declaration
package ch.nolix.core.container.pair;

//own imports
import ch.nolix.coreapi.containerapi.pairapi.IIntPair;

//class
/**
 * A {@link IntPair} contains 2 ints. A {@link IntPair} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2020-05-30
 */
public final class IntPair implements IIntPair {

  //constant
  public static final int DEFAULT_VALUE = 0;

  //attribute
  private final int value1;

  //attribute
  private final int value2;

  //constructor
  /**
   * Creates a new {@link IntPair} with default values.
   */
  public IntPair() {

    //Calls other constructor.
    this(DEFAULT_VALUE, DEFAULT_VALUE);
  }

  //constructor
  /**
   * Creates a new {@link IntPair} with the given values.
   * 
   * @param value1
   * @param value2
   */
  public IntPair(final int value1, final int value2) {

    //Sets the values of the current IntPair.
    this.value1 = value1;
    this.value2 = value2;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {

    //Handles the case that the given object is not an IntPair.
    if (!(object instanceof IntPair)) {
      return false;
    }

    //Handles the case that the given object is an IntPair.
    final var intPair = (IntPair) object;
    return (getValue1() == intPair.getValue1() && getValue2() == intPair.getValue2());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int getValue1() {
    return value1;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int getValue2() {
    return value2;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return ("(" + getValue1() + "," + getValue2() + ")");
  }
}
