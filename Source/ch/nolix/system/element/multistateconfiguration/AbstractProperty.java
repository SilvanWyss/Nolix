/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.attribute.mandatoryattribute.NameHolder;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.INode;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <S> is the type of the {@link Enum} representation of the state of the
 *            {@link AbstractMultiStateConfiguration} of a
 *            {@link AbstractProperty}.
 */
public abstract class AbstractProperty<S extends Enum<S>> implements NameHolder {
  private final String name;

  protected AbstractMultiStateConfiguration<?, S> parent;

  protected AbstractProperty(final String name) {
    Validator.assertThat(name).thatIsNamed(LowerCaseVariableCatalog.NAME).isNotBlank();

    this.name = name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String getName() {
    return name;
  }

  protected abstract void fillUpValuesSpecificationInto(ILinkedList<INode<?>> list);

  protected abstract void setFrom(AbstractProperty<S> property);

  protected abstract void setUndefined();

  protected abstract void setValueFromSpecification(INode<?> specification);

  final void setParent(final AbstractMultiStateConfiguration<?, S> parent) {
    Validator.assertThat(parent).thatIsNamed(LowerCaseVariableCatalog.PARENT).isNotNull();

    this.parent = parent;
  }
}
