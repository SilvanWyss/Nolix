/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.valueproperty;

import java.util.function.Consumer;
import java.util.function.Function;

import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.element.valueproperty.IValueProperty;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link ValueProperty}.
 */
public final class ValueProperty<V> extends AbstractValueProperty<V> implements IValueProperty<V> {
  private final Consumer<V> setter;

  private V memberValue;

  /**
   * Creates a new {@link ValueProperty} with the given name, defaultValue, setter,
   * valueMapper and specificationMapper.
   * 
   * @param name
   * @param defaultValue
   * @param setter
   * @param valueMapper
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given defaultValue is null
   * @throws RuntimeException if the given setter is null
   * @throws RuntimeException if the given valueMapper is null
   * @throws RuntimeException if the given specificationMapper is null
   */
  private ValueProperty(
    final String name,
    final V defaultValue,
    final Consumer<V> setter,
    final Function<Node<?>, V> valueMapper,
    final Function<V, Node<?>> specificationMapper) {
    super(name, valueMapper, specificationMapper);

    Validator.assertThat(setter).thatIsNamed(LowerCaseVariableNameCatalog.SETTER).isNotNull();
    Validator.assertThat(defaultValue).thatIsNamed(LowerCaseVariableNameCatalog.DEFAULT_VALUE).isNotNull();

    this.setter = setter;
    this.memberValue = defaultValue;
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @return a new {@link ValueProperty} with the given name, defaultValue and setter and
   *         that will store a {@link Boolean}
   * @throws RuntimeException if the given name is null
   * @throws RuntimeException if the given name is blank
   * @throws RuntimeException if the given setter is null
   */
  public static ValueProperty<Boolean> forBooleanWithNameAndDefaultValueAndSetter(
    final String name,
    final boolean defaultValue,
    final Consumer<Boolean> setter) {
    return new ValueProperty<>(name, defaultValue, setter, Node::getSingleChildNodeAsBoolean,
      ImmutableNode::withChildNode);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @return a new {@link ValueProperty} with the given name, defaultValue and setter and
   *         that will store a {@link Double}
   * @throws RuntimeException if the given name is null
   * @throws RuntimeException if the given name is blank
   * @throws RuntimeException if the given setter is null
   */
  public static ValueProperty<Double> forDoubleWithNameAndDefaultValueAndSetter(
    final String name,
    final double defaultValue,
    final Consumer<Double> setter) {
    return new ValueProperty<>(name, defaultValue, setter, Node::getSingleChildNodeAsDouble, ImmutableNode::withChildNode);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @param valueMapper
   * @param <E>          the type of the value of a {@link ValueProperty}
   * @return a new {@link ValueProperty} with the given name, defaultValue, setter and
   *         valueMapper and and that can store a {@link Element}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given defaultValue is null
   * @throws RuntimeException if the given setter is null
   * @throws RuntimeException if the given valueMapper is null
   */
  public static <E extends Element> ValueProperty<E> forElementWithNameAndDefaultValueAndSetterAndValueMapper(
    final String name,
    final E defaultValue,
    final Consumer<E> setter,
    final Function<Node<?>, E> valueMapper) {
    return new ValueProperty<>(name, defaultValue, setter, valueMapper, Element::getSpecification);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @return a new {@link ValueProperty} with the given name, defaultValue and setter and
   *         that will store a {@link Integer}
   * @throws RuntimeException if the given name is null
   * @throws RuntimeException if the given name is blank
   * @throws RuntimeException if the given setter is null
   */
  public static ValueProperty<Integer> forIntWithNameAndDefaultValueAndSetter(
    final String name,
    final int defaultValue,
    final Consumer<Integer> setter) {
    return new ValueProperty<>(name, defaultValue, setter, Node::getSingleChildNodeAsInt, ImmutableNode::withChildNode);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @return a new {@link ValueProperty} with the given name, defaultValue and setter and
   *         that will store a {@link String}
   * @throws RuntimeException if the given name is null
   * @throws RuntimeException if the given name is blank
   * @throws RuntimeException if the given setter is null
   */
  public static ValueProperty<String> forStringWithNameAndDefaultValueAndSetter(
    final String name,
    final String defaultValue,
    final Consumer<String> setter) {
    return new ValueProperty<>(
      name,
      defaultValue,
      setter,
      s -> s.getStoredSingleChildNode().getHeaderOrEmptyString(),
      ImmutableNode::fromString);
  }

  /**
   * @param name
   * @param defaultValue
   * @param setter
   * @param valueMapper
   * @param specificationMapper
   * @param <T>                 the type of the value of the created {@link ValueProperty}
   * @return a new {@link ValueProperty} with the given name, defaultValue, setter,
   *         valueMapper and specificationMapper
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given defaultValue is null
   * @throws RuntimeException if the given setter is null
   * @throws RuntimeException if the given valueMapper is null
   * @throws RuntimeException if the given specificationMapper is null
   */
  public static <T> ValueProperty<T> withNameAndDefaultValueAndSetterAndValueMapperAndSpecificationMapper(
    final String name,
    final T defaultValue,
    final Consumer<T> setter,
    final Function<Node<?>, T> valueMapper,
    final Function<T, Node<?>> specificationMapper) {
    return new ValueProperty<>(name, defaultValue, setter, valueMapper, specificationMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<Node<?>> list) {
    final var attribute = mapValueToSpecification(memberValue).withNewHeader(getName());

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
  public void setValue(final V value) {
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableNameCatalog.VALUE).isNotNull();

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
