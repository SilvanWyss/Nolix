/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.view;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.property.view.IMultiValueView;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values a {@link MultiValueView} forwards.
 */
public final class MultiValueView<V> implements IMultiValueView {
  private final String name;

  private final Consumer<V> adder;

  private final Supplier<IContainer<V>> getter;

  private final Function<INode<?>, V> valueMapper;

  private final Function<V, INode<?>> specificationMapper;

  /**
   * Creates a new {@link MultiValueView} with the given name, adder, getter,
   * valueMapper and specificationMapper.
   * 
   * @param name
   * @param adder
   * @param getter
   * @param valueMapper
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given adder is null.
   * @throws RuntimeException if the given getter is null.
   * @throws RuntimeException if the given valueMapper is null.
   * @throws RuntimeException if the given specificationMapper is null.
   */
  private MultiValueView(
    final String name,
    final Consumer<V> adder,
    final Supplier<IContainer<V>> getter,
    final Function<INode<?>, V> valueMapper,
    final Function<V, INode<?>> specificationMapper) {
    Validator.assertThat(name).thatIsNamed(PascalCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(adder).thatIsNamed(LowerCaseVariableCatalog.ADDER).isNotNull();
    Validator.assertThat(getter).thatIsNamed(LowerCaseVariableCatalog.GETTER).isNotNull();
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
   * @param <T>                 is the type of the values the created
   *                            {@link MultiValueView} forwards.
   * @return a new {@link MultiValueView} with the given name, adder, getter,
   *         valueMapper and specificationMapper.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given adder is null.
   * @throws RuntimeException if the given getter is null.
   * @throws RuntimeException if the given valueMapper is null.
   * @throws RuntimeException if the given specificationMapper is null.
   */
  public static <T> MultiValueView<T> withNameAndAdderAndGetterAndValueMapperAndSpecificationMapper(
    final String name,
    final Consumer<T> adder,
    final Supplier<IContainer<T>> getter,
    final Function<INode<?>, T> valueMapper,
    final Function<T, INode<?>> specificationMapper) {
    return new MultiValueView<>(name, adder, getter, valueMapper, specificationMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addedOrChangedAttribute(final INode<?> attribute) {
    if (attribute.hasHeader(getName())) {
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
  public void fillUpAttributesIntoList(final ILinkedList<INode<?>> list) {
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
