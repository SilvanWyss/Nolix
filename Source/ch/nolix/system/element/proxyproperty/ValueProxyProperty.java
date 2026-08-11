/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.proxyproperty;

import java.util.function.Consumer;
import java.util.function.Supplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.systemapi.element.proxyproperty.IValueProxyProperty;

/**
 * @author Silvan Wyss
 */
public final class ValueProxyProperty implements IValueProxyProperty {
  private final String name;

  private final Consumer<Node<?>> valueSpecificationConsumer;

  private final Supplier<Node<?>> valueSpecificationSupplier;

  /**
   * Creates a new {@link ValueProxyProperty} with the given name,
   * valueSpecificationConsumer and valueSpecificationSupplier.
   * 
   * @param name
   * @param valueSpecificationConsumer
   * @param valueSpecificationSupplier
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given valueSpecificationConsumer is null
   * @throws RuntimeException if the given valueSpecificationSupplier is null
   */
  private ValueProxyProperty(
    final String name,
    final Consumer<Node<?>> valueSpecificationConsumer,
    final Supplier<Node<?>> valueSpecificationSupplier) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableNameCatalog.NAME).isNotBlank();
    Validator.assertThat(valueSpecificationConsumer).thatIsNamed("value specification consumer").isNotNull();
    Validator.assertThat(valueSpecificationSupplier).thatIsNamed("value specification supplier").isNotNull();

    this.name = name;
    this.valueSpecificationConsumer = valueSpecificationConsumer;
    this.valueSpecificationSupplier = valueSpecificationSupplier;
  }

  /**
   * @param name
   * @param valueSpecificationConsumer
   * @param valueSpecificationSupplier
   * @return a new {@link ValueProxyProperty} with the given name,
   *         valueSpecificationConsumer and valueSpecificationSupplier
   * @throws RuntimeException if the given name is null or blank
   * @throws RuntimeException if the given valueSpecificationConsumer is null
   * @throws RuntimeException if the given valueSpecificationSupplier is null
   */
  public static ValueProxyProperty withNameAndValueSpecificationConsumerAndValueSpecificationSupplier(
    final String name,
    final Consumer<Node<?>> valueSpecificationConsumer,
    final Supplier<Node<?>> valueSpecificationSupplier) {
    return new ValueProxyProperty(name, valueSpecificationConsumer, valueSpecificationSupplier);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addedOrChangedAttribute(final Node<?> attribute) {
    if (attribute != null && attribute.hasHeader(getName())) {
      valueSpecificationConsumer.accept(attribute);

      return true;
    }

    return false;
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
  public void fillUpAttributesIntoList(final ILinkedList<Node<?>> list) {
    final var attribute = valueSpecificationSupplier.get();

    list.addAtEnd(attribute);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }
}
