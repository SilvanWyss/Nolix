/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.element.property.IProperty;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values a {@link MultiValueProxy} forwards.
 */
public final class MultiValueProxy<V> implements IProperty, INameHolder {
  private final String name;

  private final Consumer<V> adder;

  private final Supplier<IContainer<V>> getter;

  private final Function<INode<?>, V> valueMapper;

  private final Function<V, INode<?>> specificationMapper;

  /**
   * Creates a new {@link MultiValueProxy} with the given name, adder, getter,
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
  public MultiValueProxy(
    final String name,
    final Consumer<V> adder,
    final Supplier<IContainer<V>> getter,
    final Function<INode<?>, V> valueMapper,
    final Function<V, INode<?>> specificationMapper) {
    Validator.assertThat(name).thatIsNamed(PascalCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(adder).thatIsNamed("adder").isNotNull();
    Validator.assertThat(getter).thatIsNamed("getter").isNotNull();
    Validator.assertThat(valueMapper).thatIsNamed("value mapper").isNotNull();
    Validator.assertThat(specificationMapper).thatIsNamed("specification mapper").isNotNull();

    this.name = name;
    this.adder = adder;
    this.getter = getter;
    this.valueMapper = valueMapper;
    this.specificationMapper = specificationMapper;
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
  public boolean addedOrChangedAttribute(final INode<?> attribute) {
    if (attribute.hasHeader(getName())) {
      adder.accept(valueMapper.apply(attribute));

      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    for (final var v : getter.get()) {
      list.addAtEnd(specificationMapper.apply(v));
    }
  }
}
