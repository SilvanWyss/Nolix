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
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.element.base.Element;
import ch.nolix.systemapi.element.valueproperty.IOptionalValueProperty;

/**
 * @author Silvan Wyss
 * @param <V> the type of the value of a {@link OptionalValueProperty}.
 */
public final class OptionalValueProperty<V> extends AbstractValueProperty<V> implements IOptionalValueProperty<V> {
  private final Consumer<V> setter;

  private V memberOptionalValue;

  /**
   * Creates a new {@link OptionalValueProperty} with the given name, setter, valueMapper
   * and specificationMapper.
   * 
   * @param name
   * @param setter
   * @param valueMapper
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given valueMapper is null
   * @throws RuntimeException if the given specificationMapper is null
   */
  private OptionalValueProperty(
    final String name,
    final Consumer<V> setter,
    final Function<Node<?>, V> valueMapper,
    final Function<V, Node<?>> specificationMapper) {
    super(name, valueMapper, specificationMapper);

    Validator.assertThat(setter).thatIsNamed(LowerCaseVariableNameCatalog.SETTER).isNotNull();

    this.setter = setter;
  }

  /**
   * @param name
   * @param setter
   * @return a new {@link OptionalValueProperty} with the given name and setter and that
   *         can store a {@link Boolean}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given setter is null
   */
  public static OptionalValueProperty<Boolean> forBooleanWithNameAndSetter(final String name, final Consumer<Boolean> setter) {
    return new OptionalValueProperty<>(name, setter, Node::getSingleChildNodeAsBoolean, ImmutableNode::withChildNode);
  }

  /**
   * @param name
   * @param setter
   * @return a new {@link OptionalValueProperty} with the given name and setter and that
   *         can store a {@link Double}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given setter is null
   */
  public static OptionalValueProperty<Double> forDoubleWithNameAndSetter(final String name, final Consumer<Double> setter) {
    return new OptionalValueProperty<>(name, setter, Node::getSingleChildNodeAsDouble, ImmutableNode::withChildNode);
  }

  /**
   * @param name
   * @param setter
   * @param valueMapper
   * @param <E>         the type of the value of a {@link OptionalValueProperty}
   * @return a new {@link OptionalValueProperty} with the given name, setter and
   *         valueMapperand and that can store a {@link Element}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given setter is null
   * @throws RuntimeException if the given valueMapper is null
   */
  public static <E extends Element> OptionalValueProperty<E> forElementWithNameAndSetterAndValueMapper(
    final String name,
    final Consumer<E> setter,
    final Function<Node<?>, E> valueMapper) {
    return new OptionalValueProperty<>(name, setter, valueMapper, Element::getSpecification);
  }

  /**
   * @param enumClass
   * @param name
   * @param setter
   * @param <E>       the type of the {@link Enum} the created
   *                  {@link OptionalValueProperty} can store
   * @return a new {@link OptionalValueProperty} with the given name and setter and that
   *         can store an {@link Enum} of the given enumClass
   * @throws RuntimeException if the given enumClass is null
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given setter is null
   */
  public static <E extends Enum<E>> OptionalValueProperty<E> forEnumWithNameAndSetter(
    final Class<E> enumClass,
    final String name,
    final Consumer<E> setter) {
    Validator.assertThat(enumClass).thatIsNamed("enum class").isNotNull();

    return //
    new OptionalValueProperty<>(
      name,
      setter,
      n -> Enum.valueOf(enumClass, n.getSingleChildNodeHeader()),
      ImmutableNode::fromEnum);
  }

  /**
   * @param name
   * @param setter
   * @return a new {@link OptionalValueProperty} with the given name and setter and that
   *         can store a {@link Integer}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given setter is null
   */
  public static OptionalValueProperty<Integer> forIntWithNameAndSetter(final String name, final Consumer<Integer> setter) {
    return new OptionalValueProperty<>(name, setter, Node::getSingleChildNodeAsInt, ImmutableNode::withChildNode);
  }

  /**
   * @param name
   * @param setter
   * @return a new {@link OptionalValueProperty} with the given name and setter and that
   *         can store a {@link String}
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given setter is null
   */
  public static OptionalValueProperty<String> forStringWithNameAndSetter(final String name, final Consumer<String> setter) {
    return new OptionalValueProperty<>(
      name,
      setter,
      s -> s.getStoredSingleChildNode().getHeaderOrEmptyString(),
      ImmutableNode::fromString);
  }

  /**
   * @param name
   * @param setter
   * @param valueMapper
   * @param specificationMapper
   * @param <T>                 the type of the value of a {@link OptionalValueProperty}
   * @return a new {@link OptionalValueProperty} with the given name, setter, valueMapper
   *         and specificationMapper
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given valueMapper is null
   * @throws RuntimeException if the given specificationMapper is null
   */
  public static <T> OptionalValueProperty<T> withNameAndSetterAndValueMapperAndSpecificationMapper(
    final String name,
    final Consumer<T> setter,
    final Function<Node<?>, T> valueMapper,
    final Function<T, Node<?>> specificationMapper) {
    return new OptionalValueProperty<>(name, setter, valueMapper, specificationMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    memberOptionalValue = null;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<Node<?>> list) {
    if (memberOptionalValue != null) {
      final var attribute = mapValueToSpecification(memberOptionalValue).withNewHeader(getName());

      list.addAtEnd(attribute);
    }
  }

  // For a better performance, this implementation does not use all available comfort methods.
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
        LowerCaseVariableNameCatalog.VALUE);
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
  public void setValue(final V value) {
    Validator.assertThat(value).thatIsNamed(LowerCaseVariableNameCatalog.VALUE).isNotNull();

    memberOptionalValue = value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addOrChangeValue(final V value) {
    setter.accept(value);
  }
}
