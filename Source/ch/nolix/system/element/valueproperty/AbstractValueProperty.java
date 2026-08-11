/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.valueproperty;

import java.util.function.Function;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.element.valueproperty.BaseValueProperty;

/**
 * @author Silvan Wyss
 * @param <V> the type of the values of a {@link AbstractValueProperty}.
 */
public abstract class AbstractValueProperty<V> implements BaseValueProperty {
  private final String name;

  private final Function<Node<?>, V> valueMapper;

  private final Function<V, Node<?>> specificationMapper;

  /**
   * Creates a new {@link AbstractValueProperty} with the given name, valueMapper
   * and specificationMapper.
   * 
   * @param name
   * @param valueMapper
   * @param specificationMapper
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given valueMapper is null
   * @throws RuntimeException if the given specificationMapper is null
   */
  protected AbstractValueProperty(
    final String name,
    final Function<Node<?>, V> valueMapper,
    final Function<V, Node<?>> specificationMapper) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();
    Validator.assertThat(valueMapper).thatIsNamed("value mapper").isNotNull();
    Validator.assertThat(specificationMapper).thatIsNamed("specification mapper").isNotNull();

    this.name = name;
    this.valueMapper = valueMapper;
    this.specificationMapper = specificationMapper;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean addedOrChangedAttribute(final Node<?> attribute) {
    if (attribute != null && attribute.hasHeader(getName())) {
      final var value = valueMapper.apply(attribute);

      addOrChangeValue(value);

      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isMaterialized() {
    return true;
  }

  /**
   * Adds or change the given value to the current {@link AbstractValueProperty}.
   * 
   * @param value
   */
  protected abstract void addOrChangeValue(V value);

  /**
   * @param value
   * @return the specification from the given value.
   */
  protected final Node<?> mapValueToSpecification(final V value) {
    return specificationMapper.apply(value);
  }
}
