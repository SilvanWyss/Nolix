/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.element.multistateconfiguration;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.document.node.Node;

/**
 * @author Silvan Wyss
 * @param <S> the type of the states of a {@link ForwardingProperty}.
 * @param <V> the type of the values of a {@link ForwardingProperty}.
 */
public final class ForwardingProperty<S extends Enum<S>, V> extends AbstractProperty<S> {
  private final ExtendedIterable<AbstractMaterializedProperty<S, V>> abstractMaterializedProperties;

  @SafeVarargs
  private ForwardingProperty(
    final String name,
    final AbstractMaterializedProperty<S, V>... materializedProperties) {
    this(name, ImmutableList.fromArray(materializedProperties));
  }

  private ForwardingProperty(
    final String name,
    final ExtendedIterable<AbstractMaterializedProperty<S, V>> materializedProperties) {
    super(name);

    this.abstractMaterializedProperties = LinkedList.fromIterable(materializedProperties);
  }

  @SafeVarargs
  public static <S2 extends Enum<S2>, V2> ForwardingProperty<S2, V2> withNameAndForProperty(
    final String name,
    final AbstractMaterializedProperty<S2, V2>... materializedProperties) {
    return new ForwardingProperty<>(name, materializedProperties);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setUndefined() {
    abstractMaterializedProperties.forEach(AbstractMaterializedProperty::setUndefined);
  }

  public void setValueForState(final S state, final V value) {
    for (final var p : abstractMaterializedProperties) {
      p.setValueForState(state, value);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void fillUpValuesSpecificationInto(ILinkedList<Node<?>> list) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void setFrom(AbstractProperty<S> property) {
    // Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void setValueFromSpecification(final Node<?> specification) {
    for (final var p : abstractMaterializedProperties) {
      p.setValueFromSpecification(specification);
    }
  }
}
