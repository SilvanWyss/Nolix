/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.value;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.base.document.node.Node;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.property.value.IMutableValue;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the value of a {@link MutableValue}.
 */
public final class MutableValue<V> extends AbstractValue<V> implements IMutableValue<V> {
  private final Consumer<V> setter;

  private V memberValue;

  /**
   * Creates a new {@link MutableValue} with the given name, defaultValue, setter,
   * valueMapper and specificationMapper.
   * 
   * @param name
   * @param defaultValue
   * @param setter
   * @param valueMapper
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given defaultValue is null.
   * @throws RuntimeException if the given setter is null.
   * @throws RuntimeException if the given valueMapper is null.
   * @throws RuntimeException if the given specificationMapper is null.
   */
  private MutableValue(
    final String name,
    final V defaultValue,
    final Consumer<V> setter,
    final Function<INode<?>, V> valueMapper,
    final Function<V, INode<?>> specificationMapper) {
    super(name, valueMapper, specificationMapper);

    Validator.assertThat(setter).thatIsNamed(LowerCaseVariableCatalog.SETTER).isNotNull();
    Validator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableCatalog.DEFAULT_VALUE).isNotNull();

    this.setter = setter;
    this.memberValue = defaultValue;
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @return a new {@link MutableValue} with the given name, defaultValue and
   *         setter and that will store a {@link Boolean}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   * @throws RuntimeException if the given setter is null.
   */
  public static MutableValue<Boolean> forBooleanWithNameAndDefaultValueAndSetter(
    final String name,
    final boolean defaultValue,
    final Consumer<Boolean> setter) {
    return new MutableValue<>(name, defaultValue, setter, INode::getSingleChildNodeAsBoolean,
      Node::withChildNode);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @return a new {@link MutableValue} with the given name, defaultValue and
   *         setter and that will store a {@link Double}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   * @throws RuntimeException if the given setter is null.
   */
  public static MutableValue<Double> forDoubleWithNameAndDefaultValueAndSetter(
    final String name,
    final double defaultValue,
    final Consumer<Double> setter) {
    return new MutableValue<>(name, defaultValue, setter, INode::getSingleChildNodeAsDouble, Node::withChildNode);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @return a new {@link MutableValue} with the given name, defaultValue and
   *         setter and that will store a {@link Integer}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   * @throws RuntimeException if the given setter is null.
   */
  public static MutableValue<Integer> forIntWithNameAndDefaultValueAndSetter(
    final String name,
    final int defaultValue,
    final Consumer<Integer> setter) {
    return new MutableValue<>(name, defaultValue, setter, INode::getSingleChildNodeAsInt, Node::withChildNode);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @return a new {@link MutableValue} with the given name, defaultValue and
   *         setter and that will store a {@link String}.
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   * @throws RuntimeException if the given setter is null.
   */
  public static MutableValue<String> forStringWithNameAndDefaultValueAndSetter(
    final String name,
    final String defaultValue,
    final Consumer<String> setter) {
    return new MutableValue<>(
      name,
      defaultValue,
      setter,
      s -> s.getStoredSingleChildNode().getHeaderOrEmptyString(),
      Node::fromString);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @param valueMapper
   * @param specificationMapper
   * @param <T>                 is the type of the value of the created
   *                            {@link MutableValue}.
   * @return a new {@link MutableValue} with the given name, defaultValue, setter,
   *         valueMapper and specificationMapper.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given defaultValue is null.
   * @throws RuntimeException if the given setter is null.
   * @throws RuntimeException if the given valueMapper is null.
   * @throws RuntimeException if the given specificationMapper is null.
   */
  public static <T> MutableValue<T> withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    final String name,
    final T defaultValue,
    final Consumer<T> setter,
    final Function<INode<?>, T> valueMapper,
    final Function<T, INode<?>> specificationMapper) {
    return new MutableValue<>(name, defaultValue, setter, valueMapper, specificationMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<INode<?>> list) {
    final var attribute = mapValueToSpecification(memberValue).asWithHeader(getName());

    list.addAtEnd(attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public V getStoredValue() {
    return memberValue;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMutable() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(final V value) {
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableCatalog.VALUE).isNotNull();

    this.memberValue = value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addOrChangeValue(final V value) {
    setter.accept(value);
  }
}
