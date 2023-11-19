//package declaration
package ch.nolix.system.element.relativevalue;

//Java imports
import java.text.DecimalFormat;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.documentapi.nodeapi.INode;
import ch.nolix.coreapi.programatomapi.variablenameapi.LowerCaseCatalogue;
import ch.nolix.system.element.main.Element;
import ch.nolix.systemapi.elementapi.relativevalueapi.IAbsoluteOrRelativeInt;

//class
/**
 * A {@link AbsoluteOrRelativeInt} stores either an integer or a percentage. A
 * {@link AbsoluteOrRelativeInt} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2022-10-15
 */
public final class AbsoluteOrRelativeInt extends Element implements IAbsoluteOrRelativeInt {

  //attribute
  private final boolean isAbsolute;

  //attribute
  private final int absoluteValue;

  //attribute
  private final double percentage;

  //constructor
  /**
   * Creates a new {@link AbsoluteOrRelativeInt} with the given intValue.
   * 
   * @param intValue
   */
  private AbsoluteOrRelativeInt(final int intValue) {
    isAbsolute = true;
    this.absoluteValue = intValue;
    percentage = 0.0;
  }

  //constructor
  /**
   * Creates a new {@link AbsoluteOrRelativeInt} with the given percentage.
   * 
   * @param percentage
   * @throws NegativeArgumentException if the given percentage is negative.
   */
  private AbsoluteOrRelativeInt(final double percentage) {

    GlobalValidator.assertThat(percentage).thatIsNamed(LowerCaseCatalogue.PERCENTAGE).isNotNegative();

    isAbsolute = false;
    absoluteValue = 0;
    this.percentage = percentage;
  }

  //static method
  /**
   * @param specification
   * @return a new {@link AbsoluteOrRelativeInt} from the given specification.
   * @throws InvalidArgumentException if the given specification is not valid.
   */
  public static AbsoluteOrRelativeInt fromSpecification(final INode<?> specification) {

    final var attribute = specification.getSingleChildNodeHeader();

    if (attribute.endsWith("%")) {
      return withPercentage(0.01 * Double.valueOf(attribute.substring(0, attribute.length() - 1)));
    }

    return withIntValue(Integer.parseInt(attribute));
  }

  //static method
  /**
   * @param intValue
   * @return a new {@link AbsoluteOrRelativeInt} with the given intValue.
   */
  public static AbsoluteOrRelativeInt withIntValue(final int intValue) {
    return new AbsoluteOrRelativeInt(intValue);
  }

  //static method
  /**
   * @param percentage
   * @return a new {@link AbsoluteOrRelativeInt} with the given percentage.
   * @throws NegativeArgumentException if the given percentage is negative.
   */
  public static AbsoluteOrRelativeInt withPercentage(final double percentage) {
    return new AbsoluteOrRelativeInt(percentage);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {

    final var attributes = new LinkedList<INode<?>>();

    if (isAbsolute()) {
      attributes.addAtEnd(Node.withHeader(getAbsoluteValue()));
    } else if (isRelative()) {
      attributes.addAtEnd(Node.withHeader(new DecimalFormat("0.#").format(100.0 * getPercentage()) + "%"));
    } else {
      throw InvalidArgumentException.forArgument(this);
    }

    return attributes;
  }

  //method

  @Override
  public int getAbsoluteValue() {

    assertIsAbsolute();

    return absoluteValue;
  }

  //method

  @Override
  public double getPercentage() {

    assertIsRelative();

    return percentage;
  }

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public int getValueRelativeToHundredPercentValue(final int hundredPercentValue) {

    if (isAbsolute) {
      return absoluteValue;
    }

    return (int) (percentage * hundredPercentValue);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAbsolute() {
    return isAbsolute;
  }

  //method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isPositive() {

    if (isAbsolute) {
      return (absoluteValue > 0);
    }

    return (percentage > 0.0);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isRelative() {
    return !isAbsolute();
  }

  //method
  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbsoluteOrRelativeInt}
   *                                               does not have an integer value.
   */
  private void assertIsAbsolute() {
    if (!isAbsolute()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "absolute value");
    }
  }

  //method
  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbsoluteOrRelativeInt}
   *                                               does not have a percentage.
   */
  private void assertIsRelative() {
    if (!isRelative()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.PERCENTAGE);
    }
  }
}
