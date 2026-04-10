/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.property;

import java.util.function.Function;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.attribute.mandatoryattribute.INameHolder;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.systemapi.property.value.IBaseValueProperty;

/**
 * @author Silvan Wyss
 * @param <V> is the type of the values of a {@link AbstractValue}.
 */
public abstract class AbstractValue<V> implements IBaseValueProperty, INameHolder {
  private final String name;

  private final Function<INode<?>, V> valueCreator;

  protected final Function<V, INode<?>> specificationCreator;

  /**
   * Creates a new {@link AbstractValue} with the given name, valueCreator and
   * specificationCreator.
   * 
   * @param name
   * @param valueCreator
   * @param specificationCreator
   * @throws RuntimeException if the given name is null.
   * @throws RuntimeException if the given name is blank.
   * @throws RuntimeException if the given valueCreator is null.
   * @throws RuntimeException if the given specificationCreator is null.
   */
  AbstractValue(
    final String name,
    final Function<INode<?>, V> valueCreator,
    final Function<V, INode<?>> specificationCreator) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();
    Validator.assertThat(valueCreator).thatIsNamed("value creator").isNotNull();
    Validator.assertThat(specificationCreator).thatIsNamed("specificaiton creator").isNotNull();

    this.name = name;
    this.valueCreator = valueCreator;
    this.specificationCreator = specificationCreator;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return name;
  }

  /**
   * Adds or changes the value from the given attribute to the current
   * {@link AbstractValue}.
   * 
   * @param attribute
   */
  @Override
  public final boolean addedOrChangedAttribute(final INode<?> attribute) {
    if (attribute.hasHeader(getName())) {
      addOrChangeValue(valueCreator.apply(attribute));
      return true;
    }

    return false;
  }

  /**
   * Adds or change the given value to the current {@link AbstractValue}.
   * 
   * @param value
   */
  protected abstract void addOrChangeValue(V value);
}
