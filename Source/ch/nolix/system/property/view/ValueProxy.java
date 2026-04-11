/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.property.view;

import java.util.function.Consumer;
import java.util.function.Supplier;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.property.base.IProperty;

/**
 * @author Silvan Wyss
 */
public final class ValueProxy implements IProperty, INameHolder {
  private final String name;

  private final Consumer<INode<?>> valueSpecificationConsumer;

  private final Supplier<INode<?>> valueSpecificationSupplier;

  /**
   * Creates a new {@link ValueProxy} with the given name,
   * valueSpecificationConsumer and valueSpecificationSupplier.
   * 
   * @param name
   * @param valueSpecificationConsumer
   * @param valueSpecificationSupplier
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given valueSpecificationConsumer is null.
   * @throws RuntimeException if the given valueSpecificationSupplier is null.
   */
  private ValueProxy(
    final String name,
    final Consumer<INode<?>> valueSpecificationConsumer,
    final Supplier<INode<?>> valueSpecificationSupplier) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
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
   * @return a new {@link ValueProxy} with the given name,
   *         valueSpecificationConsumer and valueSpecificationSupplier.
   * @throws RuntimeException if the given name is null or blank.
   * @throws RuntimeException if the given valueSpecificationConsumer is null.
   * @throws RuntimeException if the given valueSpecificationSupplier is null.
   */
  public static ValueProxy withNameAndValueSpecificationConsumerAndValueSpecificationSupplier(
    final String name,
    final Consumer<INode<?>> valueSpecificationConsumer,
    final Supplier<INode<?>> valueSpecificationSupplier) {
    return new ValueProxy(name, valueSpecificationConsumer, valueSpecificationSupplier);
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
  public void fillUpAttributesIntoList(final ILinkedList<INode<?>> list) {
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
