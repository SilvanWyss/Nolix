/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.webgui.base;

import java.text.DecimalFormat;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.system.element.base.AbstractElement;
import ch.nolix.systemapi.webgui.base.IAbsoluteOrRelativeInt;

/**
 * A {@link AbsoluteOrRelativeInt} stores either an integer or a percentage. A
 * {@link AbsoluteOrRelativeInt} is not mutable.
 * 
 * @author Silvan Wyss
 */
public final class AbsoluteOrRelativeInt extends AbstractElement implements IAbsoluteOrRelativeInt {
  private final boolean isAbsolute;

  private final int absoluteValue;

  private final double percentage;

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

  /**
   * Creates a new {@link AbsoluteOrRelativeInt} with the given percentage.
   * 
   * @param percentage
   * @throws RuntimeException if the given percentage is negative
   */
  private AbsoluteOrRelativeInt(final double percentage) {
    Validator.assertThat(percentage).thatIsNamed(LowerCaseVariableNameCatalog.PERCENTAGE).isNotNegative();

    isAbsolute = false;
    absoluteValue = 0;
    this.percentage = percentage;
  }

  /**
   * @param specification
   * @return a new {@link AbsoluteOrRelativeInt} from the given specification
   * @throws RuntimeException if the given specification is not valid
   */
  public static AbsoluteOrRelativeInt fromSpecification(final Node<?> specification) {
    final var attribute = specification.getSingleChildNodeHeader();

    if (attribute.endsWith("%")) {
      return withPercentage(0.01 * Double.valueOf(attribute.substring(0, attribute.length() - 1)));
    }

    return withIntValue(Integer.parseInt(attribute));
  }

  /**
   * @param intValue
   * @return a new {@link AbsoluteOrRelativeInt} with the given intValue.
   */
  public static AbsoluteOrRelativeInt withIntValue(final int intValue) {
    return new AbsoluteOrRelativeInt(intValue);
  }

  /**
   * @param percentage
   * @return a new {@link AbsoluteOrRelativeInt} with the given percentage
   * @throws RuntimeException if the given percentage is negative
   */
  public static AbsoluteOrRelativeInt withPercentage(final double percentage) {
    return new AbsoluteOrRelativeInt(percentage);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<Node<?>> getAttributes() {
    final ILinkedList<Node<?>> attributes = LinkedList.createEmpty();

    if (isAbsolute()) {
      attributes.addAtEnd(ImmutableNode.withHeader(getAbsoluteValue()));
    } else if (isRelative()) {
      attributes.addAtEnd(ImmutableNode.withHeader(new DecimalFormat("0.#").format(100.0 * getPercentage()) + "%"));
    } else {
      throw InvalidArgumentException.forArgument(this);
    }

    return attributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getAbsoluteValue() {
    assertIsAbsolute();

    return absoluteValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double getPercentage() {
    assertIsRelative();

    return percentage;
  }

  // For a better performance, this implementation does not use all available comfort methods.
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

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isAbsolute() {
    return isAbsolute;
  }

  // For a better performance, this implementation does not use all available comfort methods.
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

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isRelative() {
    return !isAbsolute();
  }

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

  /**
   * @throws ArgumentDoesNotHaveAttributeException if the current
   *                                               {@link AbsoluteOrRelativeInt}
   *                                               does not have a percentage.
   */
  private void assertIsRelative() {
    if (!isRelative()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableNameCatalog.PERCENTAGE);
    }
  }
}
