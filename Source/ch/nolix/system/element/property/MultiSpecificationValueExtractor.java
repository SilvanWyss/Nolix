/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Supplier;

import ch.nolix.base.errorcontrol.validator.Validator;
import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.element.property.IProperty;

/**
 * @author Silvan Wyss
 */
public final class MultiSpecificationValueExtractor implements IProperty, INameHolder {
  private final String name;

  private final Consumer<INode<?>> adder;

  private final Supplier<IContainer<INode<?>>> getter;

  public MultiSpecificationValueExtractor(
    final String name,
    final Consumer<INode<?>> adder,
    final Supplier<IContainer<INode<?>>> getter) {
    Validator.assertThat(name).thatIsNamed(PascalCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(adder).thatIsNamed("adder").isNotNull();
    Validator.assertThat(getter).thatIsNamed("getter").isNotNull();

    this.name = name;
    this.adder = adder;
    this.getter = getter;
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
      adder.accept(attribute);
      return true;
    }

    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUpAttributesInto(final ILinkedList<INode<?>> list) {
    list.addAtEnd(getter.get());
  }
}
