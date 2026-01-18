/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.property;

import java.util.function.Consumer;
import java.util.function.Supplier;

import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.document.node.INode;
import ch.nolix.coreapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.element.property.IProperty;

/**
 * @author Silvan Wyss
 */
public final class MutableSpecificationValueExtractor implements IProperty, INameHolder {
  private final String name;

  private final Consumer<INode<?>> setter;

  private final Supplier<INode<?>> getter;

  public MutableSpecificationValueExtractor(
    final String name,
    final Consumer<INode<?>> setter,
    final Supplier<INode<?>> getter) {
    Validator.assertThat(name).thatIsNamed(PascalCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(setter).thatIsNamed("setter").isNotNull();
    Validator.assertThat(getter).thatIsNamed("getter").isNotNull();

    this.name = name;
    this.setter = setter;
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
      setter.accept(attribute);
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
