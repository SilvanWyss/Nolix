/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.proxy;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.systemapi.property.proxy.IMultiValueProxy;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values a {@link MultiValueProxy} forwards.
 */
public final class MultiValueProxy<V> implements IMultiValueProxy {
  private final String name;

  private final Consumer<V> adder;

  private final Supplier<ExtendedIterable<V>> getter;

  private final Function<Node<?>, V> valueMapper;

  private final Function<V, Node<?>> specificationMapper;

  /**
   * Creates a new {@link MultiValueProxy} with the given name, adder, getter,
   * valueMapper and specificationMapper.
   * 
   * @param name
   * @param adder
   * @param getter
   * @param valueMapper
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   * @throws RuntimeException if the given getter is null
   * @throws RuntimeException if the given valueMapper is null
   * @throws RuntimeException if the given specificationMapper is null
   */
  private MultiValueProxy(
    final String name,
    final Consumer<V> adder,
    final Supplier<ExtendedIterable<V>> getter,
    final Function<Node<?>, V> valueMapper,
    final Function<V, Node<?>> specificationMapper) {
    Validator.assertThat(name).thatIsNamed(PascalCaseVariableNameCatalog.NAME).isNotBlank();
    Validator.assertThat(adder).thatIsNamed(LowerCaseVariableNameCatalog.ADDER).isNotNull();
    Validator.assertThat(getter).thatIsNamed(LowerCaseVariableNameCatalog.GETTER).isNotNull();
    Validator.assertThat(valueMapper).thatIsNamed("value mapper").isNotNull();
    Validator.assertThat(specificationMapper).thatIsNamed("specification mapper").isNotNull();

    this.name = name;
    this.adder = adder;
    this.getter = getter;
    this.valueMapper = valueMapper;
    this.specificationMapper = specificationMapper;
  }

  /**
   * @param name
   * @param adder
   * @param getter
   * @param valueMapper
   * @param specificationMapper
   * @param <T>                 the type of the values the created
   *                            {@link MultiValueProxy} forwards
   * @return a new {@link MultiValueProxy} with the given name, adder, getter,
   *         valueMapper and specificationMapper
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given adder is null
   * @throws RuntimeException if the given getter is null
   * @throws RuntimeException if the given valueMapper is null
   * @throws RuntimeException if the given specificationMapper is null
   */
  public static <T> MultiValueProxy<T> withNameAndAdderAndGetterAndValueMapperAndSpecificationMapper(
    final String name,
    final Consumer<T> adder,
    final Supplier<ExtendedIterable<T>> getter,
    final Function<Node<?>, T> valueMapper,
    final Function<T, Node<?>> specificationMapper) {
    return new MultiValueProxy<>(name, adder, getter, valueMapper, specificationMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addedOrChangedAttribute(final Node<?> attribute) {
    if (attribute != null && attribute.hasHeader(getName())) {
      final var value = valueMapper.apply(attribute);

      adder.accept(value);

      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesIntoList(final ILinkedList<Node<?>> list) {
    for (final var v : getter.get()) {
      final var attribute = specificationMapper.apply(v);

      list.addAtEnd(attribute);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return getter.get().isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }
}
