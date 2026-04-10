/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.property;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;

import ch.nolix.base.document.node.Node;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.container.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.misc.variable.PascalCaseVariableCatalog;
import ch.nolix.systemapi.property.base.IProperty;

/**
 * @author Silvan Wyss
 */
public final class MutableOptionalSpecificationValueExtractor implements IProperty, INameHolder {
  private final String name;

  private final Consumer<INode<?>> setter;

  private final BooleanSupplier valuePresenceChecker;

  private final Supplier<Node> getter;

  public MutableOptionalSpecificationValueExtractor(
    final String name,
    final Consumer<INode<?>> setter,
    final BooleanSupplier valuePresenceChecker,
    final Supplier<Node> getter) {
    Validator.assertThat(name).thatIsNamed(PascalCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(setter).thatIsNamed(LowerCaseVariableCatalog.SETTER).isNotNull();
    Validator.assertThat(valuePresenceChecker).thatIsNamed("value presence checker").isNotNull();
    Validator.assertThat(getter).thatIsNamed(LowerCaseVariableCatalog.GETTER).isNotNull();

    this.name = name;
    this.setter = setter;
    this.valuePresenceChecker = valuePresenceChecker;
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
  public void fillUpAttributesIntoList(final ILinkedList<INode<?>> list) {
    if (valuePresenceChecker.getAsBoolean()) {
      list.addAtEnd(getter.get());
    }
  }
}
