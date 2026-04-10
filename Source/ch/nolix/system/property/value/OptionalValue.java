/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.value;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.base.container.linkedlist.LinkedList;
import ch.nolix.base.document.node.Node;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.property.value.IOptionalValue;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the value of a {@link OptionalValue}.
 */
public final class OptionalValue<V> extends AbstractValue<V> implements IOptionalValue<V> {
  private final Consumer<V> setter;

  private V memberOptionalValue;

  /**
   * Creates a new {@link OptionalValue} with the given name, setter, valueMapper
   * and specificationMapper.
   * 
   * @param name
   * @param setter
   * @param valueMapper
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given valueMapper is null.
   * @throws RuntimeException if the given specificationMapper is null.
   */
  private OptionalValue(
    final String name,
    final Consumer<V> setter,
    final Function<INode<?>, V> valueMapper,
    final Function<V, INode<?>> specificationMapper) {
    super(name, valueMapper, specificationMapper);

    Validator.assertThat(setter).thatIsNamed(LowerCaseVariableCatalog.SETTER).isNotNull();

    this.setter = setter;
  }

  /**
   * @param name
   * @param setter
   * @return a new {@link OptionalValue} with the given name and setter and that
   *         can store a {@link Boolean}.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given setter is null.
   */
  public static OptionalValue<Boolean> forBooleanWithNameAndSetter(final String name, final Consumer<Boolean> setter) {
    return new OptionalValue<>(name, setter, INode::getSingleChildNodeAsBoolean, Node::withChildNode);
  }

  /**
   * @param name
   * @param setter
   * @return a new {@link OptionalValue} with the given name and setter and that
   *         can store a {@link Double}.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given setter is null.
   */
  public static OptionalValue<Double> forDoubleWithNameAndSetter(final String name, final Consumer<Double> setter) {
    return new OptionalValue<>(name, setter, INode::getSingleChildNodeAsDouble, Node::withChildNode);
  }

  /**
   * @param name
   * @param setter
   * @return a new {@link OptionalValue} with the given name and setter and that
   *         can store a {@link Integer}.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given setter is null.
   */
  public static OptionalValue<Integer> forIntWithNameAndSetter(final String name, final Consumer<Integer> setter) {
    return new OptionalValue<>(name, setter, INode::getSingleChildNodeAsInt, Node::withChildNode);
  }

  /**
   * @param name
   * @param setter
   * @return a new {@link OptionalValue} with the given name and setter and that
   *         can store a {@link String}.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given setter is null.
   */
  public static OptionalValue<String> forStringWithNameAndSetter(final String name, final Consumer<String> setter) {
    return new OptionalValue<>(
      name,
      setter,
      s -> s.getStoredSingleChildNode().getHeaderOrEmptyString(),
      Node::fromString);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    memberOptionalValue = null;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<INode<?>> list) {
    if (memberOptionalValue != null) {
      list.addAtEnd(mapValueToSpecification(memberOptionalValue).asWithHeader(getName()));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<INode<?>> getAttributes() {
    final ILinkedList<INode<?>> attributes = LinkedList.createEmpty();
    fillUpAttributesIntoList(attributes);
    return attributes;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public INode<?> getSpecification() {
    return Node.withHeaderAndChildNodes(getName(), getAttributes());
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public V getStoredValue() {
    if (memberOptionalValue == null) {
      throw //
      ArgumentDoesNotContainElementException.forArgumentAndArgumentNameAndElementName(
        this,
        getName(),
        LowerCaseVariableCatalog.VALUE);
    }

    return memberOptionalValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return memberOptionalValue == null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMutable() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(final V value) {
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    assertIsEmpty();

    memberOptionalValue = value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addOrChangeValue(final V value) {
    setter.accept(value);
  }

  /**
   * @throws RuntimeException if the current {@link OptionalValue} is not empty.
   */
  private void assertIsEmpty() {
    if (containsAny()) {
      throw NonEmptyArgumentException.forArgumentAndArgumentName(this, getName());
    }
  }
}
